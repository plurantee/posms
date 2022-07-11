<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="shopeeOrder">
        <h2 class="jh-entity-heading" data-cy="shopeeOrderDetailsHeading"><span>ShopeeOrder</span> {{ shopeeOrder.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Order Id</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.orderId }}</span>
          </dd>
          <dt>
            <span>Order Status</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.orderStatus }}</span>
          </dd>
          <dt>
            <span>Return Refund Status</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.returnRefundStatus }}</span>
          </dd>
          <dt>
            <span>Tracking Number</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.trackingNumber }}</span>
          </dd>
          <dt>
            <span>Shipping Option</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.shippingOption }}</span>
          </dd>
          <dt>
            <span>Shipment Method</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.shipmentMethod }}</span>
          </dd>
          <dt>
            <span>Estimated Ship Out Date</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.estimatedShipOutDate | formatDate }}</span>
          </dd>
          <dt>
            <span>Ship Time</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.shipTime | formatDate }}</span>
          </dd>
          <dt>
            <span>Order Creation Date</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.orderCreationDate | formatDate }}</span>
          </dd>
          <dt>
            <span>Order Paid Time</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.orderPaidTime | formatDate }}</span>
          </dd>
          <dt>
            <span>Parent Sku Reference No</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.parentSkuReferenceNo }}</span>
          </dd>
          <dt>
            <span>Product Name</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.productName }}</span>
          </dd>
          <dt>
            <span>Sku Reference No</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.skuReferenceNo }}</span>
          </dd>
          <dt>
            <span>Variation Name</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.variationName }}</span>
          </dd>
          <dt>
            <span>Original Price</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.originalPrice }}</span>
          </dd>
          <dt>
            <span>Deal Price</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.dealPrice }}</span>
          </dd>
          <dt>
            <span>Quantity</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.quantity }}</span>
          </dd>
          <dt>
            <span>Product Subtotal</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.productSubtotal }}</span>
          </dd>
          <dt>
            <span>Total Discount</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.totalDiscount }}</span>
          </dd>
          <dt>
            <span>Price Discount From Seller</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.priceDiscountFromSeller }}</span>
          </dd>
          <dt>
            <span>Shopee Rebate</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.shopeeRebate }}</span>
          </dd>
          <dt>
            <span>Sku Total Weight</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.skuTotalWeight }}</span>
          </dd>
          <dt>
            <span>Number Of Items In Order</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.numberOfItemsInOrder }}</span>
          </dd>
          <dt>
            <span>Order Total Weight</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.orderTotalWeight }}</span>
          </dd>
          <dt>
            <span>Seller Voucher</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.sellerVoucher }}</span>
          </dd>
          <dt>
            <span>Seller Absorbed Coin Cashback</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.sellerAbsorbedCoinCashback }}</span>
          </dd>
          <dt>
            <span>Shopee Voucher</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.shopeeVoucher }}</span>
          </dd>
          <dt>
            <span>Bundle Deals Indicator YN</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.bundleDealsIndicatorYN }}</span>
          </dd>
          <dt>
            <span>Shopee Bundle Discount</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.shopeeBundleDiscount }}</span>
          </dd>
          <dt>
            <span>Seller Bundle Discount</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.sellerBundleDiscount }}</span>
          </dd>
          <dt>
            <span>Shopee Coins Offset</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.shopeeCoinsOffset }}</span>
          </dd>
          <dt>
            <span>Credit Card Discount Total</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.creditCardDiscountTotal }}</span>
          </dd>
          <dt>
            <span>Products Price Paid By Buyer</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.productsPricePaidByBuyer }}</span>
          </dd>
          <dt>
            <span>Buyer Paid Shipping Fee</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.buyerPaidShippingFee }}</span>
          </dd>
          <dt>
            <span>Shipping Rebate Estimate</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.shippingRebateEstimate }}</span>
          </dd>
          <dt>
            <span>Reverse Shipping Fee</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.reverseShippingFee }}</span>
          </dd>
          <dt>
            <span>Service Fee</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.serviceFee }}</span>
          </dd>
          <dt>
            <span>Grand Total</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.grandTotal }}</span>
          </dd>
          <dt>
            <span>Estimated Shipping Fee</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.estimatedShippingFee }}</span>
          </dd>
          <dt>
            <span>Username Buyer</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.usernameBuyer }}</span>
          </dd>
          <dt>
            <span>Receiver Name</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.receiverName }}</span>
          </dd>
          <dt>
            <span>Phone Number</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.phoneNumber }}</span>
          </dd>
          <dt>
            <span>Delivery Address</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.deliveryAddress }}</span>
          </dd>
          <dt>
            <span>Town</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.town }}</span>
          </dd>
          <dt>
            <span>District</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.district }}</span>
          </dd>
          <dt>
            <span>Province</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.province }}</span>
          </dd>
          <dt>
            <span>Region</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.region }}</span>
          </dd>
          <dt>
            <span>Country</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.country }}</span>
          </dd>
          <dt>
            <span>Zip Code</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.zipCode }}</span>
          </dd>
          <dt>
            <span>Remark From Buyer</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.remarkFromBuyer }}</span>
          </dd>
          <dt>
            <span>Order Complete Time</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.orderCompleteTime | formatDate }}</span>
          </dd>
          <dt>
            <span>Note</span>
          </dt>
          <dd>
            <span>{{ shopeeOrder.note }}</span>
          </dd>
          <dt>
            <span>Client</span>
          </dt>
          <dd>
            <div v-if="shopeeOrder.client">
              <router-link :to="{ name: 'ClientView', params: { clientId: shopeeOrder.client.id } }">{{
                shopeeOrder.client.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span>Shop</span>
          </dt>
          <dd>
            <div v-if="shopeeOrder.shop">
              <router-link :to="{ name: 'ShopView', params: { shopId: shopeeOrder.shop.id } }">{{ shopeeOrder.shop.id }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link
          v-if="shopeeOrder.id"
          :to="{ name: 'ShopeeOrderEdit', params: { shopeeOrderId: shopeeOrder.id } }"
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

<script lang="ts" src="./shopee-order-details.component.ts"></script>
