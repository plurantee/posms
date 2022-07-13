<template>
  <div>
    <form name="form" role="form" id="tracker-form" v-on:submit.prevent="searchText()">
      <div class="form-group">
        <label class="form-control-label" for="barcode">Shop Type</label>

        <select class="form-select" aria-label="Shop" v-model="shopType" required>
          <option value="lazada" selected>Lazada</option>
          <option value="shopee">Shopee</option>
        </select>
      </div>

      <div class="form-group">
        <label class="form-control-label" for="barcode">Barcode Number</label>
        <input type="text" class="form-control" id="barcode" inputname="barcode" autofocus required v-model="barcode" />
      </div>

      <button type="submit" class="btn btn-primary" data-cy="submit">Search</button>
    </form>
  </div>
</template>

<script lang="ts" src="./client-order-tracker.component.ts"></script>
