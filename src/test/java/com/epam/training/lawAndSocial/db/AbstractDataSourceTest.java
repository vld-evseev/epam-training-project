package com.epam.training.lawAndSocial.db;

import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.Before;

import javax.sql.DataSource;
import java.sql.SQLException;

public abstract class AbstractDataSourceTest {

    private final Flyway flyway;

    protected abstract DataSource getDataSource();

    protected abstract void dropSchema() throws SQLException;

    AbstractDataSourceTest() {
        flyway = new Flyway();
        flyway.setDataSource(getDataSource());
        flyway.setLocations("db.migrate");
    }

    @Before
    public void before() throws SQLException {
        //dropSchema();
        flyway.migrate();
    }

    @After
    public void after() throws SQLException {
        flyway.clean();
        dropSchema();
    }

    /*@Test
    public void createAndGetUsersTest() throws SQLException {
        try (Connection connection = getDataSource().getConnection()) {
            final PreparedStatement query = connection
                    .prepareStatement("SELECT * FROM lawAndSocialDb.user;");
            final ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                System.out.printf("id: [%d], username: [%s], firstName: [%s], lastName: [%s], email: [%s], birthdate: [%s], hash: [%s]\n",
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("email"),
                        resultSet.getString("birthdate"),
                        resultSet.getString("passwordHash")
                );
            }
        }
    }*/


}
