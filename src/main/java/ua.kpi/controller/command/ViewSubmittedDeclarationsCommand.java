package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.path.JspPath;
import ua.kpi.model.entities.ClientUser;
import ua.kpi.model.entities.Declaration;
import ua.kpi.model.services.DeclarationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ua.kpi.controller.TextConstants.DECLARATION_LIST;
import static ua.kpi.controller.TextConstants.USER;

public class ViewSubmittedDeclarationsCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ViewSubmittedDeclarationsCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DeclarationService declarationService = new DeclarationService();

        ClientUser user = (ClientUser) request.getSession().getAttribute(USER);
        List<Declaration> declarationList = declarationService.findAllByClientLogin(user.getLogin());

        request.getSession().setAttribute(DECLARATION_LIST, declarationList);

        forward(request, response, JspPath.SUBMITTED_DECLARATIONS_PAGE);
    }
}
