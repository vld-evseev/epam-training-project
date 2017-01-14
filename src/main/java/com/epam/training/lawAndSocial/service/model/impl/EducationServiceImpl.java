package com.epam.training.lawAndSocial.service.model.impl;

import com.epam.training.lawAndSocial.dao.SchoolDao;
import com.epam.training.lawAndSocial.dao.UniversityDao;
import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.model.education.School;
import com.epam.training.lawAndSocial.model.education.University;
import com.epam.training.lawAndSocial.service.model.EducationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;
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
                    result = addUserToSchool(userId, school);
                    LOGGER.debug("userId {} added school info {}", userId, school.toString());
                } else {
                    if (school.getName().isEmpty()) {
                        result = deleteUserFromSchool(userId, school);
                        LOGGER.debug("userId {} deleted school info {} - by name", userId, school.toString());
                    } else {
                        result = updateSchoolByUserId(userId, school);
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
            result = deleteUserFromSchool(userId, school);
            LOGGER.debug("userId {} deleted school info {} - by intersection", userId, school.toString());
        }

        return result;
    }

    public long updateSchoolByUserId(long userId, School school) {
        try {
            return schoolDao.updateSchoolByUserId(userId, school);
        } catch (PersistException e) {
            LOGGER.error("Updating user school caused an exception: {}", e.getMessage());
            LOGGER.error("School: {}", school.toString());
            LOGGER.error("User id: {}", userId);
            return -1;
        }
    }

    public long deleteUserFromSchool(long userId, School school) {
        try {
            return schoolDao.deleteUserFromSchool(userId, school);
        } catch (PersistException e) {
            LOGGER.error("Deleting school by user_id caused an exception: {}", e.getMessage());
            LOGGER.error("School: {}", school.toString());
            return -1;
        }
    }

    public long addUserToSchool(long userId, School school) {
        try {
            return schoolDao.addUserToSchool(userId, school);
        } catch (PersistException e) {
            LOGGER.error("Adding school caused an exception: {}", e.getMessage());
            LOGGER.error("School: {}", school.toString());
            return -1;
        }
    }

    @Override
    public List<School> getSchoolsByUserId(long userId) {
        try {
            return schoolDao.getByUserId(userId);
        } catch (PersistException e) {
            LOGGER.error("Getting school by userName caused an exception: {}", e.getMessage());
            LOGGER.error("UserId: {}", userId);
            return Collections.emptyList();
        }
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
                    result = addUserToUniversity(userId, university);
                    LOGGER.debug("userId {} added university info {}", userId, university.toString());
                } else {
                    if (university.getName().isEmpty()) {
                        result = deleteUserFromUniversity(userId, university);
                        LOGGER.debug("userId {} deleted university info {} - by name", userId, university.toString());
                    } else {
                        result = updateUniversityByUserId(userId, university);
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
            result = deleteUserFromUniversity(userId, university);
            LOGGER.debug("userId {} deleted university info {} - by intersection", userId, university.toString());
        }

        return result;
    }

    public long updateUniversityByUserId(long userId, University university) {
        try {
            return universityDao.updateUniversityByUserId(userId, university);
        } catch (PersistException e) {
            LOGGER.error("Updating user university caused an exception: {}", e.getMessage());
            LOGGER.error("University: {}", university.toString());
            LOGGER.error("User id: {}", userId);
            return -1;
        }
    }

    public long deleteUserFromUniversity(long userId, University university) {
        try {
            return universityDao.deleteUserFromUniversity(userId, university);
        } catch (PersistException e) {
            LOGGER.error("Deleting university by user_id caused an exception: {}", e.getMessage());
            LOGGER.error("University: {}", university.toString());
            return -1;
        }
    }

    public long addUserToUniversity(long userId, University university) {
        try {
            return universityDao.addUserToUniversity(userId, university);
        } catch (PersistException e) {
            LOGGER.error("Adding university caused an exception: {}", e.getMessage());
            LOGGER.error("University: {}", university.toString());
            return -1;
        }
    }

    @Override
    public List<University> getUniversitiesByUserId(long userId) {
        try {
            return universityDao.getByUserId(userId);
        } catch (PersistException e) {
            LOGGER.error("Getting university by userName caused an exception: {}", e.getMessage());
            LOGGER.error("UserId: {}", userId);
            return Collections.emptyList();
        }
    }

    private List<School> findSchoolsToDelete(long userId, List<School> updatedSchoolList) {
        final List<School> persistentSchoolList = getSchoolsByUserId(userId);
        LOGGER.debug("persistent schools: {}", Arrays.toString(persistentSchoolList.toArray()));
        LOGGER.debug("updatedSchoolList: {}", Arrays.toString(updatedSchoolList.toArray()));
        persistentSchoolList.removeAll(updatedSchoolList);
        return persistentSchoolList;
    }

    private List<University> findUniversitiesToDelete(long userId, List<University> updatedUniversityList) {
        final List<University> persistentUniversityList = getUniversitiesByUserId(userId);
        LOGGER.debug("persistent universities: {}", Arrays.toString(persistentUniversityList.toArray()));
        LOGGER.debug("updatedUniversityList: {}", Arrays.toString(updatedUniversityList.toArray()));
        persistentUniversityList.removeAll(updatedUniversityList);
        return persistentUniversityList;
    }

    private boolean userAssociatedToUniversity(long userId, University university) {
        final Optional<University> universityOptional = getByUniversityName(university.getName());
        if (universityOptional.isPresent()) {
            return university.getUserId() == userId;
        }

        return false;
    }

    public Optional<University> getByUniversityName(String universityName) {
        try {
            return universityDao.getByUniversityName(universityName);
        } catch (PersistException e) {
            LOGGER.error("Getting university by name caused an exception: {}", e.getMessage());
            LOGGER.error("University name: {}", universityName);
            return Optional.empty();
        }
    }

    private boolean userAssociatedToSchool(long userId, School school) {
        final Optional<School> schoolOptional = getSchoolByName(school.getName());
        if (schoolOptional.isPresent()) {
            return school.getUserId() == userId;
        }

        return false;
    }

    public Optional<School> getSchoolByName(String schoolName) {
        try {
            return schoolDao.getBySchoolName(schoolName);
        } catch (PersistException e) {
            LOGGER.error("Getting school by name caused an exception: {}", e.getMessage());
            LOGGER.error("School name: {}", schoolName);
            return Optional.empty();
        }
    }
}
