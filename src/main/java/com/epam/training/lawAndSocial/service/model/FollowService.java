package com.epam.training.lawAndSocial.service.model;

import com.epam.training.lawAndSocial.model.User;

import java.util.List;
import java.util.Set;

public interface FollowService {

    int follow(long userId, long followedUserId);

    int unfollow(long userId, long unfollowedUserId);

    List<User> getFollowing(long userId, int offset);

    List<User> getFollowers(long userId, int offset);

    long getFollowersNumber(long userId);

    long getFollowingNumber(long userId);

    Set<Long> getFollowingIds(long userId);

}
