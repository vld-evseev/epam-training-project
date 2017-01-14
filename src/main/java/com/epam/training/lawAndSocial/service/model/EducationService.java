package com.epam.training.lawAndSocial.service.model;

import com.epam.training.lawAndSocial.model.education.School;
import com.epam.training.lawAndSocial.model.education.University;

import java.util.List;
import java.util.Optional;

public interface EducationService {

    long updateUserSchools(long userId, List<School> schoolList);

    List<School> getSchoolsByUserId(long userId);

    Optional<School> getSchoolByName(String schoolName);

    long addUserToSchool(long userId, School school);

    long deleteUserFromSchool(long userId, School school);

    long updateSchoolByUserId(long userId, School school);

    long updateUserUniversities(long userId, List<University> universityList);

    List<University> getUniversitiesByUserId(long userId);

    Optional<University> getByUniversityName(String universityName);

    long addUserToUniversity(long userId, University university);

}
