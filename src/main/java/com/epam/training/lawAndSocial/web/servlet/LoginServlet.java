package com.epam.training.lawAndSocial.web.servlet;

import com.epam.training.lawAndSocial.model.Credentials;
import com.epam.training.lawAndSocial.service.ValidationService;
import com.epam.training.lawAndSocial.web.servlet.model.FormValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.epam.training.lawAndSocial.utils.ServletParams.*;

@Singleton
public class LoginServlet extends HttpServlet {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);
    private final ValidationService validationService;

    @Inject
    public LoginServlet(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(CREDENTIALS_PARAM, Credentials.builder().build());
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Map<String, String> params = collectParams(req);

        final Credentials credentials = Credentials.builder()
                .username(params.get(USERNAME_PARAM))
                .password(params.get(PASSWORD_PARAM))
                .build();

        final FormValidation validation = validationService.verify(params);
        if (!validation.isValid()) {
            req.setAttribute(VALIDATION_PARAM, validation);
            req.setAttribute(CREDENTIALS_PARAM, credentials);
            req.getSession().setAttribute(IS_AUTHORISED_PARAM, false);
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp")
                    .forward(req, resp);
            return;
        }

        req.getSession().setAttribute(IS_AUTHORISED_PARAM, true);

        resp.sendRedirect("/WEB-INF/index.jsp");
    }

    private Map<String, String> collectParams(HttpServletRequest req) {
        final Map<String, String> params = new HashMap<>();
        params.put(USERNAME_PARAM, req.getParameter(USERNAME_PARAM));
        params.put(PASSWORD_PARAM, req.getParameter(PASSWORD_PARAM));
        return params;
    }
}
