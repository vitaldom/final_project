package ua.kpi.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.path.ServletPath;
import ua.kpi.model.entities.AbstractAppUser;
import ua.kpi.model.services.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.apache.commons.lang3.ObjectUtils.allNotNull;


public class LoginCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        String userLanguage = (String) request.getSession().getAttribute("sessionLang");
        Locale locale = new Locale(userLanguage);
        ResourceBundle errorMessages = ResourceBundle.getBundle("web interface", locale);

        if (!allNotNull(login, password)) { //TODO add error message
            request.setAttribute("service_message", errorMessages.getString("login.invalid.combination"));
            forward(request, response, ServletPath.START_PAGE);
            return;
        }

        UserService userService = new UserService();
        Optional<AbstractAppUser> suggestedUser = Optional.ofNullable(userService.find(login, password));

        if (suggestedUser.isPresent()) {
            Set<String> loggedUsers = (HashSet<String>) request.getServletContext().getAttribute("loggedUsers");
            loggedUsers.add(login);
            AbstractAppUser user = suggestedUser.get();
            request.getSession().setAttribute("user", user);

            LOGGER.info("User logged in: {} :", login);

            redirect(request, response, ServletPath.MENU);
        }
    }



//        LOGGER.debug("User Locale before check: {} ", locale);
//        UserService userService = new UserService();
//
////        try {
//         boolean tmp = userService.create(user); //TODO consider use of tmp
//
//
//         if (registeredUser == null) {
//             LOGGER.info("Registration failed for user: {} ", user);
//         } else {
//             LOGGER.info("New user created: {}", registeredUser);
////             request.getSession().setAttribute("service_message",
////                     errorMessages.getString("registration.successful.registration")); TODO add success message?
//             redirect(request, response, ServletPath.START_PAGE);
//             return;
//         }
//////        }
////////        catch (LoginExistsException exception) {
////////            request.setAttribute("error_message", errorMessages.getString("registration.login.already.exists"));
////////            LOGGER.info("New user registration failed because login already exists : {}", login);
////    }
//        forward(request, response, REGISTRATION_PAGE);
//    }
}
