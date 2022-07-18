import { Component, Vue, Inject } from 'vue-property-decorator';

import { ILazadaOrderPayments } from '@/shared/model/lazada-order-payments.model';
import LazadaOrderPaymentsService from './client-lazada-order-payments.service';
import AlertService from '@/shared/alert/alert.service';
import lazadaOrderPaymentsDetailsVue from '../lazada-order-payments/lazada-order-payments-details.vue';
import LazadaOrderPaymentsDetails from '../lazada-order-payments/lazada-order-payments-details.component';

@Component
export default class ClientLazadaOrderPaymentsDetails extends LazadaOrderPaymentsDetails {
  public lazadaOrderPayments: ILazadaOrderPayments = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.lazadaOrderPaymentsId) {
        vm.retrieveLazadaOrderPayments(to.params.lazadaOrderPaymentsId);
      }
    });
  }

  public retrieveLazadaOrderPayments(lazadaOrderPaymentsId) {
    this.lazadaOrderPaymentsService()
      .find(lazadaOrderPaymentsId)
      .then(res => {
        this.lazadaOrderPayments = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
