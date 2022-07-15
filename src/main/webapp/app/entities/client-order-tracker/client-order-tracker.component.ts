import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import axios from 'axios';
import { IOrderTracker, OrderTracker } from '@/shared/model/order-tracker.model';
import AlertService from '@/shared/alert/alert.service';

const baseApiUrl = 'api/order-tracker';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class ClientOrderTracker extends Vue {
  @Inject('alertService') private clientAlertService: () => AlertService;
  public barcode = '';
  public orderTrackers: IOrderTracker[] = [];
  public isFetching = false;
  private file = null;

  public searchText() {
    this.search(this.barcode).then(
      res => {
        this.orderTrackers = res.data;
        this.isFetching = false;
        this.barcode = '';
      },
      err => {
        this.isFetching = false;
        this.clientAlertService().showHttpError(this, err.response);
      }
    );
  }

  public search(query: string): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/` + query)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });

      (<HTMLInputElement>this.$refs.barcode).focus();
    });
  }

  public uploadFile(): void {
    this.file = (<HTMLInputElement>this.$refs.file).files[0];
  }

  public submitFile(): void {
    this.isFetching = true;
    // eslint-disable-next-line prefer-const
    let formData = new FormData();
    formData.append('file', this.file);
    formData.append('orders', JSON.stringify(this.orderTrackers));
    this.uploadLazadaWaybill(formData)
      .then(
        res => {
          return this.$root.$bvToast.toast('Downloading new Waybill Uploaded', {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        },
        err => {
          this.isFetching = false;
          this.clientAlertService().showHttpError(this, err.response);
        }
      );
  }

  public uploadLazadaWaybill(file: FormData) {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/upload`, file, {
          headers: {
            'Content-Type': 'multipart/form-data',
            'Accept': 'application/pdf'
          },
          responseType: 'blob'
        })
        .then(res => {
          const blob = new Blob([res.data], { type: 'application/pdf' });
          const objectUrl = window.URL.createObjectURL(blob);
          window.open(objectUrl);
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
