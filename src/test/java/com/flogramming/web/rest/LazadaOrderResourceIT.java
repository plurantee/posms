package com.flogramming.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.flogramming.IntegrationTest;
import com.flogramming.domain.LazadaOrder;
import com.flogramming.repository.LazadaOrderRepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link LazadaOrderResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LazadaOrderResourceIT {

    private static final String DEFAULT_ORDER_ITEM_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_ITEM_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ORDER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_GUARANTEE = "AAAAAAAAAA";
    private static final String UPDATED_GUARANTEE = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_LAZADA_ID = "AAAAAAAAAA";
    private static final String UPDATED_LAZADA_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SELLER_SKU = "AAAAAAAAAA";
    private static final String UPDATED_SELLER_SKU = "BBBBBBBBBB";

    private static final String DEFAULT_WARE_HOUSE = "AAAAAAAAAA";
    private static final String UPDATED_WARE_HOUSE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATE_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATE_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATE_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATE_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_RTA_SLA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RTA_SLA = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TTS_SLA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TTS_SLA = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ORDER_NUMBER = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ORDER_NUMBER = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_INVOICE_REQUIRED = false;
    private static final Boolean UPDATED_INVOICE_REQUIRED = true;

    private static final String DEFAULT_INVOICE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_INVOICE_NUMBER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DELIVERY_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DELIVERY_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CUSTOMER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_NATIONAL_REGISTRATION_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_NATIONAL_REGISTRATION_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_ADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_ADDRESS_3 = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_ADDRESS_3 = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_ADDRESS_4 = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_ADDRESS_4 = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_ADDRESS_5 = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_ADDRESS_5 = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_PHONE_2 = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_PHONE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_CITY = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_POST_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_POST_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_REGION = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_REGION = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_ADDR = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ADDR = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_ADDR_3 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ADDR_3 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_ADDR_4 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ADDR_4 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_ADDR_5 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ADDR_5 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_PHONE_2 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_PHONE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_CITY = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_POST_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_POST_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_TAX_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TAX_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_TAX_INVOICE_REQUESTED = "AAAAAAAAAA";
    private static final String UPDATED_TAX_INVOICE_REQUESTED = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_PAY_METHOD = "BBBBBBBBBB";

    private static final String DEFAULT_PAID_PRICE = "AAAAAAAAAA";
    private static final String UPDATED_PAID_PRICE = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT_PRICE = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_PRICE = "BBBBBBBBBB";

    private static final String DEFAULT_SELLER_DISCOUNT_TOTAL = "AAAAAAAAAA";
    private static final String UPDATED_SELLER_DISCOUNT_TOTAL = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_FEE = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_FEE = "BBBBBBBBBB";

    private static final String DEFAULT_WALLET_CREDIT = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_CREDIT = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VARIATION = "AAAAAAAAAA";
    private static final String UPDATED_VARIATION = "BBBBBBBBBB";

    private static final String DEFAULT_CD_SHIPPING_PROVIDER = "AAAAAAAAAA";
    private static final String UPDATED_CD_SHIPPING_PROVIDER = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_PROVIDER = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_PROVIDER = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPMENT_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHIPMENT_TYPE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_PROVIDER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_PROVIDER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CD_TRACKING_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CD_TRACKING_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TRACKING_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TRACKING_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TRACKING_URL = "AAAAAAAAAA";
    private static final String UPDATED_TRACKING_URL = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_PROVIDER_FM = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_PROVIDER_FM = "BBBBBBBBBB";

    private static final String DEFAULT_TRACKING_CODE_FM = "AAAAAAAAAA";
    private static final String UPDATED_TRACKING_CODE_FM = "BBBBBBBBBB";

    private static final String DEFAULT_TRACKING_URL_FM = "AAAAAAAAAA";
    private static final String UPDATED_TRACKING_URL_FM = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PROMISED_SHIPPING_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PROMISED_SHIPPING_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PREMIUM = "AAAAAAAAAA";
    private static final String UPDATED_PREMIUM = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_BUYER_FAILED_DELIVERY_RETURN_INITIATOR = "AAAAAAAAAA";
    private static final String UPDATED_BUYER_FAILED_DELIVERY_RETURN_INITIATOR = "BBBBBBBBBB";

    private static final String DEFAULT_BUYER_FAILED_DELIVERY_REASON = "AAAAAAAAAA";
    private static final String UPDATED_BUYER_FAILED_DELIVERY_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_BUYER_FAILED_DELIVERY_DETAIL = "AAAAAAAAAA";
    private static final String UPDATED_BUYER_FAILED_DELIVERY_DETAIL = "BBBBBBBBBB";

    private static final String DEFAULT_BUYER_FAILED_DELIVERY_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BUYER_FAILED_DELIVERY_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BUNDLE_ID = "AAAAAAAAAA";
    private static final String UPDATED_BUNDLE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BUNDLE_DISCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_BUNDLE_DISCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_REFUND_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_REFUND_AMOUNT = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/lazada-orders";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private LazadaOrderRepository lazadaOrderRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLazadaOrderMockMvc;

    private LazadaOrder lazadaOrder;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LazadaOrder createEntity(EntityManager em) {
        LazadaOrder lazadaOrder = new LazadaOrder()
            .orderItemId(DEFAULT_ORDER_ITEM_ID)
            .orderType(DEFAULT_ORDER_TYPE)
            .guarantee(DEFAULT_GUARANTEE)
            .deliveryType(DEFAULT_DELIVERY_TYPE)
            .lazadaId(DEFAULT_LAZADA_ID)
            .sellerSku(DEFAULT_SELLER_SKU)
            .wareHouse(DEFAULT_WARE_HOUSE)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME)
            .rtaSla(DEFAULT_RTA_SLA)
            .ttsSla(DEFAULT_TTS_SLA)
            .orderNumber(DEFAULT_ORDER_NUMBER)
            .invoiceRequired(DEFAULT_INVOICE_REQUIRED)
            .invoiceNumber(DEFAULT_INVOICE_NUMBER)
            .deliveryDate(DEFAULT_DELIVERY_DATE)
            .customerName(DEFAULT_CUSTOMER_NAME)
            .customerEmail(DEFAULT_CUSTOMER_EMAIL)
            .nationalRegistrationNumber(DEFAULT_NATIONAL_REGISTRATION_NUMBER)
            .shippingName(DEFAULT_SHIPPING_NAME)
            .shippingAddress(DEFAULT_SHIPPING_ADDRESS)
            .shippingAddress2(DEFAULT_SHIPPING_ADDRESS_2)
            .shippingAddress3(DEFAULT_SHIPPING_ADDRESS_3)
            .shippingAddress4(DEFAULT_SHIPPING_ADDRESS_4)
            .shippingAddress5(DEFAULT_SHIPPING_ADDRESS_5)
            .shippingPhone(DEFAULT_SHIPPING_PHONE)
            .shippingPhone2(DEFAULT_SHIPPING_PHONE_2)
            .shippingCity(DEFAULT_SHIPPING_CITY)
            .shippingPostCode(DEFAULT_SHIPPING_POST_CODE)
            .shippingCountry(DEFAULT_SHIPPING_COUNTRY)
            .shippingRegion(DEFAULT_SHIPPING_REGION)
            .billingName(DEFAULT_BILLING_NAME)
            .billingAddr(DEFAULT_BILLING_ADDR)
            .billingAddr3(DEFAULT_BILLING_ADDR_3)
            .billingAddr4(DEFAULT_BILLING_ADDR_4)
            .billingAddr5(DEFAULT_BILLING_ADDR_5)
            .billingPhone(DEFAULT_BILLING_PHONE)
            .billingPhone2(DEFAULT_BILLING_PHONE_2)
            .billingCity(DEFAULT_BILLING_CITY)
            .billingPostCode(DEFAULT_BILLING_POST_CODE)
            .billingCountry(DEFAULT_BILLING_COUNTRY)
            .taxCode(DEFAULT_TAX_CODE)
            .branchNumber(DEFAULT_BRANCH_NUMBER)
            .taxInvoiceRequested(DEFAULT_TAX_INVOICE_REQUESTED)
            .payMethod(DEFAULT_PAY_METHOD)
            .paidPrice(DEFAULT_PAID_PRICE)
            .unitPrice(DEFAULT_UNIT_PRICE)
            .sellerDiscountTotal(DEFAULT_SELLER_DISCOUNT_TOTAL)
            .shippingFee(DEFAULT_SHIPPING_FEE)
            .walletCredit(DEFAULT_WALLET_CREDIT)
            .itemName(DEFAULT_ITEM_NAME)
            .variation(DEFAULT_VARIATION)
            .cdShippingProvider(DEFAULT_CD_SHIPPING_PROVIDER)
            .shippingProvider(DEFAULT_SHIPPING_PROVIDER)
            .shipmentTypeName(DEFAULT_SHIPMENT_TYPE_NAME)
            .shippingProviderType(DEFAULT_SHIPPING_PROVIDER_TYPE)
            .cdTrackingCode(DEFAULT_CD_TRACKING_CODE)
            .trackingCode(DEFAULT_TRACKING_CODE)
            .trackingUrl(DEFAULT_TRACKING_URL)
            .shippingProviderFM(DEFAULT_SHIPPING_PROVIDER_FM)
            .trackingCodeFM(DEFAULT_TRACKING_CODE_FM)
            .trackingUrlFM(DEFAULT_TRACKING_URL_FM)
            .promisedShippingTime(DEFAULT_PROMISED_SHIPPING_TIME)
            .premium(DEFAULT_PREMIUM)
            .status(DEFAULT_STATUS)
            .buyerFailedDeliveryReturnInitiator(DEFAULT_BUYER_FAILED_DELIVERY_RETURN_INITIATOR)
            .buyerFailedDeliveryReason(DEFAULT_BUYER_FAILED_DELIVERY_REASON)
            .buyerFailedDeliveryDetail(DEFAULT_BUYER_FAILED_DELIVERY_DETAIL)
            .buyerFailedDeliveryUserName(DEFAULT_BUYER_FAILED_DELIVERY_USER_NAME)
            .bundleId(DEFAULT_BUNDLE_ID)
            .bundleDiscount(DEFAULT_BUNDLE_DISCOUNT)
            .refundAmount(DEFAULT_REFUND_AMOUNT);
        return lazadaOrder;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LazadaOrder createUpdatedEntity(EntityManager em) {
        LazadaOrder lazadaOrder = new LazadaOrder()
            .orderItemId(UPDATED_ORDER_ITEM_ID)
            .orderType(UPDATED_ORDER_TYPE)
            .guarantee(UPDATED_GUARANTEE)
            .deliveryType(UPDATED_DELIVERY_TYPE)
            .lazadaId(UPDATED_LAZADA_ID)
            .sellerSku(UPDATED_SELLER_SKU)
            .wareHouse(UPDATED_WARE_HOUSE)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME)
            .rtaSla(UPDATED_RTA_SLA)
            .ttsSla(UPDATED_TTS_SLA)
            .orderNumber(UPDATED_ORDER_NUMBER)
            .invoiceRequired(UPDATED_INVOICE_REQUIRED)
            .invoiceNumber(UPDATED_INVOICE_NUMBER)
            .deliveryDate(UPDATED_DELIVERY_DATE)
            .customerName(UPDATED_CUSTOMER_NAME)
            .customerEmail(UPDATED_CUSTOMER_EMAIL)
            .nationalRegistrationNumber(UPDATED_NATIONAL_REGISTRATION_NUMBER)
            .shippingName(UPDATED_SHIPPING_NAME)
            .shippingAddress(UPDATED_SHIPPING_ADDRESS)
            .shippingAddress2(UPDATED_SHIPPING_ADDRESS_2)
            .shippingAddress3(UPDATED_SHIPPING_ADDRESS_3)
            .shippingAddress4(UPDATED_SHIPPING_ADDRESS_4)
            .shippingAddress5(UPDATED_SHIPPING_ADDRESS_5)
            .shippingPhone(UPDATED_SHIPPING_PHONE)
            .shippingPhone2(UPDATED_SHIPPING_PHONE_2)
            .shippingCity(UPDATED_SHIPPING_CITY)
            .shippingPostCode(UPDATED_SHIPPING_POST_CODE)
            .shippingCountry(UPDATED_SHIPPING_COUNTRY)
            .shippingRegion(UPDATED_SHIPPING_REGION)
            .billingName(UPDATED_BILLING_NAME)
            .billingAddr(UPDATED_BILLING_ADDR)
            .billingAddr3(UPDATED_BILLING_ADDR_3)
            .billingAddr4(UPDATED_BILLING_ADDR_4)
            .billingAddr5(UPDATED_BILLING_ADDR_5)
            .billingPhone(UPDATED_BILLING_PHONE)
            .billingPhone2(UPDATED_BILLING_PHONE_2)
            .billingCity(UPDATED_BILLING_CITY)
            .billingPostCode(UPDATED_BILLING_POST_CODE)
            .billingCountry(UPDATED_BILLING_COUNTRY)
            .taxCode(UPDATED_TAX_CODE)
            .branchNumber(UPDATED_BRANCH_NUMBER)
            .taxInvoiceRequested(UPDATED_TAX_INVOICE_REQUESTED)
            .payMethod(UPDATED_PAY_METHOD)
            .paidPrice(UPDATED_PAID_PRICE)
            .unitPrice(UPDATED_UNIT_PRICE)
            .sellerDiscountTotal(UPDATED_SELLER_DISCOUNT_TOTAL)
            .shippingFee(UPDATED_SHIPPING_FEE)
            .walletCredit(UPDATED_WALLET_CREDIT)
            .itemName(UPDATED_ITEM_NAME)
            .variation(UPDATED_VARIATION)
            .cdShippingProvider(UPDATED_CD_SHIPPING_PROVIDER)
            .shippingProvider(UPDATED_SHIPPING_PROVIDER)
            .shipmentTypeName(UPDATED_SHIPMENT_TYPE_NAME)
            .shippingProviderType(UPDATED_SHIPPING_PROVIDER_TYPE)
            .cdTrackingCode(UPDATED_CD_TRACKING_CODE)
            .trackingCode(UPDATED_TRACKING_CODE)
            .trackingUrl(UPDATED_TRACKING_URL)
            .shippingProviderFM(UPDATED_SHIPPING_PROVIDER_FM)
            .trackingCodeFM(UPDATED_TRACKING_CODE_FM)
            .trackingUrlFM(UPDATED_TRACKING_URL_FM)
            .promisedShippingTime(UPDATED_PROMISED_SHIPPING_TIME)
            .premium(UPDATED_PREMIUM)
            .status(UPDATED_STATUS)
            .buyerFailedDeliveryReturnInitiator(UPDATED_BUYER_FAILED_DELIVERY_RETURN_INITIATOR)
            .buyerFailedDeliveryReason(UPDATED_BUYER_FAILED_DELIVERY_REASON)
            .buyerFailedDeliveryDetail(UPDATED_BUYER_FAILED_DELIVERY_DETAIL)
            .buyerFailedDeliveryUserName(UPDATED_BUYER_FAILED_DELIVERY_USER_NAME)
            .bundleId(UPDATED_BUNDLE_ID)
            .bundleDiscount(UPDATED_BUNDLE_DISCOUNT)
            .refundAmount(UPDATED_REFUND_AMOUNT);
        return lazadaOrder;
    }

    @BeforeEach
    public void initTest() {
        lazadaOrder = createEntity(em);
    }

    @Test
    @Transactional
    void createLazadaOrder() throws Exception {
        int databaseSizeBeforeCreate = lazadaOrderRepository.findAll().size();
        // Create the LazadaOrder
        restLazadaOrderMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(lazadaOrder)))
            .andExpect(status().isCreated());

        // Validate the LazadaOrder in the database
        List<LazadaOrder> lazadaOrderList = lazadaOrderRepository.findAll();
        assertThat(lazadaOrderList).hasSize(databaseSizeBeforeCreate + 1);
        LazadaOrder testLazadaOrder = lazadaOrderList.get(lazadaOrderList.size() - 1);
        assertThat(testLazadaOrder.getOrderItemId()).isEqualTo(DEFAULT_ORDER_ITEM_ID);
        assertThat(testLazadaOrder.getOrderType()).isEqualTo(DEFAULT_ORDER_TYPE);
        assertThat(testLazadaOrder.getGuarantee()).isEqualTo(DEFAULT_GUARANTEE);
        assertThat(testLazadaOrder.getDeliveryType()).isEqualTo(DEFAULT_DELIVERY_TYPE);
        assertThat(testLazadaOrder.getLazadaId()).isEqualTo(DEFAULT_LAZADA_ID);
        assertThat(testLazadaOrder.getSellerSku()).isEqualTo(DEFAULT_SELLER_SKU);
        assertThat(testLazadaOrder.getWareHouse()).isEqualTo(DEFAULT_WARE_HOUSE);
        assertThat(testLazadaOrder.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testLazadaOrder.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testLazadaOrder.getRtaSla()).isEqualTo(DEFAULT_RTA_SLA);
        assertThat(testLazadaOrder.getTtsSla()).isEqualTo(DEFAULT_TTS_SLA);
        assertThat(testLazadaOrder.getOrderNumber()).isEqualTo(DEFAULT_ORDER_NUMBER);
        assertThat(testLazadaOrder.getInvoiceRequired()).isEqualTo(DEFAULT_INVOICE_REQUIRED);
        assertThat(testLazadaOrder.getInvoiceNumber()).isEqualTo(DEFAULT_INVOICE_NUMBER);
        assertThat(testLazadaOrder.getDeliveryDate()).isEqualTo(DEFAULT_DELIVERY_DATE);
        assertThat(testLazadaOrder.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testLazadaOrder.getCustomerEmail()).isEqualTo(DEFAULT_CUSTOMER_EMAIL);
        assertThat(testLazadaOrder.getNationalRegistrationNumber()).isEqualTo(DEFAULT_NATIONAL_REGISTRATION_NUMBER);
        assertThat(testLazadaOrder.getShippingName()).isEqualTo(DEFAULT_SHIPPING_NAME);
        assertThat(testLazadaOrder.getShippingAddress()).isEqualTo(DEFAULT_SHIPPING_ADDRESS);
        assertThat(testLazadaOrder.getShippingAddress2()).isEqualTo(DEFAULT_SHIPPING_ADDRESS_2);
        assertThat(testLazadaOrder.getShippingAddress3()).isEqualTo(DEFAULT_SHIPPING_ADDRESS_3);
        assertThat(testLazadaOrder.getShippingAddress4()).isEqualTo(DEFAULT_SHIPPING_ADDRESS_4);
        assertThat(testLazadaOrder.getShippingAddress5()).isEqualTo(DEFAULT_SHIPPING_ADDRESS_5);
        assertThat(testLazadaOrder.getShippingPhone()).isEqualTo(DEFAULT_SHIPPING_PHONE);
        assertThat(testLazadaOrder.getShippingPhone2()).isEqualTo(DEFAULT_SHIPPING_PHONE_2);
        assertThat(testLazadaOrder.getShippingCity()).isEqualTo(DEFAULT_SHIPPING_CITY);
        assertThat(testLazadaOrder.getShippingPostCode()).isEqualTo(DEFAULT_SHIPPING_POST_CODE);
        assertThat(testLazadaOrder.getShippingCountry()).isEqualTo(DEFAULT_SHIPPING_COUNTRY);
        assertThat(testLazadaOrder.getShippingRegion()).isEqualTo(DEFAULT_SHIPPING_REGION);
        assertThat(testLazadaOrder.getBillingName()).isEqualTo(DEFAULT_BILLING_NAME);
        assertThat(testLazadaOrder.getBillingAddr()).isEqualTo(DEFAULT_BILLING_ADDR);
        assertThat(testLazadaOrder.getBillingAddr3()).isEqualTo(DEFAULT_BILLING_ADDR_3);
        assertThat(testLazadaOrder.getBillingAddr4()).isEqualTo(DEFAULT_BILLING_ADDR_4);
        assertThat(testLazadaOrder.getBillingAddr5()).isEqualTo(DEFAULT_BILLING_ADDR_5);
        assertThat(testLazadaOrder.getBillingPhone()).isEqualTo(DEFAULT_BILLING_PHONE);
        assertThat(testLazadaOrder.getBillingPhone2()).isEqualTo(DEFAULT_BILLING_PHONE_2);
        assertThat(testLazadaOrder.getBillingCity()).isEqualTo(DEFAULT_BILLING_CITY);
        assertThat(testLazadaOrder.getBillingPostCode()).isEqualTo(DEFAULT_BILLING_POST_CODE);
        assertThat(testLazadaOrder.getBillingCountry()).isEqualTo(DEFAULT_BILLING_COUNTRY);
        assertThat(testLazadaOrder.getTaxCode()).isEqualTo(DEFAULT_TAX_CODE);
        assertThat(testLazadaOrder.getBranchNumber()).isEqualTo(DEFAULT_BRANCH_NUMBER);
        assertThat(testLazadaOrder.getTaxInvoiceRequested()).isEqualTo(DEFAULT_TAX_INVOICE_REQUESTED);
        assertThat(testLazadaOrder.getPayMethod()).isEqualTo(DEFAULT_PAY_METHOD);
        assertThat(testLazadaOrder.getPaidPrice()).isEqualTo(DEFAULT_PAID_PRICE);
        assertThat(testLazadaOrder.getUnitPrice()).isEqualTo(DEFAULT_UNIT_PRICE);
        assertThat(testLazadaOrder.getSellerDiscountTotal()).isEqualTo(DEFAULT_SELLER_DISCOUNT_TOTAL);
        assertThat(testLazadaOrder.getShippingFee()).isEqualTo(DEFAULT_SHIPPING_FEE);
        assertThat(testLazadaOrder.getWalletCredit()).isEqualTo(DEFAULT_WALLET_CREDIT);
        assertThat(testLazadaOrder.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testLazadaOrder.getVariation()).isEqualTo(DEFAULT_VARIATION);
        assertThat(testLazadaOrder.getCdShippingProvider()).isEqualTo(DEFAULT_CD_SHIPPING_PROVIDER);
        assertThat(testLazadaOrder.getShippingProvider()).isEqualTo(DEFAULT_SHIPPING_PROVIDER);
        assertThat(testLazadaOrder.getShipmentTypeName()).isEqualTo(DEFAULT_SHIPMENT_TYPE_NAME);
        assertThat(testLazadaOrder.getShippingProviderType()).isEqualTo(DEFAULT_SHIPPING_PROVIDER_TYPE);
        assertThat(testLazadaOrder.getCdTrackingCode()).isEqualTo(DEFAULT_CD_TRACKING_CODE);
        assertThat(testLazadaOrder.getTrackingCode()).isEqualTo(DEFAULT_TRACKING_CODE);
        assertThat(testLazadaOrder.getTrackingUrl()).isEqualTo(DEFAULT_TRACKING_URL);
        assertThat(testLazadaOrder.getShippingProviderFM()).isEqualTo(DEFAULT_SHIPPING_PROVIDER_FM);
        assertThat(testLazadaOrder.getTrackingCodeFM()).isEqualTo(DEFAULT_TRACKING_CODE_FM);
        assertThat(testLazadaOrder.getTrackingUrlFM()).isEqualTo(DEFAULT_TRACKING_URL_FM);
        assertThat(testLazadaOrder.getPromisedShippingTime()).isEqualTo(DEFAULT_PROMISED_SHIPPING_TIME);
        assertThat(testLazadaOrder.getPremium()).isEqualTo(DEFAULT_PREMIUM);
        assertThat(testLazadaOrder.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testLazadaOrder.getBuyerFailedDeliveryReturnInitiator()).isEqualTo(DEFAULT_BUYER_FAILED_DELIVERY_RETURN_INITIATOR);
        assertThat(testLazadaOrder.getBuyerFailedDeliveryReason()).isEqualTo(DEFAULT_BUYER_FAILED_DELIVERY_REASON);
        assertThat(testLazadaOrder.getBuyerFailedDeliveryDetail()).isEqualTo(DEFAULT_BUYER_FAILED_DELIVERY_DETAIL);
        assertThat(testLazadaOrder.getBuyerFailedDeliveryUserName()).isEqualTo(DEFAULT_BUYER_FAILED_DELIVERY_USER_NAME);
        assertThat(testLazadaOrder.getBundleId()).isEqualTo(DEFAULT_BUNDLE_ID);
        assertThat(testLazadaOrder.getBundleDiscount()).isEqualTo(DEFAULT_BUNDLE_DISCOUNT);
        assertThat(testLazadaOrder.getRefundAmount()).isEqualTo(DEFAULT_REFUND_AMOUNT);
    }

    @Test
    @Transactional
    void createLazadaOrderWithExistingId() throws Exception {
        // Create the LazadaOrder with an existing ID
        lazadaOrder.setId(1L);

        int databaseSizeBeforeCreate = lazadaOrderRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLazadaOrderMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(lazadaOrder)))
            .andExpect(status().isBadRequest());

        // Validate the LazadaOrder in the database
        List<LazadaOrder> lazadaOrderList = lazadaOrderRepository.findAll();
        assertThat(lazadaOrderList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllLazadaOrders() throws Exception {
        // Initialize the database
        lazadaOrderRepository.saveAndFlush(lazadaOrder);

        // Get all the lazadaOrderList
        restLazadaOrderMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lazadaOrder.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderItemId").value(hasItem(DEFAULT_ORDER_ITEM_ID)))
            .andExpect(jsonPath("$.[*].orderType").value(hasItem(DEFAULT_ORDER_TYPE)))
            .andExpect(jsonPath("$.[*].guarantee").value(hasItem(DEFAULT_GUARANTEE)))
            .andExpect(jsonPath("$.[*].deliveryType").value(hasItem(DEFAULT_DELIVERY_TYPE)))
            .andExpect(jsonPath("$.[*].lazadaId").value(hasItem(DEFAULT_LAZADA_ID)))
            .andExpect(jsonPath("$.[*].sellerSku").value(hasItem(DEFAULT_SELLER_SKU)))
            .andExpect(jsonPath("$.[*].wareHouse").value(hasItem(DEFAULT_WARE_HOUSE)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].rtaSla").value(hasItem(DEFAULT_RTA_SLA.toString())))
            .andExpect(jsonPath("$.[*].ttsSla").value(hasItem(DEFAULT_TTS_SLA.toString())))
            .andExpect(jsonPath("$.[*].orderNumber").value(hasItem(DEFAULT_ORDER_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].invoiceRequired").value(hasItem(DEFAULT_INVOICE_REQUIRED.booleanValue())))
            .andExpect(jsonPath("$.[*].invoiceNumber").value(hasItem(DEFAULT_INVOICE_NUMBER)))
            .andExpect(jsonPath("$.[*].deliveryDate").value(hasItem(DEFAULT_DELIVERY_DATE.toString())))
            .andExpect(jsonPath("$.[*].customerName").value(hasItem(DEFAULT_CUSTOMER_NAME)))
            .andExpect(jsonPath("$.[*].customerEmail").value(hasItem(DEFAULT_CUSTOMER_EMAIL)))
            .andExpect(jsonPath("$.[*].nationalRegistrationNumber").value(hasItem(DEFAULT_NATIONAL_REGISTRATION_NUMBER)))
            .andExpect(jsonPath("$.[*].shippingName").value(hasItem(DEFAULT_SHIPPING_NAME)))
            .andExpect(jsonPath("$.[*].shippingAddress").value(hasItem(DEFAULT_SHIPPING_ADDRESS)))
            .andExpect(jsonPath("$.[*].shippingAddress2").value(hasItem(DEFAULT_SHIPPING_ADDRESS_2)))
            .andExpect(jsonPath("$.[*].shippingAddress3").value(hasItem(DEFAULT_SHIPPING_ADDRESS_3)))
            .andExpect(jsonPath("$.[*].shippingAddress4").value(hasItem(DEFAULT_SHIPPING_ADDRESS_4)))
            .andExpect(jsonPath("$.[*].shippingAddress5").value(hasItem(DEFAULT_SHIPPING_ADDRESS_5)))
            .andExpect(jsonPath("$.[*].shippingPhone").value(hasItem(DEFAULT_SHIPPING_PHONE)))
            .andExpect(jsonPath("$.[*].shippingPhone2").value(hasItem(DEFAULT_SHIPPING_PHONE_2)))
            .andExpect(jsonPath("$.[*].shippingCity").value(hasItem(DEFAULT_SHIPPING_CITY)))
            .andExpect(jsonPath("$.[*].shippingPostCode").value(hasItem(DEFAULT_SHIPPING_POST_CODE)))
            .andExpect(jsonPath("$.[*].shippingCountry").value(hasItem(DEFAULT_SHIPPING_COUNTRY)))
            .andExpect(jsonPath("$.[*].shippingRegion").value(hasItem(DEFAULT_SHIPPING_REGION)))
            .andExpect(jsonPath("$.[*].billingName").value(hasItem(DEFAULT_BILLING_NAME)))
            .andExpect(jsonPath("$.[*].billingAddr").value(hasItem(DEFAULT_BILLING_ADDR)))
            .andExpect(jsonPath("$.[*].billingAddr3").value(hasItem(DEFAULT_BILLING_ADDR_3)))
            .andExpect(jsonPath("$.[*].billingAddr4").value(hasItem(DEFAULT_BILLING_ADDR_4)))
            .andExpect(jsonPath("$.[*].billingAddr5").value(hasItem(DEFAULT_BILLING_ADDR_5)))
            .andExpect(jsonPath("$.[*].billingPhone").value(hasItem(DEFAULT_BILLING_PHONE)))
            .andExpect(jsonPath("$.[*].billingPhone2").value(hasItem(DEFAULT_BILLING_PHONE_2)))
            .andExpect(jsonPath("$.[*].billingCity").value(hasItem(DEFAULT_BILLING_CITY)))
            .andExpect(jsonPath("$.[*].billingPostCode").value(hasItem(DEFAULT_BILLING_POST_CODE)))
            .andExpect(jsonPath("$.[*].billingCountry").value(hasItem(DEFAULT_BILLING_COUNTRY)))
            .andExpect(jsonPath("$.[*].taxCode").value(hasItem(DEFAULT_TAX_CODE)))
            .andExpect(jsonPath("$.[*].branchNumber").value(hasItem(DEFAULT_BRANCH_NUMBER)))
            .andExpect(jsonPath("$.[*].taxInvoiceRequested").value(hasItem(DEFAULT_TAX_INVOICE_REQUESTED)))
            .andExpect(jsonPath("$.[*].payMethod").value(hasItem(DEFAULT_PAY_METHOD)))
            .andExpect(jsonPath("$.[*].paidPrice").value(hasItem(DEFAULT_PAID_PRICE)))
            .andExpect(jsonPath("$.[*].unitPrice").value(hasItem(DEFAULT_UNIT_PRICE)))
            .andExpect(jsonPath("$.[*].sellerDiscountTotal").value(hasItem(DEFAULT_SELLER_DISCOUNT_TOTAL)))
            .andExpect(jsonPath("$.[*].shippingFee").value(hasItem(DEFAULT_SHIPPING_FEE)))
            .andExpect(jsonPath("$.[*].walletCredit").value(hasItem(DEFAULT_WALLET_CREDIT)))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].variation").value(hasItem(DEFAULT_VARIATION)))
            .andExpect(jsonPath("$.[*].cdShippingProvider").value(hasItem(DEFAULT_CD_SHIPPING_PROVIDER)))
            .andExpect(jsonPath("$.[*].shippingProvider").value(hasItem(DEFAULT_SHIPPING_PROVIDER)))
            .andExpect(jsonPath("$.[*].shipmentTypeName").value(hasItem(DEFAULT_SHIPMENT_TYPE_NAME)))
            .andExpect(jsonPath("$.[*].shippingProviderType").value(hasItem(DEFAULT_SHIPPING_PROVIDER_TYPE)))
            .andExpect(jsonPath("$.[*].cdTrackingCode").value(hasItem(DEFAULT_CD_TRACKING_CODE)))
            .andExpect(jsonPath("$.[*].trackingCode").value(hasItem(DEFAULT_TRACKING_CODE)))
            .andExpect(jsonPath("$.[*].trackingUrl").value(hasItem(DEFAULT_TRACKING_URL)))
            .andExpect(jsonPath("$.[*].shippingProviderFM").value(hasItem(DEFAULT_SHIPPING_PROVIDER_FM)))
            .andExpect(jsonPath("$.[*].trackingCodeFM").value(hasItem(DEFAULT_TRACKING_CODE_FM)))
            .andExpect(jsonPath("$.[*].trackingUrlFM").value(hasItem(DEFAULT_TRACKING_URL_FM)))
            .andExpect(jsonPath("$.[*].promisedShippingTime").value(hasItem(DEFAULT_PROMISED_SHIPPING_TIME.toString())))
            .andExpect(jsonPath("$.[*].premium").value(hasItem(DEFAULT_PREMIUM)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].buyerFailedDeliveryReturnInitiator").value(hasItem(DEFAULT_BUYER_FAILED_DELIVERY_RETURN_INITIATOR)))
            .andExpect(jsonPath("$.[*].buyerFailedDeliveryReason").value(hasItem(DEFAULT_BUYER_FAILED_DELIVERY_REASON)))
            .andExpect(jsonPath("$.[*].buyerFailedDeliveryDetail").value(hasItem(DEFAULT_BUYER_FAILED_DELIVERY_DETAIL)))
            .andExpect(jsonPath("$.[*].buyerFailedDeliveryUserName").value(hasItem(DEFAULT_BUYER_FAILED_DELIVERY_USER_NAME)))
            .andExpect(jsonPath("$.[*].bundleId").value(hasItem(DEFAULT_BUNDLE_ID)))
            .andExpect(jsonPath("$.[*].bundleDiscount").value(hasItem(DEFAULT_BUNDLE_DISCOUNT)))
            .andExpect(jsonPath("$.[*].refundAmount").value(hasItem(DEFAULT_REFUND_AMOUNT)));
    }

    @Test
    @Transactional
    void getLazadaOrder() throws Exception {
        // Initialize the database
        lazadaOrderRepository.saveAndFlush(lazadaOrder);

        // Get the lazadaOrder
        restLazadaOrderMockMvc
            .perform(get(ENTITY_API_URL_ID, lazadaOrder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(lazadaOrder.getId().intValue()))
            .andExpect(jsonPath("$.orderItemId").value(DEFAULT_ORDER_ITEM_ID))
            .andExpect(jsonPath("$.orderType").value(DEFAULT_ORDER_TYPE))
            .andExpect(jsonPath("$.guarantee").value(DEFAULT_GUARANTEE))
            .andExpect(jsonPath("$.deliveryType").value(DEFAULT_DELIVERY_TYPE))
            .andExpect(jsonPath("$.lazadaId").value(DEFAULT_LAZADA_ID))
            .andExpect(jsonPath("$.sellerSku").value(DEFAULT_SELLER_SKU))
            .andExpect(jsonPath("$.wareHouse").value(DEFAULT_WARE_HOUSE))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.rtaSla").value(DEFAULT_RTA_SLA.toString()))
            .andExpect(jsonPath("$.ttsSla").value(DEFAULT_TTS_SLA.toString()))
            .andExpect(jsonPath("$.orderNumber").value(DEFAULT_ORDER_NUMBER.toString()))
            .andExpect(jsonPath("$.invoiceRequired").value(DEFAULT_INVOICE_REQUIRED.booleanValue()))
            .andExpect(jsonPath("$.invoiceNumber").value(DEFAULT_INVOICE_NUMBER))
            .andExpect(jsonPath("$.deliveryDate").value(DEFAULT_DELIVERY_DATE.toString()))
            .andExpect(jsonPath("$.customerName").value(DEFAULT_CUSTOMER_NAME))
            .andExpect(jsonPath("$.customerEmail").value(DEFAULT_CUSTOMER_EMAIL))
            .andExpect(jsonPath("$.nationalRegistrationNumber").value(DEFAULT_NATIONAL_REGISTRATION_NUMBER))
            .andExpect(jsonPath("$.shippingName").value(DEFAULT_SHIPPING_NAME))
            .andExpect(jsonPath("$.shippingAddress").value(DEFAULT_SHIPPING_ADDRESS))
            .andExpect(jsonPath("$.shippingAddress2").value(DEFAULT_SHIPPING_ADDRESS_2))
            .andExpect(jsonPath("$.shippingAddress3").value(DEFAULT_SHIPPING_ADDRESS_3))
            .andExpect(jsonPath("$.shippingAddress4").value(DEFAULT_SHIPPING_ADDRESS_4))
            .andExpect(jsonPath("$.shippingAddress5").value(DEFAULT_SHIPPING_ADDRESS_5))
            .andExpect(jsonPath("$.shippingPhone").value(DEFAULT_SHIPPING_PHONE))
            .andExpect(jsonPath("$.shippingPhone2").value(DEFAULT_SHIPPING_PHONE_2))
            .andExpect(jsonPath("$.shippingCity").value(DEFAULT_SHIPPING_CITY))
            .andExpect(jsonPath("$.shippingPostCode").value(DEFAULT_SHIPPING_POST_CODE))
            .andExpect(jsonPath("$.shippingCountry").value(DEFAULT_SHIPPING_COUNTRY))
            .andExpect(jsonPath("$.shippingRegion").value(DEFAULT_SHIPPING_REGION))
            .andExpect(jsonPath("$.billingName").value(DEFAULT_BILLING_NAME))
            .andExpect(jsonPath("$.billingAddr").value(DEFAULT_BILLING_ADDR))
            .andExpect(jsonPath("$.billingAddr3").value(DEFAULT_BILLING_ADDR_3))
            .andExpect(jsonPath("$.billingAddr4").value(DEFAULT_BILLING_ADDR_4))
            .andExpect(jsonPath("$.billingAddr5").value(DEFAULT_BILLING_ADDR_5))
            .andExpect(jsonPath("$.billingPhone").value(DEFAULT_BILLING_PHONE))
            .andExpect(jsonPath("$.billingPhone2").value(DEFAULT_BILLING_PHONE_2))
            .andExpect(jsonPath("$.billingCity").value(DEFAULT_BILLING_CITY))
            .andExpect(jsonPath("$.billingPostCode").value(DEFAULT_BILLING_POST_CODE))
            .andExpect(jsonPath("$.billingCountry").value(DEFAULT_BILLING_COUNTRY))
            .andExpect(jsonPath("$.taxCode").value(DEFAULT_TAX_CODE))
            .andExpect(jsonPath("$.branchNumber").value(DEFAULT_BRANCH_NUMBER))
            .andExpect(jsonPath("$.taxInvoiceRequested").value(DEFAULT_TAX_INVOICE_REQUESTED))
            .andExpect(jsonPath("$.payMethod").value(DEFAULT_PAY_METHOD))
            .andExpect(jsonPath("$.paidPrice").value(DEFAULT_PAID_PRICE))
            .andExpect(jsonPath("$.unitPrice").value(DEFAULT_UNIT_PRICE))
            .andExpect(jsonPath("$.sellerDiscountTotal").value(DEFAULT_SELLER_DISCOUNT_TOTAL))
            .andExpect(jsonPath("$.shippingFee").value(DEFAULT_SHIPPING_FEE))
            .andExpect(jsonPath("$.walletCredit").value(DEFAULT_WALLET_CREDIT))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.variation").value(DEFAULT_VARIATION))
            .andExpect(jsonPath("$.cdShippingProvider").value(DEFAULT_CD_SHIPPING_PROVIDER))
            .andExpect(jsonPath("$.shippingProvider").value(DEFAULT_SHIPPING_PROVIDER))
            .andExpect(jsonPath("$.shipmentTypeName").value(DEFAULT_SHIPMENT_TYPE_NAME))
            .andExpect(jsonPath("$.shippingProviderType").value(DEFAULT_SHIPPING_PROVIDER_TYPE))
            .andExpect(jsonPath("$.cdTrackingCode").value(DEFAULT_CD_TRACKING_CODE))
            .andExpect(jsonPath("$.trackingCode").value(DEFAULT_TRACKING_CODE))
            .andExpect(jsonPath("$.trackingUrl").value(DEFAULT_TRACKING_URL))
            .andExpect(jsonPath("$.shippingProviderFM").value(DEFAULT_SHIPPING_PROVIDER_FM))
            .andExpect(jsonPath("$.trackingCodeFM").value(DEFAULT_TRACKING_CODE_FM))
            .andExpect(jsonPath("$.trackingUrlFM").value(DEFAULT_TRACKING_URL_FM))
            .andExpect(jsonPath("$.promisedShippingTime").value(DEFAULT_PROMISED_SHIPPING_TIME.toString()))
            .andExpect(jsonPath("$.premium").value(DEFAULT_PREMIUM))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.buyerFailedDeliveryReturnInitiator").value(DEFAULT_BUYER_FAILED_DELIVERY_RETURN_INITIATOR))
            .andExpect(jsonPath("$.buyerFailedDeliveryReason").value(DEFAULT_BUYER_FAILED_DELIVERY_REASON))
            .andExpect(jsonPath("$.buyerFailedDeliveryDetail").value(DEFAULT_BUYER_FAILED_DELIVERY_DETAIL))
            .andExpect(jsonPath("$.buyerFailedDeliveryUserName").value(DEFAULT_BUYER_FAILED_DELIVERY_USER_NAME))
            .andExpect(jsonPath("$.bundleId").value(DEFAULT_BUNDLE_ID))
            .andExpect(jsonPath("$.bundleDiscount").value(DEFAULT_BUNDLE_DISCOUNT))
            .andExpect(jsonPath("$.refundAmount").value(DEFAULT_REFUND_AMOUNT));
    }

    @Test
    @Transactional
    void getNonExistingLazadaOrder() throws Exception {
        // Get the lazadaOrder
        restLazadaOrderMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewLazadaOrder() throws Exception {
        // Initialize the database
        lazadaOrderRepository.saveAndFlush(lazadaOrder);

        int databaseSizeBeforeUpdate = lazadaOrderRepository.findAll().size();

        // Update the lazadaOrder
        LazadaOrder updatedLazadaOrder = lazadaOrderRepository.findById(lazadaOrder.getId()).get();
        // Disconnect from session so that the updates on updatedLazadaOrder are not directly saved in db
        em.detach(updatedLazadaOrder);
        updatedLazadaOrder
            .orderItemId(UPDATED_ORDER_ITEM_ID)
            .orderType(UPDATED_ORDER_TYPE)
            .guarantee(UPDATED_GUARANTEE)
            .deliveryType(UPDATED_DELIVERY_TYPE)
            .lazadaId(UPDATED_LAZADA_ID)
            .sellerSku(UPDATED_SELLER_SKU)
            .wareHouse(UPDATED_WARE_HOUSE)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME)
            .rtaSla(UPDATED_RTA_SLA)
            .ttsSla(UPDATED_TTS_SLA)
            .orderNumber(UPDATED_ORDER_NUMBER)
            .invoiceRequired(UPDATED_INVOICE_REQUIRED)
            .invoiceNumber(UPDATED_INVOICE_NUMBER)
            .deliveryDate(UPDATED_DELIVERY_DATE)
            .customerName(UPDATED_CUSTOMER_NAME)
            .customerEmail(UPDATED_CUSTOMER_EMAIL)
            .nationalRegistrationNumber(UPDATED_NATIONAL_REGISTRATION_NUMBER)
            .shippingName(UPDATED_SHIPPING_NAME)
            .shippingAddress(UPDATED_SHIPPING_ADDRESS)
            .shippingAddress2(UPDATED_SHIPPING_ADDRESS_2)
            .shippingAddress3(UPDATED_SHIPPING_ADDRESS_3)
            .shippingAddress4(UPDATED_SHIPPING_ADDRESS_4)
            .shippingAddress5(UPDATED_SHIPPING_ADDRESS_5)
            .shippingPhone(UPDATED_SHIPPING_PHONE)
            .shippingPhone2(UPDATED_SHIPPING_PHONE_2)
            .shippingCity(UPDATED_SHIPPING_CITY)
            .shippingPostCode(UPDATED_SHIPPING_POST_CODE)
            .shippingCountry(UPDATED_SHIPPING_COUNTRY)
            .shippingRegion(UPDATED_SHIPPING_REGION)
            .billingName(UPDATED_BILLING_NAME)
            .billingAddr(UPDATED_BILLING_ADDR)
            .billingAddr3(UPDATED_BILLING_ADDR_3)
            .billingAddr4(UPDATED_BILLING_ADDR_4)
            .billingAddr5(UPDATED_BILLING_ADDR_5)
            .billingPhone(UPDATED_BILLING_PHONE)
            .billingPhone2(UPDATED_BILLING_PHONE_2)
            .billingCity(UPDATED_BILLING_CITY)
            .billingPostCode(UPDATED_BILLING_POST_CODE)
            .billingCountry(UPDATED_BILLING_COUNTRY)
            .taxCode(UPDATED_TAX_CODE)
            .branchNumber(UPDATED_BRANCH_NUMBER)
            .taxInvoiceRequested(UPDATED_TAX_INVOICE_REQUESTED)
            .payMethod(UPDATED_PAY_METHOD)
            .paidPrice(UPDATED_PAID_PRICE)
            .unitPrice(UPDATED_UNIT_PRICE)
            .sellerDiscountTotal(UPDATED_SELLER_DISCOUNT_TOTAL)
            .shippingFee(UPDATED_SHIPPING_FEE)
            .walletCredit(UPDATED_WALLET_CREDIT)
            .itemName(UPDATED_ITEM_NAME)
            .variation(UPDATED_VARIATION)
            .cdShippingProvider(UPDATED_CD_SHIPPING_PROVIDER)
            .shippingProvider(UPDATED_SHIPPING_PROVIDER)
            .shipmentTypeName(UPDATED_SHIPMENT_TYPE_NAME)
            .shippingProviderType(UPDATED_SHIPPING_PROVIDER_TYPE)
            .cdTrackingCode(UPDATED_CD_TRACKING_CODE)
            .trackingCode(UPDATED_TRACKING_CODE)
            .trackingUrl(UPDATED_TRACKING_URL)
            .shippingProviderFM(UPDATED_SHIPPING_PROVIDER_FM)
            .trackingCodeFM(UPDATED_TRACKING_CODE_FM)
            .trackingUrlFM(UPDATED_TRACKING_URL_FM)
            .promisedShippingTime(UPDATED_PROMISED_SHIPPING_TIME)
            .premium(UPDATED_PREMIUM)
            .status(UPDATED_STATUS)
            .buyerFailedDeliveryReturnInitiator(UPDATED_BUYER_FAILED_DELIVERY_RETURN_INITIATOR)
            .buyerFailedDeliveryReason(UPDATED_BUYER_FAILED_DELIVERY_REASON)
            .buyerFailedDeliveryDetail(UPDATED_BUYER_FAILED_DELIVERY_DETAIL)
            .buyerFailedDeliveryUserName(UPDATED_BUYER_FAILED_DELIVERY_USER_NAME)
            .bundleId(UPDATED_BUNDLE_ID)
            .bundleDiscount(UPDATED_BUNDLE_DISCOUNT)
            .refundAmount(UPDATED_REFUND_AMOUNT);

        restLazadaOrderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedLazadaOrder.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedLazadaOrder))
            )
            .andExpect(status().isOk());

        // Validate the LazadaOrder in the database
        List<LazadaOrder> lazadaOrderList = lazadaOrderRepository.findAll();
        assertThat(lazadaOrderList).hasSize(databaseSizeBeforeUpdate);
        LazadaOrder testLazadaOrder = lazadaOrderList.get(lazadaOrderList.size() - 1);
        assertThat(testLazadaOrder.getOrderItemId()).isEqualTo(UPDATED_ORDER_ITEM_ID);
        assertThat(testLazadaOrder.getOrderType()).isEqualTo(UPDATED_ORDER_TYPE);
        assertThat(testLazadaOrder.getGuarantee()).isEqualTo(UPDATED_GUARANTEE);
        assertThat(testLazadaOrder.getDeliveryType()).isEqualTo(UPDATED_DELIVERY_TYPE);
        assertThat(testLazadaOrder.getLazadaId()).isEqualTo(UPDATED_LAZADA_ID);
        assertThat(testLazadaOrder.getSellerSku()).isEqualTo(UPDATED_SELLER_SKU);
        assertThat(testLazadaOrder.getWareHouse()).isEqualTo(UPDATED_WARE_HOUSE);
        assertThat(testLazadaOrder.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testLazadaOrder.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testLazadaOrder.getRtaSla()).isEqualTo(UPDATED_RTA_SLA);
        assertThat(testLazadaOrder.getTtsSla()).isEqualTo(UPDATED_TTS_SLA);
        assertThat(testLazadaOrder.getOrderNumber()).isEqualTo(UPDATED_ORDER_NUMBER);
        assertThat(testLazadaOrder.getInvoiceRequired()).isEqualTo(UPDATED_INVOICE_REQUIRED);
        assertThat(testLazadaOrder.getInvoiceNumber()).isEqualTo(UPDATED_INVOICE_NUMBER);
        assertThat(testLazadaOrder.getDeliveryDate()).isEqualTo(UPDATED_DELIVERY_DATE);
        assertThat(testLazadaOrder.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testLazadaOrder.getCustomerEmail()).isEqualTo(UPDATED_CUSTOMER_EMAIL);
        assertThat(testLazadaOrder.getNationalRegistrationNumber()).isEqualTo(UPDATED_NATIONAL_REGISTRATION_NUMBER);
        assertThat(testLazadaOrder.getShippingName()).isEqualTo(UPDATED_SHIPPING_NAME);
        assertThat(testLazadaOrder.getShippingAddress()).isEqualTo(UPDATED_SHIPPING_ADDRESS);
        assertThat(testLazadaOrder.getShippingAddress2()).isEqualTo(UPDATED_SHIPPING_ADDRESS_2);
        assertThat(testLazadaOrder.getShippingAddress3()).isEqualTo(UPDATED_SHIPPING_ADDRESS_3);
        assertThat(testLazadaOrder.getShippingAddress4()).isEqualTo(UPDATED_SHIPPING_ADDRESS_4);
        assertThat(testLazadaOrder.getShippingAddress5()).isEqualTo(UPDATED_SHIPPING_ADDRESS_5);
        assertThat(testLazadaOrder.getShippingPhone()).isEqualTo(UPDATED_SHIPPING_PHONE);
        assertThat(testLazadaOrder.getShippingPhone2()).isEqualTo(UPDATED_SHIPPING_PHONE_2);
        assertThat(testLazadaOrder.getShippingCity()).isEqualTo(UPDATED_SHIPPING_CITY);
        assertThat(testLazadaOrder.getShippingPostCode()).isEqualTo(UPDATED_SHIPPING_POST_CODE);
        assertThat(testLazadaOrder.getShippingCountry()).isEqualTo(UPDATED_SHIPPING_COUNTRY);
        assertThat(testLazadaOrder.getShippingRegion()).isEqualTo(UPDATED_SHIPPING_REGION);
        assertThat(testLazadaOrder.getBillingName()).isEqualTo(UPDATED_BILLING_NAME);
        assertThat(testLazadaOrder.getBillingAddr()).isEqualTo(UPDATED_BILLING_ADDR);
        assertThat(testLazadaOrder.getBillingAddr3()).isEqualTo(UPDATED_BILLING_ADDR_3);
        assertThat(testLazadaOrder.getBillingAddr4()).isEqualTo(UPDATED_BILLING_ADDR_4);
        assertThat(testLazadaOrder.getBillingAddr5()).isEqualTo(UPDATED_BILLING_ADDR_5);
        assertThat(testLazadaOrder.getBillingPhone()).isEqualTo(UPDATED_BILLING_PHONE);
        assertThat(testLazadaOrder.getBillingPhone2()).isEqualTo(UPDATED_BILLING_PHONE_2);
        assertThat(testLazadaOrder.getBillingCity()).isEqualTo(UPDATED_BILLING_CITY);
        assertThat(testLazadaOrder.getBillingPostCode()).isEqualTo(UPDATED_BILLING_POST_CODE);
        assertThat(testLazadaOrder.getBillingCountry()).isEqualTo(UPDATED_BILLING_COUNTRY);
        assertThat(testLazadaOrder.getTaxCode()).isEqualTo(UPDATED_TAX_CODE);
        assertThat(testLazadaOrder.getBranchNumber()).isEqualTo(UPDATED_BRANCH_NUMBER);
        assertThat(testLazadaOrder.getTaxInvoiceRequested()).isEqualTo(UPDATED_TAX_INVOICE_REQUESTED);
        assertThat(testLazadaOrder.getPayMethod()).isEqualTo(UPDATED_PAY_METHOD);
        assertThat(testLazadaOrder.getPaidPrice()).isEqualTo(UPDATED_PAID_PRICE);
        assertThat(testLazadaOrder.getUnitPrice()).isEqualTo(UPDATED_UNIT_PRICE);
        assertThat(testLazadaOrder.getSellerDiscountTotal()).isEqualTo(UPDATED_SELLER_DISCOUNT_TOTAL);
        assertThat(testLazadaOrder.getShippingFee()).isEqualTo(UPDATED_SHIPPING_FEE);
        assertThat(testLazadaOrder.getWalletCredit()).isEqualTo(UPDATED_WALLET_CREDIT);
        assertThat(testLazadaOrder.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testLazadaOrder.getVariation()).isEqualTo(UPDATED_VARIATION);
        assertThat(testLazadaOrder.getCdShippingProvider()).isEqualTo(UPDATED_CD_SHIPPING_PROVIDER);
        assertThat(testLazadaOrder.getShippingProvider()).isEqualTo(UPDATED_SHIPPING_PROVIDER);
        assertThat(testLazadaOrder.getShipmentTypeName()).isEqualTo(UPDATED_SHIPMENT_TYPE_NAME);
        assertThat(testLazadaOrder.getShippingProviderType()).isEqualTo(UPDATED_SHIPPING_PROVIDER_TYPE);
        assertThat(testLazadaOrder.getCdTrackingCode()).isEqualTo(UPDATED_CD_TRACKING_CODE);
        assertThat(testLazadaOrder.getTrackingCode()).isEqualTo(UPDATED_TRACKING_CODE);
        assertThat(testLazadaOrder.getTrackingUrl()).isEqualTo(UPDATED_TRACKING_URL);
        assertThat(testLazadaOrder.getShippingProviderFM()).isEqualTo(UPDATED_SHIPPING_PROVIDER_FM);
        assertThat(testLazadaOrder.getTrackingCodeFM()).isEqualTo(UPDATED_TRACKING_CODE_FM);
        assertThat(testLazadaOrder.getTrackingUrlFM()).isEqualTo(UPDATED_TRACKING_URL_FM);
        assertThat(testLazadaOrder.getPromisedShippingTime()).isEqualTo(UPDATED_PROMISED_SHIPPING_TIME);
        assertThat(testLazadaOrder.getPremium()).isEqualTo(UPDATED_PREMIUM);
        assertThat(testLazadaOrder.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testLazadaOrder.getBuyerFailedDeliveryReturnInitiator()).isEqualTo(UPDATED_BUYER_FAILED_DELIVERY_RETURN_INITIATOR);
        assertThat(testLazadaOrder.getBuyerFailedDeliveryReason()).isEqualTo(UPDATED_BUYER_FAILED_DELIVERY_REASON);
        assertThat(testLazadaOrder.getBuyerFailedDeliveryDetail()).isEqualTo(UPDATED_BUYER_FAILED_DELIVERY_DETAIL);
        assertThat(testLazadaOrder.getBuyerFailedDeliveryUserName()).isEqualTo(UPDATED_BUYER_FAILED_DELIVERY_USER_NAME);
        assertThat(testLazadaOrder.getBundleId()).isEqualTo(UPDATED_BUNDLE_ID);
        assertThat(testLazadaOrder.getBundleDiscount()).isEqualTo(UPDATED_BUNDLE_DISCOUNT);
        assertThat(testLazadaOrder.getRefundAmount()).isEqualTo(UPDATED_REFUND_AMOUNT);
    }

    @Test
    @Transactional
    void putNonExistingLazadaOrder() throws Exception {
        int databaseSizeBeforeUpdate = lazadaOrderRepository.findAll().size();
        lazadaOrder.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLazadaOrderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, lazadaOrder.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(lazadaOrder))
            )
            .andExpect(status().isBadRequest());

        // Validate the LazadaOrder in the database
        List<LazadaOrder> lazadaOrderList = lazadaOrderRepository.findAll();
        assertThat(lazadaOrderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLazadaOrder() throws Exception {
        int databaseSizeBeforeUpdate = lazadaOrderRepository.findAll().size();
        lazadaOrder.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLazadaOrderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(lazadaOrder))
            )
            .andExpect(status().isBadRequest());

        // Validate the LazadaOrder in the database
        List<LazadaOrder> lazadaOrderList = lazadaOrderRepository.findAll();
        assertThat(lazadaOrderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLazadaOrder() throws Exception {
        int databaseSizeBeforeUpdate = lazadaOrderRepository.findAll().size();
        lazadaOrder.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLazadaOrderMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(lazadaOrder)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LazadaOrder in the database
        List<LazadaOrder> lazadaOrderList = lazadaOrderRepository.findAll();
        assertThat(lazadaOrderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLazadaOrderWithPatch() throws Exception {
        // Initialize the database
        lazadaOrderRepository.saveAndFlush(lazadaOrder);

        int databaseSizeBeforeUpdate = lazadaOrderRepository.findAll().size();

        // Update the lazadaOrder using partial update
        LazadaOrder partialUpdatedLazadaOrder = new LazadaOrder();
        partialUpdatedLazadaOrder.setId(lazadaOrder.getId());

        partialUpdatedLazadaOrder
            .orderItemId(UPDATED_ORDER_ITEM_ID)
            .guarantee(UPDATED_GUARANTEE)
            .deliveryType(UPDATED_DELIVERY_TYPE)
            .lazadaId(UPDATED_LAZADA_ID)
            .sellerSku(UPDATED_SELLER_SKU)
            .wareHouse(UPDATED_WARE_HOUSE)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME)
            .rtaSla(UPDATED_RTA_SLA)
            .ttsSla(UPDATED_TTS_SLA)
            .invoiceRequired(UPDATED_INVOICE_REQUIRED)
            .deliveryDate(UPDATED_DELIVERY_DATE)
            .customerName(UPDATED_CUSTOMER_NAME)
            .shippingAddress5(UPDATED_SHIPPING_ADDRESS_5)
            .shippingPostCode(UPDATED_SHIPPING_POST_CODE)
            .shippingCountry(UPDATED_SHIPPING_COUNTRY)
            .shippingRegion(UPDATED_SHIPPING_REGION)
            .billingAddr3(UPDATED_BILLING_ADDR_3)
            .billingAddr4(UPDATED_BILLING_ADDR_4)
            .billingAddr5(UPDATED_BILLING_ADDR_5)
            .billingPhone(UPDATED_BILLING_PHONE)
            .billingPhone2(UPDATED_BILLING_PHONE_2)
            .billingCity(UPDATED_BILLING_CITY)
            .billingCountry(UPDATED_BILLING_COUNTRY)
            .taxCode(UPDATED_TAX_CODE)
            .unitPrice(UPDATED_UNIT_PRICE)
            .sellerDiscountTotal(UPDATED_SELLER_DISCOUNT_TOTAL)
            .shippingFee(UPDATED_SHIPPING_FEE)
            .shippingProvider(UPDATED_SHIPPING_PROVIDER)
            .cdTrackingCode(UPDATED_CD_TRACKING_CODE)
            .trackingUrl(UPDATED_TRACKING_URL)
            .trackingCodeFM(UPDATED_TRACKING_CODE_FM)
            .trackingUrlFM(UPDATED_TRACKING_URL_FM)
            .promisedShippingTime(UPDATED_PROMISED_SHIPPING_TIME)
            .premium(UPDATED_PREMIUM)
            .refundAmount(UPDATED_REFUND_AMOUNT);

        restLazadaOrderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLazadaOrder.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLazadaOrder))
            )
            .andExpect(status().isOk());

        // Validate the LazadaOrder in the database
        List<LazadaOrder> lazadaOrderList = lazadaOrderRepository.findAll();
        assertThat(lazadaOrderList).hasSize(databaseSizeBeforeUpdate);
        LazadaOrder testLazadaOrder = lazadaOrderList.get(lazadaOrderList.size() - 1);
        assertThat(testLazadaOrder.getOrderItemId()).isEqualTo(UPDATED_ORDER_ITEM_ID);
        assertThat(testLazadaOrder.getOrderType()).isEqualTo(DEFAULT_ORDER_TYPE);
        assertThat(testLazadaOrder.getGuarantee()).isEqualTo(UPDATED_GUARANTEE);
        assertThat(testLazadaOrder.getDeliveryType()).isEqualTo(UPDATED_DELIVERY_TYPE);
        assertThat(testLazadaOrder.getLazadaId()).isEqualTo(UPDATED_LAZADA_ID);
        assertThat(testLazadaOrder.getSellerSku()).isEqualTo(UPDATED_SELLER_SKU);
        assertThat(testLazadaOrder.getWareHouse()).isEqualTo(UPDATED_WARE_HOUSE);
        assertThat(testLazadaOrder.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testLazadaOrder.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testLazadaOrder.getRtaSla()).isEqualTo(UPDATED_RTA_SLA);
        assertThat(testLazadaOrder.getTtsSla()).isEqualTo(UPDATED_TTS_SLA);
        assertThat(testLazadaOrder.getOrderNumber()).isEqualTo(DEFAULT_ORDER_NUMBER);
        assertThat(testLazadaOrder.getInvoiceRequired()).isEqualTo(UPDATED_INVOICE_REQUIRED);
        assertThat(testLazadaOrder.getInvoiceNumber()).isEqualTo(DEFAULT_INVOICE_NUMBER);
        assertThat(testLazadaOrder.getDeliveryDate()).isEqualTo(UPDATED_DELIVERY_DATE);
        assertThat(testLazadaOrder.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testLazadaOrder.getCustomerEmail()).isEqualTo(DEFAULT_CUSTOMER_EMAIL);
        assertThat(testLazadaOrder.getNationalRegistrationNumber()).isEqualTo(DEFAULT_NATIONAL_REGISTRATION_NUMBER);
        assertThat(testLazadaOrder.getShippingName()).isEqualTo(DEFAULT_SHIPPING_NAME);
        assertThat(testLazadaOrder.getShippingAddress()).isEqualTo(DEFAULT_SHIPPING_ADDRESS);
        assertThat(testLazadaOrder.getShippingAddress2()).isEqualTo(DEFAULT_SHIPPING_ADDRESS_2);
        assertThat(testLazadaOrder.getShippingAddress3()).isEqualTo(DEFAULT_SHIPPING_ADDRESS_3);
        assertThat(testLazadaOrder.getShippingAddress4()).isEqualTo(DEFAULT_SHIPPING_ADDRESS_4);
        assertThat(testLazadaOrder.getShippingAddress5()).isEqualTo(UPDATED_SHIPPING_ADDRESS_5);
        assertThat(testLazadaOrder.getShippingPhone()).isEqualTo(DEFAULT_SHIPPING_PHONE);
        assertThat(testLazadaOrder.getShippingPhone2()).isEqualTo(DEFAULT_SHIPPING_PHONE_2);
        assertThat(testLazadaOrder.getShippingCity()).isEqualTo(DEFAULT_SHIPPING_CITY);
        assertThat(testLazadaOrder.getShippingPostCode()).isEqualTo(UPDATED_SHIPPING_POST_CODE);
        assertThat(testLazadaOrder.getShippingCountry()).isEqualTo(UPDATED_SHIPPING_COUNTRY);
        assertThat(testLazadaOrder.getShippingRegion()).isEqualTo(UPDATED_SHIPPING_REGION);
        assertThat(testLazadaOrder.getBillingName()).isEqualTo(DEFAULT_BILLING_NAME);
        assertThat(testLazadaOrder.getBillingAddr()).isEqualTo(DEFAULT_BILLING_ADDR);
        assertThat(testLazadaOrder.getBillingAddr3()).isEqualTo(UPDATED_BILLING_ADDR_3);
        assertThat(testLazadaOrder.getBillingAddr4()).isEqualTo(UPDATED_BILLING_ADDR_4);
        assertThat(testLazadaOrder.getBillingAddr5()).isEqualTo(UPDATED_BILLING_ADDR_5);
        assertThat(testLazadaOrder.getBillingPhone()).isEqualTo(UPDATED_BILLING_PHONE);
        assertThat(testLazadaOrder.getBillingPhone2()).isEqualTo(UPDATED_BILLING_PHONE_2);
        assertThat(testLazadaOrder.getBillingCity()).isEqualTo(UPDATED_BILLING_CITY);
        assertThat(testLazadaOrder.getBillingPostCode()).isEqualTo(DEFAULT_BILLING_POST_CODE);
        assertThat(testLazadaOrder.getBillingCountry()).isEqualTo(UPDATED_BILLING_COUNTRY);
        assertThat(testLazadaOrder.getTaxCode()).isEqualTo(UPDATED_TAX_CODE);
        assertThat(testLazadaOrder.getBranchNumber()).isEqualTo(DEFAULT_BRANCH_NUMBER);
        assertThat(testLazadaOrder.getTaxInvoiceRequested()).isEqualTo(DEFAULT_TAX_INVOICE_REQUESTED);
        assertThat(testLazadaOrder.getPayMethod()).isEqualTo(DEFAULT_PAY_METHOD);
        assertThat(testLazadaOrder.getPaidPrice()).isEqualTo(DEFAULT_PAID_PRICE);
        assertThat(testLazadaOrder.getUnitPrice()).isEqualTo(UPDATED_UNIT_PRICE);
        assertThat(testLazadaOrder.getSellerDiscountTotal()).isEqualTo(UPDATED_SELLER_DISCOUNT_TOTAL);
        assertThat(testLazadaOrder.getShippingFee()).isEqualTo(UPDATED_SHIPPING_FEE);
        assertThat(testLazadaOrder.getWalletCredit()).isEqualTo(DEFAULT_WALLET_CREDIT);
        assertThat(testLazadaOrder.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testLazadaOrder.getVariation()).isEqualTo(DEFAULT_VARIATION);
        assertThat(testLazadaOrder.getCdShippingProvider()).isEqualTo(DEFAULT_CD_SHIPPING_PROVIDER);
        assertThat(testLazadaOrder.getShippingProvider()).isEqualTo(UPDATED_SHIPPING_PROVIDER);
        assertThat(testLazadaOrder.getShipmentTypeName()).isEqualTo(DEFAULT_SHIPMENT_TYPE_NAME);
        assertThat(testLazadaOrder.getShippingProviderType()).isEqualTo(DEFAULT_SHIPPING_PROVIDER_TYPE);
        assertThat(testLazadaOrder.getCdTrackingCode()).isEqualTo(UPDATED_CD_TRACKING_CODE);
        assertThat(testLazadaOrder.getTrackingCode()).isEqualTo(DEFAULT_TRACKING_CODE);
        assertThat(testLazadaOrder.getTrackingUrl()).isEqualTo(UPDATED_TRACKING_URL);
        assertThat(testLazadaOrder.getShippingProviderFM()).isEqualTo(DEFAULT_SHIPPING_PROVIDER_FM);
        assertThat(testLazadaOrder.getTrackingCodeFM()).isEqualTo(UPDATED_TRACKING_CODE_FM);
        assertThat(testLazadaOrder.getTrackingUrlFM()).isEqualTo(UPDATED_TRACKING_URL_FM);
        assertThat(testLazadaOrder.getPromisedShippingTime()).isEqualTo(UPDATED_PROMISED_SHIPPING_TIME);
        assertThat(testLazadaOrder.getPremium()).isEqualTo(UPDATED_PREMIUM);
        assertThat(testLazadaOrder.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testLazadaOrder.getBuyerFailedDeliveryReturnInitiator()).isEqualTo(DEFAULT_BUYER_FAILED_DELIVERY_RETURN_INITIATOR);
        assertThat(testLazadaOrder.getBuyerFailedDeliveryReason()).isEqualTo(DEFAULT_BUYER_FAILED_DELIVERY_REASON);
        assertThat(testLazadaOrder.getBuyerFailedDeliveryDetail()).isEqualTo(DEFAULT_BUYER_FAILED_DELIVERY_DETAIL);
        assertThat(testLazadaOrder.getBuyerFailedDeliveryUserName()).isEqualTo(DEFAULT_BUYER_FAILED_DELIVERY_USER_NAME);
        assertThat(testLazadaOrder.getBundleId()).isEqualTo(DEFAULT_BUNDLE_ID);
        assertThat(testLazadaOrder.getBundleDiscount()).isEqualTo(DEFAULT_BUNDLE_DISCOUNT);
        assertThat(testLazadaOrder.getRefundAmount()).isEqualTo(UPDATED_REFUND_AMOUNT);
    }

    @Test
    @Transactional
    void fullUpdateLazadaOrderWithPatch() throws Exception {
        // Initialize the database
        lazadaOrderRepository.saveAndFlush(lazadaOrder);

        int databaseSizeBeforeUpdate = lazadaOrderRepository.findAll().size();

        // Update the lazadaOrder using partial update
        LazadaOrder partialUpdatedLazadaOrder = new LazadaOrder();
        partialUpdatedLazadaOrder.setId(lazadaOrder.getId());

        partialUpdatedLazadaOrder
            .orderItemId(UPDATED_ORDER_ITEM_ID)
            .orderType(UPDATED_ORDER_TYPE)
            .guarantee(UPDATED_GUARANTEE)
            .deliveryType(UPDATED_DELIVERY_TYPE)
            .lazadaId(UPDATED_LAZADA_ID)
            .sellerSku(UPDATED_SELLER_SKU)
            .wareHouse(UPDATED_WARE_HOUSE)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME)
            .rtaSla(UPDATED_RTA_SLA)
            .ttsSla(UPDATED_TTS_SLA)
            .orderNumber(UPDATED_ORDER_NUMBER)
            .invoiceRequired(UPDATED_INVOICE_REQUIRED)
            .invoiceNumber(UPDATED_INVOICE_NUMBER)
            .deliveryDate(UPDATED_DELIVERY_DATE)
            .customerName(UPDATED_CUSTOMER_NAME)
            .customerEmail(UPDATED_CUSTOMER_EMAIL)
            .nationalRegistrationNumber(UPDATED_NATIONAL_REGISTRATION_NUMBER)
            .shippingName(UPDATED_SHIPPING_NAME)
            .shippingAddress(UPDATED_SHIPPING_ADDRESS)
            .shippingAddress2(UPDATED_SHIPPING_ADDRESS_2)
            .shippingAddress3(UPDATED_SHIPPING_ADDRESS_3)
            .shippingAddress4(UPDATED_SHIPPING_ADDRESS_4)
            .shippingAddress5(UPDATED_SHIPPING_ADDRESS_5)
            .shippingPhone(UPDATED_SHIPPING_PHONE)
            .shippingPhone2(UPDATED_SHIPPING_PHONE_2)
            .shippingCity(UPDATED_SHIPPING_CITY)
            .shippingPostCode(UPDATED_SHIPPING_POST_CODE)
            .shippingCountry(UPDATED_SHIPPING_COUNTRY)
            .shippingRegion(UPDATED_SHIPPING_REGION)
            .billingName(UPDATED_BILLING_NAME)
            .billingAddr(UPDATED_BILLING_ADDR)
            .billingAddr3(UPDATED_BILLING_ADDR_3)
            .billingAddr4(UPDATED_BILLING_ADDR_4)
            .billingAddr5(UPDATED_BILLING_ADDR_5)
            .billingPhone(UPDATED_BILLING_PHONE)
            .billingPhone2(UPDATED_BILLING_PHONE_2)
            .billingCity(UPDATED_BILLING_CITY)
            .billingPostCode(UPDATED_BILLING_POST_CODE)
            .billingCountry(UPDATED_BILLING_COUNTRY)
            .taxCode(UPDATED_TAX_CODE)
            .branchNumber(UPDATED_BRANCH_NUMBER)
            .taxInvoiceRequested(UPDATED_TAX_INVOICE_REQUESTED)
            .payMethod(UPDATED_PAY_METHOD)
            .paidPrice(UPDATED_PAID_PRICE)
            .unitPrice(UPDATED_UNIT_PRICE)
            .sellerDiscountTotal(UPDATED_SELLER_DISCOUNT_TOTAL)
            .shippingFee(UPDATED_SHIPPING_FEE)
            .walletCredit(UPDATED_WALLET_CREDIT)
            .itemName(UPDATED_ITEM_NAME)
            .variation(UPDATED_VARIATION)
            .cdShippingProvider(UPDATED_CD_SHIPPING_PROVIDER)
            .shippingProvider(UPDATED_SHIPPING_PROVIDER)
            .shipmentTypeName(UPDATED_SHIPMENT_TYPE_NAME)
            .shippingProviderType(UPDATED_SHIPPING_PROVIDER_TYPE)
            .cdTrackingCode(UPDATED_CD_TRACKING_CODE)
            .trackingCode(UPDATED_TRACKING_CODE)
            .trackingUrl(UPDATED_TRACKING_URL)
            .shippingProviderFM(UPDATED_SHIPPING_PROVIDER_FM)
            .trackingCodeFM(UPDATED_TRACKING_CODE_FM)
            .trackingUrlFM(UPDATED_TRACKING_URL_FM)
            .promisedShippingTime(UPDATED_PROMISED_SHIPPING_TIME)
            .premium(UPDATED_PREMIUM)
            .status(UPDATED_STATUS)
            .buyerFailedDeliveryReturnInitiator(UPDATED_BUYER_FAILED_DELIVERY_RETURN_INITIATOR)
            .buyerFailedDeliveryReason(UPDATED_BUYER_FAILED_DELIVERY_REASON)
            .buyerFailedDeliveryDetail(UPDATED_BUYER_FAILED_DELIVERY_DETAIL)
            .buyerFailedDeliveryUserName(UPDATED_BUYER_FAILED_DELIVERY_USER_NAME)
            .bundleId(UPDATED_BUNDLE_ID)
            .bundleDiscount(UPDATED_BUNDLE_DISCOUNT)
            .refundAmount(UPDATED_REFUND_AMOUNT);

        restLazadaOrderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLazadaOrder.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLazadaOrder))
            )
            .andExpect(status().isOk());

        // Validate the LazadaOrder in the database
        List<LazadaOrder> lazadaOrderList = lazadaOrderRepository.findAll();
        assertThat(lazadaOrderList).hasSize(databaseSizeBeforeUpdate);
        LazadaOrder testLazadaOrder = lazadaOrderList.get(lazadaOrderList.size() - 1);
        assertThat(testLazadaOrder.getOrderItemId()).isEqualTo(UPDATED_ORDER_ITEM_ID);
        assertThat(testLazadaOrder.getOrderType()).isEqualTo(UPDATED_ORDER_TYPE);
        assertThat(testLazadaOrder.getGuarantee()).isEqualTo(UPDATED_GUARANTEE);
        assertThat(testLazadaOrder.getDeliveryType()).isEqualTo(UPDATED_DELIVERY_TYPE);
        assertThat(testLazadaOrder.getLazadaId()).isEqualTo(UPDATED_LAZADA_ID);
        assertThat(testLazadaOrder.getSellerSku()).isEqualTo(UPDATED_SELLER_SKU);
        assertThat(testLazadaOrder.getWareHouse()).isEqualTo(UPDATED_WARE_HOUSE);
        assertThat(testLazadaOrder.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testLazadaOrder.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testLazadaOrder.getRtaSla()).isEqualTo(UPDATED_RTA_SLA);
        assertThat(testLazadaOrder.getTtsSla()).isEqualTo(UPDATED_TTS_SLA);
        assertThat(testLazadaOrder.getOrderNumber()).isEqualTo(UPDATED_ORDER_NUMBER);
        assertThat(testLazadaOrder.getInvoiceRequired()).isEqualTo(UPDATED_INVOICE_REQUIRED);
        assertThat(testLazadaOrder.getInvoiceNumber()).isEqualTo(UPDATED_INVOICE_NUMBER);
        assertThat(testLazadaOrder.getDeliveryDate()).isEqualTo(UPDATED_DELIVERY_DATE);
        assertThat(testLazadaOrder.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testLazadaOrder.getCustomerEmail()).isEqualTo(UPDATED_CUSTOMER_EMAIL);
        assertThat(testLazadaOrder.getNationalRegistrationNumber()).isEqualTo(UPDATED_NATIONAL_REGISTRATION_NUMBER);
        assertThat(testLazadaOrder.getShippingName()).isEqualTo(UPDATED_SHIPPING_NAME);
        assertThat(testLazadaOrder.getShippingAddress()).isEqualTo(UPDATED_SHIPPING_ADDRESS);
        assertThat(testLazadaOrder.getShippingAddress2()).isEqualTo(UPDATED_SHIPPING_ADDRESS_2);
        assertThat(testLazadaOrder.getShippingAddress3()).isEqualTo(UPDATED_SHIPPING_ADDRESS_3);
        assertThat(testLazadaOrder.getShippingAddress4()).isEqualTo(UPDATED_SHIPPING_ADDRESS_4);
        assertThat(testLazadaOrder.getShippingAddress5()).isEqualTo(UPDATED_SHIPPING_ADDRESS_5);
        assertThat(testLazadaOrder.getShippingPhone()).isEqualTo(UPDATED_SHIPPING_PHONE);
        assertThat(testLazadaOrder.getShippingPhone2()).isEqualTo(UPDATED_SHIPPING_PHONE_2);
        assertThat(testLazadaOrder.getShippingCity()).isEqualTo(UPDATED_SHIPPING_CITY);
        assertThat(testLazadaOrder.getShippingPostCode()).isEqualTo(UPDATED_SHIPPING_POST_CODE);
        assertThat(testLazadaOrder.getShippingCountry()).isEqualTo(UPDATED_SHIPPING_COUNTRY);
        assertThat(testLazadaOrder.getShippingRegion()).isEqualTo(UPDATED_SHIPPING_REGION);
        assertThat(testLazadaOrder.getBillingName()).isEqualTo(UPDATED_BILLING_NAME);
        assertThat(testLazadaOrder.getBillingAddr()).isEqualTo(UPDATED_BILLING_ADDR);
        assertThat(testLazadaOrder.getBillingAddr3()).isEqualTo(UPDATED_BILLING_ADDR_3);
        assertThat(testLazadaOrder.getBillingAddr4()).isEqualTo(UPDATED_BILLING_ADDR_4);
        assertThat(testLazadaOrder.getBillingAddr5()).isEqualTo(UPDATED_BILLING_ADDR_5);
        assertThat(testLazadaOrder.getBillingPhone()).isEqualTo(UPDATED_BILLING_PHONE);
        assertThat(testLazadaOrder.getBillingPhone2()).isEqualTo(UPDATED_BILLING_PHONE_2);
        assertThat(testLazadaOrder.getBillingCity()).isEqualTo(UPDATED_BILLING_CITY);
        assertThat(testLazadaOrder.getBillingPostCode()).isEqualTo(UPDATED_BILLING_POST_CODE);
        assertThat(testLazadaOrder.getBillingCountry()).isEqualTo(UPDATED_BILLING_COUNTRY);
        assertThat(testLazadaOrder.getTaxCode()).isEqualTo(UPDATED_TAX_CODE);
        assertThat(testLazadaOrder.getBranchNumber()).isEqualTo(UPDATED_BRANCH_NUMBER);
        assertThat(testLazadaOrder.getTaxInvoiceRequested()).isEqualTo(UPDATED_TAX_INVOICE_REQUESTED);
        assertThat(testLazadaOrder.getPayMethod()).isEqualTo(UPDATED_PAY_METHOD);
        assertThat(testLazadaOrder.getPaidPrice()).isEqualTo(UPDATED_PAID_PRICE);
        assertThat(testLazadaOrder.getUnitPrice()).isEqualTo(UPDATED_UNIT_PRICE);
        assertThat(testLazadaOrder.getSellerDiscountTotal()).isEqualTo(UPDATED_SELLER_DISCOUNT_TOTAL);
        assertThat(testLazadaOrder.getShippingFee()).isEqualTo(UPDATED_SHIPPING_FEE);
        assertThat(testLazadaOrder.getWalletCredit()).isEqualTo(UPDATED_WALLET_CREDIT);
        assertThat(testLazadaOrder.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testLazadaOrder.getVariation()).isEqualTo(UPDATED_VARIATION);
        assertThat(testLazadaOrder.getCdShippingProvider()).isEqualTo(UPDATED_CD_SHIPPING_PROVIDER);
        assertThat(testLazadaOrder.getShippingProvider()).isEqualTo(UPDATED_SHIPPING_PROVIDER);
        assertThat(testLazadaOrder.getShipmentTypeName()).isEqualTo(UPDATED_SHIPMENT_TYPE_NAME);
        assertThat(testLazadaOrder.getShippingProviderType()).isEqualTo(UPDATED_SHIPPING_PROVIDER_TYPE);
        assertThat(testLazadaOrder.getCdTrackingCode()).isEqualTo(UPDATED_CD_TRACKING_CODE);
        assertThat(testLazadaOrder.getTrackingCode()).isEqualTo(UPDATED_TRACKING_CODE);
        assertThat(testLazadaOrder.getTrackingUrl()).isEqualTo(UPDATED_TRACKING_URL);
        assertThat(testLazadaOrder.getShippingProviderFM()).isEqualTo(UPDATED_SHIPPING_PROVIDER_FM);
        assertThat(testLazadaOrder.getTrackingCodeFM()).isEqualTo(UPDATED_TRACKING_CODE_FM);
        assertThat(testLazadaOrder.getTrackingUrlFM()).isEqualTo(UPDATED_TRACKING_URL_FM);
        assertThat(testLazadaOrder.getPromisedShippingTime()).isEqualTo(UPDATED_PROMISED_SHIPPING_TIME);
        assertThat(testLazadaOrder.getPremium()).isEqualTo(UPDATED_PREMIUM);
        assertThat(testLazadaOrder.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testLazadaOrder.getBuyerFailedDeliveryReturnInitiator()).isEqualTo(UPDATED_BUYER_FAILED_DELIVERY_RETURN_INITIATOR);
        assertThat(testLazadaOrder.getBuyerFailedDeliveryReason()).isEqualTo(UPDATED_BUYER_FAILED_DELIVERY_REASON);
        assertThat(testLazadaOrder.getBuyerFailedDeliveryDetail()).isEqualTo(UPDATED_BUYER_FAILED_DELIVERY_DETAIL);
        assertThat(testLazadaOrder.getBuyerFailedDeliveryUserName()).isEqualTo(UPDATED_BUYER_FAILED_DELIVERY_USER_NAME);
        assertThat(testLazadaOrder.getBundleId()).isEqualTo(UPDATED_BUNDLE_ID);
        assertThat(testLazadaOrder.getBundleDiscount()).isEqualTo(UPDATED_BUNDLE_DISCOUNT);
        assertThat(testLazadaOrder.getRefundAmount()).isEqualTo(UPDATED_REFUND_AMOUNT);
    }

    @Test
    @Transactional
    void patchNonExistingLazadaOrder() throws Exception {
        int databaseSizeBeforeUpdate = lazadaOrderRepository.findAll().size();
        lazadaOrder.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLazadaOrderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, lazadaOrder.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(lazadaOrder))
            )
            .andExpect(status().isBadRequest());

        // Validate the LazadaOrder in the database
        List<LazadaOrder> lazadaOrderList = lazadaOrderRepository.findAll();
        assertThat(lazadaOrderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLazadaOrder() throws Exception {
        int databaseSizeBeforeUpdate = lazadaOrderRepository.findAll().size();
        lazadaOrder.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLazadaOrderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(lazadaOrder))
            )
            .andExpect(status().isBadRequest());

        // Validate the LazadaOrder in the database
        List<LazadaOrder> lazadaOrderList = lazadaOrderRepository.findAll();
        assertThat(lazadaOrderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLazadaOrder() throws Exception {
        int databaseSizeBeforeUpdate = lazadaOrderRepository.findAll().size();
        lazadaOrder.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLazadaOrderMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(lazadaOrder))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the LazadaOrder in the database
        List<LazadaOrder> lazadaOrderList = lazadaOrderRepository.findAll();
        assertThat(lazadaOrderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLazadaOrder() throws Exception {
        // Initialize the database
        lazadaOrderRepository.saveAndFlush(lazadaOrder);

        int databaseSizeBeforeDelete = lazadaOrderRepository.findAll().size();

        // Delete the lazadaOrder
        restLazadaOrderMockMvc
            .perform(delete(ENTITY_API_URL_ID, lazadaOrder.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LazadaOrder> lazadaOrderList = lazadaOrderRepository.findAll();
        assertThat(lazadaOrderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
