package com.flogramming.web.rest;

import com.flogramming.domain.ShopItems;
import com.flogramming.repository.ShopItemsRepository;
import com.flogramming.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link com.flogramming.domain.ShopItems}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ShopItemsResource {

    private final Logger log = LoggerFactory.getLogger(ShopItemsResource.class);

    private static final String ENTITY_NAME = "shopItems";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ShopItemsRepository shopItemsRepository;

    public ShopItemsResource(ShopItemsRepository shopItemsRepository) {
        this.shopItemsRepository = shopItemsRepository;
    }

    /**
     * {@code POST  /shop-items} : Create a new shopItems.
     *
     * @param shopItems the shopItems to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new shopItems, or with status {@code 400 (Bad Request)} if the shopItems has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/shop-items")
    public ResponseEntity<ShopItems> createShopItems(@RequestBody ShopItems shopItems) throws URISyntaxException {
        log.debug("REST request to save ShopItems : {}", shopItems);
        if (shopItems.getId() != null) {
            throw new BadRequestAlertException("A new shopItems cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShopItems result = shopItemsRepository.save(shopItems);
        return ResponseEntity
            .created(new URI("/api/shop-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /shop-items/:id} : Updates an existing shopItems.
     *
     * @param id the id of the shopItems to save.
     * @param shopItems the shopItems to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shopItems,
     * or with status {@code 400 (Bad Request)} if the shopItems is not valid,
     * or with status {@code 500 (Internal Server Error)} if the shopItems couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/shop-items/{id}")
    public ResponseEntity<ShopItems> updateShopItems(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ShopItems shopItems
    ) throws URISyntaxException {
        log.debug("REST request to update ShopItems : {}, {}", id, shopItems);
        if (shopItems.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, shopItems.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!shopItemsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ShopItems result = shopItemsRepository.save(shopItems);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, shopItems.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /shop-items/:id} : Partial updates given fields of an existing shopItems, field will ignore if it is null
     *
     * @param id the id of the shopItems to save.
     * @param shopItems the shopItems to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shopItems,
     * or with status {@code 400 (Bad Request)} if the shopItems is not valid,
     * or with status {@code 404 (Not Found)} if the shopItems is not found,
     * or with status {@code 500 (Internal Server Error)} if the shopItems couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/shop-items/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ShopItems> partialUpdateShopItems(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ShopItems shopItems
    ) throws URISyntaxException {
        log.debug("REST request to partial update ShopItems partially : {}, {}", id, shopItems);
        if (shopItems.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, shopItems.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!shopItemsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ShopItems> result = shopItemsRepository
            .findById(shopItems.getId())
            .map(existingShopItems -> {
                if (shopItems.getStock() != null) {
                    existingShopItems.setStock(shopItems.getStock());
                }
                if (shopItems.getPrice() != null) {
                    existingShopItems.setPrice(shopItems.getPrice());
                }

                return existingShopItems;
            })
            .map(shopItemsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, shopItems.getId().toString())
        );
    }

    /**
     * {@code GET  /shop-items} : get all the shopItems.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of shopItems in body.
     */
    @GetMapping("/shop-items")
    public ResponseEntity<List<ShopItems>> getAllShopItems(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of ShopItems");
        Page<ShopItems> page = shopItemsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /shop-items/:id} : get the "id" shopItems.
     *
     * @param id the id of the shopItems to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the shopItems, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/shop-items/{id}")
    public ResponseEntity<ShopItems> getShopItems(@PathVariable Long id) {
        log.debug("REST request to get ShopItems : {}", id);
        Optional<ShopItems> shopItems = shopItemsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(shopItems);
    }

    /**
     * {@code DELETE  /shop-items/:id} : delete the "id" shopItems.
     *
     * @param id the id of the shopItems to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/shop-items/{id}")
    public ResponseEntity<Void> deleteShopItems(@PathVariable Long id) {
        log.debug("REST request to delete ShopItems : {}", id);
        shopItemsRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
