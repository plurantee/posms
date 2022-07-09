package com.flogramming.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * A LazadaOrder.
 */
@Entity
@Table(name = "lazada_order")
public class LazadaOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "order_item_id")
    private String orderItemId;

    @Column(name = "order_type")
    private String orderType;

    @Column(name = "guarantee")
    private String guarantee;

    @Column(name = "delivery_type")
    private String deliveryType;

    @Column(name = "lazada_id")
    private String lazadaId;

    @Column(name = "seller_sku")
    private String sellerSku;

    @Column(name = "lazada_sku")
    private String lazadaSku;

    @Column(name = "ware_house")
    private String wareHouse;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "rta_sla")
    private LocalDateTime rtaSla;

    @Column(name = "tts_sla")
    private LocalDateTime ttsSla;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "invoice_required")
    private Boolean invoiceRequired;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "national_registration_number")
    private String nationalRegistrationNumber;

    @Column(name = "shipping_name")
    private String shippingName;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "shipping_address_2")
    private String shippingAddress2;

    @Column(name = "shipping_address_3")
    private String shippingAddress3;

    @Column(name = "shipping_address_4")
    private String shippingAddress4;

    @Column(name = "shipping_address_5")
    private String shippingAddress5;

    @Column(name = "shipping_phone")
    private String shippingPhone;

    @Column(name = "shipping_phone_2")
    private String shippingPhone2;

    @Column(name = "shipping_city")
    private String shippingCity;

    @Column(name = "shipping_post_code")
    private String shippingPostCode;

    @Column(name = "shipping_country")
    private String shippingCountry;

    @Column(name = "shipping_region")
    private String shippingRegion;

    @Column(name = "billing_name")
    private String billingName;

    @Column(name = "billing_addr")
    private String billingAddr;

    @Column(name = "billing_addr_2")
    private String billingAddr2;

    @Column(name = "billing_addr_3")
    private String billingAddr3;

    @Column(name = "billing_addr_4")
    private String billingAddr4;

    @Column(name = "billing_addr_5")
    private String billingAddr5;

    @Column(name = "billing_phone")
    private String billingPhone;

    @Column(name = "billing_phone_2")
    private String billingPhone2;

    @Column(name = "billing_city")
    private String billingCity;

    @Column(name = "billing_post_code")
    private String billingPostCode;

    @Column(name = "billing_country")
    private String billingCountry;

    @Column(name = "tax_code")
    private String taxCode;

    @Column(name = "branch_number")
    private String branchNumber;

    @Column(name = "tax_invoice_requested")
    private String taxInvoiceRequested;

    @Column(name = "pay_method")
    private String payMethod;

    @Column(name = "paid_price")
    private String paidPrice;

    @Column(name = "unit_price")
    private String unitPrice;

    @Column(name = "seller_discount_total")
    private String sellerDiscountTotal;

    @Column(name = "shipping_fee")
    private String shippingFee;

    @Column(name = "wallet_credit")
    private String walletCredit;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "variation")
    private String variation;

    @Column(name = "cd_shipping_provider")
    private String cdShippingProvider;

    @Column(name = "shipping_provider")
    private String shippingProvider;

    @Column(name = "shipment_type_name")
    private String shipmentTypeName;

    @Column(name = "shipping_provider_type")
    private String shippingProviderType;

    @Column(name = "cd_tracking_code")
    private String cdTrackingCode;

    @Column(name = "tracking_code")
    private String trackingCode;

    @Column(name = "tracking_url")
    private String trackingUrl;

    @Column(name = "shipping_provider_fm")
    private String shippingProviderFM;

    @Column(name = "tracking_code_fm")
    private String trackingCodeFM;

    @Column(name = "tracking_url_fm")
    private String trackingUrlFM;

    @Column(name = "promised_shipping_time")
    private LocalDateTime promisedShippingTime;

    @Column(name = "premium")
    private String premium;

    @Column(name = "status")
    private String status;

    @Column(name = "buyer_failed_delivery_return_initiator")
    private String buyerFailedDeliveryReturnInitiator;

    @Column(name = "buyer_failed_delivery_reason")
    private String buyerFailedDeliveryReason;

    @Column(name = "buyer_failed_delivery_detail")
    private String buyerFailedDeliveryDetail;

    @Column(name = "buyer_failed_delivery_user_name")
    private String buyerFailedDeliveryUserName;

    @Column(name = "bundle_id")
    private String bundleId;

    @Column(name = "bundle_discount")
    private String bundleDiscount;

    @Column(name = "refund_amount")
    private String refundAmount;

    @ManyToOne
    @JsonIgnoreProperties(value = { "lazadaOrders", "clientCode" }, allowSetters = true)
    private Shop shop;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public LazadaOrder id(Long id) {
        this.Id(id);
        return this;
    }

    public void Id(Long id) {
        this.id = id;
    }

    public String getOrderItemId() {
        return this.orderItemId;
    }

    public LazadaOrder orderItemId(String orderItemId) {
        this.OrderItemId(orderItemId);
        return this;
    }

    public void OrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getOrderType() {
        return this.orderType;
    }

    public LazadaOrder orderType(String orderType) {
        this.OrderType(orderType);
        return this;
    }

    public void OrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getGuarantee() {
        return this.guarantee;
    }

    public LazadaOrder guarantee(String guarantee) {
        this.Guarantee(guarantee);
        return this;
    }

    public void Guarantee(String guarantee) {
        this.guarantee = guarantee;
    }

    public String getDeliveryType() {
        return this.deliveryType;
    }

    public LazadaOrder deliveryType(String deliveryType) {
        this.DeliveryType(deliveryType);
        return this;
    }

    public void DeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getLazadaId() {
        return this.lazadaId;
    }

    public LazadaOrder lazadaId(String lazadaId) {
        this.LazadaId(lazadaId);
        return this;
    }

    public void LazadaId(String lazadaId) {
        this.lazadaId = lazadaId;
    }

    public String getSellerSku() {
        return this.sellerSku;
    }

    public LazadaOrder sellerSku(String sellerSku) {
        this.SellerSku(sellerSku);
        return this;
    }

    public void SellerSku(String sellerSku) {
        this.sellerSku = sellerSku;
    }

    public String getLazadaSku() {
        return this.lazadaSku;
    }

    public LazadaOrder lazadaSku(String lazadaSku) {
        this.LazadaSku(lazadaSku);
        return this;
    }

    public void LazadaSku(String lazadaSku) {
        this.lazadaSku = lazadaSku;
    }

    public String getWareHouse() {
        return this.wareHouse;
    }

    public LazadaOrder wareHouse(String wareHouse) {
        this.WareHouse(wareHouse);
        return this;
    }

    public void WareHouse(String wareHouse) {
        this.wareHouse = wareHouse;
    }

    public LocalDateTime getCreateTime() {
        return this.createTime;
    }

    public LazadaOrder createTime(LocalDateTime createTime) {
        this.CreateTime(createTime);
        return this;
    }

    public void CreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return this.updateTime;
    }

    public LazadaOrder updateTime(LocalDateTime updateTime) {
        this.UpdateTime(updateTime);
        return this;
    }

    public void UpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getRtaSla() {
        return this.rtaSla;
    }

    public LazadaOrder rtaSla(LocalDateTime rtaSla) {
        this.RtaSla(rtaSla);
        return this;
    }

    public void RtaSla(LocalDateTime rtaSla) {
        this.rtaSla = rtaSla;
    }

    public LocalDateTime getTtsSla() {
        return this.ttsSla;
    }

    public LazadaOrder ttsSla(LocalDateTime ttsSla) {
        this.TtsSla(ttsSla);
        return this;
    }

    public void TtsSla(LocalDateTime ttsSla) {
        this.ttsSla = ttsSla;
    }

    public String getOrderNumber() {
        return this.orderNumber;
    }

    public LazadaOrder orderNumber(String orderNumber) {
        this.OrderNumber(orderNumber);
        return this;
    }

    public void OrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Boolean getInvoiceRequired() {
        return this.invoiceRequired;
    }

    public LazadaOrder invoiceRequired(Boolean invoiceRequired) {
        this.InvoiceRequired(invoiceRequired);
        return this;
    }

    public void InvoiceRequired(Boolean invoiceRequired) {
        this.invoiceRequired = invoiceRequired;
    }

    public String getInvoiceNumber() {
        return this.invoiceNumber;
    }

    public LazadaOrder invoiceNumber(String invoiceNumber) {
        this.InvoiceNumber(invoiceNumber);
        return this;
    }

    public void InvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDateTime getDeliveryDate() {
        return this.deliveryDate;
    }

    public LazadaOrder deliveryDate(LocalDateTime deliveryDate) {
        this.DeliveryDate(deliveryDate);
        return this;
    }

    public void DeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public LazadaOrder customerName(String customerName) {
        this.CustomerName(customerName);
        return this;
    }

    public void CustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return this.customerEmail;
    }

    public LazadaOrder customerEmail(String customerEmail) {
        this.CustomerEmail(customerEmail);
        return this;
    }

    public void CustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getNationalRegistrationNumber() {
        return this.nationalRegistrationNumber;
    }

    public LazadaOrder nationalRegistrationNumber(String nationalRegistrationNumber) {
        this.NationalRegistrationNumber(nationalRegistrationNumber);
        return this;
    }

    public void NationalRegistrationNumber(String nationalRegistrationNumber) {
        this.nationalRegistrationNumber = nationalRegistrationNumber;
    }

    public String getShippingName() {
        return this.shippingName;
    }

    public LazadaOrder shippingName(String shippingName) {
        this.ShippingName(shippingName);
        return this;
    }

    public void ShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public String getShippingAddress() {
        return this.shippingAddress;
    }

    public LazadaOrder shippingAddress(String shippingAddress) {
        this.ShippingAddress(shippingAddress);
        return this;
    }

    public void ShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingAddress2() {
        return this.shippingAddress2;
    }

    public LazadaOrder shippingAddress2(String shippingAddress2) {
        this.ShippingAddress2(shippingAddress2);
        return this;
    }

    public void ShippingAddress2(String shippingAddress2) {
        this.shippingAddress2 = shippingAddress2;
    }

    public String getShippingAddress3() {
        return this.shippingAddress3;
    }

    public LazadaOrder shippingAddress3(String shippingAddress3) {
        this.ShippingAddress3(shippingAddress3);
        return this;
    }

    public void ShippingAddress3(String shippingAddress3) {
        this.shippingAddress3 = shippingAddress3;
    }

    public String getShippingAddress4() {
        return this.shippingAddress4;
    }

    public LazadaOrder shippingAddress4(String shippingAddress4) {
        this.ShippingAddress4(shippingAddress4);
        return this;
    }

    public void ShippingAddress4(String shippingAddress4) {
        this.shippingAddress4 = shippingAddress4;
    }

    public String getShippingAddress5() {
        return this.shippingAddress5;
    }

    public LazadaOrder shippingAddress5(String shippingAddress5) {
        this.ShippingAddress5(shippingAddress5);
        return this;
    }

    public void ShippingAddress5(String shippingAddress5) {
        this.shippingAddress5 = shippingAddress5;
    }

    public String getShippingPhone() {
        return this.shippingPhone;
    }

    public LazadaOrder shippingPhone(String shippingPhone) {
        this.ShippingPhone(shippingPhone);
        return this;
    }

    public void ShippingPhone(String shippingPhone) {
        this.shippingPhone = shippingPhone;
    }

    public String getShippingPhone2() {
        return this.shippingPhone2;
    }

    public LazadaOrder shippingPhone2(String shippingPhone2) {
        this.ShippingPhone2(shippingPhone2);
        return this;
    }

    public void ShippingPhone2(String shippingPhone2) {
        this.shippingPhone2 = shippingPhone2;
    }

    public String getShippingCity() {
        return this.shippingCity;
    }

    public LazadaOrder shippingCity(String shippingCity) {
        this.ShippingCity(shippingCity);
        return this;
    }

    public void ShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingPostCode() {
        return this.shippingPostCode;
    }

    public LazadaOrder shippingPostCode(String shippingPostCode) {
        this.ShippingPostCode(shippingPostCode);
        return this;
    }

    public void ShippingPostCode(String shippingPostCode) {
        this.shippingPostCode = shippingPostCode;
    }

    public String getShippingCountry() {
        return this.shippingCountry;
    }

    public LazadaOrder shippingCountry(String shippingCountry) {
        this.ShippingCountry(shippingCountry);
        return this;
    }

    public void ShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    public String getShippingRegion() {
        return this.shippingRegion;
    }

    public LazadaOrder shippingRegion(String shippingRegion) {
        this.ShippingRegion(shippingRegion);
        return this;
    }

    public void ShippingRegion(String shippingRegion) {
        this.shippingRegion = shippingRegion;
    }

    public String getBillingName() {
        return this.billingName;
    }

    public LazadaOrder billingName(String billingName) {
        this.BillingName(billingName);
        return this;
    }

    public void BillingName(String billingName) {
        this.billingName = billingName;
    }

    public String getBillingAddr() {
        return this.billingAddr;
    }

    public LazadaOrder billingAddr(String billingAddr) {
        this.BillingAddr(billingAddr);
        return this;
    }

    public void BillingAddr(String billingAddr) {
        this.billingAddr = billingAddr;
    }

    public String getBillingAddr2() {
        return this.billingAddr2;
    }

    public LazadaOrder billingAddr2(String billingAddr2) {
        this.BillingAddr2(billingAddr2);
        return this;
    }

    public void BillingAddr2(String billingAddr2) {
        this.billingAddr2 = billingAddr2;
    }

    public String getBillingAddr3() {
        return this.billingAddr3;
    }

    public LazadaOrder billingAddr3(String billingAddr3) {
        this.BillingAddr3(billingAddr3);
        return this;
    }

    public void BillingAddr3(String billingAddr3) {
        this.billingAddr3 = billingAddr3;
    }

    public String getBillingAddr4() {
        return this.billingAddr4;
    }

    public LazadaOrder billingAddr4(String billingAddr4) {
        this.BillingAddr4(billingAddr4);
        return this;
    }

    public void BillingAddr4(String billingAddr4) {
        this.billingAddr4 = billingAddr4;
    }

    public String getBillingAddr5() {
        return this.billingAddr5;
    }

    public LazadaOrder billingAddr5(String billingAddr5) {
        this.BillingAddr5(billingAddr5);
        return this;
    }

    public void BillingAddr5(String billingAddr5) {
        this.billingAddr5 = billingAddr5;
    }

    public String getBillingPhone() {
        return this.billingPhone;
    }

    public LazadaOrder billingPhone(String billingPhone) {
        this.BillingPhone(billingPhone);
        return this;
    }

    public void BillingPhone(String billingPhone) {
        this.billingPhone = billingPhone;
    }

    public String getBillingPhone2() {
        return this.billingPhone2;
    }

    public LazadaOrder billingPhone2(String billingPhone2) {
        this.BillingPhone2(billingPhone2);
        return this;
    }

    public void BillingPhone2(String billingPhone2) {
        this.billingPhone2 = billingPhone2;
    }

    public String getBillingCity() {
        return this.billingCity;
    }

    public LazadaOrder billingCity(String billingCity) {
        this.BillingCity(billingCity);
        return this;
    }

    public void BillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingPostCode() {
        return this.billingPostCode;
    }

    public LazadaOrder billingPostCode(String billingPostCode) {
        this.BillingPostCode(billingPostCode);
        return this;
    }

    public void BillingPostCode(String billingPostCode) {
        this.billingPostCode = billingPostCode;
    }

    public String getBillingCountry() {
        return this.billingCountry;
    }

    public LazadaOrder billingCountry(String billingCountry) {
        this.BillingCountry(billingCountry);
        return this;
    }

    public void BillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getTaxCode() {
        return this.taxCode;
    }

    public LazadaOrder taxCode(String taxCode) {
        this.TaxCode(taxCode);
        return this;
    }

    public void TaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getBranchNumber() {
        return this.branchNumber;
    }

    public LazadaOrder branchNumber(String branchNumber) {
        this.BranchNumber(branchNumber);
        return this;
    }

    public void BranchNumber(String branchNumber) {
        this.branchNumber = branchNumber;
    }

    public String getTaxInvoiceRequested() {
        return this.taxInvoiceRequested;
    }

    public LazadaOrder taxInvoiceRequested(String taxInvoiceRequested) {
        this.TaxInvoiceRequested(taxInvoiceRequested);
        return this;
    }

    public void TaxInvoiceRequested(String taxInvoiceRequested) {
        this.taxInvoiceRequested = taxInvoiceRequested;
    }

    public String getPayMethod() {
        return this.payMethod;
    }

    public LazadaOrder payMethod(String payMethod) {
        this.PayMethod(payMethod);
        return this;
    }

    public void PayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getPaidPrice() {
        return this.paidPrice;
    }

    public LazadaOrder paidPrice(String paidPrice) {
        this.PaidPrice(paidPrice);
        return this;
    }

    public void PaidPrice(String paidPrice) {
        this.paidPrice = paidPrice;
    }

    public String getUnitPrice() {
        return this.unitPrice;
    }

    public LazadaOrder unitPrice(String unitPrice) {
        this.UnitPrice(unitPrice);
        return this;
    }

    public void UnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSellerDiscountTotal() {
        return this.sellerDiscountTotal;
    }

    public LazadaOrder sellerDiscountTotal(String sellerDiscountTotal) {
        this.SellerDiscountTotal(sellerDiscountTotal);
        return this;
    }

    public void SellerDiscountTotal(String sellerDiscountTotal) {
        this.sellerDiscountTotal = sellerDiscountTotal;
    }

    public String getShippingFee() {
        return this.shippingFee;
    }

    public LazadaOrder shippingFee(String shippingFee) {
        this.ShippingFee(shippingFee);
        return this;
    }

    public void ShippingFee(String shippingFee) {
        this.shippingFee = shippingFee;
    }

    public String getWalletCredit() {
        return this.walletCredit;
    }

    public LazadaOrder walletCredit(String walletCredit) {
        this.WalletCredit(walletCredit);
        return this;
    }

    public void WalletCredit(String walletCredit) {
        this.walletCredit = walletCredit;
    }

    public String getItemName() {
        return this.itemName;
    }

    public LazadaOrder itemName(String itemName) {
        this.ItemName(itemName);
        return this;
    }

    public void ItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getVariation() {
        return this.variation;
    }

    public LazadaOrder variation(String variation) {
        this.Variation(variation);
        return this;
    }

    public void Variation(String variation) {
        this.variation = variation;
    }

    public String getCdShippingProvider() {
        return this.cdShippingProvider;
    }

    public LazadaOrder cdShippingProvider(String cdShippingProvider) {
        this.CdShippingProvider(cdShippingProvider);
        return this;
    }

    public void CdShippingProvider(String cdShippingProvider) {
        this.cdShippingProvider = cdShippingProvider;
    }

    public String getShippingProvider() {
        return this.shippingProvider;
    }

    public LazadaOrder shippingProvider(String shippingProvider) {
        this.ShippingProvider(shippingProvider);
        return this;
    }

    public void ShippingProvider(String shippingProvider) {
        this.shippingProvider = shippingProvider;
    }

    public String getShipmentTypeName() {
        return this.shipmentTypeName;
    }

    public LazadaOrder shipmentTypeName(String shipmentTypeName) {
        this.ShipmentTypeName(shipmentTypeName);
        return this;
    }

    public void ShipmentTypeName(String shipmentTypeName) {
        this.shipmentTypeName = shipmentTypeName;
    }

    public String getShippingProviderType() {
        return this.shippingProviderType;
    }

    public LazadaOrder shippingProviderType(String shippingProviderType) {
        this.ShippingProviderType(shippingProviderType);
        return this;
    }

    public void ShippingProviderType(String shippingProviderType) {
        this.shippingProviderType = shippingProviderType;
    }

    public String getCdTrackingCode() {
        return this.cdTrackingCode;
    }

    public LazadaOrder cdTrackingCode(String cdTrackingCode) {
        this.CdTrackingCode(cdTrackingCode);
        return this;
    }

    public void CdTrackingCode(String cdTrackingCode) {
        this.cdTrackingCode = cdTrackingCode;
    }

    public String getTrackingCode() {
        return this.trackingCode;
    }

    public LazadaOrder trackingCode(String trackingCode) {
        this.TrackingCode(trackingCode);
        return this;
    }

    public void TrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }

    public String getTrackingUrl() {
        return this.trackingUrl;
    }

    public LazadaOrder trackingUrl(String trackingUrl) {
        this.TrackingUrl(trackingUrl);
        return this;
    }

    public void TrackingUrl(String trackingUrl) {
        this.trackingUrl = trackingUrl;
    }

    public String getShippingProviderFM() {
        return this.shippingProviderFM;
    }

    public LazadaOrder shippingProviderFM(String shippingProviderFM) {
        this.ShippingProviderFM(shippingProviderFM);
        return this;
    }

    public void ShippingProviderFM(String shippingProviderFM) {
        this.shippingProviderFM = shippingProviderFM;
    }

    public String getTrackingCodeFM() {
        return this.trackingCodeFM;
    }

    public LazadaOrder trackingCodeFM(String trackingCodeFM) {
        this.TrackingCodeFM(trackingCodeFM);
        return this;
    }

    public void TrackingCodeFM(String trackingCodeFM) {
        this.trackingCodeFM = trackingCodeFM;
    }

    public String getTrackingUrlFM() {
        return this.trackingUrlFM;
    }

    public LazadaOrder trackingUrlFM(String trackingUrlFM) {
        this.TrackingUrlFM(trackingUrlFM);
        return this;
    }

    public void TrackingUrlFM(String trackingUrlFM) {
        this.trackingUrlFM = trackingUrlFM;
    }

    public LocalDateTime getPromisedShippingTime() {
        return this.promisedShippingTime;
    }

    public LazadaOrder promisedShippingTime(LocalDateTime promisedShippingTime) {
        this.PromisedShippingTime(promisedShippingTime);
        return this;
    }

    public void PromisedShippingTime(LocalDateTime promisedShippingTime) {
        this.promisedShippingTime = promisedShippingTime;
    }

    public String getPremium() {
        return this.premium;
    }

    public LazadaOrder premium(String premium) {
        this.Premium(premium);
        return this;
    }

    public void Premium(String premium) {
        this.premium = premium;
    }

    public String getStatus() {
        return this.status;
    }

    public LazadaOrder status(String status) {
        this.Status(status);
        return this;
    }

    public void Status(String status) {
        this.status = status;
    }

    public String getBuyerFailedDeliveryReturnInitiator() {
        return this.buyerFailedDeliveryReturnInitiator;
    }

    public LazadaOrder buyerFailedDeliveryReturnInitiator(String buyerFailedDeliveryReturnInitiator) {
        this.BuyerFailedDeliveryReturnInitiator(buyerFailedDeliveryReturnInitiator);
        return this;
    }

    public void BuyerFailedDeliveryReturnInitiator(String buyerFailedDeliveryReturnInitiator) {
        this.buyerFailedDeliveryReturnInitiator = buyerFailedDeliveryReturnInitiator;
    }

    public String getBuyerFailedDeliveryReason() {
        return this.buyerFailedDeliveryReason;
    }

    public LazadaOrder buyerFailedDeliveryReason(String buyerFailedDeliveryReason) {
        this.BuyerFailedDeliveryReason(buyerFailedDeliveryReason);
        return this;
    }

    public void BuyerFailedDeliveryReason(String buyerFailedDeliveryReason) {
        this.buyerFailedDeliveryReason = buyerFailedDeliveryReason;
    }

    public String getBuyerFailedDeliveryDetail() {
        return this.buyerFailedDeliveryDetail;
    }

    public LazadaOrder buyerFailedDeliveryDetail(String buyerFailedDeliveryDetail) {
        this.BuyerFailedDeliveryDetail(buyerFailedDeliveryDetail);
        return this;
    }

    public void BuyerFailedDeliveryDetail(String buyerFailedDeliveryDetail) {
        this.buyerFailedDeliveryDetail = buyerFailedDeliveryDetail;
    }

    public String getBuyerFailedDeliveryUserName() {
        return this.buyerFailedDeliveryUserName;
    }

    public LazadaOrder buyerFailedDeliveryUserName(String buyerFailedDeliveryUserName) {
        this.BuyerFailedDeliveryUserName(buyerFailedDeliveryUserName);
        return this;
    }

    public void BuyerFailedDeliveryUserName(String buyerFailedDeliveryUserName) {
        this.buyerFailedDeliveryUserName = buyerFailedDeliveryUserName;
    }

    public String getBundleId() {
        return this.bundleId;
    }

    public LazadaOrder bundleId(String bundleId) {
        this.BundleId(bundleId);
        return this;
    }

    public void BundleId(String bundleId) {
        this.bundleId = bundleId;
    }

    public String getBundleDiscount() {
        return this.bundleDiscount;
    }

    public LazadaOrder bundleDiscount(String bundleDiscount) {
        this.BundleDiscount(bundleDiscount);
        return this;
    }

    public void BundleDiscount(String bundleDiscount) {
        this.bundleDiscount = bundleDiscount;
    }

    public String getRefundAmount() {
        return this.refundAmount;
    }

    public LazadaOrder refundAmount(String refundAmount) {
        this.RefundAmount(refundAmount);
        return this;
    }

    public void RefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Shop getShop() {
        return this.shop;
    }

    public void Shop(Shop shop) {
        this.shop = shop;
    }

    public LazadaOrder shop(Shop shop) {
        this.Shop(shop);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LazadaOrder)) {
            return false;
        }
        return id != null && id.equals(((LazadaOrder) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LazadaOrder{" +
            "id=" + getId() +
            ", orderItemId='" + getOrderItemId() + "'" +
            ", orderType='" + getOrderType() + "'" +
            ", guarantee='" + getGuarantee() + "'" +
            ", deliveryType='" + getDeliveryType() + "'" +
            ", lazadaId='" + getLazadaId() + "'" +
            ", sellerSku='" + getSellerSku() + "'" +
            ", lazadaSku='" + getLazadaSku() + "'" +
            ", wareHouse='" + getWareHouse() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", rtaSla='" + getRtaSla() + "'" +
            ", ttsSla='" + getTtsSla() + "'" +
            ", orderNumber='" + getOrderNumber() + "'" +
            ", invoiceRequired='" + getInvoiceRequired() + "'" +
            ", invoiceNumber='" + getInvoiceNumber() + "'" +
            ", deliveryDate='" + getDeliveryDate() + "'" +
            ", customerName='" + getCustomerName() + "'" +
            ", customerEmail='" + getCustomerEmail() + "'" +
            ", nationalRegistrationNumber='" + getNationalRegistrationNumber() + "'" +
            ", shippingName='" + getShippingName() + "'" +
            ", shippingAddress='" + getShippingAddress() + "'" +
            ", shippingAddress2='" + getShippingAddress2() + "'" +
            ", shippingAddress3='" + getShippingAddress3() + "'" +
            ", shippingAddress4='" + getShippingAddress4() + "'" +
            ", shippingAddress5='" + getShippingAddress5() + "'" +
            ", shippingPhone='" + getShippingPhone() + "'" +
            ", shippingPhone2='" + getShippingPhone2() + "'" +
            ", shippingCity='" + getShippingCity() + "'" +
            ", shippingPostCode='" + getShippingPostCode() + "'" +
            ", shippingCountry='" + getShippingCountry() + "'" +
            ", shippingRegion='" + getShippingRegion() + "'" +
            ", billingName='" + getBillingName() + "'" +
            ", billingAddr='" + getBillingAddr() + "'" +
            ", billingAddr3='" + getBillingAddr3() + "'" +
            ", billingAddr4='" + getBillingAddr4() + "'" +
            ", billingAddr5='" + getBillingAddr5() + "'" +
            ", billingPhone='" + getBillingPhone() + "'" +
            ", billingPhone2='" + getBillingPhone2() + "'" +
            ", billingCity='" + getBillingCity() + "'" +
            ", billingPostCode='" + getBillingPostCode() + "'" +
            ", billingCountry='" + getBillingCountry() + "'" +
            ", taxCode='" + getTaxCode() + "'" +
            ", branchNumber='" + getBranchNumber() + "'" +
            ", taxInvoiceRequested='" + getTaxInvoiceRequested() + "'" +
            ", payMethod='" + getPayMethod() + "'" +
            ", paidPrice='" + getPaidPrice() + "'" +
            ", unitPrice='" + getUnitPrice() + "'" +
            ", sellerDiscountTotal='" + getSellerDiscountTotal() + "'" +
            ", shippingFee='" + getShippingFee() + "'" +
            ", walletCredit='" + getWalletCredit() + "'" +
            ", itemName='" + getItemName() + "'" +
            ", variation='" + getVariation() + "'" +
            ", cdShippingProvider='" + getCdShippingProvider() + "'" +
            ", shippingProvider='" + getShippingProvider() + "'" +
            ", shipmentTypeName='" + getShipmentTypeName() + "'" +
            ", shippingProviderType='" + getShippingProviderType() + "'" +
            ", cdTrackingCode='" + getCdTrackingCode() + "'" +
            ", trackingCode='" + getTrackingCode() + "'" +
            ", trackingUrl='" + getTrackingUrl() + "'" +
            ", shippingProviderFM='" + getShippingProviderFM() + "'" +
            ", trackingCodeFM='" + getTrackingCodeFM() + "'" +
            ", trackingUrlFM='" + getTrackingUrlFM() + "'" +
            ", promisedShippingTime='" + getPromisedShippingTime() + "'" +
            ", premium='" + getPremium() + "'" +
            ", status='" + getStatus() + "'" +
            ", buyerFailedDeliveryReturnInitiator='" + getBuyerFailedDeliveryReturnInitiator() + "'" +
            ", buyerFailedDeliveryReason='" + getBuyerFailedDeliveryReason() + "'" +
            ", buyerFailedDeliveryDetail='" + getBuyerFailedDeliveryDetail() + "'" +
            ", buyerFailedDeliveryUserName='" + getBuyerFailedDeliveryUserName() + "'" +
            ", bundleId='" + getBundleId() + "'" +
            ", bundleDiscount='" + getBundleDiscount() + "'" +
            ", refundAmount='" + getRefundAmount() + "'" +
            "}";
    }

    public LazadaOrder() {}

    public LazadaOrder(
        String orderItemId,
        String orderType,
        String guarantee,
        String deliveryType,
        String lazadaId,
        String sellerSku,
        String lazadaSku,
        String wareHouse,
        LocalDateTime createTime,
        LocalDateTime updateTime,
        LocalDateTime rtaSla,
        LocalDateTime ttsSla,
        String orderNumber,
        Boolean invoiceRequired,
        String invoiceNumber,
        LocalDateTime deliveryDate,
        String customerName,
        String customerEmail,
        String nationalRegistrationNumber,
        String shippingName,
        String shippingAddress,
        String shippingAddress2,
        String shippingAddress3,
        String shippingAddress4,
        String shippingAddress5,
        String shippingPhone,
        String shippingPhone2,
        String shippingCity,
        String shippingPostCode,
        String shippingCountry,
        String shippingRegion,
        String billingName,
        String billingAddr,
        String billingAddr3,
        String billingAddr4,
        String billingAddr5,
        String billingPhone,
        String billingPhone2,
        String billingCity,
        String billingPostCode,
        String billingCountry,
        String taxCode,
        String branchNumber,
        String taxInvoiceRequested,
        String payMethod,
        String paidPrice,
        String unitPrice,
        String sellerDiscountTotal,
        String shippingFee,
        String walletCredit,
        String itemName,
        String variation,
        String cdShippingProvider,
        String shippingProvider,
        String shipmentTypeName,
        String shippingProviderType,
        String cdTrackingCode,
        String trackingCode,
        String trackingUrl,
        String shippingProviderFM,
        String trackingCodeFM,
        String trackingUrlFM,
        LocalDateTime promisedShippingTime,
        String premium,
        String status,
        String buyerFailedDeliveryReturnInitiator,
        String buyerFailedDeliveryReason,
        String buyerFailedDeliveryDetail,
        String buyerFailedDeliveryUserName,
        String bundleId,
        String bundleDiscount,
        String refundAmount,
        Shop shop
    ) {
        this.orderItemId = orderItemId;
        this.orderType = orderType;
        this.guarantee = guarantee;
        this.deliveryType = deliveryType;
        this.lazadaId = lazadaId;
        this.sellerSku = sellerSku;
        this.lazadaSku = lazadaSku;
        this.wareHouse = wareHouse;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.rtaSla = rtaSla;
        this.ttsSla = ttsSla;
        this.orderNumber = orderNumber;
        this.invoiceRequired = invoiceRequired;
        this.invoiceNumber = invoiceNumber;
        this.deliveryDate = deliveryDate;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.nationalRegistrationNumber = nationalRegistrationNumber;
        this.shippingName = shippingName;
        this.shippingAddress = shippingAddress;
        this.shippingAddress2 = shippingAddress2;
        this.shippingAddress3 = shippingAddress3;
        this.shippingAddress4 = shippingAddress4;
        this.shippingAddress5 = shippingAddress5;
        this.shippingPhone = shippingPhone;
        this.shippingPhone2 = shippingPhone2;
        this.shippingCity = shippingCity;
        this.shippingPostCode = shippingPostCode;
        this.shippingCountry = shippingCountry;
        this.shippingRegion = shippingRegion;
        this.billingName = billingName;
        this.billingAddr = billingAddr;
        this.billingAddr3 = billingAddr3;
        this.billingAddr4 = billingAddr4;
        this.billingAddr5 = billingAddr5;
        this.billingPhone = billingPhone;
        this.billingPhone2 = billingPhone2;
        this.billingCity = billingCity;
        this.billingPostCode = billingPostCode;
        this.billingCountry = billingCountry;
        this.taxCode = taxCode;
        this.branchNumber = branchNumber;
        this.taxInvoiceRequested = taxInvoiceRequested;
        this.payMethod = payMethod;
        this.paidPrice = paidPrice;
        this.unitPrice = unitPrice;
        this.sellerDiscountTotal = sellerDiscountTotal;
        this.shippingFee = shippingFee;
        this.walletCredit = walletCredit;
        this.itemName = itemName;
        this.variation = variation;
        this.cdShippingProvider = cdShippingProvider;
        this.shippingProvider = shippingProvider;
        this.shipmentTypeName = shipmentTypeName;
        this.shippingProviderType = shippingProviderType;
        this.cdTrackingCode = cdTrackingCode;
        this.trackingCode = trackingCode;
        this.trackingUrl = trackingUrl;
        this.shippingProviderFM = shippingProviderFM;
        this.trackingCodeFM = trackingCodeFM;
        this.trackingUrlFM = trackingUrlFM;
        this.promisedShippingTime = promisedShippingTime;
        this.premium = premium;
        this.status = status;
        this.buyerFailedDeliveryReturnInitiator = buyerFailedDeliveryReturnInitiator;
        this.buyerFailedDeliveryReason = buyerFailedDeliveryReason;
        this.buyerFailedDeliveryDetail = buyerFailedDeliveryDetail;
        this.buyerFailedDeliveryUserName = buyerFailedDeliveryUserName;
        this.bundleId = bundleId;
        this.bundleDiscount = bundleDiscount;
        this.refundAmount = refundAmount;
        this.shop = shop;
    }
}
