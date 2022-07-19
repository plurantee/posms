import { ILazadaOrder } from '@/shared/model/lazada-order.model';

export interface ILazadaOrderPayments {
  id?: number;
  transactionDate?: Date | null;
  transactionType?: string | null;
  feeName?: string | null;
  transactionNumber?: string | null;
  details?: string | null;
  sellerSku?: string | null;
  lazadaSku?: string | null;
  amount?: number | null;
  vatInAmount?: number | null;
  whtAmount?: number | null;
  whtIncludedInAmount?: boolean | null;
  statement?: string | null;
  paidStatus?: string | null;
  orderNo?: string | null;
  orderItemNo?: string | null;
  orderItemStatus?: string | null;
  shippingProvider?: string | null;
  shippingSpeed?: string | null;
  shipmentType?: string | null;
  reference?: string | null;
  comment?: string | null;
  paymentRefId?: string | null;
  internalStatus?: string | null;
  lazadaOrder?: ILazadaOrder | null;
}

export class LazadaOrderPayments implements ILazadaOrderPayments {
  constructor(
    public id?: number,
    public transactionDate?: Date | null,
    public transactionType?: string | null,
    public feeName?: string | null,
    public transactionNumber?: string | null,
    public details?: string | null,
    public sellerSku?: string | null,
    public lazadaSku?: string | null,
    public amount?: number | null,
    public vatInAmount?: number | null,
    public whtAmount?: number | null,
    public whtIncludedInAmount?: boolean | null,
    public statement?: string | null,
    public paidStatus?: string | null,
    public orderNo?: string | null,
    public orderItemNo?: string | null,
    public orderItemStatus?: string | null,
    public shippingProvider?: string | null,
    public shippingSpeed?: string | null,
    public shipmentType?: string | null,
    public reference?: string | null,
    public comment?: string | null,
    public paymentRefId?: string | null,
    public internalStatus?: string | null,
    public lazadaOrder?: ILazadaOrder | null
  ) {
    this.whtIncludedInAmount = this.whtIncludedInAmount ?? false;
  }
}
