import { IShopeeOrderPayments } from '@/shared/model/shopee-order-payments.model';
import { IInventory } from '@/shared/model/inventory.model';
import { IClient } from '@/shared/model/client.model';
import { IShop } from '@/shared/model/shop.model';

export interface IShopeeOrder {
  id?: number;
  orderId?: string | null;
  orderStatus?: string | null;
  returnRefundStatus?: string | null;
  trackingNumber?: string | null;
  shippingOption?: string | null;
  shipmentMethod?: string | null;
  estimatedShipOutDate?: Date | null;
  shipTime?: Date | null;
  orderCreationDate?: Date | null;
  orderPaidTime?: Date | null;
  parentSkuReferenceNo?: string | null;
  productName?: string | null;
  skuReferenceNo?: string | null;
  variationName?: string | null;
  originalPrice?: number | null;
  dealPrice?: number | null;
  quantity?: number | null;
  productSubtotal?: number | null;
  totalDiscount?: number | null;
  priceDiscountFromSeller?: number | null;
  shopeeRebate?: number | null;
  skuTotalWeight?: string | null;
  numberOfItemsInOrder?: string | null;
  orderTotalWeight?: string | null;
  sellerVoucher?: number | null;
  sellerAbsorbedCoinCashback?: string | null;
  shopeeVoucher?: number | null;
  bundleDealsIndicatorYN?: string | null;
  shopeeBundleDiscount?: number | null;
  sellerBundleDiscount?: number | null;
  shopeeCoinsOffset?: number | null;
  creditCardDiscountTotal?: number | null;
  productsPricePaidByBuyer?: number | null;
  buyerPaidShippingFee?: number | null;
  shippingRebateEstimate?: number | null;
  reverseShippingFee?: number | null;
  serviceFee?: number | null;
  grandTotal?: number | null;
  estimatedShippingFee?: number | null;
  usernameBuyer?: string | null;
  receiverName?: string | null;
  phoneNumber?: string | null;
  deliveryAddress?: string | null;
  town?: string | null;
  district?: string | null;
  province?: string | null;
  region?: string | null;
  country?: string | null;
  zipCode?: string | null;
  remarkFromBuyer?: string | null;
  orderCompleteTime?: Date | null;
  note?: string | null;
  dateUploaded?: Date | null;
  dateReleasedOrCancelled?: Date | null;
  payments?: IShopeeOrderPayments[] | null;
  inventory?: IInventory | null;
  client?: IClient | null;
  shop?: IShop | null;
}

export class ShopeeOrder implements IShopeeOrder {
  constructor(
    public id?: number,
    public orderId?: string | null,
    public orderStatus?: string | null,
    public returnRefundStatus?: string | null,
    public trackingNumber?: string | null,
    public shippingOption?: string | null,
    public shipmentMethod?: string | null,
    public estimatedShipOutDate?: Date | null,
    public shipTime?: Date | null,
    public orderCreationDate?: Date | null,
    public orderPaidTime?: Date | null,
    public parentSkuReferenceNo?: string | null,
    public productName?: string | null,
    public skuReferenceNo?: string | null,
    public variationName?: string | null,
    public originalPrice?: number | null,
    public dealPrice?: number | null,
    public quantity?: number | null,
    public productSubtotal?: number | null,
    public totalDiscount?: number | null,
    public priceDiscountFromSeller?: number | null,
    public shopeeRebate?: number | null,
    public skuTotalWeight?: string | null,
    public numberOfItemsInOrder?: string | null,
    public orderTotalWeight?: string | null,
    public sellerVoucher?: number | null,
    public sellerAbsorbedCoinCashback?: string | null,
    public shopeeVoucher?: number | null,
    public bundleDealsIndicatorYN?: string | null,
    public shopeeBundleDiscount?: number | null,
    public sellerBundleDiscount?: number | null,
    public shopeeCoinsOffset?: number | null,
    public creditCardDiscountTotal?: number | null,
    public productsPricePaidByBuyer?: number | null,
    public buyerPaidShippingFee?: number | null,
    public shippingRebateEstimate?: number | null,
    public reverseShippingFee?: number | null,
    public serviceFee?: number | null,
    public grandTotal?: number | null,
    public estimatedShippingFee?: number | null,
    public usernameBuyer?: string | null,
    public receiverName?: string | null,
    public phoneNumber?: string | null,
    public deliveryAddress?: string | null,
    public town?: string | null,
    public district?: string | null,
    public province?: string | null,
    public region?: string | null,
    public country?: string | null,
    public zipCode?: string | null,
    public remarkFromBuyer?: string | null,
    public orderCompleteTime?: Date | null,
    public note?: string | null,
    public dateUploaded?: Date | null,
    public dateReleasedOrCancelled?: Date | null,
    public payments?: IShopeeOrderPayments[] | null,
    public inventory?: IInventory | null,
    public client?: IClient | null,
    public shop?: IShop | null
  ) {}
}
