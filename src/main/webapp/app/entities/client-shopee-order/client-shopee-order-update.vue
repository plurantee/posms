<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="floPosmsApp.shopeeOrder.home.createOrEditLabel" data-cy="ShopeeOrderCreateUpdateHeading">Create or edit a ShopeeOrder</h2>
        <div>
          <div class="form-group" v-if="shopeeOrder.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="shopeeOrder.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-orderId">Order Id</label>
            <input
              type="text"
              class="form-control"
              name="orderId"
              id="shopee-order-orderId"
              data-cy="orderId"
              :class="{ valid: !$v.shopeeOrder.orderId.$invalid, invalid: $v.shopeeOrder.orderId.$invalid }"
              v-model="$v.shopeeOrder.orderId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-orderStatus">Order Status</label>
            <input
              type="text"
              class="form-control"
              name="orderStatus"
              id="shopee-order-orderStatus"
              data-cy="orderStatus"
              :class="{ valid: !$v.shopeeOrder.orderStatus.$invalid, invalid: $v.shopeeOrder.orderStatus.$invalid }"
              v-model="$v.shopeeOrder.orderStatus.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-returnRefundStatus">Return Refund Status</label>
            <input
              type="text"
              class="form-control"
              name="returnRefundStatus"
              id="shopee-order-returnRefundStatus"
              data-cy="returnRefundStatus"
              :class="{ valid: !$v.shopeeOrder.returnRefundStatus.$invalid, invalid: $v.shopeeOrder.returnRefundStatus.$invalid }"
              v-model="$v.shopeeOrder.returnRefundStatus.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-trackingNumber">Tracking Number</label>
            <input
              type="text"
              class="form-control"
              name="trackingNumber"
              id="shopee-order-trackingNumber"
              data-cy="trackingNumber"
              :class="{ valid: !$v.shopeeOrder.trackingNumber.$invalid, invalid: $v.shopeeOrder.trackingNumber.$invalid }"
              v-model="$v.shopeeOrder.trackingNumber.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-shippingOption">Shipping Option</label>
            <input
              type="text"
              class="form-control"
              name="shippingOption"
              id="shopee-order-shippingOption"
              data-cy="shippingOption"
              :class="{ valid: !$v.shopeeOrder.shippingOption.$invalid, invalid: $v.shopeeOrder.shippingOption.$invalid }"
              v-model="$v.shopeeOrder.shippingOption.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-shipmentMethod">Shipment Method</label>
            <input
              type="text"
              class="form-control"
              name="shipmentMethod"
              id="shopee-order-shipmentMethod"
              data-cy="shipmentMethod"
              :class="{ valid: !$v.shopeeOrder.shipmentMethod.$invalid, invalid: $v.shopeeOrder.shipmentMethod.$invalid }"
              v-model="$v.shopeeOrder.shipmentMethod.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-estimatedShipOutDate">Estimated Ship Out Date</label>
            <div class="d-flex">
              <input
                id="shopee-order-estimatedShipOutDate"
                data-cy="estimatedShipOutDate"
                type="datetime-local"
                class="form-control"
                name="estimatedShipOutDate"
                :class="{ valid: !$v.shopeeOrder.estimatedShipOutDate.$invalid, invalid: $v.shopeeOrder.estimatedShipOutDate.$invalid }"
                :value="convertDateTimeFromServer($v.shopeeOrder.estimatedShipOutDate.$model)"
                @change="updateZonedDateTimeField('estimatedShipOutDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-shipTime">Ship Time</label>
            <div class="d-flex">
              <input
                id="shopee-order-shipTime"
                data-cy="shipTime"
                type="datetime-local"
                class="form-control"
                name="shipTime"
                :class="{ valid: !$v.shopeeOrder.shipTime.$invalid, invalid: $v.shopeeOrder.shipTime.$invalid }"
                :value="convertDateTimeFromServer($v.shopeeOrder.shipTime.$model)"
                @change="updateZonedDateTimeField('shipTime', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-orderCreationDate">Order Creation Date</label>
            <div class="d-flex">
              <input
                id="shopee-order-orderCreationDate"
                data-cy="orderCreationDate"
                type="datetime-local"
                class="form-control"
                name="orderCreationDate"
                :class="{ valid: !$v.shopeeOrder.orderCreationDate.$invalid, invalid: $v.shopeeOrder.orderCreationDate.$invalid }"
                :value="convertDateTimeFromServer($v.shopeeOrder.orderCreationDate.$model)"
                @change="updateZonedDateTimeField('orderCreationDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-orderPaidTime">Order Paid Time</label>
            <div class="d-flex">
              <input
                id="shopee-order-orderPaidTime"
                data-cy="orderPaidTime"
                type="datetime-local"
                class="form-control"
                name="orderPaidTime"
                :class="{ valid: !$v.shopeeOrder.orderPaidTime.$invalid, invalid: $v.shopeeOrder.orderPaidTime.$invalid }"
                :value="convertDateTimeFromServer($v.shopeeOrder.orderPaidTime.$model)"
                @change="updateZonedDateTimeField('orderPaidTime', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-parentSkuReferenceNo">Parent Sku Reference No</label>
            <input
              type="text"
              class="form-control"
              name="parentSkuReferenceNo"
              id="shopee-order-parentSkuReferenceNo"
              data-cy="parentSkuReferenceNo"
              :class="{ valid: !$v.shopeeOrder.parentSkuReferenceNo.$invalid, invalid: $v.shopeeOrder.parentSkuReferenceNo.$invalid }"
              v-model="$v.shopeeOrder.parentSkuReferenceNo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-productName">Product Name</label>
            <input
              type="text"
              class="form-control"
              name="productName"
              id="shopee-order-productName"
              data-cy="productName"
              :class="{ valid: !$v.shopeeOrder.productName.$invalid, invalid: $v.shopeeOrder.productName.$invalid }"
              v-model="$v.shopeeOrder.productName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-skuReferenceNo">Sku Reference No</label>
            <input
              type="text"
              class="form-control"
              name="skuReferenceNo"
              id="shopee-order-skuReferenceNo"
              data-cy="skuReferenceNo"
              :class="{ valid: !$v.shopeeOrder.skuReferenceNo.$invalid, invalid: $v.shopeeOrder.skuReferenceNo.$invalid }"
              v-model="$v.shopeeOrder.skuReferenceNo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-variationName">Variation Name</label>
            <input
              type="text"
              class="form-control"
              name="variationName"
              id="shopee-order-variationName"
              data-cy="variationName"
              :class="{ valid: !$v.shopeeOrder.variationName.$invalid, invalid: $v.shopeeOrder.variationName.$invalid }"
              v-model="$v.shopeeOrder.variationName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-originalPrice">Original Price</label>
            <input
              type="number"
              class="form-control"
              name="originalPrice"
              id="shopee-order-originalPrice"
              data-cy="originalPrice"
              :class="{ valid: !$v.shopeeOrder.originalPrice.$invalid, invalid: $v.shopeeOrder.originalPrice.$invalid }"
              v-model.number="$v.shopeeOrder.originalPrice.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-dealPrice">Deal Price</label>
            <input
              type="number"
              class="form-control"
              name="dealPrice"
              id="shopee-order-dealPrice"
              data-cy="dealPrice"
              :class="{ valid: !$v.shopeeOrder.dealPrice.$invalid, invalid: $v.shopeeOrder.dealPrice.$invalid }"
              v-model.number="$v.shopeeOrder.dealPrice.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-quantity">Quantity</label>
            <input
              type="number"
              class="form-control"
              name="quantity"
              id="shopee-order-quantity"
              data-cy="quantity"
              :class="{ valid: !$v.shopeeOrder.quantity.$invalid, invalid: $v.shopeeOrder.quantity.$invalid }"
              v-model.number="$v.shopeeOrder.quantity.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-productSubtotal">Product Subtotal</label>
            <input
              type="number"
              class="form-control"
              name="productSubtotal"
              id="shopee-order-productSubtotal"
              data-cy="productSubtotal"
              :class="{ valid: !$v.shopeeOrder.productSubtotal.$invalid, invalid: $v.shopeeOrder.productSubtotal.$invalid }"
              v-model.number="$v.shopeeOrder.productSubtotal.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-totalDiscount">Total Discount</label>
            <input
              type="number"
              class="form-control"
              name="totalDiscount"
              id="shopee-order-totalDiscount"
              data-cy="totalDiscount"
              :class="{ valid: !$v.shopeeOrder.totalDiscount.$invalid, invalid: $v.shopeeOrder.totalDiscount.$invalid }"
              v-model.number="$v.shopeeOrder.totalDiscount.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-priceDiscountFromSeller">Price Discount From Seller</label>
            <input
              type="number"
              class="form-control"
              name="priceDiscountFromSeller"
              id="shopee-order-priceDiscountFromSeller"
              data-cy="priceDiscountFromSeller"
              :class="{ valid: !$v.shopeeOrder.priceDiscountFromSeller.$invalid, invalid: $v.shopeeOrder.priceDiscountFromSeller.$invalid }"
              v-model.number="$v.shopeeOrder.priceDiscountFromSeller.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-shopeeRebate">Shopee Rebate</label>
            <input
              type="number"
              class="form-control"
              name="shopeeRebate"
              id="shopee-order-shopeeRebate"
              data-cy="shopeeRebate"
              :class="{ valid: !$v.shopeeOrder.shopeeRebate.$invalid, invalid: $v.shopeeOrder.shopeeRebate.$invalid }"
              v-model.number="$v.shopeeOrder.shopeeRebate.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-skuTotalWeight">Sku Total Weight</label>
            <input
              type="text"
              class="form-control"
              name="skuTotalWeight"
              id="shopee-order-skuTotalWeight"
              data-cy="skuTotalWeight"
              :class="{ valid: !$v.shopeeOrder.skuTotalWeight.$invalid, invalid: $v.shopeeOrder.skuTotalWeight.$invalid }"
              v-model="$v.shopeeOrder.skuTotalWeight.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-numberOfItemsInOrder">Number Of Items In Order</label>
            <input
              type="text"
              class="form-control"
              name="numberOfItemsInOrder"
              id="shopee-order-numberOfItemsInOrder"
              data-cy="numberOfItemsInOrder"
              :class="{ valid: !$v.shopeeOrder.numberOfItemsInOrder.$invalid, invalid: $v.shopeeOrder.numberOfItemsInOrder.$invalid }"
              v-model="$v.shopeeOrder.numberOfItemsInOrder.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-orderTotalWeight">Order Total Weight</label>
            <input
              type="text"
              class="form-control"
              name="orderTotalWeight"
              id="shopee-order-orderTotalWeight"
              data-cy="orderTotalWeight"
              :class="{ valid: !$v.shopeeOrder.orderTotalWeight.$invalid, invalid: $v.shopeeOrder.orderTotalWeight.$invalid }"
              v-model="$v.shopeeOrder.orderTotalWeight.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-sellerVoucher">Seller Voucher</label>
            <input
              type="number"
              class="form-control"
              name="sellerVoucher"
              id="shopee-order-sellerVoucher"
              data-cy="sellerVoucher"
              :class="{ valid: !$v.shopeeOrder.sellerVoucher.$invalid, invalid: $v.shopeeOrder.sellerVoucher.$invalid }"
              v-model.number="$v.shopeeOrder.sellerVoucher.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-sellerAbsorbedCoinCashback">Seller Absorbed Coin Cashback</label>
            <input
              type="text"
              class="form-control"
              name="sellerAbsorbedCoinCashback"
              id="shopee-order-sellerAbsorbedCoinCashback"
              data-cy="sellerAbsorbedCoinCashback"
              :class="{
                valid: !$v.shopeeOrder.sellerAbsorbedCoinCashback.$invalid,
                invalid: $v.shopeeOrder.sellerAbsorbedCoinCashback.$invalid,
              }"
              v-model="$v.shopeeOrder.sellerAbsorbedCoinCashback.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-shopeeVoucher">Shopee Voucher</label>
            <input
              type="number"
              class="form-control"
              name="shopeeVoucher"
              id="shopee-order-shopeeVoucher"
              data-cy="shopeeVoucher"
              :class="{ valid: !$v.shopeeOrder.shopeeVoucher.$invalid, invalid: $v.shopeeOrder.shopeeVoucher.$invalid }"
              v-model.number="$v.shopeeOrder.shopeeVoucher.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-bundleDealsIndicatorYN">Bundle Deals Indicator YN</label>
            <input
              type="text"
              class="form-control"
              name="bundleDealsIndicatorYN"
              id="shopee-order-bundleDealsIndicatorYN"
              data-cy="bundleDealsIndicatorYN"
              :class="{ valid: !$v.shopeeOrder.bundleDealsIndicatorYN.$invalid, invalid: $v.shopeeOrder.bundleDealsIndicatorYN.$invalid }"
              v-model="$v.shopeeOrder.bundleDealsIndicatorYN.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-shopeeBundleDiscount">Shopee Bundle Discount</label>
            <input
              type="number"
              class="form-control"
              name="shopeeBundleDiscount"
              id="shopee-order-shopeeBundleDiscount"
              data-cy="shopeeBundleDiscount"
              :class="{ valid: !$v.shopeeOrder.shopeeBundleDiscount.$invalid, invalid: $v.shopeeOrder.shopeeBundleDiscount.$invalid }"
              v-model.number="$v.shopeeOrder.shopeeBundleDiscount.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-sellerBundleDiscount">Seller Bundle Discount</label>
            <input
              type="number"
              class="form-control"
              name="sellerBundleDiscount"
              id="shopee-order-sellerBundleDiscount"
              data-cy="sellerBundleDiscount"
              :class="{ valid: !$v.shopeeOrder.sellerBundleDiscount.$invalid, invalid: $v.shopeeOrder.sellerBundleDiscount.$invalid }"
              v-model.number="$v.shopeeOrder.sellerBundleDiscount.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-shopeeCoinsOffset">Shopee Coins Offset</label>
            <input
              type="number"
              class="form-control"
              name="shopeeCoinsOffset"
              id="shopee-order-shopeeCoinsOffset"
              data-cy="shopeeCoinsOffset"
              :class="{ valid: !$v.shopeeOrder.shopeeCoinsOffset.$invalid, invalid: $v.shopeeOrder.shopeeCoinsOffset.$invalid }"
              v-model.number="$v.shopeeOrder.shopeeCoinsOffset.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-creditCardDiscountTotal">Credit Card Discount Total</label>
            <input
              type="number"
              class="form-control"
              name="creditCardDiscountTotal"
              id="shopee-order-creditCardDiscountTotal"
              data-cy="creditCardDiscountTotal"
              :class="{ valid: !$v.shopeeOrder.creditCardDiscountTotal.$invalid, invalid: $v.shopeeOrder.creditCardDiscountTotal.$invalid }"
              v-model.number="$v.shopeeOrder.creditCardDiscountTotal.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-productsPricePaidByBuyer">Products Price Paid By Buyer</label>
            <input
              type="number"
              class="form-control"
              name="productsPricePaidByBuyer"
              id="shopee-order-productsPricePaidByBuyer"
              data-cy="productsPricePaidByBuyer"
              :class="{
                valid: !$v.shopeeOrder.productsPricePaidByBuyer.$invalid,
                invalid: $v.shopeeOrder.productsPricePaidByBuyer.$invalid,
              }"
              v-model.number="$v.shopeeOrder.productsPricePaidByBuyer.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-buyerPaidShippingFeeShippingRebateEstimate"
              >Buyer Paid Shipping Fee Shipping Rebate Estimate</label
            >
            <input
              type="text"
              class="form-control"
              name="buyerPaidShippingFeeShippingRebateEstimate"
              id="shopee-order-buyerPaidShippingFeeShippingRebateEstimate"
              data-cy="buyerPaidShippingFeeShippingRebateEstimate"
              :class="{
                valid: !$v.shopeeOrder.buyerPaidShippingFeeShippingRebateEstimate.$invalid,
                invalid: $v.shopeeOrder.buyerPaidShippingFeeShippingRebateEstimate.$invalid,
              }"
              v-model="$v.shopeeOrder.buyerPaidShippingFeeShippingRebateEstimate.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-reverseShippingFee">Reverse Shipping Fee</label>
            <input
              type="number"
              class="form-control"
              name="reverseShippingFee"
              id="shopee-order-reverseShippingFee"
              data-cy="reverseShippingFee"
              :class="{ valid: !$v.shopeeOrder.reverseShippingFee.$invalid, invalid: $v.shopeeOrder.reverseShippingFee.$invalid }"
              v-model.number="$v.shopeeOrder.reverseShippingFee.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-serviceFeeGrandTotal">Service Fee Grand Total</label>
            <input
              type="number"
              class="form-control"
              name="serviceFeeGrandTotal"
              id="shopee-order-serviceFeeGrandTotal"
              data-cy="serviceFeeGrandTotal"
              :class="{ valid: !$v.shopeeOrder.serviceFeeGrandTotal.$invalid, invalid: $v.shopeeOrder.serviceFeeGrandTotal.$invalid }"
              v-model.number="$v.shopeeOrder.serviceFeeGrandTotal.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-estimatedShippingFee">Estimated Shipping Fee</label>
            <input
              type="number"
              class="form-control"
              name="estimatedShippingFee"
              id="shopee-order-estimatedShippingFee"
              data-cy="estimatedShippingFee"
              :class="{ valid: !$v.shopeeOrder.estimatedShippingFee.$invalid, invalid: $v.shopeeOrder.estimatedShippingFee.$invalid }"
              v-model.number="$v.shopeeOrder.estimatedShippingFee.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-usernameBuyer">Username Buyer</label>
            <input
              type="text"
              class="form-control"
              name="usernameBuyer"
              id="shopee-order-usernameBuyer"
              data-cy="usernameBuyer"
              :class="{ valid: !$v.shopeeOrder.usernameBuyer.$invalid, invalid: $v.shopeeOrder.usernameBuyer.$invalid }"
              v-model="$v.shopeeOrder.usernameBuyer.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-receiverName">Receiver Name</label>
            <input
              type="text"
              class="form-control"
              name="receiverName"
              id="shopee-order-receiverName"
              data-cy="receiverName"
              :class="{ valid: !$v.shopeeOrder.receiverName.$invalid, invalid: $v.shopeeOrder.receiverName.$invalid }"
              v-model="$v.shopeeOrder.receiverName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-phoneNumber">Phone Number</label>
            <input
              type="text"
              class="form-control"
              name="phoneNumber"
              id="shopee-order-phoneNumber"
              data-cy="phoneNumber"
              :class="{ valid: !$v.shopeeOrder.phoneNumber.$invalid, invalid: $v.shopeeOrder.phoneNumber.$invalid }"
              v-model="$v.shopeeOrder.phoneNumber.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-deliveryAddress">Delivery Address</label>
            <input
              type="text"
              class="form-control"
              name="deliveryAddress"
              id="shopee-order-deliveryAddress"
              data-cy="deliveryAddress"
              :class="{ valid: !$v.shopeeOrder.deliveryAddress.$invalid, invalid: $v.shopeeOrder.deliveryAddress.$invalid }"
              v-model="$v.shopeeOrder.deliveryAddress.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-town">Town</label>
            <input
              type="text"
              class="form-control"
              name="town"
              id="shopee-order-town"
              data-cy="town"
              :class="{ valid: !$v.shopeeOrder.town.$invalid, invalid: $v.shopeeOrder.town.$invalid }"
              v-model="$v.shopeeOrder.town.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-district">District</label>
            <input
              type="text"
              class="form-control"
              name="district"
              id="shopee-order-district"
              data-cy="district"
              :class="{ valid: !$v.shopeeOrder.district.$invalid, invalid: $v.shopeeOrder.district.$invalid }"
              v-model="$v.shopeeOrder.district.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-province">Province</label>
            <input
              type="text"
              class="form-control"
              name="province"
              id="shopee-order-province"
              data-cy="province"
              :class="{ valid: !$v.shopeeOrder.province.$invalid, invalid: $v.shopeeOrder.province.$invalid }"
              v-model="$v.shopeeOrder.province.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-region">Region</label>
            <input
              type="text"
              class="form-control"
              name="region"
              id="shopee-order-region"
              data-cy="region"
              :class="{ valid: !$v.shopeeOrder.region.$invalid, invalid: $v.shopeeOrder.region.$invalid }"
              v-model="$v.shopeeOrder.region.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-country">Country</label>
            <input
              type="text"
              class="form-control"
              name="country"
              id="shopee-order-country"
              data-cy="country"
              :class="{ valid: !$v.shopeeOrder.country.$invalid, invalid: $v.shopeeOrder.country.$invalid }"
              v-model="$v.shopeeOrder.country.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-zipCode">Zip Code</label>
            <input
              type="text"
              class="form-control"
              name="zipCode"
              id="shopee-order-zipCode"
              data-cy="zipCode"
              :class="{ valid: !$v.shopeeOrder.zipCode.$invalid, invalid: $v.shopeeOrder.zipCode.$invalid }"
              v-model="$v.shopeeOrder.zipCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-remarkFromBuyer">Remark From Buyer</label>
            <input
              type="text"
              class="form-control"
              name="remarkFromBuyer"
              id="shopee-order-remarkFromBuyer"
              data-cy="remarkFromBuyer"
              :class="{ valid: !$v.shopeeOrder.remarkFromBuyer.$invalid, invalid: $v.shopeeOrder.remarkFromBuyer.$invalid }"
              v-model="$v.shopeeOrder.remarkFromBuyer.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-orderCompleteTime">Order Complete Time</label>
            <div class="d-flex">
              <input
                id="shopee-order-orderCompleteTime"
                data-cy="orderCompleteTime"
                type="datetime-local"
                class="form-control"
                name="orderCompleteTime"
                :class="{ valid: !$v.shopeeOrder.orderCompleteTime.$invalid, invalid: $v.shopeeOrder.orderCompleteTime.$invalid }"
                :value="convertDateTimeFromServer($v.shopeeOrder.orderCompleteTime.$model)"
                @change="updateZonedDateTimeField('orderCompleteTime', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-note">Note</label>
            <input
              type="text"
              class="form-control"
              name="note"
              id="shopee-order-note"
              data-cy="note"
              :class="{ valid: !$v.shopeeOrder.note.$invalid, invalid: $v.shopeeOrder.note.$invalid }"
              v-model="$v.shopeeOrder.note.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-shop">Shop</label>
            <select class="form-control" id="shopee-order-shop" data-cy="shop" name="shop" v-model="shopeeOrder.shop">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="shopeeOrder.shop && shopOption.id === shopeeOrder.shop.id ? shopeeOrder.shop : shopOption"
                v-for="shopOption in shops"
                :key="shopOption.id"
              >
                {{ shopOption.id }}
              </option>
            </select>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.shopeeOrder.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./client-shopee-order-update.component.ts"></script>
