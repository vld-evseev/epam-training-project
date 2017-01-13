package com.epam.training.lawAndSocial.web.servlet.user;

import com.epam.training.lawAndSocial.model.Contacts;
import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.model.education.School;
import com.epam.training.lawAndSocial.service.UserService;
import com.epam.training.lawAndSocial.service.model.ContactsService;
import com.epam.training.lawAndSocial.service.model.EducationService;
import com.epam.training.lawAndSocial.utils.CheckUtils;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.epam.training.lawAndSocial.utils.ServletParams.*;

@Singleton
public class ProfileServlet extends HttpServlet {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProfileServlet.class);

    private final EducationService educationService;
    private final ContactsService contactsService;
    private final UserService userService;

    @Inject
    public ProfileServlet(EducationService educationService, ContactsService contactsService, UserService userService) {
        this.educationService = educationService;
        this.contactsService = contactsService;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("Successfully authorised");
        final HttpSession session = req.getSession(true);
        final User user = (User) session.getAttribute(USER_ATTR);

        final String idParam = req.getParameter("id");
        if (idParam != null && !idParam.isEmpty() && CheckUtils.isNumeric(idParam)) {
            final Optional<User> userOptional = userService.getUserById(Long.parseLong(idParam));
            if (userOptional.isPresent()) {
                final User requestedUser = userOptional.get();
                req.setAttribute(REQUESTED_USER_ATTR, requestedUser);

                final Contacts requestedUserContacts = getContacts(requestedUser.getId());
                req.setAttribute(CONTACTS_ATTR, requestedUserContacts);

                final List<School> requestedUserSchools = educationService.getUserSchools(requestedUser.getId());
                req.setAttribute(REQUESTED_USER_SCHOOLS_ATTR, requestedUserSchools);

                req.getRequestDispatcher(USER_PAGE)
                        .forward(req, resp);
                return;
            }
        }

        req.setAttribute(REQUESTED_USER_ATTR, User.builder().build());
        req.setAttribute(REQUESTED_USER_SCHOOLS_ATTR, Collections.emptyList());

        if (user != null) {
            final Contacts contacts = getContacts(user.getId());
            req.setAttribute(CONTACTS_ATTR, contacts);

            final List<School> userSchools = educationService.getUserSchools(user.getId());
            session.setAttribute(SCHOOLS_ATTR, userSchools);
        }

        LOGGER.debug("user entered profile page: {}", user.toString());

        req.getRequestDispatcher(USER_PAGE)
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(USER_PAGE)
                .forward(req, resp);
    }

    private Contacts getContacts(long userId) {
        final Optional<Contacts> contacts = contactsService.getByUserId(userId);
        if (contacts.isPresent()) {
            return contacts.get();
        }

        return Contacts.builder().build();
    }
}
