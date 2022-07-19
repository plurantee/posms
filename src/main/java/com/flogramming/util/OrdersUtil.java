package com.flogramming.util;

import com.flogramming.domain.LazadaOrder;
import com.flogramming.domain.Shop;

import java.time.ZonedDateTime;

public class OrdersUtil {

    public LazadaOrder lazadaOrder;

    public OrdersUtil(LazadaOrder lazadaOrder) {
        this.lazadaOrder = lazadaOrder;
    }

    public LazadaOrder getLazadaOrder() {
        return lazadaOrder;
    }

    // Lazada stuff

    public LazadaOrder id(Long id) {
        lazadaOrder.setId(id);
        return lazadaOrder;
    }

    public LazadaOrder orderItemId(String orderItemId) {
        lazadaOrder.setOrderItemId(orderItemId);
        return lazadaOrder;
    }

    public LazadaOrder orderType(String orderType) {
        lazadaOrder.setOrderType(orderType);
        return lazadaOrder;
    }

    public LazadaOrder guarantee(String guarantee) {
        lazadaOrder.setGuarantee(guarantee);
        return lazadaOrder;
    }

    public LazadaOrder deliveryType(String deliveryType) {
        lazadaOrder.setDeliveryType(deliveryType);
        return lazadaOrder;
    }

    public LazadaOrder lazadaId(String lazadaId) {
        lazadaOrder.setLazadaId(lazadaId);
        return lazadaOrder;
    }

    public LazadaOrder sellerSku(String sellerSku) {
        lazadaOrder.setSellerSku(sellerSku);
        return lazadaOrder;
    }


    public LazadaOrder lazadaSku(String lazadaSku) {
        lazadaOrder.setLazadaSku(lazadaSku);
        return lazadaOrder;
    }

    public LazadaOrder wareHouse(String wareHouse) {
        lazadaOrder.setWareHouse(wareHouse);
        return lazadaOrder;
    }

    public LazadaOrder createTime(ZonedDateTime createTime) {
        lazadaOrder.setCreateTime(createTime);
        return lazadaOrder;
    }

    public LazadaOrder updateTime(ZonedDateTime updateTime) {
        lazadaOrder.setUpdateTime(updateTime);
        return lazadaOrder;
    }

    public LazadaOrder rtaSla(ZonedDateTime rtaSla) {
        lazadaOrder.setRtaSla(rtaSla);
        return lazadaOrder;
    }

    public LazadaOrder ttsSla(ZonedDateTime ttsSla) {
        lazadaOrder.setTtsSla(ttsSla);
        return lazadaOrder;
    }

    public LazadaOrder orderNumber(String orderNumber) {
        lazadaOrder.setOrderNumber(orderNumber);
        return lazadaOrder;
    }

    public LazadaOrder invoiceRequired(Boolean invoiceRequired) {
        lazadaOrder.setInvoiceRequired(invoiceRequired);
        return lazadaOrder;
    }

    public LazadaOrder invoiceNumber(String invoiceNumber) {
        lazadaOrder.setInvoiceNumber(invoiceNumber);
        return lazadaOrder;
    }

    public LazadaOrder deliveryDate(ZonedDateTime deliveryDate) {
        lazadaOrder.setDeliveryDate(deliveryDate);
        return lazadaOrder;
    }

    public LazadaOrder customerName(String customerName) {
        lazadaOrder.setCustomerName(customerName);
        return lazadaOrder;
    }

    public LazadaOrder customerEmail(String customerEmail) {
        lazadaOrder.setCustomerEmail(customerEmail);
        return lazadaOrder;
    }

    public LazadaOrder nationalRegistrationNumber(String nationalRegistrationNumber) {
        lazadaOrder.setNationalRegistrationNumber(nationalRegistrationNumber);
        return lazadaOrder;
    }

    public LazadaOrder shippingName(String shippingName) {
        lazadaOrder.setShippingName(shippingName);
        return lazadaOrder;
    }

    public LazadaOrder shippingAddress(String shippingAddress) {
        lazadaOrder.setShippingAddress(shippingAddress);
        return lazadaOrder;
    }

    public LazadaOrder shippingAddress2(String shippingAddress2) {
        lazadaOrder.setShippingAddress2(shippingAddress2);
        return lazadaOrder;
    }

    public LazadaOrder shippingAddress3(String shippingAddress3) {
        lazadaOrder.setShippingAddress3(shippingAddress3);
        return lazadaOrder;
    }

