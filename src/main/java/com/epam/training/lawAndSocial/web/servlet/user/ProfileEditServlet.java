package com.epam.training.lawAndSocial.web.servlet.user;


import com.epam.training.lawAndSocial.model.Contacts;
import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.model.education.EducationInfo;
import com.epam.training.lawAndSocial.service.model.ContactsService;
import com.epam.training.lawAndSocial.service.model.EducationInfoService;
import com.epam.training.lawAndSocial.service.model.impl.educationInfo.annotations.SchoolInfo;
import com.epam.training.lawAndSocial.service.model.impl.educationInfo.annotations.UniverInfo;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.epam.training.lawAndSocial.utils.ServletParams.*;

@Singleton
public class ProfileEditServlet extends HttpServlet {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProfileEditServlet.class);
    private final ContactsService contactsService;
    private final EducationInfoService<EducationInfo> schoolInfoService;
    private final EducationInfoService<EducationInfo> univerInfoService;

    @Inject
    public ProfileEditServlet(ContactsService contactsService,
                              @SchoolInfo EducationInfoService<EducationInfo> schoolInfoService,
                              @UniverInfo EducationInfoService<EducationInfo> univerInfoService) {
        this.contactsService = contactsService;
        this.schoolInfoService = schoolInfoService;
        this.univerInfoService = univerInfoService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("showing {}", PROFILE_EDIT_JSP);
        final HttpSession session = req.getSession(true);
        final User user = (User) session.getAttribute(USER_ATTR);
        if (user != null) {
            final Contacts contacts = getContacts(user.getId());
            session.setAttribute(CONTACTS_ATTR, contacts);

            final List<EducationInfo> userSchools = schoolInfoService.getList(user.getId());
            session.setAttribute(SCHOOLS_ATTR, userSchools);

            final List<EducationInfo> userUniversities = univerInfoService.getList(user.getId());

            LOGGER.debug("----------------");
            for (EducationInfo userUniversity : userUniversities) {
                LOGGER.debug(userUniversity.toString());
            }


            session.setAttribute(UNIVERSITIES_ATTR, userUniversities);
        }

        final Map<String, Boolean> activeTab = new HashMap<>();
        activeTab.put("commonInfoTab", true);
        req.setAttribute(ACTIVE_TAB_ATTR, activeTab);
        req.getRequestDispatcher(PROFILE_EDIT_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doPost(req, resp);
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
