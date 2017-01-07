package com.epam.training.lawAndSocial.web.servlet.user.edit;

import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.model.education.School;
import com.epam.training.lawAndSocial.model.education.University;
import com.epam.training.lawAndSocial.service.ValidationService;
import com.epam.training.lawAndSocial.service.model.EducationService;
import com.epam.training.lawAndSocial.utils.CheckUtils;
import com.epam.training.lawAndSocial.utils.DateValidator;
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
import java.util.*;

import static com.epam.training.lawAndSocial.utils.ServletParams.*;

@Singleton
public class EducationInfoServlet extends HttpServlet {

    private final static Logger LOGGER = LoggerFactory.getLogger(EducationInfoServlet.class);
    private final EducationService educationService;
    private final ValidationService validationService;

    @Inject
    public EducationInfoServlet(EducationService educationService, ValidationService validationService) {
        this.educationService = educationService;
        this.validationService = validationService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Map<String, Boolean> activeTab = new HashMap<>();
        activeTab.put("educationInfoTab", true);
        req.setAttribute(ACTIVE_TAB_ATTR, activeTab);

        final User currentUser = (User) req.getSession(true).getAttribute(USER_ATTR);

        if (currentUser == null) {
            LOGGER.error("user not exists in session");
            resp.sendRedirect(req.getContextPath() + "/user/edit/common");
            return;
        }

        final Map<String, String[]> params = collectParams(req);
        final FormValidation validation = new FormValidation();

        final Set<Map.Entry<String, String[]>> entries = params.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            LOGGER.debug(entry.getKey() + " : " + Arrays.toString(entry.getValue()));
        }

        final List<School> schoolList = getSchoolList(params, validation);
        final List<University> universitiesList = getUniversitiesList(params, validation);

        for (String msg : validation.messages()) {
            LOGGER.debug(msg);
        }

        if (validation.isValid()) {
            LOGGER.debug(Arrays.toString(schoolList.toArray()));
            updateSchools(currentUser.getId(), schoolList, validation);
            updateUniversities(currentUser.getId(), universitiesList, validation);
        }

        if (!validation.isValid()) {
            req.setAttribute(VALIDATION_ATTR, validation);
            req.getRequestDispatcher(PROFILE_EDIT_JSP).forward(req, resp);
            return;
        }

        final List<School> updatedSchoolList = educationService.getUserSchools(currentUser.getId());
        final List<University> updatedUniversitiesList = educationService.getUserUniversities(currentUser.getId());

        req.getSession(true).setAttribute(SCHOOLS_ATTR, updatedSchoolList);
        req.getSession(true).setAttribute(UNIVERSITIES_ATTR, updatedUniversitiesList);
        req.getRequestDispatcher(PROFILE_EDIT_JSP).forward(req, resp);
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

    static List<School> getSchoolList(Map<String, String[]> params, FormValidation validation) {
        final String[] nameParams = params.get(SCHOOL_NAME_PARAM);
        final String[] idParams = params.get(SCHOOL_ID_PARAM);
        final String[] userIdParams = params.get(SCHOOL_USER_ID_PARAM);
        final String[] yearFromParams = params.get(SCHOOL_YEAR_FROM_PARAM);
        final String[] yearToParams = params.get(SCHOOL_YEAR_TO_PARAM);
        final String[] countryParams = params.get(SCHOOL_COUNTRY_PARAM);
        final String[] cityParams = params.get(SCHOOL_CITY_PARAM);
        final List<School> schoolList = new LinkedList<>();
        for (int i = 0; i < nameParams.length; i++) {
            School school = School.builder().build();
            final String nameParam = validateParam(nameParams[i]);
            if (nameParam != null && !nameParam.isEmpty()) {
                final String idParam = validateIntParam(
                        getParam(i, idParams.length, idParams)
                );
                final String userIdParam = validateIntParam(
                        getParam(i, userIdParams.length, userIdParams)
                );
                final String dateFromParam = validateIntParam(
                        getParam(i, yearFromParams.length, yearFromParams)
                );
                final String dateToParam = validateIntParam(
                        getParam(i, yearToParams.length, yearToParams)
                );
                final String countryParam = validateParam(
                        getParam(i, countryParams.length, countryParams)
                );
                final String cityParam = validateParam(
                        getParam(i, cityParams.length, cityParams)
                );

                final String dateFrom = DateValidator.parseDate(
                        dateFromParam,
                        DateValidator.Pattern.YYYY,
                        validation
                );
                final String dateTo = DateValidator.parseDate(
                        dateToParam,
                        DateValidator.Pattern.YYYY,
                        validation
                );
                school = School.builder()
                        .id(Long.parseLong(idParam))
                        .userId(Integer.parseInt(userIdParam))
                        .name(nameParam)
                        .country(countryParam)
                        .city(cityParam)
                        .yearsFrom(Integer.parseInt(dateFrom))
                        .yearsTo(Integer.parseInt(dateTo)).build();
            }
            schoolList.add(school);
        }
        return schoolList;
    }

