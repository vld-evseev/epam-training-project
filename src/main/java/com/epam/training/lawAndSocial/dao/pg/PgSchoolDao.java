package com.epam.training.lawAndSocial.dao.pg;

import com.epam.training.lawAndSocial.dao.SchoolDao;
import com.epam.training.lawAndSocial.model.education.School;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
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
    public List<School> getByUserId(long userId) {
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
        } catch (SQLException e) {
            LOGGER.error("Getting school by userName caused an exception: {}", e.getMessage());
            LOGGER.error("SQL state: {}\nError code: {}", e.getSQLState(), e.getErrorCode());
            LOGGER.error("UserId: {}", userId);
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(result);
    }

    @Override
    public Optional<School> getBySchoolId(long id) {
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
        } catch (SQLException e) {
            LOGGER.error("Getting school by id caused an exception: {}", e.getMessage());
            LOGGER.error("SQL state: {}\nError code: {}", e.getSQLState(), e.getErrorCode());
            LOGGER.error("School id: {}", id);
            return Optional.empty();
        }

        return result;
    }

    @Override
    public Optional<School> getBySchoolName(String schoolName) {
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
        } catch (SQLException e) {
            LOGGER.error("Getting school by name caused an exception: {}", e.getMessage());
            LOGGER.error("SQL state: {}\nError code: {}", e.getSQLState(), e.getErrorCode());
            LOGGER.error("School name: {}", schoolName);
            return Optional.empty();
        }

        return result;
    }


    @Override
    public long addUserToSchool(long userId, School school) {
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
        } catch (SQLException e) {
            LOGGER.error("Adding school caused an exception: {}", e.getMessage());
            LOGGER.error("SQL state: {}\nError code: {}", e.getSQLState(), e.getErrorCode());
            LOGGER.error("School: {}", school.toString());
        }

        return result;
    }

    @Override
    public long deleteUserFromSchool(long userId, School school) {
        int result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "DELETE FROM lawAndSocialDb.school" +
                            " WHERE id = ? AND user_id = ?;"
            );
            query.setLong(1, school.getId());
            query.setLong(2, userId);
            result = query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Deleting school by user_id caused an exception: {}", e.getMessage());
            LOGGER.error("SQL state: {}\nError code: {}", e.getSQLState(), e.getErrorCode());
            LOGGER.error("School: {}", school.toString());
        }

        return result;
    }

    @Override
    public long updateSchoolByUserId(long userId, School school) {
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
        } catch (SQLException e) {
            LOGGER.error("Updating user school caused an exception: {}", e.getMessage());
            LOGGER.error("SQL state: {}\nError code: {}", e.getSQLState(), e.getErrorCode());
            LOGGER.error("School: {}", school.toString());
            LOGGER.error("User id: {}", userId);
            return result;
        }

        return result;
    }

}
