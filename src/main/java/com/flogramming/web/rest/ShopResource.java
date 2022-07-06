package com.flogramming.web.rest;

import com.flogramming.domain.Shop;
import com.flogramming.domain.User;
import com.flogramming.domain.UserInfo;
import com.flogramming.repository.ShopRepository;
import com.flogramming.repository.UserInfoRepository;
import com.flogramming.service.UserService;
import com.flogramming.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.flogramming.domain.Shop}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ShopResource {

    private final Logger log = LoggerFactory.getLogger(ShopResource.class);

    private static final String ENTITY_NAME = "shop";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;


    private final ShopRepository shopRepository;
    private final UserService userService;
    private final UserInfoRepository userInfoRepository;

    public ShopResource(ShopRepository shopRepository, UserService userService, UserInfoRepository userInfoRepository) {
        this.shopRepository = shopRepository;
        this.userService = userService;
        this.userInfoRepository = userInfoRepository;
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
        User user = userService.getUserWithAuthorities().get();
        UserInfo userInfo = userInfoRepository.findByUser(user).get();
        log.debug("REST request to save Shop : {}", shop);
        if (shop.getId() != null) {
            throw new BadRequestAlertException("A new shop cannot already have an ID", ENTITY_NAME, "idexists");
        }
        shop.setShopCode(userInfo.getClientCode().getClientCode() + "-" + shop.getShopName().replaceAll("\\s+",""));
        if(!user.hasRole("ROLE_ADMIN")) {
            shop.setClientCode(userInfo.getClientCode());
        }
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
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of shops in body.
     */
    @GetMapping("/shops")
    public List<Shop> getAllShops() {
        log.debug("REST request to get all Shops");
        User user = userService.getUserWithAuthorities().get();
        UserInfo userInfo = userInfoRepository.findByUser(user).get();
        if (user.hasRole("ROLE_ADMIN")) {
            return shopRepository.findAll();
        } else {
            return shopRepository.findByClientCode(userInfo.getClientCode());
        }


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
        Optional<Shop> shop = shopRepository.findById(id);
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
        shopRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
