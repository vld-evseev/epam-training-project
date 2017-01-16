package com.epam.training.lawAndSocial.web.servlet;

import com.epam.training.lawAndSocial.model.News;
import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.service.ValidationService;
import com.epam.training.lawAndSocial.service.model.NewsService;
import com.epam.training.lawAndSocial.utils.CheckUtils;
import com.epam.training.lawAndSocial.web.servlet.model.FormValidation;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.training.lawAndSocial.utils.ServletParams.*;

@Singleton
public class NewsServlet extends HttpServlet {

    final static int NEWS_PER_PAGE = 10;
    private final NewsService newsService;
    private final ValidationService validationService;

    @Inject
    public NewsServlet(NewsService newsService, ValidationService validationService) {
        this.newsService = newsService;
        this.validationService = validationService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession(true);
        final User user = (User) session.getAttribute(USER_ATTR);

        req.setAttribute("path", "/news");
        final long numberOfNews = getNumberOfNews(user.getId());
        final int pagesCount = getPagesCount(numberOfNews);
        req.setAttribute(PAGES_COUNT_ATTR, pagesCount);

        final int currentPageNumber = getCurrentPageNumber(req);
        final List<News> requestedNews = getRequestedNews(currentPageNumber);
        req.setAttribute(NEWS_LIST_ATTR, requestedNews);

        String queryString = getPageQueryString(req);
        req.getRequestDispatcher(NEWS_JSP + "?" + queryString).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession(true);
        final User user = (User) session.getAttribute(USER_ATTR);

        final Map<String, String> params = collectParams(req);
        final Map<String, String> validatedParams = collectValidatedParams(req);

        final FormValidation validation = validationService.verify(validatedParams);

        if (validation.isValid()) {
            final News news = News.builder()
                    .user(user)
                    .date(Instant.now().toEpochMilli())
                    .heading(params.get(NEWS_HEADING_PARAM))
                    .content(params.get(NEWS_CONTENT_PARAM))
                    .build();
            addNews(news, validation);
        }

        if (!validation.isValid()) {
            req.setAttribute(VALIDATION_ATTR, validation);
        }

        doGet(req, resp);
    }

    private void addNews(News news, FormValidation validation) {
        final int result = newsService.add(news.getUser().getId(), news);
        if (result < 0) {
            validation.getErrors().put(
                    "INTERNAL_ERROR",
                    true
            );
        }
    }

    private int getNumberOfNews(long userId) {
        return newsService.getNumberOfNews(userId);
    }

    private List<News> getRequestedNews(int currentPageNumber) {
        final int currentOffset = (currentPageNumber - 1) * NEWS_PER_PAGE;
        return newsService.getAll(currentOffset);
    }

    static String getPageQueryString(HttpServletRequest req) {
        String queryString = req.getQueryString();
        if (queryString == null || queryString.isEmpty() || !queryString.startsWith("page")) {
            queryString = "page=1";
        }
        return queryString;
    }

    static int getCurrentPageNumber(HttpServletRequest req) {
        final String pageParam = req.getParameter("page");
        int currentPageNumber = 1;
        if (pageParam != null && !pageParam.isEmpty() && CheckUtils.isNumeric(pageParam)) {
            currentPageNumber = Integer.parseInt(pageParam);
        }
        return currentPageNumber;
    }

    static int getPagesCount(double numberOfNews) {
        return (int) Math.ceil(numberOfNews / NEWS_PER_PAGE);
    }

    static Map<String, String> collectParams(HttpServletRequest req) {
        final Map<String, String> params = new HashMap<>();
        params.put(NEWS_HEADING_PARAM, req.getParameter(NEWS_HEADING_PARAM));
        params.put(NEWS_CONTENT_PARAM, req.getParameter(NEWS_CONTENT_PARAM));
        return params;
    }

    static Map<String, String> collectValidatedParams(HttpServletRequest req) {
        final Map<String, String> params = new HashMap<>();
        params.put(NEWS_CONTENT_PARAM, req.getParameter(NEWS_CONTENT_PARAM));
        return params;
    }

}
