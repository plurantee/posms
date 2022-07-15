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
      required: false,
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
    if (this.$props?.shop) {
      this.retrieveAllShopeeOrdersByShop(this.$props.shop);
    } else {
      this.retrieveAllShopeeOrdersByClient();
    }
  }

  public retrieveAllShopeeOrders(): void {
    return;
  }

  public retrieveAllShopeeOrdersByClient(): void {
    this.isFetching = true;
    this.clientShopeeOrderService()
      .retrieveByClient()
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

  public retrieveAllShopeeOrdersByShop(shop: IShop): void {
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
    this.isFetching = true;
    // eslint-disable-next-line prefer-const
    let formData = new FormData();
    formData.append('file', this.file);
    this.clientShopeeOrderService()
      .uploadLazadaExcel(formData)
      .then(
        res => {
          this.handleSyncList();
          this.isFetching = false;
          return this.$root.$bvToast.toast(this.file.name + ' Uploaded', {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        },
        err => {
          this.handleSyncList();
          this.isFetching = false;
          this.clientAlertService().showHttpError(this, err.response);
        }
      );
  }


  public removeShopeeOrder(): void {
    this.clientShopeeOrderService()
      .delete(this.removeId)
      .then(() => {
        const message = 'A ShopeeOrder is deleted with identifier ' + this.removeId;
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllShopeeOrdersByClient();
        this.closeDialog();
      })
      .catch(error => {
        this.clientAlertService().showHttpError(this, error.response);
      });
  }
}
