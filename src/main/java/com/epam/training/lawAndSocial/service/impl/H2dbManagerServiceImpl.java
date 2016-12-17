package com.epam.training.lawAndSocial.service.impl;

import com.epam.training.lawAndSocial.db.DbManager;
import com.epam.training.lawAndSocial.db.H2Config;
import com.epam.training.lawAndSocial.db.impl.h2.H2ConfigProvider;
import com.epam.training.lawAndSocial.db.impl.h2.H2DatasourceProvider;
import com.epam.training.lawAndSocial.db.impl.h2.H2Manager;
import com.epam.training.lawAndSocial.service.DBManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class H2dbManagerServiceImpl implements DBManagerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(H2dbManagerServiceImpl.class);

    private final DbManager dbManager;

    private H2dbManagerServiceImpl() {
        final H2ConfigProvider provider = new H2ConfigProvider();
        final H2Config h2Config = provider.get();
        final H2DatasourceProvider dataSource = new H2DatasourceProvider(h2Config);
        this.dbManager = new H2Manager(dataSource.get());
    }

    public static DBManagerService getInstance() {
        return Wrapper.instance;
    }

    @Override
    public void initialize() {
        dbManager.create();
    }

    private static class Wrapper {
        private final static DBManagerService instance = new H2dbManagerServiceImpl();
    }


}
