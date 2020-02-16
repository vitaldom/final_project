package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.path.JspPath;
import ua.kpi.controller.path.ServletPath;
import ua.kpi.controller.inputcheck.InputChecker;
import ua.kpi.model.entities.AbstractAppUser;
import ua.kpi.model.entities.ClientUser;
import ua.kpi.model.exceptions.LoginExistsException;
import ua.kpi.model.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.commons.lang3.ObjectUtils.allNotNull;
import static ua.kpi.controller.TextConstants.*;

/**
 * Encapsulates registration logic for client users.
 */
public class RegistrationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(RegistrationCommand.class);
    UserService userService = new UserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter(FIRST_NAME);
        String secondName = request.getParameter(SECOND_NAME);
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        if (!allNotNull(firstName, secondName, login, password)) {                  // Page requested for the first time
            forward(request, response, JspPath.REGISTRATION_PAGE);
            return;
        }

        boolean dataIsValid = false;

        try {                                                                       // Custom exception
            dataIsValid = InputChecker.checkUserRegistrationData(request, firstName, secondName, login, password);
        } catch (LoginExistsException exception) {
            LOGGER.error("New user registration failed. Error message: {}", exception.getMessage());
        }

        if (!dataIsValid) {
            response.sendRedirect(ServletPath.REGISTRATION);
            return;
        }

        AbstractAppUser user = new ClientUser(firstName, secondName, login, password);

         userService.create(user);
         AbstractAppUser registeredUser = userService.find(login, password);        // Check for actual insert into DB

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
}
