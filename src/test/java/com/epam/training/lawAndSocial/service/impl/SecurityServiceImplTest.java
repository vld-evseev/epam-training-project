package com.epam.training.lawAndSocial.service.impl;

import com.epam.training.lawAndSocial.service.SecurityService;
import org.junit.Assert;
import org.junit.Test;

public class SecurityServiceImplTest {

    @Test
    public void encrypt() {
        final String testPassword = "testPassword";
        final String wrongTestPassword = "wrongTestPassword";
        SecurityService securityService = new SecurityServiceImpl();

        final String hash = securityService.encrypt(testPassword);
        final boolean validated = securityService.validate(testPassword, hash);
        Assert.assertTrue(validated);

        final boolean notValidated = securityService.validate(wrongTestPassword, hash);
        Assert.assertFalse(notValidated);
    }

}
