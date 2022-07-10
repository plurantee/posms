<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="lazadaOrder">
        <h2 class="jh-entity-heading" data-cy="lazadaOrderDetailsHeading"><span>LazadaOrder</span> {{ lazadaOrder.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Order Item Id</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.orderItemId }}</span>
          </dd>
          <dt>
            <span>Order Type</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.orderType }}</span>
          </dd>
          <dt>
            <span>Guarantee</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.guarantee }}</span>
          </dd>
          <dt>
            <span>Delivery Type</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.deliveryType }}</span>
          </dd>
          <dt>
            <span>Lazada Id</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.lazadaId }}</span>
          </dd>
          <dt>
            <span>Seller Sku</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.sellerSku }}</span>
          </dd>
          <dt>
            <span>Lazada Sku</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.lazadaSku }}</span>
          </dd>
          <dt>
            <span>Ware House</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.wareHouse }}</span>
          </dd>
          <dt>
            <span>Create Time</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.createTime }}</span>
          </dd>
          <dt>
            <span>Update Time</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.updateTime }}</span>
          </dd>
          <dt>
            <span>Rta Sla</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.rtaSla }}</span>
          </dd>
          <dt>
            <span>Tts Sla</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.ttsSla }}</span>
          </dd>
          <dt>
            <span>Order Number</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.orderNumber }}</span>
          </dd>
          <dt>
            <span>Invoice Required</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.invoiceRequired }}</span>
          </dd>
          <dt>
            <span>Invoice Number</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.invoiceNumber }}</span>
          </dd>
          <dt>
            <span>Delivery Date</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.deliveryDate }}</span>
          </dd>
          <dt>
            <span>Customer Name</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.customerName }}</span>
          </dd>
          <dt>
            <span>Customer Email</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.customerEmail }}</span>
          </dd>
          <dt>
            <span>National Registration Number</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.nationalRegistrationNumber }}</span>
          </dd>
          <dt>
            <span>Shipping Name</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.shippingName }}</span>
          </dd>
          <dt>
            <span>Shipping Address</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.shippingAddress }}</span>
          </dd>
          <dt>
            <span>Shipping Address 2</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.shippingAddress2 }}</span>
          </dd>
          <dt>
            <span>Shipping Address 3</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.shippingAddress3 }}</span>
          </dd>
          <dt>
            <span>Shipping Address 4</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.shippingAddress4 }}</span>
          </dd>
          <dt>
            <span>Shipping Address 5</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.shippingAddress5 }}</span>
          </dd>
          <dt>
            <span>Shipping Phone</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.shippingPhone }}</span>
          </dd>
          <dt>
            <span>Shipping Phone 2</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.shippingPhone2 }}</span>
          </dd>
          <dt>
            <span>Shipping City</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.shippingCity }}</span>
          </dd>
          <dt>
            <span>Shipping Post Code</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.shippingPostCode }}</span>
          </dd>
          <dt>
            <span>Shipping Country</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.shippingCountry }}</span>
          </dd>
          <dt>
            <span>Shipping Region</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.shippingRegion }}</span>
          </dd>
          <dt>
            <span>Billing Name</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.billingName }}</span>
          </dd>
          <dt>
            <span>Billing Addr</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.billingAddr }}</span>
          </dd>
          <dt>
            <span>Billing Addr 3</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.billingAddr3 }}</span>
          </dd>
          <dt>
            <span>Billing Addr 4</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.billingAddr4 }}</span>
          </dd>
          <dt>
            <span>Billing Addr 5</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.billingAddr5 }}</span>
          </dd>
          <dt>
            <span>Billing Phone</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.billingPhone }}</span>
          </dd>
          <dt>
            <span>Billing Phone 2</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.billingPhone2 }}</span>
          </dd>
          <dt>
            <span>Billing City</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.billingCity }}</span>
          </dd>
          <dt>
            <span>Billing Post Code</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.billingPostCode }}</span>
          </dd>
          <dt>
            <span>Billing Country</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.billingCountry }}</span>
          </dd>
          <dt>
            <span>Tax Code</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.taxCode }}</span>
          </dd>
          <dt>
            <span>Branch Number</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.branchNumber }}</span>
          </dd>
          <dt>
            <span>Tax Invoice Requested</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.taxInvoiceRequested }}</span>
          </dd>
          <dt>
            <span>Pay Method</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.payMethod }}</span>
          </dd>
          <dt>
            <span>Paid Price</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.paidPrice }}</span>
          </dd>
          <dt>
            <span>Unit Price</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.unitPrice }}</span>
          </dd>
          <dt>
            <span>Seller Discount Total</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.sellerDiscountTotal }}</span>
          </dd>
          <dt>
            <span>Shipping Fee</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.shippingFee }}</span>
          </dd>
          <dt>
            <span>Wallet Credit</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.walletCredit }}</span>
          </dd>
          <dt>
            <span>Item Name</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.itemName }}</span>
          </dd>
          <dt>
            <span>Variation</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.variation }}</span>
          </dd>
          <dt>
            <span>Cd Shipping Provider</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.cdShippingProvider }}</span>
          </dd>
          <dt>
            <span>Shipping Provider</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.shippingProvider }}</span>
          </dd>
          <dt>
            <span>Shipment Type Name</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.shipmentTypeName }}</span>
          </dd>
          <dt>
            <span>Shipping Provider Type</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.shippingProviderType }}</span>
          </dd>
          <dt>
            <span>Cd Tracking Code</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.cdTrackingCode }}</span>
          </dd>
          <dt>
            <span>Tracking Code</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.trackingCode }}</span>
          </dd>
          <dt>
            <span>Tracking Url</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.trackingUrl }}</span>
          </dd>
          <dt>
            <span>Shipping Provider FM</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.shippingProviderFM }}</span>
          </dd>
          <dt>
            <span>Tracking Code FM</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.trackingCodeFM }}</span>
          </dd>
          <dt>
            <span>Tracking Url FM</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.trackingUrlFM }}</span>
          </dd>
          <dt>
            <span>Promised Shipping Time</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.promisedShippingTime }}</span>
          </dd>
          <dt>
            <span>Premium</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.premium }}</span>
          </dd>
          <dt>
            <span>Status</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.status }}</span>
          </dd>
          <dt>
            <span>Buyer Failed Delivery Return Initiator</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.buyerFailedDeliveryReturnInitiator }}</span>
          </dd>
          <dt>
            <span>Buyer Failed Delivery Reason</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.buyerFailedDeliveryReason }}</span>
          </dd>
          <dt>
            <span>Buyer Failed Delivery Detail</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.buyerFailedDeliveryDetail }}</span>
          </dd>
          <dt>
            <span>Buyer Failed Delivery User Name</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.buyerFailedDeliveryUserName }}</span>
          </dd>
          <dt>
            <span>Bundle Id</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.bundleId }}</span>
          </dd>
          <dt>
            <span>Bundle Discount</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.bundleDiscount }}</span>
          </dd>
          <dt>
            <span>Refund Amount</span>
          </dt>
          <dd>
            <span>{{ lazadaOrder.refundAmount }}</span>
          </dd>
          <dt>
            <span>Shop</span>
          </dt>
          <dd>
            <div v-if="lazadaOrder.shop">
              <router-link :to="{ name: 'ShopView', params: { shopId: lazadaOrder.shop.id } }">{{ lazadaOrder.shop.id }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link
          v-if="lazadaOrder.id"
          :to="{ name: 'LazadaOrderEdit', params: { lazadaOrderId: lazadaOrder.id } }"
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

<script lang="ts" src="./client-lazada-order-details.component.ts"></script>
