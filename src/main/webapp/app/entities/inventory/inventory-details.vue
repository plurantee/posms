<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="inventory">
        <h2 class="jh-entity-heading" data-cy="inventoryDetailsHeading"><span>Inventory</span> {{ inventory.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Sku</span>
          </dt>
          <dd>
            <span>{{ inventory.sku }}</span>
          </dd>
          <dt>
            <span>Stocks</span>
          </dt>
          <dd>
            <span>{{ inventory.stocks }}</span>
          </dd>
          <dt>
            <span>Cost</span>
          </dt>
          <dd>
            <span>{{ inventory.cost }}</span>
          </dd>
          <dt>
            <span>Price</span>
          </dt>
          <dd>
            <span>{{ inventory.price }}</span>
          </dd>
          <dt>
            <span>Threshold</span>
          </dt>
          <dd>
            <span>{{ inventory.threshold }}</span>
          </dd>
          <dt>
            <span>Client</span>
          </dt>
          <dd>
            <div v-if="inventory.client">
              <router-link :to="{ name: 'ClientView', params: { clientId: inventory.client.id } }">{{ inventory.client.id }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link
          v-if="inventory.id"
          :to="{ name: 'InventoryEdit', params: { inventoryId: inventory.id } }"
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

<script lang="ts" src="./inventory-details.component.ts"></script>
