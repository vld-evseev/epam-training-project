package com.epam.training.lawAndSocial.dao.pg;


import com.epam.training.lawAndSocial.dao.ContactsDao;
import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.model.Contacts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class PgContactsDao implements ContactsDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(PgContactsDao.class);
    private final DataSource dataSource;

    @Inject
    public PgContactsDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long add(long userId, Contacts contacts) throws PersistException {
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
            if (result == 0) {
                throw new PersistException("Rows are not affected on add: " + result);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return result;
    }

    @Override
    public Optional<Contacts> get(long userId) throws PersistException {
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
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return result;
    }

    @Override
    public long update(long userId, Contacts contacts) throws PersistException {
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
            if (result != 1) {
                throw new PersistException("None or more than one row affected on update: " + result);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return result;
    }

    @Override
    public long delete(long userId) throws PersistException {
        int result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "DELETE FROM lawAndSocialDb.contacts" +
                            " WHERE user_id = ?;"
            );
            query.setLong(1, userId);
            result = query.executeUpdate();

            if (result != 1) {
                throw new PersistException("None or more than one row affected on delete: " + result);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return result;
    }


}
