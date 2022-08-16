<template>
  <div>
    <h2 id="page-heading" data-cy="ShopeeOrderPaymentsHeading">
      <span id="shopee-order-payments-heading">Shopee Order Payments</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh List</span>
        </button>
        <router-link :to="{ name: 'ShopeeOrderPaymentsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-shopee-order-payments"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span> Create a new Shopee Order Payments </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && shopeeOrderPayments && shopeeOrderPayments.length === 0">
      <span>No shopeeOrderPayments found</span>
    </div>
    <div class="table-responsive" v-if="shopeeOrderPayments && shopeeOrderPayments.length > 0">
      <table class="table table-striped" aria-describedby="shopeeOrderPayments">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('orderId')">
              <span>Order Id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'orderId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('refundId')">
              <span>Refund Id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'refundId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('usernameBuyer')">
              <span>Username Buyer</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'usernameBuyer'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('orderCreationDate')">
              <span>Order Creation Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'orderCreationDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('buyerPaymentMethod')">
              <span>Buyer Payment Method</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'buyerPaymentMethod'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('payoutCompletedDate')">
              <span>Payout Completed Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'payoutCompletedDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('originalProductPrice')">
              <span>Original Product Price</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'originalProductPrice'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('sellerProductPromotion')">
              <span>Seller Product Promotion</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'sellerProductPromotion'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('refundAmountToBuyer')">
              <span>Refund Amount To Buyer</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'refundAmountToBuyer'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('productDiscountRebateFromShopee')">
              <span>Product Discount Rebate From Shopee</span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'productDiscountRebateFromShopee'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('sellerVoucherDiscount')">
              <span>Seller Voucher Discount</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'sellerVoucherDiscount'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('sellerAbsorbedCoinCashback')">
              <span>Seller Absorbed Coin Cashback</span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'sellerAbsorbedCoinCashback'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('buyerPaidShippingFee')">
              <span>Buyer Paid Shipping Fee</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'buyerPaidShippingFee'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shippingFeeRebateFromShopee')">
              <span>Shipping Fee Rebate From Shopee</span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'shippingFeeRebateFromShopee'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('thirdPartyLogisticsDefinedShippingFee')">
              <span>Third Party Logistics Defined Shipping Fee</span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'thirdPartyLogisticsDefinedShippingFee'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('reverseShippingFee')">
              <span>Reverse Shipping Fee</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'reverseShippingFee'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('commissionFee')">
              <span>Commission Fee</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'commissionFee'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('serviceFee')">
              <span>Service Fee</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'serviceFee'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('transactionFee')">
              <span>Transaction Fee</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'transactionFee'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalReleasedAmount')">
              <span>Total Released Amount</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'totalReleasedAmount'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('sellerVoucherCode')">
              <span>Seller Voucher Code</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'sellerVoucherCode'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lostCompensation')">
              <span>Lost Compensation</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lostCompensation'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalActualWeightPerOrder')">
              <span>Total Actual Weight Per Order</span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'totalActualWeightPerOrder'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shippingFeePromotionBySeller')">
              <span>Shipping Fee Promotion By Seller</span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'shippingFeePromotionBySeller'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shippingProvider')">
              <span>Shipping Provider</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shippingProvider'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('courierName')">
              <span>Courier Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'courierName'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="shopeeOrderPayments in shopeeOrderPayments" :key="shopeeOrderPayments.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ShopeeOrderPaymentsView', params: { shopeeOrderPaymentsId: shopeeOrderPayments.id } }">{{
                shopeeOrderPayments.id
              }}</router-link>
            </td>
            <td>{{ shopeeOrderPayments.orderId }}</td>
            <td>{{ shopeeOrderPayments.refundId }}</td>
            <td>{{ shopeeOrderPayments.usernameBuyer }}</td>
            <td>{{ shopeeOrderPayments.orderCreationDate | formatDate }}</td>
            <td>{{ shopeeOrderPayments.buyerPaymentMethod }}</td>
            <td>{{ shopeeOrderPayments.payoutCompletedDate | formatDate }}</td>
            <td>{{ shopeeOrderPayments.originalProductPrice }}</td>
            <td>{{ shopeeOrderPayments.sellerProductPromotion }}</td>
            <td>{{ shopeeOrderPayments.refundAmountToBuyer }}</td>
            <td>{{ shopeeOrderPayments.productDiscountRebateFromShopee }}</td>
            <td>{{ shopeeOrderPayments.sellerVoucherDiscount }}</td>
            <td>{{ shopeeOrderPayments.sellerAbsorbedCoinCashback }}</td>
            <td>{{ shopeeOrderPayments.buyerPaidShippingFee }}</td>
            <td>{{ shopeeOrderPayments.shippingFeeRebateFromShopee }}</td>
            <td>{{ shopeeOrderPayments.thirdPartyLogisticsDefinedShippingFee }}</td>
            <td>{{ shopeeOrderPayments.reverseShippingFee }}</td>
            <td>{{ shopeeOrderPayments.commissionFee }}</td>
            <td>{{ shopeeOrderPayments.serviceFee }}</td>
            <td>{{ shopeeOrderPayments.transactionFee }}</td>
            <td>{{ shopeeOrderPayments.totalReleasedAmount }}</td>
            <td>{{ shopeeOrderPayments.sellerVoucherCode }}</td>
            <td>{{ shopeeOrderPayments.lostCompensation }}</td>
            <td>{{ shopeeOrderPayments.totalActualWeightPerOrder }}</td>
            <td>{{ shopeeOrderPayments.shippingFeePromotionBySeller }}</td>
            <td>{{ shopeeOrderPayments.shippingProvider }}</td>
            <td>{{ shopeeOrderPayments.courierName }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ShopeeOrderPaymentsView', params: { shopeeOrderPaymentsId: shopeeOrderPayments.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'ShopeeOrderPaymentsEdit', params: { shopeeOrderPaymentsId: shopeeOrderPayments.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(shopeeOrderPayments)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="floPosmsApp.shopeeOrderPayments.delete.question" data-cy="shopeeOrderPaymentsDeleteDialogHeading"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-shopeeOrderPayments-heading">Are you sure you want to delete this Shopee Order Payments?</p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-shopeeOrderPayments"
          data-cy="entityConfirmDeleteButton"
          v-on:click="removeShopeeOrderPayments()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="shopeeOrderPayments && shopeeOrderPayments.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./shopee-order-payments.component.ts"></script>
