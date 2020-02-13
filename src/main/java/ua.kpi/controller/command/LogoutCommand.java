package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.path.JspPath;
import ua.kpi.controller.path.ServletPath;
import ua.kpi.model.entities.AbstractAppUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LogoutCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(LogoutCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            AbstractAppUser user = (AbstractAppUser) session.getAttribute("user");
            Set<String> loggedUsers = (HashSet<String>) request.getServletContext().getAttribute("loggedUsers");
            loggedUsers.remove(user.getLogin());

            LOGGER.info("Session invalidate for user : {}", user.getLogin());
            session.invalidate();

        } else {
            LOGGER.debug("Null session invalidation attempt");
        }

//        forward(request, response, JSPPath.LOGIN); TODO
        response.sendRedirect(ServletPath.START_PAGE);
    }
}