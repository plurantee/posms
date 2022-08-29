<template>
  <div>
    <div class="form-group">
      <label class="form-control-label" for="file">Lazada Orders</label>

      <div class="row col-md-12">
        <div class="form-group">
          <label class="form-control-label" for="shop-clientCode">Shop</label>
          <select class="form-control" id="shop" data-cy="shop" name="shop" v-model="shop">
            <option v-bind:value="null"></option>
            <option v-for="shopOption in shops" :key="shopOption.id" v-bind:value="shopOption.id">
              {{ shopOption.shopName }}
            </option>
          </select>
        </div>
      </div>
      <div class="row col-md-12">
        <b-form-file
          v-model="file"
          placeholder="Upload Lazada File"
          v-on:change="uploadFile()"
          accept=".xlsx"
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
          <span> Upload Lazada Orders </span>
        </button>
      </div>
    </div>
    <div class="form-group">
      <label class="form-control-label" for="file">Filter</label>
      <select
        class="form-control col-md-2"
        id="filter"
        v-model="filter"
        v-on:change="retrieveAllLazadaOrdersByClient()"
        data-cy="filter"
        name="filter"
      >
        <option value="all">All</option>
        <option value="unpaid">Unpaid</option>
      </select>
    </div>
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
            <th scope="row"><span>Order Number</span></th>
            <th scope="row"><span>Guarantee</span></th>
            <th scope="row"><span>Delivery Type</span></th>
            <th scope="row"><span>Lazada Id</span></th>
            <th scope="row"><span>Seller Sku</span></th>
            <th scope="row"><span>Tracking Number</span></th>
            <th scope="row"><span>Create Time</span></th>
            <th scope="row"><span>Date Uploaded</span></th>
            <th scope="row"><span>Date Released/Cancelled</span></th>
            <th scope="row"><span>Shop</span></th>
            <th scope="row"><span>Status</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="lazadaOrder in lazadaOrders" :key="lazadaOrder.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ClientLazadaOrderView', params: { lazadaOrderId: lazadaOrder.id } }">{{
                lazadaOrder.id
              }}</router-link>
            </td>
            <td>{{ lazadaOrder.orderItemId }}</td>
            <td>
              {{ lazadaOrder.orderNumber }}
            </td>
            <td>{{ lazadaOrder.guarantee }}</td>
            <td>{{ lazadaOrder.deliveryType }}</td>
            <td>{{ lazadaOrder.lazadaId }}</td>
            <td>
              <div v-if="lazadaOrder.inventory">
                <router-link :to="{ name: 'ClientInventoryView', params: { inventoryId: lazadaOrder.inventory.id } }">{{
                  lazadaOrder.inventory.sku
                }}</router-link>
              </div>
              <div v-else>
                {{ lazadaOrder.sellerSku }}
              </div>
            </td>
            <td>{{ lazadaOrder.trackingCode }}</td>
            <td>{{ lazadaOrder.createTime | formatDate }}</td>
            <td>{{ lazadaOrder.dateUploaded | formatDate }}</td>
            <td>{{ lazadaOrder.dateReleasedOrCancelled | formatDate }}</td>
            <td>
              <div v-if="lazadaOrder.shop">
                <router-link :to="{ name: 'ClientShopView', params: { shopId: lazadaOrder.shop.id } }">{{
                  lazadaOrder.shop.shopName
                }}</router-link>
              </div>
              <div v-else></div>
            </td>
            <td>{{ lazadaOrder.status }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ClientLazadaOrderView', params: { lazadaOrderId: lazadaOrder.id } }"
                  custom
                  v-slot="{ navigate }"
                >
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

<script lang="ts" src="./client-lazada-order.component.ts"></script>
