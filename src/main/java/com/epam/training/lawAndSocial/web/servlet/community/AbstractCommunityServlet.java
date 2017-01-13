package com.epam.training.lawAndSocial.web.servlet.community;

import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.service.model.FollowService;
import com.epam.training.lawAndSocial.utils.CheckUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.epam.training.lawAndSocial.utils.ServletParams.*;

@Singleton
public abstract class AbstractCommunityServlet extends HttpServlet {

    final static int USERS_PER_PAGE = 30;
    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractCommunityServlet.class);

    private final FollowService followService;

    @Inject
    protected AbstractCommunityServlet(FollowService followService) {
        this.followService = followService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession(true);
        final User user = (User) session.getAttribute("user");

        setPathAttribute(req);

        final Set<Long> followingIds = followService.getFollowingIds(user.getId());
        req.setAttribute("followingIds", followingIds);

        final long numberOfUsers = getNumberOfUsers(user.getId());
        final int pagesCount = getPagesCount(numberOfUsers);
        req.setAttribute(PAGES_COUNT_ATTR, pagesCount);

        final int currentPageNumber = getCurrentPageNumber(req);
        final List<User> requestedUsers = getRequestedUsers(user.getId(), currentPageNumber);
        req.setAttribute(USER_LIST_ATTR, requestedUsers);

        String queryString = getPageQueryString(req);
        req.getRequestDispatcher(COMMUNITY_JSP + "?" + queryString).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Map<String, String> params = collectParams(req);

        if (!paramsValidated(params)) {
            LOGGER.error("Params are invalid");
            doGet(req, resp);
        }

        final HttpSession session = req.getSession(true);
        final User currentUser = (User) session.getAttribute(USER_ATTR);

        final String userId = params.get(USER_ID_PARAM);
        final String actionParam = params.get(ACTION_PARAM);
        if (actionParam.equals("follow")) {
            followService.follow(currentUser.getId(), Long.parseLong(userId));
        }

        if (actionParam.equals("unfollow")) {
            followService.unfollow(currentUser.getId(), Long.parseLong(userId));
        }

        doGet(req, resp);
    }

    private boolean paramsValidated(Map<String, String> params) {
        if (params.containsValue(null) || params.containsValue("")) {
            LOGGER.error("request params are NULL or empty");
            return false;
        }

        final String userId = params.get(USER_ID_PARAM);
        if (!CheckUtils.isNumeric(userId)) {
            LOGGER.error("user id is violated: {}", userId);
            return false;
        }

        return true;
    }

    static Map<String, String> collectParams(HttpServletRequest req) {
        final Map<String, String> params = new HashMap<>();
        params.put(ACTION_PARAM, req.getParameter(ACTION_PARAM));
        params.put(USER_ID_PARAM, req.getParameter(USER_ID_PARAM));
        return params;
    }

    protected abstract long getNumberOfUsers(long userId);

    protected abstract List<User> getRequestedUsers(long userId, int currentPageNumber);

    protected abstract void setPathAttribute(HttpServletRequest req);

    static int getCurrentPageNumber(HttpServletRequest req) {
        final String pageParam = req.getParameter("page");
        int currentPageNumber = 1;
        if (pageParam != null && !pageParam.isEmpty() && CheckUtils.isNumeric(pageParam)) {
            currentPageNumber = Integer.parseInt(pageParam);
        }
        return currentPageNumber;
    }

    static String getPageQueryString(HttpServletRequest req) {
        String queryString = req.getQueryString();
        if (queryString == null || queryString.isEmpty() || !queryString.startsWith("page")) {
            queryString = "page=1";
        }
        return queryString;
    }

    static int getPagesCount(double numberOfUsers) {
        return (int) Math.ceil(numberOfUsers / USERS_PER_PAGE);
    }

}
