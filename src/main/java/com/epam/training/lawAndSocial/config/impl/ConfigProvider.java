package com.epam.training.lawAndSocial.config.impl;

import com.epam.training.lawAndSocial.config.Config;
import com.google.inject.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProvider implements Provider<Config> {

    private final static Logger LOGGER = LoggerFactory.getLogger(ConfigProvider.class);

    @Override
    public Config get() {
        final Properties properties = new Properties();
        try (InputStream stream
                     = getClass().getClassLoader().getResourceAsStream("config/settings.properties")) {
            properties.load(stream);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }

        return new Config() {
            @Override
            public String getWebsocketHost() {
                return properties.getProperty("settings.websocket.host");
            }

            @Override
            public String getWebsocketPort() {
                return properties.getProperty("settings.websocket.port");
            }

            @Override
            public String getDatabaseString() {
                return properties.getProperty("settings.database");
            }
        };
    }
}
