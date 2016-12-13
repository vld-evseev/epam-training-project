package com.epam.training.lawAndSocial.web.servlet;

import com.epam.training.lawAndSocial.model.Credentials;
import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.service.SecurityService;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static com.epam.training.lawAndSocial.utils.ServletParams.*;

@Singleton
public class RegistrationServlet extends HttpServlet {

    private final static Logger LOGGER = LoggerFactory.getLogger(RegistrationServlet.class);

    private final ValidationService validationService;
    private final SecurityService securityService;

    @Inject
    public RegistrationServlet(ValidationService validationService, SecurityService securityService) {
        this.validationService = validationService;
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Map<String, String> params = collectParams(req);

        final Credentials credentials = Credentials.builder()
                .username(params.get(USERNAME_PARAM))
                .password(params.get(PASSWORD_PARAM))
                .build();

        final User user = User.builder()
                .email(params.get(EMAIL_PARAM))
                .userName(params.get(USERNAME_PARAM))
                .firstName(params.get(EMAIL_PARAM))
                .lastName(params.get(LASTNAME_PARAM))
                .date(parseDate(params.get(DATE_PARAM)))
                .passwordHash(securityService.encrypt(credentials.getPassword()))
                .build();

        final FormValidation validation = validationService.verify(params);
        if (!validation.isValid()) {
            req.setAttribute("validation", validation);
        }


        doGet(req, resp);
    }

    private Map<String, String> collectParams(HttpServletRequest req) {
        final Map<String, String> params = new HashMap<>();
        params.put(req.getParameter(EMAIL_PARAM), EMAIL_PARAM);
        params.put(req.getParameter(USERNAME_PARAM), USERNAME_PARAM);
        params.put(req.getParameter(FIRSTNAME_PARAM), FIRSTNAME_PARAM);
        params.put(req.getParameter(LASTNAME_PARAM), LASTNAME_PARAM);
        params.put(req.getParameter(DATE_PARAM), DATE_PARAM);
        params.put(req.getParameter(PASSWORD_PARAM), PASSWORD_PARAM);
        params.put(req.getParameter(CONFIRM_PASSWORD_PARAM), CONFIRM_PASSWORD_PARAM);
        return params;
    }

    private LocalDate parseDate(String date) {
        final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd.mm.yyyy");
        return LocalDate.parse(date, pattern);
    }
}
