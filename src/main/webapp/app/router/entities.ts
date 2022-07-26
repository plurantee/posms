import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore
const Entities = () => import('@/entities/entities.vue');

// prettier-ignore
const Client = () => import('@/entities/client/client.vue');
// prettier-ignore
const ClientUpdate = () => import('@/entities/client/client-update.vue');
// prettier-ignore
const ClientDetails = () => import('@/entities/client/client-details.vue');
// prettier-ignore
const UserInfo = () => import('@/entities/user-info/user-info.vue');
// prettier-ignore
const UserInfoUpdate = () => import('@/entities/user-info/user-info-update.vue');
// prettier-ignore
const UserInfoDetails = () => import('@/entities/user-info/user-info-details.vue');
// prettier-ignore
const Shop = () => import('@/entities/shop/shop.vue');
// prettier-ignore
const ShopUpdate = () => import('@/entities/shop/shop-update.vue');
// prettier-ignore
const ShopDetails = () => import('@/entities/shop/shop-details.vue');
// prettier-ignore
const ShopItems = () => import('@/entities/shop-items/shop-items.vue');
// prettier-ignore
const ShopItemsUpdate = () => import('@/entities/shop-items/shop-items-update.vue');
// prettier-ignore
const ShopItemsDetails = () => import('@/entities/shop-items/shop-items-details.vue');
// prettier-ignore
const LazadaOrder = () => import('@/entities/lazada-order/lazada-order.vue');
// prettier-ignore
const LazadaOrderUpdate = () => import('@/entities/lazada-order/lazada-order-update.vue');
// prettier-ignore
const LazadaOrderDetails = () => import('@/entities/lazada-order/lazada-order-details.vue');
// prettier-ignore
const ShopeeOrder = () => import('@/entities/shopee-order/shopee-order.vue');
// prettier-ignore
const ShopeeOrderUpdate = () => import('@/entities/shopee-order/shopee-order-update.vue');
// prettier-ignore
const ShopeeOrderDetails = () => import('@/entities/shopee-order/shopee-order-details.vue');
// prettier-ignore
const LazadaOrderPayments = () => import('@/entities/lazada-order-payments/lazada-order-payments.vue');
// prettier-ignore
const LazadaOrderPaymentsUpdate = () => import('@/entities/lazada-order-payments/lazada-order-payments-update.vue');
// prettier-ignore
const LazadaOrderPaymentsDetails = () => import('@/entities/lazada-order-payments/lazada-order-payments-details.vue');
// prettier-ignore
const Inventory = () => import('@/entities/inventory/inventory.vue');
// prettier-ignore
const InventoryUpdate = () => import('@/entities/inventory/inventory-update.vue');
// prettier-ignore
const InventoryDetails = () => import('@/entities/inventory/inventory-details.vue');
// prettier-ignore
const ShopeeOrderPayments = () => import('@/entities/shopee-order-payments/shopee-order-payments.vue');
// prettier-ignore
const ShopeeOrderPaymentsUpdate = () => import('@/entities/shopee-order-payments/shopee-order-payments-update.vue');
// prettier-ignore
const ShopeeOrderPaymentsDetails = () => import('@/entities/shopee-order-payments/shopee-order-payments-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

const ClientOrderTracker = () => import('@/entities/client-order-tracker/client-order-tracker.vue');
// prettier-ignore

const ClientLazadaOrder = () => import('@/entities/client-lazada-order/client-lazada-order.vue');
// prettier-ignore
const ClientShopeeOrderDetails = () => import('@/entities/client-shopee-order/client-shopee-order-details.vue');
// prettier-ignore
const ClientLazadaOrderDetails = () => import('@/entities/client-lazada-order/client-lazada-order-details.vue');

// prettier-ignore
const ClientInventory = () => import('@/entities/client-inventory/inventory.vue');
// prettier-ignore
const ClientInventoryUpdate = () => import('@/entities/client-inventory/inventory-update.vue');
// prettier-ignore
const ClientInventoryDetails = () => import('@/entities/client-inventory/inventory-details.vue');
export default {
  path: '/',
  component: Entities,
  children: [
    {
      path: 'client',
      name: 'Client',
      component: Client,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client/new',
      name: 'ClientCreate',
      component: ClientUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client/:clientId/edit',
      name: 'ClientEdit',
      component: ClientUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client/:clientId/view',
      name: 'ClientView',
      component: ClientDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'user-info',
      name: 'UserInfo',
      component: UserInfo,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'user-info/new',
      name: 'UserInfoCreate',
      component: UserInfoUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'user-info/:userInfoId/edit',
      name: 'UserInfoEdit',
      component: UserInfoUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'user-info/:userInfoId/view',
      name: 'UserInfoView',
      component: UserInfoDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'shop',
      name: 'Shop',
      component: Shop,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'shop/new',
      name: 'ShopCreate',
      component: ShopUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'shop/:shopId/edit',
      name: 'ShopEdit',
      component: ShopUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'shop/:shopId/view',
      name: 'ShopView',
      component: ShopDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'shop-items',
      name: 'ShopItems',
      component: ShopItems,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'shop-items/new',
      name: 'ShopItemsCreate',
      component: ShopItemsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'shop-items/:shopItemsId/edit',
      name: 'ShopItemsEdit',
      component: ShopItemsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'shop-items/:shopItemsId/view',
      name: 'ShopItemsView',
      component: ShopItemsDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'lazada-order',
      name: 'LazadaOrder',
      component: LazadaOrder,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'lazada-order/:lazadaOrderId/view',
      name: 'LazadaOrderView',
      component: LazadaOrderDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'shopee-order',
      name: 'ShopeeOrder',
      component: ShopeeOrder,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'shopee-order/:shopeeOrderId/view',
      name: 'ShopeeOrderView',
      component: ShopeeOrderDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'lazada-order-payments',
      name: 'LazadaOrderPayments',
      component: LazadaOrderPayments,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'lazada-order-payments/new',
      name: 'LazadaOrderPaymentsCreate',
      component: LazadaOrderPaymentsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'lazada-order-payments/:lazadaOrderPaymentsId/edit',
      name: 'LazadaOrderPaymentsEdit',
      component: LazadaOrderPaymentsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'lazada-order-payments/:lazadaOrderPaymentsId/view',
      name: 'LazadaOrderPaymentsView',
      component: LazadaOrderPaymentsDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'inventory',
      name: 'Inventory',
      component: Inventory,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'inventory/new',
      name: 'InventoryCreate',
      component: InventoryUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'inventory/:inventoryId/edit',
      name: 'InventoryEdit',
      component: InventoryUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'inventory/:inventoryId/view',
      name: 'InventoryView',
      component: InventoryDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'shopee-order-payments',
      name: 'ShopeeOrderPayments',
      component: ShopeeOrderPayments,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'shopee-order-payments/new',
      name: 'ShopeeOrderPaymentsCreate',
      component: ShopeeOrderPaymentsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'shopee-order-payments/:shopeeOrderPaymentsId/edit',
      name: 'ShopeeOrderPaymentsEdit',
      component: ShopeeOrderPaymentsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'shopee-order-payments/:shopeeOrderPaymentsId/view',
      name: 'ShopeeOrderPaymentsView',
      component: ShopeeOrderPaymentsDetails,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
    {
      path: '/client/order-tracker',
      name: 'ClientOrderTracker',
      component: ClientOrderTracker,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client/shopee-order/:shopeeOrderId/view',
      name: 'ClientShopeeOrderView',
      component: ClientShopeeOrderDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client/lazada-order/:lazadaOrderId/view',
      name: 'ClientLazadaOrderView',
      component: ClientLazadaOrderDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client/inventory',
      name: 'ClientInventory',
      component: ClientInventory,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client/inventory/new',
      name: 'ClientInventoryCreate',
      component: ClientInventoryUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client/inventory/:inventoryId/edit',
      name: 'ClientInventoryEdit',
      component: ClientInventoryUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client/inventory/:inventoryId/view',
      name: 'ClientInventoryView',
      component: ClientInventoryDetails,
      meta: { authorities: [Authority.USER] },
    },
  ],
};
