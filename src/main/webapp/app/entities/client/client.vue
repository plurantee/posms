<template>
  <div>
    <h2 id="page-heading" data-cy="ClientHeading">
      <span id="client-heading">Clients</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh List</span>
        </button>
        <router-link :to="{ name: 'ClientCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-client"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span> Create a new Client </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && clients && clients.length === 0">
      <span>No clients found</span>
    </div>
    <div class="table-responsive" v-if="clients && clients.length > 0">
      <table class="table table-striped" aria-describedby="clients">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('clientName')">
              <span>Client Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'clientName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('clientCode')">
              <span>Client Code</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'clientCode'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('clientType')">
              <span>Client Type</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'clientType'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('validityDate')">
              <span>Validity Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'validityDate'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="client in clients" :key="client.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ClientView', params: { clientId: client.id } }">{{ client.id }}</router-link>
            </td>
            <td>{{ client.clientName }}</td>
            <td>{{ client.clientCode }}</td>
            <td>{{ client.clientType }}</td>
            <td>{{ client.validityDate | formatDate }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ClientView', params: { clientId: client.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ClientEdit', params: { clientId: client.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(client)"
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
        ><span id="floPosmsApp.client.delete.question" data-cy="clientDeleteDialogHeading">Confirm delete operation</span></span
      >
      <div class="modal-body">
        <p id="jhi-delete-client-heading">Are you sure you want to delete this Client?</p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-client"
          data-cy="entityConfirmDeleteButton"
          v-on:click="removeClient()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="clients && clients.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./client.component.ts"></script>
