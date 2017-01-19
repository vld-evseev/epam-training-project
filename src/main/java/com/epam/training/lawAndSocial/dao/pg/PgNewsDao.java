package com.epam.training.lawAndSocial.dao.pg;

import com.epam.training.lawAndSocial.dao.NewsDao;
import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.model.News;
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
import java.util.List;

public class PgNewsDao implements NewsDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(PgNewsDao.class);
    private final DataSource dataSource;

    @Inject
    public PgNewsDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int add(long userId, News news) throws PersistException {
        int result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "INSERT INTO lawAndSocialDb.news(id, user_id, date_value, heading, content)" +
                            " VALUES (nextval('lawAndSocialDb.news_seq')," +
                            " ?, ?, ?, ?);"
            );
            query.setLong(1, news.getUser().getId());
            query.setLong(2, news.getDate());
            query.setString(3, news.getHeading());
            query.setString(4, news.getContent());
            result = query.executeUpdate();

            if (result != 1) {
                throw new PersistException("None or more than one row affected on add: " + result);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return result;
    }

    @Override
    public List<News> getByUserId(long userId, int limit, int offset) throws PersistException {
        List<News> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT us.id AS us_id," +
                            " us.firstName AS us_firstName," +
                            " us.lastName AS us_lastName," +
                            " us.avatar AS us_avatar," +
                            " nw.id AS nw_id," +
                            " nw.user_id AS nw_user_id," +
                            " nw.date_value AS nw_date_value," +
                            " nw.heading AS nw_heading," +
                            " nw.content AS nw_content" +
                            " FROM lawAndSocialDb.news nw" +
                            " JOIN lawAndSocialDb.user us" +
                            " ON us.id = nw.user_id" +
                            " WHERE nw.user_id = ?" +
                            " ORDER BY date_value DESC" +
                            " LIMIT ?" +
                            " OFFSET ?;");
            query.setLong(1, userId);
            query.setInt(2, limit);
            query.setInt(3, offset);
            final ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                result.add(
                        News.builder()
                                .id(resultSet.getLong("nw_id"))
                                .user(User.builder()
                                        .id(resultSet.getLong("us_id"))
                                        .firstName(resultSet.getString("us_firstName"))
                                        .lastName(resultSet.getString("us_lastName"))
                                        .avatar(resultSet.getString("us_avatar"))
                                        .build())
                                .date(resultSet.getLong("nw_date_value"))
                                .heading(resultSet.getString("nw_heading"))
                                .content(resultSet.getString("nw_content"))
                                .build()
                );
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return Collections.unmodifiableList(result);
    }

    @Override
    public List<News> getAll(int limit, int offset) throws PersistException {
        List<News> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT us.id AS us_id," +
                            " us.firstName AS us_firstName," +
                            " us.lastName AS us_lastName," +
                            " us.avatar AS us_avatar," +
                            " nw.id AS nw_id," +
                            " nw.user_id AS nw_user_id," +
                            " nw.date_value AS nw_date_value," +
                            " nw.heading AS nw_heading," +
                            " nw.content AS nw_content" +
                            " FROM lawAndSocialDb.news nw" +
                            " JOIN lawAndSocialDb.user us" +
                            " ON us.id = nw.user_id" +
                            " ORDER BY date_value DESC" +
                            " LIMIT ?" +
                            " OFFSET ?;");
            query.setLong(1, limit);
            query.setLong(2, offset);
            final ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                result.add(
                        News.builder()
                                .id(resultSet.getLong("nw_id"))
                                .user(User.builder()
                                        .id(resultSet.getLong("us_id"))
                                        .firstName(resultSet.getString("us_firstName"))
                                        .lastName(resultSet.getString("us_lastName"))
                                        .avatar(resultSet.getString("us_avatar"))
                                        .build())
                                .date(resultSet.getLong("nw_date_value"))
                                .heading(resultSet.getString("nw_heading"))
                                .content(resultSet.getString("nw_content"))
                                .build()
                );
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return Collections.unmodifiableList(result);
    }
}
