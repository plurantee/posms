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
            String trackingNumber = getTrackingNumberPerPage(pdDocument, i+1);
            Map<String, Long> ordersMap = orders.stream()
                .filter(order -> trackingNumber.equals(order.getBarcodeNumber()))
                .collect(Collectors.groupingBy(e -> e.getSkuReference(), Collectors.counting()));

            PDPageContentStream contentStream = new PDPageContentStream(pdDocument, page, PDPageContentStream.AppendMode.PREPEND, true);
            // - Paddings
            float pageHeight = mm2pt(150);
            float pageWidth = mm2pt(104);





            PDRectangle mediabox = new PDRectangle(-30, -110, pageWidth, pageHeight);
            page.setMediaBox(mediabox);
            page.setCropBox(mediabox);
            // - End Paddings

            contentStream.setMiterLimit(1);
            contentStream.beginText();
            // - Table
            fillPage(pdDocument, page, ordersMap);
            Matrix matrix = new Matrix();
            float xScale = 0.8f;
            float yScale = 0.75f;
            matrix.scale(xScale, yScale);
            contentStream.transform(matrix);
            contentStream.endText();
            contentStream.close();
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        pdDocument.save(byteArrayOutputStream);
        pdDocument.close();
        InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        return IOUtils.toByteArray(inputStream);
    }

    private static void fillPage(PDDocument pdDocument, PDPage page, Map<String, Long> ordersMap) throws IOException {
        float yTableStart = page.getMediaBox().getHeight() - 530;
        float tableWidth = page.getMediaBox().getWidth() - 0;
        BaseTable table = new BaseTable(0, yTableStart, -400, tableWidth, -30, pdDocument, page, true, true);
        BaseTable table2 = new BaseTable(0, yTableStart, -400, tableWidth, 150, pdDocument, page, true, true);
        // Table 1
        Row<PDPage> hRow = table.createRow(10);
        Cell<PDPage> cell = null;
        hRow.createCell(30, "SKU", HorizontalAlignment.LEFT, VerticalAlignment.TOP).setFont(PDType1Font.HELVETICA);
        hRow.createCell(30, "PCS", HorizontalAlignment.LEFT, VerticalAlignment.TOP).setFont(PDType1Font.HELVETICA);
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
            t1Row.createCell(30, key);
            t1Row.createCell(30, value);
        }

        table.draw();
        // - End Table 1

        // Table 2
        Row<PDPage> hRow2 = table2.createRow(10);
        Cell<PDPage> cell2 = null;
        hRow2.createCell(30, "SKU", HorizontalAlignment.LEFT, VerticalAlignment.TOP).setFont(PDType1Font.HELVETICA);
        hRow2.createCell(30, "PCS", HorizontalAlignment.LEFT, VerticalAlignment.TOP).setFont(PDType1Font.HELVETICA);
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
            t2Row.createCell(30, key);
            t2Row.createCell(30, value);
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
            List<LazadaOrder> lazadaOrders = clientLazadaOrderRepository.findByTrackingCode(trackingNumber);

            lazadaOrders.forEach(order -> {
                orderTrackers.add(OrderTrackerUtil.mapLazadaToOrderTracker(order));
            });
        } else {
            List<ShopeeOrder> shopeeOrders = clientShopeeOrderRepository.findByTrackingNumber(trackingNumber);
            shopeeOrders.forEach(order -> {
                orderTrackers.add(OrderTrackerUtil.mapShopeeToOrderTracker(order));
            });
        }

        return orderTrackers;
    }

    public String getTrackingNumberPerPage(PDDocument pdDocument, int page) throws IOException {
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


        return trackingNumber;
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
