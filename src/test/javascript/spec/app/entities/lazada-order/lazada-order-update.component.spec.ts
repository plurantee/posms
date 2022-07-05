/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import LazadaOrderUpdateComponent from '@/entities/lazada-order/lazada-order-update.vue';
import LazadaOrderClass from '@/entities/lazada-order/lazada-order-update.component';
import LazadaOrderService from '@/entities/lazada-order/lazada-order.service';

import ShopService from '@/entities/shop/shop.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.use(ToastPlugin);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('LazadaOrder Management Update Component', () => {
    let wrapper: Wrapper<LazadaOrderClass>;
    let comp: LazadaOrderClass;
    let lazadaOrderServiceStub: SinonStubbedInstance<LazadaOrderService>;

    beforeEach(() => {
      lazadaOrderServiceStub = sinon.createStubInstance<LazadaOrderService>(LazadaOrderService);

      wrapper = shallowMount<LazadaOrderClass>(LazadaOrderUpdateComponent, {
        store,
        localVue,
        router,
        provide: {
          lazadaOrderService: () => lazadaOrderServiceStub,
          alertService: () => new AlertService(),

          shopService: () =>
            sinon.createStubInstance<ShopService>(ShopService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.lazadaOrder = entity;
        lazadaOrderServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(lazadaOrderServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.lazadaOrder = entity;
        lazadaOrderServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(lazadaOrderServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundLazadaOrder = { id: 123 };
        lazadaOrderServiceStub.find.resolves(foundLazadaOrder);
        lazadaOrderServiceStub.retrieve.resolves([foundLazadaOrder]);

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
