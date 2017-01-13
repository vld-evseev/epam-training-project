package com.epam.training.lawAndSocial.web.servlet.community;

import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.service.model.FollowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Singleton
public class FollowerServlet extends AbstractCommunityServlet {

    private final static Logger LOGGER = LoggerFactory.getLogger(FollowingServlet.class);
    private final FollowService followService;

    @Inject
    public FollowerServlet(FollowService followService) {
        super(followService);
        this.followService = followService;
    }

    @Override
    protected long getNumberOfUsers(long userId) {
        return followService.getFollowersNumber(userId);
    }

    @Override
    protected List<User> getRequestedUsers(long userId, int currentPageNumber) {
        final int currentOffset = (currentPageNumber - 1) * USERS_PER_PAGE;
        return followService.getFollowers(userId, currentOffset);
    }

    @Override
    protected void setPathAttribute(HttpServletRequest req) {
        req.setAttribute("path", "/user/followers");
    }
}
