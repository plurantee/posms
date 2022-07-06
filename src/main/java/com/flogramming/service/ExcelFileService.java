package com.flogramming.service;

import com.flogramming.domain.LazadaOrder;
import com.flogramming.domain.Shop;
import com.flogramming.repository.LazadaOrderRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        for(Cell cell: columnNamesRow) {
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
            for (int j=0; j < columnNames.size(); j++) {
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
        excelHashMap.remove(0); // Column rows
        for (HashMap<String, String> row : excelHashMap.values()) {
            LazadaOrder lazadaOrder = new LazadaOrder();
            lazadaOrder.setShop(shop);
            lazadaOrder.setOrderItemId(row.get("orderItemId"));
            lazadaOrder.setOrderType(row.get("orderType"));
            lazadaOrder.setGuarantee(row.get("Guarantee"));
            lazadaOrder.setDeliveryType(row.get("deliveryType"));
            lazadaOrder.setLazadaId(row.get("lazadaId"));
            lazadaOrder.setSellerSku(row.get("sellerSku"));
            lazadaOrder.setLazadaSku(row.get("lazadaSku"));
            result.add(lazadaOrder);
        }
        lazadaOrderRepository.saveAll(result);
        return result;
        /*
        * orderItemId
            orderType
            Guarantee
            deliveryType
            lazadaId
            sellerSku
            lazadaSku
            wareHouse
            createTime
            updateTime
            rtsSla
            ttsSla
            orderNumber
            invoiceRequired
            invoiceNumber
            deliveredDate
            customerName
            customerEmail
            nationalRegistrationNumber
            shippingName
            shippingAddress
            shippingAddress2
            shippingAddress3
            shippingAddress4
            shippingAddress5
            shippingPhone
            shippingPhone2
            shippingCity
            shippingPostCode
            shippingCountry
            shippingRegion
            billingName
            billingAddr
            billingAddr2
            billingAddr3
            billingAddr4
            billingAddr5
            billingPhone
            billingPhone2
            billingCity
            billingPostCode
            billingCountry
            taxCode
            branchNumber
            taxInvoiceRequested
            payMethod
            paidPrice
            unitPrice
            sellerDiscountTotal
            shippingFee
            walletCredit
            itemName
            variation
            cdShippingProvider
            shippingProvider
            shipmentTypeName
            shippingProviderType
            cdTrackingCode
            trackingCode
            trackingUrl
            shippingProviderFM
            trackingCodeFM
            trackingUrlFM
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
}
