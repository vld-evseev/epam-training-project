package com.epam.training.lawAndSocial.web.listener;

import com.epam.training.lawAndSocial.service.SecurityService;
import com.epam.training.lawAndSocial.service.ValidationService;
import com.epam.training.lawAndSocial.service.impl.SecurityServiceImpl;
import com.epam.training.lawAndSocial.service.impl.ValidationServiceImpl;
import com.epam.training.lawAndSocial.web.servlet.*;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import javax.inject.Singleton;
import javax.servlet.annotation.WebListener;

@WebListener
public class GuiceConfig extends GuiceServletContextListener {

    private static class DependencyModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(ValidationService.class).to(ValidationServiceImpl.class).in(Singleton.class);
            bind(SecurityService.class).to(SecurityServiceImpl.class).in(Singleton.class);
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

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(
                new DependencyModule(),
                new ServletConfigModule()
        );
    }
}
