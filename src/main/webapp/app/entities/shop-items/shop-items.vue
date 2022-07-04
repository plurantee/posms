<template>
  <div>
    <h2 id="page-heading" data-cy="ShopItemsHeading">
      <span id="shop-items-heading">Shop Items</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh List</span>
        </button>
        <router-link :to="{ name: 'ShopItemsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-shop-items"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span> Create a new Shop Items </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && shopItems && shopItems.length === 0">
      <span>No shopItems found</span>
    </div>
    <div class="table-responsive" v-if="shopItems && shopItems.length > 0">
      <table class="table table-striped" aria-describedby="shopItems">
        <thead>
          <tr>
            <th scope="row"><span>ID</span></th>
            <th scope="row"><span>Stock</span></th>
            <th scope="row"><span>Price</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="shopItems in shopItems" :key="shopItems.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ShopItemsView', params: { shopItemsId: shopItems.id } }">{{ shopItems.id }}</router-link>
            </td>
            <td>{{ shopItems.stock }}</td>
            <td>{{ shopItems.price }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ShopItemsView', params: { shopItemsId: shopItems.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ShopItemsEdit', params: { shopItemsId: shopItems.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(shopItems)"
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
        ><span id="floPosmsApp.shopItems.delete.question" data-cy="shopItemsDeleteDialogHeading">Confirm delete operation</span></span
      >
      <div class="modal-body">
        <p id="jhi-delete-shopItems-heading">Are you sure you want to delete this Shop Items?</p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-shopItems"
          data-cy="entityConfirmDeleteButton"
          v-on:click="removeShopItems()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./shop-items.component.ts"></script>
