<template>
  <div>
    <b-nav tabs>
      <b-nav-item @click="switchNav('waybill')" :active="nav == 'waybill'">Track by waybill</b-nav-item>
      <b-nav-item @click="switchNav('query')" :active="nav == 'query'">Track by query</b-nav-item>
    </b-nav>
    <div v-if="nav == 'waybill'">
      <div class="form-group">
        <label class="form-control-label" for="file">Upload to fill Waybill informations</label>
        <div class="row col-md-12">
          <b-form-file
            ref="file"
            v-model="file"
            placeholder="Upload Waybill"
            v-on:change="uploadFile($event)"
            accept=".pdf"
            drop-placeholder="Drop file here..."
            class="col-md-4"
          ></b-form-file>
          <button
            @click="submitFile()"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-lazada-order"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span> Upload Waybill </span>
          </button>
        </div>
      </div>

      <form name="form" role="form" id="tracker-form" v-on:submit.prevent="searchText()">
        <div class="form-group">
          <label class="form-control-label" for="barcode">Barcode Number</label>
          <input
            ref="barcode"
            type="text"
            class="form-control"
            id="barcode"
            inputname="barcode"
            placeholder="Scan tracking number or order id"
            autofocus
            required
            v-model="barcode"
          />
        </div>
        <button type="submit" class="btn btn-primary" data-cy="submit">Search</button>
      </form>
    </div>

    <form v-if="nav == 'query'" name="form" role="form" id="tracker-form" v-on:submit.prevent="query()">
      <div class="form-group">
        <div class="">
          <label class="form-control-label" for="client-validityDate">From:</label>
          <div class="">
            <input
              id="client-validityDate"
              data-cy="validityDate"
              type="datetime-local"
              class="form-control col-md-4"
              name="validityDate"
              @change="updateStartDate($event)"
            />
          </div>
        </div>

        <div class="">
          <label class="form-control-label" for="client-validityDate">To:</label>
          <div>
            <input
              id="client-validityDate"
              data-cy="validityDate"
              type="datetime-local"
              class="form-control col-md-4"
              name="validityDate"
              @change="updateEndDate($event)"
            />
          </div>
        </div>

        <label class="form-control-label" for="site">Site:</label>
        <select class="form-control col-md-4" name="site" v-model="site" data-cy="site">
          <option value="all" selected>ALL</option>
          <option value="shopee">SHOPEE</option>
          <option value="lazada">LAZADA</option>
        </select>

        <label class="form-control-label" for="status">Status:</label>
        <select class="form-control col-md-4" name="status" v-model="status" data-cy="status">
          <option value="all" selected>ALL</option>
          <option value="paid">PAID</option>
          <option value="pending_payment">PENDING PAYMENT</option>
          <option value="returned">RETURNED</option>
          <option value="pending_pickup">PENDING PICKUP</option>
          <option value="cancelled">CANCELLED</option>
        </select>
      </div>
      <button type="submit" class="btn btn-primary" data-cy="submit">Search</button>
    </form>

    <div class="alert alert-warning" v-if="!isFetching && orderTrackers && orderTrackers.length === 0">
      <span>No Orders found</span>
    </div>
    <div class="table-responsive" v-if="orderTrackers && orderTrackers.length > 0">
      <div class="modify-orders" v-if="nav == 'waybill'">
        <button @click="releaseOrders()" class="btn btn-success jh-create-entity create-lazada-order">
          <span> Release Orders </span>
        </button>
        <button @click="returnOrders()" class="btn btn-warning jh-create-entity create-lazada-order">
          <span> Return Orders </span>
        </button>
        <button @click="cancelOrders()" class="btn btn-danger jh-create-entity create-lazada-order">
          <span> Cancel Orders </span>
        </button>
      </div>

      <table class="table table-striped" aria-describedby="orderTracker">
        <thead>
          <tr>
            <th scope="row"><span>ID</span></th>
            <th scope="row"><span>Order Item Id</span></th>
            <th scope="row"><span>Order Type</span></th>
            <th scope="row"><span>Tracking Number</span></th>
            <th scope="row"><span>SKU Reference</span></th>
            <th scope="row"><span>Status</span></th>
            <th scope="row"><span>Site</span></th>
            <th scope="row"><span>Courier</span></th>
            <th scope="row"><span>Date Uploaded</span></th>
            <th scope="row"><span>Date Released Or Cancelled</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="orderTracker in orderTrackers" :key="orderTracker.id" data-cy="entityTable">
            <td>
              <router-link
                v-if="orderTracker.site == 'LAZADA'"
                :to="{ name: 'LazadaOrderView', params: { lazadaOrderId: orderTracker.id } }"
                >{{ orderTracker.id }}</router-link
              >
              <router-link
                v-if="orderTracker.site == 'SHOPEE'"
                :to="{ name: 'ShopeeOrderView', params: { shopeeOrderId: orderTracker.id } }"
                >{{ orderTracker.id }}</router-link
              >
            </td>
            <td>{{ orderTracker.orderItemId }}</td>
            <td>{{ orderTracker.orderType }}</td>
            <td>{{ orderTracker.barcodeNumber }}</td>
            <td>{{ orderTracker.skuReference }}</td>
            <td>{{ orderTracker.status }}</td>
            <td>{{ orderTracker.site }}</td>
            <td>{{ orderTracker.courier }}</td>
            <td>{{ orderTracker.dateUploaded | formatDate }}</td>
            <td>{{ orderTracker.dateReleasedOrCancelled | formatDate }}</td>

            <td class="text-right">
              <div class="btn-group">
                <router-link
                  v-if="orderTracker.site == 'LAZADA'"
                  :to="{ name: 'LazadaOrderView', params: { lazadaOrderId: orderTracker.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">View</span>
                  </button>
                </router-link>
                <router-link
                  v-if="orderTracker.site == 'SHOPEE'"
                  :to="{ name: 'ShopeeOrderView', params: { shopeeOrderId: orderTracker.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">View</span>
                  </button>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script lang="ts" src="./client-order-tracker.component.ts"></script>

<style scoped>
.modify-orders {
  margin: 5px;
}
</style>
