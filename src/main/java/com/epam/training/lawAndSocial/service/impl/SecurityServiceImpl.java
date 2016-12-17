package com.epam.training.lawAndSocial.service.impl;

import com.epam.training.lawAndSocial.service.SecurityService;
import com.lambdaworks.crypto.SCryptUtil;

public class SecurityServiceImpl implements SecurityService {

    private static final int N = 16384; //CPU cost parameter
    private static final int r = 8; //Memory cost parameter
    private static final int p = 1; //Parallelization parameter

    @Override
    public String encrypt(String password) {
        return SCryptUtil.scrypt(password, N, r, p);
    }

    @Override
    public boolean validate(String password, String hash) {
        return SCryptUtil.check(password, hash);
    }
}
