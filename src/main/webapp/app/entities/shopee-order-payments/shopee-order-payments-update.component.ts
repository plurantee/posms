import { Component, Vue, Inject } from 'vue-property-decorator';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import ShopeeOrderService from '@/entities/shopee-order/shopee-order.service';
import { IShopeeOrder } from '@/shared/model/shopee-order.model';

import { IShopeeOrderPayments, ShopeeOrderPayments } from '@/shared/model/shopee-order-payments.model';
import ShopeeOrderPaymentsService from './shopee-order-payments.service';

const validations: any = {
  shopeeOrderPayments: {
    orderId: {},
    refundId: {},
    usernameBuyer: {},
    orderCreationDate: {},
    buyerPaymentMethod: {},
    payoutCompletedDate: {},
    originalProductPrice: {},
    sellerProductPromotion: {},
    refundAmountToBuyer: {},
    productDiscountRebateFromShopee: {},
    sellerVoucherDiscount: {},
    sellerAbsorbedCoinCashback: {},
    buyerPaidShippingFee: {},
    shippingFeeRebateFromShopee: {},
    thirdPartyLogisticsDefinedShippingFee: {},
    reverseShippingFee: {},
    commissionFee: {},
    serviceFee: {},
    transactionFee: {},
    totalReleasedAmount: {},
    sellerVoucherCode: {},
    lostCompensation: {},
    totalActualWeightPerOrder: {},
    shippingFeePromotionBySeller: {},
    shippingProvider: {},
    courierName: {},
  },
};

@Component({
  validations,
})
export default class ShopeeOrderPaymentsUpdate extends Vue {
  @Inject('shopeeOrderPaymentsService') private shopeeOrderPaymentsService: () => ShopeeOrderPaymentsService;
  @Inject('alertService') private alertService: () => AlertService;

  public shopeeOrderPayments: IShopeeOrderPayments = new ShopeeOrderPayments();

  @Inject('shopeeOrderService') private shopeeOrderService: () => ShopeeOrderService;

  public shopeeOrders: IShopeeOrder[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.shopeeOrderPaymentsId) {
        vm.retrieveShopeeOrderPayments(to.params.shopeeOrderPaymentsId);
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
    if (this.shopeeOrderPayments.id) {
      this.shopeeOrderPaymentsService()
        .update(this.shopeeOrderPayments)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A ShopeeOrderPayments is updated with identifier ' + param.id;
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
      this.shopeeOrderPaymentsService()
        .create(this.shopeeOrderPayments)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A ShopeeOrderPayments is created with identifier ' + param.id;
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
      this.shopeeOrderPayments[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.shopeeOrderPayments[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.shopeeOrderPayments[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.shopeeOrderPayments[field] = null;
    }
  }

  public retrieveShopeeOrderPayments(shopeeOrderPaymentsId): void {
    this.shopeeOrderPaymentsService()
      .find(shopeeOrderPaymentsId)
      .then(res => {
        res.orderCreationDate = new Date(res.orderCreationDate);
        res.payoutCompletedDate = new Date(res.payoutCompletedDate);
        this.shopeeOrderPayments = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.shopeeOrderService()
      .retrieve()
      .then(res => {
        this.shopeeOrders = res.data;
      });
  }
}
