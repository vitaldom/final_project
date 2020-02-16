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

import static ua.kpi.controller.TextConstants.*;

/**
 * Renders page for client user to view a declined declaration.
 */
public class ViewDeclinedDeclarationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ViewDeclinedDeclarationCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter(DECLARATION_INDEX_IN_ARRAY) == null) {        //on-page language change case
            forward(request, response, JspPath.CORRECT_DECLARATION_PAGE);
        }

        int index = Integer.parseInt(request.getParameter(DECLARATION_INDEX_IN_ARRAY));

        ArrayList<Declaration> declarationList =
                (ArrayList<Declaration>) request.getSession().getAttribute(DECLARATION_LIST);

        Declaration declarationToShow = declarationList.get(index);

        request.getSession().setAttribute(DECLARATION_TO_SHOW, declarationToShow);

        forward(request, response, JspPath.CORRECT_DECLARATION_PAGE);
    }
}
