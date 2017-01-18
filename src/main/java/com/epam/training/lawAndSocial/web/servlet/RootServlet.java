package com.epam.training.lawAndSocial.web.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.training.lawAndSocial.utils.ServletParams.USER_ATTR;

@Singleton
public class RootServlet extends HttpServlet {

    private final static Logger LOGGER = LoggerFactory.getLogger(RootServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Object user = req.getSession(true).getAttribute(USER_ATTR);
        if (user != null) {
            LOGGER.debug("redirecting to user page");
            resp.sendRedirect(req.getContextPath() + "/user");
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
