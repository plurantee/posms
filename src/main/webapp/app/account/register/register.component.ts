import Vue from 'vue';
import { Component, Inject } from 'vue-property-decorator';
import { email, helpers, maxLength, minLength, required, sameAs } from 'vuelidate/lib/validators';
import LoginService from '@/account/login.service';
import RegisterService from '@/account/register/register.service';
import { EMAIL_ALREADY_USED_TYPE, LOGIN_ALREADY_USED_TYPE } from '@/constants';
import { IClient } from '@/shared/model/client.model';
import AccountService from '@/account/account.service';

const loginPattern = helpers.regex('alpha', /^[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$|^[_.@A-Za-z0-9-]+$/);
const validations: any = {
  registerAccount: {
    clientCode: {},
    login: {
      required,
      minLength: minLength(1),
      maxLength: maxLength(50),
      pattern: loginPattern,
    },
    email: {
      required,
      minLength: minLength(5),
      maxLength: maxLength(254),
      email,
    },
    password: {
      required,
      minLength: minLength(4),
      maxLength: maxLength(254),
    },
  },
  confirmPassword: {
    required,
    minLength: minLength(4),
    maxLength: maxLength(50),
    // prettier-ignore
    sameAsPassword: sameAs(function() {
      return this.registerAccount.password;
    }),
  },
};

@Component({
  validations,
})
export default class Register extends Vue {
  @Inject('registerService') private registerService: () => RegisterService;
  @Inject('loginService') private loginService: () => LoginService;
  @Inject('accountService') private accountService: () => AccountService;
  public registerAccount: any = {
    clientCode: undefined,
    login: undefined,
    email: undefined,
    password: undefined,
  };
  private hasAnyAuthorityValue = false;
  public confirmPassword: any = null;
  public error = '';
  public errorEmailExists = '';
  public errorUserExists = '';
  public success = false;
  public clients: IClient[] = [];

  public mounted(): void {
    this.registerService()
      .getClients()
      .then(res => {
        this.clients = res.data;
      });
  }

  public register(): void {
    this.error = null;
    this.errorUserExists = null;
    this.errorEmailExists = null;
    this.registerAccount.langKey = 'en';
    this.registerService()
      .processRegistration(this.registerAccount)
      .then(() => {
        this.success = true;
      })
      .catch(error => {
        this.success = null;
        if (error.response.status === 400 && error.response.data.type === LOGIN_ALREADY_USED_TYPE) {
          this.errorUserExists = 'ERROR';
        } else if (error.response.status === 400 && error.response.data.type === EMAIL_ALREADY_USED_TYPE) {
          this.errorEmailExists = 'ERROR';
        } else {
          this.error = 'ERROR';
        }
      });
  }

  public openLogin(): void {
    this.loginService().openLogin((<any>this).$root);
  }

  public hasAnyAuthority(authorities: any): boolean {
    this.accountService()
      .hasAnyAuthorityAndCheckAuth(authorities)
      .then(value => {
        this.hasAnyAuthorityValue = value;
      });
    return this.hasAnyAuthorityValue;
  }
}
