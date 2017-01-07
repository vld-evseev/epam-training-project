package com.epam.training.lawAndSocial.dao.pg;

import com.epam.training.lawAndSocial.dao.UserDao;
import com.epam.training.lawAndSocial.db.H2DataSourceTest;
import com.epam.training.lawAndSocial.model.Gender;
import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.service.SecurityService;
import com.epam.training.lawAndSocial.utils.ImageUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PgUserDaoTest extends H2DataSourceTest {


    private DataSource dataSource;

    @Before
    public void initDataSource() {
        dataSource = getDataSource();
    }

    @Test
    public void addUserTest() {
        final SecurityService securityService = mock(SecurityService.class);
        when(securityService.encrypt("testPassword")).thenReturn("passwordHash");

        final User testUser = User.builder()
                .userName("testUser")
                .firstName("John")
                .lastName("Doe")
                .date("01.01.1990")
                .passwordHash(securityService.encrypt("testPassword"))
                .build();

        final UserDao userDao = new PgUserDao(dataSource);
        final long id = userDao.add(testUser);
        Assert.assertTrue(id > 0);

        final Optional<User> createdUser = userDao.getByUsername("testUser");
        Assert.assertTrue(createdUser.isPresent());
    }

    @Test
    public void getUserByNameTest() {
        final UserDao userDao = new PgUserDao(dataSource);
        final Optional<User> testUser = userDao.getByUsername("testUser");
        Assert.assertTrue(testUser.isPresent());
    }

    @Test
    public void updateUserTest() {
        final PgUserDao userDao = new PgUserDao(dataSource);
        final Optional<User> testUser = userDao.getByUsername("testUser");
        Assert.assertTrue(testUser.isPresent());

        final User updatedUser = User.builder()
                .id(testUser.get().getId())
                .userName(testUser.get().getUserName())
                .firstName("updatedFirstName")
                .lastName("updatedLastName")
                .patronymic("updatedPatronymic")
                .gender(Gender.MALE)
                .date("02.12.1950")
                .build();

        final long updated = userDao.update(updatedUser);
        Assert.assertTrue(updated > 0);

        final Optional<User> updatedUserOptional = userDao.getByUsername("testUser");
        Assert.assertTrue(updatedUserOptional.isPresent());

        final User user = updatedUserOptional.get();
        Assert.assertEquals(user.getId(), testUser.get().getId());
        Assert.assertEquals(user.getUserName(), testUser.get().getUserName());
        Assert.assertEquals(user.getFirstName(), "updatedFirstName");
        Assert.assertEquals(user.getLastName(), "updatedLastName");
        Assert.assertEquals(user.getPatronymic(), "updatedPatronymic");
        Assert.assertEquals(user.getGender(), Gender.MALE);
        Assert.assertEquals(user.getDate(), "02.12.1950");
    }

    @Test
    public void getUsers() throws Exception {
        final PgUserDao userDao = new PgUserDao(dataSource);

        final int offset = 1;
        final int limit = 2;

        final List<User> userList = userDao.getUsers(limit, offset);
        System.out.println(Arrays.toString(userList.toArray()));
        Assert.assertFalse(userList.isEmpty());
    }

    @Test
    public void updateAvatar() throws Exception {
        final PgUserDao userDao = new PgUserDao(dataSource);
        final Optional<User> testUser = userDao.getByUsername("testUser");
        Assert.assertTrue(testUser.isPresent());

        final URL defaultAvatar = getClass().getClassLoader().getResource("imgs/default_user.png");

        final long updated = userDao.updateAvatar(testUser.get().getId(), ImageUtils.encodeBase64(defaultAvatar));
        Assert.assertTrue(updated > 0);

        final Optional<User> updatedTestUser = userDao.getByUsername("testUser");
        Assert.assertTrue(updatedTestUser.isPresent());

        final User user = updatedTestUser.get();
        Assert.assertFalse(user.getAvatar().isEmpty());

        System.out.println(user.toString());
    }

}
