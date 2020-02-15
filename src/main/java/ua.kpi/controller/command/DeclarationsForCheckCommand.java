package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.path.JspPath;
import ua.kpi.model.entities.Declaration;
import ua.kpi.model.entities.InspectorUser;
import ua.kpi.model.services.declaration.DeclarationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeclarationsForCheckCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(DeclarationsForCheckCommand.class);

    DeclarationService declarationService = new DeclarationService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        InspectorUser user = (InspectorUser) request.getSession().getAttribute("user");

        List<Declaration> declarationList = declarationService.findAllByInspectorLogin(user.getLogin());

        request.getSession().setAttribute("declarationList", declarationList);

        LOGGER.info("Showing declarations for check for user {}", user.getLogin());

        forward(request, response, JspPath.DECLARATIONS_FOR_CHECK_PAGE);
    }
}
