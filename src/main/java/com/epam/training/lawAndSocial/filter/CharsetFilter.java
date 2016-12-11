package com.epam.training.lawAndSocial.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {

    private final static Logger LOGGER = LoggerFactory.getLogger(CharsetFilter.class);

    private String encoding = "utf-8";

    public void init(FilterConfig config) throws ServletException {
        final String encodingParam = config.getInitParameter("encoding");
        if (encodingParam != null) {
            encoding = encodingParam;
        }
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        LOGGER.debug("Charset: {}", encoding);

        req.setCharacterEncoding(encoding);
        resp.setCharacterEncoding(encoding);
        chain.doFilter(req, resp);
    }

    public void destroy() {
    }

}
