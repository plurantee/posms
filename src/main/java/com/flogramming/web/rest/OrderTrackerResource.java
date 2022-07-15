package com.flogramming.web.rest;

import com.flogramming.domain.LazadaOrder;
import com.flogramming.domain.Order;
import com.flogramming.domain.OrderTracker;
import com.flogramming.domain.ShopeeOrder;
import com.flogramming.domain.enumeration.OrderCategory;
import com.flogramming.repository.ClientLazadaOrderRepository;
import com.flogramming.repository.ClientShopeeOrderRepository;
import com.flogramming.service.WaybillFileService;
import liquibase.pro.packaged.T;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/order-tracker")
@Transactional
public class OrderTrackerResource {

    @Autowired
    private ClientLazadaOrderRepository clientLazadaOrderRepository;

    @Autowired
    private ClientShopeeOrderRepository clientShopeeOrderRepository;

    @Autowired
    private WaybillFileService waybillFileService;

    @GetMapping("{queryId}")
    public ResponseEntity<List<OrderTracker>> getOrder(@PathVariable String queryId) {
        List<OrderTracker> result = new ArrayList<>();
        List<ShopeeOrder> shopeeOrders = clientShopeeOrderRepository.findByOrderId(queryId);
        if (shopeeOrders.isEmpty()) {
            shopeeOrders = clientShopeeOrderRepository.findByTrackingNumber(queryId);
        }

        if (!shopeeOrders.isEmpty()) {
            shopeeOrders.forEach(order -> {
                result.add(mapShopeeToOrderTracker(order));
            });
            return ResponseEntity.ok(result);
        }
        List<LazadaOrder> lazadaOrders = clientLazadaOrderRepository.findByOrderItemId(queryId);
        if (lazadaOrders.isEmpty()) {
            lazadaOrders = clientLazadaOrderRepository.findByTrackingCode(queryId);
        }

        if (!lazadaOrders.isEmpty()) {
            lazadaOrders.forEach(order -> {
                result.add(mapLazadaToOrderTracker(order));
            });
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping(path = "/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> modifyWaybill(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] result = waybillFileService.processWaybill(file);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("Filled-"+file.getName(), "Filled-"+file.getName());
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(result, headers, HttpStatus.OK);
        return response;
    }

    public OrderTracker mapLazadaToOrderTracker(LazadaOrder lazadaOrder) {
        OrderTracker orderTracker = new OrderTracker();
        orderTracker.setSite("LAZADA");

        orderTracker.setId(lazadaOrder.getId());
        orderTracker.setOrderItemId(lazadaOrder.getOrderItemId());
        orderTracker.setSkuReference(lazadaOrder.getSellerSku());
        orderTracker.setStatus(lazadaOrder.getStatus());
        orderTracker.setOrderType(lazadaOrder.getOrderType());
        return orderTracker;

    }

    public OrderTracker mapShopeeToOrderTracker(ShopeeOrder shopeeOrder) {
        OrderTracker orderTracker = new OrderTracker();
        orderTracker.setSite("SHOPEE");

        orderTracker.setId(shopeeOrder.getId());
        orderTracker.setOrderItemId(shopeeOrder.getOrderId());
        orderTracker.setSkuReference(shopeeOrder.getSkuReferenceNo());
        orderTracker.setStatus(shopeeOrder.getOrderStatus());
        return orderTracker;

    }
}
