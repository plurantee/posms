import { Component, Vue, Inject } from 'vue-property-decorator';

import { ILazadaOrderPayments } from '@/shared/model/lazada-order-payments.model';
import LazadaOrderPaymentsService from './lazada-order-payments.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class LazadaOrderPaymentsDetails extends Vue {
  @Inject('lazadaOrderPaymentsService') protected lazadaOrderPaymentsService: () => LazadaOrderPaymentsService;
  @Inject('alertService') protected alertService: () => AlertService;

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
