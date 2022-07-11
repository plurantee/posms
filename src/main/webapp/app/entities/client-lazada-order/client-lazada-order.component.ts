import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ILazadaOrder } from '@/shared/model/lazada-order.model';

import ClientLazadaOrderService from './client-lazada-order.service';
import AlertService from '@/shared/alert/alert.service';
import { IShop } from '@/shared/model/shop.model';
import LazadaOrder from '../lazada-order/lazada-order.component';

@Component({
  watch: {
    shop: async function () {
      if (this.shop) {
        this.posts = await this.retrieveAllLazadaOrdersByShop(this.shop);
      }
    },
  },
  mixins: [Vue2Filters.mixin],
  props: {
    shop: {
      required: false,
      type: Object as () => IShop,
    },
  },
})
export default class ClientLazadaOrder extends LazadaOrder {
  @Inject('clientLazadaOrderService') private clientLazadaOrderService: () => ClientLazadaOrderService;
  @Inject('alertService') private clientAlertService: () => AlertService;

  public lazadaOrders: ILazadaOrder[] = [];

  public isFetching = false;

  private file = null;

  public mounted(): void {
    this.clear();
  }

  public clear(): void {
    if (this.$props?.shop) {
      this.retrieveAllLazadaOrdersByShop(this.$props.shop);
    } else {
      this.retrieveAllLazadaOrdersByClient();
    }
  }

  public retrieveAllLazadaOrdersByClient(): void {
    this.isFetching = true;
    this.clientLazadaOrderService()
      .retrieveByClient()
      .then(
        res => {
          this.lazadaOrders = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
          this.clientAlertService().showHttpError(this, err.response);
        }
      );
  }

  public retrieveAllLazadaOrdersByShop(shop: IShop): void {
    this.isFetching = true;
    this.clientLazadaOrderService()
      .retrieveByShop(shop)
      .then(
        res => {
          this.lazadaOrders = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
          this.clientAlertService().showHttpError(this, err.response);
        }
      );
  }
  public retrieveAllLazadaOrders(): void {
    return;
  }

  public handleSyncList(): void {
    this.clear();
  }

  public uploadFile(): void {
    this.file = (<HTMLInputElement>this.$refs.file).files[0];
  }

  public submitFile(): void {
    // eslint-disable-next-line prefer-const
    let formData = new FormData();
    formData.append('file', this.file);
    this.clientLazadaOrderService()
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

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
