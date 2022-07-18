<template>
  <div>
    <h2 id="page-heading" data-cy="LazadaOrderPaymentsHeading">
      <span id="lazada-order-payments-heading">Upload Lazada Payments</span>
      <div class="d-flex justify-content-end">
        <router-link :to="{ name: 'LazadaOrderPaymentsCreate' }" custom v-slot="{ navigate }">
          <b-form-file
            v-model="file"
            placeholder="Upload Lazada File"
            v-on:change="uploadFile()"
            drop-placeholder="Drop file here..."
          ></b-form-file>
          <button
            @click="submitFile()"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-lazada-order"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span> Upload Lazada Orders </span>
          </button>
        </router-link>
      </div>
    </h2>
  </div>
</template>

<script lang="ts" src="./client-upload-lazada-order-payments.component.ts"></script>
