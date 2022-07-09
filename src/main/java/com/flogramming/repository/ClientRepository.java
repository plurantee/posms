package com.flogramming.repository;

import com.flogramming.domain.Client;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Client entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByClientCode(String clientCode);
}
