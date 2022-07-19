import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import LazadaOrderService from '@/entities/lazada-order/lazada-order.service';
import { ILazadaOrder } from '@/shared/model/lazada-order.model';

import ShopeeOrderService from '@/entities/shopee-order/shopee-order.service';
import { IShopeeOrder } from '@/shared/model/shopee-order.model';

import ClientService from '@/entities/client/client.service';
import { IClient } from '@/shared/model/client.model';

import { IInventory, Inventory } from '@/shared/model/inventory.model';
import InventoryService from './inventory.service';

const validations: any = {
  inventory: {
    sku: {},
    stocks: {},
    cost: {},
    price: {},
    threshold: {},
  },
};

@Component({
  validations,
})
export default class InventoryUpdate extends Vue {
  @Inject('inventoryService') private inventoryService: () => InventoryService;
  @Inject('alertService') private alertService: () => AlertService;

  public inventory: IInventory = new Inventory();

  @Inject('lazadaOrderService') private lazadaOrderService: () => LazadaOrderService;

  public lazadaOrders: ILazadaOrder[] = [];

  @Inject('shopeeOrderService') private shopeeOrderService: () => ShopeeOrderService;

  public shopeeOrders: IShopeeOrder[] = [];

  @Inject('clientService') private clientService: () => ClientService;

  public clients: IClient[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.inventoryId) {
        vm.retrieveInventory(to.params.inventoryId);
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
    if (this.inventory.id) {
      this.inventoryService()
        .update(this.inventory)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Inventory is updated with identifier ' + param.id;
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
      this.inventoryService()
        .create(this.inventory)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Inventory is created with identifier ' + param.id;
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

  public retrieveInventory(inventoryId): void {
    this.inventoryService()
      .find(inventoryId)
      .then(res => {
        this.inventory = res;
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
    this.shopeeOrderService()
      .retrieve()
      .then(res => {
        this.shopeeOrders = res.data;
      });
    this.clientService()
      .retrieve()
      .then(res => {
        this.clients = res.data;
      });
  }
}
