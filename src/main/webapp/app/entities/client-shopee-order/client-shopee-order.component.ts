import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IShopeeOrder } from '@/shared/model/shopee-order.model';

import ShopeeOrderService from './client-shopee-order.service';
import AlertService from '@/shared/alert/alert.service';
import ClientShopeeOrderService from './client-shopee-order.service';
import { IShop } from '@/shared/model/shop.model';
import ShopeeOrder from '../shopee-order/shopee-order.component';
import ClientShopService from '../client-shop/shop.service';

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
  @Inject('clientShopService') private clientShopService: () => ClientShopService;
  @Inject('alertService') private clientAlertService: () => AlertService;

  public shopeeOrders: IShopeeOrder[] = [];

  public isFetching = false;

  private file = null;

  public shops = null;

  public shop = null;

  public filter = 'all';

  public mounted(): void {
    this.clear();
  }

  public clear(): void {
    this.initRelationships();
    if (this.shop) {
      this.retrieveAllShopeeOrdersByShop(this.shop);
    } else {
      this.retrieveAllShopeeOrdersByClient();
    }
  }

  public retrieveAllShopeeOrders(): void {
    return;
  }

  public retrieveAllShopeeOrdersByClient(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort(),
    };

    this.clientShopeeOrderService()
      .retrieveByClient(this.filter, paginationQuery)
      .then(
        res => {
          this.shopeeOrders = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
          this.clientAlertService().showHttpError(this, err.response);
        }
      );
  }

  public retrieveAllShopeeOrdersByShop(shop): void {
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
    if (this.shop) {
      formData.append('shopId', this.shop);
    }
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

  public loadPage(page: number): void {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  public transition(): void {
    this.retrieveAllShopeeOrdersByClient();
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

  public initRelationships(): void {
    this.clientShopService()
      .retrieve()
      .then(res => {
        this.shops = res.data;
      });
  }
}
