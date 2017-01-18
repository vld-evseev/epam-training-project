package com.epam.training.lawAndSocial.web.servlet;

import com.epam.training.lawAndSocial.config.Config;
import com.epam.training.lawAndSocial.model.Message;
import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.service.model.MessageHistoryService;
import com.epam.training.lawAndSocial.service.model.UserService;
import com.epam.training.lawAndSocial.utils.CheckUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.epam.training.lawAndSocial.utils.ServletParams.MESSAGE_JSP;
import static com.epam.training.lawAndSocial.utils.ServletParams.USER_ATTR;

@Singleton
public class MessageServlet extends HttpServlet {

    private final static Logger LOGGER = LoggerFactory.getLogger(MessageServlet.class);

    private final Config config;
    private final UserService userService;
    private final MessageHistoryService messageHistoryService;

    @Inject
    public MessageServlet(Config config, UserService userService, MessageHistoryService messageHistoryService) {
        this.config = config;
        this.userService = userService;
        this.messageHistoryService = messageHistoryService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long opponentIdParam = getOpponentIdParam(req);
        final Optional<User> opponentOptional = userService.getUserById(opponentIdParam);
        if (opponentIdParam < 0 || !opponentOptional.isPresent()) {
            LOGGER.debug("attempting to request unexisting User by id param: {}", opponentIdParam);
            resp.sendRedirect(req.getContextPath());
            return;
        }

        final User user = (User) req.getSession(true).getAttribute(USER_ATTR);

        final List<Message> userMessageHistory = messageHistoryService.getByUserId(user.getId(), opponentIdParam);
        req.setAttribute("messageHistory", userMessageHistory);
        LOGGER.debug("opponent id: {}", opponentIdParam);
        LOGGER.debug("User {} message history size: {}", user.getUserName(), userMessageHistory.size());
        for (Message message : userMessageHistory) {
            LOGGER.debug(message.toString());
        }

        final User opponent = opponentOptional.get();
        req.setAttribute("opponent", opponent);

        final Long sessionId = createUserPairSessionId(user.getUuid(), opponent.getUuid());
        req.setAttribute("sessionId", sessionId);

        final String websocketHost = config.getWebsocketHost();
        req.setAttribute("websocketHost", websocketHost);

        final Integer websocketPort = Integer.valueOf(config.getWebsocketPort());
        req.setAttribute("websocketPort", websocketPort);

        final String queryString = req.getQueryString();
        req.getRequestDispatcher(MESSAGE_JSP + "?" + queryString).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private static Long createUserPairSessionId(String uuid1, String uuid2) {
        return (long) (Math.abs(uuid1.hashCode()) + Math.abs(uuid2.hashCode()));
    }

    static long getOpponentIdParam(HttpServletRequest req) {
        final String id = req.getParameter("id");
        long userId = -1;
        if (id != null && !id.isEmpty() && CheckUtils.isNumeric(id)) {
            userId = Long.parseLong(id);
        }
        return userId;
    }
}
