import { IUserInfo } from '@/shared/model/user-info.model';

export interface IClient {
  id?: number;
  clientName?: string | null;
  clientCode?: string | null;
  userInfos?: IUserInfo[] | null;
}

export class Client implements IClient {
  constructor(
    public id?: number,
    public clientName?: string | null,
    public clientCode?: string | null,
    public userInfos?: IUserInfo[] | null
  ) {}
}
