package com.epam.training.lawAndSocial.dao;

import com.epam.training.lawAndSocial.model.Message;

import java.util.List;

public interface MessageHistoryDao {

    int add(Message message);

    int addAll(List<Message> messages);

    List<Message> getByUserId(long userId, long otherUserId);


}
