import { Component, Vue, Inject } from 'vue-property-decorator';

import { IInventory } from '@/shared/model/inventory.model';
import InventoryService from './inventory.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class InventoryDetails extends Vue {
  @Inject('inventoryService') private inventoryService: () => InventoryService;
  @Inject('alertService') private alertService: () => AlertService;

  public inventory: IInventory = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.inventoryId) {
        vm.retrieveInventory(to.params.inventoryId);
      }
    });
  }

  public retrieveInventory(inventoryId) {
    this.inventoryService()
      .find(inventoryId)
      .then(res => {
        this.inventory = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
