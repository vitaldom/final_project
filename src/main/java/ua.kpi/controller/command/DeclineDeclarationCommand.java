package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.inputcheck.InputChecker;
import ua.kpi.controller.path.ServletPath;
import ua.kpi.model.entities.Declaration;
import ua.kpi.model.services.declaration.DeclarationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeclineDeclarationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(DeclineDeclarationCommand.class);

    DeclarationService declarationService = new  DeclarationService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Declaration declaration = (Declaration) request.getSession().getAttribute("declarationToProceed");
        String declineMessage = request.getParameter("declineMessage");

        if (declineMessage == null) { //TODO check if this null, or empty string

            InputChecker.setSessionErrorMessage(request, "check.declaration.no.decline.message");
            response.sendRedirect(ServletPath.CHECK_DECLARATION);
            return;
        }

        declarationService.decline(declaration.getId(), declineMessage); //TODO consider adding checks/ try-catch

        InputChecker.setServiceMessage(request, "check.declaration.successful.decline");
        response.sendRedirect(ServletPath.VIEW_DECLARATIONS_FOR_CHECK);
    }
}
