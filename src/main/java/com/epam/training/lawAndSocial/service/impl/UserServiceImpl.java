package com.epam.training.lawAndSocial.service.impl;

import com.epam.training.lawAndSocial.dao.UserDao;
import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.model.Credentials;
import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.service.SecurityService;
import com.epam.training.lawAndSocial.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final SecurityService securityService;
    private final UserDao userDao;
    private static final int USERS_SHOW_LIMIT = 30;

    @Inject
    public UserServiceImpl(SecurityService securityService, UserDao userDao) {
        this.securityService = securityService;
        this.userDao = userDao;
    }

    @Override
    public Optional<User> getByCredentials(Credentials credentials) {
        final Optional<User> userOptional = getByUsername(credentials.getUsername());
        if (!userOptional.isPresent()) {
            return Optional.empty();
        }

        final User user = userOptional.get();

        final boolean validated = securityService.validate(credentials.getPassword(), user.getPasswordHash());
        if (!validated) {
            LOGGER.debug("invalid password entered by user {}", credentials.getUsername());
            return Optional.empty();
        }

        LOGGER.debug("user {} was found", credentials.getUsername());

        return Optional.of(user);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        try {
            return userDao.getByUsername(username);
        } catch (PersistException e) {
            LOGGER.error("Getting user by userName caused an exception: {}", e.getMessage());
            LOGGER.error("Username: {}", username);
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getUserById(long id) {
        try {
            return userDao.getByUserId(id);
        } catch (PersistException e) {
            LOGGER.error("Getting user by userId caused an exception: {}", e.getMessage());
            LOGGER.error("userId: {}", id);
            return Optional.empty();
        }
    }

    @Override
    public long add(User user) {
        try {
            return userDao.add(user);
        } catch (PersistException e) {
            LOGGER.error("Adding user caused an exception: {}", e.getMessage());
            LOGGER.error("User: {}", user.toString());
            return -1;
        }
    }

    @Override
    public long update(User user) {
        try {
            return userDao.update(user);
        } catch (PersistException e) {
            LOGGER.error("Updating user caused an exception: {}", e.getMessage());
            LOGGER.error("User: {}", user.toString());
            return -1;
        }
    }

    @Override
    public long updateAvatar(long id, String base64EncodedImage) {
        try {
            return userDao.updateAvatar(id, base64EncodedImage);
        } catch (PersistException e) {
            LOGGER.error("Updating avatar caused an exception: {}", e.getMessage());
            LOGGER.error("User id: {}", id);
            return -1;
        }
    }

    @Override
    public List<User> getUsers(int offset) {
        try {
            return userDao.getUsers(USERS_SHOW_LIMIT, offset);
        } catch (PersistException e) {
            LOGGER.error("Getting list of users caused an exception: {}", e.getMessage());
            LOGGER.error("current offset: {}", offset);
            return Collections.emptyList();
        }
    }

    @Override
    public long getNumberOfUsers() {
        try {
            return userDao.getNumberOfUsers();
        } catch (PersistException e) {
            LOGGER.error("Getting number of users caused an exception: {}", e.getMessage());
            return -1;
        }
    }


}
