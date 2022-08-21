package com.flogramming.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flogramming.domain.LazadaOrder;
import com.flogramming.domain.OrderTracker;
import com.flogramming.domain.ShopeeOrder;
import com.flogramming.repository.ClientLazadaOrderRepository;
import com.flogramming.repository.ClientShopeeOrderRepository;
import com.flogramming.service.WaybillFileService;
import com.flogramming.util.OrderTrackerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.ZonedDateTime;
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
                result.add(OrderTrackerUtil.mapShopeeToOrderTracker(order));
            });
            return ResponseEntity.ok(result);
        }
        List<LazadaOrder> lazadaOrders = clientLazadaOrderRepository.findByOrderNumber(queryId);
        if (lazadaOrders.isEmpty()) {
            lazadaOrders = clientLazadaOrderRepository.findByOrderItemId(queryId);
        }
        if (lazadaOrders.isEmpty()) {
            lazadaOrders = clientLazadaOrderRepository.findByTrackingCode(queryId);
        }

        if (!lazadaOrders.isEmpty()) {
            lazadaOrders.forEach(order -> {
                result.add(OrderTrackerUtil.mapLazadaToOrderTracker(order));
            });
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces =
        MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> modifyWaybill(@RequestParam("file") MultipartFile file,
                                                               @RequestParam("orders") String orders) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<OrderTracker> ordersArray = List.of(objectMapper.readValue(orders, OrderTracker[].class));
        byte[] result = waybillFileService.processWaybill(file, ordersArray);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("Filled-" + file.getName(), "Filled-" + file.getName());
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(result, headers, HttpStatus.OK);
        return response;
    }

    @PostMapping(path = "/init-upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<List<OrderTracker>> initupload(@RequestParam("file") MultipartFile file) throws IOException {

        List<OrderTracker> result = waybillFileService.viewWaybill(file);

        return ResponseEntity.ok(result);
    }

    @PutMapping(path = "/release")
    public ResponseEntity<List<OrderTracker>> release(@RequestBody List<OrderTracker> orderTrackers) throws IOException {
        List<OrderTracker> result = new ArrayList<>();
        if ("LAZADA".equals(orderTrackers.get(0).getSite())) {
            result = modifyLazadaOrders(orderTrackers, ProcessType.RELEASE);
        } else if ("SHOPEE".equals(orderTrackers.get(0).getSite())) {
            result = modifyShopeeOrders(orderTrackers, ProcessType.RELEASE);
        }

        return ResponseEntity.ok(result);
    }

    @PutMapping(path = "/cancel")
    public ResponseEntity<List<OrderTracker>> cancel(@RequestBody List<OrderTracker> orderTrackers) throws IOException {
        List<OrderTracker> result = new ArrayList<>();
        if ("LAZADA".equals(orderTrackers.get(0).getSite())) {
            result = modifyLazadaOrders(orderTrackers, ProcessType.CANCEL);
        } else if ("SHOPEE".equals(orderTrackers.get(0).getSite())) {
            result = modifyShopeeOrders(orderTrackers, ProcessType.CANCEL);
        }

        return ResponseEntity.ok(result);
    }

    private List<OrderTracker> modifyShopeeOrders(List<OrderTracker> orderTrackers, ProcessType release) {
        List<OrderTracker> result = new ArrayList<>();
        orderTrackers.forEach( orderTracker -> {
            clientShopeeOrderRepository.findByOrderId(orderTracker.getOrderItemId())
                .forEach(shopeeOrder -> {
                    shopeeOrder.setOrderStatus(release.getProcess());
                    clientShopeeOrderRepository.save(shopeeOrder);
                    shopeeOrder.setDateReleasedOrCancelled(ZonedDateTime.now());
                    result.add(OrderTrackerUtil.mapShopeeToOrderTracker(shopeeOrder));
                });

        });
        return result;
    }

    private List<OrderTracker> modifyLazadaOrders(List<OrderTracker> orderTrackers, ProcessType release) {
        List<OrderTracker> result = new ArrayList<>();
        orderTrackers.forEach( orderTracker -> {
            clientLazadaOrderRepository.findByOrderItemId(orderTracker.getOrderItemId())
                .forEach(lazadaOrder -> {
                    lazadaOrder.setStatus(release.getProcess());
                    lazadaOrder.dateReleasedOrCancelled(ZonedDateTime.now());
                    clientLazadaOrderRepository.save(lazadaOrder);
                    result.add(OrderTrackerUtil.mapLazadaToOrderTracker(lazadaOrder));
                });

        });
        return result;
    }


}

enum ProcessType {
    RELEASE("released"),
    CANCEL("cancelled");

    public final String process;
    ProcessType(String process) {
        this.process = process;
    }

    public String getProcess() {
        return process;
    }
}
