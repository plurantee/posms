<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="floPosmsApp.shopItems.home.createOrEditLabel" data-cy="ShopItemsCreateUpdateHeading">Create or edit a ShopItems</h2>
        <div>
          <div class="form-group" v-if="shopItems.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="shopItems.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shop-items-stock">Stock</label>
            <input
              type="number"
              class="form-control"
              name="stock"
              id="shop-items-stock"
              data-cy="stock"
              :class="{ valid: !$v.shopItems.stock.$invalid, invalid: $v.shopItems.stock.$invalid }"
              v-model.number="$v.shopItems.stock.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shop-items-price">Price</label>
            <input
              type="number"
              class="form-control"
              name="price"
              id="shop-items-price"
              data-cy="price"
              :class="{ valid: !$v.shopItems.price.$invalid, invalid: $v.shopItems.price.$invalid }"
              v-model.number="$v.shopItems.price.$model"
            />
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
            :disabled="$v.shopItems.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./shop-items-update.component.ts"></script>
