package com.flogramming.web.rest;

import com.flogramming.domain.LazadaOrderPayments;
import com.flogramming.repository.ClientLazadaOrderRepository;
import com.flogramming.repository.ClientShopeeOrderRepository;
import com.flogramming.service.ExcelFileService;
import com.flogramming.service.WaybillFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/order/payments")
@Transactional
public class OrderPaymentsResource {

    @Autowired
    private ClientLazadaOrderRepository clientLazadaOrderRepository;

    @Autowired
    private ClientShopeeOrderRepository clientShopeeOrderRepository;

    @Autowired
    private ExcelFileService excelFileService;

    @Autowired
    private WaybillFileService waybillFileService;

    @PostMapping(path = "/lazada/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<List> processLazadaOrder(@RequestParam("file") MultipartFile file) throws IOException {
        List<LazadaOrderPayments> lazadaOrderPayments = excelFileService.processLazadaPaymentsExcelFile(file);
        return ResponseEntity.ok(lazadaOrderPayments);
    }
}

