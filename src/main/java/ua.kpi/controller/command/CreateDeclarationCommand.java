package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.path.JspPath;
import ua.kpi.controller.path.ServletPath;
import ua.kpi.model.InputChecker;
import ua.kpi.model.entities.ClientUser;
import ua.kpi.model.entities.Declaration;
import ua.kpi.model.services.declaration.DeclarationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.apache.commons.lang3.ObjectUtils.allNotNull;

public class CreateDeclarationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CreateDeclarationCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        String declarationYear = request.getParameter("declarationYear");
        String taxCategory = request.getParameter("taxCategory");
        String income = request.getParameter("income");
        String taxSumDeclared = request.getParameter("taxSumDeclared");

        if (!allNotNull(firstName, secondName, declarationYear, taxCategory, income, taxSumDeclared)) { //TODO add error message
            forward(request, response, JspPath.NEW_DECLARATION_PAGE);
            return;
        }

        ClientUser user = (ClientUser) request.getSession().getAttribute("user");

        if (!checkDataValidity(request, user, firstName, secondName, income, taxSumDeclared)) {
            forward(request, response, JspPath.NEW_DECLARATION_PAGE);
            return;
        }

        Declaration declaration = new Declaration.Builder().author(user).authorLogin(user.getLogin())
                .declarationYear(Declaration.DeclarationYear.valueOf("YEAR_" + declarationYear))
                .taxCategory(Declaration.TaxCategory.valueOf(taxCategory.toUpperCase()))
                .income(Long.parseLong(income)).taxSumDeclared(Long.parseLong(taxSumDeclared))
                .status(Declaration.Status.valueOf("SUBMITTED")).build();

        LOGGER.debug("New declaration object created: {} ", declaration.toString());


        DeclarationService declarationService = new DeclarationService();
//        try {
        boolean tmp = declarationService.create(declaration); //TODO consider use of tmp
        LOGGER.debug("New declaration written to database, value of tmp: {} ", tmp);
                                                                //TODO consider check for actual insert into DB

        ResourceBundle webInterface = ResourceBundleDispathcher.getResourceBundle(request);

        if(tmp) {

            request.getSession().setAttribute("service_message",
                    webInterface.getString("new.declaration.successful.submission"));

             redirect(request, response, ServletPath.MENU);
             return;
         }

        forward(request, response, JspPath.CLIENT_MENU_PAGE); // TODO test if this forward ever happens
    }

    boolean checkDataValidity(HttpServletRequest request, ClientUser user, String firstName,
                              String secondName, String income, String taxSumDeclared) {

        if (!firstName.equals(user.getFirstName())) {
            setErrorMessage(request,"new.declaration.error.first.name");
            return false;
        }

        if (!secondName.equals(user.getSecondName())) {
            setErrorMessage(request,"new.declaration.error.second.name");
            return false;
        }
        //TODO consider check for declaration Year to avoid duplicates

        if (!InputChecker.longIsValid(income)) {
            setErrorMessage(request,"new.declaration.error.income");
            return false;
        }

        if (!InputChecker.longIsValid(taxSumDeclared)) {
            setErrorMessage(request,"new.declaration.error.sum.declared");
            return false;
        }

        return true;
    }

    void setErrorMessage(HttpServletRequest request, String errorMessage) {

        String userLanguage = (String) request.getSession().getAttribute("sessionLang");
        Locale locale = new Locale(userLanguage);                             //TODO make universal solution for Locale
        ResourceBundle errorMessages = ResourceBundle.getBundle("web interface", locale);

        request.setAttribute("error_message", errorMessages.getString(errorMessage));
    }
}
