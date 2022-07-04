import { IUserInfo } from '@/shared/model/user-info.model';
import { IShop } from '@/shared/model/shop.model';

export interface IClient {
  id?: number;
  clientName?: string | null;
  clientCode?: string | null;
  userInfos?: IUserInfo[] | null;
  shops?: IShop[] | null;
}

export class Client implements IClient {
  constructor(
    public id?: number,
    public clientName?: string | null,
    public clientCode?: string | null,
    public userInfos?: IUserInfo[] | null,
    public shops?: IShop[] | null
  ) {}
}
