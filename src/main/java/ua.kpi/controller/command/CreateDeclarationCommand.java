package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.path.JspPath;
import ua.kpi.controller.path.ServletPath;
import ua.kpi.controller.inputcheck.InputChecker;
import ua.kpi.model.entities.ClientUser;
import ua.kpi.model.entities.Declaration;
import ua.kpi.model.services.DeclarationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static ua.kpi.controller.TextConstants.*;

import static org.apache.commons.lang3.ObjectUtils.allNotNull;

/**
 * Encapsulates logic for creation of a new declaration.
 */
public class CreateDeclarationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CreateDeclarationCommand.class);

    DeclarationService declarationService = new DeclarationService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter(FIRST_NAME);
        String secondName = request.getParameter(SECOND_NAME);
        String declarationYear = request.getParameter(DECLARATION_YEAR);
        String taxCategory = request.getParameter(TAX_CATEGORY);
        String income = request.getParameter(INCOME);
        String taxSumDeclared = request.getParameter(TAX_SUM_DECLARED);

        if (!allNotNull(firstName, secondName, declarationYear, taxCategory, income, taxSumDeclared)) { //TODO add error message
            forward(request, response, JspPath.NEW_DECLARATION_PAGE);
            return;
        }

        ClientUser user = (ClientUser) request.getSession().getAttribute(USER);

        if (!InputChecker.checkDeclarationDataValidity(request, user, firstName, secondName, income, taxSumDeclared)) {
            response.sendRedirect(ServletPath.NEW_DECLARATION);
            return;
        }

        Declaration declaration = new Declaration.Builder().author(user).authorLogin(user.getLogin())
                .declarationYear(Declaration.DeclarationYear.valueOf(YEAR_ + declarationYear))
                .taxCategory(Declaration.TaxCategory.valueOf(taxCategory.toUpperCase()))
                .income(Long.parseLong(income)).taxSumDeclared(Long.parseLong(taxSumDeclared))
                .status(Declaration.Status.valueOf(SUBMITTED)).build(); //TODO consider revising enum/String usage of Status

        LOGGER.debug("New declaration object created: {} ", declaration);

        boolean tmp = declarationService.create(declaration); //TODO consider use of tmp

        LOGGER.debug("New declaration written to database, value of tmp: {} ", tmp);
                                                                        //TODO consider check for actual insert into DB

        if (tmp) {
             InputChecker.setServiceMessage(request, "new.declaration.successful.submission");

             redirect(request, response, ServletPath.MENU);
             return;
        }

        forward(request, response, JspPath.CLIENT_MENU_PAGE); // TODO test if this forward ever happens
    }
}
