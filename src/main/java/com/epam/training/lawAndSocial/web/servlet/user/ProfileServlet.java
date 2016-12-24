package com.epam.training.lawAndSocial.web.servlet.user;

import com.epam.training.lawAndSocial.model.Contacts;
import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.model.education.School;
import com.epam.training.lawAndSocial.service.model.ContactsService;
import com.epam.training.lawAndSocial.service.model.EducationService;
import com.epam.training.lawAndSocial.utils.ServletParams;
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
import java.util.List;
import java.util.Optional;

@Singleton
public class ProfileServlet extends HttpServlet {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProfileServlet.class);

    private final EducationService educationService;
    private final ContactsService contactsService;

    @Inject
    public ProfileServlet(EducationService educationService, ContactsService contactsService) {
        this.educationService = educationService;
        this.contactsService = contactsService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("Successfully authorised");
        final HttpSession session = req.getSession(true);
        final User user = (User) session.getAttribute(ServletParams.USER_ATTR);
        if (user != null) {
            final Contacts contacts = getContacts(user.getId());
            req.setAttribute(ServletParams.CONTACTS_ATTR, contacts);

            final List<School> userSchools = educationService.getUserSchools(user.getId());
            if (userSchools.isEmpty()) {
                final School school = School.builder().build();
                session.setAttribute("school", school);
            } else {
                session.setAttribute("school", userSchools.get(0));
            }
        }

        req.getRequestDispatcher(ServletParams.USER_PAGE)
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ServletParams.USER_PAGE)
                .forward(req, resp);
    }

    private Contacts getContacts(long userId) {
        final Optional<Contacts> contacts = contactsService.getByUserId(userId);
        if (contacts.isPresent()) {
            return contacts.get();
        } else {
            return Contacts.builder().build();
        }
    }
}
