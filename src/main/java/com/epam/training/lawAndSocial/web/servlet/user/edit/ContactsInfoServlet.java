package com.epam.training.lawAndSocial.web.servlet.user.edit;

import com.epam.training.lawAndSocial.model.Contacts;
import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.service.ValidationService;
import com.epam.training.lawAndSocial.service.model.ContactsService;
import com.epam.training.lawAndSocial.utils.ServletParams;
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
public class ContactsInfoServlet extends HttpServlet {

    private final static Logger LOGGER = LoggerFactory.getLogger(ContactsInfoServlet.class);

    private final ContactsService contactsService;
    private final ValidationService validationService;

    @Inject
    public ContactsInfoServlet(ContactsService contactsService, ValidationService validationService) {
        this.contactsService = contactsService;
        this.validationService = validationService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PROFILE_EDIT_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Map<String, Boolean> activeTab = new HashMap<>();
        activeTab.put("contactsInfoTab", true);
        req.setAttribute(ACTIVE_TAB_ATTR, activeTab);

        final User currentUser = (User) req.getSession(true).getAttribute(USER_ATTR);

        if (currentUser == null) {
            LOGGER.error("user not exists in session");
            resp.sendRedirect(req.getContextPath() + "/user/edit/common");
            return;
        }

        final Map<String, String> params = collectParams(req);
        final FormValidation validation = validationService.verify(collectVerifiedParams(req));

        final Contacts contacts = Contacts.builder()
                .email(params.get(EMAIL_PARAM))
                .phone(params.get(PHONE_PARAM))
                .website(params.get(WEBSITE_PARAM))
                .build();

        if (validation.isValid()) {
            update(currentUser.getId(), contacts, validation);
        }

        if (!validation.isValid()) {
            req.setAttribute(VALIDATION_ATTR, validation);
            req.getRequestDispatcher(PROFILE_EDIT_JSP).forward(req, resp);
            return;
        }

        req.getSession(true).setAttribute(CONTACTS_ATTR, contacts);
        /*resp.sendRedirect(req.getContextPath() + "/user/edit");*/
        req.getRequestDispatcher(ServletParams.PROFILE_EDIT_JSP).forward(req, resp);
    }

    private void update(long userId, Contacts contacts, FormValidation validation) {
        final long id = contactsService.update(userId, contacts);
        if (id < 0) {
            LOGGER.error("error while updating contacts {}", contacts);
            validation.getErrors().put(
                    "INTERNAL_ERROR",
                    true
            );
        }
    }

    private Map<String, String> collectParams(HttpServletRequest req) {
        final Map<String, String> params = new HashMap<>();
        params.put(EMAIL_PARAM, req.getParameter(EMAIL_PARAM));
        params.put(PHONE_PARAM, req.getParameter(PHONE_PARAM));
        params.put(WEBSITE_PARAM, req.getParameter(WEBSITE_PARAM));
        return params;
    }

    private Map<String, String> collectVerifiedParams(HttpServletRequest req) {
        final Map<String, String> params = new HashMap<>();
        params.put(EMAIL_PARAM, req.getParameter(EMAIL_PARAM));
        return params;
    }
}
