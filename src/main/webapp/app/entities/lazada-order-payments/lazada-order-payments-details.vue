<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="lazadaOrderPayments">
        <h2 class="jh-entity-heading" data-cy="lazadaOrderPaymentsDetailsHeading">
          <span>LazadaOrderPayments</span> {{ lazadaOrderPayments.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Transaction Date</span>
          </dt>
          <dd>
            <span>{{ lazadaOrderPayments.transactionDate | formatDate }}</span>
          </dd>
          <dt>
            <span>Transaction Type</span>
          </dt>
          <dd>
            <span>{{ lazadaOrderPayments.transactionType }}</span>
          </dd>
          <dt>
            <span>Fee Name</span>
          </dt>
          <dd>
            <span>{{ lazadaOrderPayments.feeName }}</span>
          </dd>
          <dt>
            <span>Transaction Number</span>
          </dt>
          <dd>
            <span>{{ lazadaOrderPayments.transactionNumber }}</span>
          </dd>
          <dt>
            <span>Details</span>
          </dt>
          <dd>
            <span>{{ lazadaOrderPayments.details }}</span>
          </dd>
          <dt>
            <span>Seller Sku</span>
          </dt>
          <dd>
            <span>{{ lazadaOrderPayments.sellerSku }}</span>
          </dd>
          <dt>
            <span>Lazada Sku</span>
          </dt>
          <dd>
            <span>{{ lazadaOrderPayments.lazadaSku }}</span>
          </dd>
          <dt>
            <span>Amount</span>
          </dt>
          <dd>
            <span>{{ lazadaOrderPayments.amount }}</span>
          </dd>
          <dt>
            <span>Vat In Amount</span>
          </dt>
          <dd>
            <span>{{ lazadaOrderPayments.vatInAmount }}</span>
          </dd>
          <dt>
            <span>Wht Amount</span>
          </dt>
          <dd>
            <span>{{ lazadaOrderPayments.whtAmount }}</span>
          </dd>
          <dt>
            <span>Wht Included In Amount</span>
          </dt>
          <dd>
            <span>{{ lazadaOrderPayments.whtIncludedInAmount }}</span>
          </dd>
          <dt>
            <span>Statement</span>
          </dt>
          <dd>
            <span>{{ lazadaOrderPayments.statement }}</span>
          </dd>
          <dt>
            <span>Paid Status</span>
          </dt>
          <dd>
            <span>{{ lazadaOrderPayments.paidStatus }}</span>
          </dd>
          <dt>
            <span>Order No</span>
          </dt>
          <dd>
            <span>{{ lazadaOrderPayments.orderNo }}</span>
          </dd>
          <dt>
            <span>Order Item No</span>
          </dt>
          <dd>
            <span>{{ lazadaOrderPayments.orderItemNo }}</span>
          </dd>
          <dt>
            <span>Order Item Status</span>
          </dt>
          <dd>
            <span>{{ lazadaOrderPayments.orderItemStatus }}</span>
          </dd>
          <dt>
            <span>Shipping Provider</span>
          </dt>
          <dd>
            <span>{{ lazadaOrderPayments.shippingProvider }}</span>
          </dd>
          <dt>
            <span>Shipping Speed</span>
          </dt>
          <dd>
            <span>{{ lazadaOrderPayments.shippingSpeed }}</span>
          </dd>
          <dt>
            <span>Shipment Type</span>
          </dt>
          <dd>
            <span>{{ lazadaOrderPayments.shipmentType }}</span>
          </dd>
          <dt>
            <span>Reference</span>
          </dt>
          <dd>
            <span>{{ lazadaOrderPayments.reference }}</span>
          </dd>
          <dt>
            <span>Comment</span>
          </dt>
          <dd>
            <span>{{ lazadaOrderPayments.comment }}</span>
          </dd>
          <dt>
            <span>Payment Ref Id</span>
          </dt>
          <dd>
            <span>{{ lazadaOrderPayments.paymentRefId }}</span>
          </dd>
          <dt>
            <span>Lazada Order</span>
          </dt>
          <dd>
            <div v-if="lazadaOrderPayments.lazadaOrder">
              <router-link :to="{ name: 'LazadaOrderView', params: { lazadaOrderId: lazadaOrderPayments.lazadaOrder.id } }">{{
                lazadaOrderPayments.lazadaOrder.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link
          v-if="lazadaOrderPayments.id"
          :to="{ name: 'LazadaOrderPaymentsEdit', params: { lazadaOrderPaymentsId: lazadaOrderPayments.id } }"
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

<script lang="ts" src="./lazada-order-payments-details.component.ts"></script>
