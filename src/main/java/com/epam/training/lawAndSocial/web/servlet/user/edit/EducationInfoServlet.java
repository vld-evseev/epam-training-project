package com.epam.training.lawAndSocial.web.servlet.user.edit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Singleton
public class EducationInfoServlet extends HttpServlet {

    private final static Logger LOGGER = LoggerFactory.getLogger(EducationInfoServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Map<String, String[]> params = collectParams(req);

        final Set<Map.Entry<String, String[]>> entries = params.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            LOGGER.debug(entry.getKey() + " : " + Arrays.toString(entry.getValue()));
        }


    }

    private Map<String, String[]> collectParams(HttpServletRequest req) {
        final Map<String, String[]> params = new HashMap<>();
        params.put("schoolName[]", req.getParameterValues("schoolName[]"));
        params.put("schoolCountry[]", req.getParameterValues("schoolCountry[]"));
        params.put("schoolCity[]", req.getParameterValues("schoolCity[]"));
        params.put("schoolYearFrom[]", req.getParameterValues("schoolYearFrom[]"));
        params.put("schoolYearTo[]", req.getParameterValues("schoolYearTo[]"));
        return params;
    }
}
