package com.epam.training.lawAndSocial.dao.impl;

import com.epam.training.lawAndSocial.dao.pg.PgContactsDao;
import com.epam.training.lawAndSocial.dao.pg.PgUserDao;
import com.epam.training.lawAndSocial.db.H2DataSourceTest;
import com.epam.training.lawAndSocial.model.Contacts;
import com.epam.training.lawAndSocial.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.Optional;

public class PgContactsDaoTest extends H2DataSourceTest {

    private DataSource dataSource;

    @Before
    public void initDatasource() {
        dataSource = getDataSource();
    }

    @Test
    public void addTest() {
        final PgContactsDao contactsDao = new PgContactsDao(dataSource);
        final PgUserDao userDao = new PgUserDao(dataSource);
        final Optional<User> testUser = userDao.getByUsername("anotherUser");
        Assert.assertTrue(testUser.isPresent());

        final Contacts contacts = Contacts.builder()
                .userId(testUser.get().getId())
                .email("another@mail.com")
                .phone("+9444111")
                .website("testWebsite.com")
                .build();

        final long id = contactsDao.add(testUser.get().getId(), contacts);
        Assert.assertTrue(id > 0);
    }

    @Test
    public void getTest() {
        final PgContactsDao contactsDao = new PgContactsDao(dataSource);
        final PgUserDao userDao = new PgUserDao(dataSource);
        final Optional<User> testUser = userDao.getByUsername("testUser");
        Assert.assertTrue(testUser.isPresent());

        final Optional<Contacts> contactsOptional = contactsDao.get(testUser.get().getId());
        Assert.assertTrue(contactsOptional.isPresent());

        final Contacts contacts = contactsOptional.get();

        Assert.assertEquals(contacts.getUserId(), testUser.get().getId());
        Assert.assertEquals(contacts.getEmail(), "test@mail.com");
        Assert.assertEquals(contacts.getPhone(), "+7226663331");
        Assert.assertEquals(contacts.getWebsite(), "tesuserwebsite.com");
    }

    @Test
    public void deleteTest() {
        final PgContactsDao contactsDao = new PgContactsDao(dataSource);
        final PgUserDao userDao = new PgUserDao(dataSource);
        final Optional<User> testUser = userDao.getByUsername("testUser");
        Assert.assertTrue(testUser.isPresent());

        final long deleted = contactsDao.delete(testUser.get().getId());
        Assert.assertTrue(deleted > 0);
    }

    @Test
    public void updateTest() {
        final PgContactsDao contactsDao = new PgContactsDao(dataSource);
        final PgUserDao userDao = new PgUserDao(dataSource);
        final Optional<User> testUser = userDao.getByUsername("testUser");
        Assert.assertTrue(testUser.isPresent());

        final Contacts updatedContacts = Contacts.builder()
                .email("updated@mail.com")
                .phone("+7123456")
                .website("updated.website.com")
                .build();

        final long updated = contactsDao.update(testUser.get().getId(), updatedContacts);
        Assert.assertTrue(updated > 0);

        final Optional<Contacts> contactsOptional = contactsDao.get(testUser.get().getId());
        Assert.assertTrue(contactsOptional.isPresent());
        final Contacts contacts = contactsOptional.get();

        Assert.assertEquals(contacts.getUserId(), testUser.get().getId());
        Assert.assertEquals(contacts.getEmail(), "updated@mail.com");
        Assert.assertEquals(contacts.getPhone(), "+7123456");
        Assert.assertEquals(contacts.getWebsite(), "updated.website.com");
    }
}
