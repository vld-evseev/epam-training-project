package com.epam.training.lawAndSocial.service.impl;

import com.epam.training.lawAndSocial.service.ValidationService;
import com.epam.training.lawAndSocial.web.servlet.model.FieldValidation;
import com.epam.training.lawAndSocial.web.servlet.model.FormValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

public class ValidationServiceImpl implements ValidationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationServiceImpl.class);

    @Override
    public FormValidation verify(Map<String, String> params) {
        final FormValidation validation = new FormValidation();

        final Set<Map.Entry<String, String>> entries = params.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            final String paramName = entry.getKey();
            final String paramValue = entry.getValue();

            if (!paramExists(paramValue)) {
                validation.getFields().put(
                        paramName,
                        FieldValidation.builder().isEmptyField(true).build()
                );
            }
        }

        return validation;
    }

    private boolean paramExists(String param) {
        return param != null && !param.isEmpty();
    }
}
