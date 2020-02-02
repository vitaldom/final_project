package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.path.JspPath;
import ua.kpi.model.entities.AbstractAppUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RoleMenuCommand implements Command {

        private static final Logger LOGGER = LogManager.getLogger(RoleMenuCommand.class);

        private final Map<String, String> path = new HashMap<>();

        {
            path.put("INSPECTOR", JspPath.INSPECTOR_MENU_PAGE);
            path.put("CLIENT", JspPath.CLIENT_MENU_PAGE);
        }

        @Override
        public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            AbstractAppUser user = (AbstractAppUser) request.getSession().getAttribute("user");

            LOGGER.debug("Entered execution in RoleMenuCommand");

            LOGGER.debug("Entering menu page for {} : {}", user.getLogin(), path.get(user.getRole()));

            forward(request, response, path.get(user.getRole()));
        }

}
