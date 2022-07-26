package com.flogramming.service;

import com.flogramming.domain.LazadaOrder;
import com.flogramming.domain.OrderTracker;
import com.flogramming.domain.UploadWaybillResponse;
import com.flogramming.repository.ClientLazadaOrderRepository;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class WaybillFileService {

    private final Logger log = LoggerFactory.getLogger(WaybillFileService.class);

    @Autowired
    private ClientLazadaOrderRepository clientLazadaOrderRepository;

    public byte[] processWaybill(MultipartFile file, List<OrderTracker> orders) throws IOException {
        PDDocument pdDocument = PDDocument.load(file.getInputStream());

        PDPage page = pdDocument.getPage(0);
        PDPageContentStream contentStream = new PDPageContentStream(pdDocument, page, true, true);
        //Begin the Content stream
        contentStream.beginText();

        //Setting the font to the Content stream
        contentStream.setFont(PDType1Font.COURIER_BOLD, 12);

        contentStream.newLineAtOffset(page.getTrimBox().getWidth() - 130, page.getTrimBox().getHeight() - 15);

        Map<String, Long> ordersMap = orders.stream().collect(Collectors.groupingBy(e -> e.getSkuReference(), Collectors.counting()));
        for (Map.Entry<String, Long> entry : ordersMap.entrySet()) {
            contentStream.showText(entry.getValue() + "pcs " + entry.getKey());
            contentStream.newLine();
        }

        contentStream.endText();
        contentStream.close();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        pdDocument.save(byteArrayOutputStream);
        pdDocument.close();
        InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        return IOUtils.toByteArray(inputStream);
    }

    public OrderTracker mapLazadaToOrderTracker(LazadaOrder lazadaOrder) {
        OrderTracker orderTracker = new OrderTracker();
        orderTracker.setSite("LAZADA");

        orderTracker.setId(lazadaOrder.getId());
        orderTracker.setOrderItemId(lazadaOrder.getOrderItemId());
        orderTracker.setSkuReference(lazadaOrder.getSellerSku());
        orderTracker.setStatus(lazadaOrder.getStatus());
        orderTracker.setOrderType(lazadaOrder.getOrderType());
        orderTracker.setBarcodeNumber(lazadaOrder.getTrackingCode());
        return orderTracker;
    }

    public List<OrderTracker> viewWaybill(MultipartFile file) throws IOException {
        PDDocument pdDocument = PDDocument.load(file.getInputStream());

        PDFTextStripper reader = new PDFTextStripper();
        String pageText = reader.getText(pdDocument);
        // Get tracking number
        List<String> pageTexts = List.of(pageText.split("\n"));
        String trackingNumber = "";
        try {
            trackingNumber = pageTexts.stream().filter(e -> e.toLowerCase().contains("tracking number")).findFirst().get();
            trackingNumber = trackingNumber.replaceAll("(?i)tracking number", "").replaceAll("[\\n\\r\\t]+", "");
        } catch (Exception e) {
            throw new RuntimeException("No Tracking Number found");
        }

        List<LazadaOrder> lazadaOrders = clientLazadaOrderRepository.findByTrackingCode(trackingNumber);
        List<OrderTracker> orderTrackers = new ArrayList<>();
        lazadaOrders.forEach(order -> {
            orderTrackers.add(mapLazadaToOrderTracker(order));
        });
        return orderTrackers;
    }
}