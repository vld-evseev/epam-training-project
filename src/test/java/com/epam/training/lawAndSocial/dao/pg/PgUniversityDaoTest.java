package com.epam.training.lawAndSocial.dao.pg;

import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.db.H2DataSourceTest;
import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.model.education.EducationInfo;
import com.epam.training.lawAndSocial.model.education.impl.University;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class PgUniversityDaoTest extends H2DataSourceTest {

    private DataSource dataSource;

    @Before
    public void initDataSource() {
        dataSource = getDataSource();
    }

    @Test
    public void getByUserId() throws Exception {
        final PgUniversityDao univDao = new PgUniversityDao(dataSource);

        final PgUserDao userDao = new PgUserDao(dataSource);
        final Optional<User> testUser = userDao.getByUsername("testUser");
        Assert.assertTrue(testUser.isPresent());

        final List<EducationInfo> universityList = univDao.getByUserId(testUser.get().getId());
        Assert.assertFalse(universityList.isEmpty());
        Assert.assertEquals(universityList.size(), 2);

        final EducationInfo university = universityList.get(0);
        Assert.assertNotNull(university);

        final String name = university.getName();
        Assert.assertEquals(name, "Harvard");
    }

    @Test
    public void getByUniversityId() throws Exception {
        final PgUniversityDao universityDao = new PgUniversityDao(dataSource);

        final Optional<EducationInfo> universityOptional = universityDao.getBySubjectId(1);
        Assert.assertTrue(universityOptional.isPresent());
    }

    @Test
    public void getByUniversityName() throws Exception {
        final PgUniversityDao universityDao = new PgUniversityDao(dataSource);

        final Optional<EducationInfo> universityOptional = universityDao.getBySubjectName("Harvard");
        Assert.assertTrue(universityOptional.isPresent());
    }

    @Test
    public void addUserToUniversity() throws Exception {
        final PgUniversityDao universityDao = new PgUniversityDao(dataSource);

        final Optional<EducationInfo> universityOptional = universityDao.getBySubjectId(3);
        Assert.assertTrue(universityOptional.isPresent());

        final PgUserDao userDao = new PgUserDao(dataSource);
        final Optional<User> testUser = userDao.getByUsername("testUser");
        Assert.assertTrue(testUser.isPresent());

        final long inserted = universityDao.addUserToSubject(testUser.get().getId(), universityOptional.get());
        Assert.assertTrue(inserted > 0);
    }

    @Test(expected = PersistException.class)
    public void deleteUserFromUniversity() throws Exception {
        final PgUniversityDao universityDao = new PgUniversityDao(dataSource);

        final Optional<EducationInfo> universityOptional = universityDao.getBySubjectId(1);
        Assert.assertTrue(universityOptional.isPresent());

        final PgUserDao userDao = new PgUserDao(dataSource);
        final Optional<User> testUser = userDao.getByUsername("testUser");
        Assert.assertTrue(testUser.isPresent());

        final long deleted = universityDao.deleteUserFromSubject(testUser.get().getId(), universityOptional.get());
        Assert.assertEquals(deleted, 1);

        final long notDeletedBecauseNoLongerExists = universityDao.deleteUserFromSubject(testUser.get().getId(), universityOptional.get());
        Assert.assertEquals(notDeletedBecauseNoLongerExists, 0);

        final List<EducationInfo> testUserUniversityList = universityDao.getByUserId(testUser.get().getId());
        Assert.assertEquals(testUserUniversityList.size(), 1);
    }

    @Test
    public void updateUniversityByUserId() throws Exception {
        final PgUniversityDao universityDao = new PgUniversityDao(dataSource);

        final Optional<EducationInfo> univToUpdateOptional = universityDao.getBySubjectId(1);
        Assert.assertTrue(univToUpdateOptional.isPresent());

        final PgUserDao userDao = new PgUserDao(dataSource);
        final Optional<User> testUser = userDao.getByUsername("testUser");
        Assert.assertTrue(testUser.isPresent());

        final University updatedUniv = University.builder()
                .id(univToUpdateOptional.get().getId())
                .userId(testUser.get().getId())
                .name("UpdatedName")
                .country("UpdatedCountry")
                .city("UpdatedCity")
                .yearFrom(1987)
                .yearTo(1995)
                .build();

        final long updated = universityDao.updateSubjectByUserId(testUser.get().getId(), updatedUniv);
        Assert.assertEquals(updated, 1);

        final Optional<EducationInfo> updatedUnivOptional = universityDao.getBySubjectId(1);
        Assert.assertTrue(updatedUnivOptional.isPresent());
        final EducationInfo university = updatedUnivOptional.get();

        Assert.assertEquals(university.getId(), 1);
        Assert.assertEquals(university.getUserId(), testUser.get().getId());
        Assert.assertEquals(university.getName(), "UpdatedName");
        Assert.assertEquals(university.getCountry(), "UpdatedCountry");
        Assert.assertEquals(university.getCity(), "UpdatedCity");
        Assert.assertEquals(university.getYearFrom(), 1987);
        Assert.assertEquals(university.getYearTo(), 1995);
    }

}