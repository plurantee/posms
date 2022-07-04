import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IShopItems } from '@/shared/model/shop-items.model';

import ShopItemsService from './shop-items.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class ShopItems extends Vue {
  @Inject('shopItemsService') private shopItemsService: () => ShopItemsService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public shopItems: IShopItems[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllShopItemss();
  }

  public clear(): void {
    this.retrieveAllShopItemss();
  }

  public retrieveAllShopItemss(): void {
    this.isFetching = true;
    this.shopItemsService()
      .retrieve()
      .then(
        res => {
          this.shopItems = res.data;
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

  public prepareRemove(instance: IShopItems): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeShopItems(): void {
    this.shopItemsService()
      .delete(this.removeId)
      .then(() => {
        const message = 'A ShopItems is deleted with identifier ' + this.removeId;
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllShopItemss();
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
