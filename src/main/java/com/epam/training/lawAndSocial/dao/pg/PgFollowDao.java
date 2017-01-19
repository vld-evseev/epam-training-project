package com.epam.training.lawAndSocial.dao.pg;

import com.epam.training.lawAndSocial.dao.FollowDao;
import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PgFollowDao implements FollowDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(PgFollowDao.class);
    private final DataSource dataSource;

    @Inject
    public PgFollowDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int follow(long userId, long followedUserId) throws PersistException {
        int result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "INSERT INTO lawAndSocialDb.follow(user_id, followed_user_id)" +
                            " VALUES (?, ?);"
            );
            query.setLong(1, userId);
            query.setLong(2, followedUserId);
            result = query.executeUpdate();

            if (result != 1) {
                throw new PersistException("None or more than one row affected on follow: " + result);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return result;
    }

    @Override
    public int unfollow(long userId, long unfollowedUserId) throws PersistException {
        int result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "DELETE FROM lawAndSocialDb.follow" +
                            " WHERE user_id = ? AND followed_user_id = ?;"
            );
            query.setLong(1, userId);
            query.setLong(2, unfollowedUserId);
            result = query.executeUpdate();

            if (result != 1) {
                throw new PersistException("None or more than one row affected on unfollow: " + result);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return result;
    }

    @Override
    public List<User> getFollowing(long userId, int limit, int offset) throws PersistException {
        List<User> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT us.id AS us_id," +
                            " us.firstName AS us_firstName," +
                            " us.lastName AS us_lastName," +
                            " us.avatar AS us_avatar," +
                            " fl.user_id AS fl_user_id," +
                            " fl.followed_user_id AS fl_followed_user_id" +
                            " FROM lawAndSocialDb.follow fl" +
                            " JOIN lawAndSocialDb.user us" +
                            " ON us.id = fl.followed_user_id" +
                            " WHERE fl.user_id = ?" +
                            " ORDER BY fl.followed_user_id ASC" +
                            " LIMIT ?" +
                            " OFFSET ?;"
            );
            query.setLong(1, userId);
            query.setInt(2, limit);
            query.setInt(3, offset);
            final ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                result.add(
                        User.builder()
                                .id(resultSet.getLong("fl_followed_user_id"))
                                .firstName(resultSet.getString("us_firstName"))
                                .lastName(resultSet.getString("us_lastName"))
                                .avatar(resultSet.getString("us_avatar"))
                                .build()
                );
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return Collections.unmodifiableList(result);
    }

    @Override
    public List<User> getFollowers(long userId, int limit, int offset) throws PersistException {
        List<User> result = new LinkedList<>();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT us.id AS us_id," +
                            " us.firstName AS us_firstName," +
                            " us.lastName AS us_lastName," +
                            " us.avatar AS us_avatar," +
                            " fl.user_id AS fl_user_id," +
                            " fl.followed_user_id AS fl_followed_user_id" +
                            " FROM lawAndSocialDb.follow fl" +
                            " JOIN lawAndSocialDb.user us" +
                            " ON us.id = fl.user_id" +
                            " WHERE fl.followed_user_id = ?" +
                            " ORDER BY fl_followed_user_id ASC" +
                            " LIMIT ?" +
                            " OFFSET ?;"
            );
            query.setLong(1, userId);
            query.setInt(2, limit);
            query.setInt(3, offset);
            final ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                result.add(
                        User.builder()
                                .id(resultSet.getLong("us_id"))
                                .firstName(resultSet.getString("us_firstName"))
                                .lastName(resultSet.getString("us_lastName"))
                                .avatar(resultSet.getString("us_avatar"))
                                .build()
                );
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return Collections.unmodifiableList(result);
    }

    @Override
    public long getFollowersNumber(long userId) throws PersistException {
        long result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT count(user_id) AS total FROM lawAndSocialDb.follow" +
                            " WHERE followed_user_id = ?;"
            );
            query.setLong(1, userId);
            final ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getLong("total");
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return result;
    }

    @Override
    public long getFollowingNumber(long userId) throws PersistException {
        long result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT count(followed_user_id) AS total FROM lawAndSocialDb.follow" +
                            " WHERE user_id = ?;"
            );
            query.setLong(1, userId);
            final ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getLong("total");
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return result;
    }
}
