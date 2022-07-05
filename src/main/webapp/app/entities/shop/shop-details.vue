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
          <dd>
            <span>{{ shop.shopType }}</span>
          </dd>
          <dt>
            <span>Client Code</span>
          </dt>
          <dd>
            <div v-if="shop.clientCode">
              <router-link :to="{ name: 'ClientView', params: { clientId: shop.clientCode.id } }">{{ shop.clientCode.id }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link v-if="shop.id" :to="{ name: 'ShopEdit', params: { shopId: shop.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
          </button>
        </router-link>
        <lazada-order v-if="shop" :shop="shop"></lazada-order>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./shop-details.component.ts"></script>
