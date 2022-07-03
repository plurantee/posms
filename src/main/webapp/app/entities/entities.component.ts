import { Component, Provide, Vue } from 'vue-property-decorator';

import UserService from '@/entities/user/user.service';
import ClientService from './client/client.service';
import UserInfoService from './user-info/user-info.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

@Component
export default class Entities extends Vue {
  @Provide('userService') private userService = () => new UserService();
  @Provide('clientService') private clientService = () => new ClientService();
  @Provide('userInfoService') private userInfoService = () => new UserInfoService();
  // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
}
