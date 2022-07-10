package com.flogramming.web.rest;

import com.flogramming.domain.LazadaOrder;
import com.flogramming.domain.Shop;
import com.flogramming.repository.ClientLazadaOrderRepository;
import com.flogramming.repository.ShopRepository;
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

    public ClientLazadaOrderResource(
        ShopRepository shopRepository,
        ExcelFileService excelFileService,
        ClientLazadaOrderRepository lazadaOrderRepository
    ) {
        this.shopRepository = shopRepository;
        this.excelFileService = excelFileService;
        this.lazadaOrderRepository = lazadaOrderRepository;
    }

    @PostMapping(path = "/lazada-orders/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<List<LazadaOrder>> uploadLazadaOrders(
        @RequestParam("file") MultipartFile file,
        @RequestParam("shopId") Long shopId
    ) throws IOException {
        Optional<Shop> oShop = shopRepository.findById(shopId);
        List<LazadaOrder> lazadaOrders = excelFileService.processLazadaExcelFile(file, oShop.get());
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
}
