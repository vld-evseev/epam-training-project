package com.epam.training.lawAndSocial.web.servlet.user.edit;

import com.epam.training.lawAndSocial.model.Gender;
import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.service.UserService;
import com.epam.training.lawAndSocial.service.ValidationService;
import com.epam.training.lawAndSocial.utils.DateValidator;
import com.epam.training.lawAndSocial.web.servlet.model.FormValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.epam.training.lawAndSocial.utils.ServletParams.*;

@Singleton
public class CommonInfoServlet extends HttpServlet {

    private final static Logger LOGGER = LoggerFactory.getLogger(CommonInfoServlet.class);
    private final UserService userService;
    private final ValidationService validationService;

    @Inject
    public CommonInfoServlet(UserService userService, ValidationService validationService) {
        this.userService = userService;
        this.validationService = validationService;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PROFILE_EDIT_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Map<String, Boolean> activeTab = new HashMap<>();
        activeTab.put("commonInfoTab", true);
        req.setAttribute(ACTIVE_TAB_ATTR, activeTab);

        final User currentUser = (User) req.getSession(true).getAttribute(USER_ATTR);

        if (currentUser == null) {
            LOGGER.error("user not exists in session");
            resp.sendRedirect(req.getContextPath() + "/user/edit/common");
            return;
        }

        final Map<String, String> params = collectParams(req);
        final FormValidation validation = validationService.verify(collectVerifiedParams(req));

        final String avatar = getImageSrc(currentUser, params);

        final User updatedUser = User.builder()
                .id(currentUser.getId())
                .userName(currentUser.getUserName())
                .firstName(params.get(FIRSTNAME_PARAM))
                .lastName(params.get(LASTNAME_PARAM))
                .patronymic(params.get(PATRONYMIC_PARAM))
                .gender(Gender.valueOf(params.get(GENDER_PARAM)))
                .avatar(avatar)
                .date(DateValidator.parseDate(params.get(BIRTH_DATE_PARAM),
                        DateValidator.Pattern.DD_MM_YYYY,
                        validation))
                .build();

        if (validation.isValid()) {
            update(updatedUser, validation);
            if (!avatar.equals(currentUser.getAvatar())) {
                userService.updateAvatar(currentUser.getId(), avatar);
            }
        }

        if (!validation.isValid()) {
            req.setAttribute(VALIDATION_ATTR, validation);
            req.getRequestDispatcher(PROFILE_EDIT_JSP).forward(req, resp);
            return;
        }

        req.getSession(true).setAttribute(USER_ATTR, updatedUser);
        resp.sendRedirect(req.getContextPath() + "/user/edit");
    }

    static String getImageSrc(User currentUser, Map<String, String> params) {
        final String av = params.get(AVATAR_SRC_PARAM);
        if (av != null && !av.isEmpty()) {
            return av;
        }

        return currentUser.getAvatar();
    }

    void update(User user, FormValidation validation) {
        final long id = userService.update(user);
        if (id < 0) {
            LOGGER.error("error while updating user {}", user);
            validation.getErrors().put(
                    "INTERNAL_ERROR",
                    true
            );
        }
    }

    private Map<String, String> collectParams(HttpServletRequest req) {
        final Map<String, String> params = new HashMap<>();
        params.put(FIRSTNAME_PARAM, req.getParameter(FIRSTNAME_PARAM));
        params.put(LASTNAME_PARAM, req.getParameter(LASTNAME_PARAM));
        params.put(PATRONYMIC_PARAM, req.getParameter(PATRONYMIC_PARAM));
        params.put(GENDER_PARAM, req.getParameter(GENDER_PARAM));
        params.put(BIRTH_DATE_PARAM, req.getParameter(BIRTH_DATE_PARAM));
        params.put(AVATAR_SRC_PARAM, req.getParameter(AVATAR_SRC_PARAM));
        return params;
    }

    private Map<String, String> collectVerifiedParams(HttpServletRequest req) {
        final Map<String, String> params = new HashMap<>();
        params.put(FIRSTNAME_PARAM, req.getParameter(FIRSTNAME_PARAM));
        params.put(LASTNAME_PARAM, req.getParameter(LASTNAME_PARAM));
        params.put(BIRTH_DATE_PARAM, req.getParameter(BIRTH_DATE_PARAM));
        return params;
    }
}
