package com.epam.training.lawAndSocial.dao;

import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.model.User;

import java.util.List;

public interface FollowDao {

    int follow(long userId, long followedUserId) throws PersistException;

    int unfollow(long userId, long unfollowedUserId) throws PersistException;

    List<User> getFollowing(long userId, int limit, int offset) throws PersistException;

    List<User> getFollowers(long userId, int limit, int offset) throws PersistException;

    long getFollowersNumber(long userId) throws PersistException;

    long getFollowingNumber(long userId) throws PersistException;

}
