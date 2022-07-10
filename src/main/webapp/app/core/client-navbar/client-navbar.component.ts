import { Component, Inject, Vue } from 'vue-property-decorator';
import LoginService from '@/account/login.service';
import AccountService from '@/account/account.service';

import EntitiesMenu from '@/entities/entities-menu.vue';
import JhiNavbar from '../jhi-navbar/jhi-navbar.component';

@Component({
  components: {
    'entities-menu': EntitiesMenu,
  },
})
export default class ClientNavbar extends JhiNavbar {
  public version = 'v' + VERSION;

  created() {
    console.log('Opened Client Navbar');
  }

  public subIsActive(input) {
    const paths = Array.isArray(input) ? input : [input];
    return paths.some(path => {
      return this.$route.path.indexOf(path) === 0; // current path starts with this path string
    });
  }

  public logout(): Promise<any> {
    localStorage.removeItem('jhi-authenticationToken');
    sessionStorage.removeItem('jhi-authenticationToken');
    this.$store.commit('logout');
    if (this.$route.path !== '/') {
      return this.$router.push('/');
    }
    return Promise.resolve(this.$router.currentRoute);
  }

  public get authenticated(): boolean {
    return this.$store.getters.authenticated;
  }

  public get openAPIEnabled(): boolean {
    return this.$store.getters.activeProfiles.indexOf('api-docs') > -1;
  }

  public get inProduction(): boolean {
    return this.$store.getters.activeProfiles.indexOf('prod') > -1;
  }
}
