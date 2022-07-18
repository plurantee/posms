<template>
  <div>
    <h2 id="page-heading" data-cy="LazadaOrderPaymentsHeading">
      <span id="lazada-order-payments-heading">Lazada Order Payments</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh List</span>
        </button>
        <router-link :to="{ name: 'LazadaOrderPaymentsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-lazada-order-payments"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span> Create a new Lazada Order Payments </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && lazadaOrderPayments && lazadaOrderPayments.length === 0">
      <span>No lazadaOrderPayments found</span>
    </div>
    <div class="table-responsive" v-if="lazadaOrderPayments && lazadaOrderPayments.length > 0">
      <table class="table table-striped" aria-describedby="lazadaOrderPayments">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('transactionDate')">
              <span>Transaction Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'transactionDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('transactionType')">
              <span>Transaction Type</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'transactionType'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('feeName')">
              <span>Fee Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'feeName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('transactionNumber')">
              <span>Transaction Number</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'transactionNumber'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('details')">
              <span>Details</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'details'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('sellerSku')">
              <span>Seller Sku</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'sellerSku'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lazadaSku')">
              <span>Lazada Sku</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lazadaSku'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('amount')">
              <span>Amount</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'amount'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('vatInAmount')">
              <span>Vat In Amount</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'vatInAmount'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('whtAmount')">
              <span>Wht Amount</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'whtAmount'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('whtIncludedInAmount')">
              <span>Wht Included In Amount</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'whtIncludedInAmount'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('statement')">
              <span>Statement</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'statement'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('paidStatus')">
              <span>Paid Status</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'paidStatus'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('orderNo')">
              <span>Order No</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'orderNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('orderItemNo')">
              <span>Order Item No</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'orderItemNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('orderItemStatus')">
              <span>Order Item Status</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'orderItemStatus'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shippingProvider')">
              <span>Shipping Provider</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shippingProvider'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shippingSpeed')">
              <span>Shipping Speed</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shippingSpeed'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shipmentType')">
              <span>Shipment Type</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shipmentType'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('reference')">
              <span>Reference</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'reference'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('comment')">
              <span>Comment</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'comment'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('paymentRefId')">
              <span>Payment Ref Id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'paymentRefId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lazadaOrder.id')">
              <span>Lazada Order</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lazadaOrder.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="lazadaOrderPayments in lazadaOrderPayments" :key="lazadaOrderPayments.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'LazadaOrderPaymentsView', params: { lazadaOrderPaymentsId: lazadaOrderPayments.id } }">{{
                lazadaOrderPayments.id
              }}</router-link>
            </td>
            <td>{{ lazadaOrderPayments.transactionDate | formatDate }}</td>
            <td>{{ lazadaOrderPayments.transactionType }}</td>
            <td>{{ lazadaOrderPayments.feeName }}</td>
            <td>{{ lazadaOrderPayments.transactionNumber }}</td>
            <td>{{ lazadaOrderPayments.details }}</td>
            <td>{{ lazadaOrderPayments.sellerSku }}</td>
            <td>{{ lazadaOrderPayments.lazadaSku }}</td>
            <td>{{ lazadaOrderPayments.amount }}</td>
            <td>{{ lazadaOrderPayments.vatInAmount }}</td>
            <td>{{ lazadaOrderPayments.whtAmount }}</td>
            <td>{{ lazadaOrderPayments.whtIncludedInAmount }}</td>
            <td>{{ lazadaOrderPayments.statement }}</td>
            <td>{{ lazadaOrderPayments.paidStatus }}</td>
            <td>{{ lazadaOrderPayments.orderNo }}</td>
            <td>{{ lazadaOrderPayments.orderItemNo }}</td>
            <td>{{ lazadaOrderPayments.orderItemStatus }}</td>
            <td>{{ lazadaOrderPayments.shippingProvider }}</td>
            <td>{{ lazadaOrderPayments.shippingSpeed }}</td>
            <td>{{ lazadaOrderPayments.shipmentType }}</td>
            <td>{{ lazadaOrderPayments.reference }}</td>
            <td>{{ lazadaOrderPayments.comment }}</td>
            <td>{{ lazadaOrderPayments.paymentRefId }}</td>
            <td>
              <div v-if="lazadaOrderPayments.lazadaOrder">
                <router-link :to="{ name: 'LazadaOrderView', params: { lazadaOrderId: lazadaOrderPayments.lazadaOrder.id } }">{{
                  lazadaOrderPayments.lazadaOrder.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'LazadaOrderPaymentsView', params: { lazadaOrderPaymentsId: lazadaOrderPayments.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'LazadaOrderPaymentsEdit', params: { lazadaOrderPaymentsId: lazadaOrderPayments.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(lazadaOrderPayments)"
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
        ><span id="floPosmsApp.lazadaOrderPayments.delete.question" data-cy="lazadaOrderPaymentsDeleteDialogHeading"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-lazadaOrderPayments-heading">Are you sure you want to delete this Lazada Order Payments?</p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-lazadaOrderPayments"
          data-cy="entityConfirmDeleteButton"
          v-on:click="removeLazadaOrderPayments()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="lazadaOrderPayments && lazadaOrderPayments.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./lazada-order-payments.component.ts"></script>
