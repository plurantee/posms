package com.flogramming.service;

import com.flogramming.domain.Client;
import com.flogramming.domain.Inventory;
import com.flogramming.domain.LazadaOrder;
import com.flogramming.domain.LazadaOrderPayments;
import com.flogramming.domain.ShopeeOrder;
import com.flogramming.repository.ClientInventoryRepository;
import com.flogramming.repository.ClientLazadaOrderPaymentsRepository;
import com.flogramming.repository.ClientLazadaOrderRepository;
import com.flogramming.repository.ClientShopeeOrderRepository;
import com.flogramming.util.OrdersUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExcelFileService {

    private final Logger log = LoggerFactory.getLogger(ExcelFileService.class);

    @Autowired
    private ClientLazadaOrderRepository lazadaOrderRepository;

    @Autowired
    private ClientShopeeOrderRepository clientShopeeOrderRepository;

    @Autowired
    private ClientUserService clientUserService;

    @Autowired
    private ClientInventoryRepository clientInventoryRepository;

    @Autowired
    private ClientLazadaOrderPaymentsRepository clientLazadaOrderPaymentsRepository;

    /**
     * Lazada
     */
    public Page<LazadaOrder> processLazadaExcelFile(MultipartFile file) throws IOException {
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
                        excelHashMap.get(i).put(columnNames.get(j),
                            row.getCell(j).getRichStringCellValue().getString());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(row.getCell(j))) {
                            excelHashMap.get(i).put(columnNames.get(j), row.getCell(j).getDateCellValue() + "");
                        }
                        else {
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
            }

            i++;
        }
        return setLazadaOrderFromExcelHashMap(excelHashMap);
    }

    public Page<LazadaOrder> setLazadaOrderFromExcelHashMap(Map<Integer, HashMap<String, String>> excelHashMap) {
        List<LazadaOrder> result = new ArrayList<>();
        // 04 Jul 2022 11:46
        excelHashMap.remove(0); // Column rows
        Client client = clientUserService.getCurrentUser().getClientCode();

        // Clear database for order item ids that is in this excel
        for (HashMap<String, String> row : excelHashMap.values()) {
            String orderItemId = row.get("orderItemId");
            if (!StringUtils.isEmpty(orderItemId)) {
                long numbersDeleted = lazadaOrderRepository.deleteByOrderItemId(orderItemId);
                log.info("Clearing database for orderItemId (Lazada): " + orderItemId + ". Numbers deleted: " + numbersDeleted);
            }
        }

        for (HashMap<String, String> row : excelHashMap.values()) {
            LazadaOrder lazadaOrder = new LazadaOrder();
            lazadaOrder.setClient(client);

            OrdersUtil lazadaUtil = new OrdersUtil(lazadaOrder);
            lazadaUtil.orderItemId(row.get("orderItemId"));
            lazadaUtil.orderType(row.get("orderType"));
            lazadaUtil.guarantee(row.get("Guarantee"));
            lazadaUtil.deliveryType(row.get("deliveryType"));
            lazadaUtil.lazadaId(row.get("lazadaId"));
            lazadaUtil.sellerSku(row.get("sellerSku"));
            lazadaUtil.lazadaSku(row.get("lazadaSku"));
            lazadaUtil.wareHouse(row.get("wareHouse"));
            lazadaUtil.createTime(convertLazadaDate(row.get("createTime")));
            lazadaUtil.updateTime(convertLazadaDate(row.get("updateTime")));
            lazadaUtil.rtaSla(convertLazadaDate(row.get("rtsSla")));
            lazadaUtil.ttsSla(convertLazadaDate(row.get("ttsSla")));
            lazadaUtil.orderNumber(row.get("orderNumber"));
            lazadaUtil.invoiceRequired("Yes".equals(row.get("invoiceRequired")));
            lazadaUtil.invoiceNumber(row.get("invoiceNumber"));
            lazadaUtil.deliveryDate(convertLazadaDate(row.get("deliveredDate")));
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
            lazadaUtil.paidPrice(valueOf(row.get("paidPrice")));
            lazadaUtil.unitPrice(valueOf(row.get("unitPrice")));
            lazadaUtil.sellerDiscountTotal(valueOf(row.get("sellerDiscountTotal")));
            lazadaUtil.shippingFee(valueOf(row.get("shippingFee")));
            lazadaUtil.walletCredit(valueOf(row.get("walletCredit")));
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
            lazadaUtil.promisedShippingTime(convertLazadaDate(row.get("promisedShippingTime")));
            lazadaUtil.premium(row.get("premium"));
            lazadaUtil.status(row.get("status"));
            lazadaUtil.buyerFailedDeliveryReturnInitiator(row.get("buyerFailedDeliveryReturnInitiator"));
            lazadaUtil.buyerFailedDeliveryReason(row.get("buyerFailedDeliveryReason"));
            lazadaUtil.buyerFailedDeliveryDetail(row.get("buyerFailedDeliveryDetail"));
            lazadaUtil.buyerFailedDeliveryUserName(row.get("buyerFailedDeliveryUserName"));
            lazadaUtil.bundleId(row.get("bundleId"));
            lazadaUtil.bundleDiscount(valueOf(row.get("bundleDiscount")));
            lazadaUtil.refundAmount(valueOf(row.get("refundAmount")));
            String sellerSku = lazadaUtil.getLazadaOrder().getSellerSku();
            Inventory inventory = clientInventoryRepository.findBySku(sellerSku);
            if (inventory == null) {
                inventory = new Inventory();
                inventory.setCost(0.0);
                inventory.setPrice(0.0);
                inventory.setSku(sellerSku);
                inventory.setStocks(10);
                inventory.setThreshold(3);
                inventory.setClient(client);
                clientInventoryRepository.save(inventory);
            }
            inventory.setStocks(inventory.getStocks() - 1);
            clientInventoryRepository.save(inventory);
            lazadaUtil.getLazadaOrder().setInventory(inventory);
            if (!StringUtils.isEmpty(lazadaUtil.lazadaOrder.getOrderItemId())) {
                lazadaOrderRepository.save(lazadaUtil.getLazadaOrder());
            }
        }
        return lazadaOrderRepository.findByClient(client, Pageable.ofSize(10));
    }

    /**
     * Shopee
     */
    public Page<ShopeeOrder> processShopeeExcelFile(MultipartFile file) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Map<Integer, HashMap<String, String>> excelHashMap = new HashMap<>();
        List<String> columnNames = new ArrayList<>();
        Row columnNamesRow = sheet.getRow(0);
        for (Cell cell : columnNamesRow) {
            switch (cell.getCellType()) {
                case STRING:
                    columnNames.add(cell.getStringCellValue());
                    String original = cell.getStringCellValue();
                    String[] words = original.split("[\\W_]+");
                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < words.length; i++) {
                        String word = words[i];
                        word = word.isEmpty() ? word :
                            Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();

                        builder.append(word);
                    }
                    String camelCase = builder.toString();
                    System.out.println("shopeeOrder.set" + camelCase + "(row.get(\"" + original + "\"));");
                    break;
            }
        }
        int i = 0;
        for (Row row : sheet) {
            excelHashMap.put(i, new HashMap<>());
            for (int j = 0; j < columnNames.size(); j++) {
                switch (row.getCell(j).getCellType()) {
                    case STRING:
                        excelHashMap.get(i).put(columnNames.get(j),
                            row.getCell(j).getRichStringCellValue().getString());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(row.getCell(j))) {
                            excelHashMap.get(i).put(columnNames.get(j), row.getCell(j).getDateCellValue() + "");
                        }
                        else {
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
            }

            i++;
        }
        return setShopeeOrderFromExcelHashMap(excelHashMap);
    }

    public Page<ShopeeOrder> setShopeeOrderFromExcelHashMap(Map<Integer, HashMap<String, String>> excelHashMap) {
        List<LazadaOrder> result = new ArrayList<>();
        Client client = clientUserService.getCurrentUser().getClientCode();
        // 04 Jul 2022 11:46
        excelHashMap.remove(0); // Column rows
        for (HashMap<String, String> row : excelHashMap.values()) {
            String orderItemId = row.get("Order ID");
            if (!StringUtils.isEmpty(orderItemId)) {
                long numbersDeleted = clientShopeeOrderRepository.deleteByOrderId(orderItemId);
                log.info("Clearing database for Order ID (Shopee): " + orderItemId + ". Numbers deleted: " + numbersDeleted);
            }
        }
        for (HashMap<String, String> row : excelHashMap.values()) {
            ShopeeOrder shopeeOrder = new ShopeeOrder();

            shopeeOrder.setClient(client);
            shopeeOrder.setOrderId(row.get("Order ID"));
            shopeeOrder.setOrderStatus(row.get("Order Status"));
            shopeeOrder.setReturnRefundStatus(row.get("Return / Refund Status"));
            shopeeOrder.setTrackingNumber(row.get("Tracking Number*"));
            shopeeOrder.setShippingOption(row.get("Shipping Option"));
            shopeeOrder.setShipmentMethod(row.get("Shipment Method"));
            shopeeOrder.setEstimatedShipOutDate(convertShopeeDate(row.get("Estimated Ship Out Date")));
            shopeeOrder.setShipTime(convertShopeeDate(row.get("Ship Time")));
            shopeeOrder.setOrderCreationDate(convertShopeeDate(row.get("Order Creation Date")));
            shopeeOrder.setOrderPaidTime(convertShopeeDate(row.get("Order Paid Time")));
            shopeeOrder.setParentSkuReferenceNo(row.get("Parent SKU Reference No."));
            shopeeOrder.setProductName(row.get("Product Name"));
            shopeeOrder.setSkuReferenceNo(row.get("SKU Reference No."));
            shopeeOrder.setVariationName(row.get("Variation Name"));
            shopeeOrder.setOriginalPrice(valueOf(row.get("Original Price")));
            shopeeOrder.setDealPrice(valueOf(row.get("Deal Price")));
            shopeeOrder.setQuantity(valueOf(row.get("Quantity")));
            shopeeOrder.setProductSubtotal(valueOf(row.get("Product Subtotal")));
            shopeeOrder.setTotalDiscount(valueOf(row.get("Total Discount(PHP)")));
            shopeeOrder.setPriceDiscountFromSeller(valueOf(row.get("Price Discount(from Seller)(PHP)")));
            shopeeOrder.setShopeeRebate(valueOf(row.get("Shopee Rebate(PHP)")));
            shopeeOrder.setSkuTotalWeight(row.get("SKU Total Weight"));
            shopeeOrder.setNumberOfItemsInOrder(row.get("Number of Items in Order"));
            shopeeOrder.setOrderTotalWeight(row.get("Order Total Weight"));
            shopeeOrder.setSellerVoucher(valueOf(row.get("Seller Voucher(PHP)")));
            shopeeOrder.setSellerAbsorbedCoinCashback(row.get("Seller Absorbed Coin Cashback"));
            shopeeOrder.setShopeeVoucher(valueOf(row.get("Shopee Voucher(PHP)")));
            shopeeOrder.setBundleDealsIndicatorYN(row.get("Bundle Deals Indicator(Y/N)"));
            shopeeOrder.setShopeeBundleDiscount(valueOf(row.get("Shopee Bundle Discount(PHP)")));
            shopeeOrder.setSellerBundleDiscount(valueOf(row.get("Seller Bundle Discount(PHP)")));
            shopeeOrder.setShopeeCoinsOffset(valueOf(row.get("Shopee Coins Offset(PHP)")));
            shopeeOrder.setCreditCardDiscountTotal(valueOf(row.get("Credit Card Discount Total(PHP)")));
            shopeeOrder.setProductsPricePaidByBuyer(valueOf(row.get("Products' Price Paid by Buyer (PHP)")));
            shopeeOrder.setBuyerPaidShippingFee(valueOf(row.get("Buyer Paid Shipping Fee")));
            shopeeOrder.setShippingRebateEstimate(valueOf(row.get("Shipping Rebate Estimate")));
            shopeeOrder.setReverseShippingFee(valueOf(row.get("Reverse Shipping Fee")));
            shopeeOrder.setServiceFee(valueOf(row.get("Service Fee")));
            shopeeOrder.setGrandTotal(valueOf(row.get("Grand Total")));
            shopeeOrder.setEstimatedShippingFee(valueOf(row.get("Estimated Shipping Fee")));
            shopeeOrder.setUsernameBuyer(row.get("Username (Buyer)"));
            shopeeOrder.setReceiverName(row.get("Receiver Name"));
            shopeeOrder.setPhoneNumber(row.get("Phone Number"));
            shopeeOrder.setDeliveryAddress(row.get("Delivery Address"));
            shopeeOrder.setTown(row.get("Town"));
            shopeeOrder.setDistrict(row.get("District"));
            shopeeOrder.setProvince(row.get("Province"));
            shopeeOrder.setRegion(row.get("Region"));
            shopeeOrder.setCountry(row.get("Country"));
            shopeeOrder.setZipCode(row.get("Zip Code"));
            shopeeOrder.setRemarkFromBuyer(row.get("Remark from buyer"));
            shopeeOrder.setOrderCompleteTime(convertShopeeDate(row.get("Order Complete Time")));
            shopeeOrder.setNote(row.get("Note"));
            String sellerSku = shopeeOrder.getSkuReferenceNo();
            Inventory inventory = clientInventoryRepository.findBySku(sellerSku);
            if (inventory == null) {
                inventory = new Inventory();
                inventory.setCost(0.0);
                inventory.setPrice(0.0);
                inventory.setSku(sellerSku);
                inventory.setStocks(10);
                inventory.setThreshold(3);
                inventory.setClient(client);
                clientInventoryRepository.save(inventory);
            }
            inventory.setStocks(inventory.getStocks() - 1);
            clientInventoryRepository.save(inventory);
            shopeeOrder.setInventory(inventory);
            if (!StringUtils.isEmpty(shopeeOrder.getOrderId())) {
                clientShopeeOrderRepository.save(shopeeOrder);
            }

        }
        return clientShopeeOrderRepository.findByClient(client, Pageable.ofSize(10));
    }


    /**
     * Lazada Payments
     */
    public List<LazadaOrderPayments> processLazadaPaymentsExcelFile(MultipartFile file) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Map<Integer, HashMap<String, String>> excelHashMap = new HashMap<>();
        List<String> columnNames = new ArrayList<>();
        Row columnNamesRow = sheet.getRow(0);
        for (Cell cell : columnNamesRow) {
            switch (cell.getCellType()) {
                case STRING:
                    columnNames.add(cell.getStringCellValue());
                    String original = cell.getStringCellValue();
                    String[] words = original.split("[\\W_]+");
                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < words.length; i++) {
                        String word = words[i];
                        word = word.isEmpty() ? word :
                            Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();

                        builder.append(word);
                    }
                    String camelCase = builder.toString();
                    System.out.println("lazadaOrderPayment.set" + camelCase + "(row.get(\"" + original + "\"));");
                    break;
            }
        }
        int i = 0;
        for (Row row : sheet) {
            excelHashMap.put(i, new HashMap<>());
            for (int j = 0; j < columnNames.size(); j++) {
                switch (row.getCell(j).getCellType()) {
                    case STRING:
                        excelHashMap.get(i).put(columnNames.get(j),
                            row.getCell(j).getRichStringCellValue().getString());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(row.getCell(j))) {
                            excelHashMap.get(i).put(columnNames.get(j), row.getCell(j).getDateCellValue() + "");
                        }
                        else {
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
            }

            i++;
        }
        return setLazadaOrderPaymentsFromExcelHashMap(excelHashMap);
    }

    private List<LazadaOrderPayments> setLazadaOrderPaymentsFromExcelHashMap(Map<Integer, HashMap<String, String>> excelHashMap) {
        List<LazadaOrderPayments> result = new ArrayList<>();
        // 04 Jul 2022 11:46
        excelHashMap.remove(0); // Column rows
        for (HashMap<String, String> row : excelHashMap.values()) {
            LazadaOrderPayments lazadaOrderPayment = new LazadaOrderPayments();
            lazadaOrderPayment.setTransactionDate(convertLazadaPaymentDate(row.get("Transaction Date")));
            lazadaOrderPayment.setTransactionType(row.get("Transaction Type"));
            lazadaOrderPayment.setFeeName(row.get("Fee Name"));
            lazadaOrderPayment.setTransactionNumber(row.get("Transaction Number"));
            lazadaOrderPayment.setDetails(row.get("Details"));
            lazadaOrderPayment.setSellerSku(row.get("Seller SKU"));
            lazadaOrderPayment.setLazadaSku(row.get("Lazada SKU"));
            lazadaOrderPayment.setAmount(valueOf(row.get("Amount")));
            lazadaOrderPayment.setVatInAmount(valueOf(row.get("VAT in Amount")));
            lazadaOrderPayment.setWhtAmount(valueOf(row.get("WHT Amount")));
            lazadaOrderPayment.setWhtIncludedInAmount("Yes".equalsIgnoreCase(row.get("WHT included in Amount")));
            lazadaOrderPayment.setStatement(row.get("Statement"));
            lazadaOrderPayment.setPaidStatus(row.get("Paid Status"));
            lazadaOrderPayment.setOrderNo(row.get("Order No."));
            lazadaOrderPayment.setOrderItemNo(row.get("Order Item No."));
            lazadaOrderPayment.setOrderItemStatus(row.get("Order Item Status"));
            lazadaOrderPayment.setShippingProvider(row.get("Shipping Provider"));
            lazadaOrderPayment.setShippingSpeed(row.get("Shipping Speed"));
            lazadaOrderPayment.setShipmentType(row.get("Shipment Type"));
            lazadaOrderPayment.setReference(row.get("Reference"));
            lazadaOrderPayment.setComment(row.get("Comment"));
            lazadaOrderPayment.setPaymentRefId(row.get("PaymentRefId"));

            List<LazadaOrder> lazadaOrder =
                lazadaOrderRepository.findByOrderItemId(lazadaOrderPayment.getOrderItemNo());
            if (lazadaOrder.isEmpty()) {
                lazadaOrderPayment.setLazadaOrder(null);
            }
            else {
                lazadaOrderPayment.setLazadaOrder(lazadaOrder.stream().findFirst().get());
            }
            result.add(lazadaOrderPayment);

        }
        clientLazadaOrderPaymentsRepository.saveAll(result);
        return result;
    }

    /**
     * Utils
     */
    public ZonedDateTime convertLazadaDate(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        LocalDateTime ldt = LocalDateTime.parse(date, formatter);
        ZoneId zoneId = ZoneId.of("Asia/Manila");
        return ldt.atZone(zoneId);
    }

    public ZonedDateTime convertLazadaPaymentDate(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        LocalDate ldt = LocalDate.parse(date, formatter);
        ZoneId zoneId = ZoneId.of("Asia/Manila");
        return ldt.atStartOfDay(zoneId);
    }

    public ZonedDateTime convertShopeeDate(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime ldt = LocalDateTime.parse(date, formatter);
        ZoneId zoneId = ZoneId.of("Asia/Manila");
        return ldt.atZone(zoneId);
    }

    private Double valueOf(String strValue) {
        double result;
        try {
            result = Double.parseDouble(strValue);
        }
        catch (Exception e) {
            result = 0;
        }
        return result;
    }
}
