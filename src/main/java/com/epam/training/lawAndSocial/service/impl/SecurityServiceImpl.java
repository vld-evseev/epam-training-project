package com.epam.training.lawAndSocial.service.impl;

import com.epam.training.lawAndSocial.service.SecurityService;

public class SecurityServiceImpl implements SecurityService {
    @Override
    public String encrypt(String password) {
        return password;
    }

    @Override
    public boolean validate(String password, String hash) {
        return true;
    }
}
