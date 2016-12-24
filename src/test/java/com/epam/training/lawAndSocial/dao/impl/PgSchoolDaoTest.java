package com.epam.training.lawAndSocial.dao.impl;

import com.epam.training.lawAndSocial.dao.pg.PgSchoolDao;
import com.epam.training.lawAndSocial.dao.pg.PgUserDao;
import com.epam.training.lawAndSocial.db.H2DataSourceTest;
import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.model.education.School;
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
    public void getByUserIdTest() {
        final PgSchoolDao schoolDao = new PgSchoolDao(dataSource);

        final PgUserDao userDao = new PgUserDao(dataSource);
        final Optional<User> testUser = userDao.getByUsername("testUser");
        Assert.assertTrue(testUser.isPresent());

        final List<School> schoolList = schoolDao.getByUserId(testUser.get().getId());
        Assert.assertFalse(schoolList.isEmpty());
        Assert.assertEquals(schoolList.size(), 2);

        final School school = schoolList.get(0);
        Assert.assertNotNull(school);

        final String name = school.getName();
        Assert.assertEquals(name, "School1");
    }

    @Test
    public void getSchoolById() {
        final PgSchoolDao schoolDao = new PgSchoolDao(dataSource);

        final Optional<School> schoolOptional = schoolDao.getBySchoolId(1);
        Assert.assertTrue(schoolOptional.isPresent());
    }

    @Test
    public void getSchoolByName() {
        final PgSchoolDao schoolDao = new PgSchoolDao(dataSource);

        final Optional<School> schoolOptional = schoolDao.getBySchoolName("School1");
        Assert.assertTrue(schoolOptional.isPresent());
    }

    @Test
    public void addUserToSchoolTest() {
        final PgSchoolDao schoolDao = new PgSchoolDao(dataSource);

        final Optional<School> schoolOptional = schoolDao.getBySchoolId(3);
        Assert.assertTrue(schoolOptional.isPresent());

        final PgUserDao userDao = new PgUserDao(dataSource);
        final Optional<User> testUser = userDao.getByUsername("testUser");
        Assert.assertTrue(testUser.isPresent());

        final long inserted = schoolDao.addUserToSchool(testUser.get().getId(), schoolOptional.get());
        Assert.assertTrue(inserted > 0);
    }

    @Test
    public void deleteUserFromSchool() {
        final PgSchoolDao schoolDao = new PgSchoolDao(dataSource);

        final Optional<School> schoolOptional = schoolDao.getBySchoolId(1);
        Assert.assertTrue(schoolOptional.isPresent());

        final long deleted = schoolDao.deleteUserFromSchool(schoolOptional.get());
        Assert.assertEquals(deleted, 1);

        final long notDeletedBecauseNoLongerExists = schoolDao.deleteUserFromSchool(schoolOptional.get());
        Assert.assertEquals(notDeletedBecauseNoLongerExists, 0);
    }

    @Test
    public void updateUserSchoolTest() {
        final PgSchoolDao schoolDao = new PgSchoolDao(dataSource);

        final Optional<School> schoolToUpdateOptional = schoolDao.getBySchoolId(1);
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
                .yearsFrom(1987)
                .yearsTo(1995)
                .build();

        final long updated = schoolDao.updateUserSchool(testUser.get().getId(), updatedSchool);
        Assert.assertEquals(updated, 1);

        final Optional<School> updatedSchoolOptional = schoolDao.getBySchoolId(1);
        Assert.assertTrue(updatedSchoolOptional.isPresent());
        final School school = updatedSchoolOptional.get();

        Assert.assertEquals(school.getId(), 1);
        Assert.assertEquals(school.getUserId(), testUser.get().getId());
        Assert.assertEquals(school.getName(), "UpdatedName");
        Assert.assertEquals(school.getCountry(), "UpdatedCountry");
        Assert.assertEquals(school.getCity(), "UpdatedCity");
        Assert.assertEquals(school.getYearsFrom(), 1987);
        Assert.assertEquals(school.getYearsTo(), 1995);

    }

}
