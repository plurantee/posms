package com.flogramming.web.rest;

import com.flogramming.domain.LazadaOrder;
import com.flogramming.domain.Shop;
import com.flogramming.repository.LazadaOrderRepository;
import com.flogramming.repository.ShopRepository;
import com.flogramming.service.ExcelFileService;
import com.flogramming.web.rest.errors.BadRequestAlertException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.flogramming.domain.LazadaOrder}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class LazadaOrderResource {

    private final Logger log = LoggerFactory.getLogger(LazadaOrderResource.class);

    private static final String ENTITY_NAME = "lazadaOrder";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LazadaOrderRepository lazadaOrderRepository;
    private final ShopRepository shopRepository;
    private final ExcelFileService excelFileService;

    public LazadaOrderResource(
        LazadaOrderRepository lazadaOrderRepository,
        ShopRepository shopRepository,
        ExcelFileService excelFileService
    ) {
        this.lazadaOrderRepository = lazadaOrderRepository;
        this.shopRepository = shopRepository;
        this.excelFileService = excelFileService;
    }

    /**
     * {@code POST  /lazada-orders} : Create a new lazadaOrder.
     *
     * @param lazadaOrder the lazadaOrder to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new lazadaOrder, or with status {@code 400 (Bad Request)} if the lazadaOrder has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/lazada-orders")
    public ResponseEntity<LazadaOrder> createLazadaOrder(@RequestBody LazadaOrder lazadaOrder) throws URISyntaxException {
        log.debug("REST request to save LazadaOrder : {}", lazadaOrder);
        if (lazadaOrder.getId() != null) {
            throw new BadRequestAlertException("A new lazadaOrder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LazadaOrder result = lazadaOrderRepository.save(lazadaOrder);
        return ResponseEntity
            .created(new URI("/api/lazada-orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PostMapping(path = "/lazada-orders/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<List<LazadaOrder>> uploadLazadaOrders(
        @RequestParam("file") MultipartFile file,
        @RequestParam("shopId") Long shopId
    ) throws IOException {
        Optional<Shop> oShop = shopRepository.findById(shopId);
        List<LazadaOrder> lazadaOrders = excelFileService.processLazadaExcelFile(file, oShop.get());
        return ResponseEntity.ok(lazadaOrders);
    }

    /**
     * {@code PUT  /lazada-orders/:id} : Updates an existing lazadaOrder.
     *
     * @param id the id of the lazadaOrder to save.
     * @param lazadaOrder the lazadaOrder to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lazadaOrder,
     * or with status {@code 400 (Bad Request)} if the lazadaOrder is not valid,
     * or with status {@code 500 (Internal Server Error)} if the lazadaOrder couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/lazada-orders/{id}")
    public ResponseEntity<LazadaOrder> updateLazadaOrder(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LazadaOrder lazadaOrder
    ) throws URISyntaxException {
        log.debug("REST request to update LazadaOrder : {}, {}", id, lazadaOrder);
        if (lazadaOrder.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, lazadaOrder.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!lazadaOrderRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        LazadaOrder result = lazadaOrderRepository.save(lazadaOrder);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, lazadaOrder.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /lazada-orders/:id} : Partial updates given fields of an existing lazadaOrder, field will ignore if it is null
     *
     * @param id the id of the lazadaOrder to save.
     * @param lazadaOrder the lazadaOrder to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lazadaOrder,
     * or with status {@code 400 (Bad Request)} if the lazadaOrder is not valid,
     * or with status {@code 404 (Not Found)} if the lazadaOrder is not found,
     * or with status {@code 500 (Internal Server Error)} if the lazadaOrder couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/lazada-orders/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LazadaOrder> partialUpdateLazadaOrder(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LazadaOrder lazadaOrder
    ) throws URISyntaxException {
        log.debug("REST request to partial update LazadaOrder partially : {}, {}", id, lazadaOrder);
        if (lazadaOrder.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, lazadaOrder.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!lazadaOrderRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LazadaOrder> result = lazadaOrderRepository
            .findById(lazadaOrder.getId())
            .map(existingLazadaOrder -> {
                if (lazadaOrder.getOrderItemId() != null) {
                    existingLazadaOrder.OrderItemId(lazadaOrder.getOrderItemId());
                }
                if (lazadaOrder.getOrderType() != null) {
                    existingLazadaOrder.OrderType(lazadaOrder.getOrderType());
                }
                if (lazadaOrder.getGuarantee() != null) {
                    existingLazadaOrder.Guarantee(lazadaOrder.getGuarantee());
                }
                if (lazadaOrder.getDeliveryType() != null) {
                    existingLazadaOrder.DeliveryType(lazadaOrder.getDeliveryType());
                }
                if (lazadaOrder.getLazadaId() != null) {
                    existingLazadaOrder.LazadaId(lazadaOrder.getLazadaId());
                }
                if (lazadaOrder.getSellerSku() != null) {
                    existingLazadaOrder.SellerSku(lazadaOrder.getSellerSku());
                }
                if (lazadaOrder.getWareHouse() != null) {
                    existingLazadaOrder.WareHouse(lazadaOrder.getWareHouse());
                }
                if (lazadaOrder.getCreateTime() != null) {
                    existingLazadaOrder.CreateTime(lazadaOrder.getCreateTime());
                }
                if (lazadaOrder.getUpdateTime() != null) {
                    existingLazadaOrder.UpdateTime(lazadaOrder.getUpdateTime());
                }
                if (lazadaOrder.getRtaSla() != null) {
                    existingLazadaOrder.RtaSla(lazadaOrder.getRtaSla());
                }
                if (lazadaOrder.getTtsSla() != null) {
                    existingLazadaOrder.TtsSla(lazadaOrder.getTtsSla());
                }
                if (lazadaOrder.getOrderNumber() != null) {
                    existingLazadaOrder.OrderNumber(lazadaOrder.getOrderNumber());
                }
                if (lazadaOrder.getInvoiceRequired() != null) {
                    existingLazadaOrder.InvoiceRequired(lazadaOrder.getInvoiceRequired());
                }
                if (lazadaOrder.getInvoiceNumber() != null) {
                    existingLazadaOrder.InvoiceNumber(lazadaOrder.getInvoiceNumber());
                }
                if (lazadaOrder.getDeliveryDate() != null) {
                    existingLazadaOrder.DeliveryDate(lazadaOrder.getDeliveryDate());
                }
                if (lazadaOrder.getCustomerName() != null) {
                    existingLazadaOrder.CustomerName(lazadaOrder.getCustomerName());
                }
                if (lazadaOrder.getCustomerEmail() != null) {
                    existingLazadaOrder.CustomerEmail(lazadaOrder.getCustomerEmail());
                }
                if (lazadaOrder.getNationalRegistrationNumber() != null) {
                    existingLazadaOrder.NationalRegistrationNumber(lazadaOrder.getNationalRegistrationNumber());
                }
                if (lazadaOrder.getShippingName() != null) {
                    existingLazadaOrder.ShippingName(lazadaOrder.getShippingName());
                }
                if (lazadaOrder.getShippingAddress() != null) {
                    existingLazadaOrder.ShippingAddress(lazadaOrder.getShippingAddress());
                }
                if (lazadaOrder.getShippingAddress2() != null) {
                    existingLazadaOrder.ShippingAddress2(lazadaOrder.getShippingAddress2());
                }
                if (lazadaOrder.getShippingAddress3() != null) {
                    existingLazadaOrder.ShippingAddress3(lazadaOrder.getShippingAddress3());
                }
                if (lazadaOrder.getShippingAddress4() != null) {
                    existingLazadaOrder.ShippingAddress4(lazadaOrder.getShippingAddress4());
                }
                if (lazadaOrder.getShippingAddress5() != null) {
                    existingLazadaOrder.ShippingAddress5(lazadaOrder.getShippingAddress5());
                }
                if (lazadaOrder.getShippingPhone() != null) {
                    existingLazadaOrder.ShippingPhone(lazadaOrder.getShippingPhone());
                }
                if (lazadaOrder.getShippingPhone2() != null) {
                    existingLazadaOrder.ShippingPhone2(lazadaOrder.getShippingPhone2());
                }
                if (lazadaOrder.getShippingCity() != null) {
                    existingLazadaOrder.ShippingCity(lazadaOrder.getShippingCity());
                }
                if (lazadaOrder.getShippingPostCode() != null) {
                    existingLazadaOrder.ShippingPostCode(lazadaOrder.getShippingPostCode());
                }
                if (lazadaOrder.getShippingCountry() != null) {
                    existingLazadaOrder.ShippingCountry(lazadaOrder.getShippingCountry());
                }
                if (lazadaOrder.getShippingRegion() != null) {
                    existingLazadaOrder.ShippingRegion(lazadaOrder.getShippingRegion());
                }
                if (lazadaOrder.getBillingName() != null) {
                    existingLazadaOrder.BillingName(lazadaOrder.getBillingName());
                }
                if (lazadaOrder.getBillingAddr() != null) {
                    existingLazadaOrder.BillingAddr(lazadaOrder.getBillingAddr());
                }
                if (lazadaOrder.getBillingAddr3() != null) {
                    existingLazadaOrder.BillingAddr3(lazadaOrder.getBillingAddr3());
                }
                if (lazadaOrder.getBillingAddr4() != null) {
                    existingLazadaOrder.BillingAddr4(lazadaOrder.getBillingAddr4());
                }
                if (lazadaOrder.getBillingAddr5() != null) {
                    existingLazadaOrder.BillingAddr5(lazadaOrder.getBillingAddr5());
                }
                if (lazadaOrder.getBillingPhone() != null) {
                    existingLazadaOrder.BillingPhone(lazadaOrder.getBillingPhone());
                }
                if (lazadaOrder.getBillingPhone2() != null) {
                    existingLazadaOrder.BillingPhone2(lazadaOrder.getBillingPhone2());
                }
                if (lazadaOrder.getBillingCity() != null) {
                    existingLazadaOrder.BillingCity(lazadaOrder.getBillingCity());
                }
                if (lazadaOrder.getBillingPostCode() != null) {
                    existingLazadaOrder.BillingPostCode(lazadaOrder.getBillingPostCode());
                }
                if (lazadaOrder.getBillingCountry() != null) {
                    existingLazadaOrder.BillingCountry(lazadaOrder.getBillingCountry());
                }
                if (lazadaOrder.getTaxCode() != null) {
                    existingLazadaOrder.TaxCode(lazadaOrder.getTaxCode());
                }
                if (lazadaOrder.getBranchNumber() != null) {
                    existingLazadaOrder.BranchNumber(lazadaOrder.getBranchNumber());
                }
                if (lazadaOrder.getTaxInvoiceRequested() != null) {
                    existingLazadaOrder.TaxInvoiceRequested(lazadaOrder.getTaxInvoiceRequested());
                }
                if (lazadaOrder.getPayMethod() != null) {
                    existingLazadaOrder.PayMethod(lazadaOrder.getPayMethod());
                }
                if (lazadaOrder.getPaidPrice() != null) {
                    existingLazadaOrder.PaidPrice(lazadaOrder.getPaidPrice());
                }
                if (lazadaOrder.getUnitPrice() != null) {
                    existingLazadaOrder.UnitPrice(lazadaOrder.getUnitPrice());
                }
                if (lazadaOrder.getSellerDiscountTotal() != null) {
                    existingLazadaOrder.SellerDiscountTotal(lazadaOrder.getSellerDiscountTotal());
                }
                if (lazadaOrder.getShippingFee() != null) {
                    existingLazadaOrder.ShippingFee(lazadaOrder.getShippingFee());
                }
                if (lazadaOrder.getWalletCredit() != null) {
                    existingLazadaOrder.WalletCredit(lazadaOrder.getWalletCredit());
                }
                if (lazadaOrder.getItemName() != null) {
                    existingLazadaOrder.ItemName(lazadaOrder.getItemName());
                }
                if (lazadaOrder.getVariation() != null) {
                    existingLazadaOrder.Variation(lazadaOrder.getVariation());
                }
                if (lazadaOrder.getCdShippingProvider() != null) {
                    existingLazadaOrder.CdShippingProvider(lazadaOrder.getCdShippingProvider());
                }
                if (lazadaOrder.getShippingProvider() != null) {
                    existingLazadaOrder.ShippingProvider(lazadaOrder.getShippingProvider());
                }
                if (lazadaOrder.getShipmentTypeName() != null) {
                    existingLazadaOrder.ShipmentTypeName(lazadaOrder.getShipmentTypeName());
                }
                if (lazadaOrder.getShippingProviderType() != null) {
                    existingLazadaOrder.ShippingProviderType(lazadaOrder.getShippingProviderType());
                }
                if (lazadaOrder.getCdTrackingCode() != null) {
                    existingLazadaOrder.CdTrackingCode(lazadaOrder.getCdTrackingCode());
                }
                if (lazadaOrder.getTrackingCode() != null) {
                    existingLazadaOrder.TrackingCode(lazadaOrder.getTrackingCode());
                }
                if (lazadaOrder.getTrackingUrl() != null) {
                    existingLazadaOrder.TrackingUrl(lazadaOrder.getTrackingUrl());
                }
                if (lazadaOrder.getShippingProviderFM() != null) {
                    existingLazadaOrder.ShippingProviderFM(lazadaOrder.getShippingProviderFM());
                }
                if (lazadaOrder.getTrackingCodeFM() != null) {
                    existingLazadaOrder.TrackingCodeFM(lazadaOrder.getTrackingCodeFM());
                }
                if (lazadaOrder.getTrackingUrlFM() != null) {
                    existingLazadaOrder.TrackingUrlFM(lazadaOrder.getTrackingUrlFM());
                }
                if (lazadaOrder.getPromisedShippingTime() != null) {
                    existingLazadaOrder.PromisedShippingTime(lazadaOrder.getPromisedShippingTime());
                }
                if (lazadaOrder.getPremium() != null) {
                    existingLazadaOrder.Premium(lazadaOrder.getPremium());
                }
                if (lazadaOrder.getStatus() != null) {
                    existingLazadaOrder.Status(lazadaOrder.getStatus());
                }
                if (lazadaOrder.getBuyerFailedDeliveryReturnInitiator() != null) {
                    existingLazadaOrder.BuyerFailedDeliveryReturnInitiator(lazadaOrder.getBuyerFailedDeliveryReturnInitiator());
                }
                if (lazadaOrder.getBuyerFailedDeliveryReason() != null) {
                    existingLazadaOrder.BuyerFailedDeliveryReason(lazadaOrder.getBuyerFailedDeliveryReason());
                }
                if (lazadaOrder.getBuyerFailedDeliveryDetail() != null) {
                    existingLazadaOrder.BuyerFailedDeliveryDetail(lazadaOrder.getBuyerFailedDeliveryDetail());
                }
                if (lazadaOrder.getBuyerFailedDeliveryUserName() != null) {
                    existingLazadaOrder.BuyerFailedDeliveryUserName(lazadaOrder.getBuyerFailedDeliveryUserName());
                }
                if (lazadaOrder.getBundleId() != null) {
                    existingLazadaOrder.BundleId(lazadaOrder.getBundleId());
                }
                if (lazadaOrder.getBundleDiscount() != null) {
                    existingLazadaOrder.BundleDiscount(lazadaOrder.getBundleDiscount());
                }
                if (lazadaOrder.getRefundAmount() != null) {
                    existingLazadaOrder.RefundAmount(lazadaOrder.getRefundAmount());
                }

                return existingLazadaOrder;
            })
            .map(lazadaOrderRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, lazadaOrder.getId().toString())
        );
    }

    /**
     * {@code GET  /lazada-orders} : get all the lazadaOrders.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lazadaOrders in body.
     */
    @GetMapping("/lazada-orders")
    public List<LazadaOrder> getAllLazadaOrders() {
        log.debug("REST request to get all LazadaOrders");
        return lazadaOrderRepository.findAll();
    }

    @GetMapping("/lazada-orders/shop/{id}")
    public List<LazadaOrder> getAllLazadaOrdersByShop(@PathVariable Long id) {
        log.debug("REST request to get all LazadaOrders");
        Optional<Shop> shop = shopRepository.findById(id);
        if (shop.isPresent()) {
            return lazadaOrderRepository.findByShop(shop.get());
        }
        return null;
    }

    /**
     * {@code GET  /lazada-orders/:id} : get the "id" lazadaOrder.
     *
     * @param id the id of the lazadaOrder to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lazadaOrder, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/lazada-orders/{id}")
    public ResponseEntity<LazadaOrder> getLazadaOrder(@PathVariable Long id) {
        log.debug("REST request to get LazadaOrder : {}", id);
        Optional<LazadaOrder> lazadaOrder = lazadaOrderRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(lazadaOrder);
    }

    /**
     * {@code DELETE  /lazada-orders/:id} : delete the "id" lazadaOrder.
     *
     * @param id the id of the lazadaOrder to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/lazada-orders/{id}")
    public ResponseEntity<Void> deleteLazadaOrder(@PathVariable Long id) {
        log.debug("REST request to delete LazadaOrder : {}", id);
        lazadaOrderRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
