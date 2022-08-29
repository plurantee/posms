package com.flogramming.service;

import be.quodlibet.boxable.*;
import com.flogramming.domain.LazadaOrder;
import com.flogramming.domain.OrderTracker;
import com.flogramming.domain.ShopeeOrder;
import com.flogramming.repository.ClientLazadaOrderRepository;
import com.flogramming.repository.ClientShopeeOrderRepository;
import com.flogramming.util.OrderTrackerUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.util.Matrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class WaybillFileService {

    private static final Logger log = LoggerFactory.getLogger(WaybillFileService.class);

    @Autowired
    private ClientLazadaOrderRepository clientLazadaOrderRepository;

    @Autowired
    private ClientShopeeOrderRepository clientShopeeOrderRepository;

    public byte[] processWaybill(MultipartFile file, List<OrderTracker> orders) throws IOException {
        PDDocument pdDocument = PDDocument.load(file.getInputStream());
        for (int i = 0; i < pdDocument.getNumberOfPages(); i++) {
            PDPage page = pdDocument.getPage(i);
            page.setRotation(0);
            SearchTrackingNumber searchTrackingNumber = getTrackingNumberPerPage(pdDocument, i+1);
            Map<String, Long> ordersMap = orders.stream()
                .filter(order -> searchTrackingNumber.getTrackingNumber().equals(order.getBarcodeNumber()))
                .collect(Collectors.groupingBy(e -> e.getSkuReference(), Collectors.counting()));

            PDPageContentStream contentStream = new PDPageContentStream(pdDocument, page, PDPageContentStream.AppendMode.PREPEND, true);
            // - Paddings
            float pageHeight = mm2pt(150);
            float pageWidth = mm2pt(104);



            float x =  -30;
            float y = -110;


            PDRectangle mediabox = new PDRectangle(x, y, pageWidth, pageHeight);
            page.setMediaBox(mediabox);
            page.setCropBox(mediabox);
            // - End Paddings

            contentStream.setMiterLimit(1);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 40);
            // - Table
            if (searchTrackingNumber.isLazada()) {
                fillPage(pdDocument, page, ordersMap, searchTrackingNumber.isLazada());
            }
            Matrix matrix = new Matrix();
            float xScale = 0.8f;
            float yScale = 0.75f;
            matrix.scale(xScale, yScale);
            contentStream.transform(matrix);
            if (!searchTrackingNumber.isLazada()) {
                contentStream.endText();
                contentStream.close();
                contentStream = new PDPageContentStream(pdDocument, page, PDPageContentStream.AppendMode.APPEND, true, true);
                contentStream.beginText();
                fillPage(pdDocument, page, ordersMap, searchTrackingNumber.isLazada());
                Matrix shopeeMatrix = new Matrix();
                shopeeMatrix.scale(0.8f, 0.8f);
                contentStream.transform(shopeeMatrix);

                //contentStream.transform(Matrix.getRotateInstance(Math.toRadians(180), 300, 450));

            }
            contentStream.endText();
            contentStream.close();
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        pdDocument.save(byteArrayOutputStream);
        pdDocument.close();
        InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        return IOUtils.toByteArray(inputStream);
    }

    private static void fillPage(PDDocument pdDocument, PDPage page, Map<String, Long> ordersMap, boolean lazada) throws IOException {
        float yTableStart = page.getMediaBox().getHeight() - 530;
        float tableWidth = page.getMediaBox().getWidth() - 0;
        float t1margin = -30;
        float t2margin = 150;
        float yStart = lazada ? 0 : 500;
        float fontSize = 12;
        Cell<PDPage> cell;
        BaseTable table = new BaseTable(0, yTableStart, -400, tableWidth, t1margin, pdDocument, page, true, true);

        BaseTable table2 = new BaseTable(0, yTableStart, -400, tableWidth, t2margin, pdDocument, page, true, true);
        // Table 1
        Row<PDPage> hRow = table.createRow(10);
        cell = hRow.createCell(30, "SKU");
        cell.setFontSize(fontSize);
        cell.setFont(PDType1Font.HELVETICA);
        cell = hRow.createCell(30, "PCS");
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(fontSize);
        table.addHeaderRow(hRow);

        List<String> keys = new ArrayList(ordersMap.keySet());
        int t2limit = 4;
        for (int i=0; i < 4; i++) {
            Row<PDPage> t1Row = table.createRow(1);
            String key = "";
            String value = "";
            try{
                key = keys.get(i);
                value = String.valueOf(ordersMap.get(key));
            } catch (Exception e) {
                log.info("Putting empty string instead");
            }
            cell = t1Row.createCell(30, key);
            cell.setFontSize(fontSize);
            cell = t1Row.createCell(30, value);
            cell.setFontSize(fontSize);
        }

        table.draw();
        // - End Table 1

        // Table 2
        Row<PDPage> hRow2 = table2.createRow(10);

        Cell<PDPage> cell2 = null;
        cell = hRow2.createCell(30, "SKU");
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(fontSize);
        cell = hRow2.createCell(30, "PCS");
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(fontSize);
        table2.addHeaderRow(hRow);

        for (int i=0; i < t2limit; i++) {
            Row<PDPage> t2Row = table2.createRow(1);
            String key = "";
            String value = "";
            try{
                key = keys.get(i+4);
                value = String.valueOf(ordersMap.get(key));
            } catch (Exception e) {
                log.info("Putting empty string instead");
            }
            cell = t2Row.createCell(30, key);
            cell.setFontSize(fontSize);
            cell =t2Row.createCell(30, value);
            cell.setFontSize(fontSize);
        }
        table2.draw();
        // - End Table 2
    }


    public List<OrderTracker> viewWaybill(MultipartFile file) throws IOException {
        PDDocument pdDocument = PDDocument.load(file.getInputStream());

        List<OrderTracker> orderTrackers = new ArrayList<>();
        for (int i = 1; i <= pdDocument.getNumberOfPages(); i++) {
            orderTrackers.addAll(viewWaybillPerPage(pdDocument, i));

        }
        return orderTrackers;
    }

    public List<OrderTracker> viewWaybillPerPage(PDDocument pdDocument, int page) throws IOException {
        PDFTextStripper reader = new PDFTextStripper();
        List<OrderTracker> orderTrackers = new ArrayList<>();
        reader.setStartPage(page);
        reader.setEndPage(page);
        String pageText = reader.getText(pdDocument);
        // Get tracking number
        List<String> pageTexts = List.of(pageText.split("\n"));
        String trackingNumber = "";
        boolean isLazada = true;
        try {
            trackingNumber = pageTexts.stream().filter(e -> e.toLowerCase().contains("tracking number")).findFirst().orElse("");
            trackingNumber = trackingNumber.replaceAll("(?i)tracking number", "").replaceAll("[\\n\\r\\t]+", "");

            // Must be shopee waybill
            if (StringUtils.isBlank(trackingNumber)) {
                isLazada = false;
                var pattern = Pattern.compile("^(?!63)[0-9]{12,}$");
                List<String> possibleOrderTrackers = pageTexts.stream().filter(e -> pattern.matcher(e).matches()).collect(Collectors.toList());

                trackingNumber = possibleOrderTrackers.stream().findFirst().orElse("");

            }
            if (StringUtils.isBlank(trackingNumber)) {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            throw new RuntimeException("No Tracking Number found. Iteration: " + page);
        }
        if (isLazada) {
            List<LazadaOrder> lazadaOrders = clientLazadaOrderRepository.findByTrackingCodeOrderByDateUploadedDesc(trackingNumber);

            lazadaOrders.forEach(order -> {
                orderTrackers.add(OrderTrackerUtil.mapLazadaToOrderTracker(order));
            });
        } else {
            List<ShopeeOrder> shopeeOrders = clientShopeeOrderRepository.findByTrackingNumberOrderByDateUploadedDesc(trackingNumber);
            shopeeOrders.forEach(order -> {
                orderTrackers.add(OrderTrackerUtil.mapShopeeToOrderTracker(order));
            });
        }

        return orderTrackers;
    }

    public SearchTrackingNumber getTrackingNumberPerPage(PDDocument pdDocument, int page) throws IOException {
        PDFTextStripper reader = new PDFTextStripper();
        List<OrderTracker> orderTrackers = new ArrayList<>();
        reader.setStartPage(page);
        reader.setEndPage(page);
        String pageText = reader.getText(pdDocument);
        // Get tracking number
        List<String> pageTexts = List.of(pageText.split("\n"));
        String trackingNumber = "";
        boolean isLazada = true;
        try {
            trackingNumber = pageTexts.stream().filter(e -> e.toLowerCase().contains("tracking number")).findFirst().orElse("");
            trackingNumber = trackingNumber.replaceAll("(?i)tracking number", "").replaceAll("[\\n\\r\\t]+", "");

            // Must be shopee waybill
            if (StringUtils.isBlank(trackingNumber)) {
                isLazada = false;
                var pattern = Pattern.compile("^(?!63)[0-9]{12,}$");
                List<String> possibleOrderTrackers = pageTexts.stream().filter(e -> pattern.matcher(e).matches()).collect(Collectors.toList());

                trackingNumber = possibleOrderTrackers.stream().findFirst().orElse("");

            }
            if (StringUtils.isBlank(trackingNumber)) {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            throw new RuntimeException("No Tracking Number found. Iteration: " + page);
        }


        return new SearchTrackingNumber(trackingNumber, isLazada);
    }

    public float mm2pt(float pt) {
        float result = pt * 72 / 25.4f;
        return result;
    }

    public float pt2mm(float pt) {
        float result = pt * 25.4f / 72;
        return result;
    }
}
