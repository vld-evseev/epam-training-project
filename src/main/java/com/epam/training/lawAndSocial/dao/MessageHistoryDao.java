package com.epam.training.lawAndSocial.dao;

import com.epam.training.lawAndSocial.model.Message;
import com.epam.training.lawAndSocial.model.User;

import java.util.List;
import java.util.Set;

public interface MessageHistoryDao {

    int add(Message message);

    int addAll(List<Message> messages);

    List<Message> getByUserId(long userId, long otherUserId);

    Set<User> getContacts(long userId, int limit, int offset);

}
