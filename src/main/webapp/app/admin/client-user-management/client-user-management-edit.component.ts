import { email, maxLength, minLength, required } from 'vuelidate/lib/validators';
import { Component, Inject, Vue } from 'vue-property-decorator';
import { IUser, User } from '@/shared/model/user.model';
import AlertService from '@/shared/alert/alert.service';
import ClientUserManagementService from './client-user-management.service';

const loginValidator = (value: string) => {
  if (!value) {
    return true;
  }
  return /^[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$|^[_.@A-Za-z0-9-]+$/.test(value);
};

const validations: any = {
  userAccount: {
    login: {
      required,
      maxLength: maxLength(254),
      pattern: loginValidator,
    },
    firstName: {
      maxLength: maxLength(50),
    },
    lastName: {
      maxLength: maxLength(50),
    },
    email: {
      required,
      email,
      minLength: minLength(5),
      maxLength: maxLength(50),
    },
  },
};

@Component({
  validations,
})
export default class ClientUserManagementEdit extends Vue {
  @Inject('clientUserManagementService') private userManagementService: () => ClientUserManagementService;
  @Inject('alertService') private alertService: () => AlertService;

  public userAccount: IUser;
  public isSaving = false;
  public authorities: any[] = [];
  public languages: any = this.$store.getters.languages;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.initAuthorities();
      if (to.params.userId) {
        vm.init(to.params.userId);
      }
    });
  }

  public constructor() {
    super();
    this.userAccount = new User();
    this.userAccount.authorities = [];
  }

  public initAuthorities() {
    this.userManagementService()
      .retrieveAuthorities()
      .then(_res => {
        this.authorities = _res.data;
      });
  }

  public init(userId: number): void {
    this.userManagementService()
      .clientGet(userId)
      .then(res => {
        this.userAccount = res.data;
      });
  }

  public previousState(): void {
    (<any>this).$router.go(-1);
  }

  public save(): void {
    this.isSaving = true;
    if (this.userAccount.id) {
      this.userManagementService()
        .clientUpdate(this.userAccount)
        .then(res => {
          this.returnToList();
          this.$root.$bvToast.toast(this.getMessageFromHeader(res).toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = true;
          this.alertService().showHttpError(this, error.response);
        });
    } else {
      this.userAccount.langKey = 'en';
      this.userManagementService()
        .create(this.userAccount)
        .then(res => {
          this.returnToList();
          this.$root.$bvToast.toast(this.getMessageFromHeader(res).toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = true;
          this.alertService().showHttpError(this, error.response);
        });
    }
  }

  private returnToList(): void {
    this.isSaving = false;
    (<any>this).$router.go(-1);
  }

  private getMessageFromHeader(res: any): any {
    return res.headers['x-posmsapp-alert'];
  }
}
