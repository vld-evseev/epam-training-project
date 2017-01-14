package com.epam.training.lawAndSocial.dao.pg;

import com.epam.training.lawAndSocial.dao.SchoolDao;
import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.model.education.School;
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

public class PgSchoolDao implements SchoolDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(PgSchoolDao.class);

    private final DataSource dataSource;

    @Inject
    public PgSchoolDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<School> getByUserId(long userId) throws PersistException {
        List<School> result = new LinkedList<>();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT id, user_id, name, country, city, yearsFrom, yearsTo" +
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
                                .yearsFrom(resultSet.getInt("yearsFrom"))
                                .yearsTo(resultSet.getInt("yearsTo"))
                                .build()
                );
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return result;
    }

    @Override
    public Optional<School> getBySchoolId(long id) throws PersistException {
        Optional<School> result = Optional.empty();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT id, user_id, name, country, city, yearsFrom, yearsTo" +
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
                                .yearsFrom(resultSet.getInt("yearsFrom"))
                                .yearsTo(resultSet.getInt("yearsTo"))
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
    public Optional<School> getBySchoolName(String schoolName) throws PersistException {
        Optional<School> result = Optional.empty();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT id, user_id, name, country, city, yearsFrom, yearsTo" +
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
                                .yearsFrom(resultSet.getInt("yearsFrom"))
                                .yearsTo(resultSet.getInt("yearsTo"))
                                .build()
                );
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        return result;
    }


    @Override
    public long addUserToSchool(long userId, School school) throws PersistException {
        int result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final String[] returnColumns = {"id"};
            final PreparedStatement query = connection.prepareStatement(
                    "INSERT INTO lawAndSocialDb.school(id, user_id, name, country, city, yearsFrom, yearsTo)" +
                            " VALUES (nextval('lawAndSocialDb.school_seq')," +
                            " ?, ?, ?, ?, ?, ?);",
                    returnColumns
            );
            query.setLong(1, userId);
            query.setString(2, school.getName());
            query.setString(3, school.getCountry());
            query.setString(4, school.getCity());
            query.setInt(5, school.getYearsFrom());
            query.setInt(6, school.getYearsTo());
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
    public long deleteUserFromSchool(long userId, School school) throws PersistException {
        int result = -1;
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
    public long updateSchoolByUserId(long userId, School school) throws PersistException {
        long result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "UPDATE lawAndSocialDb.school" +
                            " SET name = ?, country = ?, city = ?, yearsFrom = ?, yearsTo = ?" +
                            " WHERE id = ? AND user_id = ?;"
            );
            query.setString(1, school.getName());
            query.setString(2, school.getCountry());
            query.setString(3, school.getCity());
            query.setInt(4, school.getYearsFrom());
            query.setInt(5, school.getYearsTo());
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
