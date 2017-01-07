package com.epam.training.lawAndSocial.service.model.impl;

import com.epam.training.lawAndSocial.dao.SchoolDao;
import com.epam.training.lawAndSocial.dao.UniversityDao;
import com.epam.training.lawAndSocial.model.education.School;
import com.epam.training.lawAndSocial.model.education.University;
import com.epam.training.lawAndSocial.service.model.EducationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EducationServiceImpl implements EducationService {

    private final static Logger LOGGER = LoggerFactory.getLogger(EducationServiceImpl.class);

    private final SchoolDao schoolDao;
    private final UniversityDao universityDao;

    @Inject
    public EducationServiceImpl(SchoolDao schoolDao, UniversityDao universityDao) {
        this.schoolDao = schoolDao;
        this.universityDao = universityDao;
    }

    //TODO: исправить одинаковые названия

    @Override
    public long updateUserSchools(long userId, List<School> schoolList) {
        long result = -1;

        LOGGER.debug("Schools to process");
        for (School school : schoolList) {
            LOGGER.debug(school.toString());
        }

        for (School school : schoolList) {
            if (school.getName() != null) {
                if (!userAssociatedToSchool(userId, school)) {
                    result = schoolDao.addUserToSchool(userId, school);
                    LOGGER.debug("userId {} added school info {}", userId, school.toString());
                } else {
                    if (school.getName().isEmpty()) {
                        result = schoolDao.deleteUserFromSchool(userId, school);
                        LOGGER.debug("userId {} deleted school info {} - by name", userId, school.toString());
                    } else {
                        result = schoolDao.updateSchoolByUserId(userId, school);
                        LOGGER.debug("userId {} updated school info {}", userId, school.toString());
                    }
                }
            }
        }

        final List<School> schoolsToDelete = findSchoolsToDelete(userId, schoolList);
        LOGGER.debug("Schools to delete");
        for (School school : schoolsToDelete) {
            LOGGER.debug(school.toString());
        }
        for (School school : schoolsToDelete) {
            result = schoolDao.deleteUserFromSchool(userId, school);
            LOGGER.debug("userId {} deleted school info {} - by intersection", userId, school.toString());
        }

        return result;
    }

    @Override
    public List<School> getUserSchools(long userId) {
        return schoolDao.getByUserId(userId);
    }

    @Override
    public long updateUserUniversities(long userId, List<University> universityList) {
        long result = -1;

        LOGGER.debug("Univs to process");
        for (University university : universityList) {
            LOGGER.debug(university.toString());
        }

        for (University university : universityList) {
            if (university.getName() != null) {
                if (!userAssociatedToUniversity(userId, university)) {
                    result = universityDao.addUserToUniversity(userId, university);
                    LOGGER.debug("userId {} added university info {}", userId, university.toString());
                } else {
                    if (university.getName().isEmpty()) {
                        result = universityDao.deleteUserFromUniversity(userId, university);
                        LOGGER.debug("userId {} deleted university info {} - by name", userId, university.toString());
                    } else {
                        result = universityDao.updateUniversityByUserId(userId, university);
                        LOGGER.debug("userId {} updated university info {}", userId, university.toString());
                    }
                }
            }
        }

        final List<University> universitiesToDelete = findUniversitiesToDelete(userId, universityList);
        LOGGER.debug("Universities to delete");
        for (University university : universitiesToDelete) {
            LOGGER.debug(university.toString());
        }
        for (University university : universitiesToDelete) {
            result = universityDao.deleteUserFromUniversity(userId, university);
            LOGGER.debug("userId {} deleted university info {} - by intersection", userId, university.toString());
        }

        return result;
    }

    @Override
    public List<University> getUserUniversities(long userId) {
        return universityDao.getByUserId(userId);
    }

    private List<School> findSchoolsToDelete(long userId, List<School> updatedSchoolList) {
        final List<School> persistentSchoolList = schoolDao.getByUserId(userId);
        LOGGER.debug("persistent schools: {}", Arrays.toString(persistentSchoolList.toArray()));
        LOGGER.debug("updatedSchoolList: {}", Arrays.toString(updatedSchoolList.toArray()));
        persistentSchoolList.removeAll(updatedSchoolList);
        return persistentSchoolList;
    }

    private List<University> findUniversitiesToDelete(long userId, List<University> updatedUniversityList) {
        final List<University> persistentUniversityList = universityDao.getByUserId(userId);
        LOGGER.debug("persistent universities: {}", Arrays.toString(persistentUniversityList.toArray()));
        LOGGER.debug("updatedUniversityList: {}", Arrays.toString(updatedUniversityList.toArray()));
        persistentUniversityList.removeAll(updatedUniversityList);
        return persistentUniversityList;
    }

    private boolean userAssociatedToUniversity(long userId, University university) {
        final Optional<University> universityOptional = universityDao.getByUniversityName(university.getName());
        if (universityOptional.isPresent()) {
            return university.getUserId() == userId;
        }

        return false;
    }

    private boolean userAssociatedToSchool(long userId, School school) {
        final Optional<School> schoolOptional = schoolDao.getBySchoolName(school.getName());
        if (schoolOptional.isPresent()) {
            return school.getUserId() == userId;
        }

        return false;
    }
}
