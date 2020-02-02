package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.path.JspPath;
import ua.kpi.model.entities.Declaration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CheckDeclarationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CheckDeclarationCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("decIndexInArray") == null) {        //on-page language change case
            forward(request, response, JspPath.CHECK_DECLARATION_PAGE);
        }

        int index = Integer.parseInt(request.getParameter("decIndexInArray")); //TODO add checks for these operations.
        ArrayList<Declaration> declarationList = (ArrayList<Declaration>) request.getSession().getAttribute("declarationList");
        Declaration declarationToProceed = declarationList.get(index);

        request.getSession().setAttribute("declarationToProceed", declarationToProceed);

        forward(request, response, JspPath.CHECK_DECLARATION_PAGE);
    }
}
