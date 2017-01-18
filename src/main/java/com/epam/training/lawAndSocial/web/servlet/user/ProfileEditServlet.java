package com.epam.training.lawAndSocial.web.servlet.user;


import com.epam.training.lawAndSocial.model.Contacts;
import com.epam.training.lawAndSocial.model.Job;
import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.model.education.EducationInfo;
import com.epam.training.lawAndSocial.service.model.ContactsService;
import com.epam.training.lawAndSocial.service.model.EducationInfoService;
import com.epam.training.lawAndSocial.service.model.JobInfoService;
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
    private final JobInfoService jobInfoService;

    @Inject
    public ProfileEditServlet(ContactsService contactsService,
                              JobInfoService jobInfoService,
                              @SchoolInfo EducationInfoService<EducationInfo> schoolInfoService,
                              @UniverInfo EducationInfoService<EducationInfo> univerInfoService) {
        this.contactsService = contactsService;
        this.jobInfoService = jobInfoService;
        this.schoolInfoService = schoolInfoService;
        this.univerInfoService = univerInfoService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setActiveTabAttribute(req);
        final HttpSession session = req.getSession(true);
        final User user = (User) session.getAttribute(USER_ATTR);

        final Contacts contacts = getContacts(user.getId());
        session.setAttribute(CONTACTS_ATTR, contacts);

        final List<EducationInfo> userSchools = schoolInfoService.getList(user.getId());
        session.setAttribute(SCHOOLS_ATTR, userSchools);

        final List<EducationInfo> userUniversities = univerInfoService.getList(user.getId());
        session.setAttribute(UNIVERSITIES_ATTR, userUniversities);

        final Job jobInfo = getJobInfo(user.getId());
        session.setAttribute(JOB_INFO_ATTR, jobInfo);

        req.getRequestDispatcher(PROFILE_EDIT_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private Job getJobInfo(long userId) {
        final Optional<Job> jobInfo = jobInfoService.get(userId);
        if (jobInfo.isPresent()) {
            return jobInfo.get();
        }

        return Job.builder().build();
    }

    private Contacts getContacts(long userId) {
        final Optional<Contacts> contacts = contactsService.getByUserId(userId);
        if (contacts.isPresent()) {
            return contacts.get();
        }

        return Contacts.builder().build();
    }

    private void setActiveTabAttribute(HttpServletRequest req) {
        final Map<String, Boolean> activeTab = new HashMap<>();
        activeTab.put("commonInfoTab", true);
        req.setAttribute(ACTIVE_TAB_ATTR, activeTab);
    }
}
