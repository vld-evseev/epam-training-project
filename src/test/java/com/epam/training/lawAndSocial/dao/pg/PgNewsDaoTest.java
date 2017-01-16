package com.epam.training.lawAndSocial.dao.pg;

import com.epam.training.lawAndSocial.db.H2DataSourceTest;
import com.epam.training.lawAndSocial.model.News;
import com.epam.training.lawAndSocial.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.time.Instant;
import java.util.List;

public class PgNewsDaoTest extends H2DataSourceTest {

    private DataSource dataSource;

    @Before
    public void initDatasource() {
        dataSource = getDataSource();
    }

    @Test
    public void add() throws Exception {
        final PgNewsDao newsDao = new PgNewsDao(dataSource);

        final News testNews = News.builder()
                .user(User.builder()
                        .id(2)
                        .build())
                .date(Instant.now().toEpochMilli())
                .heading("heading")
                .content("content")
                .build();

        final int result = newsDao.add(2, testNews);
        Assert.assertTrue(result > 0);
    }

    @Test
    public void getByUserId() throws Exception {
        final PgNewsDao newsDao = new PgNewsDao(dataSource);

        final List<News> newsList = newsDao.getByUserId(2, Integer.MAX_VALUE, 0);
        Assert.assertFalse(newsList.isEmpty());

        for (News news : newsList) {
            Assert.assertEquals(news.getUser().getId(), 2);
        }
    }

    @Test
    public void getAll() throws Exception {
        final PgNewsDao newsDao = new PgNewsDao(dataSource);

        final List<News> newsList = newsDao.getAll(Integer.MAX_VALUE, 0);
        Assert.assertFalse(newsList.isEmpty());
    }

}