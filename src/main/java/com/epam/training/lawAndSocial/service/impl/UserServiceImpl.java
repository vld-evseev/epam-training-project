package com.epam.training.lawAndSocial.service.impl;

import com.epam.training.lawAndSocial.dao.UserDao;
import com.epam.training.lawAndSocial.model.Credentials;
import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.service.SecurityService;
import com.epam.training.lawAndSocial.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
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
        final Optional<User> userOptional = userDao.getByUsername(credentials.getUsername());
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
        return userDao.getByUsername(username);
    }

    @Override
    public Optional<User> getUserById(long id) {
        return userDao.getByUserId(id);
    }

    @Override
    public long add(User user) {
        return userDao.add(user);
    }

    @Override
    public long update(User user) {
        return userDao.update(user);
    }

    @Override
    public long updateAvatar(long id, String base64EncodedImage) {
        return userDao.updateAvatar(id, base64EncodedImage);
    }

    @Override
    public List<User> getUsers(int offset) {
        return userDao.getUsers(USERS_SHOW_LIMIT, offset);
    }

    @Override
    public long getNumberOfUsers() {
        return userDao.getNumberOfUsers();
    }


}
