package com.epam.training.lawAndSocial.dao.pg;


import com.epam.training.lawAndSocial.dao.ContactsDao;
import com.epam.training.lawAndSocial.model.Contacts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PgContactsDao implements ContactsDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(PgContactsDao.class);
    private final DataSource dataSource;

    @Inject
    public PgContactsDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long add(long userId, Contacts contacts) {
        int result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final String[] returnColumns = {"id"};
            final PreparedStatement query = connection.prepareStatement(
                    "INSERT INTO lawAndSocialDb.contacts(id, user_id, email, phone, website)" +
                            " VALUES (nextval('lawAndSocialDb.contacts_seq')," +
                            " ?, ?, ?, ?);",
                    returnColumns
            );
            query.setLong(1, userId);
            query.setString(2, contacts.getEmail());
            query.setString(3, contacts.getPhone());
            query.setString(4, contacts.getWebsite());
            query.executeUpdate();
            final ResultSet generatedKeys = query.getGeneratedKeys();
            if (generatedKeys.next()) {
                result = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error("Adding contacts caused an exception: {}", e.getMessage());
            LOGGER.error("SQL state: {}\nError code: {}", e.getSQLState(), e.getErrorCode());
            LOGGER.error("Contacts: {}", contacts.toString());
        }

        return result;
    }

    @Override
    public Optional<Contacts> get(long userId) {
        Optional<Contacts> result = Optional.empty();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT id, user_id, email, phone, website" +
                            " FROM lawAndSocialDb.contacts WHERE user_id = ?;"
            );
            query.setLong(1, userId);
            final ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                result = Optional.of(
                        Contacts.builder()
                                .userId(userId)
                                .email(resultSet.getString("email"))
                                .phone(resultSet.getString("phone"))
                                .website(resultSet.getString("website"))
                                .build()
                );
            }
        } catch (SQLException e) {
            LOGGER.error("Getting contacts by userId caused an exception: {}", e.getMessage());
            LOGGER.error("SQL state: {}\nError code: {}", e.getSQLState(), e.getErrorCode());
            LOGGER.error("userId: {}", userId);
            return Optional.empty();
        }

        return result;
    }

    @Override
    public long update(long userId, Contacts contacts) {
        long result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "UPDATE lawAndSocialDb.contacts" +
                            " SET email = ?, phone = ?, website = ?" +
                            " WHERE user_id = ?;"
            );
            query.setString(1, contacts.getEmail());
            query.setString(2, contacts.getPhone());
            query.setString(3, contacts.getWebsite());
            query.setLong(4, userId);
            result = query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Updating contacts by userId caused an exception: {}", e.getMessage());
            LOGGER.error("SQL state: {}\nError code: {}", e.getSQLState(), e.getErrorCode());
            LOGGER.error("Contacts: {}", contacts.toString());
            LOGGER.error("UserId: {}", userId);
            return result;
        }

        if (result > 0) {
            LOGGER.debug("userID {} successfully updated contacts", userId);
        }

        return result;
    }

    @Override
    public long delete(long userId) {
        int result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "DELETE FROM lawAndSocialDb.contacts" +
                            " WHERE user_id = ?;"
            );
            query.setLong(1, userId);
            result = query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Deleting contacts by userId caused an exception: {}", e.getMessage());
            LOGGER.error("SQL state: {}\nError code: {}", e.getSQLState(), e.getErrorCode());
            LOGGER.error("userId: {}", userId);
        }

        return result;
    }


}
