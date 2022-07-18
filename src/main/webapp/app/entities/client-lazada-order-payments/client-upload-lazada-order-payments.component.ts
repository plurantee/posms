import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ILazadaOrderPayments, LazadaOrderPayments } from '@/shared/model/lazada-order-payments.model';

import AlertService from '@/shared/alert/alert.service';
import LazadaOrderPaymentsService from '../lazada-order-payments/lazada-order-payments.service';

import axios from 'axios';

const baseApiUrl = 'api/order/payments';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class ClientLazadaOrderPayments extends Vue {
  @Inject('alertService') private alertService: () => AlertService;

  public isFetching = false;
  public lazadaOrderPayments: ILazadaOrderPayments[] = [];
  private file = null;
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
}
