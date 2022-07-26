<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="floPosmsApp.shopeeOrderPayments.home.createOrEditLabel" data-cy="ShopeeOrderPaymentsCreateUpdateHeading">
          Create or edit a ShopeeOrderPayments
        </h2>
        <div>
          <div class="form-group" v-if="shopeeOrderPayments.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="shopeeOrderPayments.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-payments-orderId">Order Id</label>
            <input
              type="text"
              class="form-control"
              name="orderId"
              id="shopee-order-payments-orderId"
              data-cy="orderId"
              :class="{ valid: !$v.shopeeOrderPayments.orderId.$invalid, invalid: $v.shopeeOrderPayments.orderId.$invalid }"
              v-model="$v.shopeeOrderPayments.orderId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-payments-refundId">Refund Id</label>
            <input
              type="text"
              class="form-control"
              name="refundId"
              id="shopee-order-payments-refundId"
              data-cy="refundId"
              :class="{ valid: !$v.shopeeOrderPayments.refundId.$invalid, invalid: $v.shopeeOrderPayments.refundId.$invalid }"
              v-model="$v.shopeeOrderPayments.refundId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-payments-usernameBuyer">Username Buyer</label>
            <input
              type="text"
              class="form-control"
              name="usernameBuyer"
              id="shopee-order-payments-usernameBuyer"
              data-cy="usernameBuyer"
              :class="{ valid: !$v.shopeeOrderPayments.usernameBuyer.$invalid, invalid: $v.shopeeOrderPayments.usernameBuyer.$invalid }"
              v-model="$v.shopeeOrderPayments.usernameBuyer.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-payments-orderCreationDate">Order Creation Date</label>
            <div class="d-flex">
              <input
                id="shopee-order-payments-orderCreationDate"
                data-cy="orderCreationDate"
                type="datetime-local"
                class="form-control"
                name="orderCreationDate"
                :class="{
                  valid: !$v.shopeeOrderPayments.orderCreationDate.$invalid,
                  invalid: $v.shopeeOrderPayments.orderCreationDate.$invalid,
                }"
                :value="convertDateTimeFromServer($v.shopeeOrderPayments.orderCreationDate.$model)"
                @change="updateZonedDateTimeField('orderCreationDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-payments-buyerPaymentMethod">Buyer Payment Method</label>
            <input
              type="text"
              class="form-control"
              name="buyerPaymentMethod"
              id="shopee-order-payments-buyerPaymentMethod"
              data-cy="buyerPaymentMethod"
              :class="{
                valid: !$v.shopeeOrderPayments.buyerPaymentMethod.$invalid,
                invalid: $v.shopeeOrderPayments.buyerPaymentMethod.$invalid,
              }"
              v-model="$v.shopeeOrderPayments.buyerPaymentMethod.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-payments-payoutCompletedDate">Payout Completed Date</label>
            <div class="d-flex">
              <input
                id="shopee-order-payments-payoutCompletedDate"
                data-cy="payoutCompletedDate"
                type="datetime-local"
                class="form-control"
                name="payoutCompletedDate"
                :class="{
                  valid: !$v.shopeeOrderPayments.payoutCompletedDate.$invalid,
                  invalid: $v.shopeeOrderPayments.payoutCompletedDate.$invalid,
                }"
                :value="convertDateTimeFromServer($v.shopeeOrderPayments.payoutCompletedDate.$model)"
                @change="updateZonedDateTimeField('payoutCompletedDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-payments-originalProductPrice">Original Product Price</label>
            <input
              type="number"
              class="form-control"
              name="originalProductPrice"
              id="shopee-order-payments-originalProductPrice"
              data-cy="originalProductPrice"
              :class="{
                valid: !$v.shopeeOrderPayments.originalProductPrice.$invalid,
                invalid: $v.shopeeOrderPayments.originalProductPrice.$invalid,
              }"
              v-model.number="$v.shopeeOrderPayments.originalProductPrice.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-payments-sellerProductPromotion">Seller Product Promotion</label>
            <input
              type="number"
              class="form-control"
              name="sellerProductPromotion"
              id="shopee-order-payments-sellerProductPromotion"
              data-cy="sellerProductPromotion"
              :class="{
                valid: !$v.shopeeOrderPayments.sellerProductPromotion.$invalid,
                invalid: $v.shopeeOrderPayments.sellerProductPromotion.$invalid,
              }"
              v-model.number="$v.shopeeOrderPayments.sellerProductPromotion.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-payments-refundAmountToBuyer">Refund Amount To Buyer</label>
            <input
              type="number"
              class="form-control"
              name="refundAmountToBuyer"
              id="shopee-order-payments-refundAmountToBuyer"
              data-cy="refundAmountToBuyer"
              :class="{
                valid: !$v.shopeeOrderPayments.refundAmountToBuyer.$invalid,
                invalid: $v.shopeeOrderPayments.refundAmountToBuyer.$invalid,
              }"
              v-model.number="$v.shopeeOrderPayments.refundAmountToBuyer.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-payments-productDiscountRebateFromShopee"
              >Product Discount Rebate From Shopee</label
            >
            <input
              type="number"
              class="form-control"
              name="productDiscountRebateFromShopee"
              id="shopee-order-payments-productDiscountRebateFromShopee"
              data-cy="productDiscountRebateFromShopee"
              :class="{
                valid: !$v.shopeeOrderPayments.productDiscountRebateFromShopee.$invalid,
                invalid: $v.shopeeOrderPayments.productDiscountRebateFromShopee.$invalid,
              }"
              v-model.number="$v.shopeeOrderPayments.productDiscountRebateFromShopee.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-payments-sellerVoucherDiscount">Seller Voucher Discount</label>
            <input
              type="number"
              class="form-control"
              name="sellerVoucherDiscount"
              id="shopee-order-payments-sellerVoucherDiscount"
              data-cy="sellerVoucherDiscount"
              :class="{
                valid: !$v.shopeeOrderPayments.sellerVoucherDiscount.$invalid,
                invalid: $v.shopeeOrderPayments.sellerVoucherDiscount.$invalid,
              }"
              v-model.number="$v.shopeeOrderPayments.sellerVoucherDiscount.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-payments-sellerAbsorbedCoinCashback">Seller Absorbed Coin Cashback</label>
            <input
              type="number"
              class="form-control"
              name="sellerAbsorbedCoinCashback"
              id="shopee-order-payments-sellerAbsorbedCoinCashback"
              data-cy="sellerAbsorbedCoinCashback"
              :class="{
                valid: !$v.shopeeOrderPayments.sellerAbsorbedCoinCashback.$invalid,
                invalid: $v.shopeeOrderPayments.sellerAbsorbedCoinCashback.$invalid,
              }"
              v-model.number="$v.shopeeOrderPayments.sellerAbsorbedCoinCashback.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-payments-buyerPaidShippingFee">Buyer Paid Shipping Fee</label>
            <input
              type="number"
              class="form-control"
              name="buyerPaidShippingFee"
              id="shopee-order-payments-buyerPaidShippingFee"
              data-cy="buyerPaidShippingFee"
              :class="{
                valid: !$v.shopeeOrderPayments.buyerPaidShippingFee.$invalid,
                invalid: $v.shopeeOrderPayments.buyerPaidShippingFee.$invalid,
              }"
              v-model.number="$v.shopeeOrderPayments.buyerPaidShippingFee.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-payments-shippingFeeRebateFromShopee"
              >Shipping Fee Rebate From Shopee</label
            >
            <input
              type="number"
              class="form-control"
              name="shippingFeeRebateFromShopee"
              id="shopee-order-payments-shippingFeeRebateFromShopee"
              data-cy="shippingFeeRebateFromShopee"
              :class="{
                valid: !$v.shopeeOrderPayments.shippingFeeRebateFromShopee.$invalid,
                invalid: $v.shopeeOrderPayments.shippingFeeRebateFromShopee.$invalid,
              }"
              v-model.number="$v.shopeeOrderPayments.shippingFeeRebateFromShopee.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-payments-thirdPartyLogisticsDefinedShippingFee"
              >Third Party Logistics Defined Shipping Fee</label
            >
            <input
              type="number"
              class="form-control"
              name="thirdPartyLogisticsDefinedShippingFee"
              id="shopee-order-payments-thirdPartyLogisticsDefinedShippingFee"
              data-cy="thirdPartyLogisticsDefinedShippingFee"
              :class="{
                valid: !$v.shopeeOrderPayments.thirdPartyLogisticsDefinedShippingFee.$invalid,
                invalid: $v.shopeeOrderPayments.thirdPartyLogisticsDefinedShippingFee.$invalid,
              }"
              v-model.number="$v.shopeeOrderPayments.thirdPartyLogisticsDefinedShippingFee.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-payments-reverseShippingFee">Reverse Shipping Fee</label>
            <input
              type="number"
              class="form-control"
              name="reverseShippingFee"
              id="shopee-order-payments-reverseShippingFee"
              data-cy="reverseShippingFee"
              :class="{
                valid: !$v.shopeeOrderPayments.reverseShippingFee.$invalid,
                invalid: $v.shopeeOrderPayments.reverseShippingFee.$invalid,
              }"
              v-model.number="$v.shopeeOrderPayments.reverseShippingFee.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-payments-commissionFee">Commission Fee</label>
            <input
              type="number"
              class="form-control"
              name="commissionFee"
              id="shopee-order-payments-commissionFee"
              data-cy="commissionFee"
              :class="{ valid: !$v.shopeeOrderPayments.commissionFee.$invalid, invalid: $v.shopeeOrderPayments.commissionFee.$invalid }"
              v-model.number="$v.shopeeOrderPayments.commissionFee.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-payments-serviceFee">Service Fee</label>
            <input
              type="number"
              class="form-control"
              name="serviceFee"
              id="shopee-order-payments-serviceFee"
              data-cy="serviceFee"
              :class="{ valid: !$v.shopeeOrderPayments.serviceFee.$invalid, invalid: $v.shopeeOrderPayments.serviceFee.$invalid }"
              v-model.number="$v.shopeeOrderPayments.serviceFee.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-payments-transactionFee">Transaction Fee</label>
            <input
              type="number"
              class="form-control"
              name="transactionFee"
              id="shopee-order-payments-transactionFee"
              data-cy="transactionFee"
              :class="{ valid: !$v.shopeeOrderPayments.transactionFee.$invalid, invalid: $v.shopeeOrderPayments.transactionFee.$invalid }"
              v-model.number="$v.shopeeOrderPayments.transactionFee.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-payments-totalReleasedAmount">Total Released Amount</label>
            <input
              type="number"
              class="form-control"
              name="totalReleasedAmount"
              id="shopee-order-payments-totalReleasedAmount"
              data-cy="totalReleasedAmount"
              :class="{
                valid: !$v.shopeeOrderPayments.totalReleasedAmount.$invalid,
                invalid: $v.shopeeOrderPayments.totalReleasedAmount.$invalid,
              }"
              v-model.number="$v.shopeeOrderPayments.totalReleasedAmount.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-payments-sellerVoucherCode">Seller Voucher Code</label>
            <input
              type="text"
              class="form-control"
              name="sellerVoucherCode"
              id="shopee-order-payments-sellerVoucherCode"
              data-cy="sellerVoucherCode"
              :class="{
                valid: !$v.shopeeOrderPayments.sellerVoucherCode.$invalid,
                invalid: $v.shopeeOrderPayments.sellerVoucherCode.$invalid,
              }"
              v-model="$v.shopeeOrderPayments.sellerVoucherCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-payments-lostCompensation">Lost Compensation</label>
            <input
              type="number"
              class="form-control"
              name="lostCompensation"
              id="shopee-order-payments-lostCompensation"
              data-cy="lostCompensation"
              :class="{
                valid: !$v.shopeeOrderPayments.lostCompensation.$invalid,
                invalid: $v.shopeeOrderPayments.lostCompensation.$invalid,
              }"
              v-model.number="$v.shopeeOrderPayments.lostCompensation.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-payments-totalActualWeightPerOrder">Total Actual Weight Per Order</label>
            <input
              type="number"
              class="form-control"
              name="totalActualWeightPerOrder"
              id="shopee-order-payments-totalActualWeightPerOrder"
              data-cy="totalActualWeightPerOrder"
              :class="{
                valid: !$v.shopeeOrderPayments.totalActualWeightPerOrder.$invalid,
                invalid: $v.shopeeOrderPayments.totalActualWeightPerOrder.$invalid,
              }"
              v-model.number="$v.shopeeOrderPayments.totalActualWeightPerOrder.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-payments-shippingFeePromotionBySeller"
              >Shipping Fee Promotion By Seller</label
            >
            <input
              type="number"
              class="form-control"
              name="shippingFeePromotionBySeller"
              id="shopee-order-payments-shippingFeePromotionBySeller"
              data-cy="shippingFeePromotionBySeller"
              :class="{
                valid: !$v.shopeeOrderPayments.shippingFeePromotionBySeller.$invalid,
                invalid: $v.shopeeOrderPayments.shippingFeePromotionBySeller.$invalid,
              }"
              v-model.number="$v.shopeeOrderPayments.shippingFeePromotionBySeller.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-payments-shippingProvider">Shipping Provider</label>
            <input
              type="text"
              class="form-control"
              name="shippingProvider"
              id="shopee-order-payments-shippingProvider"
              data-cy="shippingProvider"
              :class="{
                valid: !$v.shopeeOrderPayments.shippingProvider.$invalid,
                invalid: $v.shopeeOrderPayments.shippingProvider.$invalid,
              }"
              v-model="$v.shopeeOrderPayments.shippingProvider.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-payments-courierName">Courier Name</label>
            <input
              type="text"
              class="form-control"
              name="courierName"
              id="shopee-order-payments-courierName"
              data-cy="courierName"
              :class="{ valid: !$v.shopeeOrderPayments.courierName.$invalid, invalid: $v.shopeeOrderPayments.courierName.$invalid }"
              v-model="$v.shopeeOrderPayments.courierName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shopee-order-payments-shopeeOrder">Shopee Order</label>
            <select
              class="form-control"
              id="shopee-order-payments-shopeeOrder"
              data-cy="shopeeOrder"
              name="shopeeOrder"
              v-model="shopeeOrderPayments.shopeeOrder"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  shopeeOrderPayments.shopeeOrder && shopeeOrderOption.id === shopeeOrderPayments.shopeeOrder.id
                    ? shopeeOrderPayments.shopeeOrder
                    : shopeeOrderOption
                "
                v-for="shopeeOrderOption in shopeeOrders"
                :key="shopeeOrderOption.id"
              >
                {{ shopeeOrderOption.id }}
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
            :disabled="$v.shopeeOrderPayments.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./shopee-order-payments-update.component.ts"></script>
