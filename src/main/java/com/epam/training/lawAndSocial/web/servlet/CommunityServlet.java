package com.epam.training.lawAndSocial.web.servlet;

import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.service.UserService;
import com.epam.training.lawAndSocial.utils.CheckUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Singleton
public class CommunityServlet extends HttpServlet {

    private final static Logger LOGGER = LoggerFactory.getLogger(CommunityServlet.class);
    private final static int USERS_PER_PAGE = 30;

    private final UserService userService;

    @Inject
    public CommunityServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long numberOfUsers = userService.getNumberOfUsers();
        final int pagesCount = (int) Math.ceil((double) numberOfUsers / USERS_PER_PAGE);
        LOGGER.debug("pagesCount: {}", pagesCount);

        req.setAttribute("pagesCount", pagesCount);

        String queryString = req.getQueryString();
        if (queryString == null || queryString.isEmpty() || !queryString.startsWith("page")) {
            queryString = "page=1";
        }

        final String pageParam = req.getParameter("page");
        int pageValue = 1;
        if (pageParam != null && !pageParam.isEmpty() && CheckUtils.isNumeric(pageParam)) {
            pageValue = Integer.parseInt(pageParam);
        }

        final List<User> userList = requestNextListOfUsers((pageValue - 1) * USERS_PER_PAGE);
        req.setAttribute("userList", userList);

        req.getRequestDispatcher("/WEB-INF/jsp/community.jsp?" + queryString).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private List<User> requestNextListOfUsers(int offset) {
        return userService.getUsers(offset);
    }
}
