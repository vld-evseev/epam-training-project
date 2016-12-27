package com.epam.training.lawAndSocial.service.model;

import com.epam.training.lawAndSocial.model.Contacts;

import java.util.Optional;

public interface ContactsService {

    Optional<Contacts> getByUserId(long userId);

    long add(long userId, Contacts contacts);

    long update(long userId, Contacts contacts);

}
