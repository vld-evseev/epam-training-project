package com.epam.training.lawAndSocial.dao;

import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.model.education.EducationInfo;

import java.util.List;
import java.util.Optional;

public interface EducationInfoDao<T extends EducationInfo> {

    List<T> getByUserId(long userId) throws PersistException;

    Optional<T> getBySubjectId(long id) throws PersistException;

    Optional<T> getBySubjectName(String subjectName) throws PersistException;

    long addUserToSubject(long userId, T subject) throws PersistException;

    long deleteUserFromSubject(long userId, T subject) throws PersistException;

    long updateSubjectByUserId(long userId, T subject) throws PersistException;

}
