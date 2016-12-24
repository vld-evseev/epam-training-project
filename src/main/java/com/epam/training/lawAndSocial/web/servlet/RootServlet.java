package com.epam.training.lawAndSocial.web.servlet;

import com.epam.training.lawAndSocial.utils.ServletParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class RootServlet extends HttpServlet {

    private final static Logger LOGGER = LoggerFactory.getLogger(RootServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Object user = req.getSession(true).getAttribute("user");
        if (user != null) {
            LOGGER.debug("redirecting to user page");
            req.getRequestDispatcher(ServletParams.USER_PAGE).forward(req, resp);
            return;
        }

        req.getRequestDispatcher(ServletParams.INDEX_PAGE)
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
