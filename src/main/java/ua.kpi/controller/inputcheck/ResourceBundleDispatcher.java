package ua.kpi.controller.inputcheck;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

import static ua.kpi.controller.TextConstants.SESSION_LANGUAGE;
import static ua.kpi.controller.TextConstants.UK;

public class ResourceBundleDispatcher {

    private static final String WEB_INTERFACE = "web interface";
    private static final String EN = "en";


    static final ResourceBundle INTERFACE_UKRAINIAN
            = ResourceBundle.getBundle(WEB_INTERFACE, new Locale(UK));
    static final ResourceBundle INTERFACE_ENGLISH
            = ResourceBundle.getBundle(WEB_INTERFACE, new Locale(EN));

    public static ResourceBundle getResourceBundle(HttpServletRequest request) {
        String userLanguage = (String) request.getSession().getAttribute(SESSION_LANGUAGE);

        if (userLanguage == null) {
            userLanguage = UK;
        }

        return userLanguage.equals(UK) ? INTERFACE_UKRAINIAN : INTERFACE_ENGLISH;
    }
}
