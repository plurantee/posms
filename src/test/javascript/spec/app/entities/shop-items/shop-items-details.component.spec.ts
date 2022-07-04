/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import ShopItemsDetailComponent from '@/entities/shop-items/shop-items-details.vue';
import ShopItemsClass from '@/entities/shop-items/shop-items-details.component';
import ShopItemsService from '@/entities/shop-items/shop-items.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('ShopItems Management Detail Component', () => {
    let wrapper: Wrapper<ShopItemsClass>;
    let comp: ShopItemsClass;
    let shopItemsServiceStub: SinonStubbedInstance<ShopItemsService>;

    beforeEach(() => {
      shopItemsServiceStub = sinon.createStubInstance<ShopItemsService>(ShopItemsService);

      wrapper = shallowMount<ShopItemsClass>(ShopItemsDetailComponent, {
        store,
        localVue,
        router,
        provide: { shopItemsService: () => shopItemsServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundShopItems = { id: 123 };
        shopItemsServiceStub.find.resolves(foundShopItems);

        // WHEN
        comp.retrieveShopItems(123);
        await comp.$nextTick();

        // THEN
        expect(comp.shopItems).toBe(foundShopItems);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundShopItems = { id: 123 };
        shopItemsServiceStub.find.resolves(foundShopItems);

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
