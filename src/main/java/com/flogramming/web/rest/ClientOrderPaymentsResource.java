package com.flogramming.web.rest;

import com.flogramming.domain.LazadaOrderPayments;
import com.flogramming.domain.ShopeeOrderPayments;
import com.flogramming.repository.ClientLazadaOrderPaymentsRepository;
import com.flogramming.repository.ClientShopeeOrderPaymentsRepository;
import java.util.List;
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
import tech.jhipster.web.util.PaginationUtil;

/**
 * REST controller for managing {@link LazadaOrderPayments}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ClientOrderPaymentsResource {

    private final Logger log = LoggerFactory.getLogger(ClientOrderPaymentsResource.class);

    private static final String ENTITY_NAME = "lazadaOrderPayments";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClientLazadaOrderPaymentsRepository lazadaOrderPaymentsRepository;
    private final ClientShopeeOrderPaymentsRepository shopeeOrderPaymentsRepository;

    public ClientOrderPaymentsResource(
        ClientLazadaOrderPaymentsRepository lazadaOrderPaymentsRepository,
        ClientShopeeOrderPaymentsRepository shopeeOrderPaymentsRepository
    ) {
        this.lazadaOrderPaymentsRepository = lazadaOrderPaymentsRepository;
        this.shopeeOrderPaymentsRepository = shopeeOrderPaymentsRepository;
    }

    @GetMapping("/lazada-order-payments/by-order-id/{orderId}")
    public ResponseEntity<List<LazadaOrderPayments>> getAllLazadaOrderPayments(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @PathVariable String orderId
    ) {
        log.debug("REST request to get a page of LazadaOrderPayments");
        Page<LazadaOrderPayments> page = lazadaOrderPaymentsRepository.findByOrderItemNo(orderId, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/shopee-order-payments/by-order-id/{orderId}")
    public ResponseEntity<List<ShopeeOrderPayments>> getAllShopeeOrderPayments(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @PathVariable String orderId
    ) {
        log.debug("REST request to get a page of LazadaOrderPayments");
        Page<ShopeeOrderPayments> page = shopeeOrderPaymentsRepository.findByOrderId(orderId, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
