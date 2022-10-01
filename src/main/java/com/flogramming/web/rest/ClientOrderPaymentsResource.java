package com.flogramming.web.rest;

import com.flogramming.domain.Client;
import com.flogramming.domain.Inventory;
import com.flogramming.domain.LazadaOrder;
import com.flogramming.domain.LazadaOrderPayments;
import com.flogramming.domain.ShopeeOrder;
import com.flogramming.domain.ShopeeOrderPayments;
import com.flogramming.repository.ClientInventoryRepository;
import com.flogramming.repository.ClientLazadaOrderPaymentsRepository;
import com.flogramming.repository.ClientLazadaOrderRepository;
import com.flogramming.repository.ClientShopeeOrderPaymentsRepository;
import com.flogramming.repository.ClientShopeeOrderRepository;
import com.flogramming.service.ClientUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;

import java.util.List;

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

    @Autowired
    private ClientLazadaOrderRepository clientLazadaOrderRepository;

    @Autowired
    private ClientShopeeOrderRepository clientShopeeOrderRepository;

    @Autowired
    private ClientInventoryRepository clientInventoryRepository;

    @Autowired
    private ClientUserService clientUserService;


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

    @GetMapping("/lazada-order-payments/by-order-id/cost/{orderId}")
    public ResponseEntity getLazadaCost(
        @PathVariable String orderId
    ) {
        Client client = clientUserService.getCurrentUser().getClientCode();
        log.debug("REST request to get a page of LazadaOrderPayments");
        double totalCost = 0;
        List<LazadaOrder> lazadaOrders = clientLazadaOrderRepository.findByOrderItemIdOrderByDateUploadedDesc(orderId);
        for(LazadaOrder lazadaOrder: lazadaOrders) {
            Inventory inventory = clientInventoryRepository.findBySkuAndClient(lazadaOrder.getSellerSku(), client);
            totalCost = totalCost + inventory.getCost();
        }
        return ResponseEntity.ok().body(totalCost);
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

    @GetMapping("/shopee-order-payments/by-order-id/cost/{orderId}")
    public ResponseEntity getShopeeCost(
        @PathVariable String orderId
    ) {
        Client client = clientUserService.getCurrentUser().getClientCode();
        log.debug("REST request to get a page of LazadaOrderPayments");
        double totalCost = 0;
        List<ShopeeOrder> shopeeOrders = clientShopeeOrderRepository.findByOrderIdOrderByDateUploadedDesc(orderId);
        for(ShopeeOrder shopeeOrder: shopeeOrders) {
            Inventory inventory = clientInventoryRepository.findBySkuAndClient(shopeeOrder.getSkuReferenceNo(), client);
            totalCost = totalCost + inventory.getCost();
        }
        return ResponseEntity.ok().body(totalCost);
    }
}
