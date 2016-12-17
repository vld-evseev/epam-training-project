package com.epam.training.lawAndSocial.web.listener;

import com.epam.training.lawAndSocial.dao.UserDao;
import com.epam.training.lawAndSocial.dao.pg.PgUserDao;
import com.epam.training.lawAndSocial.db.H2Config;
import com.epam.training.lawAndSocial.db.impl.h2.H2ConfigProvider;
import com.epam.training.lawAndSocial.db.impl.h2.H2DatasourceProvider;
import com.epam.training.lawAndSocial.service.SecurityService;
import com.epam.training.lawAndSocial.service.UserService;
import com.epam.training.lawAndSocial.service.ValidationService;
import com.epam.training.lawAndSocial.service.impl.SecurityServiceImpl;
import com.epam.training.lawAndSocial.service.impl.UserServiceImpl;
import com.epam.training.lawAndSocial.service.impl.ValidationServiceImpl;
import com.epam.training.lawAndSocial.web.filter.CharsetFilter;
import com.epam.training.lawAndSocial.web.filter.LoggedInFilter;
import com.epam.training.lawAndSocial.web.servlet.*;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import javax.inject.Singleton;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class GuiceConfig extends GuiceServletContextListener {

    private static class H2DbModule extends AbstractModule {

        @Override
        protected void configure() {
            bind(H2Config.class).toProvider(H2ConfigProvider.class).in(Singleton.class);
            bind(DataSource.class).toProvider(H2DatasourceProvider.class).in(Singleton.class);
            bind(UserDao.class).to(PgUserDao.class).in(Singleton.class);
        }
    }

    private static class ServicesModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(ValidationService.class).to(ValidationServiceImpl.class).in(Singleton.class);
            bind(SecurityService.class).to(SecurityServiceImpl.class).in(Singleton.class);
            bind(UserService.class).to(UserServiceImpl.class).in(Singleton.class);
        }
    }

    private static class ServletConfigModule extends ServletModule {
        @Override
        protected void configureServlets() {
            serve("/").with(RootServlet.class);
            serve("/locale").with(LocaleServlet.class);
            serve("/login").with(LoginServlet.class);
            serve("/registration").with(RegistrationServlet.class);
            serve("/profile").with(ProfileServlet.class);
        }
    }

    private static class FilterConfigModule extends ServletModule {
        @Override
        protected void configureServlets() {
            filter("/*").through(CharsetFilter.class);
            filter("/*").through(LoggedInFilter.class);
        }
    }

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(
                new ServicesModule(),
                new ServletConfigModule(),
                new FilterConfigModule(),
                new H2DbModule()
        );
    }
}
