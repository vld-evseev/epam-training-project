package com.epam.training.lawAndSocial.service.model.impl;

import com.epam.training.lawAndSocial.dao.FollowDao;
import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.service.model.FollowService;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FollowServiceImpl implements FollowService {

    private static final int USERS_SHOW_LIMIT = 30;
    private final FollowDao followDao;

    @Inject
    public FollowServiceImpl(FollowDao followDao) {
        this.followDao = followDao;
    }

    @Override
    public int follow(long userId, long followedUserId) {
        return followDao.follow(userId, followedUserId);
    }

    @Override
    public int unfollow(long userId, long unfollowedUserId) {
        return followDao.unfollow(userId, unfollowedUserId);
    }

    @Override
    public List<User> getFollowing(long userId, int offset) {
        return followDao.getFollowing(userId, USERS_SHOW_LIMIT, offset);
    }

    @Override
    public List<User> getFollowers(long userId, int offset) {
        return followDao.getFollowers(userId, USERS_SHOW_LIMIT, offset);
    }

    @Override
    public long getFollowersNumber(long userId) {
        return followDao.getFollowersNumber(userId);
    }

    @Override
    public long getFollowingNumber(long userId) {
        return followDao.getFollowingNumber(userId);
    }

    @Override
    public Set<Long> getFollowingIds(long userId) {
        final List<User> following = followDao.getFollowing(userId, Integer.MAX_VALUE, 0);
        final HashSet<Long> ids = new HashSet<>();
        for (User user : following) {
            ids.add(user.getId());
        }

        return ids;
    }
}
