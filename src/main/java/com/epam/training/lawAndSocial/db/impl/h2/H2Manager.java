package com.epam.training.lawAndSocial.db.impl.h2;

import com.epam.training.lawAndSocial.db.DbManager;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class H2Manager implements DbManager {

    private final static Logger LOGGER = LoggerFactory.getLogger(H2Manager.class);

    private final DataSource dataSource;
    private final Flyway flyway;

    public H2Manager(DataSource dataSource) {
        this.dataSource = dataSource;
        this.flyway = new Flyway();
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
