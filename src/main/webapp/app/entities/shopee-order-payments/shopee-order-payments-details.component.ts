import { Component, Vue, Inject } from 'vue-property-decorator';

import { IShopeeOrderPayments } from '@/shared/model/shopee-order-payments.model';
import ShopeeOrderPaymentsService from './shopee-order-payments.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class ShopeeOrderPaymentsDetails extends Vue {
  @Inject('shopeeOrderPaymentsService') private shopeeOrderPaymentsService: () => ShopeeOrderPaymentsService;
  @Inject('alertService') private alertService: () => AlertService;

  public shopeeOrderPayments: IShopeeOrderPayments = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.shopeeOrderPaymentsId) {
        vm.retrieveShopeeOrderPayments(to.params.shopeeOrderPaymentsId);
      }
    });
  }

  public retrieveShopeeOrderPayments(shopeeOrderPaymentsId) {
    this.shopeeOrderPaymentsService()
      .find(shopeeOrderPaymentsId)
      .then(res => {
        this.shopeeOrderPayments = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
