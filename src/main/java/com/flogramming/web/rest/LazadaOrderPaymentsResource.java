package com.flogramming.web.rest;

import com.flogramming.domain.LazadaOrderPayments;
import com.flogramming.repository.LazadaOrderPaymentsRepository;
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
 * REST controller for managing {@link com.flogramming.domain.LazadaOrderPayments}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class LazadaOrderPaymentsResource {

    private final Logger log = LoggerFactory.getLogger(LazadaOrderPaymentsResource.class);

    private static final String ENTITY_NAME = "lazadaOrderPayments";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LazadaOrderPaymentsRepository lazadaOrderPaymentsRepository;

    public LazadaOrderPaymentsResource(LazadaOrderPaymentsRepository lazadaOrderPaymentsRepository) {
        this.lazadaOrderPaymentsRepository = lazadaOrderPaymentsRepository;
    }

    /**
     * {@code POST  /lazada-order-payments} : Create a new lazadaOrderPayments.
     *
     * @param lazadaOrderPayments the lazadaOrderPayments to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new lazadaOrderPayments, or with status {@code 400 (Bad Request)} if the lazadaOrderPayments has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/lazada-order-payments")
    public ResponseEntity<LazadaOrderPayments> createLazadaOrderPayments(@RequestBody LazadaOrderPayments lazadaOrderPayments)
        throws URISyntaxException {
        log.debug("REST request to save LazadaOrderPayments : {}", lazadaOrderPayments);
        if (lazadaOrderPayments.getId() != null) {
            throw new BadRequestAlertException("A new lazadaOrderPayments cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LazadaOrderPayments result = lazadaOrderPaymentsRepository.save(lazadaOrderPayments);
        return ResponseEntity
            .created(new URI("/api/lazada-order-payments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /lazada-order-payments/:id} : Updates an existing lazadaOrderPayments.
     *
     * @param id the id of the lazadaOrderPayments to save.
     * @param lazadaOrderPayments the lazadaOrderPayments to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lazadaOrderPayments,
     * or with status {@code 400 (Bad Request)} if the lazadaOrderPayments is not valid,
     * or with status {@code 500 (Internal Server Error)} if the lazadaOrderPayments couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/lazada-order-payments/{id}")
    public ResponseEntity<LazadaOrderPayments> updateLazadaOrderPayments(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LazadaOrderPayments lazadaOrderPayments
    ) throws URISyntaxException {
        log.debug("REST request to update LazadaOrderPayments : {}, {}", id, lazadaOrderPayments);
        if (lazadaOrderPayments.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, lazadaOrderPayments.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!lazadaOrderPaymentsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        LazadaOrderPayments result = lazadaOrderPaymentsRepository.save(lazadaOrderPayments);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, lazadaOrderPayments.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /lazada-order-payments/:id} : Partial updates given fields of an existing lazadaOrderPayments, field will ignore if it is null
     *
     * @param id the id of the lazadaOrderPayments to save.
     * @param lazadaOrderPayments the lazadaOrderPayments to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lazadaOrderPayments,
     * or with status {@code 400 (Bad Request)} if the lazadaOrderPayments is not valid,
     * or with status {@code 404 (Not Found)} if the lazadaOrderPayments is not found,
     * or with status {@code 500 (Internal Server Error)} if the lazadaOrderPayments couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/lazada-order-payments/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LazadaOrderPayments> partialUpdateLazadaOrderPayments(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LazadaOrderPayments lazadaOrderPayments
    ) throws URISyntaxException {
        log.debug("REST request to partial update LazadaOrderPayments partially : {}, {}", id, lazadaOrderPayments);
        if (lazadaOrderPayments.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, lazadaOrderPayments.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!lazadaOrderPaymentsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LazadaOrderPayments> result = lazadaOrderPaymentsRepository
            .findById(lazadaOrderPayments.getId())
            .map(existingLazadaOrderPayments -> {
                if (lazadaOrderPayments.getTransactionDate() != null) {
                    existingLazadaOrderPayments.setTransactionDate(lazadaOrderPayments.getTransactionDate());
                }
                if (lazadaOrderPayments.getTransactionType() != null) {
                    existingLazadaOrderPayments.setTransactionType(lazadaOrderPayments.getTransactionType());
                }
                if (lazadaOrderPayments.getFeeName() != null) {
                    existingLazadaOrderPayments.setFeeName(lazadaOrderPayments.getFeeName());
                }
                if (lazadaOrderPayments.getTransactionNumber() != null) {
                    existingLazadaOrderPayments.setTransactionNumber(lazadaOrderPayments.getTransactionNumber());
                }
                if (lazadaOrderPayments.getDetails() != null) {
                    existingLazadaOrderPayments.setDetails(lazadaOrderPayments.getDetails());
                }
                if (lazadaOrderPayments.getSellerSku() != null) {
                    existingLazadaOrderPayments.setSellerSku(lazadaOrderPayments.getSellerSku());
                }
                if (lazadaOrderPayments.getLazadaSku() != null) {
                    existingLazadaOrderPayments.setLazadaSku(lazadaOrderPayments.getLazadaSku());
                }
                if (lazadaOrderPayments.getAmount() != null) {
                    existingLazadaOrderPayments.setAmount(lazadaOrderPayments.getAmount());
                }
                if (lazadaOrderPayments.getVatInAmount() != null) {
                    existingLazadaOrderPayments.setVatInAmount(lazadaOrderPayments.getVatInAmount());
                }
                if (lazadaOrderPayments.getWhtAmount() != null) {
                    existingLazadaOrderPayments.setWhtAmount(lazadaOrderPayments.getWhtAmount());
                }
                if (lazadaOrderPayments.getWhtIncludedInAmount() != null) {
                    existingLazadaOrderPayments.setWhtIncludedInAmount(lazadaOrderPayments.getWhtIncludedInAmount());
                }
                if (lazadaOrderPayments.getStatement() != null) {
                    existingLazadaOrderPayments.setStatement(lazadaOrderPayments.getStatement());
                }
                if (lazadaOrderPayments.getPaidStatus() != null) {
                    existingLazadaOrderPayments.setPaidStatus(lazadaOrderPayments.getPaidStatus());
                }
                if (lazadaOrderPayments.getOrderNo() != null) {
                    existingLazadaOrderPayments.setOrderNo(lazadaOrderPayments.getOrderNo());
                }
                if (lazadaOrderPayments.getOrderItemNo() != null) {
                    existingLazadaOrderPayments.setOrderItemNo(lazadaOrderPayments.getOrderItemNo());
                }
                if (lazadaOrderPayments.getOrderItemStatus() != null) {
                    existingLazadaOrderPayments.setOrderItemStatus(lazadaOrderPayments.getOrderItemStatus());
                }
                if (lazadaOrderPayments.getShippingProvider() != null) {
                    existingLazadaOrderPayments.setShippingProvider(lazadaOrderPayments.getShippingProvider());
                }
                if (lazadaOrderPayments.getShippingSpeed() != null) {
                    existingLazadaOrderPayments.setShippingSpeed(lazadaOrderPayments.getShippingSpeed());
                }
                if (lazadaOrderPayments.getShipmentType() != null) {
                    existingLazadaOrderPayments.setShipmentType(lazadaOrderPayments.getShipmentType());
                }
                if (lazadaOrderPayments.getReference() != null) {
                    existingLazadaOrderPayments.setReference(lazadaOrderPayments.getReference());
                }
                if (lazadaOrderPayments.getComment() != null) {
                    existingLazadaOrderPayments.setComment(lazadaOrderPayments.getComment());
                }
                if (lazadaOrderPayments.getPaymentRefId() != null) {
                    existingLazadaOrderPayments.setPaymentRefId(lazadaOrderPayments.getPaymentRefId());
                }
                if (lazadaOrderPayments.getInternalStatus() != null) {
                    existingLazadaOrderPayments.setInternalStatus(lazadaOrderPayments.getInternalStatus());
                }

                return existingLazadaOrderPayments;
            })
            .map(lazadaOrderPaymentsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, lazadaOrderPayments.getId().toString())
        );
    }

    /**
     * {@code GET  /lazada-order-payments} : get all the lazadaOrderPayments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lazadaOrderPayments in body.
     */
    @GetMapping("/lazada-order-payments")
    public ResponseEntity<List<LazadaOrderPayments>> getAllLazadaOrderPayments(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of LazadaOrderPayments");
        Page<LazadaOrderPayments> page = lazadaOrderPaymentsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /lazada-order-payments/:id} : get the "id" lazadaOrderPayments.
     *
     * @param id the id of the lazadaOrderPayments to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lazadaOrderPayments, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/lazada-order-payments/{id}")
    public ResponseEntity<LazadaOrderPayments> getLazadaOrderPayments(@PathVariable Long id) {
        log.debug("REST request to get LazadaOrderPayments : {}", id);
        Optional<LazadaOrderPayments> lazadaOrderPayments = lazadaOrderPaymentsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(lazadaOrderPayments);
    }

    /**
     * {@code DELETE  /lazada-order-payments/:id} : delete the "id" lazadaOrderPayments.
     *
     * @param id the id of the lazadaOrderPayments to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/lazada-order-payments/{id}")
    public ResponseEntity<Void> deleteLazadaOrderPayments(@PathVariable Long id) {
        log.debug("REST request to delete LazadaOrderPayments : {}", id);
        lazadaOrderPaymentsRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
