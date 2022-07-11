import { Component, Provide, Vue } from 'vue-property-decorator';

import UserService from '@/entities/user/user.service';
import ClientService from './client/client.service';
import UserInfoService from './user-info/user-info.service';
import ShopService from './shop/shop.service';
import ShopItemsService from './shop-items/shop-items.service';
import LazadaOrderService from './lazada-order/lazada-order.service';
import ShopeeOrderService from './shopee-order/shopee-order.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here
import ClientLazadaOrderService from './client-lazada-order/client-lazada-order.service';
import ClientShopService from './client-shop/client-shop.service';
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
  // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here

  @Provide('clientLazadaOrderService') private clientLazadaOrderService = () => new ClientLazadaOrderService();
  @Provide('clientShopService') private clientShopService = () => new ClientShopService();
  @Provide('clientShopeeOrderService') private clientShopeeOrderService = () => new ClientShopeeOrderService();
}
