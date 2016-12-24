package com.epam.training.lawAndSocial.dao;

import com.epam.training.lawAndSocial.model.education.School;

import java.util.List;
import java.util.Optional;

public interface SchoolDao {

    List<School> getByUserId(long userId);

    Optional<School> getBySchoolId(long id);

    Optional<School> getBySchoolName(String schoolName);

    long addUserToSchool(long userId, School school);

    long deleteUserFromSchool(School school);

    long updateUserSchool(long userId, School school);

}
