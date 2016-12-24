package com.epam.training.lawAndSocial.service.model.impl;

import com.epam.training.lawAndSocial.dao.ContactsDao;
import com.epam.training.lawAndSocial.model.Contacts;
import com.epam.training.lawAndSocial.service.model.ContactsService;

import javax.inject.Inject;
import java.util.Optional;

public class ContactsServiceImpl implements ContactsService {

    private final ContactsDao contactsDao;

    @Inject
    public ContactsServiceImpl(ContactsDao contactsDao) {
        this.contactsDao = contactsDao;
    }

    @Override
    public Optional<Contacts> getByUserId(long userId) {
        return contactsDao.get(userId);
    }

    @Override
    public long add(long userId, Contacts contacts) {
        return contactsDao.add(userId, contacts);
    }

}
