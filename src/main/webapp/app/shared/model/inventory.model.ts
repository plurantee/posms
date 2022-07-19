import { ILazadaOrder } from '@/shared/model/lazada-order.model';
import { IShopeeOrder } from '@/shared/model/shopee-order.model';
import { IClient } from '@/shared/model/client.model';

export interface IInventory {
  id?: number;
  sku?: string | null;
  stocks?: number | null;
  cost?: number | null;
  price?: number | null;
  threshold?: number | null;
  lazadaOrders?: ILazadaOrder[] | null;
  shopeeOrders?: IShopeeOrder[] | null;
  client?: IClient | null;
}

export class Inventory implements IInventory {
  constructor(
    public id?: number,
    public sku?: string | null,
    public stocks?: number | null,
    public cost?: number | null,
    public price?: number | null,
    public threshold?: number | null,
    public lazadaOrders?: ILazadaOrder[] | null,
    public shopeeOrders?: IShopeeOrder[] | null,
    public client?: IClient | null
  ) {}
}
