import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IInventory } from '@/shared/model/inventory.model';

import InventoryService from './inventory.service';
import AlertService from '@/shared/alert/alert.service';
import CommonsService from '../common/commons-service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Inventory extends Vue {
  @Inject('clientInventoryService') private clientInventoryService: () => InventoryService;
  @Inject('commonsService') private commonsService: () => CommonsService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;
  private file = null;

  public inventories: IInventory[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllInventorys();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllInventorys();
  }

  public retrieveAllInventorys(): void {
    this.isFetching = true;
    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort(),
    };
    this.clientInventoryService()
      .retrieve(paginationQuery)
      .then(
        res => {
          this.inventories = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
          this.alertService().showHttpError(this, err.response);
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: IInventory): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeInventory(): void {
    this.clientInventoryService()
      .delete(this.removeId)
      .then(() => {
        const message = 'A Inventory is deleted with identifier ' + this.removeId;
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllInventorys();
        this.closeDialog();
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public sort(): Array<any> {
    const result = [this.propOrder + ',' + (this.reverse ? 'desc' : 'asc')];
    if (this.propOrder !== 'id') {
      result.push('id');
    }
    return result;
  }

  public loadPage(page: number): void {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  public transition(): void {
    this.retrieveAllInventorys();
  }

  public changeOrder(propOrder): void {
    this.propOrder = propOrder;
    this.reverse = !this.reverse;
    this.transition();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }

  public uploadFile(): void {
    this.file = (<HTMLInputElement>this.$refs.file).files[0];
  }

  public submitFile(): void {
    this.isFetching = true;
    // eslint-disable-next-line prefer-const
    let formData = new FormData();
    formData.append('file', this.file);
    this.commonsService()
      .uploadInventoryExcel(formData)
      .then(
        res => {
          this.handleSyncList();
          this.isFetching = false;
          return this.$root.$bvToast.toast(this.file.name + ' Uploaded', {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        },
        err => {
          this.handleSyncList();
          this.isFetching = false;
          this.alertService().showHttpError(this, err.response);
        }
      );
  }
}
