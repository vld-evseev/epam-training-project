package com.epam.training.lawAndSocial.web.servlet;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.training.lawAndSocial.utils.ServletParams.USER_ATTR;

@Singleton
public class SignoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession(true);
        final Object userAttr = session.getAttribute(USER_ATTR);
        if (userAttr != null) {
            session.setAttribute(USER_ATTR, null);
        }
        resp.sendRedirect(req.getContextPath());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
