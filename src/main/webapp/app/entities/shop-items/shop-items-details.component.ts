import { Component, Vue, Inject } from 'vue-property-decorator';

import { IShopItems } from '@/shared/model/shop-items.model';
import ShopItemsService from './shop-items.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class ShopItemsDetails extends Vue {
  @Inject('shopItemsService') private shopItemsService: () => ShopItemsService;
  @Inject('alertService') private alertService: () => AlertService;

  public shopItems: IShopItems = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.shopItemsId) {
        vm.retrieveShopItems(to.params.shopItemsId);
      }
    });
  }

  public retrieveShopItems(shopItemsId) {
    this.shopItemsService()
      .find(shopItemsId)
      .then(res => {
        this.shopItems = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
