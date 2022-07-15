import { IUserInfo } from '@/shared/model/user-info.model';
import { IShop } from '@/shared/model/shop.model';
import { ILazadaOrder } from '@/shared/model/lazada-order.model';
import { IShopeeOrder } from '@/shared/model/shopee-order.model';

import { ClientType } from '@/shared/model/enumerations/client-type.model';
export interface IClient {
  id?: number;
  clientName?: string | null;
  clientCode?: string | null;
  clientType?: ClientType | null;
  validityDate?: Date | null;
  userInfos?: IUserInfo[] | null;
  shops?: IShop[] | null;
  lazadaOrders?: ILazadaOrder[] | null;
  shopeeOrders?: IShopeeOrder[] | null;
}

export class Client implements IClient {
  constructor(
    public id?: number,
    public clientName?: string | null,
    public clientCode?: string | null,
    public clientType?: ClientType | null,
    public validityDate?: Date | null,
    public userInfos?: IUserInfo[] | null,
    public shops?: IShop[] | null,
    public lazadaOrders?: ILazadaOrder[] | null,
    public shopeeOrders?: IShopeeOrder[] | null
  ) {}
}
