/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import LazadaOrderDetailComponent from '@/entities/lazada-order/lazada-order-details.vue';
import LazadaOrderClass from '@/entities/lazada-order/lazada-order-details.component';
import LazadaOrderService from '@/entities/lazada-order/lazada-order.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('LazadaOrder Management Detail Component', () => {
    let wrapper: Wrapper<LazadaOrderClass>;
    let comp: LazadaOrderClass;
    let lazadaOrderServiceStub: SinonStubbedInstance<LazadaOrderService>;

    beforeEach(() => {
      lazadaOrderServiceStub = sinon.createStubInstance<LazadaOrderService>(LazadaOrderService);

      wrapper = shallowMount<LazadaOrderClass>(LazadaOrderDetailComponent, {
        store,
        localVue,
        router,
        provide: { lazadaOrderService: () => lazadaOrderServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundLazadaOrder = { id: 123 };
        lazadaOrderServiceStub.find.resolves(foundLazadaOrder);

        // WHEN
        comp.retrieveLazadaOrder(123);
        await comp.$nextTick();

        // THEN
        expect(comp.lazadaOrder).toBe(foundLazadaOrder);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundLazadaOrder = { id: 123 };
        lazadaOrderServiceStub.find.resolves(foundLazadaOrder);

        // WHEN
        comp.beforeRouteEnter({ params: { lazadaOrderId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.lazadaOrder).toBe(foundLazadaOrder);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
