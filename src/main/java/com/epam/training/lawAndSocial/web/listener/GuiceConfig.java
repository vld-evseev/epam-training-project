package com.epam.training.lawAndSocial.web.listener;

import com.epam.training.lawAndSocial.web.servlet.LocaleServlet;
import com.epam.training.lawAndSocial.web.servlet.RootServlet;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import javax.servlet.annotation.WebListener;

@WebListener
public class GuiceConfig extends GuiceServletContextListener {

    private static class DependencyModule extends AbstractModule {
        @Override
        protected void configure() {

        }
    }

    private static class ServletConfigModule extends ServletModule {
        @Override
        protected void configureServlets() {
            serve("/").with(RootServlet.class);
            serve("/locale").with(LocaleServlet.class);
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
