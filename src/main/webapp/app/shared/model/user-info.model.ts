import { IUser } from '@/shared/model/user.model';
import { IClient } from '@/shared/model/client.model';

export interface IUserInfo {
  id?: number;
  user?: IUser | null;
  clientCode?: IClient | null;
}

export class UserInfo implements IUserInfo {
  constructor(public id?: number, public user?: IUser | null, public clientCode?: IClient | null) {}
}
