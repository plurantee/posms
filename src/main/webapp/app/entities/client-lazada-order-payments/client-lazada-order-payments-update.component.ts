import { Component, Vue, Inject } from 'vue-property-decorator';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import LazadaOrderService from '@/entities/lazada-order/lazada-order.service';
import { ILazadaOrder } from '@/shared/model/lazada-order.model';

import { ILazadaOrderPayments, LazadaOrderPayments } from '@/shared/model/lazada-order-payments.model';
import LazadaOrderPaymentsService from './client-lazada-order-payments.service';

const validations: any = {
  lazadaOrderPayments: {
    transactionDate: {},
    transactionType: {},
    feeName: {},
    transactionNumber: {},
    details: {},
    sellerSku: {},
    lazadaSku: {},
    amount: {},
    vatInAmount: {},
    whtAmount: {},
    whtIncludedInAmount: {},
    statement: {},
    paidStatus: {},
    orderNo: {},
    orderItemNo: {},
    orderItemStatus: {},
    shippingProvider: {},
    shippingSpeed: {},
    shipmentType: {},
    reference: {},
    comment: {},
    paymentRefId: {},
  },
};

@Component({
  validations,
})
export default class ClientLazadaOrderPaymentsUpdate extends Vue {
  @Inject('lazadaOrderPaymentsService') private lazadaOrderPaymentsService: () => LazadaOrderPaymentsService;
  @Inject('alertService') private alertService: () => AlertService;

  public lazadaOrderPayments: ILazadaOrderPayments = new LazadaOrderPayments();

  @Inject('lazadaOrderService') private lazadaOrderService: () => LazadaOrderService;

  public lazadaOrders: ILazadaOrder[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.lazadaOrderPaymentsId) {
        vm.retrieveLazadaOrderPayments(to.params.lazadaOrderPaymentsId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.lazadaOrderPayments.id) {
      this.lazadaOrderPaymentsService()
        .update(this.lazadaOrderPayments)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A LazadaOrderPayments is updated with identifier ' + param.id;
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    } else {
      this.lazadaOrderPaymentsService()
        .create(this.lazadaOrderPayments)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A LazadaOrderPayments is created with identifier ' + param.id;
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    }
  }

  public convertDateTimeFromServer(date: Date): string {
    if (date && dayjs(date).isValid()) {
      return dayjs(date).format(DATE_TIME_LONG_FORMAT);
    }
    return null;
  }

  public updateInstantField(field, event) {
    if (event.target.value) {
      this.lazadaOrderPayments[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.lazadaOrderPayments[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.lazadaOrderPayments[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.lazadaOrderPayments[field] = null;
    }
  }

  public retrieveLazadaOrderPayments(lazadaOrderPaymentsId): void {
    this.lazadaOrderPaymentsService()
      .find(lazadaOrderPaymentsId)
      .then(res => {
        res.transactionDate = new Date(res.transactionDate);
        this.lazadaOrderPayments = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.lazadaOrderService()
      .retrieve()
      .then(res => {
        this.lazadaOrders = res.data;
      });
  }
}
