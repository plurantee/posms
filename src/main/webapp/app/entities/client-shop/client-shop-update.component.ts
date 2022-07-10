import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import LazadaOrderService from '@/entities/lazada-order/lazada-order.service';
import { ILazadaOrder } from '@/shared/model/lazada-order.model';

import ClientService from '@/entities/client/client.service';
import { IClient } from '@/shared/model/client.model';

import { IShop, Shop } from '@/shared/model/shop.model';
import { ShopType } from '@/shared/model/enumerations/shop-type.model';
import AccountService from '@/account/account.service';
import ClientShopService from './client-shop.service';

const validations: any = {
  shop: {
    shopCode: {},
    shopName: {},
    shopType: {},
  },
};

@Component({
  validations,
})
export default class ClientShopUpdate extends Vue {
  @Inject('clientShopService') private shopService: () => ClientShopService;
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('accountService') private accountService: () => AccountService;

  public shop: IShop = new Shop();

  @Inject('lazadaOrderService') private lazadaOrderService: () => LazadaOrderService;

  public lazadaOrders: ILazadaOrder[] = [];

  @Inject('clientService') private clientService: () => ClientService;

  public clients: IClient[] = [];
  public shopTypeValues: string[] = Object.keys(ShopType);
  public isSaving = false;
  public currentLanguage = '';
  private hasAnyAuthorityValue = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.shopId) {
        vm.retrieveShop(to.params.shopId);
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
    if (this.shop.id) {
      this.shopService()
        .update(this.shop)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Shop is updated with identifier ' + param.id;
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
      this.shopService()
        .create(this.shop)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Shop is created with identifier ' + param.id;
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

  public retrieveShop(shopId): void {
    this.shopService()
      .find(shopId)
      .then(res => {
        this.shop = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.lazadaOrderService()
      .retrieve()
      .then(res => {
        this.lazadaOrders = res.data;
      });
    this.clientService()
      .retrieve()
      .then(res => {
        this.clients = res.data;
      });
  }

  public hasAnyAuthority(authorities: any): boolean {
    this.accountService()
      .hasAnyAuthorityAndCheckAuth(authorities)
      .then(value => {
        this.hasAnyAuthorityValue = value;
      });
    return this.hasAnyAuthorityValue;
  }
}
