package com.epam.training.lawAndSocial.service.model.impl;

import com.epam.training.lawAndSocial.dao.ContactsDao;
import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.model.Contacts;
import com.epam.training.lawAndSocial.service.model.ContactsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Optional;

public class ContactsServiceImpl implements ContactsService {

    private final ContactsDao contactsDao;
    private final static Logger LOGGER = LoggerFactory.getLogger(ContactsServiceImpl.class);

    @Inject
    public ContactsServiceImpl(ContactsDao contactsDao) {
        this.contactsDao = contactsDao;
    }

    @Override
    public Optional<Contacts> getByUserId(long userId) {
        try {
            return contactsDao.get(userId);
        } catch (PersistException e) {
            LOGGER.error("Getting contacts by userId caused an exception: {}", e.getMessage());
            LOGGER.error("userId: {}", userId);
            return Optional.empty();
        }
    }

    @Override
    public long add(long userId, Contacts contacts) {
        try {
            return contactsDao.add(userId, contacts);
        } catch (PersistException e) {
            LOGGER.error("Adding contacts caused an exception: {}", e.getMessage());
            LOGGER.error("User id: {}", userId);
            LOGGER.error("Contacts: {}", contacts.toString());
            return -1;
        }
    }

    @Override
    public long update(long userId, Contacts contacts) {
        try {
            return contactsDao.update(userId, contacts);
        } catch (PersistException e) {
            LOGGER.error("Updating contacts by userId caused an exception: {}", e.getMessage());
            LOGGER.error("Contacts: {}", contacts.toString());
            LOGGER.error("UserId: {}", userId);
            return -1;
        }
    }

}
