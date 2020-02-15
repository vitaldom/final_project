package ua.kpi.controller.servlets;

import ua.kpi.controller.command.*;
import ua.kpi.controller.path.ServletPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/")
public class ControlServlet extends HttpServlet {

    private Map<String, Command> commands = new HashMap<>();

    @Override
    public void init() throws ServletException {

            commands.put(ServletPath.REGISTRATION, new RegistrationCommand());
            commands.put(ServletPath.LOGIN, new LoginCommand());
            commands.put(ServletPath.MENU, new RoleMenuCommand());
            commands.put(ServletPath.NEW_DECLARATION, new CreateDeclarationCommand());
            commands.put(ServletPath.SUBMITTED_DECLARATIONS, new ViewSubmittedDeclarationsCommand());
            commands.put(ServletPath.VIEW_SINGLE_DECLARATION, new ViewSingleDeclarationCommand());
            commands.put(ServletPath.VIEW_DECLARATIONS_FOR_CHECK, new DeclarationsForCheckCommand());
            commands.put(ServletPath.CHECK_DECLARATION, new CheckDeclarationCommand());
            commands.put(ServletPath.APPROVE_DECLARATION, new ApproveDeclarationCommand());
            commands.put(ServletPath.DECLINE_DECLARATION, new DeclineDeclarationCommand());
            commands.put(ServletPath.VIEW_DECLINED_DECLARATION, new ViewDeclinedDeclarationCommand());
            commands.put(ServletPath.CORRECT_DECLARATION, new CorrectDeclarationCommand());
            commands.put(ServletPath.REQUEST_INSPECTOR_CHANGE, new InspectorChangeCommand());
            commands.put(ServletPath.LOGOUT, new LogoutCommand());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String commandPath = request.getRequestURI();
        Command command = commands.get(commandPath);

        command.execute(request, response);
    }
}
