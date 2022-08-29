<template>
  <div>
    <form name="form" role="form" id="tracker-form" v-on:submit.prevent="search()">
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
              :value="startDate"
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
              :value="endDate"
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
      </div>
      <button type="submit" class="btn btn-primary" data-cy="submit">Search</button>
    </form>
    <h2 id="page-heading" data-cy="ProfitHeading">
      <span id="profit-heading">Profit: {{ profit }}</span>
    </h2>
    <div v-if="profit > 0" class="">
      <button @click="viewBreakdown()" class="btn btn-primary">View breakdown</button>
    </div>
    <div class="" v-if="isViewBreakdown">
      <h2 id="page-heading" data-cy="InventoryHeading">
        <span id="inventory-heading">Lazada Profit Breakdown</span>
      </h2>
      <div class="table-responsive" v-if="dashboardData && dashboardData.lazadaMap">
        <table class="table table-striped" aria-describedby="dashboardData.thresholdItems">
          <thead>
            <tr>
              <th scope="row"><span>SKU</span></th>
              <th scope="row"><span>Payments</span></th>
              <th scope="row"><span>Product Cost</span></th>
              <th scope="row"><span>Profit</span></th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(payments, inventory) in dashboardData.lazadaMap" :key="inventory" data-cy="entityTable">
              <td>
                <router-link :to="{ name: 'ClientInventoryView', params: { inventoryId: inventory.split('|')[0] } }">
                  {{ inventory.split('|')[1] }}
                </router-link>
              </td>
              <td>
                <div class="" v-for="payment in payments" :key="payment.id">
                  <router-link :to="{ name: 'ClientLazadaOrderView', params: { lazadaOrderId: payments[0].lazadaOrder.id } }"
                    >{{ payment.lazadaOrder.orderItemId }}
                  </router-link>
                  - {{ payment.transactionType }}: {{ payment.amount }}
                </div>
                TOTAL = <b>{{ sumAllLazada(payments) }}</b>
              </td>
              <td>{{ inventory.split('|')[2] }}</td>
              <td>{{ calculateProfit(sumAllLazada(payments), inventory.split('|')[2]) }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div class="" v-if="isViewBreakdown">
      <h2 id="page-heading" data-cy="InventoryHeading">
        <span id="inventory-heading">Shopee Profit Breakdown</span>
      </h2>
      <div class="table-responsive" v-if="dashboardData && dashboardData.shopeeMap">
        <table class="table table-striped" aria-describedby="dashboardData.thresholdItems">
          <thead>
            <tr>
              <th scope="row"><span>SKU</span></th>
              <th scope="row"><span>Shopee Orders</span></th>
              <th scope="row"><span>Payments</span></th>
              <th scope="row"><span>Product Cost</span></th>
              <th scope="row"><span>Profit</span></th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(payments, inventory) in dashboardData.shopeeMap" :key="inventory" data-cy="entityTable">
              <td>
                <router-link :to="{ name: 'ClientInventoryView', params: { inventoryId: inventory.split('|')[0] } }">
                  {{ inventory.split('|')[1] }}
                </router-link>
              </td>
              <td>
                <div class="" v-for="payment in payments" :key="payment.id">
                  <router-link :to="{ name: 'ShopeeOrderPaymentsView', params: { shopeeOrderPaymentsId: payment.id } }">{{
                    payment.orderId
                  }}</router-link>
                </div>
              </td>
              <td>
                <div class="" v-for="payment in payments" :key="payment.id">
                  {{ payment.totalReleasedAmount }}
                </div>
                TOTAL = <b>{{ sumAllShopee(payments) }}</b>
              </td>
              <td>{{ inventory.split('|')[2] }}</td>
              <td>{{ calculateProfit(sumAllShopee(payments), inventory.split('|')[2]) }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <h2 id="page-heading" data-cy="InventoryHeading">
      <span id="inventory-heading">Low stock items</span>
    </h2>
    <div
      class="alert alert-warning"
      v-if="!isFetching && (!dashboardData || !dashboardData.thresholdItems || dashboardData.thresholdItems.length === 0)"
    >
      <span>No items are in low stock</span>
    </div>
    <div class="table-responsive" v-if="dashboardData && dashboardData.thresholdItems && dashboardData.thresholdItems.length > 0">
      <table class="table table-striped" aria-describedby="dashboardData.thresholdItems">
        <thead>
          <tr>
            <th scope="row"><span>SKU</span></th>
            <th scope="row"><span>Stocks</span></th>
            <th scope="row"><span>Threshold</span></th>
            <th scope="row"><span>Cost</span></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="inventory in dashboardData.thresholdItems" :key="inventory.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ClientInventoryView', params: { inventoryId: inventory.id } }">{{ inventory.sku }}</router-link>
            </td>
            <td>
              {{ inventory.stocks }}
            </td>
            <td>{{ inventory.threshold }}</td>
            <td>{{ inventory.cost }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script lang="ts" src="./dashboard.component"></script>
