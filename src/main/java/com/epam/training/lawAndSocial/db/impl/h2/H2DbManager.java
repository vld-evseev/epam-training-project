package com.epam.training.lawAndSocial.db.impl.h2;

import com.epam.training.lawAndSocial.db.DbManager;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.sql.DataSource;

public class H2DbManager implements DbManager {

    private final static Logger LOGGER = LoggerFactory.getLogger(H2DbManager.class);

    private final Flyway flyway;
    private final DataSource dataSource;

    @Inject
    public H2DbManager(Flyway flyway, DataSource dataSource) {
        this.flyway = flyway;
        this.dataSource = dataSource;
        this.flyway.setDataSource(dataSource);
        this.flyway.setLocations("db.migrate");
    }

    @Override
    public void create() {
        flyway.migrate();
        LOGGER.debug("DB was initialized");
    }

    @Override
    public void drop() {
        throw new UnsupportedOperationException();
    }
}
