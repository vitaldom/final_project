package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.inputcheck.InputChecker;
import ua.kpi.controller.path.JspPath;
import ua.kpi.controller.path.ServletPath;
import ua.kpi.model.entities.ClientUser;
import ua.kpi.model.entities.Declaration;
import ua.kpi.model.services.DeclarationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.commons.lang3.ObjectUtils.allNotNull;
import static ua.kpi.controller.TextConstants.*;

public class CorrectDeclarationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CorrectDeclarationCommand.class);

    DeclarationService declarationService = new DeclarationService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter(FIRST_NAME);
        String secondName = request.getParameter(SECOND_NAME);
        String declarationYear = request.getParameter(DECLARATION_YEAR);
        String taxCategory = request.getParameter(TAX_CATEGORY);
        String income = request.getParameter(INCOME);
        String taxSumDeclared = request.getParameter(TAX_SUM_DECLARED);

        if (!allNotNull(firstName, secondName, declarationYear, taxCategory, income, taxSumDeclared)) {
            forward(request, response, JspPath.CORRECT_DECLARATION_PAGE);
            return;
        }

        ClientUser user = (ClientUser) request.getSession().getAttribute(USER);

        if (!InputChecker.checkDeclarationDataValidity(request, user, firstName, secondName, income, taxSumDeclared)) {
            forward(request, response, JspPath.CORRECT_DECLARATION_PAGE);
            return;
        }

        Declaration correctedDeclaration = (Declaration) request.getSession().getAttribute(DECLARATION_TO_SHOW);

        correctedDeclaration.setDeclarationYear(Declaration.DeclarationYear.valueOf(YEAR_ + declarationYear));
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
