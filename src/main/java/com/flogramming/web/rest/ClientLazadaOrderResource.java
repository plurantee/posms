package com.flogramming.web.rest;

import com.flogramming.domain.Client;
import com.flogramming.domain.LazadaOrder;
import com.flogramming.domain.Shop;
import com.flogramming.repository.ClientLazadaOrderRepository;
import com.flogramming.repository.ShopRepository;
import com.flogramming.service.ClientUserService;
import com.flogramming.service.ExcelFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
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

/**
 * REST controller for managing {@link LazadaOrder}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ClientLazadaOrderResource {

    private static final String ENTITY_NAME = "lazadaOrder";
    private final Logger log = LoggerFactory.getLogger(ClientLazadaOrderResource.class);
    private final ShopRepository shopRepository;
    private final ExcelFileService excelFileService;
    private final ClientLazadaOrderRepository lazadaOrderRepository;
    private final ClientUserService clientUserService;

    public ClientLazadaOrderResource(
        ShopRepository shopRepository,
        ExcelFileService excelFileService,
        ClientLazadaOrderRepository lazadaOrderRepository,
        ClientUserService clientUserService
    ) {
        this.shopRepository = shopRepository;
        this.excelFileService = excelFileService;
        this.lazadaOrderRepository = lazadaOrderRepository;
        this.clientUserService = clientUserService;
    }

    @PostMapping(path = "/lazada-orders/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<List<LazadaOrder>> uploadLazadaOrders(@RequestParam("file") MultipartFile file, @RequestParam(value="shopId", required = false) Long shopId) throws IOException {
        Page<LazadaOrder> page = excelFileService.processLazadaExcelFile(file, shopId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/lazada-orders/shop/{id}")
    public List<LazadaOrder> getAllLazadaOrdersByShop(@PathVariable Long id) {
        log.debug("REST request to get all LazadaOrders");
        Optional<Shop> shop = shopRepository.findById(id);
        if (shop.isPresent()) {
            return lazadaOrderRepository.findByShop(shop.get());
        }
        return null;
    }

    @GetMapping("/lazada-orders/client")
    public ResponseEntity<List<LazadaOrder>> getAllLazadaOrdersByClient(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @org.springdoc.api.annotations.ParameterObject String filter
    ) {
        log.debug("REST request to get all LazadaOrders");
        Client client = clientUserService.getCurrentUser().getClientCode();
        Page<LazadaOrder> page = lazadaOrderRepository.findByClient(client, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
