package com.epam.training.lawAndSocial.dao.pg;

import com.epam.training.lawAndSocial.dao.UserDao;
import com.epam.training.lawAndSocial.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PgUserDao implements UserDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(PgUserDao.class);
    private final DataSource dataSource;

    @Inject
    public PgUserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long add(User user) {
        int result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final String[] returnColumns = {"id"};
            final PreparedStatement query = connection.prepareStatement(
                    "INSERT INTO lawAndSocialDb.user(id, username, firstName, lastName, birthdate, passwordHash)" +
                            " VALUES (nextval('lawAndSocialDb.user_seq')," +
                            " ?, ?, ?, ?, ?);",
                    returnColumns
            );
            query.setString(1, user.getUserName());
            query.setString(2, user.getFirstName());
            query.setString(3, user.getLastName());
            query.setString(4, user.getDate());
            query.setString(5, user.getPasswordHash());
            query.executeUpdate();
            final ResultSet generatedKeys = query.getGeneratedKeys();
            if (generatedKeys.next()) {
                result = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error("Adding user caused an exception: {}", e.getMessage());
            LOGGER.error("SQL state: {}\nError code: {}", e.getSQLState(), e.getErrorCode());
            LOGGER.error("User: {}", user.toString());
        }

        return result;
    }

    @Override
    public Optional<User> getByUsername(String username) {
        Optional<User> result = Optional.empty();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT id, username, firstName, lastName, birthdate, passwordHash" +
                            " FROM lawAndSocialDb.user WHERE username = ?;"
            );
            query.setString(1, username);
            final ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                result = Optional.of(
                        User.builder()
                                .id(resultSet.getLong("id"))
                                .userName(resultSet.getString("username"))
                                .firstName(resultSet.getString("firstName"))
                                .lastName(resultSet.getString("lastName"))
                                .date(resultSet.getString("birthdate"))
                                .passwordHash(resultSet.getString("passwordHash"))
                                .build()
                );
            }
        } catch (SQLException e) {
            LOGGER.error("Getting user by userName caused an exception: {}", e.getMessage());
            LOGGER.error("SQL state: {}\nError code: {}", e.getSQLState(), e.getErrorCode());
            LOGGER.error("Username: {}", username);
            return Optional.empty();
        }

        return result;
    }

    @Override
    public long update(User user) {
        return 0;
    }

    @Override
    public long delete(User user) {
        return 0;
    }
}
