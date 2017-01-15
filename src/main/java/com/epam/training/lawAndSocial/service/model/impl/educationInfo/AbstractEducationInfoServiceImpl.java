package com.epam.training.lawAndSocial.service.model.impl.educationInfo;

import com.epam.training.lawAndSocial.dao.EducationInfoDao;
import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.model.education.EducationInfo;
import com.epam.training.lawAndSocial.service.model.EducationInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

abstract class AbstractEducationInfoServiceImpl<T extends EducationInfo> implements EducationInfoService<T> {

    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractEducationInfoServiceImpl.class);
    private final EducationInfoDao<T> educationInfoDao;

    @Inject
    protected AbstractEducationInfoServiceImpl(EducationInfoDao<T> educationInfoDao) {
        this.educationInfoDao = educationInfoDao;
    }

    @Override
    public long update(long userId, List<T> subjectList) {
        long result = -1;

        LOGGER.debug("Subjects to process");
        for (T subject : subjectList) {
            LOGGER.debug(subject.toString());
        }

        for (T subject : subjectList) {
            if (subject.getName() != null) {
                if (!userAssociatedToSubject(userId, subject)) {
                    result = add(userId, subject);
                    LOGGER.debug("userId {} added subject educationInfo {}", userId, subject.toString());
                } else {
                    if (subject.getName().isEmpty()) {
                        result = delete(userId, subject);
                        LOGGER.debug("userId {} deleted subject educationInfo {} - by name", userId, subject.toString());
                    } else {
                        result = update(userId, subject);
                        LOGGER.debug("userId {} updated subject educationInfo {}", userId, subject.toString());
                    }
                }
            }
        }

        final List<T> subjectsToDelete = findSubjectToDelete(userId, subjectList);
        LOGGER.debug("Subjects to delete");
        for (T subject : subjectsToDelete) {
            LOGGER.debug(subject.toString());
        }
        for (T subject : subjectsToDelete) {
            result = delete(userId, subject);
            LOGGER.debug("userId {} deleted subject educationInfo {} - by intersection", userId, subject.toString());
        }

        return result;
    }

    @Override
    public long update(long userId, T subject) {
        try {
            return educationInfoDao.updateSubjectByUserId(userId, subject);
        } catch (PersistException e) {
            LOGGER.error("Updating user subject caused an exception: {}", e.getMessage());
            LOGGER.error("Subject: {}", subject.toString());
            LOGGER.error("User id: {}", userId);
            return -1;
        }
    }

    @Override
    public List<T> getList(long userId) {
        try {
            return educationInfoDao.getByUserId(userId);
        } catch (PersistException e) {
            LOGGER.error("Getting subject by userName caused an exception: {}", e.getMessage());
            LOGGER.error("UserId: {}", userId);
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<T> get(String subjectName) {
        try {
            return educationInfoDao.getBySubjectName(subjectName);
        } catch (PersistException e) {
            LOGGER.error("Getting subject by name caused an exception: {}", e.getMessage());
            LOGGER.error("Subject name: {}", subjectName);
            return Optional.empty();
        }
    }

    @Override
    public long add(long userId, T subject) {
        try {
            return educationInfoDao.addUserToSubject(userId, subject);
        } catch (PersistException e) {
            LOGGER.error("Adding subject caused an exception: {}", e.getMessage());
            LOGGER.error("Subject: {}", subject.toString());
            return -1;
        }
    }

    @Override
    public long delete(long userId, T subject) {
        try {
            return educationInfoDao.deleteUserFromSubject(userId, subject);
        } catch (PersistException e) {
            LOGGER.error("Deleting subject by user_id caused an exception: {}", e.getMessage());
            LOGGER.error("Subject: {}", subject.toString());
            return -1;
        }
    }

    protected boolean userAssociatedToSubject(long userId, T subject) {
        final Optional<T> subjectOptional = get(subject.getName());
        if (subjectOptional.isPresent()) {
            return subject.getUserId() == userId;
        }

        return false;
    }

    protected List<T> findSubjectToDelete(long userId, List<T> updatedSubjectList) {
        final List<T> persistentSubjectList = getList(userId);
        LOGGER.debug("persistent subjects: {}", Arrays.toString(persistentSubjectList.toArray()));
        LOGGER.debug("updated subject list: {}", Arrays.toString(updatedSubjectList.toArray()));
        persistentSubjectList.removeAll(updatedSubjectList);
        return persistentSubjectList;
    }

}
