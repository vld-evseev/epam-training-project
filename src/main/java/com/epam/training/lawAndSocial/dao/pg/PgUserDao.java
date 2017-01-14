package com.epam.training.lawAndSocial.dao.pg;

import com.epam.training.lawAndSocial.dao.UserDao;
import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.model.Gender;
import com.epam.training.lawAndSocial.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class PgUserDao implements UserDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(PgUserDao.class);
    private final DataSource dataSource;

    @Inject
    public PgUserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long add(User user) throws PersistException {
        int result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final String[] returnColumns = {"id"};
            final PreparedStatement query = connection.prepareStatement(
                    "INSERT INTO lawAndSocialDb.user(id, uuid, username, firstName, lastName, patronymic, gender, birthdate, avatar, passwordHash)" +
                            " VALUES (nextval('lawAndSocialDb.user_seq')," +
                            " ?, ?, ?, ?, ?, ?, ?, ?, ?);",
                    returnColumns
            );
            query.setString(1, user.getUuid());
            query.setString(2, user.getUserName());
            query.setString(3, user.getFirstName());
            query.setString(4, user.getLastName());
            query.setString(5, user.getPatronymic());
            query.setString(6, Gender.UNKNOWN.toString());
            query.setString(7, user.getDate());
            query.setString(8, user.getAvatar());
            query.setString(9, user.getPasswordHash());
            query.executeUpdate();
            final ResultSet generatedKeys = query.getGeneratedKeys();
            if (generatedKeys.next()) {
                result = generatedKeys.getInt(1);
            }

            if (result == 0) {
                throw new PersistException("Rows are not affected on add: " + result);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return result;
    }

    @Override
    public Optional<User> getByUsername(String username) throws PersistException {
        Optional<User> result = Optional.empty();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT id, uuid, username, firstName, lastName, patronymic, gender, birthdate, avatar, passwordHash" +
                            " FROM lawAndSocialDb.user WHERE username = ?;"
            );
            query.setString(1, username);
            final ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                final String gender = getGender(resultSet.getString("gender"));
                result = Optional.of(
                        User.builder()
                                .id(resultSet.getLong("id"))
                                .uuid(resultSet.getString("uuid"))
                                .userName(resultSet.getString("username"))
                                .firstName(resultSet.getString("firstName"))
                                .lastName(resultSet.getString("lastName"))
                                .patronymic(resultSet.getString("patronymic"))
                                .gender(Gender.valueOf(gender))
                                .date(resultSet.getString("birthdate"))
                                .avatar(resultSet.getString("avatar"))
                                .passwordHash(resultSet.getString("passwordHash"))
                                .build()
                );
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return result;
    }

    @Override
    public Optional<User> getByUserId(long id) throws PersistException {
        Optional<User> result = Optional.empty();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT id, uuid, username, firstName, lastName, patronymic, gender, birthdate, avatar, passwordHash" +
                            " FROM lawAndSocialDb.user WHERE id = ?;"
            );
            query.setLong(1, id);
            final ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                final String gender = getGender(resultSet.getString("gender"));
                result = Optional.of(
                        User.builder()
                                .id(resultSet.getLong("id"))
                                .uuid(resultSet.getString("uuid"))
                                .userName(resultSet.getString("username"))
                                .firstName(resultSet.getString("firstName"))
                                .lastName(resultSet.getString("lastName"))
                                .patronymic(resultSet.getString("patronymic"))
                                .gender(Gender.valueOf(gender))
                                .date(resultSet.getString("birthdate"))
                                .avatar(resultSet.getString("avatar"))
                                .build()
                );
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return result;
    }

    @Override
    public long update(User user) throws PersistException {
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

            if (result != 1) {
                throw new PersistException("None or more than one row affected on update: " + result);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return result;
    }

    @Override
    public long updateAvatar(long id, String base64EncodedImage) throws PersistException {
        long result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "UPDATE lawAndSocialDb.user" +
                            " SET avatar = ?" +
                            " WHERE id = ?;"
            );
            query.setString(1, base64EncodedImage);
            query.setLong(2, id);
            result = query.executeUpdate();

            if (result != 1) {
                throw new PersistException("None or more than one row affected on update: " + result);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return result;
    }

    @Override
    public List<User> getUsers(int limit, int offset) throws PersistException {
        List<User> result = new LinkedList<>();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT id, uuid, username, firstName, lastName, patronymic, gender, birthdate, avatar, passwordHash" +
                            " FROM lawAndSocialDb.user" +
                            " ORDER BY id ASC" +
                            " LIMIT ?" +
                            " OFFSET ?;");
            query.setInt(1, limit);
            query.setInt(2, offset);
            final ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                final String gender = getGender(resultSet.getString("gender"));
                result.add(
                        User.builder()
                                .id(resultSet.getLong("id"))
                                .uuid(resultSet.getString("uuid"))
                                .userName(resultSet.getString("username"))
                                .firstName(resultSet.getString("firstName"))
                                .lastName(resultSet.getString("lastName"))
                                .patronymic(resultSet.getString("patronymic"))
                                .gender(Gender.valueOf(gender))
                                .date(resultSet.getString("birthdate"))
                                .avatar(resultSet.getString("avatar"))
                                .passwordHash(resultSet.getString("passwordHash"))
                                .build()
                );
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return Collections.unmodifiableList(result);
    }

    @Override
    public long getNumberOfUsers() throws PersistException {
        long result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT count(id) AS total FROM lawAndSocialDb.user;"
            );
            final ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getLong("total");
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return result;
    }


    private String getGender(String result) {
        if (result == null) {
            return Gender.UNKNOWN.toString();
        }
        return result;
    }
}
