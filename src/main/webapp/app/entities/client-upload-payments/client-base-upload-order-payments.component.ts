import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ILazadaOrderPayments, LazadaOrderPayments } from '@/shared/model/lazada-order-payments.model';

import AlertService from '@/shared/alert/alert.service';
import LazadaOrderPaymentsService from '../lazada-order-payments/lazada-order-payments.service';

import axios from 'axios';

const baseApiUrl = 'api/order/payments';

const ClientLazadaUploadOrderPayment = () => import('@/entities/client-upload-payments/client-upload-lazada-order-payments.vue');
const ClientShopeeUploadOrderPayment = () => import('@/entities/client-upload-payments/client-upload-shopee-order-payments.vue');

@Component({
  mixins: [Vue2Filters.mixin],
  props: {
    orderId: {
      required: false,
      type: Object as () => string,
    },
  },
  components: {
    'client-lazada-payments': ClientLazadaUploadOrderPayment,
    'client-shopee-payments': ClientShopeeUploadOrderPayment,
  },
})
export default class ClientBaseOrderPayments extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  public nav: string | string[] = 'lazada';
  public isFetching = false;
  public lazadaOrderPayments: ILazadaOrderPayments[] = [];
  private file = null;
  private hasNoOrderId = true;
  public totalPayments = 0;

  public mounted(): void {
    this.clear();
  }

  public clear(): void {
    if (this.$props?.orderId) {
      this.hasNoOrderId = false;
      this.getPayments(this.$props.orderId).then(
        res => {
          this.lazadaOrderPayments = res;
          this.lazadaOrderPayments.forEach(element => {
            this.totalPayments = this.totalPayments + element.amount;
          });
          return this.$root.$bvToast.toast(this.file.name + ' Uploaded', {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        },
        err => {
          this.alertService().showHttpError(this, err.response);
        }
      );
    }
  }

  public uploadFile(): void {
    this.file = (<HTMLInputElement>this.$refs.file).files[0];
  }

  public submitFile(): void {
    this.isFetching = true;
    // eslint-disable-next-line prefer-const
    let formData = new FormData();
    formData.append('file', this.file);
    this.uploadLazadaExcel(formData).then(
      res => {
        this.lazadaOrderPayments = res;
        return this.$root.$bvToast.toast(this.file.name + ' Uploaded', {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'info',
          solid: true,
          autoHideDelay: 5000,
        });
      },
      err => {
        this.alertService().showHttpError(this, err.response);
      }
    );
  }

  public uploadLazadaExcel(file: FormData) {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/lazada/upload`, file, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        })
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public getPayments(orderId: string) {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(`api/lazada-order-payments/by-order-id/` + orderId)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public switchNav(value: string) {
    this.nav = value;
  }
}
