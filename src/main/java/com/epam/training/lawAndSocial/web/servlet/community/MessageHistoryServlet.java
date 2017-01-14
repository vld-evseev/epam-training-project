package com.epam.training.lawAndSocial.web.servlet.community;

import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.service.model.FollowService;
import com.epam.training.lawAndSocial.service.model.MessageHistoryService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Singleton
public class MessageHistoryServlet extends AbstractCommunityServlet {

    private final MessageHistoryService messageHistoryService;

    @Inject
    protected MessageHistoryServlet(FollowService followService, MessageHistoryService messageHistoryService) {
        super(followService);
        this.messageHistoryService = messageHistoryService;
    }

    @Override
    protected long getNumberOfUsers(long userId) {
        return messageHistoryService.getNumberOfContacts(userId);
    }

    @Override
    protected List<User> getRequestedUsers(long userId, int currentPageNumber) {
        final int currentOffset = (currentPageNumber - 1) * USERS_PER_PAGE;
        return messageHistoryService.getContacts(userId, currentOffset);
    }

    @Override
    protected void setPathAttribute(HttpServletRequest req) {
        req.setAttribute("path", "/user/messages");
    }
}
