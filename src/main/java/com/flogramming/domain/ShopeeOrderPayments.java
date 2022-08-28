package com.flogramming.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A ShopeeOrderPayments.
 */
@Entity
@Table(name = "shopee_order_payments")
public class ShopeeOrderPayments implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "refund_id")
    private String refundId;

    @Column(name = "username_buyer")
    private String usernameBuyer;

    @Column(name = "order_creation_date")
    private ZonedDateTime orderCreationDate;

    @Column(name = "buyer_payment_method")
    private String buyerPaymentMethod;

    @Column(name = "payout_completed_date")
    private ZonedDateTime payoutCompletedDate;

    @Column(name = "original_product_price")
    private Double originalProductPrice;

    @Column(name = "seller_product_promotion")
    private Double sellerProductPromotion;

    @Column(name = "refund_amount_to_buyer")
    private Double refundAmountToBuyer;

    @Column(name = "product_discount_rebate_from_shopee")
    private Double productDiscountRebateFromShopee;

    @Column(name = "seller_voucher_discount")
    private Double sellerVoucherDiscount;

    @Column(name = "seller_absorbed_coin_cashback")
    private Double sellerAbsorbedCoinCashback;

    @Column(name = "buyer_paid_shipping_fee")
    private Double buyerPaidShippingFee;

    @Column(name = "shipping_fee_rebate_from_shopee")
    private Double shippingFeeRebateFromShopee;

    @Column(name = "third_party_logistics_defined_shipping_fee")
    private Double thirdPartyLogisticsDefinedShippingFee;

    @Column(name = "reverse_shipping_fee")
    private Double reverseShippingFee;

    @Column(name = "commission_fee")
    private Double commissionFee;

    @Column(name = "service_fee")
    private Double serviceFee;

    @Column(name = "transaction_fee")
    private Double transactionFee;

    @Column(name = "total_released_amount")
    private Double totalReleasedAmount;

    @Column(name = "seller_voucher_code")
    private String sellerVoucherCode;

    @Column(name = "lost_compensation")
    private Double lostCompensation;

    @Column(name = "total_actual_weight_per_order")
    private Double totalActualWeightPerOrder;

    @Column(name = "shipping_fee_promotion_by_seller")
    private Double shippingFeePromotionBySeller;

    @Column(name = "shipping_provider")
    private String shippingProvider;

    @Column(name = "courier_name")
    private String courierName;

    @ManyToMany(mappedBy = "payments")
    @JsonIgnoreProperties(value = { "payments", "inventory", "client", "shop" }, allowSetters = true)
    private Set<ShopeeOrder> shopeeOrders = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ShopeeOrderPayments id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public ShopeeOrderPayments orderId(String orderId) {
        this.setOrderId(orderId);
        return this;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRefundId() {
        return this.refundId;
    }

    public ShopeeOrderPayments refundId(String refundId) {
        this.setRefundId(refundId);
        return this;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getUsernameBuyer() {
        return this.usernameBuyer;
    }

    public ShopeeOrderPayments usernameBuyer(String usernameBuyer) {
        this.setUsernameBuyer(usernameBuyer);
        return this;
    }

    public void setUsernameBuyer(String usernameBuyer) {
        this.usernameBuyer = usernameBuyer;
    }

    public ZonedDateTime getOrderCreationDate() {
        return this.orderCreationDate;
    }

    public ShopeeOrderPayments orderCreationDate(ZonedDateTime orderCreationDate) {
        this.setOrderCreationDate(orderCreationDate);
        return this;
    }

    public void setOrderCreationDate(ZonedDateTime orderCreationDate) {
        this.orderCreationDate = orderCreationDate;
    }

    public String getBuyerPaymentMethod() {
        return this.buyerPaymentMethod;
    }

    public ShopeeOrderPayments buyerPaymentMethod(String buyerPaymentMethod) {
        this.setBuyerPaymentMethod(buyerPaymentMethod);
        return this;
    }

    public void setBuyerPaymentMethod(String buyerPaymentMethod) {
        this.buyerPaymentMethod = buyerPaymentMethod;
    }

    public ZonedDateTime getPayoutCompletedDate() {
        return this.payoutCompletedDate;
    }

    public ShopeeOrderPayments payoutCompletedDate(ZonedDateTime payoutCompletedDate) {
        this.setPayoutCompletedDate(payoutCompletedDate);
        return this;
    }

    public void setPayoutCompletedDate(ZonedDateTime payoutCompletedDate) {
        this.payoutCompletedDate = payoutCompletedDate;
    }

    public Double getOriginalProductPrice() {
        return this.originalProductPrice;
    }

    public ShopeeOrderPayments originalProductPrice(Double originalProductPrice) {
        this.setOriginalProductPrice(originalProductPrice);
        return this;
    }

    public void setOriginalProductPrice(Double originalProductPrice) {
        this.originalProductPrice = originalProductPrice;
    }

    public Double getSellerProductPromotion() {
        return this.sellerProductPromotion;
    }

    public ShopeeOrderPayments sellerProductPromotion(Double sellerProductPromotion) {
        this.setSellerProductPromotion(sellerProductPromotion);
        return this;
    }

    public void setSellerProductPromotion(Double sellerProductPromotion) {
        this.sellerProductPromotion = sellerProductPromotion;
    }

    public Double getRefundAmountToBuyer() {
        return this.refundAmountToBuyer;
    }

    public ShopeeOrderPayments refundAmountToBuyer(Double refundAmountToBuyer) {
        this.setRefundAmountToBuyer(refundAmountToBuyer);
        return this;
    }

    public void setRefundAmountToBuyer(Double refundAmountToBuyer) {
        this.refundAmountToBuyer = refundAmountToBuyer;
    }

    public Double getProductDiscountRebateFromShopee() {
        return this.productDiscountRebateFromShopee;
    }

    public ShopeeOrderPayments productDiscountRebateFromShopee(Double productDiscountRebateFromShopee) {
        this.setProductDiscountRebateFromShopee(productDiscountRebateFromShopee);
        return this;
    }

    public void setProductDiscountRebateFromShopee(Double productDiscountRebateFromShopee) {
        this.productDiscountRebateFromShopee = productDiscountRebateFromShopee;
    }

    public Double getSellerVoucherDiscount() {
        return this.sellerVoucherDiscount;
    }

    public ShopeeOrderPayments sellerVoucherDiscount(Double sellerVoucherDiscount) {
        this.setSellerVoucherDiscount(sellerVoucherDiscount);
        return this;
    }

    public void setSellerVoucherDiscount(Double sellerVoucherDiscount) {
        this.sellerVoucherDiscount = sellerVoucherDiscount;
    }

    public Double getSellerAbsorbedCoinCashback() {
        return this.sellerAbsorbedCoinCashback;
    }

    public ShopeeOrderPayments sellerAbsorbedCoinCashback(Double sellerAbsorbedCoinCashback) {
        this.setSellerAbsorbedCoinCashback(sellerAbsorbedCoinCashback);
        return this;
    }

    public void setSellerAbsorbedCoinCashback(Double sellerAbsorbedCoinCashback) {
        this.sellerAbsorbedCoinCashback = sellerAbsorbedCoinCashback;
    }

    public Double getBuyerPaidShippingFee() {
        return this.buyerPaidShippingFee;
    }

    public ShopeeOrderPayments buyerPaidShippingFee(Double buyerPaidShippingFee) {
        this.setBuyerPaidShippingFee(buyerPaidShippingFee);
        return this;
    }

    public void setBuyerPaidShippingFee(Double buyerPaidShippingFee) {
        this.buyerPaidShippingFee = buyerPaidShippingFee;
    }

    public Double getShippingFeeRebateFromShopee() {
        return this.shippingFeeRebateFromShopee;
    }

    public ShopeeOrderPayments shippingFeeRebateFromShopee(Double shippingFeeRebateFromShopee) {
        this.setShippingFeeRebateFromShopee(shippingFeeRebateFromShopee);
        return this;
    }

    public void setShippingFeeRebateFromShopee(Double shippingFeeRebateFromShopee) {
        this.shippingFeeRebateFromShopee = shippingFeeRebateFromShopee;
    }

    public Double getThirdPartyLogisticsDefinedShippingFee() {
        return this.thirdPartyLogisticsDefinedShippingFee;
    }

    public ShopeeOrderPayments thirdPartyLogisticsDefinedShippingFee(Double thirdPartyLogisticsDefinedShippingFee) {
        this.setThirdPartyLogisticsDefinedShippingFee(thirdPartyLogisticsDefinedShippingFee);
        return this;
    }

    public void setThirdPartyLogisticsDefinedShippingFee(Double thirdPartyLogisticsDefinedShippingFee) {
        this.thirdPartyLogisticsDefinedShippingFee = thirdPartyLogisticsDefinedShippingFee;
    }

    public Double getReverseShippingFee() {
        return this.reverseShippingFee;
    }

    public ShopeeOrderPayments reverseShippingFee(Double reverseShippingFee) {
        this.setReverseShippingFee(reverseShippingFee);
        return this;
    }

    public void setReverseShippingFee(Double reverseShippingFee) {
        this.reverseShippingFee = reverseShippingFee;
    }

    public Double getCommissionFee() {
        return this.commissionFee;
    }

    public ShopeeOrderPayments commissionFee(Double commissionFee) {
        this.setCommissionFee(commissionFee);
        return this;
    }

    public void setCommissionFee(Double commissionFee) {
        this.commissionFee = commissionFee;
    }

    public Double getServiceFee() {
        return this.serviceFee;
    }

    public ShopeeOrderPayments serviceFee(Double serviceFee) {
        this.setServiceFee(serviceFee);
        return this;
    }

    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Double getTransactionFee() {
        return this.transactionFee;
    }

    public ShopeeOrderPayments transactionFee(Double transactionFee) {
        this.setTransactionFee(transactionFee);
        return this;
    }

    public void setTransactionFee(Double transactionFee) {
        this.transactionFee = transactionFee;
    }

    public Double getTotalReleasedAmount() {
        return this.totalReleasedAmount;
    }

    public ShopeeOrderPayments totalReleasedAmount(Double totalReleasedAmount) {
        this.setTotalReleasedAmount(totalReleasedAmount);
        return this;
    }

    public void setTotalReleasedAmount(Double totalReleasedAmount) {
        this.totalReleasedAmount = totalReleasedAmount;
    }

    public String getSellerVoucherCode() {
        return this.sellerVoucherCode;
    }

    public ShopeeOrderPayments sellerVoucherCode(String sellerVoucherCode) {
        this.setSellerVoucherCode(sellerVoucherCode);
        return this;
    }

    public void setSellerVoucherCode(String sellerVoucherCode) {
        this.sellerVoucherCode = sellerVoucherCode;
    }

    public Double getLostCompensation() {
        return this.lostCompensation;
    }

    public ShopeeOrderPayments lostCompensation(Double lostCompensation) {
        this.setLostCompensation(lostCompensation);
        return this;
    }

    public void setLostCompensation(Double lostCompensation) {
        this.lostCompensation = lostCompensation;
    }

    public Double getTotalActualWeightPerOrder() {
        return this.totalActualWeightPerOrder;
    }

    public ShopeeOrderPayments totalActualWeightPerOrder(Double totalActualWeightPerOrder) {
        this.setTotalActualWeightPerOrder(totalActualWeightPerOrder);
        return this;
    }

    public void setTotalActualWeightPerOrder(Double totalActualWeightPerOrder) {
        this.totalActualWeightPerOrder = totalActualWeightPerOrder;
    }

    public Double getShippingFeePromotionBySeller() {
        return this.shippingFeePromotionBySeller;
    }

    public ShopeeOrderPayments shippingFeePromotionBySeller(Double shippingFeePromotionBySeller) {
        this.setShippingFeePromotionBySeller(shippingFeePromotionBySeller);
        return this;
    }

    public void setShippingFeePromotionBySeller(Double shippingFeePromotionBySeller) {
        this.shippingFeePromotionBySeller = shippingFeePromotionBySeller;
    }

    public String getShippingProvider() {
        return this.shippingProvider;
    }

    public ShopeeOrderPayments shippingProvider(String shippingProvider) {
        this.setShippingProvider(shippingProvider);
        return this;
    }

    public void setShippingProvider(String shippingProvider) {
        this.shippingProvider = shippingProvider;
    }

    public String getCourierName() {
        return this.courierName;
    }

    public ShopeeOrderPayments courierName(String courierName) {
        this.setCourierName(courierName);
        return this;
    }

    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    public Set<ShopeeOrder> getShopeeOrders() {
        return this.shopeeOrders;
    }

    public void setShopeeOrders(Set<ShopeeOrder> shopeeOrders) {
        if (this.shopeeOrders != null) {
            this.shopeeOrders.forEach(i -> i.removePayments(this));
        }
        if (shopeeOrders != null) {
            shopeeOrders.forEach(i -> i.addPayments(this));
        }
        this.shopeeOrders = shopeeOrders;
    }

    public ShopeeOrderPayments shopeeOrders(Set<ShopeeOrder> shopeeOrders) {
        this.setShopeeOrders(shopeeOrders);
        return this;
    }

    public ShopeeOrderPayments addShopeeOrder(ShopeeOrder shopeeOrder) {
        this.shopeeOrders.add(shopeeOrder);
        shopeeOrder.getPayments().add(this);
        return this;
    }

    public ShopeeOrderPayments removeShopeeOrder(ShopeeOrder shopeeOrder) {
        this.shopeeOrders.remove(shopeeOrder);
        shopeeOrder.getPayments().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShopeeOrderPayments)) {
            return false;
        }
        return id != null && id.equals(((ShopeeOrderPayments) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ShopeeOrderPayments{" +
            "id=" + getId() +
            ", orderId='" + getOrderId() + "'" +
            ", refundId='" + getRefundId() + "'" +
            ", usernameBuyer='" + getUsernameBuyer() + "'" +
            ", orderCreationDate='" + getOrderCreationDate() + "'" +
            ", buyerPaymentMethod='" + getBuyerPaymentMethod() + "'" +
            ", payoutCompletedDate='" + getPayoutCompletedDate() + "'" +
            ", originalProductPrice=" + getOriginalProductPrice() +
            ", sellerProductPromotion=" + getSellerProductPromotion() +
            ", refundAmountToBuyer=" + getRefundAmountToBuyer() +
            ", productDiscountRebateFromShopee=" + getProductDiscountRebateFromShopee() +
            ", sellerVoucherDiscount=" + getSellerVoucherDiscount() +
            ", sellerAbsorbedCoinCashback=" + getSellerAbsorbedCoinCashback() +
            ", buyerPaidShippingFee=" + getBuyerPaidShippingFee() +
            ", shippingFeeRebateFromShopee=" + getShippingFeeRebateFromShopee() +
            ", thirdPartyLogisticsDefinedShippingFee=" + getThirdPartyLogisticsDefinedShippingFee() +
            ", reverseShippingFee=" + getReverseShippingFee() +
            ", commissionFee=" + getCommissionFee() +
            ", serviceFee=" + getServiceFee() +
            ", transactionFee=" + getTransactionFee() +
            ", totalReleasedAmount=" + getTotalReleasedAmount() +
            ", sellerVoucherCode='" + getSellerVoucherCode() + "'" +
            ", lostCompensation=" + getLostCompensation() +
            ", totalActualWeightPerOrder=" + getTotalActualWeightPerOrder() +
            ", shippingFeePromotionBySeller=" + getShippingFeePromotionBySeller() +
            ", shippingProvider='" + getShippingProvider() + "'" +
            ", courierName='" + getCourierName() + "'" +
            "}";
    }

    public void addShopeeOrderFromList(List<ShopeeOrder> shopeeOrders) {
        if (this.shopeeOrders == null || this.shopeeOrders.isEmpty()) {
            this.shopeeOrders = new HashSet<>();
        }
        shopeeOrders.forEach(shopeeOrder -> addShopeeOrder(shopeeOrder));
    }
}
