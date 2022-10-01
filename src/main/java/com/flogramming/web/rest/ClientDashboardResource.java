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

    private final ClientUserService clientUserService;

    @Autowired
    private ClientLazadaOrderRepository clientLazadaOrderRepository;

    @Autowired
    private ClientShopeeOrderRepository clientShopeeOrderRepository;

    @Autowired
    private ClientLazadaOrderPaymentsRepository clientLazadaOrderPaymentsRepository;

    @Autowired
    private ClientShopeeOrderPaymentsRepository clientShopeeOrderPaymentsRepository;

    @Autowired
    private ClientInventoryRepository inventoryRepository;

    public ClientDashboardResource(
        ClientUserService clientUserService
    ) {
        this.clientUserService = clientUserService;
    }

    @PostMapping("/dashboard/init")
    public DashboardData init() {
        Client client = clientUserService.getCurrentUser().getClientCode();

        List<Inventory> allInventory = inventoryRepository.findByClient(client);
        List<Inventory> thresholdItems = new ArrayList<>();
        if (allInventory!=null && allInventory.isEmpty()) {
            thresholdItems = allInventory.stream().filter(
                inventory -> inventory != null
                    && inventory.getStocks() != null
                    && inventory.getThreshold() != null
                    && inventory.getStocks() <= inventory.getThreshold()
            ).collect(Collectors.toList());
        }


        DashboardData dashboardData = new DashboardData();
        dashboardData.setThresholdItems(thresholdItems);
        return dashboardData;
    }
    @PostMapping("/dashboard/init-lazada")
    public DashboardData initlazada(@RequestParam("startDate") String startDate,
                                                  @RequestParam(value="endDate") String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ZoneId zoneId = ZoneId.of("Asia/Manila");
        ZonedDateTime zStartDate  = LocalDateTime.parse(startDate, formatter).atZone(zoneId);
        ZonedDateTime zEndDate = LocalDateTime.parse(endDate, formatter).atZone(zoneId).plusMinutes(1);
        Client client = clientUserService.getCurrentUser().getClientCode();

        Set<LazadaOrder> lazadaOrders = clientLazadaOrderRepository.findByDateUploadedBetweenOrderByDateUploadedDesc(zStartDate, zEndDate);

        var payments = clientLazadaOrderPaymentsRepository.findByTransactionDateBetweenOrderByTransactionDateDesc(zStartDate, zEndDate);

        for (LazadaOrderPayments payment : payments) {
            lazadaOrders.add(payment.getLazadaOrder());
        }
        double profit = 0;
        Map<String, Set<LazadaOrderPayments>> lazadaMap = new HashMap<>();

        for (LazadaOrder lazadaOrder : lazadaOrders) {
            Inventory inventory = inventoryRepository.findBySkuAndClient(lazadaOrder.getSellerSku(), client);
            String key = inventory.getId()+"|"+inventory.getSku()+"|"+ inventory.getCost();
            if (lazadaMap.get(key) == null || lazadaMap.get(key).isEmpty()) {
                lazadaMap.put(key, lazadaOrder.getPayments());
            } else {
                lazadaMap.get(key).addAll(lazadaOrder.getPayments());
            }
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


        DashboardData dashboardData = new DashboardData();
        dashboardData.setProfit(profit);
        dashboardData.setLazadaMap(lazadaMap);
        return dashboardData;
    }

    @PostMapping("/dashboard/init-shopee")
    public DashboardData shopee(@RequestParam("startDate") String startDate,
                                    @RequestParam(value="endDate") String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ZoneId zoneId = ZoneId.of("Asia/Manila");
        ZonedDateTime zStartDate  = LocalDateTime.parse(startDate, formatter).atZone(zoneId);
        ZonedDateTime zEndDate = LocalDateTime.parse(endDate, formatter).atZone(zoneId).plusMinutes(1);
        Client client = clientUserService.getCurrentUser().getClientCode();
        Set<ShopeeOrder> shopeeOrders = clientShopeeOrderRepository.findByDateUploadedBetweenOrderByDateUploadedDesc(zStartDate, zEndDate);

        var payments = clientShopeeOrderPaymentsRepository.findByPayoutCompletedDateBetweenOrderByPayoutCompletedDateDesc(zStartDate, zEndDate);

        for (ShopeeOrderPayments payment : payments) {
            shopeeOrders.addAll(payment.getShopeeOrders());
        }
        double profit = 0;
        Map<String, Set<ShopeeOrderPayments>> shopeeMap = new HashMap<>();

        for (ShopeeOrder shopeeOrder : shopeeOrders) {
            Inventory inventory = inventoryRepository.findBySkuAndClient(shopeeOrder.getSkuReferenceNo(), client);
            String key = inventory.getId()+"|"+inventory.getSku()+"|"+ inventory.getCost();
            if (shopeeMap.get(key) == null || shopeeMap.get(key).isEmpty()) {
                shopeeMap.put(key, shopeeOrder.getPayments());
            } else {
                shopeeMap.get(key).addAll(shopeeOrder.getPayments());
            }
            shopeeMap.put(key, shopeeOrder.getPayments());
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



        DashboardData dashboardData = new DashboardData();
        dashboardData.setProfit(profit);
        dashboardData.setShopeeMap(shopeeMap);
        return dashboardData;
    }

}
