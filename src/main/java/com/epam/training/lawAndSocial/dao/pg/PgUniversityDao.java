package com.epam.training.lawAndSocial.dao.pg;

import com.epam.training.lawAndSocial.dao.UniversityDao;
import com.epam.training.lawAndSocial.dao.exception.PersistException;
import com.epam.training.lawAndSocial.model.education.University;
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

public class PgUniversityDao implements UniversityDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(PgUniversityDao.class);
    private final DataSource dataSource;

    @Inject
    public PgUniversityDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<University> getByUserId(long userId) throws PersistException {
        List<University> result = new LinkedList<>();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT id, user_id, name, country, city, yearsFrom, yearsTo" +
                            " FROM lawAndSocialDb.university" +
                            " WHERE user_id = ?;"
            );
            query.setLong(1, userId);
            final ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                result.add(
                        University.builder()
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
    public Optional<University> getByUniversityId(long id) throws PersistException {
        Optional<University> result = Optional.empty();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT id, user_id, name, country, city, yearsFrom, yearsTo" +
                            " FROM lawAndSocialDb.university WHERE id = ?;"
            );
            query.setLong(1, id);
            final ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                result = Optional.of(
                        University.builder()
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
            LOGGER.error("Getting university by id caused an exception: {}", e.getMessage());
            LOGGER.error("University id: {}", id);
            throw new PersistException(e);
        }

        return result;
    }

    @Override
    public Optional<University> getByUniversityName(String universityName) throws PersistException {
        Optional<University> result = Optional.empty();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT id, user_id, name, country, city, yearsFrom, yearsTo" +
                            " FROM lawAndSocialDb.university WHERE name = ?;"
            );
            query.setString(1, universityName);
            final ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                result = Optional.of(
                        University.builder()
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
    public long addUserToUniversity(long userId, University university) throws PersistException {
        int result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final String[] returnColumns = {"id"};
            final PreparedStatement query = connection.prepareStatement(
                    "INSERT INTO lawAndSocialDb.university(id, user_id, name, country, city, yearsFrom, yearsTo)" +
                            " VALUES (nextval('lawAndSocialDb.university_seq')," +
                            " ?, ?, ?, ?, ?, ?);",
                    returnColumns
            );
            query.setLong(1, userId);
            query.setString(2, university.getName());
            query.setString(3, university.getCountry());
            query.setString(4, university.getCity());
            query.setInt(5, university.getYearsFrom());
            query.setInt(6, university.getYearsTo());
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
    public long deleteUserFromUniversity(long userId, University university) throws PersistException {
        int result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "DELETE FROM lawAndSocialDb.university" +
                            " WHERE id = ? AND user_id = ?;"
            );
            query.setLong(1, university.getId());
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
    public long updateUniversityByUserId(long userId, University university) throws PersistException {
        long result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "UPDATE lawAndSocialDb.university" +
                            " SET name = ?, country = ?, city = ?, yearsFrom = ?, yearsTo = ?" +
                            " WHERE id = ? AND user_id = ?;"
            );
            query.setString(1, university.getName());
            query.setString(2, university.getCountry());
            query.setString(3, university.getCity());
            query.setInt(4, university.getYearsFrom());
            query.setInt(5, university.getYearsTo());
            query.setLong(6, university.getId());
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
