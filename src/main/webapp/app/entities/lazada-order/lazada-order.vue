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
            <th scope="row"><span>ID</span></th>
            <th scope="row"><span>Order Item Id</span></th>
            <th scope="row"><span>Order Type</span></th>
            <th scope="row"><span>Guarantee</span></th>
            <th scope="row"><span>Delivery Type</span></th>
            <th scope="row"><span>Lazada Id</span></th>
            <th scope="row"><span>Seller Sku</span></th>
            <th scope="row"><span>Lazada Sku</span></th>
            <th scope="row"><span>Ware House</span></th>
            <th scope="row"><span>Create Time</span></th>
            <th scope="row"><span>Update Time</span></th>
            <th scope="row"><span>Rta Sla</span></th>
            <th scope="row"><span>Tts Sla</span></th>
            <th scope="row"><span>Order Number</span></th>
            <th scope="row"><span>Invoice Required</span></th>
            <th scope="row"><span>Invoice Number</span></th>
            <th scope="row"><span>Delivery Date</span></th>
            <th scope="row"><span>Customer Name</span></th>
            <th scope="row"><span>Customer Email</span></th>
            <th scope="row"><span>National Registration Number</span></th>
            <th scope="row"><span>Shipping Name</span></th>
            <th scope="row"><span>Shipping Address</span></th>
            <th scope="row"><span>Shipping Address 2</span></th>
            <th scope="row"><span>Shipping Address 3</span></th>
            <th scope="row"><span>Shipping Address 4</span></th>
            <th scope="row"><span>Shipping Address 5</span></th>
            <th scope="row"><span>Shipping Phone</span></th>
            <th scope="row"><span>Shipping Phone 2</span></th>
            <th scope="row"><span>Shipping City</span></th>
            <th scope="row"><span>Shipping Post Code</span></th>
            <th scope="row"><span>Shipping Country</span></th>
            <th scope="row"><span>Shipping Region</span></th>
            <th scope="row"><span>Billing Name</span></th>
            <th scope="row"><span>Billing Addr</span></th>
            <th scope="row"><span>Billing Addr 2</span></th>
            <th scope="row"><span>Billing Addr 3</span></th>
            <th scope="row"><span>Billing Addr 4</span></th>
            <th scope="row"><span>Billing Addr 5</span></th>
            <th scope="row"><span>Billing Phone</span></th>
            <th scope="row"><span>Billing Phone 2</span></th>
            <th scope="row"><span>Billing City</span></th>
            <th scope="row"><span>Billing Post Code</span></th>
            <th scope="row"><span>Billing Country</span></th>
            <th scope="row"><span>Tax Code</span></th>
            <th scope="row"><span>Branch Number</span></th>
            <th scope="row"><span>Tax Invoice Requested</span></th>
            <th scope="row"><span>Pay Method</span></th>
            <th scope="row"><span>Paid Price</span></th>
            <th scope="row"><span>Unit Price</span></th>
            <th scope="row"><span>Seller Discount Total</span></th>
            <th scope="row"><span>Shipping Fee</span></th>
            <th scope="row"><span>Wallet Credit</span></th>
            <th scope="row"><span>Item Name</span></th>
            <th scope="row"><span>Variation</span></th>
            <th scope="row"><span>Cd Shipping Provider</span></th>
            <th scope="row"><span>Shipping Provider</span></th>
            <th scope="row"><span>Shipment Type Name</span></th>
            <th scope="row"><span>Shipping Provider Type</span></th>
            <th scope="row"><span>Cd Tracking Code</span></th>
            <th scope="row"><span>Tracking Code</span></th>
            <th scope="row"><span>Tracking Url</span></th>
            <th scope="row"><span>Shipping Provider FM</span></th>
            <th scope="row"><span>Tracking Code FM</span></th>
            <th scope="row"><span>Tracking Url FM</span></th>
            <th scope="row"><span>Promised Shipping Time</span></th>
            <th scope="row"><span>Premium</span></th>
            <th scope="row"><span>Status</span></th>
            <th scope="row"><span>Buyer Failed Delivery Return Initiator</span></th>
            <th scope="row"><span>Buyer Failed Delivery Reason</span></th>
            <th scope="row"><span>Buyer Failed Delivery Detail</span></th>
            <th scope="row"><span>Buyer Failed Delivery User Name</span></th>
            <th scope="row"><span>Bundle Id</span></th>
            <th scope="row"><span>Bundle Discount</span></th>
            <th scope="row"><span>Refund Amount</span></th>
            <th scope="row"><span>Client</span></th>
            <th scope="row"><span>Shop</span></th>
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
  </div>
</template>

<script lang="ts" src="./lazada-order.component.ts"></script>
