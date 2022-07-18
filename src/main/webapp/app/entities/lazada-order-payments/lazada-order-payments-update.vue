<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="floPosmsApp.lazadaOrderPayments.home.createOrEditLabel" data-cy="LazadaOrderPaymentsCreateUpdateHeading">
          Create or edit a LazadaOrderPayments
        </h2>
        <div>
          <div class="form-group" v-if="lazadaOrderPayments.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="lazadaOrderPayments.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-payments-transactionDate">Transaction Date</label>
            <div class="d-flex">
              <input
                id="lazada-order-payments-transactionDate"
                data-cy="transactionDate"
                type="datetime-local"
                class="form-control"
                name="transactionDate"
                :class="{
                  valid: !$v.lazadaOrderPayments.transactionDate.$invalid,
                  invalid: $v.lazadaOrderPayments.transactionDate.$invalid,
                }"
                :value="convertDateTimeFromServer($v.lazadaOrderPayments.transactionDate.$model)"
                @change="updateZonedDateTimeField('transactionDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-payments-transactionType">Transaction Type</label>
            <input
              type="text"
              class="form-control"
              name="transactionType"
              id="lazada-order-payments-transactionType"
              data-cy="transactionType"
              :class="{ valid: !$v.lazadaOrderPayments.transactionType.$invalid, invalid: $v.lazadaOrderPayments.transactionType.$invalid }"
              v-model="$v.lazadaOrderPayments.transactionType.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-payments-feeName">Fee Name</label>
            <input
              type="text"
              class="form-control"
              name="feeName"
              id="lazada-order-payments-feeName"
              data-cy="feeName"
              :class="{ valid: !$v.lazadaOrderPayments.feeName.$invalid, invalid: $v.lazadaOrderPayments.feeName.$invalid }"
              v-model="$v.lazadaOrderPayments.feeName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-payments-transactionNumber">Transaction Number</label>
            <input
              type="text"
              class="form-control"
              name="transactionNumber"
              id="lazada-order-payments-transactionNumber"
              data-cy="transactionNumber"
              :class="{
                valid: !$v.lazadaOrderPayments.transactionNumber.$invalid,
                invalid: $v.lazadaOrderPayments.transactionNumber.$invalid,
              }"
              v-model="$v.lazadaOrderPayments.transactionNumber.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-payments-details">Details</label>
            <input
              type="text"
              class="form-control"
              name="details"
              id="lazada-order-payments-details"
              data-cy="details"
              :class="{ valid: !$v.lazadaOrderPayments.details.$invalid, invalid: $v.lazadaOrderPayments.details.$invalid }"
              v-model="$v.lazadaOrderPayments.details.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-payments-sellerSku">Seller Sku</label>
            <input
              type="text"
              class="form-control"
              name="sellerSku"
              id="lazada-order-payments-sellerSku"
              data-cy="sellerSku"
              :class="{ valid: !$v.lazadaOrderPayments.sellerSku.$invalid, invalid: $v.lazadaOrderPayments.sellerSku.$invalid }"
              v-model="$v.lazadaOrderPayments.sellerSku.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-payments-lazadaSku">Lazada Sku</label>
            <input
              type="text"
              class="form-control"
              name="lazadaSku"
              id="lazada-order-payments-lazadaSku"
              data-cy="lazadaSku"
              :class="{ valid: !$v.lazadaOrderPayments.lazadaSku.$invalid, invalid: $v.lazadaOrderPayments.lazadaSku.$invalid }"
              v-model="$v.lazadaOrderPayments.lazadaSku.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-payments-amount">Amount</label>
            <input
              type="number"
              class="form-control"
              name="amount"
              id="lazada-order-payments-amount"
              data-cy="amount"
              :class="{ valid: !$v.lazadaOrderPayments.amount.$invalid, invalid: $v.lazadaOrderPayments.amount.$invalid }"
              v-model.number="$v.lazadaOrderPayments.amount.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-payments-vatInAmount">Vat In Amount</label>
            <input
              type="number"
              class="form-control"
              name="vatInAmount"
              id="lazada-order-payments-vatInAmount"
              data-cy="vatInAmount"
              :class="{ valid: !$v.lazadaOrderPayments.vatInAmount.$invalid, invalid: $v.lazadaOrderPayments.vatInAmount.$invalid }"
              v-model.number="$v.lazadaOrderPayments.vatInAmount.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-payments-whtAmount">Wht Amount</label>
            <input
              type="number"
              class="form-control"
              name="whtAmount"
              id="lazada-order-payments-whtAmount"
              data-cy="whtAmount"
              :class="{ valid: !$v.lazadaOrderPayments.whtAmount.$invalid, invalid: $v.lazadaOrderPayments.whtAmount.$invalid }"
              v-model.number="$v.lazadaOrderPayments.whtAmount.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-payments-whtIncludedInAmount">Wht Included In Amount</label>
            <input
              type="checkbox"
              class="form-check"
              name="whtIncludedInAmount"
              id="lazada-order-payments-whtIncludedInAmount"
              data-cy="whtIncludedInAmount"
              :class="{
                valid: !$v.lazadaOrderPayments.whtIncludedInAmount.$invalid,
                invalid: $v.lazadaOrderPayments.whtIncludedInAmount.$invalid,
              }"
              v-model="$v.lazadaOrderPayments.whtIncludedInAmount.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-payments-statement">Statement</label>
            <input
              type="text"
              class="form-control"
              name="statement"
              id="lazada-order-payments-statement"
              data-cy="statement"
              :class="{ valid: !$v.lazadaOrderPayments.statement.$invalid, invalid: $v.lazadaOrderPayments.statement.$invalid }"
              v-model="$v.lazadaOrderPayments.statement.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-payments-paidStatus">Paid Status</label>
            <input
              type="text"
              class="form-control"
              name="paidStatus"
              id="lazada-order-payments-paidStatus"
              data-cy="paidStatus"
              :class="{ valid: !$v.lazadaOrderPayments.paidStatus.$invalid, invalid: $v.lazadaOrderPayments.paidStatus.$invalid }"
              v-model="$v.lazadaOrderPayments.paidStatus.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-payments-orderNo">Order No</label>
            <input
              type="text"
              class="form-control"
              name="orderNo"
              id="lazada-order-payments-orderNo"
              data-cy="orderNo"
              :class="{ valid: !$v.lazadaOrderPayments.orderNo.$invalid, invalid: $v.lazadaOrderPayments.orderNo.$invalid }"
              v-model="$v.lazadaOrderPayments.orderNo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-payments-orderItemNo">Order Item No</label>
            <input
              type="text"
              class="form-control"
              name="orderItemNo"
              id="lazada-order-payments-orderItemNo"
              data-cy="orderItemNo"
              :class="{ valid: !$v.lazadaOrderPayments.orderItemNo.$invalid, invalid: $v.lazadaOrderPayments.orderItemNo.$invalid }"
              v-model="$v.lazadaOrderPayments.orderItemNo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-payments-orderItemStatus">Order Item Status</label>
            <input
              type="text"
              class="form-control"
              name="orderItemStatus"
              id="lazada-order-payments-orderItemStatus"
              data-cy="orderItemStatus"
              :class="{ valid: !$v.lazadaOrderPayments.orderItemStatus.$invalid, invalid: $v.lazadaOrderPayments.orderItemStatus.$invalid }"
              v-model="$v.lazadaOrderPayments.orderItemStatus.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-payments-shippingProvider">Shipping Provider</label>
            <input
              type="text"
              class="form-control"
              name="shippingProvider"
              id="lazada-order-payments-shippingProvider"
              data-cy="shippingProvider"
              :class="{
                valid: !$v.lazadaOrderPayments.shippingProvider.$invalid,
                invalid: $v.lazadaOrderPayments.shippingProvider.$invalid,
              }"
              v-model="$v.lazadaOrderPayments.shippingProvider.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-payments-shippingSpeed">Shipping Speed</label>
            <input
              type="text"
              class="form-control"
              name="shippingSpeed"
              id="lazada-order-payments-shippingSpeed"
              data-cy="shippingSpeed"
              :class="{ valid: !$v.lazadaOrderPayments.shippingSpeed.$invalid, invalid: $v.lazadaOrderPayments.shippingSpeed.$invalid }"
              v-model="$v.lazadaOrderPayments.shippingSpeed.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-payments-shipmentType">Shipment Type</label>
            <input
              type="text"
              class="form-control"
              name="shipmentType"
              id="lazada-order-payments-shipmentType"
              data-cy="shipmentType"
              :class="{ valid: !$v.lazadaOrderPayments.shipmentType.$invalid, invalid: $v.lazadaOrderPayments.shipmentType.$invalid }"
              v-model="$v.lazadaOrderPayments.shipmentType.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-payments-reference">Reference</label>
            <input
              type="text"
              class="form-control"
              name="reference"
              id="lazada-order-payments-reference"
              data-cy="reference"
              :class="{ valid: !$v.lazadaOrderPayments.reference.$invalid, invalid: $v.lazadaOrderPayments.reference.$invalid }"
              v-model="$v.lazadaOrderPayments.reference.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-payments-comment">Comment</label>
            <input
              type="text"
              class="form-control"
              name="comment"
              id="lazada-order-payments-comment"
              data-cy="comment"
              :class="{ valid: !$v.lazadaOrderPayments.comment.$invalid, invalid: $v.lazadaOrderPayments.comment.$invalid }"
              v-model="$v.lazadaOrderPayments.comment.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-payments-paymentRefId">Payment Ref Id</label>
            <input
              type="text"
              class="form-control"
              name="paymentRefId"
              id="lazada-order-payments-paymentRefId"
              data-cy="paymentRefId"
              :class="{ valid: !$v.lazadaOrderPayments.paymentRefId.$invalid, invalid: $v.lazadaOrderPayments.paymentRefId.$invalid }"
              v-model="$v.lazadaOrderPayments.paymentRefId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-payments-lazadaOrder">Lazada Order</label>
            <select
              class="form-control"
              id="lazada-order-payments-lazadaOrder"
              data-cy="lazadaOrder"
              name="lazadaOrder"
              v-model="lazadaOrderPayments.lazadaOrder"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  lazadaOrderPayments.lazadaOrder && lazadaOrderOption.id === lazadaOrderPayments.lazadaOrder.id
                    ? lazadaOrderPayments.lazadaOrder
                    : lazadaOrderOption
                "
                v-for="lazadaOrderOption in lazadaOrders"
                :key="lazadaOrderOption.id"
              >
                {{ lazadaOrderOption.id }}
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
            :disabled="$v.lazadaOrderPayments.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./lazada-order-payments-update.component.ts"></script>
