package com.epam.training.lawAndSocial.service.model.impl;

import com.epam.training.lawAndSocial.dao.NewsDao;
import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.model.News;
import com.epam.training.lawAndSocial.service.model.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

public class NewsServiceImpl implements NewsService {

    private final static Logger LOGGER = LoggerFactory.getLogger(NewsServiceImpl.class);
    private static final int USERS_SHOW_LIMIT = 30;
    private final NewsDao newsDao;

    @Inject
    public NewsServiceImpl(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @Override
    public int add(long userId, News news) {
        try {
            return newsDao.add(userId, news);
        } catch (PersistException e) {
            LOGGER.error("Adding news caused an exception: {}", e.getMessage());
            LOGGER.error("Message: {}", news.toString());
            return -1;
        }
    }

    @Override
    public List<News> getByUserId(long userId, int offset) {
        try {
            return newsDao.getByUserId(userId, USERS_SHOW_LIMIT, offset);
        } catch (PersistException e) {
            LOGGER.error("Getting news by user id caused an exception: {}", e.getMessage());
            LOGGER.error("User id: {}", userId);
            return Collections.emptyList();
        }
    }

    @Override
    public int getNumberOfNews(long userId) {
        if (userId > 0) {
            return getByUserId(userId, 0).size();
        } else {
            return getAll(0).size();
        }
    }

    @Override
    public List<News> getAll(int offset) {
        try {
            return newsDao.getAll(USERS_SHOW_LIMIT, offset);
        } catch (PersistException e) {
            LOGGER.error("Getting all news caused an exception: {}", e.getMessage());
            LOGGER.error("limit: {}, offset: {}", USERS_SHOW_LIMIT, offset);
            return Collections.emptyList();
        }
    }
}
