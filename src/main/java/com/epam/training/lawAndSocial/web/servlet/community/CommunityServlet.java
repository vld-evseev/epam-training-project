package com.epam.training.lawAndSocial.web.servlet.community;

import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.service.model.FollowService;
import com.epam.training.lawAndSocial.service.model.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Singleton
public class CommunityServlet extends AbstractCommunityServlet {

    private final static Logger LOGGER = LoggerFactory.getLogger(CommunityServlet.class);

    private final UserService userService;

    @Inject
    public CommunityServlet(UserService userService, FollowService followService) {
        super(followService);
        this.userService = userService;
    }

    @Override
    protected long getNumberOfUsers(long userId) {
        return userService.getNumberOfUsers();
    }

    @Override
    protected List<User> getRequestedUsers(long userId, int currentPageNumber) {
        final int currentOffset = (currentPageNumber - 1) * USERS_PER_PAGE;
        return userService.getUsers(currentOffset);
    }

    @Override
    protected void setPathAttribute(HttpServletRequest req) {
        req.setAttribute("path", "/community");
    }
}
