package com.epam.training.lawAndSocial.web.servlet;

import com.epam.training.lawAndSocial.model.Contacts;
import com.epam.training.lawAndSocial.model.Credentials;
import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.service.SecurityService;
import com.epam.training.lawAndSocial.service.UserService;
import com.epam.training.lawAndSocial.service.ValidationService;
import com.epam.training.lawAndSocial.service.model.ContactsService;
import com.epam.training.lawAndSocial.utils.DateValidator;
import com.epam.training.lawAndSocial.web.servlet.model.FieldValidation;
import com.epam.training.lawAndSocial.web.servlet.model.FormValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.epam.training.lawAndSocial.utils.ServletParams.*;

@Singleton
public class RegistrationServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationServlet.class);
    private static final String REGISTRATION_JSP = "/WEB-INF/jsp/registration.jsp";

    private final ValidationService validationService;
    private final SecurityService securityService;
    private final UserService userService;
    private final ContactsService contactsService;

    @Inject
    public RegistrationServlet(ValidationService validationService,
                               SecurityService securityService,
                               UserService userService,
                               ContactsService contactsService) {
        this.validationService = validationService;
        this.securityService = securityService;
        this.userService = userService;
        this.contactsService = contactsService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(CREDENTIALS_ATTR, Credentials.builder().build());
        req.setAttribute(NEW_USER_ATTR, User.builder().build());
        req.setAttribute(CONTACTS_ATTR, Contacts.builder().build());
        req.getRequestDispatcher(REGISTRATION_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Map<String, String> params = collectParams(req);

        final FormValidation validation = validationService.verify(params);

        final Credentials credentials = Credentials.builder()
                .username(params.get(USERNAME_PARAM))
                .password(params.get(PASSWORD_PARAM))
                .build();

        LOGGER.debug("credentials: {}", credentials.toString());

        final User user = User.builder()
                .userName(params.get(USERNAME_PARAM))
                .firstName(params.get(FIRSTNAME_PARAM))
                .lastName(params.get(LASTNAME_PARAM))
                .date(DateValidator.parseDate(params.get(BIRTH_DATE_PARAM), validation))
                .passwordHash(securityService.encrypt(credentials.getPassword()))
                .build();

        final Contacts contacts = Contacts.builder()
                .email(params.get(EMAIL_PARAM))
                .build();

        if (validation.isValid()) {
            if (passwordConfirmed(params, validation)
                    && !userExists(user.getUserName(), validation)) {
                final long userId = addUser(user, validation);
                addContacts(userId, contacts, validation);
            }
        }

        if (!validation.isValid()) {
            LOGGER.debug("registration form validation failed");
            for (String message : validation.messages()) {
                LOGGER.debug(message);
            }

            req.setAttribute("newUser", user);
            req.setAttribute(CONTACTS_ATTR, contacts);
            req.setAttribute(CREDENTIALS_ATTR, credentials);
            req.setAttribute(VALIDATION_ATTR, validation);
            req.getRequestDispatcher(REGISTRATION_JSP)
                    .forward(req, resp);
            return;
        }

        LOGGER.debug("Form validation succeed");
        LOGGER.debug("user was created: {}", user.toString());

        final HttpSession session = req.getSession(true);
        session.setAttribute(USER_ATTR, user);

        resp.sendRedirect(req.getContextPath() + "/user");
    }

    static boolean passwordConfirmed(Map<String, String> params, FormValidation validation) {
        final String pwd = params.get(PASSWORD_PARAM);
        final String confirmedPwd = params.get(CONFIRM_PASSWORD_PARAM);
        final boolean isConfirmed = pwd.equals(confirmedPwd);

        if (!isConfirmed) {
            LOGGER.debug("password doesn't match");
            validation.getFields().put(
                    CONFIRM_PASSWORD_PARAM,
                    FieldValidation.builder().isIncorrect(true).build()
            );
        }

        return isConfirmed;
    }


    boolean userExists(String username, FormValidation validation) {
        final Optional<User> userOptional = userService.getByUsername(username);
        if (userOptional.isPresent()) {
            validation.getErrors().put(
                    "USERNAME_EXISTS",
                    true
            );
            return true;
        }
        return false;
    }

    long addUser(User user, FormValidation validation) {
        final long id = userService.add(user);
        if (id < 0) {
            validation.getErrors().put(
                    "INTERNAL_ERROR",
                    true
            );
        }

        return id;
    }

    void addContacts(long userId, Contacts contacts, FormValidation validation) {
        if (userId > 0) {
            final long id = contactsService.add(userId, contacts);
            if (id < 0) {
                validation.getErrors().put(
                        "INTERNAL_ERROR",
                        true
                );
            }
        }
    }

    static Map<String, String> collectParams(HttpServletRequest req) {
        final Map<String, String> params = new HashMap<>();
        params.put(EMAIL_PARAM, req.getParameter(EMAIL_PARAM));
        params.put(USERNAME_PARAM, req.getParameter(USERNAME_PARAM));
        params.put(FIRSTNAME_PARAM, req.getParameter(FIRSTNAME_PARAM));
        params.put(LASTNAME_PARAM, req.getParameter(LASTNAME_PARAM));
        params.put(BIRTH_DATE_PARAM, req.getParameter(BIRTH_DATE_PARAM));
        params.put(PASSWORD_PARAM, req.getParameter(PASSWORD_PARAM));
        params.put(CONFIRM_PASSWORD_PARAM, req.getParameter(CONFIRM_PASSWORD_PARAM));
        return params;
    }

}
