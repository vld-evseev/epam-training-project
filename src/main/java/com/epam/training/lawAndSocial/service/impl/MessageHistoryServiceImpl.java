package com.epam.training.lawAndSocial.service.impl;

import com.epam.training.lawAndSocial.dao.MessageHistoryDao;
import com.epam.training.lawAndSocial.model.Message;
import com.epam.training.lawAndSocial.service.MessageHistoryService;

import javax.inject.Inject;
import java.util.List;

public class MessageHistoryServiceImpl implements MessageHistoryService {

    private final MessageHistoryDao messageHistoryDao;

    @Inject
    public MessageHistoryServiceImpl(MessageHistoryDao messageHistoryDao) {
        this.messageHistoryDao = messageHistoryDao;
    }

    @Override
    public int add(Message message) {
        return messageHistoryDao.add(message);
    }

    @Override
    public List<Message> getByUserId(long userId, long otherUserId) {
        return messageHistoryDao.getByUserId(userId, otherUserId);
    }
}
