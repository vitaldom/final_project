package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.inputcheck.InputChecker;
import ua.kpi.controller.path.JspPath;
import ua.kpi.controller.path.ServletPath;
import ua.kpi.model.entities.Declaration;
import ua.kpi.model.entities.InspectorChangeRequest;
import ua.kpi.model.services.DeclarationService;
import ua.kpi.model.services.InspectorChangeRequestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.kpi.controller.TextConstants.DECLARATION_TO_SHOW;

/**
 * Invoked when client user chooses to send request for inspector change. This option becomes available
 * if the declaration had been previously declined by inspector. Declaration stays available for the client for view
 * (but not correction). New inspector change request is stored to database.
 */
public class InspectorChangeCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(InspectorChangeCommand.class);
    private static final String INSPECTOR_CHANGE_COMMENT = "inspectorChangeComment";

    InspectorChangeRequestService inspectorChangeRequestService = new InspectorChangeRequestService();
    DeclarationService declarationService = new DeclarationService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String inspectorChangeComment = request.getParameter(INSPECTOR_CHANGE_COMMENT);

        if (inspectorChangeComment == null || inspectorChangeComment.equals("")) {
            forward(request, response, JspPath.CORRECT_DECLARATION_PAGE);
            return;
        }

        Declaration appealedDeclaration = (Declaration) request.getSession().getAttribute(DECLARATION_TO_SHOW);

        InspectorChangeRequest inspectorChangeRequest
                = new InspectorChangeRequest(appealedDeclaration, inspectorChangeComment);

        boolean tmp = inspectorChangeRequestService.create(inspectorChangeRequest);

        LOGGER.debug("New new inspector change request written to database, value of tmp: {} ", tmp);

        declarationService.changeStatus(appealedDeclaration.getId(), Declaration.Status.APPEALED.toString());

        if (tmp) {
             InputChecker.setServiceMessage(request, "new.inspector.change.request.successful.submission");

             redirect(request, response, ServletPath.MENU);
             return;
        }

        forward(request, response, JspPath.CLIENT_MENU_PAGE);
    }
}
