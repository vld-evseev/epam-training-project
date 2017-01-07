package com.epam.training.lawAndSocial.dao;

import com.epam.training.lawAndSocial.model.education.University;

import java.util.List;
import java.util.Optional;

public interface UniversityDao {

    List<University> getByUserId(long userId);

    Optional<University> getByUniversityId(long id);

    Optional<University> getByUniversityName(String schoolName);

    long addUserToUniversity(long userId, University school);

    long deleteUserFromUniversity(long userId, University school);

    long updateUniversityByUserId(long userId, University school);

}
