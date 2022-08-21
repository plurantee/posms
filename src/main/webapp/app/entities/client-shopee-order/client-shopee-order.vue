<template>
  <div>
    <div class="form-group">
      <label class="form-control-label" for="file">Shopee Orders</label>

      <div class="row col-md-12">
        <b-form-file
          v-model="file"
          placeholder="Upload Shopee File"
          v-on:change="uploadFile()"
          drop-placeholder="Drop file here..."
          class="col-md-4"
        ></b-form-file>
        <button
          @click="submitFile()"
          id="jh-create-entity"
          data-cy="entityCreateButton"
          class="btn btn-primary jh-create-entity create-lazada-order"
        >
          <font-awesome-icon icon="plus"></font-awesome-icon>
          <span> Upload Shopee Orders </span>
        </button>
      </div>
    </div>
    <div class="form-group">
      <label class="form-control-label" for="file">Filter</label>
      <select
        class="form-control col-md-2"
        id="filter"
        v-model="filter"
        v-on:change="retrieveAllShopeeOrdersByClient()"
        data-cy="filter"
        name="filter"
      >
        <option value="all">All</option>
        <option value="unpaid">Unpaid</option>
      </select>
    </div>
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
            <th scope="row"><span>Sku Reference No</span></th>
            <th scope="row"><span>Quantity</span></th>
            <th scope="row"><span>Tracking Number</span></th>
            <th scope="row"><span>Shipping Option</span></th>
            <th scope="row"><span>Shipment Method</span></th>
            <th scope="row"><span>Estimated Ship Out Date</span></th>
            <th scope="row"><span>Ship Time</span></th>
            <th scope="row"><span>Date Order Created</span></th>
            <th scope="row"><span>Date Uploaded</span></th>
            <th scope="row"><span>Date Released/Cancelled</span></th>
            <th scope="row"><span>Order Paid Time</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="shopeeOrder in shopeeOrders" :key="shopeeOrder.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ClientShopeeOrderView', params: { shopeeOrderId: shopeeOrder.id } }">{{
                shopeeOrder.id
              }}</router-link>
            </td>
            <td>{{ shopeeOrder.orderId }}</td>
            <td>{{ shopeeOrder.orderStatus }}</td>
            <td>
              <div v-if="shopeeOrder.inventory">
                <router-link :to="{ name: 'ClientInventoryView', params: { inventoryId: shopeeOrder.inventory.id } }">{{
                  shopeeOrder.inventory.sku
                }}</router-link>
              </div>
              <div v-else>
                {{ shopeeOrder.skuReferenceNo }}
              </div>
            </td>
            <td>{{ shopeeOrder.quantity }}</td>
            <td>{{ shopeeOrder.trackingNumber }}</td>
            <td>{{ shopeeOrder.shippingOption }}</td>
            <td>{{ shopeeOrder.shipmentMethod }}</td>
            <td>{{ shopeeOrder.estimatedShipOutDate | formatDate }}</td>
            <td>{{ shopeeOrder.shipTime | formatDate }}</td>
            <td>{{ shopeeOrder.orderCreationDate | formatDate }}</td>
            <td>{{ shopeeOrder.dateUploaded | formatDate }}</td>
            <td>{{ shopeeOrder.dateReleasedOrCancelled | formatDate }}</td>
            <td>{{ shopeeOrder.orderPaidTime | formatDate }}</td>
            <td>
              <div v-if="shopeeOrder.shop">
                <router-link :to="{ name: 'ShopView', params: { shopId: shopeeOrder.shop.id } }">{{ shopeeOrder.shop.id }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ClientShopeeOrderView', params: { shopeeOrderId: shopeeOrder.id } }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">View</span>
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

<script lang="ts" src="./client-shopee-order.component.ts"></script>
