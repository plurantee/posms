package com.flogramming.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.flogramming.IntegrationTest;
import com.flogramming.domain.Shop;
import com.flogramming.domain.enumeration.ShopType;
import com.flogramming.repository.ShopRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ShopResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ShopResourceIT {

    private static final String DEFAULT_SHOP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SHOP_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SHOP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHOP_NAME = "BBBBBBBBBB";

    private static final ShopType DEFAULT_SHOP_TYPE = ShopType.LAZADA;
    private static final ShopType UPDATED_SHOP_TYPE = ShopType.SHOPEE;

    private static final String ENTITY_API_URL = "/api/shops";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restShopMockMvc;

    private Shop shop;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Shop createEntity(EntityManager em) {
        Shop shop = new Shop().shopCode(DEFAULT_SHOP_CODE).shopName(DEFAULT_SHOP_NAME).shopType(DEFAULT_SHOP_TYPE);
        return shop;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Shop createUpdatedEntity(EntityManager em) {
        Shop shop = new Shop().shopCode(UPDATED_SHOP_CODE).shopName(UPDATED_SHOP_NAME).shopType(UPDATED_SHOP_TYPE);
        return shop;
    }

    @BeforeEach
    public void initTest() {
        shop = createEntity(em);
    }

    @Test
    @Transactional
    void createShop() throws Exception {
        int databaseSizeBeforeCreate = shopRepository.findAll().size();
        // Create the Shop
        restShopMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(shop)))
            .andExpect(status().isCreated());

        // Validate the Shop in the database
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeCreate + 1);
        Shop testShop = shopList.get(shopList.size() - 1);
        assertThat(testShop.getShopCode()).isEqualTo(DEFAULT_SHOP_CODE);
        assertThat(testShop.getShopName()).isEqualTo(DEFAULT_SHOP_NAME);
        assertThat(testShop.getShopType()).isEqualTo(DEFAULT_SHOP_TYPE);
    }

    @Test
    @Transactional
    void createShopWithExistingId() throws Exception {
        // Create the Shop with an existing ID
        shop.setId(1L);

        int databaseSizeBeforeCreate = shopRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restShopMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(shop)))
            .andExpect(status().isBadRequest());

        // Validate the Shop in the database
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllShops() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get all the shopList
        restShopMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shop.getId().intValue())))
            .andExpect(jsonPath("$.[*].shopCode").value(hasItem(DEFAULT_SHOP_CODE)))
            .andExpect(jsonPath("$.[*].shopName").value(hasItem(DEFAULT_SHOP_NAME)))
            .andExpect(jsonPath("$.[*].shopType").value(hasItem(DEFAULT_SHOP_TYPE.toString())));
    }

    @Test
    @Transactional
    void getShop() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        // Get the shop
        restShopMockMvc
            .perform(get(ENTITY_API_URL_ID, shop.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(shop.getId().intValue()))
            .andExpect(jsonPath("$.shopCode").value(DEFAULT_SHOP_CODE))
            .andExpect(jsonPath("$.shopName").value(DEFAULT_SHOP_NAME))
            .andExpect(jsonPath("$.shopType").value(DEFAULT_SHOP_TYPE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingShop() throws Exception {
        // Get the shop
        restShopMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewShop() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        int databaseSizeBeforeUpdate = shopRepository.findAll().size();

        // Update the shop
        Shop updatedShop = shopRepository.findById(shop.getId()).get();
        // Disconnect from session so that the updates on updatedShop are not directly saved in db
        em.detach(updatedShop);
        updatedShop.shopCode(UPDATED_SHOP_CODE).shopName(UPDATED_SHOP_NAME).shopType(UPDATED_SHOP_TYPE);

        restShopMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedShop.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedShop))
            )
            .andExpect(status().isOk());

        // Validate the Shop in the database
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeUpdate);
        Shop testShop = shopList.get(shopList.size() - 1);
        assertThat(testShop.getShopCode()).isEqualTo(UPDATED_SHOP_CODE);
        assertThat(testShop.getShopName()).isEqualTo(UPDATED_SHOP_NAME);
        assertThat(testShop.getShopType()).isEqualTo(UPDATED_SHOP_TYPE);
    }

    @Test
    @Transactional
    void putNonExistingShop() throws Exception {
        int databaseSizeBeforeUpdate = shopRepository.findAll().size();
        shop.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShopMockMvc
            .perform(
                put(ENTITY_API_URL_ID, shop.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(shop))
            )
            .andExpect(status().isBadRequest());

        // Validate the Shop in the database
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchShop() throws Exception {
        int databaseSizeBeforeUpdate = shopRepository.findAll().size();
        shop.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restShopMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(shop))
            )
            .andExpect(status().isBadRequest());

        // Validate the Shop in the database
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamShop() throws Exception {
        int databaseSizeBeforeUpdate = shopRepository.findAll().size();
        shop.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restShopMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(shop)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Shop in the database
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateShopWithPatch() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        int databaseSizeBeforeUpdate = shopRepository.findAll().size();

        // Update the shop using partial update
        Shop partialUpdatedShop = new Shop();
        partialUpdatedShop.setId(shop.getId());

        partialUpdatedShop.shopType(UPDATED_SHOP_TYPE);

        restShopMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedShop.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedShop))
            )
            .andExpect(status().isOk());

        // Validate the Shop in the database
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeUpdate);
        Shop testShop = shopList.get(shopList.size() - 1);
        assertThat(testShop.getShopCode()).isEqualTo(DEFAULT_SHOP_CODE);
        assertThat(testShop.getShopName()).isEqualTo(DEFAULT_SHOP_NAME);
        assertThat(testShop.getShopType()).isEqualTo(UPDATED_SHOP_TYPE);
    }

    @Test
    @Transactional
    void fullUpdateShopWithPatch() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        int databaseSizeBeforeUpdate = shopRepository.findAll().size();

        // Update the shop using partial update
        Shop partialUpdatedShop = new Shop();
        partialUpdatedShop.setId(shop.getId());

        partialUpdatedShop.shopCode(UPDATED_SHOP_CODE).shopName(UPDATED_SHOP_NAME).shopType(UPDATED_SHOP_TYPE);

        restShopMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedShop.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedShop))
            )
            .andExpect(status().isOk());

        // Validate the Shop in the database
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeUpdate);
        Shop testShop = shopList.get(shopList.size() - 1);
        assertThat(testShop.getShopCode()).isEqualTo(UPDATED_SHOP_CODE);
        assertThat(testShop.getShopName()).isEqualTo(UPDATED_SHOP_NAME);
        assertThat(testShop.getShopType()).isEqualTo(UPDATED_SHOP_TYPE);
    }

    @Test
    @Transactional
    void patchNonExistingShop() throws Exception {
        int databaseSizeBeforeUpdate = shopRepository.findAll().size();
        shop.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShopMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, shop.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(shop))
            )
            .andExpect(status().isBadRequest());

        // Validate the Shop in the database
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchShop() throws Exception {
        int databaseSizeBeforeUpdate = shopRepository.findAll().size();
        shop.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restShopMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(shop))
            )
            .andExpect(status().isBadRequest());

        // Validate the Shop in the database
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamShop() throws Exception {
        int databaseSizeBeforeUpdate = shopRepository.findAll().size();
        shop.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restShopMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(shop)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Shop in the database
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteShop() throws Exception {
        // Initialize the database
        shopRepository.saveAndFlush(shop);

        int databaseSizeBeforeDelete = shopRepository.findAll().size();

        // Delete the shop
        restShopMockMvc
            .perform(delete(ENTITY_API_URL_ID, shop.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Shop> shopList = shopRepository.findAll();
        assertThat(shopList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
