/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import ShopItemsUpdateComponent from '@/entities/shop-items/shop-items-update.vue';
import ShopItemsClass from '@/entities/shop-items/shop-items-update.component';
import ShopItemsService from '@/entities/shop-items/shop-items.service';

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
  describe('ShopItems Management Update Component', () => {
    let wrapper: Wrapper<ShopItemsClass>;
    let comp: ShopItemsClass;
    let shopItemsServiceStub: SinonStubbedInstance<ShopItemsService>;

    beforeEach(() => {
      shopItemsServiceStub = sinon.createStubInstance<ShopItemsService>(ShopItemsService);

      wrapper = shallowMount<ShopItemsClass>(ShopItemsUpdateComponent, {
        store,
        localVue,
        router,
        provide: {
          shopItemsService: () => shopItemsServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.shopItems = entity;
        shopItemsServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(shopItemsServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.shopItems = entity;
        shopItemsServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(shopItemsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundShopItems = { id: 123 };
        shopItemsServiceStub.find.resolves(foundShopItems);
        shopItemsServiceStub.retrieve.resolves([foundShopItems]);

        // WHEN
        comp.beforeRouteEnter({ params: { shopItemsId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.shopItems).toBe(foundShopItems);
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
