package com.flogramming.service;

import com.flogramming.domain.LazadaOrder;
import com.flogramming.domain.Shop;
import com.flogramming.repository.LazadaOrderRepository;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExcelFileService {

    @Autowired
    private LazadaOrderRepository lazadaOrderRepository;

    public List<LazadaOrder> processLazadaExcelFile(MultipartFile file, Shop shop) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Map<Integer, HashMap<String, String>> excelHashMap = new HashMap<>();
        List<String> columnNames = new ArrayList<>();
        Row columnNamesRow = sheet.getRow(0);
        for (Cell cell : columnNamesRow) {
            switch (cell.getCellType()) {
                case STRING:
                    columnNames.add(cell.getStringCellValue());
                    System.out.println(cell.getStringCellValue());
                    break;
            }
        }
        int i = 0;
        for (Row row : sheet) {
            excelHashMap.put(i, new HashMap<>());
            for (int j = 0; j < columnNames.size(); j++) {
                switch (row.getCell(j).getCellType()) {
                    case STRING:
                        excelHashMap.get(i).put(columnNames.get(j), row.getCell(j).getRichStringCellValue().getString());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(row.getCell(j))) {
                            excelHashMap.get(i).put(columnNames.get(j), row.getCell(j).getDateCellValue() + "");
                        } else {
                            excelHashMap.get(i).put(columnNames.get(j), row.getCell(j).getNumericCellValue() + "" + "");
                        }
                        break;
                    case BOOLEAN:
                        excelHashMap.get(i).put(columnNames.get(j), row.getCell(j).getBooleanCellValue() + "" + "");
                        break;
                    case FORMULA:
                        excelHashMap.get(i).put(columnNames.get(j), row.getCell(j).getCellFormula() + "");
                        break;
                    default:
                        excelHashMap.get(i).put(columnNames.get(j), " ");
                }
                excelHashMap.get(i).put(columnNames.get(j), row.getCell(j).getStringCellValue());
            }

            i++;
        }
        return setFromExcelHashMap(excelHashMap, shop);
    }

    public List<LazadaOrder> setFromExcelHashMap(Map<Integer, HashMap<String, String>> excelHashMap, Shop shop) {
        List<LazadaOrder> result = new ArrayList<>();
        // 04 Jul 2022 11:46
        excelHashMap.remove(0); // Column rows
        for (HashMap<String, String> row : excelHashMap.values()) {
            LazadaOrder lazadaOrder = null;
            var search = lazadaOrderRepository.findByOrderItemId(row.get("orderItemId"));
            if (search.isPresent()) {
                lazadaOrder = search.get();
            } else {
                lazadaOrder = new LazadaOrder();
            }
            lazadaOrder.Shop(shop);
            lazadaOrder.orderItemId(row.get("orderItemId"));
            lazadaOrder.orderType(row.get("orderType"));
            lazadaOrder.guarantee(row.get("Guarantee"));
            lazadaOrder.deliveryType(row.get("deliveryType"));
            lazadaOrder.lazadaId(row.get("lazadaId"));
            lazadaOrder.sellerSku(row.get("sellerSku"));
            lazadaOrder.lazadaSku(row.get("lazadaSku"));
            lazadaOrder.wareHouse(row.get("wareHouse"));
            lazadaOrder.createTime(convertDate(row.get("createTime")));
            lazadaOrder.updateTime(convertDate(row.get("updateTime")));
            lazadaOrder.rtaSla(convertDate(row.get("rtsSla")));
            lazadaOrder.ttsSla(convertDate(row.get("ttsSla")));
            lazadaOrder.orderNumber(row.get("orderNumber"));
            lazadaOrder.invoiceRequired("Yes".equals(row.get("invoiceRequired")));
            lazadaOrder.invoiceNumber(row.get("invoiceNumber"));
            lazadaOrder.deliveryDate(convertDate(row.get("deliveredDate")));
            lazadaOrder.customerName(row.get("customerName"));
            lazadaOrder.customerEmail(row.get("customerEmail"));
            lazadaOrder.nationalRegistrationNumber(row.get("nationalRegistrationNumber"));
            lazadaOrder.shippingName(row.get("shippingName"));
            lazadaOrder.shippingAddress(row.get("shippingAddress"));
            lazadaOrder.shippingAddress2(row.get("shippingAddress2"));
            lazadaOrder.shippingAddress3(row.get("shippingAddress3"));
            lazadaOrder.shippingAddress4(row.get("shippingAddress4"));
            lazadaOrder.shippingAddress5(row.get("shippingAddress5"));
            lazadaOrder.shippingPhone(row.get("shippingPhone"));
            lazadaOrder.shippingPhone2(row.get("shippingPhone2"));
            lazadaOrder.shippingCity(row.get("shippingCity"));
            lazadaOrder.shippingPostCode(row.get("shippingPostCode"));
            lazadaOrder.shippingCountry(row.get("shippingCountry"));
            lazadaOrder.shippingRegion(row.get("shippingRegion"));
            lazadaOrder.billingName(row.get("billingName"));
            lazadaOrder.billingAddr(row.get("billingAddr"));
            lazadaOrder.billingAddr2(row.get("billingAddr2"));
            lazadaOrder.billingAddr3(row.get("billingAddr3"));
            lazadaOrder.billingAddr4(row.get("billingAddr4"));
            lazadaOrder.billingAddr5(row.get("billingAddr5"));
            lazadaOrder.billingPhone(row.get("billingPhone"));
            lazadaOrder.billingPhone2(row.get("billingPhone2"));
            lazadaOrder.billingCity(row.get("billingCity"));
            lazadaOrder.billingPostCode(row.get("billingPostCode"));
            lazadaOrder.billingCountry(row.get("billingCountry"));
            lazadaOrder.taxCode(row.get("taxCode"));
            lazadaOrder.branchNumber(row.get("branchNumber"));
            lazadaOrder.taxInvoiceRequested(row.get("taxInvoiceRequested"));
            lazadaOrder.payMethod(row.get("payMethod"));
            lazadaOrder.paidPrice(row.get("paidPrice"));
            lazadaOrder.unitPrice(row.get("unitPrice"));
            lazadaOrder.sellerDiscountTotal(row.get("sellerDiscountTotal"));
            lazadaOrder.shippingFee(row.get("shippingFee"));
            lazadaOrder.walletCredit(row.get("walletCredit"));
            lazadaOrder.itemName(row.get("itemName"));
            lazadaOrder.variation(row.get("variation"));
            lazadaOrder.cdShippingProvider(row.get("cdShippingProvider"));
            lazadaOrder.shippingProvider(row.get("shippingProvider"));
            lazadaOrder.shipmentTypeName(row.get("shipmentTypeName"));
            lazadaOrder.shippingProviderType(row.get("shippingProviderType"));
            lazadaOrder.cdTrackingCode(row.get("cdTrackingCode"));
            lazadaOrder.trackingCode(row.get("trackingCode"));
            lazadaOrder.trackingUrl(row.get("trackingUrl"));
            lazadaOrder.shippingProviderFM(row.get("shippingProviderFM"));
            lazadaOrder.trackingCodeFM(row.get("trackingCodeFM"));
            lazadaOrder.promisedShippingTime(convertDate(row.get("promisedShippingTime")));
            lazadaOrder.premium(row.get("premium"));
            lazadaOrder.status(row.get("status"));
            lazadaOrder.buyerFailedDeliveryReturnInitiator(row.get("buyerFailedDeliveryReturnInitiator"));
            lazadaOrder.buyerFailedDeliveryReason(row.get("buyerFailedDeliveryReason"));
            lazadaOrder.buyerFailedDeliveryDetail(row.get("buyerFailedDeliveryDetail"));
            lazadaOrder.buyerFailedDeliveryUserName(row.get("buyerFailedDeliveryUserName"));
            lazadaOrder.bundleId(row.get("bundleId"));
            lazadaOrder.bundleDiscount(row.get("bundleDiscount"));
            lazadaOrder.refundAmount(row.get("refundAmount"));

            lazadaOrderRepository.save(lazadaOrder);
        }
        return lazadaOrderRepository.findByShop(shop);
        /*
            promisedShippingTime
            premium
            status
            buyerFailedDeliveryReturnInitiator
            buyerFailedDeliveryReason
            buyerFailedDeliveryDetail
            buyerFailedDeliveryUserName
            bundleId
            bundleDiscount
            refundAmount
        * */
    }

    public LocalDateTime convertDate(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return LocalDateTime.parse(date, formatter);
    }
}
