import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IShopeeOrder } from '@/shared/model/shopee-order.model';

import ShopeeOrderService from './shopee-order.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class ShopeeOrder extends Vue {
  @Inject('shopeeOrderService') private shopeeOrderService: () => ShopeeOrderService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public shopeeOrders: IShopeeOrder[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllShopeeOrders();
  }

  public clear(): void {
    this.retrieveAllShopeeOrders();
  }

  public retrieveAllShopeeOrders(): void {
    this.isFetching = true;
    this.shopeeOrderService()
      .retrieve()
      .then(
        res => {
          this.shopeeOrders = res.data;
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

  public prepareRemove(instance: IShopeeOrder): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeShopeeOrder(): void {
    this.shopeeOrderService()
      .delete(this.removeId)
      .then(() => {
        const message = 'A ShopeeOrder is deleted with identifier ' + this.removeId;
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllShopeeOrders();
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
