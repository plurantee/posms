package com.flogramming.service;

import com.flogramming.domain.OrderTracker;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WaybillFileService {

    private final Logger log = LoggerFactory.getLogger(WaybillFileService.class);

    public byte[] processWaybill(MultipartFile file, List<OrderTracker> orders) throws IOException {
        PDDocument pdDocument = PDDocument.load(file.getInputStream());

        PDPage page = pdDocument.getPage(0);
        PDPageContentStream contentStream = new PDPageContentStream(pdDocument, page, true, true);
        //Begin the Content stream
        contentStream.beginText();

        //Setting the font to the Content stream
        contentStream.setFont(PDType1Font.COURIER_BOLD, 12);

        contentStream.newLineAtOffset(page.getTrimBox().getWidth() - 130, page.getTrimBox().getHeight() - 15);

        Map<String, Long> ordersMap = orders.stream().collect(Collectors.groupingBy(e -> e.getSkuReference(),
            Collectors.counting()));
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
}
