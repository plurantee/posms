import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import ClientService from '@/entities/client/client.service';
import { IClient } from '@/shared/model/client.model';

import { IShop, Shop } from '@/shared/model/shop.model';
import ShopService from './shop.service';
import { ShopType } from '@/shared/model/enumerations/shop-type.model';

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
export default class ShopUpdate extends Vue {
  @Inject('shopService') private shopService: () => ShopService;
  @Inject('alertService') private alertService: () => AlertService;

  public shop: IShop = new Shop();

  @Inject('clientService') private clientService: () => ClientService;

  public clients: IClient[] = [];
  public shopTypeValues: string[] = Object.keys(ShopType);
  public isSaving = false;
  public currentLanguage = '';

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
    this.clientService()
      .retrieve()
      .then(res => {
        this.clients = res.data;
      });
  }
}