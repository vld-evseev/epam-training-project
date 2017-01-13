package com.epam.training.lawAndSocial.service;

import com.epam.training.lawAndSocial.model.Message;

import java.util.List;

public interface MessageHistoryService {

    int add(Message message);

    List<Message> getByUserId(long userId, long otherUserUd);

}
