package com.epam.training.lawAndSocial.web.servlet.user.edit;

import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.model.education.School;
import com.epam.training.lawAndSocial.model.education.University;
import com.epam.training.lawAndSocial.service.model.EducationService;
import com.epam.training.lawAndSocial.web.servlet.model.FormValidation;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
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
import java.util.*;

import static com.epam.training.lawAndSocial.utils.ServletParams.*;

@Singleton
public class EducationInfoServlet extends HttpServlet {

    private final static Logger LOGGER = LoggerFactory.getLogger(EducationInfoServlet.class);
    private final EducationService educationService;
    private final Gson gson;

    @Inject
    public EducationInfoServlet(EducationService educationService, Gson gson) {
        this.educationService = educationService;
        this.gson = gson;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setActiveTabAttribute(req);
        req.getRequestDispatcher(PROFILE_EDIT_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setActiveTabAttribute(req);

        final HttpSession session = req.getSession(true);
        final User currentUser = (User) session.getAttribute(USER_ATTR);

        final Map<String, String> params = collectParams(req);
        final FormValidation validation = new FormValidation();

        final List<School> schoolList = getSchoolList(params, validation);
        final List<University> universityList = getUniversityList(params, validation);

        if (validation.isValid()) {
            updateSchools(currentUser.getId(), schoolList, validation);
            updateUniversities(currentUser.getId(), universityList, validation);
        }

        if (!validation.isValid()) {
            for (String msg : validation.messages()) {
                LOGGER.debug(msg);
            }
            req.setAttribute(VALIDATION_ATTR, validation);
            req.getRequestDispatcher(PROFILE_EDIT_JSP).forward(req, resp);
            return;
        }

        final List<School> updatedSchoolList = educationService.getSchoolsByUserId(currentUser.getId());
        final List<University> updatedUniversitiesList = educationService.getUniversitiesByUserId(currentUser.getId());

        session.setAttribute(SCHOOLS_ATTR, updatedSchoolList);
        session.setAttribute(UNIVERSITIES_ATTR, updatedUniversitiesList);
        req.getRequestDispatcher(PROFILE_EDIT_JSP).forward(req, resp);
    }

    private void setActiveTabAttribute(HttpServletRequest req) {
        final Map<String, Boolean> activeTab = new HashMap<>();
        activeTab.put("educationInfoTab", true);
        req.setAttribute(ACTIVE_TAB_ATTR, activeTab);
    }

    private List<School> getSchoolList(Map<String, String> params, FormValidation validation) {
        final String schoolData = "[" + params.get(JSON_SCHOOL_DATA_PARAM) + "]";
        final List<School> schoolList;

        try {
            schoolList = Arrays.asList(
                    gson.fromJson(schoolData, School[].class)
            );
        } catch (JsonSyntaxException ex) {
            LOGGER.error("Exception while parsing school list: {}", ex.getMessage());
            LOGGER.error("Param values: {}", schoolData);
            validation.getErrors().put(
                    "PARSING_ERROR",
                    true
            );
            return Collections.emptyList();
        }

        LOGGER.debug("Schools:");
        for (School school : schoolList) {
            LOGGER.debug(school.toString());
        }
        return schoolList;
    }

    private List<University> getUniversityList(Map<String, String> params, FormValidation validation) {
        final String universityData = "[" + params.get(JSON_UNIVERSITY_DATA_PARAM) + "]";
        final List<University> universityList;

        try {
            universityList = Arrays.asList(
                    gson.fromJson(universityData, University[].class)
            );
        } catch (JsonSyntaxException ex) {
            LOGGER.error("Exception while parsing university list: {}", ex.getMessage());
            LOGGER.error("Param values: {}", universityData);
            validation.getErrors().put(
                    "PARSING_ERROR",
                    true
            );
            return Collections.emptyList();
        }

        LOGGER.debug("Universities:");
        for (University university : universityList) {
            LOGGER.debug(university.toString());
        }
        return universityList;
    }

    private void updateSchools(long userId, List<School> schoolList, FormValidation validation) {
        final long result = educationService.updateUserSchools(userId, schoolList);
        if (result < 0) {
            validation.getErrors().put(
                    "INTERNAL_ERROR",
                    true
            );
        }
    }

    private void updateUniversities(long userId, List<University> universityList, FormValidation validation) {
        final long result = educationService.updateUserUniversities(userId, universityList);
        if (result < 0) {
            validation.getErrors().put(
                    "INTERNAL_ERROR",
                    true
            );
        }
    }

    private Map<String, String> collectParams(HttpServletRequest req) {
        final Map<String, String> params = new HashMap<>();
        params.put(JSON_SCHOOL_DATA_PARAM, req.getParameter(JSON_SCHOOL_DATA_PARAM));
        params.put(JSON_UNIVERSITY_DATA_PARAM, req.getParameter(JSON_UNIVERSITY_DATA_PARAM));
        return params;
    }
}
