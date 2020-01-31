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





    }

    private CommandMapper() {
    };

    public static Command getCommand(String key) {
        return commands.get(key);
    }


}
