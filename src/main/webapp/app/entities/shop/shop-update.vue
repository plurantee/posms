<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="floPosmsApp.shop.home.createOrEditLabel" data-cy="ShopCreateUpdateHeading">Create or edit a Shop</h2>
        <div>
          <div class="form-group" v-if="shop.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="shop.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shop-shopName">Shop Name</label>
            <input
              type="text"
              class="form-control"
              name="shopName"
              id="shop-shopName"
              data-cy="shopName"
              :class="{ valid: !$v.shop.shopName.$invalid, invalid: $v.shop.shopName.$invalid }"
              v-model="$v.shop.shopName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="shop-shopType">Shop Type</label>
            <select
              class="form-control"
              name="shopType"
              :class="{ valid: !$v.shop.shopType.$invalid, invalid: $v.shop.shopType.$invalid }"
              v-model="$v.shop.shopType.$model"
              id="shop-shopType"
              data-cy="shopType"
            >
              <option v-for="shopType in shopTypeValues" :key="shopType" v-bind:value="shopType">{{ shopType }}</option>
            </select>
          </div>
          <div v-if="hasAnyAuthority('ROLE_ADMIN')" class="form-group">
            <label class="form-control-label" for="shop-clientCode">Client Code</label>
            <select class="form-control" id="shop-clientCode" data-cy="clientCode" name="clientCode" v-model="shop.clientCode">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="shop.clientCode && clientOption.id === shop.clientCode.id ? shop.clientCode : clientOption"
                v-for="clientOption in clients"
                :key="clientOption.id"
              >
                {{ clientOption.clientCode }}
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
            :disabled="$v.shop.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./shop-update.component.ts"></script>
