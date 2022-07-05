/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_FORMAT } from '@/shared/date/filters';
import LazadaOrderService from '@/entities/lazada-order/lazada-order.service';
import { LazadaOrder } from '@/shared/model/lazada-order.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('LazadaOrder Service', () => {
    let service: LazadaOrderService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new LazadaOrderService();
      currentDate = new Date();
      elemDefault = new LazadaOrder(
        123,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        currentDate,
        currentDate,
        currentDate,
        false,
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            createTime: dayjs(currentDate).format(DATE_FORMAT),
            updateTime: dayjs(currentDate).format(DATE_FORMAT),
            rtaSla: dayjs(currentDate).format(DATE_FORMAT),
            ttsSla: dayjs(currentDate).format(DATE_FORMAT),
            orderNumber: dayjs(currentDate).format(DATE_FORMAT),
            deliveryDate: dayjs(currentDate).format(DATE_FORMAT),
            promisedShippingTime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a LazadaOrder', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            createTime: dayjs(currentDate).format(DATE_FORMAT),
            updateTime: dayjs(currentDate).format(DATE_FORMAT),
            rtaSla: dayjs(currentDate).format(DATE_FORMAT),
            ttsSla: dayjs(currentDate).format(DATE_FORMAT),
            orderNumber: dayjs(currentDate).format(DATE_FORMAT),
            deliveryDate: dayjs(currentDate).format(DATE_FORMAT),
            promisedShippingTime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            createTime: currentDate,
            updateTime: currentDate,
            rtaSla: currentDate,
            ttsSla: currentDate,
            orderNumber: currentDate,
            deliveryDate: currentDate,
            promisedShippingTime: currentDate,
          },
          returnedFromService
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a LazadaOrder', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a LazadaOrder', async () => {
        const returnedFromService = Object.assign(
          {
            orderItemId: 'BBBBBB',
            orderType: 'BBBBBB',
            guarantee: 'BBBBBB',
            deliveryType: 'BBBBBB',
            lazadaId: 'BBBBBB',
            sellerSku: 'BBBBBB',
            wareHouse: 'BBBBBB',
            createTime: dayjs(currentDate).format(DATE_FORMAT),
            updateTime: dayjs(currentDate).format(DATE_FORMAT),
            rtaSla: dayjs(currentDate).format(DATE_FORMAT),
            ttsSla: dayjs(currentDate).format(DATE_FORMAT),
            orderNumber: dayjs(currentDate).format(DATE_FORMAT),
            invoiceRequired: true,
            invoiceNumber: 'BBBBBB',
            deliveryDate: dayjs(currentDate).format(DATE_FORMAT),
            customerName: 'BBBBBB',
            customerEmail: 'BBBBBB',
            nationalRegistrationNumber: 'BBBBBB',
            shippingName: 'BBBBBB',
            shippingAddress: 'BBBBBB',
            shippingAddress2: 'BBBBBB',
            shippingAddress3: 'BBBBBB',
            shippingAddress4: 'BBBBBB',
            shippingAddress5: 'BBBBBB',
            shippingPhone: 'BBBBBB',
            shippingPhone2: 'BBBBBB',
            shippingCity: 'BBBBBB',
            shippingPostCode: 'BBBBBB',
            shippingCountry: 'BBBBBB',
            shippingRegion: 'BBBBBB',
            billingName: 'BBBBBB',
            billingAddr: 'BBBBBB',
            billingAddr3: 'BBBBBB',
            billingAddr4: 'BBBBBB',
            billingAddr5: 'BBBBBB',
            billingPhone: 'BBBBBB',
            billingPhone2: 'BBBBBB',
            billingCity: 'BBBBBB',
            billingPostCode: 'BBBBBB',
            billingCountry: 'BBBBBB',
            taxCode: 'BBBBBB',
            branchNumber: 'BBBBBB',
            taxInvoiceRequested: 'BBBBBB',
            payMethod: 'BBBBBB',
            paidPrice: 'BBBBBB',
            unitPrice: 'BBBBBB',
            sellerDiscountTotal: 'BBBBBB',
            shippingFee: 'BBBBBB',
            walletCredit: 'BBBBBB',
            itemName: 'BBBBBB',
            variation: 'BBBBBB',
            cdShippingProvider: 'BBBBBB',
            shippingProvider: 'BBBBBB',
            shipmentTypeName: 'BBBBBB',
            shippingProviderType: 'BBBBBB',
            cdTrackingCode: 'BBBBBB',
            trackingCode: 'BBBBBB',
            trackingUrl: 'BBBBBB',
            shippingProviderFM: 'BBBBBB',
            trackingCodeFM: 'BBBBBB',
            trackingUrlFM: 'BBBBBB',
            promisedShippingTime: dayjs(currentDate).format(DATE_FORMAT),
            premium: 'BBBBBB',
            status: 'BBBBBB',
            buyerFailedDeliveryReturnInitiator: 'BBBBBB',
            buyerFailedDeliveryReason: 'BBBBBB',
            buyerFailedDeliveryDetail: 'BBBBBB',
            buyerFailedDeliveryUserName: 'BBBBBB',
            bundleId: 'BBBBBB',
            bundleDiscount: 'BBBBBB',
            refundAmount: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createTime: currentDate,
            updateTime: currentDate,
            rtaSla: currentDate,
            ttsSla: currentDate,
            orderNumber: currentDate,
            deliveryDate: currentDate,
            promisedShippingTime: currentDate,
          },
          returnedFromService
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a LazadaOrder', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a LazadaOrder', async () => {
        const patchObject = Object.assign(
          {
            lazadaId: 'BBBBBB',
            updateTime: dayjs(currentDate).format(DATE_FORMAT),
            ttsSla: dayjs(currentDate).format(DATE_FORMAT),
            invoiceNumber: 'BBBBBB',
            deliveryDate: dayjs(currentDate).format(DATE_FORMAT),
            shippingName: 'BBBBBB',
            shippingAddress2: 'BBBBBB',
            shippingAddress4: 'BBBBBB',
            shippingPhone: 'BBBBBB',
            shippingPhone2: 'BBBBBB',
            shippingCity: 'BBBBBB',
            shippingCountry: 'BBBBBB',
            shippingRegion: 'BBBBBB',
            billingAddr3: 'BBBBBB',
            billingAddr4: 'BBBBBB',
            billingPostCode: 'BBBBBB',
            branchNumber: 'BBBBBB',
            taxInvoiceRequested: 'BBBBBB',
            walletCredit: 'BBBBBB',
            cdShippingProvider: 'BBBBBB',
            shippingProvider: 'BBBBBB',
            shippingProviderType: 'BBBBBB',
            cdTrackingCode: 'BBBBBB',
            trackingCode: 'BBBBBB',
            shippingProviderFM: 'BBBBBB',
            trackingCodeFM: 'BBBBBB',
            trackingUrlFM: 'BBBBBB',
            promisedShippingTime: dayjs(currentDate).format(DATE_FORMAT),
            buyerFailedDeliveryReason: 'BBBBBB',
            bundleId: 'BBBBBB',
          },
          new LazadaOrder()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            createTime: currentDate,
            updateTime: currentDate,
            rtaSla: currentDate,
            ttsSla: currentDate,
            orderNumber: currentDate,
            deliveryDate: currentDate,
            promisedShippingTime: currentDate,
          },
          returnedFromService
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a LazadaOrder', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of LazadaOrder', async () => {
        const returnedFromService = Object.assign(
          {
            orderItemId: 'BBBBBB',
            orderType: 'BBBBBB',
            guarantee: 'BBBBBB',
            deliveryType: 'BBBBBB',
            lazadaId: 'BBBBBB',
            sellerSku: 'BBBBBB',
            wareHouse: 'BBBBBB',
            createTime: dayjs(currentDate).format(DATE_FORMAT),
            updateTime: dayjs(currentDate).format(DATE_FORMAT),
            rtaSla: dayjs(currentDate).format(DATE_FORMAT),
            ttsSla: dayjs(currentDate).format(DATE_FORMAT),
            orderNumber: dayjs(currentDate).format(DATE_FORMAT),
            invoiceRequired: true,
            invoiceNumber: 'BBBBBB',
            deliveryDate: dayjs(currentDate).format(DATE_FORMAT),
            customerName: 'BBBBBB',
            customerEmail: 'BBBBBB',
            nationalRegistrationNumber: 'BBBBBB',
            shippingName: 'BBBBBB',
            shippingAddress: 'BBBBBB',
            shippingAddress2: 'BBBBBB',
            shippingAddress3: 'BBBBBB',
            shippingAddress4: 'BBBBBB',
            shippingAddress5: 'BBBBBB',
            shippingPhone: 'BBBBBB',
            shippingPhone2: 'BBBBBB',
            shippingCity: 'BBBBBB',
            shippingPostCode: 'BBBBBB',
            shippingCountry: 'BBBBBB',
            shippingRegion: 'BBBBBB',
            billingName: 'BBBBBB',
            billingAddr: 'BBBBBB',
            billingAddr3: 'BBBBBB',
            billingAddr4: 'BBBBBB',
            billingAddr5: 'BBBBBB',
            billingPhone: 'BBBBBB',
            billingPhone2: 'BBBBBB',
            billingCity: 'BBBBBB',
            billingPostCode: 'BBBBBB',
            billingCountry: 'BBBBBB',
            taxCode: 'BBBBBB',
            branchNumber: 'BBBBBB',
            taxInvoiceRequested: 'BBBBBB',
            payMethod: 'BBBBBB',
            paidPrice: 'BBBBBB',
            unitPrice: 'BBBBBB',
            sellerDiscountTotal: 'BBBBBB',
            shippingFee: 'BBBBBB',
            walletCredit: 'BBBBBB',
            itemName: 'BBBBBB',
            variation: 'BBBBBB',
            cdShippingProvider: 'BBBBBB',
            shippingProvider: 'BBBBBB',
            shipmentTypeName: 'BBBBBB',
            shippingProviderType: 'BBBBBB',
            cdTrackingCode: 'BBBBBB',
            trackingCode: 'BBBBBB',
            trackingUrl: 'BBBBBB',
            shippingProviderFM: 'BBBBBB',
            trackingCodeFM: 'BBBBBB',
            trackingUrlFM: 'BBBBBB',
            promisedShippingTime: dayjs(currentDate).format(DATE_FORMAT),
            premium: 'BBBBBB',
            status: 'BBBBBB',
            buyerFailedDeliveryReturnInitiator: 'BBBBBB',
            buyerFailedDeliveryReason: 'BBBBBB',
            buyerFailedDeliveryDetail: 'BBBBBB',
            buyerFailedDeliveryUserName: 'BBBBBB',
            bundleId: 'BBBBBB',
            bundleDiscount: 'BBBBBB',
            refundAmount: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            createTime: currentDate,
            updateTime: currentDate,
            rtaSla: currentDate,
            ttsSla: currentDate,
            orderNumber: currentDate,
            deliveryDate: currentDate,
            promisedShippingTime: currentDate,
          },
          returnedFromService
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of LazadaOrder', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a LazadaOrder', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a LazadaOrder', async () => {
        axiosStub.delete.rejects(error);

        return service
          .delete(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
