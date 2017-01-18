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
        flyway.migrate();
    }

    @After
    public void after() throws SQLException {
        flyway.clean();
        dropSchema();
    }
}
