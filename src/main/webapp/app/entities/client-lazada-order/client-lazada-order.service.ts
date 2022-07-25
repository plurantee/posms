import axios from 'axios';

import { IShop } from '@/shared/model/shop.model';
import LazadaOrderService from '../lazada-order/lazada-order.service';
import buildPaginationQueryOpts from '@/shared/sort/sorts';

const baseApiUrl = 'api/lazada-orders';

export default class ClientLazadaOrderService extends LazadaOrderService {
  public retrieveByShop(shop: IShop): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/shop/${shop.id}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieveByClient(filter?: string, paginationQuery?: any): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/client?${buildPaginationQueryOpts(paginationQuery)}&filter=${filter}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public uploadLazadaExcel(file: FormData) {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/upload`, file, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        })
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
