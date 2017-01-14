package com.epam.training.lawAndSocial.service.model.impl;

import com.epam.training.lawAndSocial.dao.MessageHistoryDao;
import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.model.Message;
import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.service.model.MessageHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class MessageHistoryServiceImpl implements MessageHistoryService {

    private final static Logger LOGGER = LoggerFactory.getLogger(MessageHistoryServiceImpl.class);
    private static final int USERS_SHOW_LIMIT = 30;
    private final MessageHistoryDao messageHistoryDao;

    @Inject
    public MessageHistoryServiceImpl(MessageHistoryDao messageHistoryDao) {
        this.messageHistoryDao = messageHistoryDao;
    }

    @Override
    public int add(Message message) {
        try {
            return messageHistoryDao.add(message);
        } catch (PersistException e) {
            LOGGER.error("Adding message caused an exception: {}", e.getMessage());
            LOGGER.error("Message: {}", message.toString());
            return -1;
        }
    }

    @Override
    public List<Message> getByUserId(long userId, long otherUserId) {
        try {
            return Collections.unmodifiableList(messageHistoryDao.getByUserId(userId, otherUserId));
        } catch (PersistException e) {
            LOGGER.error("Getting list of messages by user id caused an exception: {}", e.getMessage());
            LOGGER.error("User id: {}", userId);
            return Collections.emptyList();
        }
    }

    @Override
    public int getNumberOfContacts(long userId) {
        return getContacts(userId, Integer.MAX_VALUE, 0).size();
    }

    @Override
    public List<User> getContacts(long userId, int offset) {
        final Set<User> contacts = getContacts(userId, USERS_SHOW_LIMIT, offset);
        final List<User> users = new ArrayList<>();
        users.addAll(contacts);
        return Collections.unmodifiableList(users);
    }

    private Set<User> getContacts(long userId, int limit, int offset) {
        try {
            return messageHistoryDao.getContacts(userId, limit, offset);
        } catch (PersistException e) {
            LOGGER.error("Getting contacts by user id caused an exception: {}", e.getMessage());
            LOGGER.error("UserId: {}, limit: {}, offset: {}", userId, limit, offset);
            return Collections.emptySet();
        }
    }
}
