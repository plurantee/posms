import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import UserInfoService from '@/entities/user-info/user-info.service';
import { IUserInfo } from '@/shared/model/user-info.model';

import ShopService from '@/entities/shop/shop.service';
import { IShop } from '@/shared/model/shop.model';

import LazadaOrderService from '@/entities/lazada-order/lazada-order.service';
import { ILazadaOrder } from '@/shared/model/lazada-order.model';

import ShopeeOrderService from '@/entities/shopee-order/shopee-order.service';
import { IShopeeOrder } from '@/shared/model/shopee-order.model';

import { IClient, Client } from '@/shared/model/client.model';
import ClientService from './client.service';

const validations: any = {
  client: {
    clientName: {},
    clientCode: {},
  },
};

@Component({
  validations,
})
export default class ClientUpdate extends Vue {
  @Inject('clientService') private clientService: () => ClientService;
  @Inject('alertService') private alertService: () => AlertService;

  public client: IClient = new Client();

  @Inject('userInfoService') private userInfoService: () => UserInfoService;

  public userInfos: IUserInfo[] = [];

  @Inject('shopService') private shopService: () => ShopService;

  public shops: IShop[] = [];

  @Inject('lazadaOrderService') private lazadaOrderService: () => LazadaOrderService;

  public lazadaOrders: ILazadaOrder[] = [];

  @Inject('shopeeOrderService') private shopeeOrderService: () => ShopeeOrderService;

  public shopeeOrders: IShopeeOrder[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.clientId) {
        vm.retrieveClient(to.params.clientId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.client.id) {
      this.clientService()
        .update(this.client)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Client is updated with identifier ' + param.id;
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    } else {
      this.clientService()
        .create(this.client)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Client is created with identifier ' + param.id;
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    }
  }

  public retrieveClient(clientId): void {
    this.clientService()
      .find(clientId)
      .then(res => {
        this.client = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.userInfoService()
      .retrieve()
      .then(res => {
        this.userInfos = res.data;
      });
    this.shopService()
      .retrieve()
      .then(res => {
        this.shops = res.data;
      });
    this.lazadaOrderService()
      .retrieve()
      .then(res => {
        this.lazadaOrders = res.data;
      });
    this.shopeeOrderService()
      .retrieve()
      .then(res => {
        this.shopeeOrders = res.data;
      });
  }
}
