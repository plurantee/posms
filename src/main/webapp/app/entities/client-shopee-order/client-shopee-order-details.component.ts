import { Component, Vue, Inject } from 'vue-property-decorator';

import { IShopeeOrder } from '@/shared/model/shopee-order.model';
import ShopeeOrderService from './client-shopee-order.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class ClientShopeeOrderDetails extends Vue {
  @Inject('shopeeOrderService') private shopeeOrderService: () => ShopeeOrderService;
  @Inject('alertService') private alertService: () => AlertService;

  public shopeeOrder: IShopeeOrder = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.shopeeOrderId) {
        vm.retrieveShopeeOrder(to.params.shopeeOrderId);
      }
    });
  }

  public retrieveShopeeOrder(shopeeOrderId) {
    this.shopeeOrderService()
      .find(shopeeOrderId)
      .then(res => {
        this.shopeeOrder = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
