package com.flogramming.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A LazadaOrderPayments.
 */
@Entity
@Table(name = "lazada_order_payments")
public class LazadaOrderPayments implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "transaction_date")
    private ZonedDateTime transactionDate;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "fee_name")
    private String feeName;

    @Column(name = "transaction_number")
    private String transactionNumber;

    @Column(name = "details")
    private String details;

    @Column(name = "seller_sku")
    private String sellerSku;

    @Column(name = "lazada_sku")
    private String lazadaSku;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "vat_in_amount")
    private Double vatInAmount;

    @Column(name = "wht_amount")
    private Double whtAmount;

    @Column(name = "wht_included_in_amount")
    private Boolean whtIncludedInAmount;

    @Column(name = "statement")
    private String statement;

    @Column(name = "paid_status")
    private String paidStatus;

    @Column(name = "order_no")
    private String orderNo;

    @Column(name = "order_item_no")
    private String orderItemNo;

    @Column(name = "order_item_status")
    private String orderItemStatus;

    @Column(name = "shipping_provider")
    private String shippingProvider;

    @Column(name = "shipping_speed")
    private String shippingSpeed;

    @Column(name = "shipment_type")
    private String shipmentType;

    @Column(name = "reference")
    private String reference;

    @Column(name = "comment")
    private String comment;

    @Column(name = "payment_ref_id")
    private String paymentRefId;

    @ManyToOne
    @JsonIgnoreProperties(value = {"payments", "client", "shop"}, allowSetters = true)
    private LazadaOrder lazadaOrder;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LazadaOrderPayments id(Long id) {
        this.setId(id);
        return this;
    }

    public ZonedDateTime getTransactionDate() {
        return this.transactionDate;
    }

    public void setTransactionDate(ZonedDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public LazadaOrderPayments transactionDate(ZonedDateTime transactionDate) {
        this.setTransactionDate(transactionDate);
        return this;
    }

    public String getTransactionType() {
        return this.transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public LazadaOrderPayments transactionType(String transactionType) {
        this.setTransactionType(transactionType);
        return this;
    }

    public String getFeeName() {
        return this.feeName;
    }

    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    public LazadaOrderPayments feeName(String feeName) {
        this.setFeeName(feeName);
        return this;
    }

    public String getTransactionNumber() {
        return this.transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public LazadaOrderPayments transactionNumber(String transactionNumber) {
        this.setTransactionNumber(transactionNumber);
        return this;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LazadaOrderPayments details(String details) {
        this.setDetails(details);
        return this;
    }

    public String getSellerSku() {
        return this.sellerSku;
    }

    public void setSellerSku(String sellerSku) {
        this.sellerSku = sellerSku;
    }

    public LazadaOrderPayments sellerSku(String sellerSku) {
        this.setSellerSku(sellerSku);
        return this;
    }

    public String getLazadaSku() {
        return this.lazadaSku;
    }

    public void setLazadaSku(String lazadaSku) {
        this.lazadaSku = lazadaSku;
    }

    public LazadaOrderPayments lazadaSku(String lazadaSku) {
        this.setLazadaSku(lazadaSku);
        return this;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LazadaOrderPayments amount(Double amount) {
        this.setAmount(amount);
        return this;
    }

    public Double getVatInAmount() {
        return this.vatInAmount;
    }

    public void setVatInAmount(Double vatInAmount) {
        this.vatInAmount = vatInAmount;
    }

    public LazadaOrderPayments vatInAmount(Double vatInAmount) {
        this.setVatInAmount(vatInAmount);
        return this;
    }

    public Double getWhtAmount() {
        return this.whtAmount;
    }

    public void setWhtAmount(Double whtAmount) {
        this.whtAmount = whtAmount;
    }

    public LazadaOrderPayments whtAmount(Double whtAmount) {
        this.setWhtAmount(whtAmount);
        return this;
    }

    public Boolean getWhtIncludedInAmount() {
        return this.whtIncludedInAmount;
    }

    public void setWhtIncludedInAmount(Boolean whtIncludedInAmount) {
        this.whtIncludedInAmount = whtIncludedInAmount;
    }

    public LazadaOrderPayments whtIncludedInAmount(Boolean whtIncludedInAmount) {
        this.setWhtIncludedInAmount(whtIncludedInAmount);
        return this;
    }

    public String getStatement() {
        return this.statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public LazadaOrderPayments statement(String statement) {
        this.setStatement(statement);
        return this;
    }

    public String getPaidStatus() {
        return this.paidStatus;
    }

    public void setPaidStatus(String paidStatus) {
        this.paidStatus = paidStatus;
    }

    public LazadaOrderPayments paidStatus(String paidStatus) {
        this.setPaidStatus(paidStatus);
        return this;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public LazadaOrderPayments orderNo(String orderNo) {
        this.setOrderNo(orderNo);
        return this;
    }

    public String getOrderItemNo() {
        return this.orderItemNo;
    }

    public void setOrderItemNo(String orderItemNo) {
        this.orderItemNo = orderItemNo;
    }

    public LazadaOrderPayments orderItemNo(String orderItemNo) {
        this.setOrderItemNo(orderItemNo);
        return this;
    }

    public String getOrderItemStatus() {
        return this.orderItemStatus;
    }

    public void setOrderItemStatus(String orderItemStatus) {
        this.orderItemStatus = orderItemStatus;
    }

    public LazadaOrderPayments orderItemStatus(String orderItemStatus) {
        this.setOrderItemStatus(orderItemStatus);
        return this;
    }

    public String getShippingProvider() {
        return this.shippingProvider;
    }

    public void setShippingProvider(String shippingProvider) {
        this.shippingProvider = shippingProvider;
    }

    public LazadaOrderPayments shippingProvider(String shippingProvider) {
        this.setShippingProvider(shippingProvider);
        return this;
    }

    public String getShippingSpeed() {
        return this.shippingSpeed;
    }

    public void setShippingSpeed(String shippingSpeed) {
        this.shippingSpeed = shippingSpeed;
    }

    public LazadaOrderPayments shippingSpeed(String shippingSpeed) {
        this.setShippingSpeed(shippingSpeed);
        return this;
    }

    public String getShipmentType() {
        return this.shipmentType;
    }

    public void setShipmentType(String shipmentType) {
        this.shipmentType = shipmentType;
    }

    public LazadaOrderPayments shipmentType(String shipmentType) {
        this.setShipmentType(shipmentType);
        return this;
    }

    public String getReference() {
        return this.reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LazadaOrderPayments reference(String reference) {
        this.setReference(reference);
        return this;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LazadaOrderPayments comment(String comment) {
        this.setComment(comment);
        return this;
    }

    public String getPaymentRefId() {
        return this.paymentRefId;
    }

    public void setPaymentRefId(String paymentRefId) {
        this.paymentRefId = paymentRefId;
    }

    public LazadaOrderPayments paymentRefId(String paymentRefId) {
        this.setPaymentRefId(paymentRefId);
        return this;
    }

    public LazadaOrder getLazadaOrder() {
        return this.lazadaOrder;
    }

    public void setLazadaOrder(LazadaOrder lazadaOrder) {
        this.lazadaOrder = lazadaOrder;
    }

    public LazadaOrderPayments lazadaOrder(LazadaOrder lazadaOrder) {
        this.setLazadaOrder(lazadaOrder);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LazadaOrderPayments)) {
            return false;
        }
        return id != null && id.equals(((LazadaOrderPayments) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LazadaOrderPayments{" +
            "id=" + getId() +
            ", transactionDate='" + getTransactionDate() + "'" +
            ", transactionType='" + getTransactionType() + "'" +
            ", feeName='" + getFeeName() + "'" +
            ", transactionNumber='" + getTransactionNumber() + "'" +
            ", details='" + getDetails() + "'" +
            ", sellerSku='" + getSellerSku() + "'" +
            ", lazadaSku='" + getLazadaSku() + "'" +
            ", amount=" + getAmount() +
            ", vatInAmount=" + getVatInAmount() +
            ", whtAmount=" + getWhtAmount() +
            ", whtIncludedInAmount='" + getWhtIncludedInAmount() + "'" +
            ", statement='" + getStatement() + "'" +
            ", paidStatus='" + getPaidStatus() + "'" +
            ", orderNo='" + getOrderNo() + "'" +
            ", orderItemNo='" + getOrderItemNo() + "'" +
            ", orderItemStatus='" + getOrderItemStatus() + "'" +
            ", shippingProvider='" + getShippingProvider() + "'" +
            ", shippingSpeed='" + getShippingSpeed() + "'" +
            ", shipmentType='" + getShipmentType() + "'" +
            ", reference='" + getReference() + "'" +
            ", comment='" + getComment() + "'" +
            ", paymentRefId='" + getPaymentRefId() + "'" +
            "}";
    }
}
