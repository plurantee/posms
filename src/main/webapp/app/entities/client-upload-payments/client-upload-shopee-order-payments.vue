<template>
  <div>
    <div class="form-group" v-if="hasNoOrderId">
      <label class="form-control-label" for="file">Upload Shopee Payments</label>

      <div class="row col-md-12">
        <b-form-file
          v-model="file"
          placeholder="Upload Shopee File"
          v-on:change="uploadFile()"
          accept=".xls"
          drop-placeholder="Drop file here..."
          class="col-md-4"
        ></b-form-file>
        <button
          @click="submitFile()"
          id="jh-create-entity"
          data-cy="entityCreateButton"
          class="btn btn-primary jh-create-entity create-shopee-order"
        >
          <font-awesome-icon icon="plus"></font-awesome-icon>
          <span> Upload Shopee Payments </span>
        </button>
      </div>
    </div>
    <div class="total-payments" v-else>Total Payments: {{ totalPayments }}</div>
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
            <th scope="row" v-on:click="changeOrder('orderCreationDate')">
              <span>Order Creation Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'orderCreationDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('payoutCompletedDate')">
              <span>Payout Completed Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'payoutCompletedDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('originalProductPrice')">
              <span>Original Product Price</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'originalProductPrice'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalReleasedAmount')">
              <span>Total Released Amount</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'totalReleasedAmount'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shippingProvider')">
              <span>Shipping Provider</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shippingProvider'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('courierName')">
              <span>Courier Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'courierName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shopeeOrder.id')">
              <span>Shopee Order</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shopeeOrder.id'"></jhi-sort-indicator>
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
            <td>{{ shopeeOrderPayments.orderCreationDate | formatDate }}</td>
            <td>{{ shopeeOrderPayments.payoutCompletedDate | formatDate }}</td>
            <td>{{ shopeeOrderPayments.originalProductPrice }}</td>
            <td>{{ shopeeOrderPayments.totalReleasedAmount }}</td>
            <td>{{ shopeeOrderPayments.shippingProvider }}</td>
            <td>{{ shopeeOrderPayments.courierName }}</td>
            <td>
              <div v-if="shopeeOrderPayments.shopeeOrder">
                <router-link :to="{ name: 'ShopeeOrderView', params: { shopeeOrderId: shopeeOrderPayments.shopeeOrder.id } }">{{
                  shopeeOrderPayments.shopeeOrder.id
                }}</router-link>
              </div>
            </td>
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

<script lang="ts" src="./client-upload-shopee-order-payments.component.ts"></script>
<style scoped>
.total-payments {
  font-weight: 700;
  font-size: large;
  color: red;
}
</style>
