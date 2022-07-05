import { Component, Vue, Inject } from 'vue-property-decorator';

import { ILazadaOrder } from '@/shared/model/lazada-order.model';
import LazadaOrderService from './lazada-order.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class LazadaOrderDetails extends Vue {
  @Inject('lazadaOrderService') private lazadaOrderService: () => LazadaOrderService;
  @Inject('alertService') private alertService: () => AlertService;

  public lazadaOrder: ILazadaOrder = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.lazadaOrderId) {
        vm.retrieveLazadaOrder(to.params.lazadaOrderId);
      }
    });
  }

  public retrieveLazadaOrder(lazadaOrderId) {
    this.lazadaOrderService()
      .find(lazadaOrderId)
      .then(res => {
        this.lazadaOrder = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
