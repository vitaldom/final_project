package ua.kpi.controller.inputcheck;

import ua.kpi.model.entities.ClientUser;
import ua.kpi.model.exceptions.LoginExistsException;
import ua.kpi.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

import static org.apache.commons.lang3.ObjectUtils.allNotNull;
import static ua.kpi.controller.TextConstants.*;

/**
 * Contains utility methods for check of users' input.
 */
public class InputChecker {

    private static final String NAME_REGEX_UA = "^[А-ЩЬЮЯҐІЇЄ][а-щьюяґіїє']{1,20}$";
    private static final String NAME_REGEX_EN = "^[A-Z][a-z]{1,20}$";
    private static final String LOGIN_REGEX ="^[\\w-.]{3,20}$";
    private static final String PASSWORD_REGEX = "^[\\w\\W]{4,20}$";

    static UserService userService = new UserService();

    public static boolean nameIsValid(String name, String userLanguage) {
        if (!allNotNull(name, userLanguage)) {
            return false;
        }
        return userLanguage.equals(UK) ? name.matches(NAME_REGEX_UA) : name.matches(NAME_REGEX_EN);
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
            setSessionErrorMessage(request,"new.declaration.error.first.name");
            return false;
        }

        if (!secondName.equals(user.getSecondName())) {
            setSessionErrorMessage(request,"new.declaration.error.second.name");
            return false;
        }

        if (!longIsValid(income)) {
            setSessionErrorMessage(request,"new.declaration.error.income");
            return false;
        }

        if (!longIsValid(taxSumDeclared)) {
            setSessionErrorMessage(request,"new.declaration.error.sum.declared");
            return false;
        }

        return true;
    }

    public static boolean checkUserRegistrationData(HttpServletRequest request, String firstName, String secondName,
                                                    String login, String password) throws LoginExistsException {

        String userLanguage = (String) request.getSession().getAttribute(SESSION_LANGUAGE);

        if(!nameIsValid(firstName, userLanguage)) {
            setSessionErrorMessage(request, "registration.invalid.first.name");
            return false;
        }

        if(!nameIsValid(secondName, userLanguage)) {
            setSessionErrorMessage(request,"registration.invalid.second.name");
            return false;
        }

        if(!loginIsValid(login)) {
            setSessionErrorMessage(request,"registration.invalid.login");
            return false;
        }

        if (userService.findClientByLogin(login) != null) {
            setSessionErrorMessage(request,"registration.login.already.exists");

            throw new LoginExistsException("New user registration failed, because login '" +login + "'already exists" );
        }

        if(!passwordIsValid(password)) {
            setSessionErrorMessage(request,"registration.invalid.password");
            return false;
        }

        return true;
    }

    static void setErrorMessage(HttpServletRequest request, String errorMessage) {

        ResourceBundle webInterface = ResourceBundleDispatcher.getResourceBundle(request);

        request.setAttribute(ERROR_MESSAGE, webInterface.getString(errorMessage));
    }

    public static void setServiceMessage(HttpServletRequest request, String serviceMessage) {

        ResourceBundle webInterface = ResourceBundleDispatcher.getResourceBundle(request);

        request.getSession().setAttribute(SERVICE_MESSAGE, webInterface.getString(serviceMessage));
    }

    public static void setSessionErrorMessage(HttpServletRequest request, String errorMessage) {

        ResourceBundle webInterface = ResourceBundleDispatcher.getResourceBundle(request);

        request.getSession().setAttribute(ERROR_MESSAGE, webInterface.getString(errorMessage));
    }
}
