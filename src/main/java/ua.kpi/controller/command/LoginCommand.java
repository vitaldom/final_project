package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.inputcheck.InputChecker;
import ua.kpi.controller.path.ServletPath;
import ua.kpi.model.entities.AbstractAppUser;
import ua.kpi.model.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.apache.commons.lang3.ObjectUtils.allNotNull;
import static ua.kpi.controller.TextConstants.*;


public class LoginCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    UserService userService = new UserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        if (!allNotNull(login, password)) {
            InputChecker.setSessionErrorMessage(request, "login.invalid.combination");
            response.sendRedirect(ServletPath.START_PAGE);
            return;
        }

        Optional<AbstractAppUser> suggestedUser = Optional.ofNullable(userService.find(login, password));

        if (suggestedUser.isPresent()) {
            Set<String> loggedUsers = (HashSet<String>) request.getServletContext().getAttribute(LOGGED_USERS);
            loggedUsers.add(login);
            AbstractAppUser user = suggestedUser.get();
            request.getSession().setAttribute(USER, user);

            LOGGER.info("User logged in: {} :", login);

            response.sendRedirect(ServletPath.MENU);

        } else {
            LOGGER.warn("Attempt to login with invalid credentials. Login: {} Password: {}", login, password);

            InputChecker.setSessionErrorMessage(request, "login.invalid.combination");
            response.sendRedirect(ServletPath.START_PAGE);
        }
    }
}
