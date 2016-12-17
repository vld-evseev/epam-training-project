package com.epam.training.lawAndSocial.db.impl.pg;

import com.epam.training.lawAndSocial.db.PgConfig;
import com.google.inject.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PgConfigProvider implements Provider<PgConfig> {

    private final static Logger LOGGER = LoggerFactory.getLogger(PgConfigProvider.class);

    @Override
    public PgConfig get() {
        final Properties properties = new Properties();
        try (InputStream stream
                     = getClass().getClassLoader().getResourceAsStream("config/postgres.properties")) {
            properties.load(stream);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }

        return new PgConfig() {
            @Override
            public String getHost() {
                return properties.getProperty("postgres.host");
            }

            @Override
            public int getPort() {
                return Integer.parseInt(properties.getProperty("postgres.port"));
            }

            @Override
            public String getDbName() {
                return properties.getProperty("postgres.dbName");
            }

            @Override
            public String getUser() {
                return properties.getProperty("postgres.user");
            }

            @Override
            public String getPassword() {
                return properties.getProperty("postgres.password");
            }
        };
    }
}