    public LazadaOrder shippingAddress4(String shippingAddress4) {
        lazadaOrder.setShippingAddress4(shippingAddress4);
        return lazadaOrder;
    }

    public LazadaOrder shippingAddress5(String shippingAddress5) {
        lazadaOrder.setShippingAddress5(shippingAddress5);
        return lazadaOrder;
    }

    public LazadaOrder shippingPhone(String shippingPhone) {
        lazadaOrder.setShippingPhone(shippingPhone);
        return lazadaOrder;
    }

    public LazadaOrder shippingPhone2(String shippingPhone2) {
        lazadaOrder.setShippingPhone2(shippingPhone2);
        return lazadaOrder;
    }

    public LazadaOrder shippingCity(String shippingCity) {
        lazadaOrder.setShippingCity(shippingCity);
        return lazadaOrder;
    }

    public LazadaOrder shippingPostCode(String shippingPostCode) {
        lazadaOrder.setShippingPostCode(shippingPostCode);
        return lazadaOrder;
    }

    public LazadaOrder shippingCountry(String shippingCountry) {
        lazadaOrder.setShippingCountry(shippingCountry);
        return lazadaOrder;
    }

    public LazadaOrder shippingRegion(String shippingRegion) {
        lazadaOrder.setShippingRegion(shippingRegion);
        return lazadaOrder;
    }

    public LazadaOrder billingName(String billingName) {
        lazadaOrder.setBillingName(billingName);
        return lazadaOrder;
    }

    public LazadaOrder billingAddr(String billingAddr) {
        lazadaOrder.setBillingAddr(billingAddr);
        return lazadaOrder;
    }

    public LazadaOrder billingAddr2(String billingAddr2) {
        lazadaOrder.setBillingAddr2(billingAddr2);
        return lazadaOrder;
    }

    public LazadaOrder billingAddr3(String billingAddr3) {
        lazadaOrder.setBillingAddr3(billingAddr3);
        return lazadaOrder;
    }

    public LazadaOrder billingAddr4(String billingAddr4) {
        lazadaOrder.setBillingAddr4(billingAddr4);
        return lazadaOrder;
    }

    public LazadaOrder billingAddr5(String billingAddr5) {
        lazadaOrder.setBillingAddr5(billingAddr5);
        return lazadaOrder;
    }

    public LazadaOrder billingPhone(String billingPhone) {
        lazadaOrder.setBillingPhone(billingPhone);
        return lazadaOrder;
    }

    public LazadaOrder billingPhone2(String billingPhone2) {
        lazadaOrder.setBillingPhone2(billingPhone2);
        return lazadaOrder;
    }

    public LazadaOrder billingCity(String billingCity) {
        lazadaOrder.setBillingCity(billingCity);
        return lazadaOrder;
    }

    public LazadaOrder billingPostCode(String billingPostCode) {
        lazadaOrder.setBillingPostCode(billingPostCode);
        return lazadaOrder;
    }

    public LazadaOrder billingCountry(String billingCountry) {
        lazadaOrder.setBillingCountry(billingCountry);
        return lazadaOrder;
    }

    public LazadaOrder taxCode(String taxCode) {
        lazadaOrder.setTaxCode(taxCode);
        return lazadaOrder;
    }

    public LazadaOrder branchNumber(String branchNumber) {
        lazadaOrder.setBranchNumber(branchNumber);
        return lazadaOrder;
    }

    public LazadaOrder taxInvoiceRequested(String taxInvoiceRequested) {
        lazadaOrder.setTaxInvoiceRequested(taxInvoiceRequested);
        return lazadaOrder;
    }

    public LazadaOrder payMethod(String payMethod) {
        lazadaOrder.setPayMethod(payMethod);
        return lazadaOrder;
    }

    public LazadaOrder paidPrice(Double paidPrice) {
        lazadaOrder.setPaidPrice(paidPrice);
        return lazadaOrder;
    }

    public LazadaOrder unitPrice(Double unitPrice) {
        lazadaOrder.setUnitPrice(unitPrice);
        return lazadaOrder;
    }

    public LazadaOrder sellerDiscountTotal(Double sellerDiscountTotal) {
        lazadaOrder.setSellerDiscountTotal(sellerDiscountTotal);
        return lazadaOrder;
    }

