package com.flogramming.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

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
    private ZonedDateTime createTime;

    @Column(name = "update_time")
    private ZonedDateTime updateTime;

    @Column(name = "rta_sla")
    private ZonedDateTime rtaSla;

    @Column(name = "tts_sla")
    private ZonedDateTime ttsSla;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "invoice_required")
    private Boolean invoiceRequired;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "delivery_date")
    private ZonedDateTime deliveryDate;

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
    private Double paidPrice;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "seller_discount_total")
    private Double sellerDiscountTotal;

    @Column(name = "shipping_fee")
    private Double shippingFee;

    @Column(name = "wallet_credit")
    private Double walletCredit;

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
    private ZonedDateTime promisedShippingTime;

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
    private Double bundleDiscount;

    @Column(name = "refund_amount")
    private Double refundAmount;

    @OneToMany(mappedBy = "lazadaOrder")
    @JsonIgnoreProperties(value = {"lazadaOrder"}, allowSetters = true)
    private Set<LazadaOrderPayments> payments = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = {"userInfos", "shops", "lazadaOrders", "shopeeOrders"}, allowSetters = true)
    private Client client;

    @ManyToOne
    @JsonIgnoreProperties(value = {"lazadaOrders", "shopeeOrders", "clientCode"}, allowSetters = true)
    private Shop shop;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LazadaOrder id(Long id) {
        this.setId(id);
        return this;
    }

    public String getOrderItemId() {
        return this.orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public LazadaOrder orderItemId(String orderItemId) {
        this.setOrderItemId(orderItemId);
        return this;
    }

    public String getOrderType() {
        return this.orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public LazadaOrder orderType(String orderType) {
        this.setOrderType(orderType);
        return this;
    }

    public String getGuarantee() {
        return this.guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }

    public LazadaOrder guarantee(String guarantee) {
        this.setGuarantee(guarantee);
        return this;
    }

    public String getDeliveryType() {
        return this.deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public LazadaOrder deliveryType(String deliveryType) {
        this.setDeliveryType(deliveryType);
        return this;
    }

    public String getLazadaId() {
        return this.lazadaId;
    }

    public void setLazadaId(String lazadaId) {
        this.lazadaId = lazadaId;
    }

    public LazadaOrder lazadaId(String lazadaId) {
        this.setLazadaId(lazadaId);
        return this;
    }

    public String getSellerSku() {
        return this.sellerSku;
    }

    public void setSellerSku(String sellerSku) {
        this.sellerSku = sellerSku;
    }

    public LazadaOrder sellerSku(String sellerSku) {
        this.setSellerSku(sellerSku);
        return this;
    }

    public String getLazadaSku() {
        return this.lazadaSku;
    }

    public void setLazadaSku(String lazadaSku) {
        this.lazadaSku = lazadaSku;
    }

    public LazadaOrder lazadaSku(String lazadaSku) {
        this.setLazadaSku(lazadaSku);
        return this;
    }

    public String getWareHouse() {
        return this.wareHouse;
    }

    public void setWareHouse(String wareHouse) {
        this.wareHouse = wareHouse;
    }

    public LazadaOrder wareHouse(String wareHouse) {
        this.setWareHouse(wareHouse);
        return this;
    }

    public ZonedDateTime getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public LazadaOrder createTime(ZonedDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public ZonedDateTime getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LazadaOrder updateTime(ZonedDateTime updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public ZonedDateTime getRtaSla() {
        return this.rtaSla;
    }

    public void setRtaSla(ZonedDateTime rtaSla) {
        this.rtaSla = rtaSla;
    }

    public LazadaOrder rtaSla(ZonedDateTime rtaSla) {
        this.setRtaSla(rtaSla);
        return this;
    }

    public ZonedDateTime getTtsSla() {
        return this.ttsSla;
    }

    public void setTtsSla(ZonedDateTime ttsSla) {
        this.ttsSla = ttsSla;
    }

    public LazadaOrder ttsSla(ZonedDateTime ttsSla) {
        this.setTtsSla(ttsSla);
        return this;
    }

    public String getOrderNumber() {
        return this.orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LazadaOrder orderNumber(String orderNumber) {
        this.setOrderNumber(orderNumber);
        return this;
    }

    public Boolean getInvoiceRequired() {
        return this.invoiceRequired;
    }

    public void setInvoiceRequired(Boolean invoiceRequired) {
        this.invoiceRequired = invoiceRequired;
    }

    public LazadaOrder invoiceRequired(Boolean invoiceRequired) {
        this.setInvoiceRequired(invoiceRequired);
        return this;
    }

    public String getInvoiceNumber() {
        return this.invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LazadaOrder invoiceNumber(String invoiceNumber) {
        this.setInvoiceNumber(invoiceNumber);
        return this;
    }

    public ZonedDateTime getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setDeliveryDate(ZonedDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public LazadaOrder deliveryDate(ZonedDateTime deliveryDate) {
        this.setDeliveryDate(deliveryDate);
        return this;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LazadaOrder customerName(String customerName) {
        this.setCustomerName(customerName);
        return this;
    }

    public String getCustomerEmail() {
        return this.customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public LazadaOrder customerEmail(String customerEmail) {
        this.setCustomerEmail(customerEmail);
        return this;
    }

    public String getNationalRegistrationNumber() {
        return this.nationalRegistrationNumber;
    }

    public void setNationalRegistrationNumber(String nationalRegistrationNumber) {
        this.nationalRegistrationNumber = nationalRegistrationNumber;
    }

    public LazadaOrder nationalRegistrationNumber(String nationalRegistrationNumber) {
        this.setNationalRegistrationNumber(nationalRegistrationNumber);
        return this;
    }

    public String getShippingName() {
        return this.shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public LazadaOrder shippingName(String shippingName) {
        this.setShippingName(shippingName);
        return this;
    }

    public String getShippingAddress() {
        return this.shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public LazadaOrder shippingAddress(String shippingAddress) {
        this.setShippingAddress(shippingAddress);
        return this;
    }

    public String getShippingAddress2() {
        return this.shippingAddress2;
    }

    public void setShippingAddress2(String shippingAddress2) {
        this.shippingAddress2 = shippingAddress2;
    }

    public LazadaOrder shippingAddress2(String shippingAddress2) {
        this.setShippingAddress2(shippingAddress2);
        return this;
    }

    public String getShippingAddress3() {
        return this.shippingAddress3;
    }

    public void setShippingAddress3(String shippingAddress3) {
        this.shippingAddress3 = shippingAddress3;
    }

    public LazadaOrder shippingAddress3(String shippingAddress3) {
        this.setShippingAddress3(shippingAddress3);
        return this;
    }

    public String getShippingAddress4() {
        return this.shippingAddress4;
    }

    public void setShippingAddress4(String shippingAddress4) {
        this.shippingAddress4 = shippingAddress4;
    }

    public LazadaOrder shippingAddress4(String shippingAddress4) {
        this.setShippingAddress4(shippingAddress4);
        return this;
    }

    public String getShippingAddress5() {
        return this.shippingAddress5;
    }

    public void setShippingAddress5(String shippingAddress5) {
        this.shippingAddress5 = shippingAddress5;
    }

    public LazadaOrder shippingAddress5(String shippingAddress5) {
        this.setShippingAddress5(shippingAddress5);
        return this;
    }

    public String getShippingPhone() {
        return this.shippingPhone;
    }

    public void setShippingPhone(String shippingPhone) {
        this.shippingPhone = shippingPhone;
    }

    public LazadaOrder shippingPhone(String shippingPhone) {
        this.setShippingPhone(shippingPhone);
        return this;
    }

    public String getShippingPhone2() {
        return this.shippingPhone2;
    }

    public void setShippingPhone2(String shippingPhone2) {
        this.shippingPhone2 = shippingPhone2;
    }

    public LazadaOrder shippingPhone2(String shippingPhone2) {
        this.setShippingPhone2(shippingPhone2);
        return this;
    }

    public String getShippingCity() {
        return this.shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public LazadaOrder shippingCity(String shippingCity) {
        this.setShippingCity(shippingCity);
        return this;
    }

    public String getShippingPostCode() {
        return this.shippingPostCode;
    }

    public void setShippingPostCode(String shippingPostCode) {
        this.shippingPostCode = shippingPostCode;
    }

    public LazadaOrder shippingPostCode(String shippingPostCode) {
        this.setShippingPostCode(shippingPostCode);
        return this;
    }

    public String getShippingCountry() {
        return this.shippingCountry;
    }

    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    public LazadaOrder shippingCountry(String shippingCountry) {
        this.setShippingCountry(shippingCountry);
        return this;
    }

    public String getShippingRegion() {
        return this.shippingRegion;
    }

    public void setShippingRegion(String shippingRegion) {
        this.shippingRegion = shippingRegion;
    }

    public LazadaOrder shippingRegion(String shippingRegion) {
        this.setShippingRegion(shippingRegion);
        return this;
    }

    public String getBillingName() {
        return this.billingName;
    }

    public void setBillingName(String billingName) {
        this.billingName = billingName;
    }

    public LazadaOrder billingName(String billingName) {
        this.setBillingName(billingName);
        return this;
    }

    public String getBillingAddr() {
        return this.billingAddr;
    }

    public void setBillingAddr(String billingAddr) {
        this.billingAddr = billingAddr;
    }

    public LazadaOrder billingAddr(String billingAddr) {
        this.setBillingAddr(billingAddr);
        return this;
    }

    public String getBillingAddr2() {
        return this.billingAddr2;
    }

    public void setBillingAddr2(String billingAddr2) {
        this.billingAddr2 = billingAddr2;
    }

    public LazadaOrder billingAddr2(String billingAddr2) {
        this.setBillingAddr2(billingAddr2);
        return this;
    }

    public String getBillingAddr3() {
        return this.billingAddr3;
    }

    public void setBillingAddr3(String billingAddr3) {
        this.billingAddr3 = billingAddr3;
    }

    public LazadaOrder billingAddr3(String billingAddr3) {
        this.setBillingAddr3(billingAddr3);
        return this;
    }

    public String getBillingAddr4() {
        return this.billingAddr4;
    }

    public void setBillingAddr4(String billingAddr4) {
        this.billingAddr4 = billingAddr4;
    }

    public LazadaOrder billingAddr4(String billingAddr4) {
        this.setBillingAddr4(billingAddr4);
        return this;
    }

    public String getBillingAddr5() {
        return this.billingAddr5;
    }

    public void setBillingAddr5(String billingAddr5) {
        this.billingAddr5 = billingAddr5;
    }

    public LazadaOrder billingAddr5(String billingAddr5) {
        this.setBillingAddr5(billingAddr5);
        return this;
    }

    public String getBillingPhone() {
        return this.billingPhone;
    }

    public void setBillingPhone(String billingPhone) {
        this.billingPhone = billingPhone;
    }

    public LazadaOrder billingPhone(String billingPhone) {
        this.setBillingPhone(billingPhone);
        return this;
    }

    public String getBillingPhone2() {
        return this.billingPhone2;
    }

    public void setBillingPhone2(String billingPhone2) {
        this.billingPhone2 = billingPhone2;
    }

    public LazadaOrder billingPhone2(String billingPhone2) {
        this.setBillingPhone2(billingPhone2);
        return this;
    }

    public String getBillingCity() {
        return this.billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public LazadaOrder billingCity(String billingCity) {
        this.setBillingCity(billingCity);
        return this;
    }

    public String getBillingPostCode() {
        return this.billingPostCode;
    }

    public void setBillingPostCode(String billingPostCode) {
        this.billingPostCode = billingPostCode;
    }

    public LazadaOrder billingPostCode(String billingPostCode) {
        this.setBillingPostCode(billingPostCode);
        return this;
    }

    public String getBillingCountry() {
        return this.billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public LazadaOrder billingCountry(String billingCountry) {
        this.setBillingCountry(billingCountry);
        return this;
    }

    public String getTaxCode() {
        return this.taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public LazadaOrder taxCode(String taxCode) {
        this.setTaxCode(taxCode);
        return this;
    }

    public String getBranchNumber() {
        return this.branchNumber;
    }

    public void setBranchNumber(String branchNumber) {
        this.branchNumber = branchNumber;
    }

    public LazadaOrder branchNumber(String branchNumber) {
        this.setBranchNumber(branchNumber);
        return this;
    }

    public String getTaxInvoiceRequested() {
        return this.taxInvoiceRequested;
    }

    public void setTaxInvoiceRequested(String taxInvoiceRequested) {
        this.taxInvoiceRequested = taxInvoiceRequested;
    }

    public LazadaOrder taxInvoiceRequested(String taxInvoiceRequested) {
        this.setTaxInvoiceRequested(taxInvoiceRequested);
        return this;
    }

    public String getPayMethod() {
        return this.payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public LazadaOrder payMethod(String payMethod) {
        this.setPayMethod(payMethod);
        return this;
    }

    public Double getPaidPrice() {
        return this.paidPrice;
    }

    public void setPaidPrice(Double paidPrice) {
        this.paidPrice = paidPrice;
    }

    public LazadaOrder paidPrice(Double paidPrice) {
        this.setPaidPrice(paidPrice);
        return this;
    }

    public Double getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public LazadaOrder unitPrice(Double unitPrice) {
        this.setUnitPrice(unitPrice);
        return this;
    }

    public Double getSellerDiscountTotal() {
        return this.sellerDiscountTotal;
    }

    public void setSellerDiscountTotal(Double sellerDiscountTotal) {
        this.sellerDiscountTotal = sellerDiscountTotal;
    }

    public LazadaOrder sellerDiscountTotal(Double sellerDiscountTotal) {
        this.setSellerDiscountTotal(sellerDiscountTotal);
        return this;
    }

    public Double getShippingFee() {
        return this.shippingFee;
    }

    public void setShippingFee(Double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public LazadaOrder shippingFee(Double shippingFee) {
        this.setShippingFee(shippingFee);
        return this;
    }

    public Double getWalletCredit() {
        return this.walletCredit;
    }

    public void setWalletCredit(Double walletCredit) {
        this.walletCredit = walletCredit;
    }

    public LazadaOrder walletCredit(Double walletCredit) {
        this.setWalletCredit(walletCredit);
        return this;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public LazadaOrder itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public String getVariation() {
        return this.variation;
    }

    public void setVariation(String variation) {
        this.variation = variation;
    }

    public LazadaOrder variation(String variation) {
        this.setVariation(variation);
        return this;
    }

    public String getCdShippingProvider() {
        return this.cdShippingProvider;
    }

    public void setCdShippingProvider(String cdShippingProvider) {
        this.cdShippingProvider = cdShippingProvider;
    }

    public LazadaOrder cdShippingProvider(String cdShippingProvider) {
        this.setCdShippingProvider(cdShippingProvider);
        return this;
    }

    public String getShippingProvider() {
        return this.shippingProvider;
    }

    public void setShippingProvider(String shippingProvider) {
        this.shippingProvider = shippingProvider;
    }

    public LazadaOrder shippingProvider(String shippingProvider) {
        this.setShippingProvider(shippingProvider);
        return this;
    }

    public String getShipmentTypeName() {
        return this.shipmentTypeName;
    }

    public void setShipmentTypeName(String shipmentTypeName) {
        this.shipmentTypeName = shipmentTypeName;
    }

    public LazadaOrder shipmentTypeName(String shipmentTypeName) {
        this.setShipmentTypeName(shipmentTypeName);
        return this;
    }

    public String getShippingProviderType() {
        return this.shippingProviderType;
    }

    public void setShippingProviderType(String shippingProviderType) {
        this.shippingProviderType = shippingProviderType;
    }

    public LazadaOrder shippingProviderType(String shippingProviderType) {
        this.setShippingProviderType(shippingProviderType);
        return this;
    }

    public String getCdTrackingCode() {
        return this.cdTrackingCode;
    }

    public void setCdTrackingCode(String cdTrackingCode) {
        this.cdTrackingCode = cdTrackingCode;
    }

    public LazadaOrder cdTrackingCode(String cdTrackingCode) {
        this.setCdTrackingCode(cdTrackingCode);
        return this;
    }

    public String getTrackingCode() {
        return this.trackingCode;
    }

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }

    public LazadaOrder trackingCode(String trackingCode) {
        this.setTrackingCode(trackingCode);
        return this;
    }

    public String getTrackingUrl() {
        return this.trackingUrl;
    }

    public void setTrackingUrl(String trackingUrl) {
        this.trackingUrl = trackingUrl;
    }

    public LazadaOrder trackingUrl(String trackingUrl) {
        this.setTrackingUrl(trackingUrl);
        return this;
    }

    public String getShippingProviderFM() {
        return this.shippingProviderFM;
    }

    public void setShippingProviderFM(String shippingProviderFM) {
        this.shippingProviderFM = shippingProviderFM;
    }

    public LazadaOrder shippingProviderFM(String shippingProviderFM) {
        this.setShippingProviderFM(shippingProviderFM);
        return this;
    }

    public String getTrackingCodeFM() {
        return this.trackingCodeFM;
    }

    public void setTrackingCodeFM(String trackingCodeFM) {
        this.trackingCodeFM = trackingCodeFM;
    }

    public LazadaOrder trackingCodeFM(String trackingCodeFM) {
        this.setTrackingCodeFM(trackingCodeFM);
        return this;
    }

    public String getTrackingUrlFM() {
        return this.trackingUrlFM;
    }

    public void setTrackingUrlFM(String trackingUrlFM) {
        this.trackingUrlFM = trackingUrlFM;
    }

    public LazadaOrder trackingUrlFM(String trackingUrlFM) {
        this.setTrackingUrlFM(trackingUrlFM);
        return this;
    }

    public ZonedDateTime getPromisedShippingTime() {
        return this.promisedShippingTime;
    }

    public void setPromisedShippingTime(ZonedDateTime promisedShippingTime) {
        this.promisedShippingTime = promisedShippingTime;
    }

    public LazadaOrder promisedShippingTime(ZonedDateTime promisedShippingTime) {
        this.setPromisedShippingTime(promisedShippingTime);
        return this;
    }

    public String getPremium() {
        return this.premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public LazadaOrder premium(String premium) {
        this.setPremium(premium);
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LazadaOrder status(String status) {
        this.setStatus(status);
        return this;
    }

    public String getBuyerFailedDeliveryReturnInitiator() {
        return this.buyerFailedDeliveryReturnInitiator;
    }

    public void setBuyerFailedDeliveryReturnInitiator(String buyerFailedDeliveryReturnInitiator) {
        this.buyerFailedDeliveryReturnInitiator = buyerFailedDeliveryReturnInitiator;
    }

    public LazadaOrder buyerFailedDeliveryReturnInitiator(String buyerFailedDeliveryReturnInitiator) {
        this.setBuyerFailedDeliveryReturnInitiator(buyerFailedDeliveryReturnInitiator);
        return this;
    }

    public String getBuyerFailedDeliveryReason() {
        return this.buyerFailedDeliveryReason;
    }

    public void setBuyerFailedDeliveryReason(String buyerFailedDeliveryReason) {
        this.buyerFailedDeliveryReason = buyerFailedDeliveryReason;
    }

    public LazadaOrder buyerFailedDeliveryReason(String buyerFailedDeliveryReason) {
        this.setBuyerFailedDeliveryReason(buyerFailedDeliveryReason);
        return this;
    }

    public String getBuyerFailedDeliveryDetail() {
        return this.buyerFailedDeliveryDetail;
    }

    public void setBuyerFailedDeliveryDetail(String buyerFailedDeliveryDetail) {
        this.buyerFailedDeliveryDetail = buyerFailedDeliveryDetail;
    }

    public LazadaOrder buyerFailedDeliveryDetail(String buyerFailedDeliveryDetail) {
        this.setBuyerFailedDeliveryDetail(buyerFailedDeliveryDetail);
        return this;
    }

    public String getBuyerFailedDeliveryUserName() {
        return this.buyerFailedDeliveryUserName;
    }

    public void setBuyerFailedDeliveryUserName(String buyerFailedDeliveryUserName) {
        this.buyerFailedDeliveryUserName = buyerFailedDeliveryUserName;
    }

    public LazadaOrder buyerFailedDeliveryUserName(String buyerFailedDeliveryUserName) {
        this.setBuyerFailedDeliveryUserName(buyerFailedDeliveryUserName);
        return this;
    }

    public String getBundleId() {
        return this.bundleId;
    }

    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }

    public LazadaOrder bundleId(String bundleId) {
        this.setBundleId(bundleId);
        return this;
    }

    public Double getBundleDiscount() {
        return this.bundleDiscount;
    }

    public void setBundleDiscount(Double bundleDiscount) {
        this.bundleDiscount = bundleDiscount;
    }

    public LazadaOrder bundleDiscount(Double bundleDiscount) {
        this.setBundleDiscount(bundleDiscount);
        return this;
    }

    public Double getRefundAmount() {
        return this.refundAmount;
    }

    public void setRefundAmount(Double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public LazadaOrder refundAmount(Double refundAmount) {
        this.setRefundAmount(refundAmount);
        return this;
    }

    public Set<LazadaOrderPayments> getPayments() {
        return this.payments;
    }

    public void setPayments(Set<LazadaOrderPayments> lazadaOrderPayments) {
        if (this.payments != null) {
            this.payments.forEach(i -> i.setLazadaOrder(null));
        }
        if (lazadaOrderPayments != null) {
            lazadaOrderPayments.forEach(i -> i.setLazadaOrder(this));
        }
        this.payments = lazadaOrderPayments;
    }

    public LazadaOrder payments(Set<LazadaOrderPayments> lazadaOrderPayments) {
        this.setPayments(lazadaOrderPayments);
        return this;
    }

    public LazadaOrder addPayments(LazadaOrderPayments lazadaOrderPayments) {
        this.payments.add(lazadaOrderPayments);
        lazadaOrderPayments.setLazadaOrder(this);
        return this;
    }

    public LazadaOrder removePayments(LazadaOrderPayments lazadaOrderPayments) {
        this.payments.remove(lazadaOrderPayments);
        lazadaOrderPayments.setLazadaOrder(null);
        return this;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LazadaOrder client(Client client) {
        this.setClient(client);
        return this;
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
            ", billingAddr2='" + getBillingAddr2() + "'" +
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
            ", paidPrice=" + getPaidPrice() +
            ", unitPrice=" + getUnitPrice() +
            ", sellerDiscountTotal=" + getSellerDiscountTotal() +
            ", shippingFee=" + getShippingFee() +
            ", walletCredit=" + getWalletCredit() +
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
            ", bundleDiscount=" + getBundleDiscount() +
            ", refundAmount=" + getRefundAmount() +
            "}";
    }
}
