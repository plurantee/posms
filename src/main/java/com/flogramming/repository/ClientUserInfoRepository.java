package com.flogramming.repository;

import com.flogramming.domain.Client;
import com.flogramming.domain.User;
import com.flogramming.domain.UserInfo;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the UserInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientUserInfoRepository extends UserInfoRepository {
    Optional<UserInfo> findByUser(User user);
    Page<UserInfo> findAllByClientCode(Client client, Pageable pageable);
}