import { IShop } from '@/shared/model/shop.model';
import axios from 'axios';

import ShopeeOrderService from '../shopee-order/shopee-order.service';
import buildPaginationQueryOpts from '@/shared/sort/sorts';

const baseApiUrl = 'api/shopee-orders';

export default class ClientShopeeOrderService extends ShopeeOrderService {
  public retrieveByShop(shop): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/shop/${shop}`)
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
