import Component from 'vue-class-component';
import { Inject, Provide, Vue } from 'vue-property-decorator';
import LoginService from '@/account/login.service';
import Home from '../home/home.component';
import ClientLazadaOrderService from '@/entities/client-lazada-order/client-lazada-order.service';
import ClientShopeeOrderService from '@/entities/client-shopee-order/client-shopee-order.service';

const Dashboard = () => import('@/entities/dashboard/dashboard.vue');
const ClientLazadaOrder = () => import('@/entities/client-lazada-order/client-lazada-order.vue');
const ClientShopeeOrder = () => import('@/entities/client-shopee-order/client-shopee-order.vue');
const ClientBaseOrderPayments = () => import('@/entities/client-upload-payments/client-base-upload-order-payments.vue');
const ClientOrderTracker = () => import('@/entities/client-order-tracker/client-order-tracker.vue');
@Component({
  components: {
    'client-lazada-order': ClientLazadaOrder,
    'client-shopee-order': ClientShopeeOrder,
    'client-payments': ClientBaseOrderPayments,
    'client-order-tracker': ClientOrderTracker,
    'client-dashboard': Dashboard,
  },
})
export default class ClientHome extends Home {
  @Inject('clientLazadaOrderService') private clientLazadaOrderService = () => new ClientLazadaOrderService();
  @Inject('loginService') private clientShopeeOrderService = () => new ClientShopeeOrderService();
  public nav: string | string[] = 'dashboard';
  public initNav = null;
  public barcodeNumber = null;
  public mounted(): void {
    if (this.$route.query?.path) {
      this.nav = this.$route.query.path;
    }
    if (this.$route.query?.barcodeNumber) {
      this.barcodeNumber = this.$route.query.barcodeNumber;
    }
  }

  public switchNav(value: string) {
    this.nav = value;
  }
}
