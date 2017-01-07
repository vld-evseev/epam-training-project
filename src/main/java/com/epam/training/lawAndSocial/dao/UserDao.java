package com.epam.training.lawAndSocial.dao;

import com.epam.training.lawAndSocial.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    long add(User user);

    Optional<User> getByUsername(String username);

    Optional<User> getByUserId(long id);

    long update(User user);

    long updateAvatar(long id, String base64EncodedImage);

    long delete(User user);

    List<User> getUsers(int limit, int offset);

    long getNumberOfUsers();

}
