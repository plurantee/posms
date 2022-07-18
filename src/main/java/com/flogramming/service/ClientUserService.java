package com.flogramming.service;

import com.flogramming.domain.Authority;
import com.flogramming.domain.User;
import com.flogramming.domain.UserInfo;
import com.flogramming.repository.AuthorityRepository;
import com.flogramming.repository.ClientClientRepository;
import com.flogramming.repository.ClientRepository;
import com.flogramming.repository.ClientUserInfoRepository;
import com.flogramming.repository.UserRepository;
import com.flogramming.security.AuthoritiesConstants;
import com.flogramming.web.rest.vm.ManagedUserVM;
import org.springframework.cache.CacheManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.security.RandomUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class ClientUserService extends UserService {

    private final ClientUserInfoRepository clientUserInfoRepository;
    private final ClientClientRepository clientRepository;

    public ClientUserService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        AuthorityRepository authorityRepository,
        CacheManager cacheManager,
        ClientRepository clientRepository,
        ClientUserInfoRepository userInfoRepository,
        ClientUserInfoRepository clientUserInfoRepository,
        ClientClientRepository clientRepository1
    ) {
        super(userRepository, passwordEncoder, authorityRepository, cacheManager, userInfoRepository);
        this.clientUserInfoRepository = clientUserInfoRepository;
        this.clientRepository = clientRepository1;
    }

    public UserInfo getCurrentUser() {
        User user = getUserWithAuthorities().get();
        return clientUserInfoRepository.findByUser(user).get();
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> getAuthorities() {
        boolean isAdmin = getCurrentUser().getUser().hasRole(AuthoritiesConstants.ADMIN);
        List<String> auths = new ArrayList<>();
        auths.add(AuthoritiesConstants.USER);
        auths.add(AuthoritiesConstants.CLIENT_USER);
        if (isAdmin) {
            auths.add(AuthoritiesConstants.ADMIN);
        }
        return auths;
    }

    public User registerUser(ManagedUserVM userDTO, String password) {
        var oClient = clientRepository.findByClientCode(userDTO.getClientCode());
        if (oClient.isEmpty()) {
            throw new ClientDoesNotExistException();
        }
        var client = oClient.get();
        userDTO.setLogin(client.getClientCode() + "-" + userDTO.getLogin());
        userRepository
            .findOneByLogin(userDTO.getLogin().toLowerCase())
            .ifPresent(existingUser -> {
                boolean removed = removeNonActivatedUser(existingUser);
                if (!removed) {
                    throw new UsernameAlreadyUsedException();
                }
            });
        userRepository
            .findOneByEmailIgnoreCase(userDTO.getEmail())
            .ifPresent(existingUser -> {
                boolean removed = removeNonActivatedUser(existingUser);
                if (!removed) {
                    throw new EmailAlreadyUsedException();
                }
            });
        User newUser = new User();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setLogin(userDTO.getLogin().toLowerCase());
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        if (userDTO.getEmail() != null) {
            newUser.setEmail(userDTO.getEmail().toLowerCase());
        }
        newUser.setImageUrl(userDTO.getImageUrl());
        newUser.setLangKey(userDTO.getLangKey());
        // new user is not active
        newUser.setActivated(true);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        Set<Authority> authorities = new HashSet<>();
        authorityRepository.findById(AuthoritiesConstants.USER).ifPresent(authorities::add);
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        this.clearUserCaches(newUser);
        log.debug("Created Information for User: {}", newUser);

        UserInfo userInfo = new UserInfo();
        userInfo.setUser(newUser);
        userInfo.setClientCode(client);
        userInfoRepository.save(userInfo);

        return newUser;
    }
}
