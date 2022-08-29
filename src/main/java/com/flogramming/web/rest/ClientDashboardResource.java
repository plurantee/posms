package com.flogramming.web.rest;

import com.flogramming.domain.*;
import com.flogramming.repository.*;
import com.flogramming.service.ClientUserService;
import com.flogramming.service.ExcelFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * REST controller for managing {@link LazadaOrder}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ClientDashboardResource {

    private static final String ENTITY_NAME = "lazadaOrder";
    private final Logger log = LoggerFactory.getLogger(ClientDashboardResource.class);
    private final ShopRepository shopRepository;
    private final ExcelFileService excelFileService;
    private final ClientLazadaOrderRepository lazadaOrderRepository;
    private final ClientUserService clientUserService;

    @Autowired
    private ClientLazadaOrderRepository clientLazadaOrderRepository;

    @Autowired
    private ClientShopeeOrderRepository clientShopeeOrderRepository;

    @Autowired
    private ClientInventoryRepository inventoryRepository;

    public ClientDashboardResource(
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

    @PostMapping("/dashboard/init")
    public DashboardData getAllLazadaOrdersByShop(@RequestParam("startDate") String startDate,
                                                  @RequestParam(value="endDate") String endDate,
                                                  @RequestParam(value="site") String site) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ZoneId zoneId = ZoneId.of("Asia/Manila");
        ZonedDateTime zStartDate  = LocalDateTime.parse(startDate, formatter).atZone(zoneId);
        ZonedDateTime zEndDate = LocalDateTime.parse(endDate, formatter).atZone(zoneId).plusMinutes(1);
        Client client = clientUserService.getCurrentUser().getClientCode();
        List<LazadaOrder> lazadaOrders = new ArrayList<>();
        List<ShopeeOrder> shopeeOrders = new ArrayList<>();
        if ("all".equals(site) || "lazada".equals(site)) {
            lazadaOrders = clientLazadaOrderRepository.findByDateUploadedBetweenOrderByDateUploadedDesc(zStartDate, zEndDate);
        }
        if ("all".equals(site) || "shopee".equals(site)) {
            shopeeOrders = clientShopeeOrderRepository.findByDateUploadedBetweenOrderByDateUploadedDesc(zStartDate, zEndDate);
        }
        double profit = 0;
        Map<String, Set<LazadaOrderPayments>> lazadaMap = new HashMap<>();
        Map<String, Set<ShopeeOrderPayments>> shopeeMap = new HashMap<>();

        for (LazadaOrder lazadaOrder : lazadaOrders) {
            Inventory inventory = inventoryRepository.findBySkuAndClient(lazadaOrder.getSellerSku(), client);
            String key = inventory.getId()+"|"+inventory.getSku()+"|"+ inventory.getCost();
            lazadaMap.get(key);
            if (lazadaMap.get(key) == null || lazadaMap.get(key).isEmpty()) {
                lazadaMap.put(key, lazadaOrder.getPayments());
            } else {
                lazadaMap.get(key).addAll(lazadaOrder.getPayments());
            }
        }
        for (ShopeeOrder shopeeOrder : shopeeOrders) {
            Inventory inventory = inventoryRepository.findBySkuAndClient(shopeeOrder.getSkuReferenceNo(), client);
            String key = inventory.getId()+"|"+inventory.getSku()+"|"+ inventory.getCost();
            lazadaMap.get(key);
            if (shopeeMap.get(key) == null || shopeeMap.get(key).isEmpty()) {
                shopeeMap.put(key, shopeeOrder.getPayments());
            } else {
                shopeeMap.get(key).addAll(shopeeOrder.getPayments());
            }
            shopeeMap.put(key, shopeeOrder.getPayments());
        }

        for(Map.Entry<String, Set<LazadaOrderPayments>> entry : lazadaMap.entrySet()) {
            String[] inventoryDetails = entry.getKey().split("\\|");
            Inventory inventory = inventoryRepository.findBySkuAndClient(inventoryDetails[1], client);
            double gross = 0;
            for (LazadaOrderPayments lazadaOrderPayments : entry.getValue()) {
                gross = gross + lazadaOrderPayments.getAmount();
            }
            profit = profit + (gross - inventory.getCost());
        }

        for(Map.Entry<String, Set<ShopeeOrderPayments>> entry : shopeeMap.entrySet()) {
            String[] inventoryDetails = entry.getKey().split("\\|");
            Inventory inventory = inventoryRepository.findBySkuAndClient(inventoryDetails[1], client);
            double gross = 0;
            for (ShopeeOrderPayments shopeeOrderPayments : entry.getValue()) {
                gross = gross + shopeeOrderPayments.getTotalReleasedAmount();
            }
            profit = profit + (gross - inventory.getCost());
        }

        List<Inventory> thresholdItems = inventoryRepository.findAll().stream().filter(
            inventory -> inventory.getStocks() <= inventory.getThreshold()
        ).collect(Collectors.toList());

        DashboardData dashboardData = new DashboardData();
        dashboardData.setProfit(profit);
        dashboardData.setThresholdItems(thresholdItems);
        dashboardData.setLazadaMap(lazadaMap);
        dashboardData.setShopeeMap(shopeeMap);
        return dashboardData;
    }

}
