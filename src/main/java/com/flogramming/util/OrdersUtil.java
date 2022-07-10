package com.flogramming.util;

import com.flogramming.domain.LazadaOrder;
import com.flogramming.domain.Shop;
import java.time.LocalDateTime;

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
        lazadaOrder.Id(id);
        return lazadaOrder;
    }

    public LazadaOrder orderItemId(String orderItemId) {
        lazadaOrder.OrderItemId(orderItemId);
        return lazadaOrder;
    }

    public LazadaOrder orderType(String orderType) {
        lazadaOrder.OrderType(orderType);
        return lazadaOrder;
    }

    public LazadaOrder guarantee(String guarantee) {
        lazadaOrder.Guarantee(guarantee);
        return lazadaOrder;
    }

    public LazadaOrder deliveryType(String deliveryType) {
        lazadaOrder.DeliveryType(deliveryType);
        return lazadaOrder;
    }

    public LazadaOrder lazadaId(String lazadaId) {
        lazadaOrder.LazadaId(lazadaId);
        return lazadaOrder;
    }

    public LazadaOrder sellerSku(String sellerSku) {
        lazadaOrder.SellerSku(sellerSku);
        return lazadaOrder;
    }

    public LazadaOrder lazadaSku(String lazadaSku) {
        lazadaOrder.LazadaSku(lazadaSku);
        return lazadaOrder;
    }

    public LazadaOrder wareHouse(String wareHouse) {
        lazadaOrder.WareHouse(wareHouse);
        return lazadaOrder;
    }

    public LazadaOrder createTime(LocalDateTime createTime) {
        lazadaOrder.CreateTime(createTime);
        return lazadaOrder;
    }

    public LazadaOrder updateTime(LocalDateTime updateTime) {
        lazadaOrder.UpdateTime(updateTime);
        return lazadaOrder;
    }

    public LazadaOrder rtaSla(LocalDateTime rtaSla) {
        lazadaOrder.RtaSla(rtaSla);
        return lazadaOrder;
    }

    public LazadaOrder ttsSla(LocalDateTime ttsSla) {
        lazadaOrder.TtsSla(ttsSla);
        return lazadaOrder;
    }

    public LazadaOrder orderNumber(String orderNumber) {
        lazadaOrder.OrderNumber(orderNumber);
        return lazadaOrder;
    }

    public LazadaOrder invoiceRequired(Boolean invoiceRequired) {
        lazadaOrder.InvoiceRequired(invoiceRequired);
        return lazadaOrder;
    }

    public LazadaOrder invoiceNumber(String invoiceNumber) {
        lazadaOrder.InvoiceNumber(invoiceNumber);
        return lazadaOrder;
    }

    public LazadaOrder deliveryDate(LocalDateTime deliveryDate) {
        lazadaOrder.DeliveryDate(deliveryDate);
        return lazadaOrder;
    }

    public LazadaOrder customerName(String customerName) {
        lazadaOrder.CustomerName(customerName);
        return lazadaOrder;
    }

    public LazadaOrder customerEmail(String customerEmail) {
        lazadaOrder.CustomerEmail(customerEmail);
        return lazadaOrder;
    }

    public LazadaOrder nationalRegistrationNumber(String nationalRegistrationNumber) {
        lazadaOrder.NationalRegistrationNumber(nationalRegistrationNumber);
        return lazadaOrder;
    }

    public LazadaOrder shippingName(String shippingName) {
        lazadaOrder.ShippingName(shippingName);
        return lazadaOrder;
    }

    public LazadaOrder shippingAddress(String shippingAddress) {
        lazadaOrder.ShippingAddress(shippingAddress);
        return lazadaOrder;
    }

    public LazadaOrder shippingAddress2(String shippingAddress2) {
        lazadaOrder.ShippingAddress2(shippingAddress2);
        return lazadaOrder;
    }

    public LazadaOrder shippingAddress3(String shippingAddress3) {
        lazadaOrder.ShippingAddress3(shippingAddress3);
        return lazadaOrder;
    }

    public LazadaOrder shippingAddress4(String shippingAddress4) {
        lazadaOrder.ShippingAddress4(shippingAddress4);
        return lazadaOrder;
    }

    public LazadaOrder shippingAddress5(String shippingAddress5) {
        lazadaOrder.ShippingAddress5(shippingAddress5);
        return lazadaOrder;
    }

    public LazadaOrder shippingPhone(String shippingPhone) {
        lazadaOrder.ShippingPhone(shippingPhone);
        return lazadaOrder;
    }

    public LazadaOrder shippingPhone2(String shippingPhone2) {
        lazadaOrder.ShippingPhone2(shippingPhone2);
        return lazadaOrder;
    }

    public LazadaOrder shippingCity(String shippingCity) {
        lazadaOrder.ShippingCity(shippingCity);
        return lazadaOrder;
    }

    public LazadaOrder shippingPostCode(String shippingPostCode) {
        lazadaOrder.ShippingPostCode(shippingPostCode);
        return lazadaOrder;
    }

    public LazadaOrder shippingCountry(String shippingCountry) {
        lazadaOrder.ShippingCountry(shippingCountry);
        return lazadaOrder;
    }

    public LazadaOrder shippingRegion(String shippingRegion) {
        lazadaOrder.ShippingRegion(shippingRegion);
        return lazadaOrder;
    }

    public LazadaOrder billingName(String billingName) {
        lazadaOrder.BillingName(billingName);
        return lazadaOrder;
    }

    public LazadaOrder billingAddr(String billingAddr) {
        lazadaOrder.BillingAddr(billingAddr);
        return lazadaOrder;
    }

    public LazadaOrder billingAddr2(String billingAddr2) {
        lazadaOrder.BillingAddr2(billingAddr2);
        return lazadaOrder;
    }

    public LazadaOrder billingAddr3(String billingAddr3) {
        lazadaOrder.BillingAddr3(billingAddr3);
        return lazadaOrder;
    }

    public LazadaOrder billingAddr4(String billingAddr4) {
        lazadaOrder.BillingAddr4(billingAddr4);
        return lazadaOrder;
    }

    public LazadaOrder billingAddr5(String billingAddr5) {
        lazadaOrder.BillingAddr5(billingAddr5);
        return lazadaOrder;
    }

    public LazadaOrder billingPhone(String billingPhone) {
        lazadaOrder.BillingPhone(billingPhone);
        return lazadaOrder;
    }

    public LazadaOrder billingPhone2(String billingPhone2) {
        lazadaOrder.BillingPhone2(billingPhone2);
        return lazadaOrder;
    }

    public LazadaOrder billingCity(String billingCity) {
        lazadaOrder.BillingCity(billingCity);
        return lazadaOrder;
    }

    public LazadaOrder billingPostCode(String billingPostCode) {
        lazadaOrder.BillingPostCode(billingPostCode);
        return lazadaOrder;
    }

    public LazadaOrder billingCountry(String billingCountry) {
        lazadaOrder.BillingCountry(billingCountry);
        return lazadaOrder;
    }

    public LazadaOrder taxCode(String taxCode) {
        lazadaOrder.TaxCode(taxCode);
        return lazadaOrder;
    }

    public LazadaOrder branchNumber(String branchNumber) {
        lazadaOrder.BranchNumber(branchNumber);
        return lazadaOrder;
    }

    public LazadaOrder taxInvoiceRequested(String taxInvoiceRequested) {
        lazadaOrder.TaxInvoiceRequested(taxInvoiceRequested);
        return lazadaOrder;
    }

    public LazadaOrder payMethod(String payMethod) {
        lazadaOrder.PayMethod(payMethod);
        return lazadaOrder;
    }

    public LazadaOrder paidPrice(String paidPrice) {
        lazadaOrder.PaidPrice(paidPrice);
        return lazadaOrder;
    }

    public LazadaOrder unitPrice(String unitPrice) {
        lazadaOrder.UnitPrice(unitPrice);
        return lazadaOrder;
    }

    public LazadaOrder sellerDiscountTotal(String sellerDiscountTotal) {
        lazadaOrder.SellerDiscountTotal(sellerDiscountTotal);
        return lazadaOrder;
    }

    public LazadaOrder shippingFee(String shippingFee) {
        lazadaOrder.ShippingFee(shippingFee);
        return lazadaOrder;
    }

    public LazadaOrder walletCredit(String walletCredit) {
        lazadaOrder.WalletCredit(walletCredit);
        return lazadaOrder;
    }

    public LazadaOrder itemName(String itemName) {
        lazadaOrder.ItemName(itemName);
        return lazadaOrder;
    }

    public LazadaOrder variation(String variation) {
        lazadaOrder.Variation(variation);
        return lazadaOrder;
    }

    public LazadaOrder cdShippingProvider(String cdShippingProvider) {
        lazadaOrder.CdShippingProvider(cdShippingProvider);
        return lazadaOrder;
    }

    public LazadaOrder shippingProvider(String shippingProvider) {
        lazadaOrder.ShippingProvider(shippingProvider);
        return lazadaOrder;
    }

    public LazadaOrder shipmentTypeName(String shipmentTypeName) {
        lazadaOrder.ShipmentTypeName(shipmentTypeName);
        return lazadaOrder;
    }

    public LazadaOrder shippingProviderType(String shippingProviderType) {
        lazadaOrder.ShippingProviderType(shippingProviderType);
        return lazadaOrder;
    }

    public LazadaOrder cdTrackingCode(String cdTrackingCode) {
        lazadaOrder.CdTrackingCode(cdTrackingCode);
        return lazadaOrder;
    }

    public LazadaOrder trackingCode(String trackingCode) {
        lazadaOrder.TrackingCode(trackingCode);
        return lazadaOrder;
    }

    public LazadaOrder trackingUrl(String trackingUrl) {
        lazadaOrder.TrackingUrl(trackingUrl);
        return lazadaOrder;
    }

    public LazadaOrder shippingProviderFM(String shippingProviderFM) {
        lazadaOrder.ShippingProviderFM(shippingProviderFM);
        return lazadaOrder;
    }

    public LazadaOrder trackingCodeFM(String trackingCodeFM) {
        lazadaOrder.TrackingCodeFM(trackingCodeFM);
        return lazadaOrder;
    }

    public LazadaOrder trackingUrlFM(String trackingUrlFM) {
        lazadaOrder.TrackingUrlFM(trackingUrlFM);
        return lazadaOrder;
    }

    public LazadaOrder promisedShippingTime(LocalDateTime promisedShippingTime) {
        lazadaOrder.PromisedShippingTime(promisedShippingTime);
        return lazadaOrder;
    }

    public LazadaOrder premium(String premium) {
        lazadaOrder.Premium(premium);
        return lazadaOrder;
    }

    public LazadaOrder status(String status) {
        lazadaOrder.Status(status);
        return lazadaOrder;
    }

    public LazadaOrder buyerFailedDeliveryReturnInitiator(String buyerFailedDeliveryReturnInitiator) {
        lazadaOrder.BuyerFailedDeliveryReturnInitiator(buyerFailedDeliveryReturnInitiator);
        return lazadaOrder;
    }

    public LazadaOrder buyerFailedDeliveryReason(String buyerFailedDeliveryReason) {
        lazadaOrder.BuyerFailedDeliveryReason(buyerFailedDeliveryReason);
        return lazadaOrder;
    }

    public LazadaOrder buyerFailedDeliveryDetail(String buyerFailedDeliveryDetail) {
        lazadaOrder.BuyerFailedDeliveryDetail(buyerFailedDeliveryDetail);
        return lazadaOrder;
    }

    public LazadaOrder buyerFailedDeliveryUserName(String buyerFailedDeliveryUserName) {
        lazadaOrder.BuyerFailedDeliveryUserName(buyerFailedDeliveryUserName);
        return lazadaOrder;
    }

    public LazadaOrder bundleId(String bundleId) {
        lazadaOrder.BundleId(bundleId);
        return lazadaOrder;
    }

    public LazadaOrder bundleDiscount(String bundleDiscount) {
        lazadaOrder.BundleDiscount(bundleDiscount);
        return lazadaOrder;
    }

    public LazadaOrder refundAmount(String refundAmount) {
        lazadaOrder.RefundAmount(refundAmount);
        return lazadaOrder;
    }

    public LazadaOrder shop(Shop shop) {
        lazadaOrder.Shop(shop);
        return lazadaOrder;
    }
}