    public LazadaOrder shippingFee(Double shippingFee) {
        lazadaOrder.setShippingFee(shippingFee);
        return lazadaOrder;
    }

    public LazadaOrder walletCredit(Double walletCredit) {
        lazadaOrder.setWalletCredit(walletCredit);
        return lazadaOrder;
    }

    public LazadaOrder itemName(String itemName) {
        lazadaOrder.setItemName(itemName);
        return lazadaOrder;
    }

    public LazadaOrder variation(String variation) {
        lazadaOrder.setVariation(variation);
        return lazadaOrder;
    }

    public LazadaOrder cdShippingProvider(String cdShippingProvider) {
        lazadaOrder.setCdShippingProvider(cdShippingProvider);
        return lazadaOrder;
    }

    public LazadaOrder shippingProvider(String shippingProvider) {
        lazadaOrder.setShippingProvider(shippingProvider);
        return lazadaOrder;
    }

    public LazadaOrder shipmentTypeName(String shipmentTypeName) {
        lazadaOrder.setShipmentTypeName(shipmentTypeName);
        return lazadaOrder;
    }

    public LazadaOrder shippingProviderType(String shippingProviderType) {
        lazadaOrder.setShippingProviderType(shippingProviderType);
        return lazadaOrder;
    }

    public LazadaOrder cdTrackingCode(String cdTrackingCode) {
        lazadaOrder.setCdTrackingCode(cdTrackingCode);
        return lazadaOrder;
    }

    public LazadaOrder trackingCode(String trackingCode) {
        lazadaOrder.setTrackingCode(trackingCode);
        return lazadaOrder;
    }

    public LazadaOrder trackingUrl(String trackingUrl) {
        lazadaOrder.setTrackingUrl(trackingUrl);
        return lazadaOrder;
    }

    public LazadaOrder shippingProviderFM(String shippingProviderFM) {
        lazadaOrder.setShippingProviderFM(shippingProviderFM);
        return lazadaOrder;
    }

    public LazadaOrder trackingCodeFM(String trackingCodeFM) {
        lazadaOrder.setTrackingCodeFM(trackingCodeFM);
        return lazadaOrder;
    }

    public LazadaOrder trackingUrlFM(String trackingUrlFM) {
        lazadaOrder.setTrackingUrlFM(trackingUrlFM);
        return lazadaOrder;
    }

    public LazadaOrder promisedShippingTime(ZonedDateTime promisedShippingTime) {
        lazadaOrder.setPromisedShippingTime(promisedShippingTime);
        return lazadaOrder;
    }

    public LazadaOrder premium(String premium) {
        lazadaOrder.setPremium(premium);
        return lazadaOrder;
    }

    public LazadaOrder status(String status) {
        lazadaOrder.setStatus(status);
        return lazadaOrder;
    }

    public LazadaOrder buyerFailedDeliveryReturnInitiator(String buyerFailedDeliveryReturnInitiator) {
        lazadaOrder.setBuyerFailedDeliveryReturnInitiator(buyerFailedDeliveryReturnInitiator);
        return lazadaOrder;
    }

    public LazadaOrder buyerFailedDeliveryReason(String buyerFailedDeliveryReason) {
        lazadaOrder.setBuyerFailedDeliveryReason(buyerFailedDeliveryReason);
        return lazadaOrder;
    }

    public LazadaOrder buyerFailedDeliveryDetail(String buyerFailedDeliveryDetail) {
        lazadaOrder.setBuyerFailedDeliveryDetail(buyerFailedDeliveryDetail);
        return lazadaOrder;
    }

    public LazadaOrder buyerFailedDeliveryUserName(String buyerFailedDeliveryUserName) {
        lazadaOrder.setBuyerFailedDeliveryUserName(buyerFailedDeliveryUserName);
        return lazadaOrder;
    }

    public LazadaOrder bundleId(String bundleId) {
        lazadaOrder.setBundleId(bundleId);
        return lazadaOrder;
    }

    public LazadaOrder bundleDiscount(Double bundleDiscount) {
        lazadaOrder.setBundleDiscount(bundleDiscount);
        return lazadaOrder;
    }

    public LazadaOrder refundAmount(Double refundAmount) {
        lazadaOrder.setRefundAmount(refundAmount);
        return lazadaOrder;
    }

    public LazadaOrder shop(Shop shop) {
        lazadaOrder.setShop(shop);
        return lazadaOrder;
    }
}
