import { IShopeeOrder } from '@/shared/model/shopee-order.model';

export interface IShopeeOrderPayments {
  id?: number;
  orderId?: string | null;
  refundId?: string | null;
  usernameBuyer?: string | null;
  orderCreationDate?: Date | null;
  buyerPaymentMethod?: string | null;
  payoutCompletedDate?: Date | null;
  originalProductPrice?: number | null;
  sellerProductPromotion?: number | null;
  refundAmountToBuyer?: number | null;
  productDiscountRebateFromShopee?: number | null;
  sellerVoucherDiscount?: number | null;
  sellerAbsorbedCoinCashback?: number | null;
  buyerPaidShippingFee?: number | null;
  shippingFeeRebateFromShopee?: number | null;
  thirdPartyLogisticsDefinedShippingFee?: number | null;
  reverseShippingFee?: number | null;
  commissionFee?: number | null;
  serviceFee?: number | null;
  transactionFee?: number | null;
  totalReleasedAmount?: number | null;
  sellerVoucherCode?: string | null;
  lostCompensation?: number | null;
  totalActualWeightPerOrder?: number | null;
  shippingFeePromotionBySeller?: number | null;
  shippingProvider?: string | null;
  courierName?: string | null;
  shopeeOrder?: IShopeeOrder | null;
}

export class ShopeeOrderPayments implements IShopeeOrderPayments {
  constructor(
    public id?: number,
    public orderId?: string | null,
    public refundId?: string | null,
    public usernameBuyer?: string | null,
    public orderCreationDate?: Date | null,
    public buyerPaymentMethod?: string | null,
    public payoutCompletedDate?: Date | null,
    public originalProductPrice?: number | null,
    public sellerProductPromotion?: number | null,
    public refundAmountToBuyer?: number | null,
    public productDiscountRebateFromShopee?: number | null,
    public sellerVoucherDiscount?: number | null,
    public sellerAbsorbedCoinCashback?: number | null,
    public buyerPaidShippingFee?: number | null,
    public shippingFeeRebateFromShopee?: number | null,
    public thirdPartyLogisticsDefinedShippingFee?: number | null,
    public reverseShippingFee?: number | null,
    public commissionFee?: number | null,
    public serviceFee?: number | null,
    public transactionFee?: number | null,
    public totalReleasedAmount?: number | null,
    public sellerVoucherCode?: string | null,
    public lostCompensation?: number | null,
    public totalActualWeightPerOrder?: number | null,
    public shippingFeePromotionBySeller?: number | null,
    public shippingProvider?: string | null,
    public courierName?: string | null,
    public shopeeOrder?: IShopeeOrder | null
  ) {}
}
