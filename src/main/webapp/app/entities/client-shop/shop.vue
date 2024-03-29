<template>
  <div>
    <h2 id="page-heading" data-cy="ShopHeading">
      <span id="shop-heading">Shops</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh List</span>
        </button>
        <router-link :to="{ name: 'ClientShopCreate' }" custom v-slot="{ navigate }">
          <button @click="navigate" id="jh-create-entity" data-cy="entityCreateButton" class="btn btn-primary jh-create-entity create-shop">
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span> Create a new Shop </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && shops && shops.length === 0">
      <span>No shops found</span>
    </div>
    <div class="table-responsive" v-if="shops && shops.length > 0">
      <table class="table table-striped" aria-describedby="shops">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shopCode')">
              <span>Shop Code</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shopCode'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shopName')">
              <span>Shop Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shopName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('shopType')">
              <span>Shop Type</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'shopType'"></jhi-sort-indicator>
            </th>

            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="shop in shops" :key="shop.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ClientShopView', params: { shopId: shop.id } }">{{ shop.id }}</router-link>
            </td>
            <td>{{ shop.shopCode }}</td>
            <td>{{ shop.shopName }}</td>
            <td>{{ shop.shopType }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ClientShopView', params: { shopId: shop.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ClientShopEdit', params: { shopId: shop.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(shop)"
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
        ><span id="floPosmsApp.shop.delete.question" data-cy="shopDeleteDialogHeading">Confirm delete operation</span></span
      >
      <div class="modal-body">
        <p id="jhi-delete-shop-heading">Are you sure you want to delete this Shop?</p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-shop"
          data-cy="entityConfirmDeleteButton"
          v-on:click="removeShop()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="shops && shops.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./shop.component.ts"></script>
