package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.inputcheck.InputChecker;
import ua.kpi.controller.path.JspPath;
import ua.kpi.controller.path.ServletPath;
import ua.kpi.model.entities.ClientUser;
import ua.kpi.model.entities.Declaration;
import ua.kpi.model.services.declaration.DeclarationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.commons.lang3.ObjectUtils.allNotNull;

public class CorrectDeclarationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CorrectDeclarationCommand.class);

    DeclarationService declarationService = new DeclarationService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        String declarationYear = request.getParameter("declarationYear");
        String taxCategory = request.getParameter("taxCategory");
        String income = request.getParameter("income");
        String taxSumDeclared = request.getParameter("taxSumDeclared");

        if (!allNotNull(firstName, secondName, declarationYear, taxCategory, income, taxSumDeclared)) {
            forward(request, response, JspPath.CORRECT_DECLARATION_PAGE);
            return;
        }

        ClientUser user = (ClientUser) request.getSession().getAttribute("user");

        if (!InputChecker.checkDeclarationDataValidity(request, user, firstName, secondName, income, taxSumDeclared)) {
            forward(request, response, JspPath.CORRECT_DECLARATION_PAGE);
            return;
        }

        Declaration correctedDeclaration = (Declaration) request.getSession().getAttribute("declarationToShow");

        correctedDeclaration.setDeclarationYear(Declaration.DeclarationYear.valueOf("YEAR_" + declarationYear));
        correctedDeclaration.setTaxCategory((Declaration.TaxCategory.valueOf(taxCategory)));
        correctedDeclaration.setIncome(Long.parseLong(income));
        correctedDeclaration.setTaxSumDeclared(Long.parseLong(taxSumDeclared));
        correctedDeclaration.setStatus(Declaration.Status.SUBMITTED);

        LOGGER.debug("Declaration object updated(corrected): {} ", correctedDeclaration);

        boolean tmp = declarationService.correct(correctedDeclaration); //TODO consider use of tmp

        LOGGER.debug("Corrected declaration written to database, value of tmp: {} ", tmp);
                                                                        //TODO consider check for actual insert into DB

        if(tmp) {
             InputChecker.setServiceMessage(request, "correct.declaration.successful.submission");

             redirect(request, response, ServletPath.MENU);
             return;
        }

        forward(request, response, JspPath.CLIENT_MENU_PAGE); // TODO test if this forward ever happens
    }
}
