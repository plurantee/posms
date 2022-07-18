<template>
  <div>
    <h2 id="page-heading" data-cy="LazadaOrderHeading">
      <span id="lazada-order-heading">Lazada Orders</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh List</span>
        </button>
        <router-link :to="{ name: 'LazadaOrderCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-lazada-order"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span> Create a new Lazada Order </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && lazadaOrders && lazadaOrders.length === 0">
      <span>No lazadaOrders found</span>
    </div>
    <div class="table-responsive" v-if="lazadaOrders && lazadaOrders.length > 0">
      <table class="table table-striped" aria-describedby="lazadaOrders">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('orderItemId')">
              <span>Order Item Id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'orderItemId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('orderType')">
              <span>Order Type</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'orderType'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('guarantee')">
              <span>Guarantee</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'guarantee'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('deliveryType')">
              <span>Delivery Type</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'deliveryType'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lazadaId')">
              <span>Lazada Id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lazadaId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('sellerSku')">
              <span>Seller Sku</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'sellerSku'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lazadaSku')">
              <span>Lazada Sku</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lazadaSku'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('wareHouse')">
              <span>Ware House</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'wareHouse'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createTime')">
              <span>Create Time</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createTime'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('updateTime')">
              <span>Update Time</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'updateTime'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('rtaSla')">
              <span>Rta Sla</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'rtaSla'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('ttsSla')">
              <span>Tts Sla</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'ttsSla'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('orderNumber')">
              <span>Order Number</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'orderNumber'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('invoiceRequired')">
              <span>Invoice Required</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'invoiceRequired'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('invoiceNumber')">
              <span>Invoice Number</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'invoiceNumber'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('deliveryDate')">
              <span>Delivery Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'deliveryDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('customerName')">
              <span>Customer Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'customerName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('customerEmail')">
              <span>Customer Email</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'customerEmail'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nationalRegistrationNumber')">
              <span>National Registration Number</span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'nationalRegistrationNumber'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shippingName')">
              <span>Shipping Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shippingName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shippingAddress')">
              <span>Shipping Address</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shippingAddress'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shippingAddress2')">
              <span>Shipping Address 2</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shippingAddress2'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shippingAddress3')">
              <span>Shipping Address 3</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shippingAddress3'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shippingAddress4')">
              <span>Shipping Address 4</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shippingAddress4'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shippingAddress5')">
              <span>Shipping Address 5</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shippingAddress5'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shippingPhone')">
              <span>Shipping Phone</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shippingPhone'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shippingPhone2')">
              <span>Shipping Phone 2</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shippingPhone2'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shippingCity')">
              <span>Shipping City</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shippingCity'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shippingPostCode')">
              <span>Shipping Post Code</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shippingPostCode'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shippingCountry')">
              <span>Shipping Country</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shippingCountry'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shippingRegion')">
              <span>Shipping Region</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shippingRegion'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('billingName')">
              <span>Billing Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'billingName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('billingAddr')">
              <span>Billing Addr</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'billingAddr'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('billingAddr2')">
              <span>Billing Addr 2</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'billingAddr2'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('billingAddr3')">
              <span>Billing Addr 3</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'billingAddr3'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('billingAddr4')">
              <span>Billing Addr 4</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'billingAddr4'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('billingAddr5')">
              <span>Billing Addr 5</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'billingAddr5'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('billingPhone')">
              <span>Billing Phone</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'billingPhone'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('billingPhone2')">
              <span>Billing Phone 2</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'billingPhone2'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('billingCity')">
              <span>Billing City</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'billingCity'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('billingPostCode')">
              <span>Billing Post Code</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'billingPostCode'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('billingCountry')">
              <span>Billing Country</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'billingCountry'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('taxCode')">
              <span>Tax Code</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'taxCode'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('branchNumber')">
              <span>Branch Number</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'branchNumber'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('taxInvoiceRequested')">
              <span>Tax Invoice Requested</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'taxInvoiceRequested'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('payMethod')">
              <span>Pay Method</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'payMethod'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('paidPrice')">
              <span>Paid Price</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'paidPrice'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('unitPrice')">
              <span>Unit Price</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'unitPrice'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('sellerDiscountTotal')">
              <span>Seller Discount Total</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'sellerDiscountTotal'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shippingFee')">
              <span>Shipping Fee</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shippingFee'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('walletCredit')">
              <span>Wallet Credit</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'walletCredit'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('itemName')">
              <span>Item Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'itemName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('variation')">
              <span>Variation</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'variation'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('cdShippingProvider')">
              <span>Cd Shipping Provider</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'cdShippingProvider'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shippingProvider')">
              <span>Shipping Provider</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shippingProvider'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shipmentTypeName')">
              <span>Shipment Type Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shipmentTypeName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shippingProviderType')">
              <span>Shipping Provider Type</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shippingProviderType'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('cdTrackingCode')">
              <span>Cd Tracking Code</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'cdTrackingCode'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('trackingCode')">
              <span>Tracking Code</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'trackingCode'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('trackingUrl')">
              <span>Tracking Url</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'trackingUrl'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shippingProviderFM')">
              <span>Shipping Provider FM</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shippingProviderFM'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('trackingCodeFM')">
              <span>Tracking Code FM</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'trackingCodeFM'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('trackingUrlFM')">
              <span>Tracking Url FM</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'trackingUrlFM'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('promisedShippingTime')">
              <span>Promised Shipping Time</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'promisedShippingTime'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('premium')">
              <span>Premium</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'premium'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('status')">
              <span>Status</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'status'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('buyerFailedDeliveryReturnInitiator')">
              <span>Buyer Failed Delivery Return Initiator</span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'buyerFailedDeliveryReturnInitiator'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('buyerFailedDeliveryReason')">
              <span>Buyer Failed Delivery Reason</span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'buyerFailedDeliveryReason'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('buyerFailedDeliveryDetail')">
              <span>Buyer Failed Delivery Detail</span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'buyerFailedDeliveryDetail'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('buyerFailedDeliveryUserName')">
              <span>Buyer Failed Delivery User Name</span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'buyerFailedDeliveryUserName'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('bundleId')">
              <span>Bundle Id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'bundleId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('bundleDiscount')">
              <span>Bundle Discount</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'bundleDiscount'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('refundAmount')">
              <span>Refund Amount</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'refundAmount'"></jhi-sort-indicator>
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
          <tr v-for="lazadaOrder in lazadaOrders" :key="lazadaOrder.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'LazadaOrderView', params: { lazadaOrderId: lazadaOrder.id } }">{{ lazadaOrder.id }}</router-link>
            </td>
            <td>{{ lazadaOrder.orderItemId }}</td>
            <td>{{ lazadaOrder.orderType }}</td>
            <td>{{ lazadaOrder.guarantee }}</td>
            <td>{{ lazadaOrder.deliveryType }}</td>
            <td>{{ lazadaOrder.lazadaId }}</td>
            <td>{{ lazadaOrder.sellerSku }}</td>
            <td>{{ lazadaOrder.lazadaSku }}</td>
            <td>{{ lazadaOrder.wareHouse }}</td>
            <td>{{ lazadaOrder.createTime | formatDate }}</td>
            <td>{{ lazadaOrder.updateTime | formatDate }}</td>
            <td>{{ lazadaOrder.rtaSla | formatDate }}</td>
            <td>{{ lazadaOrder.ttsSla | formatDate }}</td>
            <td>{{ lazadaOrder.orderNumber }}</td>
            <td>{{ lazadaOrder.invoiceRequired }}</td>
            <td>{{ lazadaOrder.invoiceNumber }}</td>
            <td>{{ lazadaOrder.deliveryDate | formatDate }}</td>
            <td>{{ lazadaOrder.customerName }}</td>
            <td>{{ lazadaOrder.customerEmail }}</td>
            <td>{{ lazadaOrder.nationalRegistrationNumber }}</td>
            <td>{{ lazadaOrder.shippingName }}</td>
            <td>{{ lazadaOrder.shippingAddress }}</td>
            <td>{{ lazadaOrder.shippingAddress2 }}</td>
            <td>{{ lazadaOrder.shippingAddress3 }}</td>
            <td>{{ lazadaOrder.shippingAddress4 }}</td>
            <td>{{ lazadaOrder.shippingAddress5 }}</td>
            <td>{{ lazadaOrder.shippingPhone }}</td>
            <td>{{ lazadaOrder.shippingPhone2 }}</td>
            <td>{{ lazadaOrder.shippingCity }}</td>
            <td>{{ lazadaOrder.shippingPostCode }}</td>
            <td>{{ lazadaOrder.shippingCountry }}</td>
            <td>{{ lazadaOrder.shippingRegion }}</td>
            <td>{{ lazadaOrder.billingName }}</td>
            <td>{{ lazadaOrder.billingAddr }}</td>
            <td>{{ lazadaOrder.billingAddr2 }}</td>
            <td>{{ lazadaOrder.billingAddr3 }}</td>
            <td>{{ lazadaOrder.billingAddr4 }}</td>
            <td>{{ lazadaOrder.billingAddr5 }}</td>
            <td>{{ lazadaOrder.billingPhone }}</td>
            <td>{{ lazadaOrder.billingPhone2 }}</td>
            <td>{{ lazadaOrder.billingCity }}</td>
            <td>{{ lazadaOrder.billingPostCode }}</td>
            <td>{{ lazadaOrder.billingCountry }}</td>
            <td>{{ lazadaOrder.taxCode }}</td>
            <td>{{ lazadaOrder.branchNumber }}</td>
            <td>{{ lazadaOrder.taxInvoiceRequested }}</td>
            <td>{{ lazadaOrder.payMethod }}</td>
            <td>{{ lazadaOrder.paidPrice }}</td>
            <td>{{ lazadaOrder.unitPrice }}</td>
            <td>{{ lazadaOrder.sellerDiscountTotal }}</td>
            <td>{{ lazadaOrder.shippingFee }}</td>
            <td>{{ lazadaOrder.walletCredit }}</td>
            <td>{{ lazadaOrder.itemName }}</td>
            <td>{{ lazadaOrder.variation }}</td>
            <td>{{ lazadaOrder.cdShippingProvider }}</td>
            <td>{{ lazadaOrder.shippingProvider }}</td>
            <td>{{ lazadaOrder.shipmentTypeName }}</td>
            <td>{{ lazadaOrder.shippingProviderType }}</td>
            <td>{{ lazadaOrder.cdTrackingCode }}</td>
            <td>{{ lazadaOrder.trackingCode }}</td>
            <td>{{ lazadaOrder.trackingUrl }}</td>
            <td>{{ lazadaOrder.shippingProviderFM }}</td>
            <td>{{ lazadaOrder.trackingCodeFM }}</td>
            <td>{{ lazadaOrder.trackingUrlFM }}</td>
            <td>{{ lazadaOrder.promisedShippingTime | formatDate }}</td>
            <td>{{ lazadaOrder.premium }}</td>
            <td>{{ lazadaOrder.status }}</td>
            <td>{{ lazadaOrder.buyerFailedDeliveryReturnInitiator }}</td>
            <td>{{ lazadaOrder.buyerFailedDeliveryReason }}</td>
            <td>{{ lazadaOrder.buyerFailedDeliveryDetail }}</td>
            <td>{{ lazadaOrder.buyerFailedDeliveryUserName }}</td>
            <td>{{ lazadaOrder.bundleId }}</td>
            <td>{{ lazadaOrder.bundleDiscount }}</td>
            <td>{{ lazadaOrder.refundAmount }}</td>
            <td>
              <div v-if="lazadaOrder.client">
                <router-link :to="{ name: 'ClientView', params: { clientId: lazadaOrder.client.id } }">{{
                  lazadaOrder.client.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="lazadaOrder.shop">
                <router-link :to="{ name: 'ShopView', params: { shopId: lazadaOrder.shop.id } }">{{ lazadaOrder.shop.id }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'LazadaOrderView', params: { lazadaOrderId: lazadaOrder.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'LazadaOrderEdit', params: { lazadaOrderId: lazadaOrder.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(lazadaOrder)"
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
        ><span id="floPosmsApp.lazadaOrder.delete.question" data-cy="lazadaOrderDeleteDialogHeading">Confirm delete operation</span></span
      >
      <div class="modal-body">
        <p id="jhi-delete-lazadaOrder-heading">Are you sure you want to delete this Lazada Order?</p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-lazadaOrder"
          data-cy="entityConfirmDeleteButton"
          v-on:click="removeLazadaOrder()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="lazadaOrders && lazadaOrders.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./lazada-order.component.ts"></script>
