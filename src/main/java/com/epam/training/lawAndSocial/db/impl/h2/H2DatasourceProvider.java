package com.epam.training.lawAndSocial.db.impl.h2;

import com.epam.training.lawAndSocial.db.H2Config;
import com.google.inject.Provider;
import org.h2.jdbcx.JdbcDataSource;

import javax.inject.Inject;
import javax.sql.DataSource;

public class H2DatasourceProvider implements Provider<DataSource> {

    private final H2Config h2Config;

    @Inject
    public H2DatasourceProvider(H2Config h2Config) {
        this.h2Config = h2Config;
    }

    @Override
    public DataSource get() {
        final JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl(h2Config.getUrl());
        dataSource.setUser(h2Config.getUser());
        dataSource.setPassword(h2Config.getPassword());
        return dataSource;
    }
}
