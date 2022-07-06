package com.flogramming.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
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

    @Column(name = "ware_house")
    private String wareHouse;

    @Column(name = "create_time")
    private LocalDate createTime;

    @Column(name = "update_time")
    private LocalDate updateTime;

    @Column(name = "rta_sla")
    private LocalDate rtaSla;

    @Column(name = "tts_sla")
    private LocalDate ttsSla;

    @Column(name = "order_number")
    private LocalDate orderNumber;

    @Column(name = "invoice_required")
    private Boolean invoiceRequired;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

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
    private LocalDate promisedShippingTime;

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
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderItemId() {
        return this.orderItemId;
    }

    public LazadaOrder orderItemId(String orderItemId) {
        this.setOrderItemId(orderItemId);
        return this;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getOrderType() {
        return this.orderType;
    }

    public LazadaOrder orderType(String orderType) {
        this.setOrderType(orderType);
        return this;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getGuarantee() {
        return this.guarantee;
    }

    public LazadaOrder guarantee(String guarantee) {
        this.setGuarantee(guarantee);
        return this;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }

    public String getDeliveryType() {
        return this.deliveryType;
    }

    public LazadaOrder deliveryType(String deliveryType) {
        this.setDeliveryType(deliveryType);
        return this;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getLazadaId() {
        return this.lazadaId;
    }

    public LazadaOrder lazadaId(String lazadaId) {
        this.setLazadaId(lazadaId);
        return this;
    }

    public void setLazadaId(String lazadaId) {
        this.lazadaId = lazadaId;
    }

    public String getSellerSku() {
        return this.sellerSku;
    }

    public LazadaOrder sellerSku(String sellerSku) {
        this.setSellerSku(sellerSku);
        return this;
    }

    public void setSellerSku(String sellerSku) {
        this.sellerSku = sellerSku;
    }

    public String getWareHouse() {
        return this.wareHouse;
    }

    public LazadaOrder wareHouse(String wareHouse) {
        this.setWareHouse(wareHouse);
        return this;
    }

    public void setWareHouse(String wareHouse) {
        this.wareHouse = wareHouse;
    }

    public LocalDate getCreateTime() {
        return this.createTime;
    }

    public LazadaOrder createTime(LocalDate createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public LocalDate getUpdateTime() {
        return this.updateTime;
    }

    public LazadaOrder updateTime(LocalDate updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDate getRtaSla() {
        return this.rtaSla;
    }

    public LazadaOrder rtaSla(LocalDate rtaSla) {
        this.setRtaSla(rtaSla);
        return this;
    }

    public void setRtaSla(LocalDate rtaSla) {
        this.rtaSla = rtaSla;
    }

    public LocalDate getTtsSla() {
        return this.ttsSla;
    }

    public LazadaOrder ttsSla(LocalDate ttsSla) {
        this.setTtsSla(ttsSla);
        return this;
    }

    public void setTtsSla(LocalDate ttsSla) {
        this.ttsSla = ttsSla;
    }

    public LocalDate getOrderNumber() {
        return this.orderNumber;
    }

    public LazadaOrder orderNumber(LocalDate orderNumber) {
        this.setOrderNumber(orderNumber);
        return this;
    }

    public void setOrderNumber(LocalDate orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Boolean getInvoiceRequired() {
        return this.invoiceRequired;
    }

    public LazadaOrder invoiceRequired(Boolean invoiceRequired) {
        this.setInvoiceRequired(invoiceRequired);
        return this;
    }

    public void setInvoiceRequired(Boolean invoiceRequired) {
        this.invoiceRequired = invoiceRequired;
    }

    public String getInvoiceNumber() {
        return this.invoiceNumber;
    }

    public LazadaOrder invoiceNumber(String invoiceNumber) {
        this.setInvoiceNumber(invoiceNumber);
        return this;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDate getDeliveryDate() {
        return this.deliveryDate;
    }

    public LazadaOrder deliveryDate(LocalDate deliveryDate) {
        this.setDeliveryDate(deliveryDate);
        return this;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public LazadaOrder customerName(String customerName) {
        this.setCustomerName(customerName);
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return this.customerEmail;
    }

    public LazadaOrder customerEmail(String customerEmail) {
        this.setCustomerEmail(customerEmail);
        return this;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getNationalRegistrationNumber() {
        return this.nationalRegistrationNumber;
    }

    public LazadaOrder nationalRegistrationNumber(String nationalRegistrationNumber) {
        this.setNationalRegistrationNumber(nationalRegistrationNumber);
        return this;
    }

    public void setNationalRegistrationNumber(String nationalRegistrationNumber) {
        this.nationalRegistrationNumber = nationalRegistrationNumber;
    }

    public String getShippingName() {
        return this.shippingName;
    }

    public LazadaOrder shippingName(String shippingName) {
        this.setShippingName(shippingName);
        return this;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public String getShippingAddress() {
        return this.shippingAddress;
    }

    public LazadaOrder shippingAddress(String shippingAddress) {
        this.setShippingAddress(shippingAddress);
        return this;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingAddress2() {
        return this.shippingAddress2;
    }

    public LazadaOrder shippingAddress2(String shippingAddress2) {
        this.setShippingAddress2(shippingAddress2);
        return this;
    }

    public void setShippingAddress2(String shippingAddress2) {
        this.shippingAddress2 = shippingAddress2;
    }

    public String getShippingAddress3() {
        return this.shippingAddress3;
    }

    public LazadaOrder shippingAddress3(String shippingAddress3) {
        this.setShippingAddress3(shippingAddress3);
        return this;
    }

    public void setShippingAddress3(String shippingAddress3) {
        this.shippingAddress3 = shippingAddress3;
    }

    public String getShippingAddress4() {
        return this.shippingAddress4;
    }

    public LazadaOrder shippingAddress4(String shippingAddress4) {
        this.setShippingAddress4(shippingAddress4);
        return this;
    }

    public void setShippingAddress4(String shippingAddress4) {
        this.shippingAddress4 = shippingAddress4;
    }

    public String getShippingAddress5() {
        return this.shippingAddress5;
    }

    public LazadaOrder shippingAddress5(String shippingAddress5) {
        this.setShippingAddress5(shippingAddress5);
        return this;
    }

    public void setShippingAddress5(String shippingAddress5) {
        this.shippingAddress5 = shippingAddress5;
    }

    public String getShippingPhone() {
        return this.shippingPhone;
    }

    public LazadaOrder shippingPhone(String shippingPhone) {
        this.setShippingPhone(shippingPhone);
        return this;
    }

    public void setShippingPhone(String shippingPhone) {
        this.shippingPhone = shippingPhone;
    }

    public String getShippingPhone2() {
        return this.shippingPhone2;
    }

    public LazadaOrder shippingPhone2(String shippingPhone2) {
        this.setShippingPhone2(shippingPhone2);
        return this;
    }

    public void setShippingPhone2(String shippingPhone2) {
        this.shippingPhone2 = shippingPhone2;
    }

    public String getShippingCity() {
        return this.shippingCity;
    }

    public LazadaOrder shippingCity(String shippingCity) {
        this.setShippingCity(shippingCity);
        return this;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingPostCode() {
        return this.shippingPostCode;
    }

    public LazadaOrder shippingPostCode(String shippingPostCode) {
        this.setShippingPostCode(shippingPostCode);
        return this;
    }

    public void setShippingPostCode(String shippingPostCode) {
        this.shippingPostCode = shippingPostCode;
    }

    public String getShippingCountry() {
        return this.shippingCountry;
    }

    public LazadaOrder shippingCountry(String shippingCountry) {
        this.setShippingCountry(shippingCountry);
        return this;
    }

    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    public String getShippingRegion() {
        return this.shippingRegion;
    }

    public LazadaOrder shippingRegion(String shippingRegion) {
        this.setShippingRegion(shippingRegion);
        return this;
    }

    public void setShippingRegion(String shippingRegion) {
        this.shippingRegion = shippingRegion;
    }

    public String getBillingName() {
        return this.billingName;
    }

    public LazadaOrder billingName(String billingName) {
        this.setBillingName(billingName);
        return this;
    }

    public void setBillingName(String billingName) {
        this.billingName = billingName;
    }

    public String getBillingAddr() {
        return this.billingAddr;
    }

    public LazadaOrder billingAddr(String billingAddr) {
        this.setBillingAddr(billingAddr);
        return this;
    }

    public void setBillingAddr(String billingAddr) {
        this.billingAddr = billingAddr;
    }

    public String getBillingAddr3() {
        return this.billingAddr3;
    }

    public LazadaOrder billingAddr3(String billingAddr3) {
        this.setBillingAddr3(billingAddr3);
        return this;
    }

    public void setBillingAddr3(String billingAddr3) {
        this.billingAddr3 = billingAddr3;
    }

    public String getBillingAddr4() {
        return this.billingAddr4;
    }

    public LazadaOrder billingAddr4(String billingAddr4) {
        this.setBillingAddr4(billingAddr4);
        return this;
    }

    public void setBillingAddr4(String billingAddr4) {
        this.billingAddr4 = billingAddr4;
    }

    public String getBillingAddr5() {
        return this.billingAddr5;
    }

    public LazadaOrder billingAddr5(String billingAddr5) {
        this.setBillingAddr5(billingAddr5);
        return this;
    }

    public void setBillingAddr5(String billingAddr5) {
        this.billingAddr5 = billingAddr5;
    }

    public String getBillingPhone() {
        return this.billingPhone;
    }

    public LazadaOrder billingPhone(String billingPhone) {
        this.setBillingPhone(billingPhone);
        return this;
    }

    public void setBillingPhone(String billingPhone) {
        this.billingPhone = billingPhone;
    }

    public String getBillingPhone2() {
        return this.billingPhone2;
    }

    public LazadaOrder billingPhone2(String billingPhone2) {
        this.setBillingPhone2(billingPhone2);
        return this;
    }

    public void setBillingPhone2(String billingPhone2) {
        this.billingPhone2 = billingPhone2;
    }

    public String getBillingCity() {
        return this.billingCity;
    }

    public LazadaOrder billingCity(String billingCity) {
        this.setBillingCity(billingCity);
        return this;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingPostCode() {
        return this.billingPostCode;
    }

    public LazadaOrder billingPostCode(String billingPostCode) {
        this.setBillingPostCode(billingPostCode);
        return this;
    }

    public void setBillingPostCode(String billingPostCode) {
        this.billingPostCode = billingPostCode;
    }

    public String getBillingCountry() {
        return this.billingCountry;
    }

    public LazadaOrder billingCountry(String billingCountry) {
        this.setBillingCountry(billingCountry);
        return this;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getTaxCode() {
        return this.taxCode;
    }

    public LazadaOrder taxCode(String taxCode) {
        this.setTaxCode(taxCode);
        return this;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getBranchNumber() {
        return this.branchNumber;
    }

    public LazadaOrder branchNumber(String branchNumber) {
        this.setBranchNumber(branchNumber);
        return this;
    }

    public void setBranchNumber(String branchNumber) {
        this.branchNumber = branchNumber;
    }

    public String getTaxInvoiceRequested() {
        return this.taxInvoiceRequested;
    }

    public LazadaOrder taxInvoiceRequested(String taxInvoiceRequested) {
        this.setTaxInvoiceRequested(taxInvoiceRequested);
        return this;
    }

    public void setTaxInvoiceRequested(String taxInvoiceRequested) {
        this.taxInvoiceRequested = taxInvoiceRequested;
    }

    public String getPayMethod() {
        return this.payMethod;
    }

    public LazadaOrder payMethod(String payMethod) {
        this.setPayMethod(payMethod);
        return this;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getPaidPrice() {
        return this.paidPrice;
    }

    public LazadaOrder paidPrice(String paidPrice) {
        this.setPaidPrice(paidPrice);
        return this;
    }

    public void setPaidPrice(String paidPrice) {
        this.paidPrice = paidPrice;
    }

    public String getUnitPrice() {
        return this.unitPrice;
    }

    public LazadaOrder unitPrice(String unitPrice) {
        this.setUnitPrice(unitPrice);
        return this;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSellerDiscountTotal() {
        return this.sellerDiscountTotal;
    }

    public LazadaOrder sellerDiscountTotal(String sellerDiscountTotal) {
        this.setSellerDiscountTotal(sellerDiscountTotal);
        return this;
    }

    public void setSellerDiscountTotal(String sellerDiscountTotal) {
        this.sellerDiscountTotal = sellerDiscountTotal;
    }

    public String getShippingFee() {
        return this.shippingFee;
    }

    public LazadaOrder shippingFee(String shippingFee) {
        this.setShippingFee(shippingFee);
        return this;
    }

    public void setShippingFee(String shippingFee) {
        this.shippingFee = shippingFee;
    }

    public String getWalletCredit() {
        return this.walletCredit;
    }

    public LazadaOrder walletCredit(String walletCredit) {
        this.setWalletCredit(walletCredit);
        return this;
    }

    public void setWalletCredit(String walletCredit) {
        this.walletCredit = walletCredit;
    }

    public String getItemName() {
        return this.itemName;
    }

    public LazadaOrder itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getVariation() {
        return this.variation;
    }

    public LazadaOrder variation(String variation) {
        this.setVariation(variation);
        return this;
    }

    public void setVariation(String variation) {
        this.variation = variation;
    }

    public String getCdShippingProvider() {
        return this.cdShippingProvider;
    }

    public LazadaOrder cdShippingProvider(String cdShippingProvider) {
        this.setCdShippingProvider(cdShippingProvider);
        return this;
    }

    public void setCdShippingProvider(String cdShippingProvider) {
        this.cdShippingProvider = cdShippingProvider;
    }

    public String getShippingProvider() {
        return this.shippingProvider;
    }

    public LazadaOrder shippingProvider(String shippingProvider) {
        this.setShippingProvider(shippingProvider);
        return this;
    }

    public void setShippingProvider(String shippingProvider) {
        this.shippingProvider = shippingProvider;
    }

    public String getShipmentTypeName() {
        return this.shipmentTypeName;
    }

    public LazadaOrder shipmentTypeName(String shipmentTypeName) {
        this.setShipmentTypeName(shipmentTypeName);
        return this;
    }

    public void setShipmentTypeName(String shipmentTypeName) {
        this.shipmentTypeName = shipmentTypeName;
    }

    public String getShippingProviderType() {
        return this.shippingProviderType;
    }

    public LazadaOrder shippingProviderType(String shippingProviderType) {
        this.setShippingProviderType(shippingProviderType);
        return this;
    }

    public void setShippingProviderType(String shippingProviderType) {
        this.shippingProviderType = shippingProviderType;
    }

    public String getCdTrackingCode() {
        return this.cdTrackingCode;
    }

    public LazadaOrder cdTrackingCode(String cdTrackingCode) {
        this.setCdTrackingCode(cdTrackingCode);
        return this;
    }

    public void setCdTrackingCode(String cdTrackingCode) {
        this.cdTrackingCode = cdTrackingCode;
    }

    public String getTrackingCode() {
        return this.trackingCode;
    }

    public LazadaOrder trackingCode(String trackingCode) {
        this.setTrackingCode(trackingCode);
        return this;
    }

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }

    public String getTrackingUrl() {
        return this.trackingUrl;
    }

    public LazadaOrder trackingUrl(String trackingUrl) {
        this.setTrackingUrl(trackingUrl);
        return this;
    }

    public void setTrackingUrl(String trackingUrl) {
        this.trackingUrl = trackingUrl;
    }

    public String getShippingProviderFM() {
        return this.shippingProviderFM;
    }

    public LazadaOrder shippingProviderFM(String shippingProviderFM) {
        this.setShippingProviderFM(shippingProviderFM);
        return this;
    }

    public void setShippingProviderFM(String shippingProviderFM) {
        this.shippingProviderFM = shippingProviderFM;
    }

    public String getTrackingCodeFM() {
        return this.trackingCodeFM;
    }

    public LazadaOrder trackingCodeFM(String trackingCodeFM) {
        this.setTrackingCodeFM(trackingCodeFM);
        return this;
    }

    public void setTrackingCodeFM(String trackingCodeFM) {
        this.trackingCodeFM = trackingCodeFM;
    }

    public String getTrackingUrlFM() {
        return this.trackingUrlFM;
    }

    public LazadaOrder trackingUrlFM(String trackingUrlFM) {
        this.setTrackingUrlFM(trackingUrlFM);
        return this;
    }

    public void setTrackingUrlFM(String trackingUrlFM) {
        this.trackingUrlFM = trackingUrlFM;
    }

    public LocalDate getPromisedShippingTime() {
        return this.promisedShippingTime;
    }

    public LazadaOrder promisedShippingTime(LocalDate promisedShippingTime) {
        this.setPromisedShippingTime(promisedShippingTime);
        return this;
    }

    public void setPromisedShippingTime(LocalDate promisedShippingTime) {
        this.promisedShippingTime = promisedShippingTime;
    }

    public String getPremium() {
        return this.premium;
    }

    public LazadaOrder premium(String premium) {
        this.setPremium(premium);
        return this;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getStatus() {
        return this.status;
    }

    public LazadaOrder status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBuyerFailedDeliveryReturnInitiator() {
        return this.buyerFailedDeliveryReturnInitiator;
    }

    public LazadaOrder buyerFailedDeliveryReturnInitiator(String buyerFailedDeliveryReturnInitiator) {
        this.setBuyerFailedDeliveryReturnInitiator(buyerFailedDeliveryReturnInitiator);
        return this;
    }

    public void setBuyerFailedDeliveryReturnInitiator(String buyerFailedDeliveryReturnInitiator) {
        this.buyerFailedDeliveryReturnInitiator = buyerFailedDeliveryReturnInitiator;
    }

    public String getBuyerFailedDeliveryReason() {
        return this.buyerFailedDeliveryReason;
    }

    public LazadaOrder buyerFailedDeliveryReason(String buyerFailedDeliveryReason) {
        this.setBuyerFailedDeliveryReason(buyerFailedDeliveryReason);
        return this;
    }

    public void setBuyerFailedDeliveryReason(String buyerFailedDeliveryReason) {
        this.buyerFailedDeliveryReason = buyerFailedDeliveryReason;
    }

    public String getBuyerFailedDeliveryDetail() {
        return this.buyerFailedDeliveryDetail;
    }

    public LazadaOrder buyerFailedDeliveryDetail(String buyerFailedDeliveryDetail) {
        this.setBuyerFailedDeliveryDetail(buyerFailedDeliveryDetail);
        return this;
    }

    public void setBuyerFailedDeliveryDetail(String buyerFailedDeliveryDetail) {
        this.buyerFailedDeliveryDetail = buyerFailedDeliveryDetail;
    }

    public String getBuyerFailedDeliveryUserName() {
        return this.buyerFailedDeliveryUserName;
    }

    public LazadaOrder buyerFailedDeliveryUserName(String buyerFailedDeliveryUserName) {
        this.setBuyerFailedDeliveryUserName(buyerFailedDeliveryUserName);
        return this;
    }

    public void setBuyerFailedDeliveryUserName(String buyerFailedDeliveryUserName) {
        this.buyerFailedDeliveryUserName = buyerFailedDeliveryUserName;
    }

    public String getBundleId() {
        return this.bundleId;
    }

    public LazadaOrder bundleId(String bundleId) {
        this.setBundleId(bundleId);
        return this;
    }

    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }

    public String getBundleDiscount() {
        return this.bundleDiscount;
    }

    public LazadaOrder bundleDiscount(String bundleDiscount) {
        this.setBundleDiscount(bundleDiscount);
        return this;
    }

    public void setBundleDiscount(String bundleDiscount) {
        this.bundleDiscount = bundleDiscount;
    }

    public String getRefundAmount() {
        return this.refundAmount;
    }

    public LazadaOrder refundAmount(String refundAmount) {
        this.setRefundAmount(refundAmount);
        return this;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Shop getShop() {
        return this.shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public LazadaOrder shop(Shop shop) {
        this.setShop(shop);
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

    public void setFromExcelHashMap() {
        /*
        * orderItemId
            orderType
            Guarantee
            deliveryType
            lazadaId
            sellerSku
            lazadaSku
            wareHouse
            createTime
            updateTime
            rtsSla
            ttsSla
            orderNumber
            invoiceRequired
            invoiceNumber
            deliveredDate
            customerName
            customerEmail
            nationalRegistrationNumber
            shippingName
            shippingAddress
            shippingAddress2
            shippingAddress3
            shippingAddress4
            shippingAddress5
            shippingPhone
            shippingPhone2
            shippingCity
            shippingPostCode
            shippingCountry
            shippingRegion
            billingName
            billingAddr
            billingAddr2
            billingAddr3
            billingAddr4
            billingAddr5
            billingPhone
            billingPhone2
            billingCity
            billingPostCode
            billingCountry
            taxCode
            branchNumber
            taxInvoiceRequested
            payMethod
            paidPrice
            unitPrice
            sellerDiscountTotal
            shippingFee
            walletCredit
            itemName
            variation
            cdShippingProvider
            shippingProvider
            shipmentTypeName
            shippingProviderType
            cdTrackingCode
            trackingCode
            trackingUrl
            shippingProviderFM
            trackingCodeFM
            trackingUrlFM
            promisedShippingTime
            premium
            status
            buyerFailedDeliveryReturnInitiator
            buyerFailedDeliveryReason
            buyerFailedDeliveryDetail
            buyerFailedDeliveryUserName
            bundleId
            bundleDiscount
            refundAmount
        * */
    }
}
