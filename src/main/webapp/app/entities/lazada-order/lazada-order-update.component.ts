import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import ShopService from '@/entities/shop/shop.service';
import { IShop } from '@/shared/model/shop.model';

import { ILazadaOrder, LazadaOrder } from '@/shared/model/lazada-order.model';
import LazadaOrderService from './lazada-order.service';

const validations: any = {
  lazadaOrder: {
    orderItemId: {},
    orderType: {},
    guarantee: {},
    deliveryType: {},
    lazadaId: {},
    sellerSku: {},
    wareHouse: {},
    createTime: {},
    updateTime: {},
    rtaSla: {},
    ttsSla: {},
    orderNumber: {},
    invoiceRequired: {},
    invoiceNumber: {},
    deliveryDate: {},
    customerName: {},
    customerEmail: {},
    nationalRegistrationNumber: {},
    shippingName: {},
    shippingAddress: {},
    shippingAddress2: {},
    shippingAddress3: {},
    shippingAddress4: {},
    shippingAddress5: {},
    shippingPhone: {},
    shippingPhone2: {},
    shippingCity: {},
    shippingPostCode: {},
    shippingCountry: {},
    shippingRegion: {},
    billingName: {},
    billingAddr: {},
    billingAddr3: {},
    billingAddr4: {},
    billingAddr5: {},
    billingPhone: {},
    billingPhone2: {},
    billingCity: {},
    billingPostCode: {},
    billingCountry: {},
    taxCode: {},
    branchNumber: {},
    taxInvoiceRequested: {},
    payMethod: {},
    paidPrice: {},
    unitPrice: {},
    sellerDiscountTotal: {},
    shippingFee: {},
    walletCredit: {},
    itemName: {},
    variation: {},
    cdShippingProvider: {},
    shippingProvider: {},
    shipmentTypeName: {},
    shippingProviderType: {},
    cdTrackingCode: {},
    trackingCode: {},
    trackingUrl: {},
    shippingProviderFM: {},
    trackingCodeFM: {},
    trackingUrlFM: {},
    promisedShippingTime: {},
    premium: {},
    status: {},
    buyerFailedDeliveryReturnInitiator: {},
    buyerFailedDeliveryReason: {},
    buyerFailedDeliveryDetail: {},
    buyerFailedDeliveryUserName: {},
    bundleId: {},
    bundleDiscount: {},
    refundAmount: {},
  },
};

@Component({
  validations,
})
export default class LazadaOrderUpdate extends Vue {
  @Inject('lazadaOrderService') private lazadaOrderService: () => LazadaOrderService;
  @Inject('alertService') private alertService: () => AlertService;

  public lazadaOrder: ILazadaOrder = new LazadaOrder();

  @Inject('shopService') private shopService: () => ShopService;

  public shops: IShop[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.lazadaOrderId) {
        vm.retrieveLazadaOrder(to.params.lazadaOrderId);
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
    if (this.lazadaOrder.id) {
      this.lazadaOrderService()
        .update(this.lazadaOrder)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A LazadaOrder is updated with identifier ' + param.id;
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
      this.lazadaOrderService()
        .create(this.lazadaOrder)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A LazadaOrder is created with identifier ' + param.id;
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

  public retrieveLazadaOrder(lazadaOrderId): void {
    this.lazadaOrderService()
      .find(lazadaOrderId)
      .then(res => {
        this.lazadaOrder = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.shopService()
      .retrieve()
      .then(res => {
        this.shops = res.data;
      });
  }
}
