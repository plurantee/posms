/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import LazadaOrderComponent from '@/entities/lazada-order/lazada-order.vue';
import LazadaOrderClass from '@/entities/lazada-order/lazada-order.component';
import LazadaOrderService from '@/entities/lazada-order/lazada-order.service';
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
  describe('LazadaOrder Management Component', () => {
    let wrapper: Wrapper<LazadaOrderClass>;
    let comp: LazadaOrderClass;
    let lazadaOrderServiceStub: SinonStubbedInstance<LazadaOrderService>;

    beforeEach(() => {
      lazadaOrderServiceStub = sinon.createStubInstance<LazadaOrderService>(LazadaOrderService);
      lazadaOrderServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<LazadaOrderClass>(LazadaOrderComponent, {
        store,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          lazadaOrderService: () => lazadaOrderServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      lazadaOrderServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllLazadaOrders();
      await comp.$nextTick();

      // THEN
      expect(lazadaOrderServiceStub.retrieve.called).toBeTruthy();
      expect(comp.lazadaOrders[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      lazadaOrderServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(lazadaOrderServiceStub.retrieve.callCount).toEqual(1);

      comp.removeLazadaOrder();
      await comp.$nextTick();

      // THEN
      expect(lazadaOrderServiceStub.delete.called).toBeTruthy();
      expect(lazadaOrderServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
