package com.flogramming.web.rest;

import com.flogramming.domain.Client;
import com.flogramming.domain.Shop;
import com.flogramming.domain.ShopeeOrder;
import com.flogramming.repository.ClientShopeeOrderPaymentsRepository;
import com.flogramming.repository.ClientShopeeOrderRepository;
import com.flogramming.repository.ShopRepository;
import com.flogramming.service.ClientUserService;
import com.flogramming.service.ExcelFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing {@link ShopeeOrder}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ClientShopeeOrderResource {

    private static final String ENTITY_NAME = "shopeeOrder";
    private final Logger log = LoggerFactory.getLogger(ClientShopeeOrderResource.class);
    private final ClientShopeeOrderRepository shopeeOrderRepository;
    private final ShopRepository shopRepository;
    private final ExcelFileService excelFileService;
    private final ClientUserService clientUserService;

    private final ClientShopeeOrderPaymentsRepository clientShopeeOrderPaymentsRepository;

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public ClientShopeeOrderResource(
        ClientShopeeOrderRepository shopeeOrderRepository,
        ShopRepository shopRepository,
        ExcelFileService excelFileService,
        ClientUserService clientUserService,
        ClientShopeeOrderPaymentsRepository clientShopeeOrderPaymentsRepository) {
        this.shopeeOrderRepository = shopeeOrderRepository;
        this.shopRepository = shopRepository;
        this.excelFileService = excelFileService;
        this.clientUserService = clientUserService;
        this.clientShopeeOrderPaymentsRepository = clientShopeeOrderPaymentsRepository;
    }

    @PostMapping(path = "/shopee-orders/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<List<ShopeeOrder>> uploadShopeeOrders(@RequestParam("file") MultipartFile file, @RequestParam(value="shopId", required = false) Long shopId) throws IOException {
        Page<ShopeeOrder> page = excelFileService.processShopeeExcelFile(file, shopId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/shopee-orders/shop/{id}")
    public List<ShopeeOrder> getAllShopeeOrdersByShop(@PathVariable Long id) {
        log.debug("REST request to get all ShopeeOrders");
        Optional<Shop> shop = shopRepository.findById(id);
        return shop.map(shopeeOrderRepository::findByShop).orElse(null);
    }

    @GetMapping("/shopee-orders/client")
    public ResponseEntity<List<ShopeeOrder>> getAllLazadaOrdersByClient(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @org.springdoc.api.annotations.ParameterObject String filter
    ) {
        log.debug("REST request to get all LazadaOrders");
        Client client = clientUserService.getCurrentUser().getClientCode();
        List<ShopeeOrder> orders = shopeeOrderRepository.findByClient(client);
        if ("all".equals(filter)) {

        } else if ("unpaid".equals(filter)) {
            orders = orders.stream().filter(e -> e.getPayments().size() == 0).collect(Collectors.toList());
        }
        Page<ShopeeOrder> page = new PageImpl(orders, pageable, orders.size());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
