package com.epam.training.lawAndSocial.dao.pg;

import com.epam.training.lawAndSocial.dao.JobInfoDao;
import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.model.Job;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class PgJobInfoDao implements JobInfoDao {

    private final DataSource dataSource;

    @Inject
    public PgJobInfoDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long add(long userId, Job job) throws PersistException {
        int result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final String[] returnColumns = {"id"};
            final PreparedStatement query = connection.prepareStatement(
                    "INSERT INTO lawAndSocialDb.job" +
                            "(id, user_id, organization, position, industry, website, yearFrom, yearTo)" +
                            " VALUES (nextval('lawAndSocialDb.job_seq')," +
                            " ?, ?, ?, ?, ?, ?, ?);",
                    returnColumns
            );
            query.setLong(1, userId);
            query.setString(2, job.getOrganization());
            query.setString(3, job.getPosition());
            query.setString(4, job.getIndustry());
            query.setString(5, job.getWebsite());
            query.setInt(6, job.getYearFrom());
            query.setInt(7, job.getYearTo());
            query.executeUpdate();
            final ResultSet generatedKeys = query.getGeneratedKeys();
            if (generatedKeys.next()) {
                result = generatedKeys.getInt(1);
            }

            if (result == 0) {
                throw new PersistException("Rows are not affected on add: " + result);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return result;
    }

    @Override
    public Optional<Job> get(long userId) throws PersistException {
        Optional<Job> result = Optional.empty();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT id, user_id, organization, position, industry, website, yearFrom, yearTo" +
                            " FROM lawAndSocialDb.job WHERE user_id = ?;"
            );
            query.setLong(1, userId);
            final ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                result = Optional.of(
                        Job.builder()
                                .id(resultSet.getLong("id"))
                                .userId(resultSet.getLong("user_id"))
                                .organization(resultSet.getString("organization"))
                                .position(resultSet.getString("position"))
                                .industry(resultSet.getString("industry"))
                                .website(resultSet.getString("website"))
                                .yearFrom(resultSet.getInt("yearFrom"))
                                .yearTo(resultSet.getInt("yearTo"))
                                .build()
                );
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return result;
    }

    @Override
    public long update(long userId, Job job) throws PersistException {
        long result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "UPDATE lawAndSocialDb.job" +
                            " SET organization = ?, position = ?, industry = ?, website = ?, yearFrom = ?, yearTo = ?" +
                            " WHERE id = ? AND user_id = ?;"
            );
            query.setString(1, job.getOrganization());
            query.setString(2, job.getPosition());
            query.setString(3, job.getIndustry());
            query.setString(4, job.getWebsite());
            query.setInt(5, job.getYearFrom());
            query.setInt(6, job.getYearTo());
            query.setLong(7, job.getId());
            query.setLong(8, userId);
            result = query.executeUpdate();

            if (result != 1) {
                throw new PersistException("None or more than one row affected on update: " + result);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return result;
    }

    @Override
    public long delete(long id, long userId) throws PersistException {
        int result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "DELETE FROM lawAndSocialDb.job" +
                            " WHERE id = ? AND user_id = ?;"
            );
            query.setLong(1, id);
            query.setLong(2, userId);
            result = query.executeUpdate();

            if (result != 1) {
                throw new PersistException("None or more than one row affected on delete: " + result);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return result;
    }
}
