import { Component, Vue, Inject } from 'vue-property-decorator';

import { IShop } from '@/shared/model/shop.model';
import AlertService from '@/shared/alert/alert.service';
import ClientShopService from './client-shop.service';
const ClientLazadaOrder = () => import('@/entities/client-lazada-order/client-lazada-order.vue');
const ClientShopeeOrder = () => import('@/entities/client-shopee-order/client-shopee-order.vue');

@Component({
  components: {
    'client-lazada-order': ClientLazadaOrder,
    'client-shopee-order': ClientShopeeOrder,
  },
})
export default class ClientShopDetails extends Vue {
  @Inject('clientShopService') private shopService: () => ClientShopService;
  @Inject('alertService') private alertService: () => AlertService;

  public shop: IShop = {};
  public shopNav = 'lazada';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.shopId) {
        vm.retrieveShop(to.params.shopId);
      }
    });
  }

  public retrieveShop(shopId) {
    this.shopService()
      .find(shopId)
      .then(res => {
        this.shop = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public switchNav(value: string) {
    this.shopNav = value;
  }
}
