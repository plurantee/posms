import { Component, Vue, Inject } from 'vue-property-decorator';

import { ILazadaOrder } from '@/shared/model/lazada-order.model';
import ClientLazadaOrderService from './client-lazada-order.service';
import AlertService from '@/shared/alert/alert.service';
import LazadaOrderDetails from '../lazada-order/lazada-order-details.component';

@Component
export default class ClientLazadaOrderDetails extends LazadaOrderDetails {
  @Inject('clientLazadaOrderService') private clientLazadaOrderService: () => ClientLazadaOrderService;
  @Inject('alertService') private clientAlertService: () => AlertService;

  public lazadaOrder: ILazadaOrder = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.lazadaOrderId) {
        vm.retrieveLazadaOrder(to.params.lazadaOrderId);
      }
    });
  }

  public retrieveLazadaOrder(lazadaOrderId) {
    this.clientLazadaOrderService()
      .find(lazadaOrderId)
      .then(res => {
        this.lazadaOrder = res;
      })
      .catch(error => {
        this.clientAlertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}