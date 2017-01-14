package com.epam.training.lawAndSocial.dao;

import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.model.Contacts;

import java.util.Optional;

public interface ContactsDao {

    long add(long userId, Contacts contacts) throws PersistException;

    Optional<Contacts> get(long userId) throws PersistException;

    long update(long userId, Contacts contacts) throws PersistException;

    long delete(long userId) throws PersistException;

}
