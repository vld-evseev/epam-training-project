package com.epam.training.lawAndSocial.web.listener;

import com.epam.training.lawAndSocial.dao.*;
import com.epam.training.lawAndSocial.dao.pg.*;
import com.epam.training.lawAndSocial.db.H2Config;
import com.epam.training.lawAndSocial.db.impl.h2.H2ConfigProvider;
import com.epam.training.lawAndSocial.db.impl.h2.H2DatasourceProvider;
import com.epam.training.lawAndSocial.service.*;
import com.epam.training.lawAndSocial.service.impl.*;
import com.epam.training.lawAndSocial.service.model.ContactsService;
import com.epam.training.lawAndSocial.service.model.EducationService;
import com.epam.training.lawAndSocial.service.model.FollowService;
import com.epam.training.lawAndSocial.service.model.impl.ContactsServiceImpl;
import com.epam.training.lawAndSocial.service.model.impl.EducationServiceImpl;
import com.epam.training.lawAndSocial.service.model.impl.FollowServiceImpl;
import com.epam.training.lawAndSocial.web.filter.CharsetFilter;
import com.epam.training.lawAndSocial.web.filter.LoggedInFilter;
import com.epam.training.lawAndSocial.web.servlet.*;
import com.epam.training.lawAndSocial.web.servlet.community.CommunityServlet;
import com.epam.training.lawAndSocial.web.servlet.community.FollowerServlet;
import com.epam.training.lawAndSocial.web.servlet.community.FollowingServlet;
import com.epam.training.lawAndSocial.web.servlet.user.ProfileEditServlet;
import com.epam.training.lawAndSocial.web.servlet.user.ProfileServlet;
import com.epam.training.lawAndSocial.web.servlet.user.TestSignInServlet;
import com.epam.training.lawAndSocial.web.servlet.user.edit.CommonInfoServlet;
import com.epam.training.lawAndSocial.web.servlet.user.edit.ContactsInfoServlet;
import com.epam.training.lawAndSocial.web.servlet.user.edit.EducationInfoServlet;
import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Binding;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import javax.inject.Singleton;
import javax.servlet.ServletContextEvent;
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
            bind(ContactsDao.class).to(PgContactsDao.class).in(Singleton.class);
            bind(SchoolDao.class).to(PgSchoolDao.class).in(Singleton.class);
            bind(UniversityDao.class).to(PgUniversityDao.class).in(Singleton.class);
            bind(FollowDao.class).to(PgFollowDao.class).in(Singleton.class);
            bind(MessageHistoryDao.class).to(PgMessageHistoryDao.class).in(Singleton.class);
        }
    }

    private static class ServicesModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(ValidationService.class).to(ValidationServiceImpl.class).in(Singleton.class);
            bind(SecurityService.class).to(SecurityServiceImpl.class).in(Singleton.class);
            bind(UserService.class).to(UserServiceImpl.class).in(Singleton.class);
            bind(EducationService.class).to(EducationServiceImpl.class).in(Singleton.class);
            bind(ContactsService.class).to(ContactsServiceImpl.class).in(Singleton.class);
            bind(FollowService.class).to(FollowServiceImpl.class).in(Singleton.class);
            bind(ChatService.class).to(ChatServiceImpl.class).in(Singleton.class);
            bind(MessageHistoryService.class).to(MessageHistoryServiceImpl.class).in(Singleton.class);
            bind(Gson.class).in(Singleton.class);
        }
    }

    private static class ServletConfigModule extends ServletModule {
        @Override
        protected void configureServlets() {
            serve("/").with(RootServlet.class);
            serve("/locale").with(LocaleServlet.class);
            serve("/login").with(LoginServlet.class);
            serve("/registration").with(RegistrationServlet.class);
            serve("/community").with(CommunityServlet.class);
            serve("/user").with(ProfileServlet.class);
            serve("/user/edit").with(ProfileEditServlet.class);
            serve("/user/edit/common").with(CommonInfoServlet.class);
            serve("/user/edit/contacts").with(ContactsInfoServlet.class);
            serve("/user/edit/education").with(EducationInfoServlet.class);
            serve("/user/following").with(FollowingServlet.class);
            serve("/user/followers").with(FollowerServlet.class);
            serve("/user/messages").with(MessageServlet.class);
            serve("/user/message").with(MessageServlet.class);
            serve("/signout").with(SignoutServlet.class);
            serve("/tlg").with(TestSignInServlet.class);

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

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //super.contextInitialized(servletContextEvent);
        final Binding<ChatService> binding = getInjector().getBinding(ChatService.class);
        binding.getProvider().get().startup();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //super.contextDestroyed(servletContextEvent);
        final Binding<ChatService> binding = getInjector().getBinding(ChatService.class);
        binding.getProvider().get().shutdown();
    }
}
