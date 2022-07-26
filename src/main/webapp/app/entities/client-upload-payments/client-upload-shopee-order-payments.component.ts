import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ILazadaOrderPayments, LazadaOrderPayments } from '@/shared/model/lazada-order-payments.model';

import AlertService from '@/shared/alert/alert.service';
import LazadaOrderPaymentsService from '../lazada-order-payments/lazada-order-payments.service';

import axios from 'axios';
import { IShopeeOrderPayments } from '@/shared/model/shopee-order-payments.model';

const baseApiUrl = 'api/order/payments';

@Component({
  mixins: [Vue2Filters.mixin],
  props: {
    orderId: {
      required: false,
      type: Object as () => string,
    },
  },
})
export default class ClientShopeeOrderPayments extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  public isFetching = false;
  public shopeeOrderPayments: IShopeeOrderPayments[] = [];
  private file = null;
  private hasNoOrderId = true;
  public totalPayments = 0;
  private removeId: number = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;
  public mounted(): void {
    this.clear();
  }

  public clear(): void {
    if (this.$props?.orderId) {
      this.hasNoOrderId = false;

      this.getPayments(this.$props.orderId).then(
        res => {
          this.shopeeOrderPayments = res;
          this.shopeeOrderPayments.forEach(element => {
            this.totalPayments = this.totalPayments + element.totalReleasedAmount;
          });
          return this.$root.$bvToast.toast(this.file.name + ' Uploaded', {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        },
        err => {
          this.alertService().showHttpError(this, err.response);
        }
      );
    }
  }

  public uploadFile(): void {
    this.file = (<HTMLInputElement>this.$refs.file).files[0];
  }

  public submitFile(): void {
    this.isFetching = true;
    // eslint-disable-next-line prefer-const
    let formData = new FormData();
    formData.append('file', this.file);
    this.uploadShopeeExcel(formData).then(
      res => {
        this.shopeeOrderPayments = res;
        return this.$root.$bvToast.toast(this.file.name + ' Uploaded', {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'info',
          solid: true,
          autoHideDelay: 5000,
        });
      },
      err => {
        this.alertService().showHttpError(this, err.response);
      }
    );
  }

  public uploadShopeeExcel(file: FormData) {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/shopee/upload`, file, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        })
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public getPayments(orderId: string) {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(`api/shopee-order-payments/by-order-id/` + orderId)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public sort(): Array<any> {
    const result = [this.propOrder + ',' + (this.reverse ? 'desc' : 'asc')];
    if (this.propOrder !== 'id') {
      result.push('id');
    }
    return result;
  }

  public loadPage(page: number): void {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  public transition(): void {
    //this.retrieveAllShopeeOrderPaymentss();
  }

  public changeOrder(propOrder): void {
    this.propOrder = propOrder;
    this.reverse = !this.reverse;
    this.transition();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
