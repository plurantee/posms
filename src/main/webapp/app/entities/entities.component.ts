import { Component, Provide, Vue } from 'vue-property-decorator';

import UserService from '@/entities/user/user.service';
import ClientService from './client/client.service';
import UserInfoService from './user-info/user-info.service';
import ShopService from './shop/shop.service';
import ShopItemsService from './shop-items/shop-items.service';
import LazadaOrderService from './lazada-order/lazada-order.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

@Component
export default class Entities extends Vue {
  @Provide('userService') private userService = () => new UserService();
  @Provide('clientService') private clientService = () => new ClientService();
  @Provide('userInfoService') private userInfoService = () => new UserInfoService();
  @Provide('shopService') private shopService = () => new ShopService();
  @Provide('shopItemsService') private shopItemsService = () => new ShopItemsService();
  @Provide('lazadaOrderService') private lazadaOrderService = () => new LazadaOrderService();
  // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
}
