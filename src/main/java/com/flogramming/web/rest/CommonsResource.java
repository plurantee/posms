package com.flogramming.web.rest;

import com.flogramming.domain.Inventory;
import com.flogramming.domain.LazadaOrder;
import com.flogramming.service.ExcelFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/commons")
@Transactional
public class CommonsResource {

    @Autowired
    private ExcelFileService excelFileService;

    @PostMapping(path = "/inventory/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<List<Inventory>> uploadLazadaOrders(@RequestParam("file") MultipartFile file) throws IOException {
        Page<Inventory> page = excelFileService.processInventoryUpload(file);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
