import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ILazadaOrder, ILazadaExcelFile } from '@/shared/model/lazada-order.model';

import LazadaOrderService from './lazada-order.service';
import AlertService from '@/shared/alert/alert.service';
import { IShop } from '@/shared/model/shop.model';

@Component({
  watch: {
    shop: async function () {
      if (this.shop) {
        this.posts = await this.retrieveAllLazadaOrdersByShop(this.shop);
      }
    }
  },
  mixins: [Vue2Filters.mixin],
  props: {
    shop: {
      required: true,
      type: Object as () => IShop,
    },
  },
})
export default class LazadaOrder extends Vue {
  @Inject('lazadaOrderService') private lazadaOrderService: () => LazadaOrderService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public lazadaOrders: ILazadaOrder[] = [];

  public isFetching = false;

  private file = null;

  public clear(): void {
    this.retrieveAllLazadaOrdersByShop(this.$props.shop);
  }

  public retrieveAllLazadaOrdersByShop(shop: IShop): void {
    console.log(this.$props.shop);
    this.isFetching = true;
    this.lazadaOrderService()
      .retrieveByShop(shop)
      .then(
        res => {
          this.lazadaOrders = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
          this.alertService().showHttpError(this, err.response);
        }
      );
  }
  public retrieveAllLazadaOrders(): void {
    this.isFetching = true;
    this.lazadaOrderService()
      .retrieve()
      .then(
        res => {
          this.lazadaOrders = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
          this.alertService().showHttpError(this, err.response);
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: ILazadaOrder): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeLazadaOrder(): void {
    this.lazadaOrderService()
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
        this.retrieveAllLazadaOrders();
        this.closeDialog();
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public uploadFile(): void {
    this.file = (<HTMLInputElement>this.$refs.file).files[0];
  }

  public submitFile(): void {
    // eslint-disable-next-line prefer-const
    let formData = new FormData();
    formData.append('file', this.file);
    formData.append('shopId', this.$props.shop.id);
    this.lazadaOrderService()
      .uploadLazadaExcel(formData)
      .then(
        res => {
          this.handleSyncList();
          this.isFetching = false;
        },
        err => {
          this.handleSyncList();
          this.isFetching = false;
          this.alertService().showHttpError(this, err.response);
        }
      );
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
