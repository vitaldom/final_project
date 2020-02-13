package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.inputcheck.InputChecker;
import ua.kpi.controller.path.ServletPath;
import ua.kpi.model.entities.AbstractAppUser;
import ua.kpi.model.services.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.apache.commons.lang3.ObjectUtils.allNotNull;


public class LoginCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (!allNotNull(login, password)) {
            InputChecker.setSessionErrorMessage(request, "login.invalid.combination");
            response.sendRedirect(ServletPath.START_PAGE);
            return;
        }

        UserService userService = new UserService();
        Optional<AbstractAppUser> suggestedUser = Optional.ofNullable(userService.find(login, password));

        if (suggestedUser.isPresent()) {
            Set<String> loggedUsers = (HashSet<String>) request.getServletContext().getAttribute("loggedUsers");
            loggedUsers.add(login);
            AbstractAppUser user = suggestedUser.get();
            request.getSession().setAttribute("user", user);

            LOGGER.info("User logged in: {} :", login);

            redirect(request, response, ServletPath.MENU);

        } else {
            LOGGER.warn("Attempt to login with invalid credentials. Login: {} Password: {}", login, password);

            InputChecker.setSessionErrorMessage(request, "login.invalid.combination");
            response.sendRedirect(ServletPath.START_PAGE);
        }
    }
}
