package com.epam.training.lawAndSocial.web.filter;

import com.epam.training.lawAndSocial.model.Credentials;
import com.epam.training.lawAndSocial.utils.ServletParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Singleton
public class LoggedInFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggedInFilter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        final HttpServletRequest request = (HttpServletRequest) req;

        final String path = request.getRequestURI();
        LOGGER.debug(path);

        if (path.indexOf("/static") > 0) {
            LOGGER.debug("contains css");
            chain.doFilter(req, resp);
        } else if (path.indexOf("/webjars") > 0) {
            LOGGER.debug("contains webjars");
            chain.doFilter(req, resp);
        }

        if (!path.endsWith("/login")
                && !path.endsWith("/registration")
                && !path.endsWith("/tlg")
                && !path.endsWith("/locale")) {
            final HttpSession session = request.getSession(false);
            final boolean isLoggedIn = session != null && session.getAttribute("user") != null;
            if (!isLoggedIn) {
                LOGGER.debug("Not logged in");
                req.setAttribute(ServletParams.CREDENTIALS_ATTR, Credentials.builder().build());
                req.getRequestDispatcher(ServletParams.LOGIN_PAGE).forward(req, resp);
                return;
            }
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        LOGGER.debug("LoggedIn Filter initialized");
    }

}
