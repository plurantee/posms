import { Component, Provide, Vue } from 'vue-property-decorator';

import UserService from '@/entities/user/user.service';
import ClientService from './client/client.service';
import UserInfoService from './user-info/user-info.service';
import ShopService from './shop/shop.service';
import ShopItemsService from './shop-items/shop-items.service';
import LazadaOrderService from './lazada-order/lazada-order.service';
import ShopeeOrderService from './shopee-order/shopee-order.service';
import LazadaOrderPaymentsService from './lazada-order-payments/lazada-order-payments.service';
import InventoryService from './inventory/inventory.service';
import ClientInventoryService from './client-inventory/inventory.service';
import ShopeeOrderPaymentsService from './shopee-order-payments/shopee-order-payments.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here
import ClientLazadaOrderService from './client-lazada-order/client-lazada-order.service';
import ClientShopeeOrderService from './client-shopee-order/client-shopee-order.service';

@Component
export default class Entities extends Vue {
  @Provide('userService') private userService = () => new UserService();
  @Provide('clientService') private clientService = () => new ClientService();
  @Provide('userInfoService') private userInfoService = () => new UserInfoService();
  @Provide('shopService') private shopService = () => new ShopService();
  @Provide('shopItemsService') private shopItemsService = () => new ShopItemsService();
  @Provide('lazadaOrderService') private lazadaOrderService = () => new LazadaOrderService();
  @Provide('shopeeOrderService') private shopeeOrderService = () => new ShopeeOrderService();
  @Provide('lazadaOrderPaymentsService') private lazadaOrderPaymentsService = () => new LazadaOrderPaymentsService();
  @Provide('inventoryService') private inventoryService = () => new InventoryService();
  @Provide('clientInventoryService') private clientInventoryService = () => new ClientInventoryService();
  @Provide('shopeeOrderPaymentsService') private shopeeOrderPaymentsService = () => new ShopeeOrderPaymentsService();
  // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here

  @Provide('clientLazadaOrderService') private clientLazadaOrderService = () => new ClientLazadaOrderService();
  @Provide('clientShopeeOrderService') private clientShopeeOrderService = () => new ClientShopeeOrderService();
}
