package ua.kpi.controller.command;

import ua.kpi.controller.path.ServletPath;

import java.util.HashMap;
import java.util.Map;

public class CommandMapper {

    static private Map<String, Command> commands = new HashMap<>();

    static {
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

    private CommandMapper() {
    };

    public static Command getCommand(String key) {
        return commands.get(key);
    }


}
