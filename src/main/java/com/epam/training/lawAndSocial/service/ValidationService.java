package com.epam.training.lawAndSocial.service;

import com.epam.training.lawAndSocial.web.servlet.model.FormValidation;

import java.util.Map;

public interface ValidationService {

    FormValidation verify(Map<String, String> params);

}
