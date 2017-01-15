package com.epam.training.lawAndSocial.service.model;

import java.util.List;
import java.util.Optional;

public interface EducationInfoService<T> {

    long update(long userId, List<T> subjectList);

    List<T> getList(long userId);

    Optional<T> get(String subjectName);

    long add(long userId, T subject);

    long delete(long userId, T subject);

    long update(long userId, T subject);

}
