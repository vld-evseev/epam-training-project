package com.epam.training.lawAndSocial.service.model.impl;

import com.epam.training.lawAndSocial.dao.JobInfoDao;
import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.model.Job;
import com.epam.training.lawAndSocial.service.model.JobInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Optional;

public class JobInfoServiceImpl implements JobInfoService {

    private final static Logger LOGGER = LoggerFactory.getLogger(JobInfoServiceImpl.class);
    private final JobInfoDao jobInfoDao;

    @Inject
    public JobInfoServiceImpl(JobInfoDao jobInfoDao) {
        this.jobInfoDao = jobInfoDao;
    }

    @Override
    public long add(long userId, Job job) {
        try {
            return jobInfoDao.add(userId, job);
        } catch (PersistException e) {
            LOGGER.error("Adding job info caused an exception: {}", e.getMessage());
            LOGGER.error("User id: {}", userId);
            LOGGER.error("Job info: {}", job.toString());
            return -1;
        }
    }

    @Override
    public Optional<Job> get(long userId) {
        try {
            return jobInfoDao.get(userId);
        } catch (PersistException e) {
            LOGGER.error("Getting job info by userId caused an exception: {}", e.getMessage());
            LOGGER.error("userId: {}", userId);
            return Optional.empty();
        }
    }

    @Override
    public long update(long userId, Job job) {
        try {
            return jobInfoDao.update(userId, job);
        } catch (PersistException e) {
            LOGGER.error("Updating job info by userId caused an exception: {}", e.getMessage());
            LOGGER.error("Job info: {}", job.toString());
            LOGGER.error("UserId: {}", userId);
            return -1;
        }
    }

    @Override
    public long delete(long userId, long jobId) {
        try {
            return jobInfoDao.delete(userId, jobId);
        } catch (PersistException e) {
            LOGGER.error("Deleting job info by userId caused an exception: {}", e.getMessage());
            LOGGER.error("Job info id: {}", jobId);
            LOGGER.error("UserId: {}", userId);
            return -1;
        }
    }

    @Override
    public long refresh(long userId, Job job) {
        final Optional<Job> currentJobInfoOptional = get(userId);
        if (currentJobInfoOptional.isPresent()) {
            final Job currentJobInfo = currentJobInfoOptional.get();
            if (currentJobInfo.getOrganization() == null || currentJobInfo.getOrganization().isEmpty()) {
                return delete(userId, job.getId());
            } else {
                return update(userId, job);
            }
        } else {
            if (job.getOrganization() != null && !job.getOrganization().isEmpty()) {
                return add(userId, job);
            } else {
                return -1;
            }
        }
    }
}
