package com.epam.training.lawAndSocial.dao.pg;

import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.db.H2DataSourceTest;
import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.model.education.EducationInfo;
import com.epam.training.lawAndSocial.model.education.impl.School;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class PgSchoolDaoTest extends H2DataSourceTest {

    private DataSource dataSource;

    @Before
    public void initDataSource() {
        dataSource = getDataSource();
    }

    @Test
    public void getByUserIdTest() throws Exception {
        final PgSchoolDao schoolDao = new PgSchoolDao(dataSource);

        final PgUserDao userDao = new PgUserDao(dataSource);
        final Optional<User> testUser = userDao.getByUsername("testUser");
        Assert.assertTrue(testUser.isPresent());

        final List<EducationInfo> schoolList = schoolDao.getByUserId(testUser.get().getId());
        Assert.assertFalse(schoolList.isEmpty());
        Assert.assertEquals(schoolList.size(), 2);

        final EducationInfo school = schoolList.get(0);
        Assert.assertNotNull(school);

        final String name = school.getName();
        Assert.assertEquals(name, "School1");
    }

    @Test
    public void getSchoolById() throws Exception {
        final PgSchoolDao schoolDao = new PgSchoolDao(dataSource);

        final Optional<EducationInfo> schoolOptional = schoolDao.getBySubjectId(1);
        Assert.assertTrue(schoolOptional.isPresent());
    }

    @Test
    public void getSchoolByName() throws Exception {
        final PgSchoolDao schoolDao = new PgSchoolDao(dataSource);

        final Optional<EducationInfo> schoolOptional = schoolDao.getBySubjectName("School1");
        Assert.assertTrue(schoolOptional.isPresent());
    }

    @Test
    public void addUserToSchoolTest() throws Exception {
        final PgSchoolDao schoolDao = new PgSchoolDao(dataSource);

        final Optional<EducationInfo> schoolOptional = schoolDao.getBySubjectId(3);
        Assert.assertTrue(schoolOptional.isPresent());

        final PgUserDao userDao = new PgUserDao(dataSource);
        final Optional<User> testUser = userDao.getByUsername("testUser");
        Assert.assertTrue(testUser.isPresent());

        final long inserted = schoolDao.addUserToSubject(testUser.get().getId(), schoolOptional.get());
        Assert.assertTrue(inserted > 0);
    }

    @Test(expected = PersistException.class)
    public void deleteUserFromSchool() throws Exception {
        final PgSchoolDao schoolDao = new PgSchoolDao(dataSource);

        final Optional<EducationInfo> schoolOptional = schoolDao.getBySubjectId(1);
        Assert.assertTrue(schoolOptional.isPresent());

        final PgUserDao userDao = new PgUserDao(dataSource);
        final Optional<User> testUser = userDao.getByUsername("testUser");
        Assert.assertTrue(testUser.isPresent());

        final long deleted = schoolDao.deleteUserFromSubject(testUser.get().getId(), schoolOptional.get());
        Assert.assertEquals(deleted, 1);

        final long notDeletedBecauseNoLongerExists = schoolDao.deleteUserFromSubject(testUser.get().getId(), schoolOptional.get());
        Assert.assertEquals(notDeletedBecauseNoLongerExists, 0);

        final List<EducationInfo> testUserSchoolList = schoolDao.getByUserId(testUser.get().getId());
        Assert.assertEquals(testUserSchoolList.size(), 1);
    }

    @Test
    public void updateUserSchoolTest() throws Exception {
        final PgSchoolDao schoolDao = new PgSchoolDao(dataSource);

        final Optional<EducationInfo> schoolToUpdateOptional = schoolDao.getBySubjectId(1);
        Assert.assertTrue(schoolToUpdateOptional.isPresent());

        final PgUserDao userDao = new PgUserDao(dataSource);
        final Optional<User> testUser = userDao.getByUsername("testUser");
        Assert.assertTrue(testUser.isPresent());

        final School updatedSchool = School.builder()
                .id(schoolToUpdateOptional.get().getId())
                .userId(testUser.get().getId())
                .name("UpdatedName")
                .country("UpdatedCountry")
                .city("UpdatedCity")
                .yearFrom(1987)
                .yearTo(1995)
                .build();

        final long updated = schoolDao.updateSubjectByUserId(testUser.get().getId(), updatedSchool);
        Assert.assertEquals(updated, 1);

        final Optional<EducationInfo> updatedSchoolOptional = schoolDao.getBySubjectId(1);
        Assert.assertTrue(updatedSchoolOptional.isPresent());
        final EducationInfo school = updatedSchoolOptional.get();

        Assert.assertEquals(school.getId(), 1);
        Assert.assertEquals(school.getUserId(), testUser.get().getId());
        Assert.assertEquals(school.getName(), "UpdatedName");
        Assert.assertEquals(school.getCountry(), "UpdatedCountry");
        Assert.assertEquals(school.getCity(), "UpdatedCity");
        Assert.assertEquals(school.getYearFrom(), 1987);
        Assert.assertEquals(school.getYearTo(), 1995);

    }

}
