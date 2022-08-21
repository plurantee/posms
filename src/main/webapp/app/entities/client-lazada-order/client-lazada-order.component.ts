import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ILazadaOrder } from '@/shared/model/lazada-order.model';

import ClientLazadaOrderService from './client-lazada-order.service';
import AlertService from '@/shared/alert/alert.service';
import { IShop } from '@/shared/model/shop.model';
import LazadaOrder from '../lazada-order/lazada-order.component';
import ClientShopService from '../client-shop/shop.service';

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
  @Inject('clientShopService') private clientShopService: () => ClientShopService;
  @Inject('alertService') private clientAlertService: () => AlertService;

  private clientRemoveId: number = null;

  public lazadaOrders: ILazadaOrder[] = [];

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
      this.retrieveAllLazadaOrdersByShop(this.shop);
    } else {
      this.retrieveAllLazadaOrdersByClient();
    }
  }

  public retrieveAllLazadaOrdersByClient(): void {
    this.isFetching = true;
    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort(),
    };

    this.clientLazadaOrderService()
      .retrieveByClient(this.filter, paginationQuery)
      .then(
        res => {
          this.lazadaOrders = res.data;
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

  public retrieveAllLazadaOrdersByShop(shop): void {
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
    this.isFetching = true;
    // eslint-disable-next-line prefer-const
    let formData = new FormData();
    formData.append('file', this.file);
    if (this.shop) {
      formData.append('shopId', this.shop);
    }

    this.clientLazadaOrderService()
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

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }

  public prepareRemove(instance: ILazadaOrder): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeLazadaOrder(): void {
    this.clientLazadaOrderService()
      .delete(this.removeId)
      .then(() => {
        const message = 'A LazadaOrder is deleted with identifier ' + this.removeId;
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.clear();
        this.closeDialog();
      })
      .catch(error => {
        this.clientAlertService().showHttpError(this, error.response);
      });
  }

  public loadPage(page: number): void {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  public transition(): void {
    this.retrieveAllLazadaOrdersByClient();
  }

  public initRelationships(): void {
    this.clientShopService()
      .retrieve()
      .then(res => {
        this.shops = res.data;
      });
  }
}
