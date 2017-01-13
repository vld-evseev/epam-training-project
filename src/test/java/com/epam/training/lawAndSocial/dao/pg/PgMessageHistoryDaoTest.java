package com.epam.training.lawAndSocial.dao.pg;

import com.epam.training.lawAndSocial.db.H2DataSourceTest;
import com.epam.training.lawAndSocial.model.Message;
import com.epam.training.lawAndSocial.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PgMessageHistoryDaoTest extends H2DataSourceTest {

    private DataSource dataSource;

    @Before
    public void initDatasource() {
        dataSource = getDataSource();
    }

    @Test
    public void add() throws Exception {
        final PgUserDao userDao = new PgUserDao(dataSource);
        final Optional<User> fromUserOptional = userDao.getByUserId(1);
        final Optional<User> toUserOptional = userDao.getByUserId(2);
        Assert.assertTrue(fromUserOptional.isPresent());
        Assert.assertTrue(toUserOptional.isPresent());

        final PgMessageHistoryDao messageHistoryDao = new PgMessageHistoryDao(dataSource);

        final int result1 = messageHistoryDao.add(
                Message.builder()
                        .fromUserId(fromUserOptional.get().getId())
                        .toUserId(toUserOptional.get().getId())
                        .date(Instant.now().toEpochMilli())
                        .text("Test message from user 1")
                        .build()
        );

        Assert.assertTrue(result1 > 0);

        final int result2 = messageHistoryDao.add(
                Message.builder()
                        .fromUserId(toUserOptional.get().getId())
                        .toUserId(fromUserOptional.get().getId())
                        .date(Instant.now().toEpochMilli())
                        .text("Test message from user 2")
                        .build()
        );

        Assert.assertTrue(result2 > 0);

        final List<Message> messageList = messageHistoryDao.getByUserId(1, 2);
        System.out.println(Arrays.toString(messageList.toArray()));
    }

    @Test
    public void getByUserId() throws Exception {
        final PgMessageHistoryDao messageHistoryDao = new PgMessageHistoryDao(dataSource);
        final List<Message> messageList = messageHistoryDao.getByUserId(1, 2);
        System.out.println(Arrays.toString(messageList.toArray()));

        Assert.assertFalse(messageList.isEmpty());
    }

}