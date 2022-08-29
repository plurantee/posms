import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import axios from 'axios';
import { IOrderTracker, OrderTracker } from '@/shared/model/order-tracker.model';
import AlertService from '@/shared/alert/alert.service';
import dayjs from 'dayjs';
import { DATE_TIME_FORMAT, DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

const baseApiUrl = 'api/order-tracker';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class ClientOrderTracker extends Vue {
  @Inject('alertService') private clientAlertService: () => AlertService;
  public barcode: string | string[] = '';
  public orderTrackers: IOrderTracker[] = [];
  public isFetching = false;
  public file = null;
  public startDate = dayjs().add(-1, 'day').format(DATE_TIME_FORMAT);
  public endDate = dayjs().format(DATE_TIME_FORMAT);
  public site = 'all';
  public status = 'all';
  public nav: string | string[] = 'waybill';

  public mounted(): void {
    
    this.clear();
  }

  public clear(): void {
    if (this.$route.query?.barcodeNumber) {
      this.barcode= this.$route.query.barcodeNumber;
      this.searchText();
    }
  }

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

  public loadByCondition() {
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

  public search(query: string | string[]): Promise<any> {
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

  public uploadFile( event ): void {
    this.file = event.target.files[0];
    this.isFetching = true;
    // eslint-disable-next-line prefer-const
    let formData = new FormData();
    formData.append('file', this.file);
    this.initUploadLazadaWaybill(formData).then(
      res => {
        this.orderTrackers = res.data;
        this.barcode = this.orderTrackers[0].barcodeNumber;
        this.isFetching = false;
      },
      err => {
        this.isFetching = false;
        this.clientAlertService().showHttpError(this, err.response);
      }
    );


  }

  public submitFile(): void {
    this.isFetching = true;
    // eslint-disable-next-line prefer-const
    let formData = new FormData();
    formData.append('file', this.file);
    formData.append('orders', JSON.stringify(this.orderTrackers));
    this.uploadLazadaWaybill(formData).then(
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

  public initUploadLazadaWaybill(file: FormData) {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/init-upload`, file, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        })
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public uploadLazadaWaybill(file: FormData) {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/upload`, file, {
          headers: {
            'Content-Type': 'multipart/form-data',
            Accept: 'application/pdf',
          },
          responseType: 'blob',
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

  public releaseOrders() {
    return new Promise<any>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}/release`, this.orderTrackers)
        .then(res => {
          resolve(res.data);
          this.orderTrackers = res.data;
          return this.$root.$bvToast.toast('Orders Released', {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public cancelOrders() {
    return new Promise<any>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}/cancel`, this.orderTrackers)
        .then(res => {
          resolve(res.data);
          this.orderTrackers = res.data;
          return this.$root.$bvToast.toast('Orders Cancelled', {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public returnOrders() {
    return new Promise<any>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}/return`, this.orderTrackers)
        .then(res => {
          resolve(res.data);
          this.orderTrackers = res.data;
          return this.$root.$bvToast.toast('Orders Returned', {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public updateStartDate(event) {
    if (event.target.value) {
      this.startDate = dayjs(event.target.value).format(DATE_TIME_FORMAT);
    } else {
      this.startDate = null;
    }
  }
  public updateEndDate(event) {
    if (event.target.value) {
      this.endDate = dayjs(event.target.value).format(DATE_TIME_FORMAT);
    } else {
      this.endDate = null;
    }
  }

  public switchNav(value: string) {
    this.orderTrackers = [];
    this.nav = value;
  }

  public query() {
    // eslint-disable-next-line prefer-const
    let formData = new FormData();
    // eslint-disable-next-line prefer-const
    let yesterday = dayjs().add(-1, 'day').format(DATE_TIME_FORMAT);
    // START DATE
    if (this.startDate) {
      formData.append('startDate', this.startDate);
    } else {
      formData.append('startDate', yesterday);
    }

    // END DATE
    if (this.endDate) {
      formData.append('endDate', this.endDate);
    } else {
      formData.append('endDate', dayjs().format(DATE_TIME_FORMAT));
    }

    // SITE
    if (this.site) {
      formData.append('site', this.site);
    } else {
      formData.append('site', 'all');
    }

    // STATUS
    if (this.status) {
      formData.append('status', this.status);
    } else {
      formData.append('status', 'all');
    }

    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/query`, formData)
        .then(res => {
          resolve(res.data);
          this.orderTrackers = res.data;
        this.isFetching = false;
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
