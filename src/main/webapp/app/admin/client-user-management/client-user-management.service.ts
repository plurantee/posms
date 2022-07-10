import axios from 'axios';
import buildPaginationQueryOpts from '@/shared/sort/sorts';
import { IUser } from '@/shared/model/user.model';
import UserManagementService from '../user-management/user-management.service';

export default class ClientUserManagementService extends UserManagementService {
  public get(userId: number): Promise<any> {
    return axios.get(`api/admin/users/${userId}`);
  }

  public create(user: IUser): Promise<any> {
    return axios.post('api/admin/users', user);
  }

  public update(user: IUser): Promise<any> {
    return axios.put('api/admin/users', user);
  }

  public remove(userId: number): Promise<any> {
    return axios.delete(`api/admin/users/${userId}`);
  }

  public retrieve(req?: any): Promise<any> {
    return axios.get(`api/admin/users?${buildPaginationQueryOpts(req)}`);
  }

  public clientGet(userId: number): Promise<any> {
    console.log('Client get');
    return axios.get(`api/client-admin/users/${userId}`);
  }
  public clientUpdate(user: IUser): Promise<any> {
    return axios.put('api/client-admin/users', user);
  }
  public retrieveByClient(req?: any): Promise<any> {
    return axios.get(`api/client-admin/users?${buildPaginationQueryOpts(req)}`);
  }

  public retrieveAuthorities(): Promise<any> {
    return axios.get('api/authorities');
  }
}
