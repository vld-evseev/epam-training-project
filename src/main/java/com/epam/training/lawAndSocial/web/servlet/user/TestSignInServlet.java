package com.epam.training.lawAndSocial.web.servlet.user;

import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.epam.training.lawAndSocial.utils.ServletParams.USER_ATTR;
import static com.epam.training.lawAndSocial.utils.ServletParams.USER_PAGE;

@Singleton
public class TestSignInServlet extends HttpServlet {

    private final UserService userService;
    private final static Logger LOGGER = LoggerFactory.getLogger(TestSignInServlet.class);

    @Inject
    public TestSignInServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Object userAttr = req.getSession(true).getAttribute(USER_ATTR);

        if (userAttr == null) {
            final Optional<User> testUser = userService.getByUsername("testUser");
            if (testUser.isPresent()) {
                LOGGER.debug("test user is present");
                req.getSession(true).setAttribute(USER_ATTR, testUser.get());
                resp.sendRedirect(req.getContextPath() + "/user");
                /*req.getRequestDispatcher(USER_PAGE).forward(req, resp);*/
                return;
            }
        }

        req.getRequestDispatcher(USER_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
