package com.epam.training.lawAndSocial.dao.pg;

import com.epam.training.lawAndSocial.dao.MessageHistoryDao;
import com.epam.training.lawAndSocial.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PgMessageHistoryDao implements MessageHistoryDao {

    private final DataSource dataSource;

    private final static Logger LOGGER = LoggerFactory.getLogger(PgMessageHistoryDao.class);

    @Inject
    public PgMessageHistoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int add(Message message) {
        int result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "INSERT INTO lawAndSocialDb.message_history(from_user_id, to_user_id, dateValue, textValue)" +
                            " VALUES (?, ?, ?, ?);"
            );
            query.setLong(1, message.getFromUserId());
            query.setLong(2, message.getToUserId());
            query.setLong(3, message.getDate());
            query.setString(4, message.getText());
            result = query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Adding message caused an exception: {}", e.getMessage());
            LOGGER.error("SQL state: {}\nError code: {}", e.getSQLState(), e.getErrorCode());
            LOGGER.error("Message: {}", message.toString());
        }

        return result;
    }

    @Override
    public int addAll(List<Message> messages) {
        return 0;
    }

    @Override
    public List<Message> getByUserId(long userId, long otherUserId) {
        List<Message> result = new LinkedList<>();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT from_user_id, to_user_id, dateValue, textValue" +
                            " FROM lawAndSocialDb.message_history" +
                            " WHERE (from_user_id = ? AND to_user_id = ?)" +
                            " OR (from_user_id = ? AND to_user_id = ?)" +
                            " ORDER BY 'date' ASC");
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
                                .date(resultSet.getLong("dateValue"))
                                .text(resultSet.getString("textValue"))
                                .build()
                );
            }
        } catch (SQLException e) {
            LOGGER.error("Getting list of messages by user id caused an exception: {}", e.getMessage());
            LOGGER.error("SQL state: {}\nError code: {}", e.getSQLState(), e.getErrorCode());
            LOGGER.error("User id: {}", userId);
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(result);
    }
}
