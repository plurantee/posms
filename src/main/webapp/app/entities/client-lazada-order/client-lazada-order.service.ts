import axios from 'axios';

import { IShop } from '@/shared/model/shop.model';
import LazadaOrderService from '../lazada-order/lazada-order.service';

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

  public retrieveByClient(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/client`)
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
