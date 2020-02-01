package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.path.JspPath;
import ua.kpi.model.entities.Declaration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CheckDeclarationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CheckDeclarationCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            Declaration declarationToShow = null; //TODO avoid assigning to null
            int id = Integer.parseInt(request.getParameter("declaration_id")); //TODO add checks for these operations.
                                                                                    //TODO possibly write to session

            List<Declaration> declarationList = (List<Declaration>) request.getSession().getAttribute("declarationList");

            for (Declaration s : declarationList) {
                if (s.getId() == id) {
                    declarationToShow = s;
                }
            }

        request.setAttribute("declarationToShow", declarationToShow);

        forward(request, response, JspPath.CHECK_DECLARATION_PAGE);
    }
}
