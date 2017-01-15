package com.epam.training.lawAndSocial.dao.pg;

import com.epam.training.lawAndSocial.db.H2DataSourceTest;
import com.epam.training.lawAndSocial.model.Job;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.Optional;

public class PgJobInfoDaoTest extends H2DataSourceTest {

    private DataSource dataSource;

    @Before
    public void initDataSource() {
        dataSource = getDataSource();
    }

    @Test
    public void add() throws Exception {
        final PgJobInfoDao workInfoDao = new PgJobInfoDao(dataSource);

        final Job testJobInfo = Job.builder()
                .organization("testOrg")
                .position("testPos")
                .industry("testInd")
                .website("testSite")
                .yearFrom(2000)
                .yearTo(2010)
                .build();

        final long result = workInfoDao.add(13, testJobInfo);
        Assert.assertTrue(result > 0);
    }

    @Test
    public void get() throws Exception {
        final PgJobInfoDao workInfoDao = new PgJobInfoDao(dataSource);
        final Optional<Job> work = workInfoDao.get(1);
        Assert.assertTrue(work.isPresent());
    }

    @Test
    public void update() throws Exception {
        final PgJobInfoDao workInfoDao = new PgJobInfoDao(dataSource);
        final Optional<Job> work = workInfoDao.get(1);
        Assert.assertTrue(work.isPresent());
        final Job currentJobInfo = work.get();

        final Job updatedJobInfo = Job.builder()
                .id(currentJobInfo.getId())
                .userId(currentJobInfo.getUserId())
                .organization("updatedOrg")
                .industry("updatedInd")
                .website("updatedSite")
                .yearFrom(1990)
                .yearTo(2000)
                .build();

        final long update = workInfoDao.update(1, updatedJobInfo);
        Assert.assertTrue(update == 1);

        final Optional<Job> workOptional = workInfoDao.get(1);
        Assert.assertTrue(workOptional.isPresent());

        Assert.assertEquals(workOptional.get().getId(), updatedJobInfo.getId());
        Assert.assertEquals(workOptional.get().getUserId(), updatedJobInfo.getUserId());
        Assert.assertEquals(workOptional.get().getOrganization(), updatedJobInfo.getOrganization());
        Assert.assertEquals(workOptional.get().getIndustry(), updatedJobInfo.getIndustry());
        Assert.assertEquals(workOptional.get().getWebsite(), updatedJobInfo.getWebsite());
        Assert.assertEquals(workOptional.get().getYearFrom(), updatedJobInfo.getYearFrom());
        Assert.assertEquals(workOptional.get().getYearTo(), updatedJobInfo.getYearTo());
    }

    @Test
    public void delete() throws Exception {
        final PgJobInfoDao workInfoDao = new PgJobInfoDao(dataSource);
        final Optional<Job> work = workInfoDao.get(1);
        Assert.assertTrue(work.isPresent());

        final long result = workInfoDao.delete(work.get().getUserId(), work.get().getId());
        Assert.assertTrue(result == 1);
    }

}