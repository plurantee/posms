import { Component, Vue, Inject } from 'vue-property-decorator';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import ClientService from '@/entities/client/client.service';
import { IClient } from '@/shared/model/client.model';

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
    lazadaSku: {},
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
    billingAddr2: {},
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

  @Inject('clientService') private clientService: () => ClientService;

  public clients: IClient[] = [];

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

  public convertDateTimeFromServer(date: Date): string {
    if (date && dayjs(date).isValid()) {
      return dayjs(date).format(DATE_TIME_LONG_FORMAT);
    }
    return null;
  }

  public updateInstantField(field, event) {
    if (event.target.value) {
      this.lazadaOrder[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.lazadaOrder[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.lazadaOrder[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.lazadaOrder[field] = null;
    }
  }

  public retrieveLazadaOrder(lazadaOrderId): void {
    this.lazadaOrderService()
      .find(lazadaOrderId)
      .then(res => {
        res.createTime = new Date(res.createTime);
        res.updateTime = new Date(res.updateTime);
        res.rtaSla = new Date(res.rtaSla);
        res.ttsSla = new Date(res.ttsSla);
        res.deliveryDate = new Date(res.deliveryDate);
        res.promisedShippingTime = new Date(res.promisedShippingTime);
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
    this.clientService()
      .retrieve()
      .then(res => {
        this.clients = res.data;
      });
    this.shopService()
      .retrieve()
      .then(res => {
        this.shops = res.data;
      });
  }
}
