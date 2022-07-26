<template>
  <div>
    <b-nav tabs>
      <b-nav-item @click="switchNav('lazada')" :active="nav == 'lazada'">Lazada</b-nav-item>
      <b-nav-item @click="switchNav('shopee')" :active="nav == 'shopee'">Shopee</b-nav-item>
    </b-nav>
    <client-lazada-payments v-if="nav == 'lazada'"></client-lazada-payments>
    <client-shopee-payments v-if="nav == 'shopee'"></client-shopee-payments>
  </div>
</template>

<script lang="ts" src="./client-base-upload-order-payments.component.ts"></script>
<style scoped>
.total-payments {
  font-weight: 700;
  font-size: large;
  color: red;
}
</style>
