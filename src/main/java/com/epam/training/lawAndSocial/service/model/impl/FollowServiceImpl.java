package com.epam.training.lawAndSocial.service.model.impl;

import com.epam.training.lawAndSocial.dao.FollowDao;
import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.service.model.FollowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FollowServiceImpl implements FollowService {

    private final static Logger LOGGER = LoggerFactory.getLogger(FollowServiceImpl.class);
    private static final int USERS_SHOW_LIMIT = 30;
    private final FollowDao followDao;

    @Inject
    public FollowServiceImpl(FollowDao followDao) {
        this.followDao = followDao;
    }

    @Override
    public int follow(long userId, long followedUserId) {
        try {
            return followDao.follow(userId, followedUserId);
        } catch (PersistException e) {
            LOGGER.error("Follow user caused an exception: {}", e.getMessage());
            LOGGER.error("UserId: {}", userId);
            LOGGER.error("Followed userId: {}", followedUserId);
            return -1;
        }
    }

    @Override
    public int unfollow(long userId, long unfollowedUserId) {
        try {
            return followDao.unfollow(userId, unfollowedUserId);
        } catch (PersistException e) {
            LOGGER.error("Unfollow user caused an exception: {}", e.getMessage());
            LOGGER.error("UserId: {}", userId);
            LOGGER.error("Unfollowed userId: {}", unfollowedUserId);
            return -1;
        }
    }

    @Override
    public List<User> getFollowing(long userId, int offset) {
        return getFollowing(userId, USERS_SHOW_LIMIT, offset);
    }

    @Override
    public List<User> getFollowers(long userId, int offset) {
        try {
            return followDao.getFollowers(userId, USERS_SHOW_LIMIT, offset);
        } catch (PersistException e) {
            LOGGER.error("Getting followers by user id caused an exception: {}", e.getMessage());
            LOGGER.error("UserId: {}", userId);
            return Collections.emptyList();
        }
    }

    @Override
    public long getFollowersNumber(long userId) {
        try {
            return followDao.getFollowersNumber(userId);
        } catch (PersistException e) {
            LOGGER.error("Getting number of users caused an exception: {}", e.getMessage());
            LOGGER.error("User id: {}", userId);
            return -1;
        }
    }

    @Override
    public long getFollowingNumber(long userId) {
        try {
            return followDao.getFollowingNumber(userId);
        } catch (PersistException e) {
            LOGGER.error("Getting number of users caused an exception: {}", e.getMessage());
            LOGGER.error("User id: {}", userId);
            return -1;
        }
    }

    @Override
    public Set<Long> getFollowingIds(long userId) {
        final List<User> following = getFollowing(userId, Integer.MAX_VALUE, 0);
        final HashSet<Long> ids = new HashSet<>();
        for (User user : following) {
            ids.add(user.getId());
        }
        return ids;
    }

    private List<User> getFollowing(long userId, int limit, int offset) {
        try {
            return followDao.getFollowing(userId, limit, offset);
        } catch (PersistException e) {
            LOGGER.error("Getting following users by user id caused an exception: {}", e.getMessage());
            LOGGER.error("UserId: {}", userId);
            return Collections.emptyList();
        }
    }
}
