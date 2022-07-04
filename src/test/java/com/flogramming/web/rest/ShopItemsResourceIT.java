package com.flogramming.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.flogramming.IntegrationTest;
import com.flogramming.domain.ShopItems;
import com.flogramming.repository.ShopItemsRepository;
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
 * Integration tests for the {@link ShopItemsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ShopItemsResourceIT {

    private static final Integer DEFAULT_STOCK = 1;
    private static final Integer UPDATED_STOCK = 2;

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;

    private static final String ENTITY_API_URL = "/api/shop-items";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ShopItemsRepository shopItemsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restShopItemsMockMvc;

    private ShopItems shopItems;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ShopItems createEntity(EntityManager em) {
        ShopItems shopItems = new ShopItems().stock(DEFAULT_STOCK).price(DEFAULT_PRICE);
        return shopItems;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ShopItems createUpdatedEntity(EntityManager em) {
        ShopItems shopItems = new ShopItems().stock(UPDATED_STOCK).price(UPDATED_PRICE);
        return shopItems;
    }

    @BeforeEach
    public void initTest() {
        shopItems = createEntity(em);
    }

    @Test
    @Transactional
    void createShopItems() throws Exception {
        int databaseSizeBeforeCreate = shopItemsRepository.findAll().size();
        // Create the ShopItems
        restShopItemsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(shopItems)))
            .andExpect(status().isCreated());

        // Validate the ShopItems in the database
        List<ShopItems> shopItemsList = shopItemsRepository.findAll();
        assertThat(shopItemsList).hasSize(databaseSizeBeforeCreate + 1);
        ShopItems testShopItems = shopItemsList.get(shopItemsList.size() - 1);
        assertThat(testShopItems.getStock()).isEqualTo(DEFAULT_STOCK);
        assertThat(testShopItems.getPrice()).isEqualTo(DEFAULT_PRICE);
    }

    @Test
    @Transactional
    void createShopItemsWithExistingId() throws Exception {
        // Create the ShopItems with an existing ID
        shopItems.setId(1L);

        int databaseSizeBeforeCreate = shopItemsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restShopItemsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(shopItems)))
            .andExpect(status().isBadRequest());

        // Validate the ShopItems in the database
        List<ShopItems> shopItemsList = shopItemsRepository.findAll();
        assertThat(shopItemsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllShopItems() throws Exception {
        // Initialize the database
        shopItemsRepository.saveAndFlush(shopItems);

        // Get all the shopItemsList
        restShopItemsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shopItems.getId().intValue())))
            .andExpect(jsonPath("$.[*].stock").value(hasItem(DEFAULT_STOCK)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())));
    }

    @Test
    @Transactional
    void getShopItems() throws Exception {
        // Initialize the database
        shopItemsRepository.saveAndFlush(shopItems);

        // Get the shopItems
        restShopItemsMockMvc
            .perform(get(ENTITY_API_URL_ID, shopItems.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(shopItems.getId().intValue()))
            .andExpect(jsonPath("$.stock").value(DEFAULT_STOCK))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()));
    }

    @Test
    @Transactional
    void getNonExistingShopItems() throws Exception {
        // Get the shopItems
        restShopItemsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewShopItems() throws Exception {
        // Initialize the database
        shopItemsRepository.saveAndFlush(shopItems);

        int databaseSizeBeforeUpdate = shopItemsRepository.findAll().size();

        // Update the shopItems
        ShopItems updatedShopItems = shopItemsRepository.findById(shopItems.getId()).get();
        // Disconnect from session so that the updates on updatedShopItems are not directly saved in db
        em.detach(updatedShopItems);
        updatedShopItems.stock(UPDATED_STOCK).price(UPDATED_PRICE);

        restShopItemsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedShopItems.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedShopItems))
            )
            .andExpect(status().isOk());

        // Validate the ShopItems in the database
        List<ShopItems> shopItemsList = shopItemsRepository.findAll();
        assertThat(shopItemsList).hasSize(databaseSizeBeforeUpdate);
        ShopItems testShopItems = shopItemsList.get(shopItemsList.size() - 1);
        assertThat(testShopItems.getStock()).isEqualTo(UPDATED_STOCK);
        assertThat(testShopItems.getPrice()).isEqualTo(UPDATED_PRICE);
    }

    @Test
    @Transactional
    void putNonExistingShopItems() throws Exception {
        int databaseSizeBeforeUpdate = shopItemsRepository.findAll().size();
        shopItems.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShopItemsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, shopItems.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(shopItems))
            )
            .andExpect(status().isBadRequest());

        // Validate the ShopItems in the database
        List<ShopItems> shopItemsList = shopItemsRepository.findAll();
        assertThat(shopItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchShopItems() throws Exception {
        int databaseSizeBeforeUpdate = shopItemsRepository.findAll().size();
        shopItems.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restShopItemsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(shopItems))
            )
            .andExpect(status().isBadRequest());

        // Validate the ShopItems in the database
        List<ShopItems> shopItemsList = shopItemsRepository.findAll();
        assertThat(shopItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamShopItems() throws Exception {
        int databaseSizeBeforeUpdate = shopItemsRepository.findAll().size();
        shopItems.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restShopItemsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(shopItems)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ShopItems in the database
        List<ShopItems> shopItemsList = shopItemsRepository.findAll();
        assertThat(shopItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateShopItemsWithPatch() throws Exception {
        // Initialize the database
        shopItemsRepository.saveAndFlush(shopItems);

        int databaseSizeBeforeUpdate = shopItemsRepository.findAll().size();

        // Update the shopItems using partial update
        ShopItems partialUpdatedShopItems = new ShopItems();
        partialUpdatedShopItems.setId(shopItems.getId());

        partialUpdatedShopItems.price(UPDATED_PRICE);

        restShopItemsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedShopItems.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedShopItems))
            )
            .andExpect(status().isOk());

        // Validate the ShopItems in the database
        List<ShopItems> shopItemsList = shopItemsRepository.findAll();
        assertThat(shopItemsList).hasSize(databaseSizeBeforeUpdate);
        ShopItems testShopItems = shopItemsList.get(shopItemsList.size() - 1);
        assertThat(testShopItems.getStock()).isEqualTo(DEFAULT_STOCK);
        assertThat(testShopItems.getPrice()).isEqualTo(UPDATED_PRICE);
    }

    @Test
    @Transactional
    void fullUpdateShopItemsWithPatch() throws Exception {
        // Initialize the database
        shopItemsRepository.saveAndFlush(shopItems);

        int databaseSizeBeforeUpdate = shopItemsRepository.findAll().size();

        // Update the shopItems using partial update
        ShopItems partialUpdatedShopItems = new ShopItems();
        partialUpdatedShopItems.setId(shopItems.getId());

        partialUpdatedShopItems.stock(UPDATED_STOCK).price(UPDATED_PRICE);

        restShopItemsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedShopItems.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedShopItems))
            )
            .andExpect(status().isOk());

        // Validate the ShopItems in the database
        List<ShopItems> shopItemsList = shopItemsRepository.findAll();
        assertThat(shopItemsList).hasSize(databaseSizeBeforeUpdate);
        ShopItems testShopItems = shopItemsList.get(shopItemsList.size() - 1);
        assertThat(testShopItems.getStock()).isEqualTo(UPDATED_STOCK);
        assertThat(testShopItems.getPrice()).isEqualTo(UPDATED_PRICE);
    }

    @Test
    @Transactional
    void patchNonExistingShopItems() throws Exception {
        int databaseSizeBeforeUpdate = shopItemsRepository.findAll().size();
        shopItems.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShopItemsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, shopItems.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(shopItems))
            )
            .andExpect(status().isBadRequest());

        // Validate the ShopItems in the database
        List<ShopItems> shopItemsList = shopItemsRepository.findAll();
        assertThat(shopItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchShopItems() throws Exception {
        int databaseSizeBeforeUpdate = shopItemsRepository.findAll().size();
        shopItems.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restShopItemsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(shopItems))
            )
            .andExpect(status().isBadRequest());

        // Validate the ShopItems in the database
        List<ShopItems> shopItemsList = shopItemsRepository.findAll();
        assertThat(shopItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamShopItems() throws Exception {
        int databaseSizeBeforeUpdate = shopItemsRepository.findAll().size();
        shopItems.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restShopItemsMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(shopItems))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ShopItems in the database
        List<ShopItems> shopItemsList = shopItemsRepository.findAll();
        assertThat(shopItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteShopItems() throws Exception {
        // Initialize the database
        shopItemsRepository.saveAndFlush(shopItems);

        int databaseSizeBeforeDelete = shopItemsRepository.findAll().size();

        // Delete the shopItems
        restShopItemsMockMvc
            .perform(delete(ENTITY_API_URL_ID, shopItems.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ShopItems> shopItemsList = shopItemsRepository.findAll();
        assertThat(shopItemsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
