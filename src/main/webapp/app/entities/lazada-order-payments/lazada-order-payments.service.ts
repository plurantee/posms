import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { ILazadaOrderPayments } from '@/shared/model/lazada-order-payments.model';

const baseApiUrl = 'api/lazada-order-payments';

export default class LazadaOrderPaymentsService {
  public find(id: number): Promise<ILazadaOrderPayments> {
    return new Promise<ILazadaOrderPayments>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(paginationQuery?: any): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl + `?${buildPaginationQueryOpts(paginationQuery)}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public delete(id: number): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .delete(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public create(entity: ILazadaOrderPayments): Promise<ILazadaOrderPayments> {
    return new Promise<ILazadaOrderPayments>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public update(entity: ILazadaOrderPayments): Promise<ILazadaOrderPayments> {
    return new Promise<ILazadaOrderPayments>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}/${entity.id}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public partialUpdate(entity: ILazadaOrderPayments): Promise<ILazadaOrderPayments> {
    return new Promise<ILazadaOrderPayments>((resolve, reject) => {
      axios
        .patch(`${baseApiUrl}/${entity.id}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
