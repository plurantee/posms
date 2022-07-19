import Component from 'vue-class-component';
import { Inject, Provide, Vue } from 'vue-property-decorator';
import LoginService from '@/account/login.service';
import Home from '../home/home.component';
import ClientLazadaOrderService from '@/entities/client-lazada-order/client-lazada-order.service';
import ClientShopeeOrderService from '@/entities/client-shopee-order/client-shopee-order.service';

const ClientLazadaOrder = () => import('@/entities/client-lazada-order/client-lazada-order.vue');
const ClientShopeeOrder = () => import('@/entities/client-shopee-order/client-shopee-order.vue');
const ClientUploadLazadaOrderPayment = () => import('@/entities/client-lazada-order-payments/client-upload-lazada-order-payments.vue');
const ClientOrderTracker = () => import('@/entities/client-order-tracker/client-order-tracker.vue');
@Component({
  components: {
    'client-lazada-order': ClientLazadaOrder,
    'client-shopee-order': ClientShopeeOrder,
    'client-lazada-payments': ClientUploadLazadaOrderPayment,
    'client-order-tracker': ClientOrderTracker,
  },
})
export default class ClientHome extends Home {
  @Inject('clientLazadaOrderService') private clientLazadaOrderService = () => new ClientLazadaOrderService();
  @Inject('loginService') private clientShopeeOrderService = () => new ClientShopeeOrderService();
  public shopNav = 'lazada';

  public switchNav(value: string) {
    this.shopNav = value;
  }
}
