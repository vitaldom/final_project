package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.inputcheck.ResourceBundleDispatcher;
import ua.kpi.controller.path.ServletPath;
import ua.kpi.model.entities.Declaration;
import ua.kpi.model.services.DeclarationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

import static ua.kpi.controller.TextConstants.DECLARATION_TO_PROCEED;
import static ua.kpi.controller.TextConstants.SERVICE_MESSAGE;

/**
 * Invoked when inspector approves a client's declaration. Declaration is stored in database with option for the client
 * to view it (no options for change).
 */
public class ApproveDeclarationCommand implements Command {

        private static final Logger LOGGER = LogManager.getLogger(ApproveDeclarationCommand.class);

        DeclarationService declarationService = new DeclarationService();

        @Override
        public void execute(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            Declaration declaration = (Declaration) request.getSession().getAttribute(DECLARATION_TO_PROCEED);

            LOGGER.trace("Declaration to be approved in ApproveDeclarationCommand: {}", declaration);

            declarationService.changeStatus(declaration.getId(), Declaration.Status.APPROVED.toString());

            ResourceBundle webInterface = ResourceBundleDispatcher.getResourceBundle(request);
            request.getSession().setAttribute(SERVICE_MESSAGE,
                    webInterface.getString("check.declaration.successful.approval"));

            redirect(request, response, ServletPath.VIEW_DECLARATIONS_FOR_CHECK);
        }
}
