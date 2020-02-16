package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.path.ServletPath;
import ua.kpi.model.entities.AbstractAppUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static ua.kpi.controller.TextConstants.LOGGED_USERS;
import static ua.kpi.controller.TextConstants.USER;

/**
 * Encapsulates logout logic.
 */
public class LogoutCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(LogoutCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            AbstractAppUser user = (AbstractAppUser) session.getAttribute(USER);
            Set<String> loggedUsers = (HashSet<String>) request.getServletContext().getAttribute(LOGGED_USERS);
            loggedUsers.remove(user.getLogin());

            LOGGER.info("Session invalidate for user : {}", user.getLogin());
            session.invalidate();

        } else {
            LOGGER.debug("Null session invalidation attempt");
        }

        response.sendRedirect(ServletPath.START_PAGE);
    }
}
