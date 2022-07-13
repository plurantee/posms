import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IShop } from '@/shared/model/shop.model';

import ClientShopService from './client-order-tracker';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class ClientOrderTracker extends Vue {
  public barcode = '';
  public shopType = 'lazada';
  public searchText() {
    alert('Searching..');
  }
}
