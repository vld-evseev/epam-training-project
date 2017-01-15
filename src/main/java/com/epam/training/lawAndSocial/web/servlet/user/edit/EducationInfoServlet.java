package com.epam.training.lawAndSocial.web.servlet.user.edit;

import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.model.education.EducationInfo;
import com.epam.training.lawAndSocial.model.education.impl.School;
import com.epam.training.lawAndSocial.model.education.impl.University;
import com.epam.training.lawAndSocial.service.model.EducationInfoService;
import com.epam.training.lawAndSocial.service.model.impl.educationInfo.annotations.SchoolInfo;
import com.epam.training.lawAndSocial.service.model.impl.educationInfo.annotations.UniverInfo;
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
    private final Gson gson;
    private final EducationInfoService<EducationInfo> schoolInfoService;
    private final EducationInfoService<EducationInfo> univerInfoService;

    @Inject
    public EducationInfoServlet(Gson gson,
                                @SchoolInfo EducationInfoService<EducationInfo> schoolInfoService,
                                @UniverInfo EducationInfoService<EducationInfo> univerInfoService) {
        this.gson = gson;
        this.schoolInfoService = schoolInfoService;
        this.univerInfoService = univerInfoService;
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

        final String schoolJsonData = "[" + params.get(JSON_SCHOOL_DATA_PARAM) + "]";
        final String universityData = "[" + params.get(JSON_UNIVERSITY_DATA_PARAM) + "]";
        final List<EducationInfo> schoolList = parseSchoolJson(schoolJsonData, validation);
        final List<EducationInfo> universityList = parseUniversityJson(universityData, validation);

        LOGGER.debug("-----------UNIVERS BEFORE UPDATE -----------------");
        for (EducationInfo educationInfo : universityList) {
            LOGGER.debug(educationInfo.toString());
        }
        LOGGER.debug("-------------------------------------------------");

        if (validation.isValid()) {
            updateSchoolInfo(currentUser.getId(), schoolList, validation);
            updateUniverInfo(currentUser.getId(), universityList, validation);
        }

        if (!validation.isValid()) {
            for (String msg : validation.messages()) {
                LOGGER.debug(msg);
            }
            req.setAttribute(VALIDATION_ATTR, validation);
            req.getRequestDispatcher(PROFILE_EDIT_JSP).forward(req, resp);
            return;
        }

        final List<EducationInfo> updatedSchoolList = schoolInfoService.getList(currentUser.getId());
        final List<EducationInfo> updatedUniversitiesList = univerInfoService.getList(currentUser.getId());

        LOGGER.debug("-----------UNIVERS AFTER UPDATE -----------------");
        for (EducationInfo educationInfo : updatedUniversitiesList) {
            LOGGER.debug(educationInfo.toString());
        }
        LOGGER.debug("-------------------------------------------------");

        session.setAttribute(SCHOOLS_ATTR, updatedSchoolList);
        session.setAttribute(UNIVERSITIES_ATTR, updatedUniversitiesList);
        req.getRequestDispatcher(PROFILE_EDIT_JSP).forward(req, resp);
    }

    private void setActiveTabAttribute(HttpServletRequest req) {
        final Map<String, Boolean> activeTab = new HashMap<>();
        activeTab.put("educationInfoTab", true);
        req.setAttribute(ACTIVE_TAB_ATTR, activeTab);
    }

    private List<EducationInfo> parseSchoolJson(String jsonData, FormValidation validation) {
        final List<EducationInfo> resultList;

        try {
            resultList = Arrays.asList(
                    gson.fromJson(jsonData, School[].class)
            );
        } catch (JsonSyntaxException ex) {
            LOGGER.error("Exception while parsing data from JSON: {}", ex.getMessage());
            LOGGER.error("Parsed JSON: {}", jsonData);
            validation.getErrors().put(
                    "PARSING_ERROR",
                    true
            );
            return Collections.emptyList();
        }
        return resultList;
    }

    private List<EducationInfo> parseUniversityJson(String jsonData, FormValidation validation) {
        final List<EducationInfo> resultList;

        try {
            resultList = Arrays.asList(
                    gson.fromJson(jsonData, University[].class)
            );
        } catch (JsonSyntaxException ex) {
            LOGGER.error("Exception while parsing data from JSON: {}", ex.getMessage());
            LOGGER.error("Parsed JSON: {}", jsonData);
            validation.getErrors().put(
                    "PARSING_ERROR",
                    true
            );
            return Collections.emptyList();
        }
        return resultList;
    }

    private void updateSchoolInfo(long userId, List<EducationInfo> schoolList, FormValidation validation) {
        final long result = schoolInfoService.update(userId, schoolList);
        if (result < 0) {
            validation.getErrors().put(
                    "INTERNAL_ERROR",
                    true
            );
        }
    }

    private void updateUniverInfo(long userId, List<EducationInfo> universityList, FormValidation validation) {
        final long result = univerInfoService.update(userId, universityList);
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
