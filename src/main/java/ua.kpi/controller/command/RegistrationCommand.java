package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.path.JspPath;
import ua.kpi.controller.path.ServletPath;
import ua.kpi.controller.inputcheck.InputChecker;
import ua.kpi.model.entities.AbstractAppUser;
import ua.kpi.model.entities.ClientUser;
import ua.kpi.model.services.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.apache.commons.lang3.ObjectUtils.allNotNull;
import static ua.kpi.controller.path.JspPath.REGISTRATION_PAGE;

public class RegistrationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(RegistrationCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

//
        if (!allNotNull(firstName, secondName, login, password)) { //TODO add error message
            forward(request, response, JspPath.REGISTRATION_PAGE);
            return;
        }

        String userLanguage = (String) request.getSession().getAttribute("sessionLang"); //TODO
        Locale locale = new Locale(userLanguage);
        ResourceBundle errorMessages = ResourceBundle.getBundle("web interface", locale);

        LOGGER.debug("User first name before check: {} ", firstName);
        LOGGER.debug("User Locale before check: {} ", locale);
        if(!InputChecker.nameIsValid(firstName, userLanguage)) {
            request.setAttribute("error_message", errorMessages.getString("registration.invalid.first.name"));
            forward(request, response, JspPath.REGISTRATION_PAGE);
            return;
        }

        LOGGER.debug("User second name before check: {} ", secondName);
        LOGGER.debug("User Locale before check: {} ", locale);
        if(!InputChecker.nameIsValid(secondName, userLanguage)) {
            request.setAttribute("error_message", errorMessages.getString("registration.invalid.second.name"));
            forward(request, response, JspPath.REGISTRATION_PAGE);
            return;
        }

        if(!InputChecker.loginIsValid(login)) {
            request.setAttribute("error_message", errorMessages.getString("registration.invalid.login"));
            forward(request, response, JspPath.REGISTRATION_PAGE);
            return;
        }

        if(!InputChecker.passwordIsValid(password)) {
            request.setAttribute("error_message", errorMessages.getString("registration.invalid.password"));
            forward(request, response, JspPath.REGISTRATION_PAGE);
            return;
        }
        //TODO check for duplicate login!

        AbstractAppUser user = new ClientUser(firstName, secondName, login, password);
        UserService userService = new UserService();

//        try {
         boolean tmp = userService.create(user); //TODO consider use of tmp
         AbstractAppUser registeredUser = userService.find(login, password);

         if (registeredUser == null) {
             LOGGER.info("Registration failed for user: {} ", user);
         } else {
             LOGGER.info("New user created: {}", registeredUser);
             request.getSession().setAttribute("service_message",
                     errorMessages.getString("registration.successful.registration"));
             redirect(request, response, ServletPath.START_PAGE);
             return;
         }
////        }
//////        catch (LoginExistsException exception) {
//////            request.setAttribute("error_message", errorMessages.getString("registration.login.already.exists"));
//////            LOGGER.info("New user registration failed because login already exists : {}", login);
//    }
        forward(request, response, REGISTRATION_PAGE);
    }
}
