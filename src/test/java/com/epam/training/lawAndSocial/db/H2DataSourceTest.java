package com.epam.training.lawAndSocial.db;

import com.epam.training.lawAndSocial.db.impl.h2.H2ConfigProvider;
import com.epam.training.lawAndSocial.db.impl.h2.H2DatasourceProvider;
import org.junit.BeforeClass;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class H2DataSourceTest extends AbstractDataSourceTest {

    private static DataSource dataSource;

    @BeforeClass
    public static void startup() {
        final H2Config h2Config = new H2ConfigProvider().get();
        final H2DatasourceProvider h2DatasourceProvider = new H2DatasourceProvider(new H2Config() {
            @Override
            public String getUrl() {
                return h2Config.getUrl();
            }

            @Override
            public String getUser() {
                return h2Config.getUser();
            }

            @Override
            public String getPassword() {
                return h2Config.getPassword();
            }
        });

        dataSource = h2DatasourceProvider.get();
    }

    @Override
    protected DataSource getDataSource() {
        return dataSource;
    }

    @Override
    protected void dropSchema() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement statement =
                    connection.prepareStatement("DROP SCHEMA IF EXISTS lawAndSocialDb;");
            statement.execute();
        }
    }
}
