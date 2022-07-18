package com.flogramming.repository;

import com.flogramming.domain.Client;
import com.flogramming.domain.User;
import com.flogramming.domain.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data SQL repository for the UserInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientUserInfoRepository extends UserInfoRepository {
    Optional<UserInfo> findByUser(User user);

    Page<UserInfo> findAllByClientCode(Client client, Pageable pageable);
}
