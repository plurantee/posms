<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="floPosmsApp.lazadaOrder.home.createOrEditLabel" data-cy="LazadaOrderCreateUpdateHeading">Create or edit a LazadaOrder</h2>
        <div>
          <div class="form-group" v-if="lazadaOrder.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="lazadaOrder.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-orderItemId">Order Item Id</label>
            <input
              type="text"
              class="form-control"
              name="orderItemId"
              id="lazada-order-orderItemId"
              data-cy="orderItemId"
              :class="{ valid: !$v.lazadaOrder.orderItemId.$invalid, invalid: $v.lazadaOrder.orderItemId.$invalid }"
              v-model="$v.lazadaOrder.orderItemId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-orderType">Order Type</label>
            <input
              type="text"
              class="form-control"
              name="orderType"
              id="lazada-order-orderType"
              data-cy="orderType"
              :class="{ valid: !$v.lazadaOrder.orderType.$invalid, invalid: $v.lazadaOrder.orderType.$invalid }"
              v-model="$v.lazadaOrder.orderType.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-guarantee">Guarantee</label>
            <input
              type="text"
              class="form-control"
              name="guarantee"
              id="lazada-order-guarantee"
              data-cy="guarantee"
              :class="{ valid: !$v.lazadaOrder.guarantee.$invalid, invalid: $v.lazadaOrder.guarantee.$invalid }"
              v-model="$v.lazadaOrder.guarantee.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-deliveryType">Delivery Type</label>
            <input
              type="text"
              class="form-control"
              name="deliveryType"
              id="lazada-order-deliveryType"
              data-cy="deliveryType"
              :class="{ valid: !$v.lazadaOrder.deliveryType.$invalid, invalid: $v.lazadaOrder.deliveryType.$invalid }"
              v-model="$v.lazadaOrder.deliveryType.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-lazadaId">Lazada Id</label>
            <input
              type="text"
              class="form-control"
              name="lazadaId"
              id="lazada-order-lazadaId"
              data-cy="lazadaId"
              :class="{ valid: !$v.lazadaOrder.lazadaId.$invalid, invalid: $v.lazadaOrder.lazadaId.$invalid }"
              v-model="$v.lazadaOrder.lazadaId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-sellerSku">Seller Sku</label>
            <input
              type="text"
              class="form-control"
              name="sellerSku"
              id="lazada-order-sellerSku"
              data-cy="sellerSku"
              :class="{ valid: !$v.lazadaOrder.sellerSku.$invalid, invalid: $v.lazadaOrder.sellerSku.$invalid }"
              v-model="$v.lazadaOrder.sellerSku.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-lazadaSku">Lazada Sku</label>
            <input
              type="text"
              class="form-control"
              name="lazadaSku"
              id="lazada-order-lazadaSku"
              data-cy="lazadaSku"
              :class="{ valid: !$v.lazadaOrder.lazadaSku.$invalid, invalid: $v.lazadaOrder.lazadaSku.$invalid }"
              v-model="$v.lazadaOrder.lazadaSku.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-wareHouse">Ware House</label>
            <input
              type="text"
              class="form-control"
              name="wareHouse"
              id="lazada-order-wareHouse"
              data-cy="wareHouse"
              :class="{ valid: !$v.lazadaOrder.wareHouse.$invalid, invalid: $v.lazadaOrder.wareHouse.$invalid }"
              v-model="$v.lazadaOrder.wareHouse.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-createTime">Create Time</label>
            <div class="d-flex">
              <input
                id="lazada-order-createTime"
                data-cy="createTime"
                type="datetime-local"
                class="form-control"
                name="createTime"
                :class="{ valid: !$v.lazadaOrder.createTime.$invalid, invalid: $v.lazadaOrder.createTime.$invalid }"
                :value="convertDateTimeFromServer($v.lazadaOrder.createTime.$model)"
                @change="updateZonedDateTimeField('createTime', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-updateTime">Update Time</label>
            <div class="d-flex">
              <input
                id="lazada-order-updateTime"
                data-cy="updateTime"
                type="datetime-local"
                class="form-control"
                name="updateTime"
                :class="{ valid: !$v.lazadaOrder.updateTime.$invalid, invalid: $v.lazadaOrder.updateTime.$invalid }"
                :value="convertDateTimeFromServer($v.lazadaOrder.updateTime.$model)"
                @change="updateZonedDateTimeField('updateTime', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-rtaSla">Rta Sla</label>
            <div class="d-flex">
              <input
                id="lazada-order-rtaSla"
                data-cy="rtaSla"
                type="datetime-local"
                class="form-control"
                name="rtaSla"
                :class="{ valid: !$v.lazadaOrder.rtaSla.$invalid, invalid: $v.lazadaOrder.rtaSla.$invalid }"
                :value="convertDateTimeFromServer($v.lazadaOrder.rtaSla.$model)"
                @change="updateZonedDateTimeField('rtaSla', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-ttsSla">Tts Sla</label>
            <div class="d-flex">
              <input
                id="lazada-order-ttsSla"
                data-cy="ttsSla"
                type="datetime-local"
                class="form-control"
                name="ttsSla"
                :class="{ valid: !$v.lazadaOrder.ttsSla.$invalid, invalid: $v.lazadaOrder.ttsSla.$invalid }"
                :value="convertDateTimeFromServer($v.lazadaOrder.ttsSla.$model)"
                @change="updateZonedDateTimeField('ttsSla', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-orderNumber">Order Number</label>
            <input
              type="text"
              class="form-control"
              name="orderNumber"
              id="lazada-order-orderNumber"
              data-cy="orderNumber"
              :class="{ valid: !$v.lazadaOrder.orderNumber.$invalid, invalid: $v.lazadaOrder.orderNumber.$invalid }"
              v-model="$v.lazadaOrder.orderNumber.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-invoiceRequired">Invoice Required</label>
            <input
              type="checkbox"
              class="form-check"
              name="invoiceRequired"
              id="lazada-order-invoiceRequired"
              data-cy="invoiceRequired"
              :class="{ valid: !$v.lazadaOrder.invoiceRequired.$invalid, invalid: $v.lazadaOrder.invoiceRequired.$invalid }"
              v-model="$v.lazadaOrder.invoiceRequired.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-invoiceNumber">Invoice Number</label>
            <input
              type="text"
              class="form-control"
              name="invoiceNumber"
              id="lazada-order-invoiceNumber"
              data-cy="invoiceNumber"
              :class="{ valid: !$v.lazadaOrder.invoiceNumber.$invalid, invalid: $v.lazadaOrder.invoiceNumber.$invalid }"
              v-model="$v.lazadaOrder.invoiceNumber.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-deliveryDate">Delivery Date</label>
            <div class="d-flex">
              <input
                id="lazada-order-deliveryDate"
                data-cy="deliveryDate"
                type="datetime-local"
                class="form-control"
                name="deliveryDate"
                :class="{ valid: !$v.lazadaOrder.deliveryDate.$invalid, invalid: $v.lazadaOrder.deliveryDate.$invalid }"
                :value="convertDateTimeFromServer($v.lazadaOrder.deliveryDate.$model)"
                @change="updateZonedDateTimeField('deliveryDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-customerName">Customer Name</label>
            <input
              type="text"
              class="form-control"
              name="customerName"
              id="lazada-order-customerName"
              data-cy="customerName"
              :class="{ valid: !$v.lazadaOrder.customerName.$invalid, invalid: $v.lazadaOrder.customerName.$invalid }"
              v-model="$v.lazadaOrder.customerName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-customerEmail">Customer Email</label>
            <input
              type="text"
              class="form-control"
              name="customerEmail"
              id="lazada-order-customerEmail"
              data-cy="customerEmail"
              :class="{ valid: !$v.lazadaOrder.customerEmail.$invalid, invalid: $v.lazadaOrder.customerEmail.$invalid }"
              v-model="$v.lazadaOrder.customerEmail.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-nationalRegistrationNumber">National Registration Number</label>
            <input
              type="text"
              class="form-control"
              name="nationalRegistrationNumber"
              id="lazada-order-nationalRegistrationNumber"
              data-cy="nationalRegistrationNumber"
              :class="{
                valid: !$v.lazadaOrder.nationalRegistrationNumber.$invalid,
                invalid: $v.lazadaOrder.nationalRegistrationNumber.$invalid,
              }"
              v-model="$v.lazadaOrder.nationalRegistrationNumber.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-shippingName">Shipping Name</label>
            <input
              type="text"
              class="form-control"
              name="shippingName"
              id="lazada-order-shippingName"
              data-cy="shippingName"
              :class="{ valid: !$v.lazadaOrder.shippingName.$invalid, invalid: $v.lazadaOrder.shippingName.$invalid }"
              v-model="$v.lazadaOrder.shippingName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-shippingAddress">Shipping Address</label>
            <input
              type="text"
              class="form-control"
              name="shippingAddress"
              id="lazada-order-shippingAddress"
              data-cy="shippingAddress"
              :class="{ valid: !$v.lazadaOrder.shippingAddress.$invalid, invalid: $v.lazadaOrder.shippingAddress.$invalid }"
              v-model="$v.lazadaOrder.shippingAddress.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-shippingAddress2">Shipping Address 2</label>
            <input
              type="text"
              class="form-control"
              name="shippingAddress2"
              id="lazada-order-shippingAddress2"
              data-cy="shippingAddress2"
              :class="{ valid: !$v.lazadaOrder.shippingAddress2.$invalid, invalid: $v.lazadaOrder.shippingAddress2.$invalid }"
              v-model="$v.lazadaOrder.shippingAddress2.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-shippingAddress3">Shipping Address 3</label>
            <input
              type="text"
              class="form-control"
              name="shippingAddress3"
              id="lazada-order-shippingAddress3"
              data-cy="shippingAddress3"
              :class="{ valid: !$v.lazadaOrder.shippingAddress3.$invalid, invalid: $v.lazadaOrder.shippingAddress3.$invalid }"
              v-model="$v.lazadaOrder.shippingAddress3.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-shippingAddress4">Shipping Address 4</label>
            <input
              type="text"
              class="form-control"
              name="shippingAddress4"
              id="lazada-order-shippingAddress4"
              data-cy="shippingAddress4"
              :class="{ valid: !$v.lazadaOrder.shippingAddress4.$invalid, invalid: $v.lazadaOrder.shippingAddress4.$invalid }"
              v-model="$v.lazadaOrder.shippingAddress4.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-shippingAddress5">Shipping Address 5</label>
            <input
              type="text"
              class="form-control"
              name="shippingAddress5"
              id="lazada-order-shippingAddress5"
              data-cy="shippingAddress5"
              :class="{ valid: !$v.lazadaOrder.shippingAddress5.$invalid, invalid: $v.lazadaOrder.shippingAddress5.$invalid }"
              v-model="$v.lazadaOrder.shippingAddress5.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-shippingPhone">Shipping Phone</label>
            <input
              type="text"
              class="form-control"
              name="shippingPhone"
              id="lazada-order-shippingPhone"
              data-cy="shippingPhone"
              :class="{ valid: !$v.lazadaOrder.shippingPhone.$invalid, invalid: $v.lazadaOrder.shippingPhone.$invalid }"
              v-model="$v.lazadaOrder.shippingPhone.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-shippingPhone2">Shipping Phone 2</label>
            <input
              type="text"
              class="form-control"
              name="shippingPhone2"
              id="lazada-order-shippingPhone2"
              data-cy="shippingPhone2"
              :class="{ valid: !$v.lazadaOrder.shippingPhone2.$invalid, invalid: $v.lazadaOrder.shippingPhone2.$invalid }"
              v-model="$v.lazadaOrder.shippingPhone2.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-shippingCity">Shipping City</label>
            <input
              type="text"
              class="form-control"
              name="shippingCity"
              id="lazada-order-shippingCity"
              data-cy="shippingCity"
              :class="{ valid: !$v.lazadaOrder.shippingCity.$invalid, invalid: $v.lazadaOrder.shippingCity.$invalid }"
              v-model="$v.lazadaOrder.shippingCity.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-shippingPostCode">Shipping Post Code</label>
            <input
              type="text"
              class="form-control"
              name="shippingPostCode"
              id="lazada-order-shippingPostCode"
              data-cy="shippingPostCode"
              :class="{ valid: !$v.lazadaOrder.shippingPostCode.$invalid, invalid: $v.lazadaOrder.shippingPostCode.$invalid }"
              v-model="$v.lazadaOrder.shippingPostCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-shippingCountry">Shipping Country</label>
            <input
              type="text"
              class="form-control"
              name="shippingCountry"
              id="lazada-order-shippingCountry"
              data-cy="shippingCountry"
              :class="{ valid: !$v.lazadaOrder.shippingCountry.$invalid, invalid: $v.lazadaOrder.shippingCountry.$invalid }"
              v-model="$v.lazadaOrder.shippingCountry.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-shippingRegion">Shipping Region</label>
            <input
              type="text"
              class="form-control"
              name="shippingRegion"
              id="lazada-order-shippingRegion"
              data-cy="shippingRegion"
              :class="{ valid: !$v.lazadaOrder.shippingRegion.$invalid, invalid: $v.lazadaOrder.shippingRegion.$invalid }"
              v-model="$v.lazadaOrder.shippingRegion.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-billingName">Billing Name</label>
            <input
              type="text"
              class="form-control"
              name="billingName"
              id="lazada-order-billingName"
              data-cy="billingName"
              :class="{ valid: !$v.lazadaOrder.billingName.$invalid, invalid: $v.lazadaOrder.billingName.$invalid }"
              v-model="$v.lazadaOrder.billingName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-billingAddr">Billing Addr</label>
            <input
              type="text"
              class="form-control"
              name="billingAddr"
              id="lazada-order-billingAddr"
              data-cy="billingAddr"
              :class="{ valid: !$v.lazadaOrder.billingAddr.$invalid, invalid: $v.lazadaOrder.billingAddr.$invalid }"
              v-model="$v.lazadaOrder.billingAddr.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-billingAddr2">Billing Addr 2</label>
            <input
              type="text"
              class="form-control"
              name="billingAddr2"
              id="lazada-order-billingAddr2"
              data-cy="billingAddr2"
              :class="{ valid: !$v.lazadaOrder.billingAddr2.$invalid, invalid: $v.lazadaOrder.billingAddr2.$invalid }"
              v-model="$v.lazadaOrder.billingAddr2.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-billingAddr3">Billing Addr 3</label>
            <input
              type="text"
              class="form-control"
              name="billingAddr3"
              id="lazada-order-billingAddr3"
              data-cy="billingAddr3"
              :class="{ valid: !$v.lazadaOrder.billingAddr3.$invalid, invalid: $v.lazadaOrder.billingAddr3.$invalid }"
              v-model="$v.lazadaOrder.billingAddr3.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-billingAddr4">Billing Addr 4</label>
            <input
              type="text"
              class="form-control"
              name="billingAddr4"
              id="lazada-order-billingAddr4"
              data-cy="billingAddr4"
              :class="{ valid: !$v.lazadaOrder.billingAddr4.$invalid, invalid: $v.lazadaOrder.billingAddr4.$invalid }"
              v-model="$v.lazadaOrder.billingAddr4.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-billingAddr5">Billing Addr 5</label>
            <input
              type="text"
              class="form-control"
              name="billingAddr5"
              id="lazada-order-billingAddr5"
              data-cy="billingAddr5"
              :class="{ valid: !$v.lazadaOrder.billingAddr5.$invalid, invalid: $v.lazadaOrder.billingAddr5.$invalid }"
              v-model="$v.lazadaOrder.billingAddr5.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-billingPhone">Billing Phone</label>
            <input
              type="text"
              class="form-control"
              name="billingPhone"
              id="lazada-order-billingPhone"
              data-cy="billingPhone"
              :class="{ valid: !$v.lazadaOrder.billingPhone.$invalid, invalid: $v.lazadaOrder.billingPhone.$invalid }"
              v-model="$v.lazadaOrder.billingPhone.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-billingPhone2">Billing Phone 2</label>
            <input
              type="text"
              class="form-control"
              name="billingPhone2"
              id="lazada-order-billingPhone2"
              data-cy="billingPhone2"
              :class="{ valid: !$v.lazadaOrder.billingPhone2.$invalid, invalid: $v.lazadaOrder.billingPhone2.$invalid }"
              v-model="$v.lazadaOrder.billingPhone2.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-billingCity">Billing City</label>
            <input
              type="text"
              class="form-control"
              name="billingCity"
              id="lazada-order-billingCity"
              data-cy="billingCity"
              :class="{ valid: !$v.lazadaOrder.billingCity.$invalid, invalid: $v.lazadaOrder.billingCity.$invalid }"
              v-model="$v.lazadaOrder.billingCity.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-billingPostCode">Billing Post Code</label>
            <input
              type="text"
              class="form-control"
              name="billingPostCode"
              id="lazada-order-billingPostCode"
              data-cy="billingPostCode"
              :class="{ valid: !$v.lazadaOrder.billingPostCode.$invalid, invalid: $v.lazadaOrder.billingPostCode.$invalid }"
              v-model="$v.lazadaOrder.billingPostCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-billingCountry">Billing Country</label>
            <input
              type="text"
              class="form-control"
              name="billingCountry"
              id="lazada-order-billingCountry"
              data-cy="billingCountry"
              :class="{ valid: !$v.lazadaOrder.billingCountry.$invalid, invalid: $v.lazadaOrder.billingCountry.$invalid }"
              v-model="$v.lazadaOrder.billingCountry.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-taxCode">Tax Code</label>
            <input
              type="text"
              class="form-control"
              name="taxCode"
              id="lazada-order-taxCode"
              data-cy="taxCode"
              :class="{ valid: !$v.lazadaOrder.taxCode.$invalid, invalid: $v.lazadaOrder.taxCode.$invalid }"
              v-model="$v.lazadaOrder.taxCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-branchNumber">Branch Number</label>
            <input
              type="text"
              class="form-control"
              name="branchNumber"
              id="lazada-order-branchNumber"
              data-cy="branchNumber"
              :class="{ valid: !$v.lazadaOrder.branchNumber.$invalid, invalid: $v.lazadaOrder.branchNumber.$invalid }"
              v-model="$v.lazadaOrder.branchNumber.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-taxInvoiceRequested">Tax Invoice Requested</label>
            <input
              type="text"
              class="form-control"
              name="taxInvoiceRequested"
              id="lazada-order-taxInvoiceRequested"
              data-cy="taxInvoiceRequested"
              :class="{ valid: !$v.lazadaOrder.taxInvoiceRequested.$invalid, invalid: $v.lazadaOrder.taxInvoiceRequested.$invalid }"
              v-model="$v.lazadaOrder.taxInvoiceRequested.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-payMethod">Pay Method</label>
            <input
              type="text"
              class="form-control"
              name="payMethod"
              id="lazada-order-payMethod"
              data-cy="payMethod"
              :class="{ valid: !$v.lazadaOrder.payMethod.$invalid, invalid: $v.lazadaOrder.payMethod.$invalid }"
              v-model="$v.lazadaOrder.payMethod.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-paidPrice">Paid Price</label>
            <input
              type="number"
              class="form-control"
              name="paidPrice"
              id="lazada-order-paidPrice"
              data-cy="paidPrice"
              :class="{ valid: !$v.lazadaOrder.paidPrice.$invalid, invalid: $v.lazadaOrder.paidPrice.$invalid }"
              v-model.number="$v.lazadaOrder.paidPrice.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-unitPrice">Unit Price</label>
            <input
              type="number"
              class="form-control"
              name="unitPrice"
              id="lazada-order-unitPrice"
              data-cy="unitPrice"
              :class="{ valid: !$v.lazadaOrder.unitPrice.$invalid, invalid: $v.lazadaOrder.unitPrice.$invalid }"
              v-model.number="$v.lazadaOrder.unitPrice.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-sellerDiscountTotal">Seller Discount Total</label>
            <input
              type="number"
              class="form-control"
              name="sellerDiscountTotal"
              id="lazada-order-sellerDiscountTotal"
              data-cy="sellerDiscountTotal"
              :class="{ valid: !$v.lazadaOrder.sellerDiscountTotal.$invalid, invalid: $v.lazadaOrder.sellerDiscountTotal.$invalid }"
              v-model.number="$v.lazadaOrder.sellerDiscountTotal.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-shippingFee">Shipping Fee</label>
            <input
              type="number"
              class="form-control"
              name="shippingFee"
              id="lazada-order-shippingFee"
              data-cy="shippingFee"
              :class="{ valid: !$v.lazadaOrder.shippingFee.$invalid, invalid: $v.lazadaOrder.shippingFee.$invalid }"
              v-model.number="$v.lazadaOrder.shippingFee.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-walletCredit">Wallet Credit</label>
            <input
              type="number"
              class="form-control"
              name="walletCredit"
              id="lazada-order-walletCredit"
              data-cy="walletCredit"
              :class="{ valid: !$v.lazadaOrder.walletCredit.$invalid, invalid: $v.lazadaOrder.walletCredit.$invalid }"
              v-model.number="$v.lazadaOrder.walletCredit.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-itemName">Item Name</label>
            <input
              type="text"
              class="form-control"
              name="itemName"
              id="lazada-order-itemName"
              data-cy="itemName"
              :class="{ valid: !$v.lazadaOrder.itemName.$invalid, invalid: $v.lazadaOrder.itemName.$invalid }"
              v-model="$v.lazadaOrder.itemName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-variation">Variation</label>
            <input
              type="text"
              class="form-control"
              name="variation"
              id="lazada-order-variation"
              data-cy="variation"
              :class="{ valid: !$v.lazadaOrder.variation.$invalid, invalid: $v.lazadaOrder.variation.$invalid }"
              v-model="$v.lazadaOrder.variation.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-cdShippingProvider">Cd Shipping Provider</label>
            <input
              type="text"
              class="form-control"
              name="cdShippingProvider"
              id="lazada-order-cdShippingProvider"
              data-cy="cdShippingProvider"
              :class="{ valid: !$v.lazadaOrder.cdShippingProvider.$invalid, invalid: $v.lazadaOrder.cdShippingProvider.$invalid }"
              v-model="$v.lazadaOrder.cdShippingProvider.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-shippingProvider">Shipping Provider</label>
            <input
              type="text"
              class="form-control"
              name="shippingProvider"
              id="lazada-order-shippingProvider"
              data-cy="shippingProvider"
              :class="{ valid: !$v.lazadaOrder.shippingProvider.$invalid, invalid: $v.lazadaOrder.shippingProvider.$invalid }"
              v-model="$v.lazadaOrder.shippingProvider.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-shipmentTypeName">Shipment Type Name</label>
            <input
              type="text"
              class="form-control"
              name="shipmentTypeName"
              id="lazada-order-shipmentTypeName"
              data-cy="shipmentTypeName"
              :class="{ valid: !$v.lazadaOrder.shipmentTypeName.$invalid, invalid: $v.lazadaOrder.shipmentTypeName.$invalid }"
              v-model="$v.lazadaOrder.shipmentTypeName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-shippingProviderType">Shipping Provider Type</label>
            <input
              type="text"
              class="form-control"
              name="shippingProviderType"
              id="lazada-order-shippingProviderType"
              data-cy="shippingProviderType"
              :class="{ valid: !$v.lazadaOrder.shippingProviderType.$invalid, invalid: $v.lazadaOrder.shippingProviderType.$invalid }"
              v-model="$v.lazadaOrder.shippingProviderType.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-cdTrackingCode">Cd Tracking Code</label>
            <input
              type="text"
              class="form-control"
              name="cdTrackingCode"
              id="lazada-order-cdTrackingCode"
              data-cy="cdTrackingCode"
              :class="{ valid: !$v.lazadaOrder.cdTrackingCode.$invalid, invalid: $v.lazadaOrder.cdTrackingCode.$invalid }"
              v-model="$v.lazadaOrder.cdTrackingCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-trackingCode">Tracking Code</label>
            <input
              type="text"
              class="form-control"
              name="trackingCode"
              id="lazada-order-trackingCode"
              data-cy="trackingCode"
              :class="{ valid: !$v.lazadaOrder.trackingCode.$invalid, invalid: $v.lazadaOrder.trackingCode.$invalid }"
              v-model="$v.lazadaOrder.trackingCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-trackingUrl">Tracking Url</label>
            <input
              type="text"
              class="form-control"
              name="trackingUrl"
              id="lazada-order-trackingUrl"
              data-cy="trackingUrl"
              :class="{ valid: !$v.lazadaOrder.trackingUrl.$invalid, invalid: $v.lazadaOrder.trackingUrl.$invalid }"
              v-model="$v.lazadaOrder.trackingUrl.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-shippingProviderFM">Shipping Provider FM</label>
            <input
              type="text"
              class="form-control"
              name="shippingProviderFM"
              id="lazada-order-shippingProviderFM"
              data-cy="shippingProviderFM"
              :class="{ valid: !$v.lazadaOrder.shippingProviderFM.$invalid, invalid: $v.lazadaOrder.shippingProviderFM.$invalid }"
              v-model="$v.lazadaOrder.shippingProviderFM.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-trackingCodeFM">Tracking Code FM</label>
            <input
              type="text"
              class="form-control"
              name="trackingCodeFM"
              id="lazada-order-trackingCodeFM"
              data-cy="trackingCodeFM"
              :class="{ valid: !$v.lazadaOrder.trackingCodeFM.$invalid, invalid: $v.lazadaOrder.trackingCodeFM.$invalid }"
              v-model="$v.lazadaOrder.trackingCodeFM.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-trackingUrlFM">Tracking Url FM</label>
            <input
              type="text"
              class="form-control"
              name="trackingUrlFM"
              id="lazada-order-trackingUrlFM"
              data-cy="trackingUrlFM"
              :class="{ valid: !$v.lazadaOrder.trackingUrlFM.$invalid, invalid: $v.lazadaOrder.trackingUrlFM.$invalid }"
              v-model="$v.lazadaOrder.trackingUrlFM.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-promisedShippingTime">Promised Shipping Time</label>
            <div class="d-flex">
              <input
                id="lazada-order-promisedShippingTime"
                data-cy="promisedShippingTime"
                type="datetime-local"
                class="form-control"
                name="promisedShippingTime"
                :class="{ valid: !$v.lazadaOrder.promisedShippingTime.$invalid, invalid: $v.lazadaOrder.promisedShippingTime.$invalid }"
                :value="convertDateTimeFromServer($v.lazadaOrder.promisedShippingTime.$model)"
                @change="updateZonedDateTimeField('promisedShippingTime', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-premium">Premium</label>
            <input
              type="text"
              class="form-control"
              name="premium"
              id="lazada-order-premium"
              data-cy="premium"
              :class="{ valid: !$v.lazadaOrder.premium.$invalid, invalid: $v.lazadaOrder.premium.$invalid }"
              v-model="$v.lazadaOrder.premium.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-status">Status</label>
            <input
              type="text"
              class="form-control"
              name="status"
              id="lazada-order-status"
              data-cy="status"
              :class="{ valid: !$v.lazadaOrder.status.$invalid, invalid: $v.lazadaOrder.status.$invalid }"
              v-model="$v.lazadaOrder.status.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-buyerFailedDeliveryReturnInitiator"
              >Buyer Failed Delivery Return Initiator</label
            >
            <input
              type="text"
              class="form-control"
              name="buyerFailedDeliveryReturnInitiator"
              id="lazada-order-buyerFailedDeliveryReturnInitiator"
              data-cy="buyerFailedDeliveryReturnInitiator"
              :class="{
                valid: !$v.lazadaOrder.buyerFailedDeliveryReturnInitiator.$invalid,
                invalid: $v.lazadaOrder.buyerFailedDeliveryReturnInitiator.$invalid,
              }"
              v-model="$v.lazadaOrder.buyerFailedDeliveryReturnInitiator.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-buyerFailedDeliveryReason">Buyer Failed Delivery Reason</label>
            <input
              type="text"
              class="form-control"
              name="buyerFailedDeliveryReason"
              id="lazada-order-buyerFailedDeliveryReason"
              data-cy="buyerFailedDeliveryReason"
              :class="{
                valid: !$v.lazadaOrder.buyerFailedDeliveryReason.$invalid,
                invalid: $v.lazadaOrder.buyerFailedDeliveryReason.$invalid,
              }"
              v-model="$v.lazadaOrder.buyerFailedDeliveryReason.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-buyerFailedDeliveryDetail">Buyer Failed Delivery Detail</label>
            <input
              type="text"
              class="form-control"
              name="buyerFailedDeliveryDetail"
              id="lazada-order-buyerFailedDeliveryDetail"
              data-cy="buyerFailedDeliveryDetail"
              :class="{
                valid: !$v.lazadaOrder.buyerFailedDeliveryDetail.$invalid,
                invalid: $v.lazadaOrder.buyerFailedDeliveryDetail.$invalid,
              }"
              v-model="$v.lazadaOrder.buyerFailedDeliveryDetail.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-buyerFailedDeliveryUserName">Buyer Failed Delivery User Name</label>
            <input
              type="text"
              class="form-control"
              name="buyerFailedDeliveryUserName"
              id="lazada-order-buyerFailedDeliveryUserName"
              data-cy="buyerFailedDeliveryUserName"
              :class="{
                valid: !$v.lazadaOrder.buyerFailedDeliveryUserName.$invalid,
                invalid: $v.lazadaOrder.buyerFailedDeliveryUserName.$invalid,
              }"
              v-model="$v.lazadaOrder.buyerFailedDeliveryUserName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-bundleId">Bundle Id</label>
            <input
              type="text"
              class="form-control"
              name="bundleId"
              id="lazada-order-bundleId"
              data-cy="bundleId"
              :class="{ valid: !$v.lazadaOrder.bundleId.$invalid, invalid: $v.lazadaOrder.bundleId.$invalid }"
              v-model="$v.lazadaOrder.bundleId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-bundleDiscount">Bundle Discount</label>
            <input
              type="number"
              class="form-control"
              name="bundleDiscount"
              id="lazada-order-bundleDiscount"
              data-cy="bundleDiscount"
              :class="{ valid: !$v.lazadaOrder.bundleDiscount.$invalid, invalid: $v.lazadaOrder.bundleDiscount.$invalid }"
              v-model.number="$v.lazadaOrder.bundleDiscount.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-refundAmount">Refund Amount</label>
            <input
              type="number"
              class="form-control"
              name="refundAmount"
              id="lazada-order-refundAmount"
              data-cy="refundAmount"
              :class="{ valid: !$v.lazadaOrder.refundAmount.$invalid, invalid: $v.lazadaOrder.refundAmount.$invalid }"
              v-model.number="$v.lazadaOrder.refundAmount.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-client">Client</label>
            <select class="form-control" id="lazada-order-client" data-cy="client" name="client" v-model="lazadaOrder.client">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="lazadaOrder.client && clientOption.id === lazadaOrder.client.id ? lazadaOrder.client : clientOption"
                v-for="clientOption in clients"
                :key="clientOption.id"
              >
                {{ clientOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="lazada-order-shop">Shop</label>
            <select class="form-control" id="lazada-order-shop" data-cy="shop" name="shop" v-model="lazadaOrder.shop">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="lazadaOrder.shop && shopOption.id === lazadaOrder.shop.id ? lazadaOrder.shop : shopOption"
                v-for="shopOption in shops"
                :key="shopOption.id"
              >
                {{ shopOption.id }}
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
            :disabled="$v.lazadaOrder.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./lazada-order-update.component.ts"></script>
