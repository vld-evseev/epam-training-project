package com.epam.training.lawAndSocial.dao;

import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.model.Job;

import java.util.Optional;

public interface JobInfoDao {

    long add(long userId, Job job) throws PersistException;

    Optional<Job> get(long userId) throws PersistException;

    long update(long userId, Job job) throws PersistException;

    long delete(long id, long userId) throws PersistException;

}
