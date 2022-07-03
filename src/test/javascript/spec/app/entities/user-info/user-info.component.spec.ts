/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import UserInfoComponent from '@/entities/user-info/user-info.vue';
import UserInfoClass from '@/entities/user-info/user-info.component';
import UserInfoService from '@/entities/user-info/user-info.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(ToastPlugin);

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('UserInfo Management Component', () => {
    let wrapper: Wrapper<UserInfoClass>;
    let comp: UserInfoClass;
    let userInfoServiceStub: SinonStubbedInstance<UserInfoService>;

    beforeEach(() => {
      userInfoServiceStub = sinon.createStubInstance<UserInfoService>(UserInfoService);
      userInfoServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<UserInfoClass>(UserInfoComponent, {
        store,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          userInfoService: () => userInfoServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      userInfoServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllUserInfos();
      await comp.$nextTick();

      // THEN
      expect(userInfoServiceStub.retrieve.called).toBeTruthy();
      expect(comp.userInfos[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      userInfoServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(userInfoServiceStub.retrieve.callCount).toEqual(1);

      comp.removeUserInfo();
      await comp.$nextTick();

      // THEN
      expect(userInfoServiceStub.delete.called).toBeTruthy();
      expect(userInfoServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
