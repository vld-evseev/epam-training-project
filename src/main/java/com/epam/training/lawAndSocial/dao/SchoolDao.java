package com.epam.training.lawAndSocial.dao;

import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.model.education.impl.School;

import java.util.List;
import java.util.Optional;

public interface SchoolDao {

    List<School> getByUserId(long userId) throws PersistException;

    Optional<School> getBySchoolId(long id) throws PersistException;

    Optional<School> getBySchoolName(String schoolName) throws PersistException;

    long addUserToSchool(long userId, School school) throws PersistException;

    long deleteUserFromSchool(long userId, School school) throws PersistException;

    long updateSchoolByUserId(long userId, School school) throws PersistException;

}
