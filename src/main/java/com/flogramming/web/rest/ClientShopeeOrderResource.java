package com.flogramming.web.rest;

import com.flogramming.domain.Client;
import com.flogramming.domain.LazadaOrder;
import com.flogramming.domain.Shop;
import com.flogramming.domain.ShopeeOrder;
import com.flogramming.repository.ClientShopeeOrderRepository;
import com.flogramming.repository.ShopRepository;
import com.flogramming.service.ClientUserService;
import com.flogramming.service.ExcelFileService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * REST controller for managing {@link ShopeeOrder}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ClientShopeeOrderResource {

    private final Logger log = LoggerFactory.getLogger(ClientShopeeOrderResource.class);

    private static final String ENTITY_NAME = "shopeeOrder";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClientShopeeOrderRepository shopeeOrderRepository;
    private final ShopRepository shopRepository;
    private final ExcelFileService excelFileService;
    private final ClientUserService clientUserService;

    public ClientShopeeOrderResource(
        ClientShopeeOrderRepository shopeeOrderRepository,
        ShopRepository shopRepository,
        ExcelFileService excelFileService,
        ClientUserService clientUserService
    ) {
        this.shopeeOrderRepository = shopeeOrderRepository;
        this.shopRepository = shopRepository;
        this.excelFileService = excelFileService;
        this.clientUserService = clientUserService;
    }

    @PostMapping(path = "/shopee-orders/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<List<ShopeeOrder>> uploadShopeeOrders(@RequestParam("file") MultipartFile file) throws IOException {
        List<ShopeeOrder> shopeeOrders = excelFileService.processShopeeExcelFile(file);
        return ResponseEntity.ok(shopeeOrders);
    }

    @GetMapping("/shopee-orders/shop/{id}")
    public List<ShopeeOrder> getAllShopeeOrdersByShop(@PathVariable Long id) {
        log.debug("REST request to get all ShopeeOrders");
        Optional<Shop> shop = shopRepository.findById(id);
        return shop.map(shopeeOrderRepository::findByShop).orElse(null);
    }

    @GetMapping("/shopee-orders/client")
    public List<ShopeeOrder> getAllLazadaOrdersByClient() {
        log.debug("REST request to get all LazadaOrders");
        Client client = clientUserService.getCurrentUser().getClientCode();
        return shopeeOrderRepository.findByClient(client);
    }
}
