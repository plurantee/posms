package com.flogramming.web.rest;

import com.flogramming.domain.LazadaOrder;
import com.flogramming.repository.LazadaOrderRepository;
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
 * REST controller for managing {@link com.flogramming.domain.LazadaOrder}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class LazadaOrderResource {

    private final Logger log = LoggerFactory.getLogger(LazadaOrderResource.class);

    private static final String ENTITY_NAME = "lazadaOrder";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LazadaOrderRepository lazadaOrderRepository;

    public LazadaOrderResource(LazadaOrderRepository lazadaOrderRepository) {
        this.lazadaOrderRepository = lazadaOrderRepository;
    }

    /**
     * {@code POST  /lazada-orders} : Create a new lazadaOrder.
     *
     * @param lazadaOrder the lazadaOrder to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new lazadaOrder, or with status {@code 400 (Bad Request)} if the lazadaOrder has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/lazada-orders")
    public ResponseEntity<LazadaOrder> createLazadaOrder(@RequestBody LazadaOrder lazadaOrder) throws URISyntaxException {
        log.debug("REST request to save LazadaOrder : {}", lazadaOrder);
        if (lazadaOrder.getId() != null) {
            throw new BadRequestAlertException("A new lazadaOrder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LazadaOrder result = lazadaOrderRepository.save(lazadaOrder);
        return ResponseEntity
            .created(new URI("/api/lazada-orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /lazada-orders/:id} : Updates an existing lazadaOrder.
     *
     * @param id the id of the lazadaOrder to save.
     * @param lazadaOrder the lazadaOrder to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lazadaOrder,
     * or with status {@code 400 (Bad Request)} if the lazadaOrder is not valid,
     * or with status {@code 500 (Internal Server Error)} if the lazadaOrder couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/lazada-orders/{id}")
    public ResponseEntity<LazadaOrder> updateLazadaOrder(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LazadaOrder lazadaOrder
    ) throws URISyntaxException {
        log.debug("REST request to update LazadaOrder : {}, {}", id, lazadaOrder);
        if (lazadaOrder.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, lazadaOrder.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!lazadaOrderRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        LazadaOrder result = lazadaOrderRepository.save(lazadaOrder);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, lazadaOrder.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /lazada-orders/:id} : Partial updates given fields of an existing lazadaOrder, field will ignore if it is null
     *
     * @param id the id of the lazadaOrder to save.
     * @param lazadaOrder the lazadaOrder to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lazadaOrder,
     * or with status {@code 400 (Bad Request)} if the lazadaOrder is not valid,
     * or with status {@code 404 (Not Found)} if the lazadaOrder is not found,
     * or with status {@code 500 (Internal Server Error)} if the lazadaOrder couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/lazada-orders/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LazadaOrder> partialUpdateLazadaOrder(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LazadaOrder lazadaOrder
    ) throws URISyntaxException {
        log.debug("REST request to partial update LazadaOrder partially : {}, {}", id, lazadaOrder);
        if (lazadaOrder.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, lazadaOrder.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!lazadaOrderRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LazadaOrder> result = lazadaOrderRepository
            .findById(lazadaOrder.getId())
            .map(existingLazadaOrder -> {
                if (lazadaOrder.getOrderItemId() != null) {
                    existingLazadaOrder.setOrderItemId(lazadaOrder.getOrderItemId());
                }
                if (lazadaOrder.getOrderType() != null) {
                    existingLazadaOrder.setOrderType(lazadaOrder.getOrderType());
                }
                if (lazadaOrder.getGuarantee() != null) {
                    existingLazadaOrder.setGuarantee(lazadaOrder.getGuarantee());
                }
                if (lazadaOrder.getDeliveryType() != null) {
                    existingLazadaOrder.setDeliveryType(lazadaOrder.getDeliveryType());
                }
                if (lazadaOrder.getLazadaId() != null) {
                    existingLazadaOrder.setLazadaId(lazadaOrder.getLazadaId());
                }
                if (lazadaOrder.getSellerSku() != null) {
                    existingLazadaOrder.setSellerSku(lazadaOrder.getSellerSku());
                }
                if (lazadaOrder.getLazadaSku() != null) {
                    existingLazadaOrder.setLazadaSku(lazadaOrder.getLazadaSku());
                }
                if (lazadaOrder.getWareHouse() != null) {
                    existingLazadaOrder.setWareHouse(lazadaOrder.getWareHouse());
                }
                if (lazadaOrder.getCreateTime() != null) {
                    existingLazadaOrder.setCreateTime(lazadaOrder.getCreateTime());
                }
                if (lazadaOrder.getUpdateTime() != null) {
                    existingLazadaOrder.setUpdateTime(lazadaOrder.getUpdateTime());
                }
                if (lazadaOrder.getRtaSla() != null) {
                    existingLazadaOrder.setRtaSla(lazadaOrder.getRtaSla());
                }
                if (lazadaOrder.getTtsSla() != null) {
                    existingLazadaOrder.setTtsSla(lazadaOrder.getTtsSla());
                }
                if (lazadaOrder.getOrderNumber() != null) {
                    existingLazadaOrder.setOrderNumber(lazadaOrder.getOrderNumber());
                }
                if (lazadaOrder.getInvoiceRequired() != null) {
                    existingLazadaOrder.setInvoiceRequired(lazadaOrder.getInvoiceRequired());
                }
                if (lazadaOrder.getInvoiceNumber() != null) {
                    existingLazadaOrder.setInvoiceNumber(lazadaOrder.getInvoiceNumber());
                }
                if (lazadaOrder.getDeliveryDate() != null) {
                    existingLazadaOrder.setDeliveryDate(lazadaOrder.getDeliveryDate());
                }
                if (lazadaOrder.getCustomerName() != null) {
                    existingLazadaOrder.setCustomerName(lazadaOrder.getCustomerName());
                }
                if (lazadaOrder.getCustomerEmail() != null) {
                    existingLazadaOrder.setCustomerEmail(lazadaOrder.getCustomerEmail());
                }
                if (lazadaOrder.getNationalRegistrationNumber() != null) {
                    existingLazadaOrder.setNationalRegistrationNumber(lazadaOrder.getNationalRegistrationNumber());
                }
                if (lazadaOrder.getShippingName() != null) {
                    existingLazadaOrder.setShippingName(lazadaOrder.getShippingName());
                }
                if (lazadaOrder.getShippingAddress() != null) {
                    existingLazadaOrder.setShippingAddress(lazadaOrder.getShippingAddress());
                }
                if (lazadaOrder.getShippingAddress2() != null) {
                    existingLazadaOrder.setShippingAddress2(lazadaOrder.getShippingAddress2());
                }
                if (lazadaOrder.getShippingAddress3() != null) {
                    existingLazadaOrder.setShippingAddress3(lazadaOrder.getShippingAddress3());
                }
                if (lazadaOrder.getShippingAddress4() != null) {
                    existingLazadaOrder.setShippingAddress4(lazadaOrder.getShippingAddress4());
                }
                if (lazadaOrder.getShippingAddress5() != null) {
                    existingLazadaOrder.setShippingAddress5(lazadaOrder.getShippingAddress5());
                }
                if (lazadaOrder.getShippingPhone() != null) {
                    existingLazadaOrder.setShippingPhone(lazadaOrder.getShippingPhone());
                }
                if (lazadaOrder.getShippingPhone2() != null) {
                    existingLazadaOrder.setShippingPhone2(lazadaOrder.getShippingPhone2());
                }
                if (lazadaOrder.getShippingCity() != null) {
                    existingLazadaOrder.setShippingCity(lazadaOrder.getShippingCity());
                }
                if (lazadaOrder.getShippingPostCode() != null) {
                    existingLazadaOrder.setShippingPostCode(lazadaOrder.getShippingPostCode());
                }
                if (lazadaOrder.getShippingCountry() != null) {
                    existingLazadaOrder.setShippingCountry(lazadaOrder.getShippingCountry());
                }
                if (lazadaOrder.getShippingRegion() != null) {
                    existingLazadaOrder.setShippingRegion(lazadaOrder.getShippingRegion());
                }
                if (lazadaOrder.getBillingName() != null) {
                    existingLazadaOrder.setBillingName(lazadaOrder.getBillingName());
                }
                if (lazadaOrder.getBillingAddr() != null) {
                    existingLazadaOrder.setBillingAddr(lazadaOrder.getBillingAddr());
                }
                if (lazadaOrder.getBillingAddr2() != null) {
                    existingLazadaOrder.setBillingAddr2(lazadaOrder.getBillingAddr2());
                }
                if (lazadaOrder.getBillingAddr3() != null) {
                    existingLazadaOrder.setBillingAddr3(lazadaOrder.getBillingAddr3());
                }
                if (lazadaOrder.getBillingAddr4() != null) {
                    existingLazadaOrder.setBillingAddr4(lazadaOrder.getBillingAddr4());
                }
                if (lazadaOrder.getBillingAddr5() != null) {
                    existingLazadaOrder.setBillingAddr5(lazadaOrder.getBillingAddr5());
                }
                if (lazadaOrder.getBillingPhone() != null) {
                    existingLazadaOrder.setBillingPhone(lazadaOrder.getBillingPhone());
                }
                if (lazadaOrder.getBillingPhone2() != null) {
                    existingLazadaOrder.setBillingPhone2(lazadaOrder.getBillingPhone2());
                }
                if (lazadaOrder.getBillingCity() != null) {
                    existingLazadaOrder.setBillingCity(lazadaOrder.getBillingCity());
                }
                if (lazadaOrder.getBillingPostCode() != null) {
                    existingLazadaOrder.setBillingPostCode(lazadaOrder.getBillingPostCode());
                }
                if (lazadaOrder.getBillingCountry() != null) {
                    existingLazadaOrder.setBillingCountry(lazadaOrder.getBillingCountry());
                }
                if (lazadaOrder.getTaxCode() != null) {
                    existingLazadaOrder.setTaxCode(lazadaOrder.getTaxCode());
                }
                if (lazadaOrder.getBranchNumber() != null) {
                    existingLazadaOrder.setBranchNumber(lazadaOrder.getBranchNumber());
                }
                if (lazadaOrder.getTaxInvoiceRequested() != null) {
                    existingLazadaOrder.setTaxInvoiceRequested(lazadaOrder.getTaxInvoiceRequested());
                }
                if (lazadaOrder.getPayMethod() != null) {
                    existingLazadaOrder.setPayMethod(lazadaOrder.getPayMethod());
                }
                if (lazadaOrder.getPaidPrice() != null) {
                    existingLazadaOrder.setPaidPrice(lazadaOrder.getPaidPrice());
                }
                if (lazadaOrder.getUnitPrice() != null) {
                    existingLazadaOrder.setUnitPrice(lazadaOrder.getUnitPrice());
                }
                if (lazadaOrder.getSellerDiscountTotal() != null) {
                    existingLazadaOrder.setSellerDiscountTotal(lazadaOrder.getSellerDiscountTotal());
                }
                if (lazadaOrder.getShippingFee() != null) {
                    existingLazadaOrder.setShippingFee(lazadaOrder.getShippingFee());
                }
                if (lazadaOrder.getWalletCredit() != null) {
                    existingLazadaOrder.setWalletCredit(lazadaOrder.getWalletCredit());
                }
                if (lazadaOrder.getItemName() != null) {
                    existingLazadaOrder.setItemName(lazadaOrder.getItemName());
                }
                if (lazadaOrder.getVariation() != null) {
                    existingLazadaOrder.setVariation(lazadaOrder.getVariation());
                }
                if (lazadaOrder.getCdShippingProvider() != null) {
                    existingLazadaOrder.setCdShippingProvider(lazadaOrder.getCdShippingProvider());
                }
                if (lazadaOrder.getShippingProvider() != null) {
                    existingLazadaOrder.setShippingProvider(lazadaOrder.getShippingProvider());
                }
                if (lazadaOrder.getShipmentTypeName() != null) {
                    existingLazadaOrder.setShipmentTypeName(lazadaOrder.getShipmentTypeName());
                }
                if (lazadaOrder.getShippingProviderType() != null) {
                    existingLazadaOrder.setShippingProviderType(lazadaOrder.getShippingProviderType());
                }
                if (lazadaOrder.getCdTrackingCode() != null) {
                    existingLazadaOrder.setCdTrackingCode(lazadaOrder.getCdTrackingCode());
                }
                if (lazadaOrder.getTrackingCode() != null) {
                    existingLazadaOrder.setTrackingCode(lazadaOrder.getTrackingCode());
                }
                if (lazadaOrder.getTrackingUrl() != null) {
                    existingLazadaOrder.setTrackingUrl(lazadaOrder.getTrackingUrl());
                }
                if (lazadaOrder.getShippingProviderFM() != null) {
                    existingLazadaOrder.setShippingProviderFM(lazadaOrder.getShippingProviderFM());
                }
                if (lazadaOrder.getTrackingCodeFM() != null) {
                    existingLazadaOrder.setTrackingCodeFM(lazadaOrder.getTrackingCodeFM());
                }
                if (lazadaOrder.getTrackingUrlFM() != null) {
                    existingLazadaOrder.setTrackingUrlFM(lazadaOrder.getTrackingUrlFM());
                }
                if (lazadaOrder.getPromisedShippingTime() != null) {
                    existingLazadaOrder.setPromisedShippingTime(lazadaOrder.getPromisedShippingTime());
                }
                if (lazadaOrder.getPremium() != null) {
                    existingLazadaOrder.setPremium(lazadaOrder.getPremium());
                }
                if (lazadaOrder.getStatus() != null) {
                    existingLazadaOrder.setStatus(lazadaOrder.getStatus());
                }
                if (lazadaOrder.getBuyerFailedDeliveryReturnInitiator() != null) {
                    existingLazadaOrder.setBuyerFailedDeliveryReturnInitiator(lazadaOrder.getBuyerFailedDeliveryReturnInitiator());
                }
                if (lazadaOrder.getBuyerFailedDeliveryReason() != null) {
                    existingLazadaOrder.setBuyerFailedDeliveryReason(lazadaOrder.getBuyerFailedDeliveryReason());
                }
                if (lazadaOrder.getBuyerFailedDeliveryDetail() != null) {
                    existingLazadaOrder.setBuyerFailedDeliveryDetail(lazadaOrder.getBuyerFailedDeliveryDetail());
                }
                if (lazadaOrder.getBuyerFailedDeliveryUserName() != null) {
                    existingLazadaOrder.setBuyerFailedDeliveryUserName(lazadaOrder.getBuyerFailedDeliveryUserName());
                }
                if (lazadaOrder.getBundleId() != null) {
                    existingLazadaOrder.setBundleId(lazadaOrder.getBundleId());
                }
                if (lazadaOrder.getBundleDiscount() != null) {
                    existingLazadaOrder.setBundleDiscount(lazadaOrder.getBundleDiscount());
                }
                if (lazadaOrder.getRefundAmount() != null) {
                    existingLazadaOrder.setRefundAmount(lazadaOrder.getRefundAmount());
                }
                if (lazadaOrder.getDateUploaded() != null) {
                    existingLazadaOrder.setDateUploaded(lazadaOrder.getDateUploaded());
                }
                if (lazadaOrder.getDateReleasedOrCancelled() != null) {
                    existingLazadaOrder.setDateReleasedOrCancelled(lazadaOrder.getDateReleasedOrCancelled());
                }

                return existingLazadaOrder;
            })
            .map(lazadaOrderRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, lazadaOrder.getId().toString())
        );
    }

    /**
     * {@code GET  /lazada-orders} : get all the lazadaOrders.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lazadaOrders in body.
     */
    @GetMapping("/lazada-orders")
    public ResponseEntity<List<LazadaOrder>> getAllLazadaOrders(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of LazadaOrders");
        Page<LazadaOrder> page = lazadaOrderRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /lazada-orders/:id} : get the "id" lazadaOrder.
     *
     * @param id the id of the lazadaOrder to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lazadaOrder, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/lazada-orders/{id}")
    public ResponseEntity<LazadaOrder> getLazadaOrder(@PathVariable Long id) {
        log.debug("REST request to get LazadaOrder : {}", id);
        Optional<LazadaOrder> lazadaOrder = lazadaOrderRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(lazadaOrder);
    }

    /**
     * {@code DELETE  /lazada-orders/:id} : delete the "id" lazadaOrder.
     *
     * @param id the id of the lazadaOrder to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/lazada-orders/{id}")
    public ResponseEntity<Void> deleteLazadaOrder(@PathVariable Long id) {
        log.debug("REST request to delete LazadaOrder : {}", id);
        lazadaOrderRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
