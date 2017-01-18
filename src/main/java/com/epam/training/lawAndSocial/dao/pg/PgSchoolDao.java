package com.epam.training.lawAndSocial.dao.pg;

import com.epam.training.lawAndSocial.dao.EducationInfoDao;
import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.model.education.EducationInfo;
import com.epam.training.lawAndSocial.model.education.impl.School;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class PgSchoolDao implements EducationInfoDao<EducationInfo> {

    private final static Logger LOGGER = LoggerFactory.getLogger(PgSchoolDao.class);

    private final DataSource dataSource;

    @Inject
    public PgSchoolDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<EducationInfo> getByUserId(long userId) throws PersistException {
        List<EducationInfo> result = new LinkedList<>();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT id, user_id, name, country, city, yearFrom, yearTo" +
                            " FROM lawAndSocialDb.school" +
                            " WHERE user_id = ?;"
            );
            query.setLong(1, userId);
            final ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                result.add(
                        School.builder()
                                .id(resultSet.getLong("id"))
                                .userId(resultSet.getLong("user_id"))
                                .name(resultSet.getString("name"))
                                .country(resultSet.getString("country"))
                                .city(resultSet.getString("city"))
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
    public Optional<EducationInfo> getBySubjectId(long id) throws PersistException {
        Optional<EducationInfo> result = Optional.empty();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT id, user_id, name, country, city, yearFrom, yearTo" +
                            " FROM lawAndSocialDb.school WHERE id = ?;"
            );
            query.setLong(1, id);
            final ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                result = Optional.of(
                        School.builder()
                                .id(resultSet.getLong("id"))
                                .userId(resultSet.getLong("user_id"))
                                .name(resultSet.getString("name"))
                                .country(resultSet.getString("country"))
                                .city(resultSet.getString("city"))
                                .yearFrom(resultSet.getInt("yearFrom"))
                                .yearTo(resultSet.getInt("yearTo"))
                                .build()
                );
            }
        } catch (Exception e) {
            LOGGER.error("Getting school by id caused an exception: {}", e.getMessage());
            LOGGER.error("School id: {}", id);
            throw new PersistException(e);
        }

        return result;
    }

    @Override
    public Optional<EducationInfo> getBySubjectName(String schoolName) throws PersistException {
        Optional<EducationInfo> result = Optional.empty();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT id, user_id, name, country, city, yearFrom, yearTo" +
                            " FROM lawAndSocialDb.school WHERE name = ?;"
            );
            query.setString(1, schoolName);
            final ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                result = Optional.of(
                        School.builder()
                                .id(resultSet.getLong("id"))
                                .name(resultSet.getString("name"))
                                .country(resultSet.getString("country"))
                                .city(resultSet.getString("city"))
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
    public long addUserToSubject(long userId, EducationInfo school) throws PersistException {
        int result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final String[] returnColumns = {"id"};
            final PreparedStatement query = connection.prepareStatement(
                    "INSERT INTO lawAndSocialDb.school(id, user_id, name, country, city, yearFrom, yearTo)" +
                            " VALUES (nextval('lawAndSocialDb.school_seq')," +
                            " ?, ?, ?, ?, ?, ?);",
                    returnColumns
            );
            query.setLong(1, userId);
            query.setString(2, school.getName());
            query.setString(3, school.getCountry());
            query.setString(4, school.getCity());
            query.setInt(5, school.getYearFrom());
            query.setInt(6, school.getYearTo());
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
    public long deleteUserFromSubject(long userId, EducationInfo school) throws PersistException {
        int result;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "DELETE FROM lawAndSocialDb.school" +
                            " WHERE id = ? AND user_id = ?;"
            );
            query.setLong(1, school.getId());
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

    @Override
    public long updateSubjectByUserId(long userId, EducationInfo school) throws PersistException {
        long result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "UPDATE lawAndSocialDb.school" +
                            " SET name = ?, country = ?, city = ?, yearFrom = ?, yearTo = ?" +
                            " WHERE id = ? AND user_id = ?;"
            );
            query.setString(1, school.getName());
            query.setString(2, school.getCountry());
            query.setString(3, school.getCity());
            query.setInt(4, school.getYearFrom());
            query.setInt(5, school.getYearTo());
            query.setLong(6, school.getId());
            query.setLong(7, userId);
            result = query.executeUpdate();

            if (result != 1) {
                throw new PersistException("None or more than one row affected on update: " + result);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return result;
    }

}
