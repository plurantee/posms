package com.flogramming.web.rest;

import com.flogramming.domain.ShopeeOrder;
import com.flogramming.repository.ShopeeOrderRepository;
import com.flogramming.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
 * REST controller for managing {@link com.flogramming.domain.ShopeeOrder}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ShopeeOrderResource {

    private static final String ENTITY_NAME = "shopeeOrder";
    private final Logger log = LoggerFactory.getLogger(ShopeeOrderResource.class);
    private final ShopeeOrderRepository shopeeOrderRepository;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public ShopeeOrderResource(ShopeeOrderRepository shopeeOrderRepository) {
        this.shopeeOrderRepository = shopeeOrderRepository;
    }

    /**
     * {@code POST  /shopee-orders} : Create a new shopeeOrder.
     *
     * @param shopeeOrder the shopeeOrder to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new shopeeOrder, or
     * with status {@code 400 (Bad Request)} if the shopeeOrder has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/shopee-orders")
    public ResponseEntity<ShopeeOrder> createShopeeOrder(@RequestBody ShopeeOrder shopeeOrder) throws URISyntaxException {
        log.debug("REST request to save ShopeeOrder : {}", shopeeOrder);
        if (shopeeOrder.getId() != null) {
            throw new BadRequestAlertException("A new shopeeOrder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShopeeOrder result = shopeeOrderRepository.save(shopeeOrder);
        return ResponseEntity
            .created(new URI("/api/shopee-orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME,
                result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /shopee-orders/:id} : Updates an existing shopeeOrder.
     *
     * @param id          the id of the shopeeOrder to save.
     * @param shopeeOrder the shopeeOrder to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shopeeOrder,
     * or with status {@code 400 (Bad Request)} if the shopeeOrder is not valid,
     * or with status {@code 500 (Internal Server Error)} if the shopeeOrder couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/shopee-orders/{id}")
    public ResponseEntity<ShopeeOrder> updateShopeeOrder(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ShopeeOrder shopeeOrder
    ) throws URISyntaxException {
        log.debug("REST request to update ShopeeOrder : {}, {}", id, shopeeOrder);
        if (shopeeOrder.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, shopeeOrder.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!shopeeOrderRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ShopeeOrder result = shopeeOrderRepository.save(shopeeOrder);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME,
                shopeeOrder.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /shopee-orders/:id} : Partial updates given fields of an existing shopeeOrder, field will ignore
     * if it is null
     *
     * @param id          the id of the shopeeOrder to save.
     * @param shopeeOrder the shopeeOrder to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shopeeOrder,
     * or with status {@code 400 (Bad Request)} if the shopeeOrder is not valid,
     * or with status {@code 404 (Not Found)} if the shopeeOrder is not found,
     * or with status {@code 500 (Internal Server Error)} if the shopeeOrder couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/shopee-orders/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<ShopeeOrder> partialUpdateShopeeOrder(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ShopeeOrder shopeeOrder
    ) throws URISyntaxException {
        log.debug("REST request to partial update ShopeeOrder partially : {}, {}", id, shopeeOrder);
        if (shopeeOrder.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, shopeeOrder.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!shopeeOrderRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ShopeeOrder> result = shopeeOrderRepository
            .findById(shopeeOrder.getId())
            .map(existingShopeeOrder -> {
                if (shopeeOrder.getOrderId() != null) {
                    existingShopeeOrder.setOrderId(shopeeOrder.getOrderId());
                }
                if (shopeeOrder.getOrderStatus() != null) {
                    existingShopeeOrder.setOrderStatus(shopeeOrder.getOrderStatus());
                }
                if (shopeeOrder.getReturnRefundStatus() != null) {
                    existingShopeeOrder.setReturnRefundStatus(shopeeOrder.getReturnRefundStatus());
                }
                if (shopeeOrder.getTrackingNumber() != null) {
                    existingShopeeOrder.setTrackingNumber(shopeeOrder.getTrackingNumber());
                }
                if (shopeeOrder.getShippingOption() != null) {
                    existingShopeeOrder.setShippingOption(shopeeOrder.getShippingOption());
                }
                if (shopeeOrder.getShipmentMethod() != null) {
                    existingShopeeOrder.setShipmentMethod(shopeeOrder.getShipmentMethod());
                }
                if (shopeeOrder.getEstimatedShipOutDate() != null) {
                    existingShopeeOrder.setEstimatedShipOutDate(shopeeOrder.getEstimatedShipOutDate());
                }
                if (shopeeOrder.getShipTime() != null) {
                    existingShopeeOrder.setShipTime(shopeeOrder.getShipTime());
                }
                if (shopeeOrder.getOrderCreationDate() != null) {
                    existingShopeeOrder.setOrderCreationDate(shopeeOrder.getOrderCreationDate());
                }
                if (shopeeOrder.getOrderPaidTime() != null) {
                    existingShopeeOrder.setOrderPaidTime(shopeeOrder.getOrderPaidTime());
                }
                if (shopeeOrder.getParentSkuReferenceNo() != null) {
                    existingShopeeOrder.setParentSkuReferenceNo(shopeeOrder.getParentSkuReferenceNo());
                }
                if (shopeeOrder.getProductName() != null) {
                    existingShopeeOrder.setProductName(shopeeOrder.getProductName());
                }
                if (shopeeOrder.getSkuReferenceNo() != null) {
                    existingShopeeOrder.setSkuReferenceNo(shopeeOrder.getSkuReferenceNo());
                }
                if (shopeeOrder.getVariationName() != null) {
                    existingShopeeOrder.setVariationName(shopeeOrder.getVariationName());
                }
                if (shopeeOrder.getOriginalPrice() != null) {
                    existingShopeeOrder.setOriginalPrice(shopeeOrder.getOriginalPrice());
                }
                if (shopeeOrder.getDealPrice() != null) {
                    existingShopeeOrder.setDealPrice(shopeeOrder.getDealPrice());
                }
                if (shopeeOrder.getQuantity() != null) {
                    existingShopeeOrder.setQuantity(shopeeOrder.getQuantity());
                }
                if (shopeeOrder.getProductSubtotal() != null) {
                    existingShopeeOrder.setProductSubtotal(shopeeOrder.getProductSubtotal());
                }
                if (shopeeOrder.getTotalDiscount() != null) {
                    existingShopeeOrder.setTotalDiscount(shopeeOrder.getTotalDiscount());
                }
                if (shopeeOrder.getPriceDiscountFromSeller() != null) {
                    existingShopeeOrder.setPriceDiscountFromSeller(shopeeOrder.getPriceDiscountFromSeller());
                }
                if (shopeeOrder.getShopeeRebate() != null) {
                    existingShopeeOrder.setShopeeRebate(shopeeOrder.getShopeeRebate());
                }
                if (shopeeOrder.getSkuTotalWeight() != null) {
                    existingShopeeOrder.setSkuTotalWeight(shopeeOrder.getSkuTotalWeight());
                }
                if (shopeeOrder.getNumberOfItemsInOrder() != null) {
                    existingShopeeOrder.setNumberOfItemsInOrder(shopeeOrder.getNumberOfItemsInOrder());
                }
                if (shopeeOrder.getOrderTotalWeight() != null) {
                    existingShopeeOrder.setOrderTotalWeight(shopeeOrder.getOrderTotalWeight());
                }
                if (shopeeOrder.getSellerVoucher() != null) {
                    existingShopeeOrder.setSellerVoucher(shopeeOrder.getSellerVoucher());
                }
                if (shopeeOrder.getSellerAbsorbedCoinCashback() != null) {
                    existingShopeeOrder.setSellerAbsorbedCoinCashback(shopeeOrder.getSellerAbsorbedCoinCashback());
                }
                if (shopeeOrder.getShopeeVoucher() != null) {
                    existingShopeeOrder.setShopeeVoucher(shopeeOrder.getShopeeVoucher());
                }
                if (shopeeOrder.getBundleDealsIndicatorYN() != null) {
                    existingShopeeOrder.setBundleDealsIndicatorYN(shopeeOrder.getBundleDealsIndicatorYN());
                }
                if (shopeeOrder.getShopeeBundleDiscount() != null) {
                    existingShopeeOrder.setShopeeBundleDiscount(shopeeOrder.getShopeeBundleDiscount());
                }
                if (shopeeOrder.getSellerBundleDiscount() != null) {
                    existingShopeeOrder.setSellerBundleDiscount(shopeeOrder.getSellerBundleDiscount());
                }
                if (shopeeOrder.getShopeeCoinsOffset() != null) {
                    existingShopeeOrder.setShopeeCoinsOffset(shopeeOrder.getShopeeCoinsOffset());
                }
                if (shopeeOrder.getCreditCardDiscountTotal() != null) {
                    existingShopeeOrder.setCreditCardDiscountTotal(shopeeOrder.getCreditCardDiscountTotal());
                }
                if (shopeeOrder.getProductsPricePaidByBuyer() != null) {
                    existingShopeeOrder.setProductsPricePaidByBuyer(shopeeOrder.getProductsPricePaidByBuyer());
                }
                if (shopeeOrder.getBuyerPaidShippingFee() != null) {
                    existingShopeeOrder.setBuyerPaidShippingFee(shopeeOrder.getBuyerPaidShippingFee());
                }
                if (shopeeOrder.getShippingRebateEstimate() != null) {
                    existingShopeeOrder.setShippingRebateEstimate(shopeeOrder.getShippingRebateEstimate());
                }
                if (shopeeOrder.getReverseShippingFee() != null) {
                    existingShopeeOrder.setReverseShippingFee(shopeeOrder.getReverseShippingFee());
                }
                if (shopeeOrder.getServiceFee() != null) {
                    existingShopeeOrder.setServiceFee(shopeeOrder.getServiceFee());
                }
                if (shopeeOrder.getGrandTotal() != null) {
                    existingShopeeOrder.setGrandTotal(shopeeOrder.getGrandTotal());
                }
                if (shopeeOrder.getEstimatedShippingFee() != null) {
                    existingShopeeOrder.setEstimatedShippingFee(shopeeOrder.getEstimatedShippingFee());
                }
                if (shopeeOrder.getUsernameBuyer() != null) {
                    existingShopeeOrder.setUsernameBuyer(shopeeOrder.getUsernameBuyer());
                }
                if (shopeeOrder.getReceiverName() != null) {
                    existingShopeeOrder.setReceiverName(shopeeOrder.getReceiverName());
                }
                if (shopeeOrder.getPhoneNumber() != null) {
                    existingShopeeOrder.setPhoneNumber(shopeeOrder.getPhoneNumber());
                }
                if (shopeeOrder.getDeliveryAddress() != null) {
                    existingShopeeOrder.setDeliveryAddress(shopeeOrder.getDeliveryAddress());
                }
                if (shopeeOrder.getTown() != null) {
                    existingShopeeOrder.setTown(shopeeOrder.getTown());
                }
                if (shopeeOrder.getDistrict() != null) {
                    existingShopeeOrder.setDistrict(shopeeOrder.getDistrict());
                }
                if (shopeeOrder.getProvince() != null) {
                    existingShopeeOrder.setProvince(shopeeOrder.getProvince());
                }
                if (shopeeOrder.getRegion() != null) {
                    existingShopeeOrder.setRegion(shopeeOrder.getRegion());
                }
                if (shopeeOrder.getCountry() != null) {
                    existingShopeeOrder.setCountry(shopeeOrder.getCountry());
                }
                if (shopeeOrder.getZipCode() != null) {
                    existingShopeeOrder.setZipCode(shopeeOrder.getZipCode());
                }
                if (shopeeOrder.getRemarkFromBuyer() != null) {
                    existingShopeeOrder.setRemarkFromBuyer(shopeeOrder.getRemarkFromBuyer());
                }
                if (shopeeOrder.getOrderCompleteTime() != null) {
                    existingShopeeOrder.setOrderCompleteTime(shopeeOrder.getOrderCompleteTime());
                }
                if (shopeeOrder.getNote() != null) {
                    existingShopeeOrder.setNote(shopeeOrder.getNote());
                }

                return existingShopeeOrder;
            })
            .map(shopeeOrderRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, shopeeOrder.getId().toString())
        );
    }

    /**
     * {@code GET  /shopee-orders} : get all the shopeeOrders.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of shopeeOrders in body.
     */
    @GetMapping("/shopee-orders")
    public ResponseEntity<List<ShopeeOrder>> getAllShopeeOrders(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of ShopeeOrders");
        Page<ShopeeOrder> page = shopeeOrderRepository.findAll(pageable);
        HttpHeaders headers =
            PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /shopee-orders/:id} : get the "id" shopeeOrder.
     *
     * @param id the id of the shopeeOrder to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the shopeeOrder, or with status
     * {@code 404 (Not Found)}.
     */
    @GetMapping("/shopee-orders/{id}")
    public ResponseEntity<ShopeeOrder> getShopeeOrder(@PathVariable Long id) {
        log.debug("REST request to get ShopeeOrder : {}", id);
        Optional<ShopeeOrder> shopeeOrder = shopeeOrderRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(shopeeOrder);
    }

    /**
     * {@code DELETE  /shopee-orders/:id} : delete the "id" shopeeOrder.
     *
     * @param id the id of the shopeeOrder to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/shopee-orders/{id}")
    public ResponseEntity<Void> deleteShopeeOrder(@PathVariable Long id) {
        log.debug("REST request to delete ShopeeOrder : {}", id);
        shopeeOrderRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
