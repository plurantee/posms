<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="floPosmsApp.inventory.home.createOrEditLabel" data-cy="InventoryCreateUpdateHeading">Create or edit a Inventory</h2>
        <div>
          <div class="form-group" v-if="inventory.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="inventory.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="inventory-sku">Sku</label>
            <input
              type="text"
              class="form-control"
              name="sku"
              id="inventory-sku"
              data-cy="sku"
              :class="{ valid: !$v.inventory.sku.$invalid, invalid: $v.inventory.sku.$invalid }"
              v-model="$v.inventory.sku.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="inventory-stocks">Stocks</label>
            <input
              type="number"
              class="form-control"
              name="stocks"
              id="inventory-stocks"
              data-cy="stocks"
              :class="{ valid: !$v.inventory.stocks.$invalid, invalid: $v.inventory.stocks.$invalid }"
              v-model.number="$v.inventory.stocks.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="inventory-cost">Cost</label>
            <input
              type="number"
              class="form-control"
              name="cost"
              id="inventory-cost"
              data-cy="cost"
              :class="{ valid: !$v.inventory.cost.$invalid, invalid: $v.inventory.cost.$invalid }"
              v-model.number="$v.inventory.cost.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="inventory-price">Price</label>
            <input
              type="number"
              class="form-control"
              name="price"
              id="inventory-price"
              data-cy="price"
              :class="{ valid: !$v.inventory.price.$invalid, invalid: $v.inventory.price.$invalid }"
              v-model.number="$v.inventory.price.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="inventory-threshold">Threshold</label>
            <input
              type="number"
              class="form-control"
              name="threshold"
              id="inventory-threshold"
              data-cy="threshold"
              :class="{ valid: !$v.inventory.threshold.$invalid, invalid: $v.inventory.threshold.$invalid }"
              v-model.number="$v.inventory.threshold.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="inventory-client">Client</label>
            <select class="form-control" id="inventory-client" data-cy="client" name="client" v-model="inventory.client">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="inventory.client && clientOption.id === inventory.client.id ? inventory.client : clientOption"
                v-for="clientOption in clients"
                :key="clientOption.id"
              >
                {{ clientOption.id }}
              </option>
            </select>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.inventory.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./inventory-update.component.ts"></script>
