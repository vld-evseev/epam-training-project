package com.epam.training.lawAndSocial.service.model;

import com.epam.training.lawAndSocial.model.education.School;
import com.epam.training.lawAndSocial.model.education.University;

import java.util.List;

public interface EducationService {

    long updateUserSchools(long userId, List<School> schoolList);

    List<School> getUserSchools(long userId);

    long updateUserUniversities(long userId, List<University> universityList);

    List<University> getUserUniversities(long userId);


}
