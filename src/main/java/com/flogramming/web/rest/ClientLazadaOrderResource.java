package com.flogramming.web.rest;

import com.flogramming.domain.Client;
import com.flogramming.domain.LazadaOrder;
import com.flogramming.domain.Shop;
import com.flogramming.repository.ClientClientRepository;
import com.flogramming.repository.ClientLazadaOrderRepository;
import com.flogramming.repository.ShopRepository;
import com.flogramming.service.ClientUserService;
import com.flogramming.service.ExcelFileService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * REST controller for managing {@link LazadaOrder}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ClientLazadaOrderResource {

    private final Logger log = LoggerFactory.getLogger(ClientLazadaOrderResource.class);

    private static final String ENTITY_NAME = "lazadaOrder";
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
    public ResponseEntity<List<LazadaOrder>> uploadLazadaOrders(@RequestParam("file") MultipartFile file) throws IOException {
        List<LazadaOrder> lazadaOrders = excelFileService.processLazadaExcelFile(file);
        return ResponseEntity.ok(lazadaOrders);
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
    public List<LazadaOrder> getAllLazadaOrdersByClient() {
        log.debug("REST request to get all LazadaOrders");
        Client client = clientUserService.getCurrentUser().getClientCode();
        return lazadaOrderRepository.findByClient(client);
    }
}
