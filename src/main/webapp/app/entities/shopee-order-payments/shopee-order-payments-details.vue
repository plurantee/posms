<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="shopeeOrderPayments">
        <h2 class="jh-entity-heading" data-cy="shopeeOrderPaymentsDetailsHeading">
          <span>ShopeeOrderPayments</span> {{ shopeeOrderPayments.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Order Id</span>
          </dt>
          <dd>
            <span>{{ shopeeOrderPayments.orderId }}</span>
          </dd>
          <dt>
            <span>Refund Id</span>
          </dt>
          <dd>
            <span>{{ shopeeOrderPayments.refundId }}</span>
          </dd>
          <dt>
            <span>Username Buyer</span>
          </dt>
          <dd>
            <span>{{ shopeeOrderPayments.usernameBuyer }}</span>
          </dd>
          <dt>
            <span>Order Creation Date</span>
          </dt>
          <dd>
            <span>{{ shopeeOrderPayments.orderCreationDate | formatDate }}</span>
          </dd>
          <dt>
            <span>Buyer Payment Method</span>
          </dt>
          <dd>
            <span>{{ shopeeOrderPayments.buyerPaymentMethod }}</span>
          </dd>
          <dt>
            <span>Payout Completed Date</span>
          </dt>
          <dd>
            <span>{{ shopeeOrderPayments.payoutCompletedDate | formatDate }}</span>
          </dd>
          <dt>
            <span>Original Product Price</span>
          </dt>
          <dd>
            <span>{{ shopeeOrderPayments.originalProductPrice }}</span>
          </dd>
          <dt>
            <span>Seller Product Promotion</span>
          </dt>
          <dd>
            <span>{{ shopeeOrderPayments.sellerProductPromotion }}</span>
          </dd>
          <dt>
            <span>Refund Amount To Buyer</span>
          </dt>
          <dd>
            <span>{{ shopeeOrderPayments.refundAmountToBuyer }}</span>
          </dd>
          <dt>
            <span>Product Discount Rebate From Shopee</span>
          </dt>
          <dd>
            <span>{{ shopeeOrderPayments.productDiscountRebateFromShopee }}</span>
          </dd>
          <dt>
            <span>Seller Voucher Discount</span>
          </dt>
          <dd>
            <span>{{ shopeeOrderPayments.sellerVoucherDiscount }}</span>
          </dd>
          <dt>
            <span>Seller Absorbed Coin Cashback</span>
          </dt>
          <dd>
            <span>{{ shopeeOrderPayments.sellerAbsorbedCoinCashback }}</span>
          </dd>
          <dt>
            <span>Buyer Paid Shipping Fee</span>
          </dt>
          <dd>
            <span>{{ shopeeOrderPayments.buyerPaidShippingFee }}</span>
          </dd>
          <dt>
            <span>Shipping Fee Rebate From Shopee</span>
          </dt>
          <dd>
            <span>{{ shopeeOrderPayments.shippingFeeRebateFromShopee }}</span>
          </dd>
          <dt>
            <span>Third Party Logistics Defined Shipping Fee</span>
          </dt>
          <dd>
            <span>{{ shopeeOrderPayments.thirdPartyLogisticsDefinedShippingFee }}</span>
          </dd>
          <dt>
            <span>Reverse Shipping Fee</span>
          </dt>
          <dd>
            <span>{{ shopeeOrderPayments.reverseShippingFee }}</span>
          </dd>
          <dt>
            <span>Commission Fee</span>
          </dt>
          <dd>
            <span>{{ shopeeOrderPayments.commissionFee }}</span>
          </dd>
          <dt>
            <span>Service Fee</span>
          </dt>
          <dd>
            <span>{{ shopeeOrderPayments.serviceFee }}</span>
          </dd>
          <dt>
            <span>Transaction Fee</span>
          </dt>
          <dd>
            <span>{{ shopeeOrderPayments.transactionFee }}</span>
          </dd>
          <dt>
            <span>Total Released Amount</span>
          </dt>
          <dd>
            <span>{{ shopeeOrderPayments.totalReleasedAmount }}</span>
          </dd>
          <dt>
            <span>Seller Voucher Code</span>
          </dt>
          <dd>
            <span>{{ shopeeOrderPayments.sellerVoucherCode }}</span>
          </dd>
          <dt>
            <span>Lost Compensation</span>
          </dt>
          <dd>
            <span>{{ shopeeOrderPayments.lostCompensation }}</span>
          </dd>
          <dt>
            <span>Total Actual Weight Per Order</span>
          </dt>
          <dd>
            <span>{{ shopeeOrderPayments.totalActualWeightPerOrder }}</span>
          </dd>
          <dt>
            <span>Shipping Fee Promotion By Seller</span>
          </dt>
          <dd>
            <span>{{ shopeeOrderPayments.shippingFeePromotionBySeller }}</span>
          </dd>
          <dt>
            <span>Shipping Provider</span>
          </dt>
          <dd>
            <span>{{ shopeeOrderPayments.shippingProvider }}</span>
          </dd>
          <dt>
            <span>Courier Name</span>
          </dt>
          <dd>
            <span>{{ shopeeOrderPayments.courierName }}</span>
          </dd>
          <dt>
            <span>Shopee Order</span>
          </dt>
          <dd>
            <div v-if="shopeeOrderPayments.shopeeOrder">
              <router-link :to="{ name: 'ShopeeOrderView', params: { shopeeOrderId: shopeeOrderPayments.shopeeOrder.id } }">{{
                shopeeOrderPayments.shopeeOrder.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link
          v-if="shopeeOrderPayments.id"
          :to="{ name: 'ShopeeOrderPaymentsEdit', params: { shopeeOrderPaymentsId: shopeeOrderPayments.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./shopee-order-payments-details.component.ts"></script>
