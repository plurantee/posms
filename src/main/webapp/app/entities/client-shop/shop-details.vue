<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="shop">
        <h2 class="jh-entity-heading" data-cy="shopDetailsHeading"><span>Shop</span> {{ shop.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Shop Code</span>
          </dt>
          <dd>
            <span>{{ shop.shopCode }}</span>
          </dd>
          <dt>
            <span>Shop Name</span>
          </dt>
          <dd>
            <span>{{ shop.shopName }}</span>
          </dd>
          <dt>
            <span>Shop Type</span>
          </dt>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link v-if="shop.id" :to="{ name: 'ShopEdit', params: { shopId: shop.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./shop-details.component.ts"></script>
