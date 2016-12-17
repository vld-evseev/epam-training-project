package com.epam.training.lawAndSocial.web.servlet;

import com.epam.training.lawAndSocial.model.Credentials;
import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.service.SecurityService;
import com.epam.training.lawAndSocial.service.UserService;
import com.epam.training.lawAndSocial.service.ValidationService;
import com.epam.training.lawAndSocial.service.impl.ValidationServiceImpl;
import com.epam.training.lawAndSocial.web.servlet.model.FormValidation;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.epam.training.lawAndSocial.utils.ServletParams.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RegistrationServletTest {

    @Test
    public void registrationTest() throws ServletException, IOException {
        final UserService userService = mock(UserService.class);

        Map<String, String> params = generateValidParams();

        final Credentials credentials = Credentials.builder()
                .username("testUser")
                .password("testPass")
                .build();

        final SecurityService securityService = mock(SecurityService.class);
        /*when(securityService.encrypt(credentials.getPassword())).thenReturn("hash");*/

        final ValidationService validationService = mock(ValidationServiceImpl.class);

        final User user = User.builder()
                .email(params.get(EMAIL_PARAM))
                .userName(params.get(USERNAME_PARAM))
                .firstName(params.get(FIRSTNAME_PARAM))
                .lastName(params.get(LASTNAME_PARAM))
                .date(params.get(DATE_PARAM))
                .passwordHash(securityService.encrypt(params.get(PASSWORD_PARAM)))
                .build();

        when(userService.getByCredentials(credentials)).thenReturn(
                Optional.of(user)
        );

        final RegistrationServlet registrationServlet = new RegistrationServlet(
                validationService, securityService, userService
        );


        final HttpServletRequest req = mock(HttpServletRequest.class);
        final HttpServletResponse resp = mock(HttpServletResponse.class);

        final HttpSession session = mock(HttpSession.class);
        when(req.getSession(true)).thenReturn(session);
        when(req.getParameter(EMAIL_PARAM)).thenReturn(params.get(EMAIL_PARAM));
        when(req.getParameter(USERNAME_PARAM)).thenReturn(params.get(USERNAME_PARAM));
        when(req.getParameter(FIRSTNAME_PARAM)).thenReturn(params.get(FIRSTNAME_PARAM));
        when(req.getParameter(DATE_PARAM)).thenReturn(params.get(DATE_PARAM));
        when(req.getParameter(PASSWORD_PARAM)).thenReturn(params.get(PASSWORD_PARAM));
        when(req.getParameter(CONFIRM_PASSWORD_PARAM)).thenReturn(params.get(CONFIRM_PASSWORD_PARAM));
        when(req.getContextPath()).thenReturn("/contextPath");

        registrationServlet.doPost(req, resp);

        verify(session).setAttribute("user", user);
        verify(resp).sendRedirect("/");
    }

    private Map<String, String> generateValidParams() {
        final Map<String, String> params = new HashMap<>();
        params.put(EMAIL_PARAM, "test@mail.com");
        params.put(USERNAME_PARAM, "testUsername");
        params.put(FIRSTNAME_PARAM, "testFirstName");
        params.put(LASTNAME_PARAM, "testLastName");
        params.put(DATE_PARAM, "12.14.1982");
        params.put(PASSWORD_PARAM, "testPassword");
        params.put(CONFIRM_PASSWORD_PARAM, "testPassword");
        return params;
    }

    @Test
    public void parseDateTest() {
        final FormValidation formValidation = mock(FormValidation.class);

        final String validDate = "01.12.1945";
        final String result1 = RegistrationServlet.parseDate(validDate, formValidation);
        assertEquals(result1, validDate);

        final String nonValidDate = "007";
        final String result2 = RegistrationServlet.parseDate(nonValidDate, formValidation);
        assertEquals(result2, LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }

    @Test
    public void passwordConfirmedTest() {
        final FormValidation validation = mock(FormValidation.class);

        final Map<String, String> params = new HashMap<>();
        params.put(PASSWORD_PARAM, "123456");
        params.put(CONFIRM_PASSWORD_PARAM, "654321");

        final boolean notConfirmed = RegistrationServlet.passwordConfirmed(params, validation);
        assertFalse(notConfirmed);

        params.put(CONFIRM_PASSWORD_PARAM, "123456");
        final boolean confirmed = RegistrationServlet.passwordConfirmed(params, validation);
        assertTrue(confirmed);
    }

}
