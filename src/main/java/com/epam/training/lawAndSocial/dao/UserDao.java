package com.epam.training.lawAndSocial.dao;

import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    long add(User user) throws PersistException;

    Optional<User> getByUsername(String username) throws PersistException;

    Optional<User> getByUserId(long id) throws PersistException;

    long update(User user) throws PersistException;

    long updateAvatar(long id, String base64EncodedImage) throws PersistException;

    List<User> getUsers(int limit, int offset) throws PersistException;

    long getNumberOfUsers() throws PersistException;

}
