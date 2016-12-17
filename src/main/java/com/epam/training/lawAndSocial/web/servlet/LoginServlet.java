package com.epam.training.lawAndSocial.web.servlet;

import com.epam.training.lawAndSocial.model.Credentials;
import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.service.UserService;
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
import java.util.Optional;

import static com.epam.training.lawAndSocial.utils.ServletParams.*;

@Singleton
public class LoginServlet extends HttpServlet {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);
    private final ValidationService validationService;
    private final UserService userService;

    @Inject
    public LoginServlet(ValidationService validationService, UserService userService) {
        this.validationService = validationService;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("show login page");
        req.getSession(true);
        req.setAttribute(CREDENTIALS_ATTR, Credentials.builder().build());
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Map<String, String> params = collectParams(req);
        final FormValidation validation = validationService.verify(params);

        final Credentials credentials = Credentials.builder()
                .username(params.get(USERNAME_PARAM))
                .password(params.get(PASSWORD_PARAM))
                .build();

        final Optional<User> userOptional = findUser(validation, credentials);

        if (!validation.isValid()) {
            req.setAttribute(VALIDATION_ATTR, validation);
            req.setAttribute(CREDENTIALS_ATTR, credentials);
            /*req.getSession().setAttribute(IS_AUTHORISED_ATTR, false);*/
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp")
                    .forward(req, resp);
            return;
        }

        /*req.getSession().setAttribute(IS_AUTHORISED_ATTR, true);*/
        req.getSession().setAttribute(USER_ATTR, userOptional.get());

        resp.sendRedirect(req.getContextPath() + "/profile");
    }

    private Optional<User> findUser(FormValidation validation, Credentials credentials) {
        final Optional<User> userOptional = userService.getByCredentials(credentials);
        if (userOptional.isPresent()) {
            LOGGER.debug("user {} is present", userOptional.get().toString());
        } else {
            LOGGER.debug("user not found");
            validation.getErrors().put(
                    "INVALID_CREDENTIALS",
                    true
            );
        }
        return userOptional;
    }


    private Map<String, String> collectParams(HttpServletRequest req) {
        final Map<String, String> params = new HashMap<>();
        params.put(USERNAME_PARAM, req.getParameter(USERNAME_PARAM));
        params.put(PASSWORD_PARAM, req.getParameter(PASSWORD_PARAM));
        return params;
    }
}
