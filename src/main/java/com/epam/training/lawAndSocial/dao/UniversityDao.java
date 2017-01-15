package com.epam.training.lawAndSocial.dao;

import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.model.education.impl.University;

import java.util.List;
import java.util.Optional;

public interface UniversityDao {

    List<University> getByUserId(long userId) throws PersistException;

    Optional<University> getByUniversityId(long id) throws PersistException;

    Optional<University> getByUniversityName(String schoolName) throws PersistException;

    long addUserToUniversity(long userId, University school) throws PersistException;

    long deleteUserFromUniversity(long userId, University school) throws PersistException;

    long updateUniversityByUserId(long userId, University school) throws PersistException;

}
