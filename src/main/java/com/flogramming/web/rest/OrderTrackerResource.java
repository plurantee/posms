package com.flogramming.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flogramming.StatusType;
import com.flogramming.domain.*;
import com.flogramming.repository.ClientLazadaOrderRepository;
import com.flogramming.repository.ClientShopeeOrderRepository;
import com.flogramming.service.WaybillFileService;
import com.flogramming.util.OrderTrackerUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        List<ShopeeOrder> shopeeOrders = clientShopeeOrderRepository.findByOrderIdOrderByDateUploadedDesc(queryId);
        if (shopeeOrders.isEmpty()) {
            shopeeOrders = clientShopeeOrderRepository.findByTrackingNumberOrderByDateUploadedDesc(queryId);
        }

        if (!shopeeOrders.isEmpty()) {
            shopeeOrders.forEach(order -> {
                result.add(OrderTrackerUtil.mapShopeeToOrderTracker(order));
            });
            return ResponseEntity.ok(result);
        }
        List<LazadaOrder> lazadaOrders = clientLazadaOrderRepository.findByOrderNumberOrderByDateUploadedDesc(queryId);
        if (lazadaOrders.isEmpty()) {
            lazadaOrders = clientLazadaOrderRepository.findByOrderItemIdOrderByDateUploadedDesc(queryId);
        }
        if (lazadaOrders.isEmpty()) {
            lazadaOrders = clientLazadaOrderRepository.findByTrackingCodeOrderByDateUploadedDesc(queryId);
        }

        if (!lazadaOrders.isEmpty()) {
            lazadaOrders.forEach(order -> {
                result.add(OrderTrackerUtil.mapLazadaToOrderTracker(order));
            });
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/query")
    public ResponseEntity<List<OrderTracker>> executeQuery(@RequestParam("startDate") String startDate,
                                                           @RequestParam(value="endDate") String endDate,
                                                           @RequestParam(value="site") String site,
                                                           @RequestParam(value="status") String status) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ZoneId zoneId = ZoneId.of("Asia/Manila");
        ZonedDateTime zStartDate  = LocalDateTime.parse(startDate, formatter).atZone(zoneId);
        ZonedDateTime zEndDate = LocalDateTime.parse(endDate, formatter).atZone(zoneId).plusMinutes(1);
        List<OrderTracker> result = new ArrayList<>();
        List<LazadaOrder> lazadaOrders = new ArrayList<>();
        List<ShopeeOrder> shopeeOrders = new ArrayList<>();
        if ("all".equals(status)) {
            if ("all".equals(site) || "lazada".equals(site)) {
                lazadaOrders = clientLazadaOrderRepository.findByDateUploadedBetweenOrderByDateUploadedDesc(zStartDate, zEndDate);
            }
            if ("all".equals(site) || "shopee".equals(site)) {
                shopeeOrders = clientShopeeOrderRepository.findByDateUploadedBetweenOrderByDateUploadedDesc(zStartDate, zEndDate);
            }

        } else {
            if ("all".equals(site) || "lazada".equals(site)) {
                lazadaOrders = clientLazadaOrderRepository.findByStatusAndDateUploadedBetweenOrderByDateUploadedDesc(status, zStartDate, zEndDate);
            }
            if ("all".equals(site) || "shopee".equals(site)) {
                shopeeOrders = clientShopeeOrderRepository.findByOrderStatusAndDateUploadedBetweenOrderByDateUploadedDesc(status, zStartDate, zEndDate);
            }
        }
        if (lazadaOrders.size() > 0) {
            lazadaOrders.forEach(lazadaOrder -> {
                result.add(OrderTrackerUtil.mapLazadaToOrderTracker(lazadaOrder));
            });
        }
        if (shopeeOrders.size() > 0) {
            shopeeOrders.forEach(shopeeOrder -> {
                result.add(OrderTrackerUtil.mapShopeeToOrderTracker(shopeeOrder));
            });
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

    @PostMapping(path = "/release-report", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity releaseReport(@RequestBody List<OrderTracker> orderTrackers, HttpServletResponse response) throws IOException {
        ServletOutputStream os = response.getOutputStream();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=test.xlsx\"");

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Released Orders");
        Row header = sheet.createRow(0);
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Name");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Age");
        headerCell.setCellStyle(headerStyle);

        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        Row row = sheet.createRow(2);
        Cell cell = row.createCell(0);
        cell.setCellValue("John Smith");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue(20);
        cell.setCellStyle(style);

        workbook.write(os);
        response.flushBuffer();
        return null;
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
    @PutMapping(path = "/return")
    public ResponseEntity<List<OrderTracker>> returnOrders(@RequestBody List<OrderTracker> orderTrackers) throws IOException {
        List<OrderTracker> result = new ArrayList<>();
        if ("LAZADA".equals(orderTrackers.get(0).getSite())) {
            result = modifyLazadaOrders(orderTrackers, ProcessType.RETURNED);
        } else if ("SHOPEE".equals(orderTrackers.get(0).getSite())) {
            result = modifyShopeeOrders(orderTrackers, ProcessType.RETURNED);
        }

        return ResponseEntity.ok(result);
    }
    private List<OrderTracker> modifyShopeeOrders(List<OrderTracker> orderTrackers, ProcessType processType) {
        List<OrderTracker> result = new ArrayList<>();
        orderTrackers.forEach( orderTracker -> {
            ShopeeOrder shopeeOrder = clientShopeeOrderRepository.findByOrderIdAndSkuReferenceNoOrderByDateUploadedDesc(orderTracker.getOrderItemId(), orderTracker.getSkuReference());
            shopeeOrder.setOrderStatus(
                ProcessType.RELEASE.getProcess().equals(processType.getProcess()) ? releaseShopeeThenChangeStatus(shopeeOrder) : processType.getProcess()
            );
            shopeeOrder.dateReleasedOrCancelled(ZonedDateTime.now());
            result.add(OrderTrackerUtil.mapShopeeToOrderTracker(shopeeOrder));
        });
        return result;
    }

    private List<OrderTracker> modifyLazadaOrders(List<OrderTracker> orderTrackers, ProcessType processType) {
        List<OrderTracker> result = new ArrayList<>();
        orderTrackers.forEach( orderTracker -> {
            clientLazadaOrderRepository.findByOrderItemIdOrderByDateUploadedDesc(orderTracker.getOrderItemId())
                .forEach(lazadaOrder -> {
                    lazadaOrder.setStatus(
                        ProcessType.RELEASE.getProcess().equals(processType.getProcess()) ? releaseLazadaThenChangeStatus(lazadaOrder) : processType.getProcess()
                    );
                    lazadaOrder.dateReleasedOrCancelled(ZonedDateTime.now());
                    clientLazadaOrderRepository.save(lazadaOrder);
                    result.add(OrderTrackerUtil.mapLazadaToOrderTracker(lazadaOrder));
                });

        });
        return result;
    }

    public String releaseLazadaThenChangeStatus(LazadaOrder lazadaOrder) {
        Set<LazadaOrderPayments> payments = lazadaOrder.getPayments();
        return payments.isEmpty() ? StatusType.PENDING_PAYMENT.getValue() : StatusType.PAID.getValue();
    }

    public String releaseShopeeThenChangeStatus(ShopeeOrder shopeeOrder) {
        Set<ShopeeOrderPayments> payments = shopeeOrder.getPayments();
        return payments.isEmpty() ? StatusType.PENDING_PAYMENT.getValue() : StatusType.PAID.getValue();
    }

}

enum ProcessType {
    RELEASE("released"),
    CANCEL("cancelled"),
    RETURNED("returned")
    ;

    public final String process;
    ProcessType(String process) {
        this.process = process;
    }

    public String getProcess() {
        return process;
    }
}
