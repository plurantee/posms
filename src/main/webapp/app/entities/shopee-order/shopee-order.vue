<template>
  <div>
    <h2 id="page-heading" data-cy="ShopeeOrderHeading">
      <span id="shopee-order-heading">Shopee Orders</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh List</span>
        </button>
        <router-link :to="{ name: 'ShopeeOrderCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-shopee-order"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span> Create a new Shopee Order </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && shopeeOrders && shopeeOrders.length === 0">
      <span>No shopeeOrders found</span>
    </div>
    <div class="table-responsive" v-if="shopeeOrders && shopeeOrders.length > 0">
      <table class="table table-striped" aria-describedby="shopeeOrders">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('orderId')">
              <span>Order Id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'orderId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('orderStatus')">
              <span>Order Status</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'orderStatus'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('returnRefundStatus')">
              <span>Return Refund Status</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'returnRefundStatus'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('trackingNumber')">
              <span>Tracking Number</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'trackingNumber'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shippingOption')">
              <span>Shipping Option</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shippingOption'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shipmentMethod')">
              <span>Shipment Method</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shipmentMethod'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('estimatedShipOutDate')">
              <span>Estimated Ship Out Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'estimatedShipOutDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shipTime')">
              <span>Ship Time</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shipTime'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('orderCreationDate')">
              <span>Order Creation Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'orderCreationDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('orderPaidTime')">
              <span>Order Paid Time</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'orderPaidTime'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('parentSkuReferenceNo')">
              <span>Parent Sku Reference No</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'parentSkuReferenceNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('productName')">
              <span>Product Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'productName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('skuReferenceNo')">
              <span>Sku Reference No</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'skuReferenceNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('variationName')">
              <span>Variation Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'variationName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('originalPrice')">
              <span>Original Price</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'originalPrice'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('dealPrice')">
              <span>Deal Price</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'dealPrice'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('quantity')">
              <span>Quantity</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'quantity'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('productSubtotal')">
              <span>Product Subtotal</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'productSubtotal'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalDiscount')">
              <span>Total Discount</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'totalDiscount'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('priceDiscountFromSeller')">
              <span>Price Discount From Seller</span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'priceDiscountFromSeller'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shopeeRebate')">
              <span>Shopee Rebate</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shopeeRebate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('skuTotalWeight')">
              <span>Sku Total Weight</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'skuTotalWeight'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('numberOfItemsInOrder')">
              <span>Number Of Items In Order</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'numberOfItemsInOrder'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('orderTotalWeight')">
              <span>Order Total Weight</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'orderTotalWeight'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('sellerVoucher')">
              <span>Seller Voucher</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'sellerVoucher'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('sellerAbsorbedCoinCashback')">
              <span>Seller Absorbed Coin Cashback</span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'sellerAbsorbedCoinCashback'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shopeeVoucher')">
              <span>Shopee Voucher</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shopeeVoucher'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('bundleDealsIndicatorYN')">
              <span>Bundle Deals Indicator YN</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'bundleDealsIndicatorYN'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shopeeBundleDiscount')">
              <span>Shopee Bundle Discount</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shopeeBundleDiscount'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('sellerBundleDiscount')">
              <span>Seller Bundle Discount</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'sellerBundleDiscount'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shopeeCoinsOffset')">
              <span>Shopee Coins Offset</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shopeeCoinsOffset'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('creditCardDiscountTotal')">
              <span>Credit Card Discount Total</span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'creditCardDiscountTotal'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('productsPricePaidByBuyer')">
              <span>Products Price Paid By Buyer</span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'productsPricePaidByBuyer'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('buyerPaidShippingFee')">
              <span>Buyer Paid Shipping Fee</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'buyerPaidShippingFee'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shippingRebateEstimate')">
              <span>Shipping Rebate Estimate</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shippingRebateEstimate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('reverseShippingFee')">
              <span>Reverse Shipping Fee</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'reverseShippingFee'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('serviceFee')">
              <span>Service Fee</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'serviceFee'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('grandTotal')">
              <span>Grand Total</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'grandTotal'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('estimatedShippingFee')">
              <span>Estimated Shipping Fee</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'estimatedShippingFee'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('usernameBuyer')">
              <span>Username Buyer</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'usernameBuyer'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('receiverName')">
              <span>Receiver Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'receiverName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('phoneNumber')">
              <span>Phone Number</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'phoneNumber'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('deliveryAddress')">
              <span>Delivery Address</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'deliveryAddress'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('town')">
              <span>Town</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'town'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('district')">
              <span>District</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'district'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('province')">
              <span>Province</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'province'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('region')">
              <span>Region</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'region'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('country')">
              <span>Country</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'country'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('zipCode')">
              <span>Zip Code</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'zipCode'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('remarkFromBuyer')">
              <span>Remark From Buyer</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'remarkFromBuyer'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('orderCompleteTime')">
              <span>Order Complete Time</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'orderCompleteTime'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('note')">
              <span>Note</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'note'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('client.id')">
              <span>Client</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'client.id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shop.id')">
              <span>Shop</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shop.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="shopeeOrder in shopeeOrders" :key="shopeeOrder.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ShopeeOrderView', params: { shopeeOrderId: shopeeOrder.id } }">{{ shopeeOrder.id }}</router-link>
            </td>
            <td>{{ shopeeOrder.orderId }}</td>
            <td>{{ shopeeOrder.orderStatus }}</td>
            <td>{{ shopeeOrder.returnRefundStatus }}</td>
            <td>{{ shopeeOrder.trackingNumber }}</td>
            <td>{{ shopeeOrder.shippingOption }}</td>
            <td>{{ shopeeOrder.shipmentMethod }}</td>
            <td>{{ shopeeOrder.estimatedShipOutDate | formatDate }}</td>
            <td>{{ shopeeOrder.shipTime | formatDate }}</td>
            <td>{{ shopeeOrder.orderCreationDate | formatDate }}</td>
            <td>{{ shopeeOrder.orderPaidTime | formatDate }}</td>
            <td>{{ shopeeOrder.parentSkuReferenceNo }}</td>
            <td>{{ shopeeOrder.productName }}</td>
            <td>{{ shopeeOrder.skuReferenceNo }}</td>
            <td>{{ shopeeOrder.variationName }}</td>
            <td>{{ shopeeOrder.originalPrice }}</td>
            <td>{{ shopeeOrder.dealPrice }}</td>
            <td>{{ shopeeOrder.quantity }}</td>
            <td>{{ shopeeOrder.productSubtotal }}</td>
            <td>{{ shopeeOrder.totalDiscount }}</td>
            <td>{{ shopeeOrder.priceDiscountFromSeller }}</td>
            <td>{{ shopeeOrder.shopeeRebate }}</td>
            <td>{{ shopeeOrder.skuTotalWeight }}</td>
            <td>{{ shopeeOrder.numberOfItemsInOrder }}</td>
            <td>{{ shopeeOrder.orderTotalWeight }}</td>
            <td>{{ shopeeOrder.sellerVoucher }}</td>
            <td>{{ shopeeOrder.sellerAbsorbedCoinCashback }}</td>
            <td>{{ shopeeOrder.shopeeVoucher }}</td>
            <td>{{ shopeeOrder.bundleDealsIndicatorYN }}</td>
            <td>{{ shopeeOrder.shopeeBundleDiscount }}</td>
            <td>{{ shopeeOrder.sellerBundleDiscount }}</td>
            <td>{{ shopeeOrder.shopeeCoinsOffset }}</td>
            <td>{{ shopeeOrder.creditCardDiscountTotal }}</td>
            <td>{{ shopeeOrder.productsPricePaidByBuyer }}</td>
            <td>{{ shopeeOrder.buyerPaidShippingFee }}</td>
            <td>{{ shopeeOrder.shippingRebateEstimate }}</td>
            <td>{{ shopeeOrder.reverseShippingFee }}</td>
            <td>{{ shopeeOrder.serviceFee }}</td>
            <td>{{ shopeeOrder.grandTotal }}</td>
            <td>{{ shopeeOrder.estimatedShippingFee }}</td>
            <td>{{ shopeeOrder.usernameBuyer }}</td>
            <td>{{ shopeeOrder.receiverName }}</td>
            <td>{{ shopeeOrder.phoneNumber }}</td>
            <td>{{ shopeeOrder.deliveryAddress }}</td>
            <td>{{ shopeeOrder.town }}</td>
            <td>{{ shopeeOrder.district }}</td>
            <td>{{ shopeeOrder.province }}</td>
            <td>{{ shopeeOrder.region }}</td>
            <td>{{ shopeeOrder.country }}</td>
            <td>{{ shopeeOrder.zipCode }}</td>
            <td>{{ shopeeOrder.remarkFromBuyer }}</td>
            <td>{{ shopeeOrder.orderCompleteTime | formatDate }}</td>
            <td>{{ shopeeOrder.note }}</td>
            <td>
              <div v-if="shopeeOrder.client">
                <router-link :to="{ name: 'ClientView', params: { clientId: shopeeOrder.client.id } }">{{
                  shopeeOrder.client.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="shopeeOrder.shop">
                <router-link :to="{ name: 'ShopView', params: { shopId: shopeeOrder.shop.id } }">{{ shopeeOrder.shop.id }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ShopeeOrderView', params: { shopeeOrderId: shopeeOrder.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ShopeeOrderEdit', params: { shopeeOrderId: shopeeOrder.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(shopeeOrder)"
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
        ><span id="floPosmsApp.shopeeOrder.delete.question" data-cy="shopeeOrderDeleteDialogHeading">Confirm delete operation</span></span
      >
      <div class="modal-body">
        <p id="jhi-delete-shopeeOrder-heading">Are you sure you want to delete this Shopee Order?</p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-shopeeOrder"
          data-cy="entityConfirmDeleteButton"
          v-on:click="removeShopeeOrder()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="shopeeOrders && shopeeOrders.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./shopee-order.component.ts"></script>
