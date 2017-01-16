package com.epam.training.lawAndSocial.service.model;

import com.epam.training.lawAndSocial.model.News;

import java.util.List;

public interface NewsService {

    int add(long userId, News news);

    List<News> getByUserId(long userId, int offset);

    int getNumberOfNews(long userId);

    List<News> getAll(int offset);

}
