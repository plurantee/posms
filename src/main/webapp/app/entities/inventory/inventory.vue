<template>
  <div>
    <h2 id="page-heading" data-cy="InventoryHeading">
      <span id="inventory-heading">Inventories</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh List</span>
        </button>
        <router-link :to="{ name: 'InventoryCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-inventory"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span> Create a new Inventory </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && inventories && inventories.length === 0">
      <span>No inventories found</span>
    </div>
    <div class="table-responsive" v-if="inventories && inventories.length > 0">
      <table class="table table-striped" aria-describedby="inventories">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('sku')">
              <span>Sku</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'sku'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('stocks')">
              <span>Stocks</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'stocks'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('cost')">
              <span>Cost</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'cost'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('price')">
              <span>Price</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'price'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('threshold')">
              <span>Threshold</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'threshold'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('client.id')">
              <span>Client</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'client.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="inventory in inventories" :key="inventory.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'InventoryView', params: { inventoryId: inventory.id } }">{{ inventory.id }}</router-link>
            </td>
            <td>{{ inventory.sku }}</td>
            <td>{{ inventory.stocks }}</td>
            <td>{{ inventory.cost }}</td>
            <td>{{ inventory.price }}</td>
            <td>{{ inventory.threshold }}</td>
            <td>
              <div v-if="inventory.client">
                <router-link :to="{ name: 'ClientView', params: { clientId: inventory.client.id } }">{{ inventory.client.id }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'InventoryView', params: { inventoryId: inventory.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'InventoryEdit', params: { inventoryId: inventory.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(inventory)"
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
        ><span id="floPosmsApp.inventory.delete.question" data-cy="inventoryDeleteDialogHeading">Confirm delete operation</span></span
      >
      <div class="modal-body">
        <p id="jhi-delete-inventory-heading">Are you sure you want to delete this Inventory?</p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-inventory"
          data-cy="entityConfirmDeleteButton"
          v-on:click="removeInventory()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="inventories && inventories.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./inventory.component.ts"></script>
