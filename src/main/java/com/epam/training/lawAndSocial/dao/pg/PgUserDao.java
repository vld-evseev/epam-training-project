package com.epam.training.lawAndSocial.dao.pg;

import com.epam.training.lawAndSocial.dao.UserDao;
import com.epam.training.lawAndSocial.model.Gender;
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
                    "INSERT INTO lawAndSocialDb.user(id, username, firstName, lastName, patronymic, gender, birthdate, passwordHash)" +
                            " VALUES (nextval('lawAndSocialDb.user_seq')," +
                            " ?, ?, ?, ?, ?, ?, ?);",
                    returnColumns
            );
            query.setString(1, user.getUserName());
            query.setString(2, user.getFirstName());
            query.setString(3, user.getLastName());
            query.setString(4, user.getPatronymic());
            query.setString(5, Gender.UNKNOWN.toString());
            query.setString(6, user.getDate());
            query.setString(7, user.getPasswordHash());
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
                    "SELECT id, username, firstName, lastName, patronymic, gender, birthdate, passwordHash" +
                            " FROM lawAndSocialDb.user WHERE username = ?;"
            );
            query.setString(1, username);
            final ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                final String gender = getGender(resultSet.getString("gender"));
                result = Optional.of(
                        User.builder()
                                .id(resultSet.getLong("id"))
                                .userName(resultSet.getString("username"))
                                .firstName(resultSet.getString("firstName"))
                                .lastName(resultSet.getString("lastName"))
                                .patronymic(resultSet.getString("patronymic"))
                                .gender(Gender.valueOf(gender))
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
        long result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "UPDATE lawAndSocialDb.user" +
                            " SET username = ?, firstName = ?, lastName = ?, patronymic = ?," +
                            " gender = ?, birthdate = ?" +
                            " WHERE id = ?;"
            );
            query.setString(1, user.getUserName());
            query.setString(2, user.getFirstName());
            query.setString(3, user.getLastName());
            query.setString(4, user.getPatronymic());
            query.setString(5, user.getGender().toString());
            query.setString(6, user.getDate());
            query.setLong(7, user.getId());
            result = query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Updating user caused an exception: {}", e.getMessage());
            LOGGER.error("SQL state: {}\nError code: {}", e.getSQLState(), e.getErrorCode());
            LOGGER.error("User: {}", user.toString());
            return result;
        }

        return result;
    }

    @Override
    public long delete(User user) {
        return 0;
    }

    private String getGender(String result) throws SQLException {
        if (result == null) {
            return Gender.UNKNOWN.toString();
        }
        return result;
    }
}
