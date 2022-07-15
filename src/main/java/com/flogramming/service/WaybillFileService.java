package com.flogramming.service;

import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class WaybillFileService {

    private final Logger log = LoggerFactory.getLogger(WaybillFileService.class);

    public byte[] processWaybill(MultipartFile file) throws IOException {
        PDDocument pdDocument = PDDocument.load(file.getInputStream());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();


        pdDocument.save(byteArrayOutputStream);
        pdDocument.close();
        InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        return IOUtils.toByteArray(inputStream);
    }
}
