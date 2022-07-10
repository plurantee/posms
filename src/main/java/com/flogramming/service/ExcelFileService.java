package com.flogramming.service;

import com.flogramming.domain.LazadaOrder;
import com.flogramming.domain.Shop;
import com.flogramming.repository.ClientLazadaOrderRepository;
import com.flogramming.repository.LazadaOrderRepository;
import com.flogramming.util.OrdersUtil;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
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
    private ClientLazadaOrderRepository lazadaOrderRepository;

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
                lazadaOrder.id(search.get().getId());
            } else {
                lazadaOrder = new LazadaOrder();
            }
            OrdersUtil lazadaUtil = new OrdersUtil(lazadaOrder);
            lazadaUtil.shop(shop);
            lazadaUtil.orderItemId(row.get("orderItemId"));
            lazadaUtil.orderType(row.get("orderType"));
            lazadaUtil.guarantee(row.get("Guarantee"));
            lazadaUtil.deliveryType(row.get("deliveryType"));
            lazadaUtil.lazadaId(row.get("lazadaId"));
            lazadaUtil.sellerSku(row.get("sellerSku"));
            lazadaUtil.lazadaSku(row.get("lazadaSku"));
            lazadaUtil.wareHouse(row.get("wareHouse"));
            lazadaUtil.createTime(convertDate(row.get("createTime")));
            lazadaUtil.updateTime(convertDate(row.get("updateTime")));
            lazadaUtil.rtaSla(convertDate(row.get("rtsSla")));
            lazadaUtil.ttsSla(convertDate(row.get("ttsSla")));
            lazadaUtil.orderNumber(row.get("orderNumber"));
            lazadaUtil.invoiceRequired("Yes".equals(row.get("invoiceRequired")));
            lazadaUtil.invoiceNumber(row.get("invoiceNumber"));
            lazadaUtil.deliveryDate(convertDate(row.get("deliveredDate")));
            lazadaUtil.customerName(row.get("customerName"));
            lazadaUtil.customerEmail(row.get("customerEmail"));
            lazadaUtil.nationalRegistrationNumber(row.get("nationalRegistrationNumber"));
            lazadaUtil.shippingName(row.get("shippingName"));
            lazadaUtil.shippingAddress(row.get("shippingAddress"));
            lazadaUtil.shippingAddress2(row.get("shippingAddress2"));
            lazadaUtil.shippingAddress3(row.get("shippingAddress3"));
            lazadaUtil.shippingAddress4(row.get("shippingAddress4"));
            lazadaUtil.shippingAddress5(row.get("shippingAddress5"));
            lazadaUtil.shippingPhone(row.get("shippingPhone"));
            lazadaUtil.shippingPhone2(row.get("shippingPhone2"));
            lazadaUtil.shippingCity(row.get("shippingCity"));
            lazadaUtil.shippingPostCode(row.get("shippingPostCode"));
            lazadaUtil.shippingCountry(row.get("shippingCountry"));
            lazadaUtil.shippingRegion(row.get("shippingRegion"));
            lazadaUtil.billingName(row.get("billingName"));
            lazadaUtil.billingAddr(row.get("billingAddr"));
            lazadaUtil.billingAddr2(row.get("billingAddr2"));
            lazadaUtil.billingAddr3(row.get("billingAddr3"));
            lazadaUtil.billingAddr4(row.get("billingAddr4"));
            lazadaUtil.billingAddr5(row.get("billingAddr5"));
            lazadaUtil.billingPhone(row.get("billingPhone"));
            lazadaUtil.billingPhone2(row.get("billingPhone2"));
            lazadaUtil.billingCity(row.get("billingCity"));
            lazadaUtil.billingPostCode(row.get("billingPostCode"));
            lazadaUtil.billingCountry(row.get("billingCountry"));
            lazadaUtil.taxCode(row.get("taxCode"));
            lazadaUtil.branchNumber(row.get("branchNumber"));
            lazadaUtil.taxInvoiceRequested(row.get("taxInvoiceRequested"));
            lazadaUtil.payMethod(row.get("payMethod"));
            lazadaUtil.paidPrice(row.get("paidPrice"));
            lazadaUtil.unitPrice(row.get("unitPrice"));
            lazadaUtil.sellerDiscountTotal(row.get("sellerDiscountTotal"));
            lazadaUtil.shippingFee(row.get("shippingFee"));
            lazadaUtil.walletCredit(row.get("walletCredit"));
            lazadaUtil.itemName(row.get("itemName"));
            lazadaUtil.variation(row.get("variation"));
            lazadaUtil.cdShippingProvider(row.get("cdShippingProvider"));
            lazadaUtil.shippingProvider(row.get("shippingProvider"));
            lazadaUtil.shipmentTypeName(row.get("shipmentTypeName"));
            lazadaUtil.shippingProviderType(row.get("shippingProviderType"));
            lazadaUtil.cdTrackingCode(row.get("cdTrackingCode"));
            lazadaUtil.trackingCode(row.get("trackingCode"));
            lazadaUtil.trackingUrl(row.get("trackingUrl"));
            lazadaUtil.shippingProviderFM(row.get("shippingProviderFM"));
            lazadaUtil.trackingCodeFM(row.get("trackingCodeFM"));
            lazadaUtil.promisedShippingTime(convertDate(row.get("promisedShippingTime")));
            lazadaUtil.premium(row.get("premium"));
            lazadaUtil.status(row.get("status"));
            lazadaUtil.buyerFailedDeliveryReturnInitiator(row.get("buyerFailedDeliveryReturnInitiator"));
            lazadaUtil.buyerFailedDeliveryReason(row.get("buyerFailedDeliveryReason"));
            lazadaUtil.buyerFailedDeliveryDetail(row.get("buyerFailedDeliveryDetail"));
            lazadaUtil.buyerFailedDeliveryUserName(row.get("buyerFailedDeliveryUserName"));
            lazadaUtil.bundleId(row.get("bundleId"));
            lazadaUtil.bundleDiscount(row.get("bundleDiscount"));
            lazadaUtil.refundAmount(row.get("refundAmount"));

            lazadaOrderRepository.save(lazadaUtil.getLazadaOrder());
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

    public ZonedDateTime convertDate(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return ZonedDateTime.parse(date, formatter);
    }
}
