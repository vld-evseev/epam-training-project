package com.epam.training.lawAndSocial.service.model;

import com.epam.training.lawAndSocial.model.Message;
import com.epam.training.lawAndSocial.model.User;

import java.util.List;

public interface MessageHistoryService {

    int add(Message message);

    List<Message> getByUserId(long userId, long otherUserUd);

    int getNumberOfContacts(long userId);

    List<User> getContacts(long userId, int offset);

}
