package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.path.ServletPath;
import ua.kpi.model.entities.Declaration;
import ua.kpi.model.services.declaration.DeclarationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

public class ApproveDeclarationCommand implements Command {

        private static final Logger LOGGER = LogManager.getLogger(ApproveDeclarationCommand.class);

        @Override
        public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            Declaration declaration = (Declaration) request.getSession().getAttribute("declarationToProceed");

            LOGGER.trace("Declaration to be approved in ApproveDeclarationCommand: {}", declaration);

            DeclarationService declarationService = new  DeclarationService();

            declarationService.changeStatus(declaration.getId(), "APPROVED");    //TODO consider adding checks/ try-catch
                                                                                // TODO Hardcoding here - bad practice?

            ResourceBundle webInterface = ResourceBundleDispathcher.getResourceBundle(request);
            request.getSession().setAttribute("service_message",
                    webInterface.getString("check.declaration.successful.approval"));

            redirect(request, response, ServletPath.VIEW_DECLARATIONS_FOR_CHECK);
        }
}