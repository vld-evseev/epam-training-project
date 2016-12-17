package com.epam.training.lawAndSocial.listener;

import com.epam.training.lawAndSocial.service.DBManagerService;
import com.epam.training.lawAndSocial.service.impl.H2dbManagerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class StartupListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartupListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.debug("Context initialized");
        DBManagerService dbManager = H2dbManagerServiceImpl.getInstance();
        dbManager.initialize();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
