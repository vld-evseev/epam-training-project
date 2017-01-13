package com.epam.training.lawAndSocial.dao.pg;

import com.epam.training.lawAndSocial.db.H2DataSourceTest;
import com.epam.training.lawAndSocial.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PgFollowDaoTest extends H2DataSourceTest {


    private DataSource dataSource;

    @Before
    public void initDatasource() {
        dataSource = getDataSource();
    }

    @Test
    public void followSameUser() throws Exception {
        final PgUserDao userDao = new PgUserDao(dataSource);
        final Optional<User> testUser = userDao.getByUsername("testUser");
        final Optional<User> following = userDao.getByUsername("JohnDoug123");

        Assert.assertTrue(testUser.isPresent());
        Assert.assertTrue(following.isPresent());

        final PgFollowDao followDao = new PgFollowDao(dataSource);
        final int result = followDao.follow(testUser.get().getId(), following.get().getId());
        Assert.assertEquals(result, -1);
    }

    @Test
    public void follow() throws Exception {
        final PgUserDao userDao = new PgUserDao(dataSource);
        final Optional<User> testUser = userDao.getByUsername("testUser");
        final Optional<User> following = userDao.getByUserId(10);

        Assert.assertTrue(testUser.isPresent());
        Assert.assertTrue(following.isPresent());

        final PgFollowDao followDao = new PgFollowDao(dataSource);
        final int result = followDao.follow(testUser.get().getId(), following.get().getId());
        Assert.assertTrue(result > 0);
    }

    @Test
    public void unfollow() throws Exception {
        final PgUserDao userDao = new PgUserDao(dataSource);
        final Optional<User> testUser = userDao.getByUsername("testUser");
        final Optional<User> unfollowing = userDao.getByUsername("JohnDoug123");

        Assert.assertTrue(testUser.isPresent());
        Assert.assertTrue(unfollowing.isPresent());

        final PgFollowDao followDao = new PgFollowDao(dataSource);
        final int result = followDao.unfollow(testUser.get().getId(), unfollowing.get().getId());
        Assert.assertTrue(result > 0);
    }

    @Test
    public void getFollowing() throws Exception {
        final PgUserDao userDao = new PgUserDao(dataSource);
        final Optional<User> testUser = userDao.getByUsername("testUser");

        Assert.assertTrue(testUser.isPresent());

        final PgFollowDao followDao = new PgFollowDao(dataSource);
        final List<User> following = followDao.getFollowing(testUser.get().getId(), 5, 0);
        System.out.println(Arrays.toString(following.toArray()));
        Assert.assertFalse(following.isEmpty());
        Assert.assertEquals(following.size(), 4);
    }

    @Test
    public void getFollowers() throws Exception {
        final PgUserDao userDao = new PgUserDao(dataSource);
        final Optional<User> testUser = userDao.getByUsername("testUser");

        Assert.assertTrue(testUser.isPresent());

        final PgFollowDao followDao = new PgFollowDao(dataSource);
        final List<User> followers = followDao.getFollowers(testUser.get().getId(), 5, 0);
        System.out.println(Arrays.toString(followers.toArray()));
        Assert.assertFalse(followers.isEmpty());
        Assert.assertEquals(followers.size(), 5);
    }

    @Test
    public void getFollowersNumber() throws Exception {
        final PgUserDao userDao = new PgUserDao(dataSource);
        final Optional<User> testUser = userDao.getByUsername("testUser");

        Assert.assertTrue(testUser.isPresent());

        final PgFollowDao followDao = new PgFollowDao(dataSource);
        final long followersNumber = followDao.getFollowersNumber(testUser.get().getId());
        Assert.assertEquals(followersNumber, 5);
    }

    @Test
    public void getFollowingNumber() throws Exception {
        final PgUserDao userDao = new PgUserDao(dataSource);
        final Optional<User> testUser = userDao.getByUsername("testUser");

        Assert.assertTrue(testUser.isPresent());

        final PgFollowDao followDao = new PgFollowDao(dataSource);
        final long followingNumber = followDao.getFollowingNumber(testUser.get().getId());
        Assert.assertEquals(followingNumber, 4);
    }

}