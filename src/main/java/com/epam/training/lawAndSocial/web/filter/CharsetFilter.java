package com.epam.training.lawAndSocial.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.servlet.*;
import java.io.IOException;

@Singleton
public class CharsetFilter implements Filter {

    private final static Logger LOGGER = LoggerFactory.getLogger(CharsetFilter.class);

    private static final String encoding = "utf-8";

    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding(encoding);
        resp.setCharacterEncoding(encoding);
        chain.doFilter(req, resp);
    }

    public void destroy() {
    }

}
