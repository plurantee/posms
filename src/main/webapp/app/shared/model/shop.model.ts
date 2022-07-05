import { ILazadaOrder } from '@/shared/model/lazada-order.model';
import { IClient } from '@/shared/model/client.model';

import { ShopType } from '@/shared/model/enumerations/shop-type.model';
export interface IShop {
  id?: number;
  shopCode?: string | null;
  shopName?: string | null;
  shopType?: ShopType | null;
  lazadaOrders?: ILazadaOrder[] | null;
  clientCode?: IClient | null;
}

export class Shop implements IShop {
  constructor(
    public id?: number,
    public shopCode?: string | null,
    public shopName?: string | null,
    public shopType?: ShopType | null,
    public lazadaOrders?: ILazadaOrder[] | null,
    public clientCode?: IClient | null
  ) {}
}
