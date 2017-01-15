package com.epam.training.lawAndSocial.service.impl;

import com.epam.training.lawAndSocial.dao.UserDao;
import com.epam.training.lawAndSocial.model.Credentials;
import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.service.SecurityService;
import com.epam.training.lawAndSocial.service.model.UserService;
import com.epam.training.lawAndSocial.service.model.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Test
    public void getByCredentialsTest() throws Exception {
        final SecurityService securityService = mock(SecurityService.class);
        when(securityService.validate("password", "pwdHash")).thenReturn(true);

        final UserDao userDao = mock(UserDao.class);
        final User user = User.builder()
                .userName("testUser")
                .firstName("John")
                .lastName("Doe")
                .date("01.01.1990")
                .passwordHash("pwdHash")
                .build();
        when(userDao.getByUsername("testUser")).thenReturn(
                Optional.of(user)
        );

        final Credentials testCredentials = Credentials.builder()
                .username("testUser")
                .password("password")
                .build();

        final UserService userService = new UserServiceImpl(securityService, userDao);
        final Optional<User> userOptional = userService.getByCredentials(testCredentials);

        Assert.assertTrue(userOptional.isPresent());
    }
}
