package com.epam.training.lawAndSocial.service;

public interface SecurityService {

    String encrypt(String password);

    boolean validate(String password, String hash);

}
