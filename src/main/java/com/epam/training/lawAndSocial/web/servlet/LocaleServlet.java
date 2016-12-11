package com.epam.training.lawAndSocial.web.servlet;

import com.epam.training.lawAndSocial.utils.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.training.lawAndSocial.utils.ServletParams.LOCALE_PARAM;
import static com.epam.training.lawAndSocial.utils.ServletParams.REDIRECT_TO_PARAM;

@WebServlet("/locale")
public class LocaleServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocaleServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession(true);
        session.setAttribute(LOCALE_PARAM, req.getParameter(LOCALE_PARAM));

        final String redirectParam = req.getParameter(REDIRECT_TO_PARAM);

        LOGGER.debug("redirectAttr: {}", redirectParam);

        if (ServletUtils.paramExists(redirectParam)) {
            LOGGER.debug("redirecting to: {}", redirectParam);
            resp.sendRedirect(redirectParam);
        } else {
            LOGGER.debug("redirecting to: {}", req.getContextPath());
            resp.sendRedirect(req.getContextPath());
        }
    }
}
