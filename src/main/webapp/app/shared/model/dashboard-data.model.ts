import { IInventory, Inventory } from './inventory.model';
import { ILazadaOrderPayments } from './lazada-order-payments.model';
import { IShopeeOrderPayments } from './shopee-order-payments.model';

export interface IDashboardData {
  profit?: number;
  thresholdItems?: IInventory[];
  lazadaMap?: Map<string, ILazadaOrderPayments[]>;
  shopeeMap?: Map<string, IShopeeOrderPayments[]>;
}

export class DashboardData implements IDashboardData {
  constructor(
    public profit?: number | null,
    public thresholdItems?: IInventory[] | null,
    public lazadaMap?: Map<string, ILazadaOrderPayments[]> | null,
    public shopeeMap?: Map<string, IShopeeOrderPayments[]> | null
  ) {}
}
