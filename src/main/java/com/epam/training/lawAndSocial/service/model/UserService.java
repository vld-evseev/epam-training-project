package com.epam.training.lawAndSocial.service.model;

import com.epam.training.lawAndSocial.model.Credentials;
import com.epam.training.lawAndSocial.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getByCredentials(Credentials credentials);

    Optional<User> getByUsername(String username);

    Optional<User> getUserById(long id);

    long add(User user);

    long update(User user);

    long updateAvatar(long id, String base64EncodedImage);

    List<User> getUsers(int offset);

    long getNumberOfUsers();

}
