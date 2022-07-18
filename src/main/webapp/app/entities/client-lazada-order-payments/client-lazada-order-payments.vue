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
            <th scope="row"><span>ID</span></th>
            <th scope="row"><span>Transaction Date</span></th>
            <th scope="row"><span>Transaction Type</span></th>
            <th scope="row"><span>Fee Name</span></th>
            <th scope="row"><span>Transaction Number</span></th>
            <th scope="row"><span>Details</span></th>
            <th scope="row"><span>Seller Sku</span></th>
            <th scope="row"><span>Lazada Sku</span></th>
            <th scope="row"><span>Amount</span></th>
            <th scope="row"><span>Vat In Amount</span></th>
            <th scope="row"><span>Wht Amount</span></th>
            <th scope="row"><span>Wht Included In Amount</span></th>
            <th scope="row"><span>Statement</span></th>
            <th scope="row"><span>Paid Status</span></th>
            <th scope="row"><span>Order No</span></th>
            <th scope="row"><span>Order Item No</span></th>
            <th scope="row"><span>Order Item Status</span></th>
            <th scope="row"><span>Shipping Provider</span></th>
            <th scope="row"><span>Shipping Speed</span></th>
            <th scope="row"><span>Shipment Type</span></th>
            <th scope="row"><span>Reference</span></th>
            <th scope="row"><span>Comment</span></th>
            <th scope="row"><span>Payment Ref Id</span></th>
            <th scope="row"><span>Lazada Order</span></th>
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
  </div>
</template>

<script lang="ts" src="./client-lazada-order-payments.component.ts"></script>
