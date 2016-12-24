package com.epam.training.lawAndSocial.dao;

import com.epam.training.lawAndSocial.model.Contacts;

import java.util.Optional;

public interface ContactsDao {

    long add(long userId, Contacts contacts);

    Optional<Contacts> get(long userId);

    long update(long userId, Contacts contacts);

    long delete(long userId);

}
