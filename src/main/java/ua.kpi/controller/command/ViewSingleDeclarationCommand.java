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

public class ViewSingleDeclarationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ViewSingleDeclarationCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("decIndexInArray") == null) {        //on-page language change case
            forward(request, response, JspPath.SINGLE_DECLARATION_PAGE);
        }

        int index = Integer.parseInt(request.getParameter("decIndexInArray"));

        ArrayList<Declaration> declarationList =
                (ArrayList<Declaration>) request.getSession().getAttribute("declarationList");

        Declaration declarationToShow = declarationList.get(index);

        request.getSession().setAttribute("declarationToShow", declarationToShow);

        forward(request, response, JspPath.SINGLE_DECLARATION_PAGE);
    }
}
