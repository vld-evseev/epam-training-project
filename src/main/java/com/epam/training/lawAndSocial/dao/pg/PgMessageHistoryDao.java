package com.epam.training.lawAndSocial.dao.pg;

import com.epam.training.lawAndSocial.dao.MessageHistoryDao;
import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.model.Message;
import com.epam.training.lawAndSocial.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class PgMessageHistoryDao implements MessageHistoryDao {

    private final DataSource dataSource;

    private final static Logger LOGGER = LoggerFactory.getLogger(PgMessageHistoryDao.class);

    @Inject
    public PgMessageHistoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int add(Message message) throws PersistException {
        int result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "INSERT INTO lawAndSocialDb.message_history(from_user_id, to_user_id, date_value, text_value)" +
                            " VALUES (?, ?, ?, ?);"
            );
            query.setLong(1, message.getFromUserId());
            query.setLong(2, message.getToUserId());
            query.setLong(3, message.getDate());
            query.setString(4, message.getText());
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
    public int addAll(List<Message> messages) {
        return 0;
    }

    @Override
    public List<Message> getByUserId(long userId, long otherUserId) throws PersistException {
        List<Message> result = new LinkedList<>();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT from_user_id, to_user_id, date_value, text_value" +
                            " FROM lawAndSocialDb.message_history" +
                            " WHERE (from_user_id = ? AND to_user_id = ?)" +
                            " OR (from_user_id = ? AND to_user_id = ?)" +
                            " ORDER BY date_value ASC");
            query.setLong(1, userId);
            query.setLong(2, otherUserId);
            query.setLong(3, otherUserId);
            query.setLong(4, userId);
            final ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                result.add(
                        Message.builder()
                                .fromUserId(resultSet.getLong("from_user_id"))
                                .toUserId(resultSet.getLong("to_user_id"))
                                .date(resultSet.getLong("date_value"))
                                .text(resultSet.getString("text_value"))
                                .build()
                );
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return Collections.unmodifiableList(result);
    }

    @Override
    public Set<User> getContacts(long userId, int limit, int offset) throws PersistException {
        Set<User> result = new LinkedHashSet<>();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT us.id AS us_id," +
                            " us.uuid AS us_uuid," +
                            " us.firstName AS us_firstName," +
                            " us.lastName AS us_lastName," +
                            " us.avatar AS us_avatar," +
                            " msg.from_user_id AS msg_from_user_id," +
                            " msg.to_user_id AS msg_to_user_id," +
                            " msg.date_value AS msg_date_value" +
                            " FROM lawAndSocialDb.message_history msg" +
                            " JOIN lawAndSocialDb.user us" +
                            " ON us.id = msg.from_user_id OR us.id = msg.to_user_id" +
                            " WHERE msg.from_user_id = ? OR msg.to_user_id = ?" +
                            " ORDER BY msg_date_value DESC" +
                            " LIMIT ?" +
                            " OFFSET ?;"
            );
            query.setLong(1, userId);
            query.setLong(2, userId);
            query.setInt(3, limit);
            query.setInt(4, offset);
            final ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                final long us_id = resultSet.getLong("us_id");
                if (us_id != userId) {
                    result.add(
                            User.builder()
                                    .id(us_id)
                                    .uuid(resultSet.getString("us_uuid"))
                                    .firstName(resultSet.getString("us_firstName"))
                                    .lastName(resultSet.getString("us_lastName"))
                                    .avatar(resultSet.getString("us_avatar"))
                                    .build()
                    );
                }
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return Collections.unmodifiableSet(result);
    }
}
