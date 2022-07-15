import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ILazadaOrder } from '@/shared/model/lazada-order.model';

import LazadaOrderService from './lazada-order.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class LazadaOrder extends Vue {
  @Inject('lazadaOrderService') private lazadaOrderService: () => LazadaOrderService;
  @Inject('alertService') private alertService: () => AlertService;

  protected removeId: number = null;

  public lazadaOrders: ILazadaOrder[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllLazadaOrders();
  }

  public clear(): void {
    this.retrieveAllLazadaOrders();
  }

  public retrieveAllLazadaOrders(): void {
    this.isFetching = true;
    this.lazadaOrderService()
      .retrieve()
      .then(
        res => {
          this.lazadaOrders = res.data;
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

  public prepareRemove(instance: ILazadaOrder): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeLazadaOrder(): void {
    this.lazadaOrderService()
      .delete(this.removeId)
      .then(() => {
        const message = 'A LazadaOrder is deleted with identifier ' + this.removeId;
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllLazadaOrders();
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