    static List<University> getUniversitiesList(Map<String, String[]> params, FormValidation validation) {
        final String[] nameParams = params.get(UNIVERSITY_NAME_PARAM);
        final String[] idParams = params.get(UNIVERSITY_ID_PARAM);
        final String[] userIdParams = params.get(UNIVERSITY_USER_ID_PARAM);
        final String[] yearFromParams = params.get(UNIVERSITY_YEAR_FROM_PARAM);
        final String[] yearToParams = params.get(UNIVERSITY_YEAR_TO_PARAM);
        final String[] countryParams = params.get(UNIVERSITY_COUNTRY_PARAM);
        final String[] cityParams = params.get(UNIVERSITY_CITY_PARAM);
        final List<University> universitiesList = new LinkedList<>();
        for (int i = 0; i < nameParams.length; i++) {
            University university = University.builder().build();
            final String nameParam = validateParam(nameParams[i]);
            if (nameParam != null && !nameParam.isEmpty()) {
                final String idParam = validateIntParam(
                        getParam(i, idParams.length, idParams)
                );
                final String userIdParam = validateIntParam(
                        getParam(i, userIdParams.length, userIdParams)
                );
                final String dateFromParam = validateIntParam(
                        getParam(i, yearFromParams.length, yearFromParams)
                );
                final String dateToParam = validateIntParam(
                        getParam(i, yearToParams.length, yearToParams)
                );
                final String countryParam = validateParam(
                        getParam(i, countryParams.length, countryParams)
                );
                final String cityParam = validateParam(
                        getParam(i, cityParams.length, cityParams)
                );

                final String dateFrom = DateValidator.parseDate(
                        dateFromParam,
                        DateValidator.Pattern.YYYY,
                        validation
                );
                final String dateTo = DateValidator.parseDate(
                        dateToParam,
                        DateValidator.Pattern.YYYY,
                        validation
                );
                university = University.builder()
                        .id(Long.parseLong(idParam))
                        .userId(Integer.parseInt(userIdParam))
                        .name(nameParam)
                        .country(countryParam)
                        .city(cityParam)
                        .yearsFrom(Integer.parseInt(dateFrom))
                        .yearsTo(Integer.parseInt(dateTo)).build();
            }
            universitiesList.add(university);
        }
        return universitiesList;
    }

    static String getParam(int index, int position, String[] values) {
        LOGGER.debug("params:: index: {}, position: {}, values: {}", index, position, Arrays.toString(values));
        if (values.length != 0 && index < position) {
            return values[index];
        }
        return "";
    }

    static String validateParam(String value) {
        if (value == null || value.isEmpty()) {
            return "";
        }

        return value;
    }

    static String validateIntParam(String value) {
        if (value == null || value.isEmpty() || !CheckUtils.isNumeric(value)) {
            return "0";
        }

        return value;
    }

    private Map<String, String[]> collectParams(HttpServletRequest req) {
        final Map<String, String[]> params = new HashMap<>();
        params.put(UNIVERSITY_ID_PARAM, req.getParameterValues(UNIVERSITY_ID_PARAM));
        params.put(UNIVERSITY_USER_ID_PARAM, req.getParameterValues(UNIVERSITY_USER_ID_PARAM));
        params.put(UNIVERSITY_NAME_PARAM, req.getParameterValues(UNIVERSITY_NAME_PARAM));
        params.put(UNIVERSITY_COUNTRY_PARAM, req.getParameterValues(UNIVERSITY_COUNTRY_PARAM));
        params.put(UNIVERSITY_CITY_PARAM, req.getParameterValues(UNIVERSITY_CITY_PARAM));
        params.put(UNIVERSITY_YEAR_FROM_PARAM, req.getParameterValues(UNIVERSITY_YEAR_FROM_PARAM));
        params.put(UNIVERSITY_YEAR_TO_PARAM, req.getParameterValues(UNIVERSITY_YEAR_TO_PARAM));

        params.put(SCHOOL_ID_PARAM, req.getParameterValues(SCHOOL_ID_PARAM));
        params.put(SCHOOL_USER_ID_PARAM, req.getParameterValues(SCHOOL_USER_ID_PARAM));
        params.put(SCHOOL_NAME_PARAM, req.getParameterValues(SCHOOL_NAME_PARAM));
        params.put(SCHOOL_COUNTRY_PARAM, req.getParameterValues(SCHOOL_COUNTRY_PARAM));
        params.put(SCHOOL_CITY_PARAM, req.getParameterValues(SCHOOL_CITY_PARAM));
        params.put(SCHOOL_YEAR_FROM_PARAM, req.getParameterValues(SCHOOL_YEAR_FROM_PARAM));
        params.put(SCHOOL_YEAR_TO_PARAM, req.getParameterValues(SCHOOL_YEAR_TO_PARAM));
        return params;
    }

    private Map<String, String> collectVerifiedParams(HttpServletRequest req) {
        final Map<String, String> params = new HashMap<>();
        params.put(SCHOOL_NAME_PARAM, req.getParameter(SCHOOL_NAME_PARAM));
        return params;
    }
}
