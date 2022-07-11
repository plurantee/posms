<template>
  <div>
    <h2 id="page-heading" data-cy="ShopeeOrderHeading">
      <span id="shopee-order-heading">Shopee Orders</span>
      <div class="d-flex justify-content-end">
        <div>
          <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
            <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh List</span>
          </button>
          <b-form-file
            v-model="file"
            placeholder="Upload Shopee File"
            v-on:change="uploadFile()"
            drop-placeholder="Drop file here..."
          ></b-form-file>
          <button
            @click="submitFile()"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-shopee-order"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span> Upload Shopee Orders </span>
          </button>
        </div>

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
            <th scope="row"><span>ID</span></th>
            <th scope="row"><span>Order Id</span></th>
            <th scope="row"><span>Order Status</span></th>
            <th scope="row"><span>Return Refund Status</span></th>
            <th scope="row"><span>Tracking Number</span></th>
            <th scope="row"><span>Shipping Option</span></th>
            <th scope="row"><span>Shipment Method</span></th>
            <th scope="row"><span>Estimated Ship Out Date</span></th>
            <th scope="row"><span>Ship Time</span></th>
            <th scope="row"><span>Order Creation Date</span></th>
            <th scope="row"><span>Order Paid Time</span></th>
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
  </div>
</template>

<script lang="ts" src="./client-shopee-order.component.ts"></script>
