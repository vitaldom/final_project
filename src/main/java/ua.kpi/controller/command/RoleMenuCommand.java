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

import static ua.kpi.controller.TextConstants.USER;

public class RoleMenuCommand implements Command {

        private static final Logger LOGGER = LogManager.getLogger(RoleMenuCommand.class);

        private final Map<String, String> path = new HashMap<>();

        {
            path.put(AbstractAppUser.Role.INSPECTOR.toString(), JspPath.INSPECTOR_MENU_PAGE);
            path.put(AbstractAppUser.Role.CLIENT.toString(), JspPath.CLIENT_MENU_PAGE);
        }

        @Override
        public void execute(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            AbstractAppUser user = (AbstractAppUser) request.getSession().getAttribute(USER);

            LOGGER.debug("Entering menu page for {} : {}", user.getLogin(), path.get(user.getRole()));

            forward(request, response, path.get(user.getRole()));
        }
}
