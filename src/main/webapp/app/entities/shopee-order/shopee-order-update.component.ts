import { Component, Vue, Inject } from 'vue-property-decorator';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import ShopeeOrderPaymentsService from '@/entities/shopee-order-payments/shopee-order-payments.service';
import { IShopeeOrderPayments } from '@/shared/model/shopee-order-payments.model';

import InventoryService from '@/entities/inventory/inventory.service';
import { IInventory } from '@/shared/model/inventory.model';

import ClientService from '@/entities/client/client.service';
import { IClient } from '@/shared/model/client.model';

import ShopService from '@/entities/shop/shop.service';
import { IShop } from '@/shared/model/shop.model';

import { IShopeeOrder, ShopeeOrder } from '@/shared/model/shopee-order.model';
import ShopeeOrderService from './shopee-order.service';

const validations: any = {
  shopeeOrder: {
    orderId: {},
    orderStatus: {},
    returnRefundStatus: {},
    trackingNumber: {},
    shippingOption: {},
    shipmentMethod: {},
    estimatedShipOutDate: {},
    shipTime: {},
    orderCreationDate: {},
    orderPaidTime: {},
    parentSkuReferenceNo: {},
    productName: {},
    skuReferenceNo: {},
    variationName: {},
    originalPrice: {},
    dealPrice: {},
    quantity: {},
    productSubtotal: {},
    totalDiscount: {},
    priceDiscountFromSeller: {},
    shopeeRebate: {},
    skuTotalWeight: {},
    numberOfItemsInOrder: {},
    orderTotalWeight: {},
    sellerVoucher: {},
    sellerAbsorbedCoinCashback: {},
    shopeeVoucher: {},
    bundleDealsIndicatorYN: {},
    shopeeBundleDiscount: {},
    sellerBundleDiscount: {},
    shopeeCoinsOffset: {},
    creditCardDiscountTotal: {},
    productsPricePaidByBuyer: {},
    buyerPaidShippingFee: {},
    shippingRebateEstimate: {},
    reverseShippingFee: {},
    serviceFee: {},
    grandTotal: {},
    estimatedShippingFee: {},
    usernameBuyer: {},
    receiverName: {},
    phoneNumber: {},
    deliveryAddress: {},
    town: {},
    district: {},
    province: {},
    region: {},
    country: {},
    zipCode: {},
    remarkFromBuyer: {},
    orderCompleteTime: {},
    note: {},
  },
};

@Component({
  validations,
})
export default class ShopeeOrderUpdate extends Vue {
  @Inject('shopeeOrderService') private shopeeOrderService: () => ShopeeOrderService;
  @Inject('alertService') private alertService: () => AlertService;

  public shopeeOrder: IShopeeOrder = new ShopeeOrder();

  @Inject('shopeeOrderPaymentsService') private shopeeOrderPaymentsService: () => ShopeeOrderPaymentsService;

  public shopeeOrderPayments: IShopeeOrderPayments[] = [];

  @Inject('inventoryService') private inventoryService: () => InventoryService;

  public inventories: IInventory[] = [];

  @Inject('clientService') private clientService: () => ClientService;

  public clients: IClient[] = [];

  @Inject('shopService') private shopService: () => ShopService;

  public shops: IShop[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.shopeeOrderId) {
        vm.retrieveShopeeOrder(to.params.shopeeOrderId);
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
    this.shopeeOrder.payments = [];
  }

  public save(): void {
    this.isSaving = true;
    if (this.shopeeOrder.id) {
      this.shopeeOrderService()
        .update(this.shopeeOrder)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A ShopeeOrder is updated with identifier ' + param.id;
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
      this.shopeeOrderService()
        .create(this.shopeeOrder)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A ShopeeOrder is created with identifier ' + param.id;
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
      this.shopeeOrder[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.shopeeOrder[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.shopeeOrder[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.shopeeOrder[field] = null;
    }
  }

  public retrieveShopeeOrder(shopeeOrderId): void {
    this.shopeeOrderService()
      .find(shopeeOrderId)
      .then(res => {
        res.estimatedShipOutDate = new Date(res.estimatedShipOutDate);
        res.shipTime = new Date(res.shipTime);
        res.orderCreationDate = new Date(res.orderCreationDate);
        res.orderPaidTime = new Date(res.orderPaidTime);
        res.orderCompleteTime = new Date(res.orderCompleteTime);
        this.shopeeOrder = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.shopeeOrderPaymentsService()
      .retrieve()
      .then(res => {
        this.shopeeOrderPayments = res.data;
      });
    this.inventoryService()
      .retrieve()
      .then(res => {
        this.inventories = res.data;
      });
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

  public getSelected(selectedVals, option): any {
    if (selectedVals) {
      return selectedVals.find(value => option.id === value.id) ?? option;
    }
    return option;
  }
}
