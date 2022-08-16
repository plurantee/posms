package com.flogramming.web.rest;

import com.flogramming.domain.ShopeeOrderPayments;
import com.flogramming.repository.ShopeeOrderPaymentsRepository;
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
 * REST controller for managing {@link com.flogramming.domain.ShopeeOrderPayments}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ShopeeOrderPaymentsResource {

    private final Logger log = LoggerFactory.getLogger(ShopeeOrderPaymentsResource.class);

    private static final String ENTITY_NAME = "shopeeOrderPayments";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ShopeeOrderPaymentsRepository shopeeOrderPaymentsRepository;

    public ShopeeOrderPaymentsResource(ShopeeOrderPaymentsRepository shopeeOrderPaymentsRepository) {
        this.shopeeOrderPaymentsRepository = shopeeOrderPaymentsRepository;
    }

    /**
     * {@code POST  /shopee-order-payments} : Create a new shopeeOrderPayments.
     *
     * @param shopeeOrderPayments the shopeeOrderPayments to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new shopeeOrderPayments, or with status {@code 400 (Bad Request)} if the shopeeOrderPayments has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/shopee-order-payments")
    public ResponseEntity<ShopeeOrderPayments> createShopeeOrderPayments(@RequestBody ShopeeOrderPayments shopeeOrderPayments)
        throws URISyntaxException {
        log.debug("REST request to save ShopeeOrderPayments : {}", shopeeOrderPayments);
        if (shopeeOrderPayments.getId() != null) {
            throw new BadRequestAlertException("A new shopeeOrderPayments cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShopeeOrderPayments result = shopeeOrderPaymentsRepository.save(shopeeOrderPayments);
        return ResponseEntity
            .created(new URI("/api/shopee-order-payments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /shopee-order-payments/:id} : Updates an existing shopeeOrderPayments.
     *
     * @param id the id of the shopeeOrderPayments to save.
     * @param shopeeOrderPayments the shopeeOrderPayments to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shopeeOrderPayments,
     * or with status {@code 400 (Bad Request)} if the shopeeOrderPayments is not valid,
     * or with status {@code 500 (Internal Server Error)} if the shopeeOrderPayments couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/shopee-order-payments/{id}")
    public ResponseEntity<ShopeeOrderPayments> updateShopeeOrderPayments(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ShopeeOrderPayments shopeeOrderPayments
    ) throws URISyntaxException {
        log.debug("REST request to update ShopeeOrderPayments : {}, {}", id, shopeeOrderPayments);
        if (shopeeOrderPayments.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, shopeeOrderPayments.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!shopeeOrderPaymentsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ShopeeOrderPayments result = shopeeOrderPaymentsRepository.save(shopeeOrderPayments);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, shopeeOrderPayments.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /shopee-order-payments/:id} : Partial updates given fields of an existing shopeeOrderPayments, field will ignore if it is null
     *
     * @param id the id of the shopeeOrderPayments to save.
     * @param shopeeOrderPayments the shopeeOrderPayments to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shopeeOrderPayments,
     * or with status {@code 400 (Bad Request)} if the shopeeOrderPayments is not valid,
     * or with status {@code 404 (Not Found)} if the shopeeOrderPayments is not found,
     * or with status {@code 500 (Internal Server Error)} if the shopeeOrderPayments couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/shopee-order-payments/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ShopeeOrderPayments> partialUpdateShopeeOrderPayments(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ShopeeOrderPayments shopeeOrderPayments
    ) throws URISyntaxException {
        log.debug("REST request to partial update ShopeeOrderPayments partially : {}, {}", id, shopeeOrderPayments);
        if (shopeeOrderPayments.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, shopeeOrderPayments.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!shopeeOrderPaymentsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ShopeeOrderPayments> result = shopeeOrderPaymentsRepository
            .findById(shopeeOrderPayments.getId())
            .map(existingShopeeOrderPayments -> {
                if (shopeeOrderPayments.getOrderId() != null) {
                    existingShopeeOrderPayments.setOrderId(shopeeOrderPayments.getOrderId());
                }
                if (shopeeOrderPayments.getRefundId() != null) {
                    existingShopeeOrderPayments.setRefundId(shopeeOrderPayments.getRefundId());
                }
                if (shopeeOrderPayments.getUsernameBuyer() != null) {
                    existingShopeeOrderPayments.setUsernameBuyer(shopeeOrderPayments.getUsernameBuyer());
                }
                if (shopeeOrderPayments.getOrderCreationDate() != null) {
                    existingShopeeOrderPayments.setOrderCreationDate(shopeeOrderPayments.getOrderCreationDate());
                }
                if (shopeeOrderPayments.getBuyerPaymentMethod() != null) {
                    existingShopeeOrderPayments.setBuyerPaymentMethod(shopeeOrderPayments.getBuyerPaymentMethod());
                }
                if (shopeeOrderPayments.getPayoutCompletedDate() != null) {
                    existingShopeeOrderPayments.setPayoutCompletedDate(shopeeOrderPayments.getPayoutCompletedDate());
                }
                if (shopeeOrderPayments.getOriginalProductPrice() != null) {
                    existingShopeeOrderPayments.setOriginalProductPrice(shopeeOrderPayments.getOriginalProductPrice());
                }
                if (shopeeOrderPayments.getSellerProductPromotion() != null) {
                    existingShopeeOrderPayments.setSellerProductPromotion(shopeeOrderPayments.getSellerProductPromotion());
                }
                if (shopeeOrderPayments.getRefundAmountToBuyer() != null) {
                    existingShopeeOrderPayments.setRefundAmountToBuyer(shopeeOrderPayments.getRefundAmountToBuyer());
                }
                if (shopeeOrderPayments.getProductDiscountRebateFromShopee() != null) {
                    existingShopeeOrderPayments.setProductDiscountRebateFromShopee(
                        shopeeOrderPayments.getProductDiscountRebateFromShopee()
                    );
                }
                if (shopeeOrderPayments.getSellerVoucherDiscount() != null) {
                    existingShopeeOrderPayments.setSellerVoucherDiscount(shopeeOrderPayments.getSellerVoucherDiscount());
                }
                if (shopeeOrderPayments.getSellerAbsorbedCoinCashback() != null) {
                    existingShopeeOrderPayments.setSellerAbsorbedCoinCashback(shopeeOrderPayments.getSellerAbsorbedCoinCashback());
                }
                if (shopeeOrderPayments.getBuyerPaidShippingFee() != null) {
                    existingShopeeOrderPayments.setBuyerPaidShippingFee(shopeeOrderPayments.getBuyerPaidShippingFee());
                }
                if (shopeeOrderPayments.getShippingFeeRebateFromShopee() != null) {
                    existingShopeeOrderPayments.setShippingFeeRebateFromShopee(shopeeOrderPayments.getShippingFeeRebateFromShopee());
                }
                if (shopeeOrderPayments.getThirdPartyLogisticsDefinedShippingFee() != null) {
                    existingShopeeOrderPayments.setThirdPartyLogisticsDefinedShippingFee(
                        shopeeOrderPayments.getThirdPartyLogisticsDefinedShippingFee()
                    );
                }
                if (shopeeOrderPayments.getReverseShippingFee() != null) {
                    existingShopeeOrderPayments.setReverseShippingFee(shopeeOrderPayments.getReverseShippingFee());
                }
                if (shopeeOrderPayments.getCommissionFee() != null) {
                    existingShopeeOrderPayments.setCommissionFee(shopeeOrderPayments.getCommissionFee());
                }
                if (shopeeOrderPayments.getServiceFee() != null) {
                    existingShopeeOrderPayments.setServiceFee(shopeeOrderPayments.getServiceFee());
                }
                if (shopeeOrderPayments.getTransactionFee() != null) {
                    existingShopeeOrderPayments.setTransactionFee(shopeeOrderPayments.getTransactionFee());
                }
                if (shopeeOrderPayments.getTotalReleasedAmount() != null) {
                    existingShopeeOrderPayments.setTotalReleasedAmount(shopeeOrderPayments.getTotalReleasedAmount());
                }
                if (shopeeOrderPayments.getSellerVoucherCode() != null) {
                    existingShopeeOrderPayments.setSellerVoucherCode(shopeeOrderPayments.getSellerVoucherCode());
                }
                if (shopeeOrderPayments.getLostCompensation() != null) {
                    existingShopeeOrderPayments.setLostCompensation(shopeeOrderPayments.getLostCompensation());
                }
                if (shopeeOrderPayments.getTotalActualWeightPerOrder() != null) {
                    existingShopeeOrderPayments.setTotalActualWeightPerOrder(shopeeOrderPayments.getTotalActualWeightPerOrder());
                }
                if (shopeeOrderPayments.getShippingFeePromotionBySeller() != null) {
                    existingShopeeOrderPayments.setShippingFeePromotionBySeller(shopeeOrderPayments.getShippingFeePromotionBySeller());
                }
                if (shopeeOrderPayments.getShippingProvider() != null) {
                    existingShopeeOrderPayments.setShippingProvider(shopeeOrderPayments.getShippingProvider());
                }
                if (shopeeOrderPayments.getCourierName() != null) {
                    existingShopeeOrderPayments.setCourierName(shopeeOrderPayments.getCourierName());
                }

                return existingShopeeOrderPayments;
            })
            .map(shopeeOrderPaymentsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, shopeeOrderPayments.getId().toString())
        );
    }

    /**
     * {@code GET  /shopee-order-payments} : get all the shopeeOrderPayments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of shopeeOrderPayments in body.
     */
    @GetMapping("/shopee-order-payments")
    public ResponseEntity<List<ShopeeOrderPayments>> getAllShopeeOrderPayments(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ShopeeOrderPayments");
        Page<ShopeeOrderPayments> page = shopeeOrderPaymentsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /shopee-order-payments/:id} : get the "id" shopeeOrderPayments.
     *
     * @param id the id of the shopeeOrderPayments to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the shopeeOrderPayments, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/shopee-order-payments/{id}")
    public ResponseEntity<ShopeeOrderPayments> getShopeeOrderPayments(@PathVariable Long id) {
        log.debug("REST request to get ShopeeOrderPayments : {}", id);
        Optional<ShopeeOrderPayments> shopeeOrderPayments = shopeeOrderPaymentsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(shopeeOrderPayments);
    }

    /**
     * {@code DELETE  /shopee-order-payments/:id} : delete the "id" shopeeOrderPayments.
     *
     * @param id the id of the shopeeOrderPayments to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/shopee-order-payments/{id}")
    public ResponseEntity<Void> deleteShopeeOrderPayments(@PathVariable Long id) {
        log.debug("REST request to delete ShopeeOrderPayments : {}", id);
        shopeeOrderPaymentsRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
