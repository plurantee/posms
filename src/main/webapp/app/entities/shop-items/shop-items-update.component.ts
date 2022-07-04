import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import { IShopItems, ShopItems } from '@/shared/model/shop-items.model';
import ShopItemsService from './shop-items.service';

const validations: any = {
  shopItems: {
    stock: {},
    price: {},
  },
};

@Component({
  validations,
})
export default class ShopItemsUpdate extends Vue {
  @Inject('shopItemsService') private shopItemsService: () => ShopItemsService;
  @Inject('alertService') private alertService: () => AlertService;

  public shopItems: IShopItems = new ShopItems();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.shopItemsId) {
        vm.retrieveShopItems(to.params.shopItemsId);
      }
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
    if (this.shopItems.id) {
      this.shopItemsService()
        .update(this.shopItems)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A ShopItems is updated with identifier ' + param.id;
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
      this.shopItemsService()
        .create(this.shopItems)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A ShopItems is created with identifier ' + param.id;
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

  public retrieveShopItems(shopItemsId): void {
    this.shopItemsService()
      .find(shopItemsId)
      .then(res => {
        this.shopItems = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
