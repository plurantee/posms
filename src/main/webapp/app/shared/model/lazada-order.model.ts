import { IShop } from '@/shared/model/shop.model';

export interface ILazadaOrder {
  id?: number;
  orderItemId?: string | null;
  orderType?: string | null;
  guarantee?: string | null;
  deliveryType?: string | null;
  lazadaId?: string | null;
  sellerSku?: string | null;
  wareHouse?: string | null;
  createTime?: Date | null;
  updateTime?: Date | null;
  rtaSla?: Date | null;
  ttsSla?: Date | null;
  orderNumber?: Date | null;
  invoiceRequired?: boolean | null;
  invoiceNumber?: string | null;
  deliveryDate?: Date | null;
  customerName?: string | null;
  customerEmail?: string | null;
  nationalRegistrationNumber?: string | null;
  shippingName?: string | null;
  shippingAddress?: string | null;
  shippingAddress2?: string | null;
  shippingAddress3?: string | null;
  shippingAddress4?: string | null;
  shippingAddress5?: string | null;
  shippingPhone?: string | null;
  shippingPhone2?: string | null;
  shippingCity?: string | null;
  shippingPostCode?: string | null;
  shippingCountry?: string | null;
  shippingRegion?: string | null;
  billingName?: string | null;
  billingAddr?: string | null;
  billingAddr3?: string | null;
  billingAddr4?: string | null;
  billingAddr5?: string | null;
  billingPhone?: string | null;
  billingPhone2?: string | null;
  billingCity?: string | null;
  billingPostCode?: string | null;
  billingCountry?: string | null;
  taxCode?: string | null;
  branchNumber?: string | null;
  taxInvoiceRequested?: string | null;
  payMethod?: string | null;
  paidPrice?: string | null;
  unitPrice?: string | null;
  sellerDiscountTotal?: string | null;
  shippingFee?: string | null;
  walletCredit?: string | null;
  itemName?: string | null;
  variation?: string | null;
  cdShippingProvider?: string | null;
  shippingProvider?: string | null;
  shipmentTypeName?: string | null;
  shippingProviderType?: string | null;
  cdTrackingCode?: string | null;
  trackingCode?: string | null;
  trackingUrl?: string | null;
  shippingProviderFM?: string | null;
  trackingCodeFM?: string | null;
  trackingUrlFM?: string | null;
  promisedShippingTime?: Date | null;
  premium?: string | null;
  status?: string | null;
  buyerFailedDeliveryReturnInitiator?: string | null;
  buyerFailedDeliveryReason?: string | null;
  buyerFailedDeliveryDetail?: string | null;
  buyerFailedDeliveryUserName?: string | null;
  bundleId?: string | null;
  bundleDiscount?: string | null;
  refundAmount?: string | null;
  shop?: IShop | null;
}

export class LazadaOrder implements ILazadaOrder {
  constructor(
    public id?: number,
    public orderItemId?: string | null,
    public orderType?: string | null,
    public guarantee?: string | null,
    public deliveryType?: string | null,
    public lazadaId?: string | null,
    public sellerSku?: string | null,
    public wareHouse?: string | null,
    public createTime?: Date | null,
    public updateTime?: Date | null,
    public rtaSla?: Date | null,
    public ttsSla?: Date | null,
    public orderNumber?: Date | null,
    public invoiceRequired?: boolean | null,
    public invoiceNumber?: string | null,
    public deliveryDate?: Date | null,
    public customerName?: string | null,
    public customerEmail?: string | null,
    public nationalRegistrationNumber?: string | null,
    public shippingName?: string | null,
    public shippingAddress?: string | null,
    public shippingAddress2?: string | null,
    public shippingAddress3?: string | null,
    public shippingAddress4?: string | null,
    public shippingAddress5?: string | null,
    public shippingPhone?: string | null,
    public shippingPhone2?: string | null,
    public shippingCity?: string | null,
    public shippingPostCode?: string | null,
    public shippingCountry?: string | null,
    public shippingRegion?: string | null,
    public billingName?: string | null,
    public billingAddr?: string | null,
    public billingAddr3?: string | null,
    public billingAddr4?: string | null,
    public billingAddr5?: string | null,
    public billingPhone?: string | null,
    public billingPhone2?: string | null,
    public billingCity?: string | null,
    public billingPostCode?: string | null,
    public billingCountry?: string | null,
    public taxCode?: string | null,
    public branchNumber?: string | null,
    public taxInvoiceRequested?: string | null,
    public payMethod?: string | null,
    public paidPrice?: string | null,
    public unitPrice?: string | null,
    public sellerDiscountTotal?: string | null,
    public shippingFee?: string | null,
    public walletCredit?: string | null,
    public itemName?: string | null,
    public variation?: string | null,
    public cdShippingProvider?: string | null,
    public shippingProvider?: string | null,
    public shipmentTypeName?: string | null,
    public shippingProviderType?: string | null,
    public cdTrackingCode?: string | null,
    public trackingCode?: string | null,
    public trackingUrl?: string | null,
    public shippingProviderFM?: string | null,
    public trackingCodeFM?: string | null,
    public trackingUrlFM?: string | null,
    public promisedShippingTime?: Date | null,
    public premium?: string | null,
    public status?: string | null,
    public buyerFailedDeliveryReturnInitiator?: string | null,
    public buyerFailedDeliveryReason?: string | null,
    public buyerFailedDeliveryDetail?: string | null,
    public buyerFailedDeliveryUserName?: string | null,
    public bundleId?: string | null,
    public bundleDiscount?: string | null,
    public refundAmount?: string | null,
    public shop?: IShop | null
  ) {
    this.invoiceRequired = this.invoiceRequired ?? false;
  }


}

export interface ILazadaExcelFile {
  file: any;
}

export class LazadaExcelFile implements ILazadaExcelFile {
  constructor(public file: any) {
    
  }
}