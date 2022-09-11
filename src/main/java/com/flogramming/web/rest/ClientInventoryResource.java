package com.flogramming.web.rest;

import com.flogramming.domain.Client;
import com.flogramming.domain.Inventory;
import com.flogramming.repository.ClientInventoryRepository;
import com.flogramming.service.ClientUserService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;

/**
 * REST controller for managing {@link Inventory}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ClientInventoryResource {

    private final Logger log = LoggerFactory.getLogger(ClientInventoryResource.class);

    private static final String ENTITY_NAME = "inventory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClientInventoryRepository inventoryRepository;

    @Autowired
    private ClientUserService clientUserService;

    public ClientInventoryResource(ClientInventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * {@code GET  /inventories} : get all the inventories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inventories in body.
     */
    @GetMapping("client/inventories")
    public ResponseEntity<List<Inventory>> getAllInventories(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Inventories");
        Client client = clientUserService.getCurrentUser().getClientCode();
        Page<Inventory> page = inventoryRepository.findByClientOrderByIdDesc(client, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
