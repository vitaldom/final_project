package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.path.JspPath;
import ua.kpi.controller.path.ServletPath;
import ua.kpi.controller.inputcheck.InputChecker;
import ua.kpi.model.entities.AbstractAppUser;
import ua.kpi.model.entities.ClientUser;
import ua.kpi.model.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.commons.lang3.ObjectUtils.allNotNull;
import static ua.kpi.controller.TextConstants.*;

public class RegistrationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(RegistrationCommand.class);
    UserService userService = new UserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter(FIRST_NAME);
        String secondName = request.getParameter(SECOND_NAME);
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        if (!allNotNull(firstName, secondName, login, password)) { // Page requested for the first time
            forward(request, response, JspPath.REGISTRATION_PAGE);
            return;
        }

        if (!checkRegistrationData(request, firstName, secondName, login, password)) {
            response.sendRedirect(ServletPath.REGISTRATION);
            return;
        }

        AbstractAppUser user = new ClientUser(firstName, secondName, login, password);

         boolean tmp = userService.create(user); //TODO consider use of tmp
         AbstractAppUser registeredUser = userService.find(login, password);

         if (registeredUser == null) {
             LOGGER.info("Registration failed for user: {} ", user);
         } else {
             LOGGER.info("New user created: {}", registeredUser);
             InputChecker.setServiceMessage(request, "registration.successful.registration");
             redirect(request, response, ServletPath.START_PAGE);
             return;
         }

        forward(request, response, JspPath.REGISTRATION_PAGE);
    }

    boolean checkRegistrationData(HttpServletRequest request, String firstName,
                                  String secondName, String login, String password) {

        String userLanguage = (String) request.getSession().getAttribute(SESSION_LANGUAGE); //TODO

        if(!InputChecker.nameIsValid(firstName, userLanguage)) {
            InputChecker.setSessionErrorMessage(request, "registration.invalid.first.name");
            return false;
        }

        if(!InputChecker.nameIsValid(secondName, userLanguage)) {
            InputChecker.setSessionErrorMessage(request,"registration.invalid.second.name");
            return false;
        }

        if(!InputChecker.loginIsValid(login)) {
            InputChecker.setSessionErrorMessage(request,"registration.invalid.login");
            return false;
        }

        if (userService.findClientByLogin(login) != null) {
            InputChecker.setSessionErrorMessage(request,"registration.login.already.exists");
            return false;
        }

        if(!InputChecker.passwordIsValid(password)) {
            InputChecker.setSessionErrorMessage(request,"registration.invalid.password");
            return false;
        }

        return true;
    }
}
