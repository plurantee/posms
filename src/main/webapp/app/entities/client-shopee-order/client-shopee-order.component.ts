import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IShopeeOrder } from '@/shared/model/shopee-order.model';

import ShopeeOrderService from './client-shopee-order.service';
import AlertService from '@/shared/alert/alert.service';
import ClientShopeeOrderService from './client-shopee-order.service';
import { IShop } from '@/shared/model/shop.model';
import ShopeeOrder from '../shopee-order/shopee-order.component';

@Component({
  watch: {
    shop: async function () {
      if (this.shop) {
        this.posts = await this.retrieveAllShopeeOrdersByShop(this.shop);
      }
    },
  },
  mixins: [Vue2Filters.mixin],
  props: {
    shop: {
      required: true,
      type: Object as () => IShop,
    },
  },
})
export default class ClientShopeeOrder extends ShopeeOrder {
  @Inject('clientShopeeOrderService') private clientShopeeOrderService: () => ClientShopeeOrderService;
  @Inject('alertService') private clientAlertService: () => AlertService;

  public shopeeOrders: IShopeeOrder[] = [];

  public isFetching = false;

  private file = null;

  public mounted(): void {
    this.clear();
  }

  public clear(): void {
    this.retrieveAllShopeeOrdersByShop(this.$props.shop);
  }

  public retrieveAllShopeeOrders(): void {
    return;
  }

  public retrieveAllShopeeOrdersByShop(shop: IShop): void {
    console.log(this.$props.shop);
    this.isFetching = true;
    this.clientShopeeOrderService()
      .retrieveByShop(shop)
      .then(
        res => {
          this.shopeeOrders = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
          this.clientAlertService().showHttpError(this, err.response);
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }

  public uploadFile(): void {
    this.file = (<HTMLInputElement>this.$refs.file).files[0];
  }

  public submitFile(): void {
    // eslint-disable-next-line prefer-const
    let formData = new FormData();
    formData.append('file', this.file);
    formData.append('shopId', this.$props.shop.id);
    this.clientShopeeOrderService()
      .uploadLazadaExcel(formData)
      .then(
        res => {
          this.handleSyncList();
          this.isFetching = false;
        },
        err => {
          this.handleSyncList();
          this.isFetching = false;
          this.clientAlertService().showHttpError(this, err.response);
        }
      );
  }
}
