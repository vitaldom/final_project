package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.inputcheck.InputChecker;
import ua.kpi.controller.path.ServletPath;
import ua.kpi.model.entities.Declaration;
import ua.kpi.model.services.DeclarationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.kpi.controller.TextConstants.DECLARATION_TO_PROCEED;
import static ua.kpi.controller.TextConstants.DECLINE_MESSAGE;

/**
 * Invoked when inspector chooses to decline a declaration, with obligatory reason message. Declaration becomes
 * available for a client for correction.
 */
public class DeclineDeclarationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(DeclineDeclarationCommand.class);

    DeclarationService declarationService = new  DeclarationService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Declaration declaration = (Declaration) request.getSession().getAttribute(DECLARATION_TO_PROCEED);
        String declineMessage = request.getParameter(DECLINE_MESSAGE);

        if (declineMessage == null) {

            InputChecker.setSessionErrorMessage(request, "check.declaration.no.decline.message");
            response.sendRedirect(ServletPath.CHECK_DECLARATION);
            return;
        }

        declarationService.decline(declaration.getId(), declineMessage);

        InputChecker.setServiceMessage(request, "check.declaration.successful.decline");
        response.sendRedirect(ServletPath.VIEW_DECLARATIONS_FOR_CHECK);
    }
}
