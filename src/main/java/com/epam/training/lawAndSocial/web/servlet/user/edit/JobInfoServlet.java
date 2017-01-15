package com.epam.training.lawAndSocial.web.servlet.user.edit;

import com.epam.training.lawAndSocial.model.Job;
import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.service.model.JobInfoService;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.epam.training.lawAndSocial.utils.ServletParams.*;

@Singleton
public class JobInfoServlet extends HttpServlet {

    private final static Logger LOGGER = LoggerFactory.getLogger(JobInfoServlet.class);

    private final JobInfoService jobInfoService;
    private final Gson gson;

    @Inject
    public JobInfoServlet(JobInfoService jobInfoService, Gson gson) {
        this.jobInfoService = jobInfoService;
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

        LOGGER.debug("USER: ", currentUser.toString());
        final Map<String, String> params = collectParams(req);
        final FormValidation validation = new FormValidation();
        final String jobInfoJsonData = params.get(JSON_JOB_DATA_PARAM);

        LOGGER.debug("JSON DATA: {}", req.getParameter(JSON_JOB_DATA_PARAM));
        LOGGER.debug("organization: {}", req.getParameter("organization"));

        final Job jobInfo = parseJobInfoJson(jobInfoJsonData, validation);

        LOGGER.debug("JOB: {}", jobInfo.toString());

        if (validation.isValid()) {
            updateJobInfo(currentUser.getId(), jobInfo, validation);
        }

        if (!validation.isValid()) {
            for (String msg : validation.messages()) {
                LOGGER.debug(msg);
            }
            req.setAttribute(VALIDATION_ATTR, validation);
            req.getRequestDispatcher(PROFILE_EDIT_JSP).forward(req, resp);
            return;
        }

        final Optional<Job> updatedJobInfoOptional = jobInfoService.get(currentUser.getId());
        if (updatedJobInfoOptional.isPresent()) {
            session.setAttribute(JOB_INFO_ATTR, updatedJobInfoOptional.get());
        } else {
            session.setAttribute(JOB_INFO_ATTR, Job.builder().build());
        }

        req.getRequestDispatcher(PROFILE_EDIT_JSP).forward(req, resp);
    }

    private void updateJobInfo(long userId, Job jobInfo, FormValidation validation) {
        final long result = jobInfoService.refresh(userId, jobInfo);
        if (result < 0) {
            validation.getErrors().put(
                    "INTERNAL_ERROR",
                    true
            );
        }
    }

    private Job parseJobInfoJson(String jsonData, FormValidation validation) {
        try {
            return gson.fromJson(jsonData, Job.class);
        } catch (JsonSyntaxException ex) {
            LOGGER.error("Exception while parsing data from JSON: {}", ex.getMessage());
            LOGGER.error("Parsed JSON: {}", jsonData);
            validation.getErrors().put(
                    "PARSING_ERROR",
                    true
            );
            return Job.builder().build();
        }
    }

    private Map<String, String> collectParams(HttpServletRequest req) {
        final Map<String, String> params = new HashMap<>();
        params.put(JSON_JOB_DATA_PARAM, req.getParameter(JSON_JOB_DATA_PARAM));
        return params;
    }

    private void setActiveTabAttribute(HttpServletRequest req) {
        final Map<String, Boolean> activeTab = new HashMap<>();
        activeTab.put("jobInfoTab", true);
        req.setAttribute(ACTIVE_TAB_ATTR, activeTab);
    }
}
