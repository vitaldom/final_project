package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.path.JspPath;
import ua.kpi.controller.path.ServletPath;
import ua.kpi.model.entities.Declaration;
import ua.kpi.model.services.declaration.DeclarationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

public class DeclineDeclarationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(DeclineDeclarationCommand.class);
    DeclarationService declarationService = new  DeclarationService(); //TODO consider reworking to singleton

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Declaration declaration = (Declaration) request.getSession().getAttribute("declarationToProceed");
        String declineMessage = request.getParameter("declineMessage");
        ResourceBundle webInterface = ResourceBundleDispathcher.getResourceBundle(request);

        if (declineMessage == null) { //TODO check if this null, or empty string

            request.setAttribute("errorMessage", webInterface.getString("check.declaration.no.decline.message"));

            forward(request, response, JspPath.CHECK_DECLARATION_PAGE);
            return;
        }

        declarationService.decline(declaration.getId(), declineMessage); //TODO consider adding checks/ try-catch

        request.getSession().setAttribute("service_message",
                webInterface.getString("check.declaration.successful.decline"));

        redirect(request, response, ServletPath.VIEW_DECLARATIONS_FOR_CHECK);
    }
}
