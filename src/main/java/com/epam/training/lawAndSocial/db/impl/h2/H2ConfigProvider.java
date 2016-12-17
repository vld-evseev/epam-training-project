package com.epam.training.lawAndSocial.db.impl.h2;

import com.epam.training.lawAndSocial.db.H2Config;
import com.google.inject.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class H2ConfigProvider implements Provider<H2Config> {

    private final static Logger LOGGER = LoggerFactory.getLogger(H2ConfigProvider.class);

    @Override
    public H2Config get() {
        final Properties properties = new Properties();
        try (InputStream stream
                     = getClass().getClassLoader().getResourceAsStream("config/h2.properties")) {
            properties.load(stream);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }

        return new H2Config() {
            @Override
            public String getUrl() {
                return properties.getProperty("h2.url");
            }

            @Override
            public String getUser() {
                return properties.getProperty("h2.user");
            }

            @Override
            public String getPassword() {
                return properties.getProperty("h2.password");
            }
        };
    }
}
