package com.epam.training.lawAndSocial.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.training.lawAndSocial.utils.ServletParams.USER_ATTR;

@Singleton
public class LoggedInFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggedInFilter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        final HttpServletRequest request = (HttpServletRequest) req;

        final String path = request.getRequestURI();
        if (path.indexOf("/static") > 0) {
            chain.doFilter(req, resp);
        } else if (path.indexOf("/webjars") > 0) {
            chain.doFilter(req, resp);
        }

        if (!path.endsWith("/login")
                && !path.endsWith("/registration")
                && !path.endsWith("/locale")) {
            final HttpSession session = request.getSession(false);
            final boolean isLoggedIn = session != null && session.getAttribute(USER_ATTR) != null;
            if (!isLoggedIn) {
                LOGGER.debug("Not logged in");
                final HttpServletResponse response = (HttpServletResponse) resp;
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        LOGGER.debug("LoggedIn Filter initialized");
    }

}
