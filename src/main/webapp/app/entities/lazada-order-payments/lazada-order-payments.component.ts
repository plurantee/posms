import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ILazadaOrderPayments } from '@/shared/model/lazada-order-payments.model';

import LazadaOrderPaymentsService from './lazada-order-payments.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class LazadaOrderPayments extends Vue {
  @Inject('lazadaOrderPaymentsService') private lazadaOrderPaymentsService: () => LazadaOrderPaymentsService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public lazadaOrderPayments: ILazadaOrderPayments[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllLazadaOrderPaymentss();
  }

  public clear(): void {
    this.retrieveAllLazadaOrderPaymentss();
  }

  public retrieveAllLazadaOrderPaymentss(): void {
    this.isFetching = true;
    this.lazadaOrderPaymentsService()
      .retrieve()
      .then(
        res => {
          this.lazadaOrderPayments = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
          this.alertService().showHttpError(this, err.response);
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: ILazadaOrderPayments): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeLazadaOrderPayments(): void {
    this.lazadaOrderPaymentsService()
      .delete(this.removeId)
      .then(() => {
        const message = 'A LazadaOrderPayments is deleted with identifier ' + this.removeId;
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllLazadaOrderPaymentss();
        this.closeDialog();
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
