package com.epam.training.lawAndSocial.service;

import com.epam.training.lawAndSocial.model.Credentials;
import com.epam.training.lawAndSocial.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getByCredentials(Credentials credentials);

    Optional<User> getByUsername(String username);

    long add(User user);

    long update(User user);

}
