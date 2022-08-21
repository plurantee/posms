package com.flogramming.web.rest;

import com.flogramming.domain.Client;
import com.flogramming.domain.Shop;
import com.flogramming.repository.ClientShopRepository;
import com.flogramming.repository.ShopRepository;
import com.flogramming.service.ClientUserService;
import com.flogramming.web.rest.errors.BadRequestAlertException;
import org.checkerframework.checker.nullness.Opt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * REST controller for managing {@link Shop}.
 */
@RestController
@RequestMapping("/api/client")
@Transactional
public class ClientShopResource {

    private final Logger log = LoggerFactory.getLogger(ClientShopResource.class);

    private static final String ENTITY_NAME = "shop";
    @Autowired
    private ClientUserService clientUserService;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClientShopRepository shopRepository;

    public ClientShopResource(ClientShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    /**
     * {@code POST  /shops} : Create a new shop.
     *
     * @param shop the shop to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new shop, or with status {@code 400 (Bad Request)} if the shop has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/shops")
    public ResponseEntity<Shop> createShop(@RequestBody Shop shop) throws URISyntaxException {
        Client client = clientUserService.getCurrentUser().getClientCode();

        log.debug("REST request to save Shop : {}", shop);
        if (shop.getId() != null) {
            throw new BadRequestAlertException("A new shop cannot already have an ID", ENTITY_NAME, "idexists");
        }
        shop.setClientCode(client);
        Shop result = shopRepository.save(shop);
        return ResponseEntity
            .created(new URI("/api/shops/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /shops/:id} : Updates an existing shop.
     *
     * @param id the id of the shop to save.
     * @param shop the shop to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shop,
     * or with status {@code 400 (Bad Request)} if the shop is not valid,
     * or with status {@code 500 (Internal Server Error)} if the shop couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/shops/{id}")
    public ResponseEntity<Shop> updateShop(@PathVariable(value = "id", required = false) final Long id, @RequestBody Shop shop)
        throws URISyntaxException {
        Client client = clientUserService.getCurrentUser().getClientCode();
        log.debug("REST request to update Shop : {}, {}", id, shop);
        if (shop.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, shop.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!shopRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        shop.setClientCode(client);
        Shop result = shopRepository.save(shop);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, shop.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /shops/:id} : Partial updates given fields of an existing shop, field will ignore if it is null
     *
     * @param id the id of the shop to save.
     * @param shop the shop to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shop,
     * or with status {@code 400 (Bad Request)} if the shop is not valid,
     * or with status {@code 404 (Not Found)} if the shop is not found,
     * or with status {@code 500 (Internal Server Error)} if the shop couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/shops/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Shop> partialUpdateShop(@PathVariable(value = "id", required = false) final Long id, @RequestBody Shop shop)
        throws URISyntaxException {
        Client client = clientUserService.getCurrentUser().getClientCode();
        log.debug("REST request to partial update Shop partially : {}, {}", id, shop);
        if (shop.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, shop.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!shopRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Shop> result = shopRepository
            .findById(shop.getId())
            .map(existingShop -> {
                if (shop.getShopCode() != null) {
                    existingShop.setShopCode(shop.getShopCode());
                }
                if (shop.getShopName() != null) {
                    existingShop.setShopName(shop.getShopName());
                }
                if (shop.getShopType() != null) {
                    existingShop.setShopType(shop.getShopType());
                }

                return existingShop;
            })
            .map(shopRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, shop.getId().toString())
        );
    }

    /**
     * {@code GET  /shops} : get all the shops.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of shops in body.
     */
    @GetMapping("/shops")
    public ResponseEntity<List<Shop>> getAllShops(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Shops");
        Client client = clientUserService.getCurrentUser().getClientCode();
        Page<Shop> page = shopRepository.findByClientCode(client, pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /shops/:id} : get the "id" shop.
     *
     * @param id the id of the shop to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the shop, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/shops/{id}")
    public ResponseEntity<Shop> getShop(@PathVariable Long id) {
        log.debug("REST request to get Shop : {}", id);
        Client client = clientUserService.getCurrentUser().getClientCode();
        Optional<Shop> shop = shopRepository.findByIdAndClientCode(id, client);
        return ResponseUtil.wrapOrNotFound(shop);
    }

    /**
     * {@code DELETE  /shops/:id} : delete the "id" shop.
     *
     * @param id the id of the shop to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/shops/{id}")
    public ResponseEntity<Void> deleteShop(@PathVariable Long id) {
        log.debug("REST request to delete Shop : {}", id);
        Client client = clientUserService.getCurrentUser().getClientCode();
        var oShop = shopRepository.findByIdAndClientCode(id, client);
        if (!oShop.isPresent()) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        var shop = oShop.get();
        shopRepository.delete(shop);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
