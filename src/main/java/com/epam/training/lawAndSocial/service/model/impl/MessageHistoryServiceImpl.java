package com.epam.training.lawAndSocial.service.model.impl;

import com.epam.training.lawAndSocial.dao.MessageHistoryDao;
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
        return messageHistoryDao.add(message);
    }

    @Override
    public List<Message> getByUserId(long userId, long otherUserId) {
        return Collections.unmodifiableList(messageHistoryDao.getByUserId(userId, otherUserId));
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

        /*LOGGER.debug("Number of users: {}, userID:{}, offset: {}", contacts.size(), userId, offset);
        LOGGER.debug("requestedUsers");
        for (User requestedUser : contacts) {
            LOGGER.debug(requestedUser.toString());
        }*/
        return Collections.unmodifiableList(users);
    }

    private Set<User> getContacts(long userId, int limit, int offset) {
        return messageHistoryDao.getContacts(userId, limit, offset);
    }
}
