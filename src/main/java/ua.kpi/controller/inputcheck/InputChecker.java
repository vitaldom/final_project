package ua.kpi.controller.inputcheck;

import ua.kpi.controller.command.ResourceBundleDispathcher;
import ua.kpi.model.entities.ClientUser;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.apache.commons.lang3.ObjectUtils.allNotNull;

public class InputChecker {

    private static final String NAME_REGEX_UA = "^[А-ЩЬЮЯҐІЇЄ][а-щьюяґіїє']{1,20}$";
    private static final String NAME_REGEX_EN = "^[A-Z][a-z]{1,20}$";
    private static final String LOGIN_REGEX ="^[\\w-.]{3,20}$";
    private static final String PASSWORD_REGEX = "^[\\w\\W]{4,20}$";
    //private static final String EMAIL_REGEX = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"; //TODO



    public static boolean nameIsValid(String name, String userLanguage) {
        if (!allNotNull(name, userLanguage)) {
            return false;
        }
        return userLanguage.equals("uk") ? name.matches(NAME_REGEX_UA) : name.matches(NAME_REGEX_EN);
    }


    public static boolean loginIsValid(String login) {
        return login != null && login.matches(LOGIN_REGEX);
    }

    public static boolean passwordIsValid(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }

    public static boolean longIsValid(String longFromInput) {
        try {
            long i = Long.parseLong(longFromInput, 10);
            return i >= 0;
        }
        catch (Exception exception) {
            return false;
        }
    }

    public static boolean checkDeclarationDataValidity(HttpServletRequest request, ClientUser user, String firstName,
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

    static void setErrorMessage(HttpServletRequest request, String errorMessage) {

        ResourceBundle webInterface = ResourceBundleDispathcher.getResourceBundle(request);

        request.setAttribute("error_message", webInterface.getString(errorMessage));
    }

    public static void setServiceMessage(HttpServletRequest request, String serviceMessage) {

        ResourceBundle webInterface = ResourceBundleDispathcher.getResourceBundle(request);

        request.getSession().setAttribute("service_message", webInterface.getString(serviceMessage));
    }
}
