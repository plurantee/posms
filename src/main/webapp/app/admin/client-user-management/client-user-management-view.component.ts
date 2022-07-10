import Vue from 'vue';
import { Component, Inject } from 'vue-property-decorator';
import AlertService from '@/shared/alert/alert.service';
import ClientUserManagementService from './client-user-management.service';

@Component
export default class ClientUserManagementView extends Vue {
  @Inject('clientUserManagementService') private userManagementService: () => ClientUserManagementService;
  @Inject('alertService') private alertService: () => AlertService;

  public user: any = null;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.userId) {
        vm.init(to.params.userId);
      }
    });
  }
  public init(userId: number): void {
    this.userManagementService()
      .clientGet(userId)
      .then(res => {
        this.user = res.data;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }
}
