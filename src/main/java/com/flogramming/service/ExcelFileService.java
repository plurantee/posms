package com.flogramming.service;

import com.flogramming.domain.*;
import com.flogramming.repository.*;
import com.flogramming.util.OrdersUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
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

    @Autowired
    private ClientShopeeOrderPaymentsRepository clientShopeeOrderPaymentsRepository;


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
            }

            i++;
        }
        return setLazadaOrderFromExcelHashMap(excelHashMap);
    }

    public Page<LazadaOrder> setLazadaOrderFromExcelHashMap(Map<Integer, HashMap<String, String>> excelHashMap) {
        List<LazadaOrder> result = new ArrayList<>();
        ZonedDateTime dateUploaded = ZonedDateTime.now();
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
            lazadaOrder.dateUploaded(dateUploaded);
            lazadaOrder.orderItemId(row.get("orderItemId"));
            lazadaOrder.orderType(row.get("orderType"));
            lazadaOrder.guarantee(row.get("Guarantee"));
            lazadaOrder.deliveryType(row.get("deliveryType"));
            lazadaOrder.lazadaId(row.get("lazadaId"));
            lazadaOrder.sellerSku(row.get("sellerSku"));
            lazadaOrder.lazadaSku(row.get("lazadaSku"));
            lazadaOrder.wareHouse(row.get("wareHouse"));
            lazadaOrder.createTime(convertLazadaDate(row.get("createTime")));
            lazadaOrder.updateTime(convertLazadaDate(row.get("updateTime")));
            lazadaOrder.rtaSla(convertLazadaDate(row.get("rtsSla")));
            lazadaOrder.ttsSla(convertLazadaDate(row.get("ttsSla")));
            lazadaOrder.orderNumber(row.get("orderNumber"));
            lazadaOrder.invoiceRequired("Yes".equals(row.get("invoiceRequired")));
            lazadaOrder.invoiceNumber(row.get("invoiceNumber"));
            lazadaOrder.deliveryDate(convertLazadaDate(row.get("deliveredDate")));
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
            lazadaOrder.paidPrice(valueOf(row.get("paidPrice")));
            lazadaOrder.unitPrice(valueOf(row.get("unitPrice")));
            lazadaOrder.sellerDiscountTotal(valueOf(row.get("sellerDiscountTotal")));
            lazadaOrder.shippingFee(valueOf(row.get("shippingFee")));
            lazadaOrder.walletCredit(valueOf(row.get("walletCredit")));
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
            lazadaOrder.promisedShippingTime(convertLazadaDate(row.get("promisedShippingTime")));
            lazadaOrder.premium(row.get("premium"));
            lazadaOrder.status(row.get("status"));
            lazadaOrder.buyerFailedDeliveryReturnInitiator(row.get("buyerFailedDeliveryReturnInitiator"));
            lazadaOrder.buyerFailedDeliveryReason(row.get("buyerFailedDeliveryReason"));
            lazadaOrder.buyerFailedDeliveryDetail(row.get("buyerFailedDeliveryDetail"));
            lazadaOrder.buyerFailedDeliveryUserName(row.get("buyerFailedDeliveryUserName"));
            lazadaOrder.bundleId(row.get("bundleId"));
            lazadaOrder.bundleDiscount(valueOf(row.get("bundleDiscount")));
            lazadaOrder.refundAmount(valueOf(row.get("refundAmount")));
            String sellerSku = lazadaOrder.getSellerSku();
            Inventory inventory = clientInventoryRepository.findBySkuAndClient(sellerSku, client);
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
            lazadaOrder.setInventory(inventory);
            if (!StringUtils.isEmpty(lazadaOrder.getOrderItemId())) {
                lazadaOrderRepository.save(lazadaOrder);
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
                        word = word.isEmpty() ? word : Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();

                        builder.append(word);
                    }
                    String camelCase = builder.toString();
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
            }

            i++;
        }
        return setShopeeOrderFromExcelHashMap(excelHashMap);
    }

    public Page<ShopeeOrder> setShopeeOrderFromExcelHashMap(Map<Integer, HashMap<String, String>> excelHashMap) {
        List<LazadaOrder> result = new ArrayList<>();
        Client client = clientUserService.getCurrentUser().getClientCode();
        ZonedDateTime dateUploaded = ZonedDateTime.now();
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
            shopeeOrder.setDateUploaded(dateUploaded);
            String sellerSku = shopeeOrder.getSkuReferenceNo();
            Inventory inventory = clientInventoryRepository.findBySkuAndClient(sellerSku, client);
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
                        word = word.isEmpty() ? word : Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();

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
            List<LazadaOrder> lazadaOrder = lazadaOrderRepository.findByOrderItemId(row.get("Order Item No."));
            if (lazadaOrder.isEmpty()) {
                continue;
            }
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

            lazadaOrderPayment.setLazadaOrder(lazadaOrder.stream().findFirst().get());
            result.add(lazadaOrderPayment);
        }
        clientLazadaOrderPaymentsRepository.saveAll(result);
        return result;
    }

    /**
     * Lazada Payments
     */
    public List<ShopeeOrderPayments> processShopeePaymentsExcelFile(MultipartFile file) throws IOException {
        Workbook workbook = new HSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Map<Integer, HashMap<String, String>> excelHashMap = new HashMap<>();
        List<String> columnNames = new ArrayList<>();
        Row columnNamesRow = sheet.getRow(5);
        for (Cell cell : columnNamesRow) {
            switch (cell.getCellType()) {
                case STRING:
                    columnNames.add(cell.getStringCellValue());
                    String original = cell.getStringCellValue();
                    String[] words = original.split("[\\W_]+");
                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < words.length; i++) {
                        String word = words[i];
                        word = word.isEmpty() ? word : Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();

                        builder.append(word);
                    }
                    String camelCase = builder.toString();
                    System.out.println("shopeeOrderPayments.set" + camelCase + "(row.get(\"" + original + "\"));");
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
            }

            i++;
        }
        return setShopeeOrderPaymentsFromExcelHashMap(excelHashMap);
    }

    private List<ShopeeOrderPayments> setShopeeOrderPaymentsFromExcelHashMap(Map<Integer, HashMap<String, String>> excelHashMap) {
        List<ShopeeOrderPayments> result = new ArrayList<>();
        // 04 Jul 2022 11:46
        excelHashMap.remove(0); // Column rows
        for (HashMap<String, String> row : excelHashMap.values()) {
            System.out.println(row.get("Order ID"));
            List<ShopeeOrder> shopeeOrders = clientShopeeOrderRepository.findByOrderId(row.get("Order ID"));
            if (shopeeOrders.isEmpty()) {
                continue;
            }
            ShopeeOrderPayments shopeeOrderPayments = new ShopeeOrderPayments();
            shopeeOrderPayments.setOrderId(row.get("Order ID"));
            shopeeOrderPayments.setRefundId(row.get("Refund ID"));
            shopeeOrderPayments.setUsernameBuyer(row.get("Username (Buyer)"));
            shopeeOrderPayments.setOrderCreationDate(convertShopeePaymentDate(row.get("Order Creation Date")));
            shopeeOrderPayments.setBuyerPaymentMethod(row.get("Buyer Payment Method"));
            shopeeOrderPayments.setPayoutCompletedDate(convertShopeePaymentDate(row.get("Payout Completed Date")));
            shopeeOrderPayments.setOriginalProductPrice(valueOf(row.get("Original Product Price")));
            shopeeOrderPayments.setSellerProductPromotion(valueOf(row.get("Seller Product Promotion")));
            shopeeOrderPayments.setRefundAmountToBuyer(valueOf(row.get("Refund Amount to Buyer (₱)")));
            shopeeOrderPayments.setProductDiscountRebateFromShopee(valueOf(row.get("Product Discount Rebate from Shopee")));
            shopeeOrderPayments.setSellerVoucherDiscount(valueOf(row.get("Seller Voucher Discount")));
            shopeeOrderPayments.setSellerAbsorbedCoinCashback(valueOf(row.get("Seller Absorbed Coin Cashback")));
            shopeeOrderPayments.setBuyerPaidShippingFee(valueOf(row.get("Buyer Paid Shipping Fee")));
            shopeeOrderPayments.setShippingFeeRebateFromShopee(valueOf(row.get("Shipping Fee Rebate From Shopee")));
            shopeeOrderPayments.setThirdPartyLogisticsDefinedShippingFee(valueOf(row.get("3rd Party Logistics - Defined Shipping Fee")));
            shopeeOrderPayments.setReverseShippingFee(valueOf(row.get("Reverse Shipping Fee")));
            shopeeOrderPayments.setCommissionFee(valueOf(row.get("Commission fee")));
            shopeeOrderPayments.setServiceFee(valueOf(row.get("Service Fee")));
            shopeeOrderPayments.setTransactionFee(valueOf(row.get("Transaction Fee")));
            shopeeOrderPayments.setTotalReleasedAmount(valueOf(row.get("Total Released Amount (₱)")));
            shopeeOrderPayments.setSellerVoucherCode(row.get("Seller Voucher Code"));
            shopeeOrderPayments.setLostCompensation(valueOf(row.get("Lost Compensation")));
            shopeeOrderPayments.setTotalActualWeightPerOrder(valueOf(row.get("Total actual weight per order")));
            shopeeOrderPayments.setShippingFeePromotionBySeller(valueOf(row.get("Shipping Fee Promotion by Seller")));
            shopeeOrderPayments.setShippingProvider(row.get("Shipping Provider"));
            shopeeOrderPayments.setCourierName(row.get("Courier Name"));

            shopeeOrderPayments.addShopeeOrderFromList(shopeeOrders);

            result.add(shopeeOrderPayments);
        }
        clientShopeeOrderPaymentsRepository.saveAll(result);
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

    public ZonedDateTime convertShopeePaymentDate(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ldt = LocalDate.parse(date, formatter);
        ZoneId zoneId = ZoneId.of("Asia/Manila");
        return ldt.atStartOfDay(zoneId);
    }

    private Double valueOf(String strValue) {
        double result;
        try {
            result = Double.parseDouble(strValue);
        } catch (Exception e) {
            result = 0;
        }
        return result;
    }

    public Page<Inventory> processInventoryUpload(MultipartFile file) throws IOException {
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
                        word = word.isEmpty() ? word : Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();

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
                if (row == null || row.getCell(j) == null) {
                    break;
                }
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
            }

            i++;
        }
        return setInventoriesFromExcelHashMap(excelHashMap);
    }

    private Page<Inventory> setInventoriesFromExcelHashMap(Map<Integer, HashMap<String, String>> excelHashMap) {
        List<Inventory> result = new ArrayList<>();
        // 04 Jul 2022 11:46
        excelHashMap.remove(0); // Column rows
        Client client = clientUserService.getCurrentUser().getClientCode();


        for (HashMap<String, String> row : excelHashMap.values()) {
            String sku = row.get("Product Name");
            Inventory inventory = null;
            if (!StringUtils.isEmpty(sku)) {
                inventory = clientInventoryRepository.findBySkuAndClient(sku, client);
            } else  {
                break;
            }
            if (inventory == null) {
                inventory = new Inventory();
                inventory.setClient(client);
                inventory.setSku(sku);
            }
            inventory.setCost(Double.valueOf(row.get("Cost")));
            inventory.setStocks(Integer.valueOf(row.get("Stocks")));
            inventory.setPrice(Double.valueOf(row.get("Price(RETAIL SALE)")));
            inventory.setThreshold(Integer.valueOf(row.get("Threshold")));
            result.add(inventory);

        }
        clientInventoryRepository.saveAll(result);
        return clientInventoryRepository.findByClient(client, Pageable.ofSize(10));
    }
}
