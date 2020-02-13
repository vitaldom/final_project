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

        if (!allNotNull(firstName, secondName, login, password)) { //TODO add error message
            forward(request, response, JspPath.REGISTRATION_PAGE);
            return;
        }

        String userLanguage = (String) request.getSession().getAttribute("sessionLang"); //TODO

        if(!InputChecker.nameIsValid(firstName, userLanguage)) {
            InputChecker.setSessionErrorMessage(request, "registration.invalid.first.name");
            response.sendRedirect(ServletPath.REGISTRATION);
            return;
        }

        if(!InputChecker.nameIsValid(secondName, userLanguage)) {
            InputChecker.setSessionErrorMessage(request,"registration.invalid.second.name");
            response.sendRedirect(ServletPath.REGISTRATION);
            return;
        }

        if(!InputChecker.loginIsValid(login)) {
            InputChecker.setSessionErrorMessage(request,"registration.invalid.login");
            response.sendRedirect(ServletPath.REGISTRATION);
            return;
        }

        if(!InputChecker.passwordIsValid(password)) {
            InputChecker.setSessionErrorMessage(request,"registration.invalid.password");
            response.sendRedirect(ServletPath.REGISTRATION);
            return;
        }
        //TODO check for duplicate login!

        AbstractAppUser user = new ClientUser(firstName, secondName, login, password);
        UserService userService = new UserService();

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

        forward(request, response, REGISTRATION_PAGE);
    }
}
