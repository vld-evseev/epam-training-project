package com.epam.training.lawAndSocial.dao;

import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.model.News;

import java.util.List;

public interface NewsDao {

    int add(long userId, News news) throws PersistException;

    List<News> getByUserId(long userId, int limit, int offset) throws PersistException;

    List<News> getAll(int limit, int offset) throws PersistException;

}
