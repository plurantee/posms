import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IUserInfo } from '@/shared/model/user-info.model';

import UserInfoService from './user-info.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class UserInfo extends Vue {
  @Inject('userInfoService') private userInfoService: () => UserInfoService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public userInfos: IUserInfo[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllUserInfos();
  }

  public clear(): void {
    this.retrieveAllUserInfos();
  }

  public retrieveAllUserInfos(): void {
    this.isFetching = true;
    this.userInfoService()
      .retrieve()
      .then(
        res => {
          this.userInfos = res.data;
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

  public prepareRemove(instance: IUserInfo): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeUserInfo(): void {
    this.userInfoService()
      .delete(this.removeId)
      .then(() => {
        const message = 'A UserInfo is deleted with identifier ' + this.removeId;
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllUserInfos();
        this.closeDialog();
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
