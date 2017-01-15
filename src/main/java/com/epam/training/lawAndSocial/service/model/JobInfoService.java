package com.epam.training.lawAndSocial.service.model;

import com.epam.training.lawAndSocial.model.Job;

import java.util.Optional;

public interface JobInfoService {

    long add(long userId, Job job);

    Optional<Job> get(long userId);

    long update(long userId, Job job);

    long delete(long userId, long jobId);

    long refresh(long userId, Job job);

}
