package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.inputcheck.InputChecker;
import ua.kpi.controller.path.JspPath;
import ua.kpi.controller.path.ServletPath;
import ua.kpi.model.entities.Declaration;
import ua.kpi.model.entities.InspectorChangeRequest;
import ua.kpi.model.services.declaration.DeclarationService;
import ua.kpi.model.services.inspectorchangerequest.InspectorChangeRequestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InspectorChangeCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(InspectorChangeCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String inspectorChangeComment = request.getParameter("inspectorChangeComment");

        if (inspectorChangeComment == null || inspectorChangeComment.equals("")) { //TODO add error message
            forward(request, response, JspPath.CORRECT_DECLARATION_PAGE);
            return;
        }

        Declaration appealedDeclaration = (Declaration) request.getSession().getAttribute("declarationToShow");

        InspectorChangeRequest inspectorChangeRequest
                = new InspectorChangeRequest(appealedDeclaration, inspectorChangeComment);

        InspectorChangeRequestService inspectorChangeRequestService = new InspectorChangeRequestService();
        boolean tmp = inspectorChangeRequestService.create(inspectorChangeRequest); //TODO consider use of tmp

        LOGGER.debug("New new inspector change request written to database, value of tmp: {} ", tmp);
        //TODO consider check for actual insert into DB

        DeclarationService declarationService = new DeclarationService();
        declarationService.changeStatus(appealedDeclaration.getId(), Declaration.Status.APPEALED.toString());
        //TODO Combine both database requests into transaction?

        if (tmp) {
             InputChecker.setServiceMessage(request, "new.inspector.change.request.successful.submission");

             redirect(request, response, ServletPath.MENU);
             return;
        }

        forward(request, response, JspPath.CLIENT_MENU_PAGE); // TODO test if this forward ever happens. Add error message?
    }
}