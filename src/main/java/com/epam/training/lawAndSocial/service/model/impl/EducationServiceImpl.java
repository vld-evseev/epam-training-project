package com.epam.training.lawAndSocial.service.model.impl;

import com.epam.training.lawAndSocial.dao.SchoolDao;
import com.epam.training.lawAndSocial.model.education.School;
import com.epam.training.lawAndSocial.service.model.EducationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class EducationServiceImpl implements EducationService {

    private final static Logger LOGGER = LoggerFactory.getLogger(EducationServiceImpl.class);

    private final SchoolDao schoolDao;

    @Inject
    public EducationServiceImpl(SchoolDao schoolDao) {
        this.schoolDao = schoolDao;
    }

    @Override
    public long updateUserSchools(long userId, List<School> schoolList) {
        for (School school : schoolList) {
            if (!userAssociatedToSchool(userId, school)) {
                schoolDao.addUserToSchool(userId, school);
                LOGGER.debug("userId {} added to school {}", userId, school.toString());
            } else {
                schoolDao.updateUserSchool(userId, school);
                LOGGER.debug("userId {} updated with school {}", userId, school.toString());
            }
        }

        return 0;
    }

    @Override
    public List<School> getUserSchools(long userId) {
        return schoolDao.getByUserId(userId);
    }

    private boolean userAssociatedToSchool(long userId, School school) {
        final Optional<School> schoolOptional = schoolDao.getBySchoolName(school.getName());
        if (schoolOptional.isPresent()) {
            return school.getUserId() == userId;
        }

        return false;
    }
}
