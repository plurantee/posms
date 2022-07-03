import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import UserService from '@/entities/user/user.service';

import ClientService from '@/entities/client/client.service';
import { IClient } from '@/shared/model/client.model';

import { IUserInfo, UserInfo } from '@/shared/model/user-info.model';
import UserInfoService from './user-info.service';

const validations: any = {
  userInfo: {},
};

@Component({
  validations,
})
export default class UserInfoUpdate extends Vue {
  @Inject('userInfoService') private userInfoService: () => UserInfoService;
  @Inject('alertService') private alertService: () => AlertService;

  public userInfo: IUserInfo = new UserInfo();

  @Inject('userService') private userService: () => UserService;

  public users: Array<any> = [];

  @Inject('clientService') private clientService: () => ClientService;

  public clients: IClient[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.userInfoId) {
        vm.retrieveUserInfo(to.params.userInfoId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.userInfo.id) {
      this.userInfoService()
        .update(this.userInfo)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A UserInfo is updated with identifier ' + param.id;
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    } else {
      this.userInfoService()
        .create(this.userInfo)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A UserInfo is created with identifier ' + param.id;
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    }
  }

  public retrieveUserInfo(userInfoId): void {
    this.userInfoService()
      .find(userInfoId)
      .then(res => {
        this.userInfo = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.userService()
      .retrieve()
      .then(res => {
        this.users = res.data;
      });
    this.clientService()
      .retrieve()
      .then(res => {
        this.clients = res.data;
      });
  }
}
