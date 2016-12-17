package com.epam.training.lawAndSocial.dao;

import com.epam.training.lawAndSocial.model.User;

import java.util.Optional;

public interface UserDao {

    long add(User user);

    Optional<User> getByUsername(String username);

    long update(User user);

    long delete(User user);

}
