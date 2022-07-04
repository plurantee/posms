<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="shopItems">
        <h2 class="jh-entity-heading" data-cy="shopItemsDetailsHeading"><span>ShopItems</span> {{ shopItems.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Stock</span>
          </dt>
          <dd>
            <span>{{ shopItems.stock }}</span>
          </dd>
          <dt>
            <span>Price</span>
          </dt>
          <dd>
            <span>{{ shopItems.price }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link
          v-if="shopItems.id"
          :to="{ name: 'ShopItemsEdit', params: { shopItemsId: shopItems.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./shop-items-details.component.ts"></script>
