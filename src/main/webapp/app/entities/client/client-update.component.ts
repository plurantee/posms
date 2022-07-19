import { Component, Vue, Inject } from 'vue-property-decorator';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import InventoryService from '@/entities/inventory/inventory.service';
import { IInventory } from '@/shared/model/inventory.model';

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
import { ClientType } from '@/shared/model/enumerations/client-type.model';

const validations: any = {
  client: {
    clientName: {},
    clientCode: {},
    clientType: {},
    validityDate: {},
  },
};

@Component({
  validations,
})
export default class ClientUpdate extends Vue {
  @Inject('clientService') private clientService: () => ClientService;
  @Inject('alertService') private alertService: () => AlertService;

  public client: IClient = new Client();

  @Inject('inventoryService') private inventoryService: () => InventoryService;

  public inventories: IInventory[] = [];

  @Inject('userInfoService') private userInfoService: () => UserInfoService;

  public userInfos: IUserInfo[] = [];

  @Inject('shopService') private shopService: () => ShopService;

  public shops: IShop[] = [];

  @Inject('lazadaOrderService') private lazadaOrderService: () => LazadaOrderService;

  public lazadaOrders: ILazadaOrder[] = [];

  @Inject('shopeeOrderService') private shopeeOrderService: () => ShopeeOrderService;

  public shopeeOrders: IShopeeOrder[] = [];
  public clientTypeValues: string[] = Object.keys(ClientType);
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

  public convertDateTimeFromServer(date: Date): string {
    if (date && dayjs(date).isValid()) {
      return dayjs(date).format(DATE_TIME_LONG_FORMAT);
    }
    return null;
  }

  public updateInstantField(field, event) {
    if (event.target.value) {
      this.client[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.client[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.client[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.client[field] = null;
    }
  }

  public retrieveClient(clientId): void {
    this.clientService()
      .find(clientId)
      .then(res => {
        res.validityDate = new Date(res.validityDate);
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
    this.inventoryService()
      .retrieve()
      .then(res => {
        this.inventories = res.data;
      });
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
