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
}
