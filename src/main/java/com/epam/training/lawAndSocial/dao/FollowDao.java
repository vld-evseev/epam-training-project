package com.epam.training.lawAndSocial.dao;

import com.epam.training.lawAndSocial.model.User;

import java.util.List;

public interface FollowDao {

    int follow(long userId, long followedUserId);

    int unfollow(long userId, long unfollowedUserId);

    List<User> getFollowing(long userId, int limit, int offset);

    List<User> getFollowers(long userId, int limit, int offset);

    long getFollowersNumber(long userId);

    long getFollowingNumber(long userId);

}
