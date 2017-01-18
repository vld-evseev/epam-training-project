package com.epam.training.lawAndSocial.dao;

import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.model.Message;
import com.epam.training.lawAndSocial.model.User;

import java.util.List;
import java.util.Set;

public interface MessageHistoryDao {

    int add(Message message) throws PersistException;

    int addAll(List<Message> messages) throws PersistException;

    List<Message> getByUserId(long userId, long otherUserId) throws PersistException;

    Set<User> getContacts(long userId, int limit, int offset) throws PersistException;

}
