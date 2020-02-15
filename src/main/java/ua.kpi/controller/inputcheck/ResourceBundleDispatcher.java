package ua.kpi.controller.inputcheck;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleDispatcher {

    static final ResourceBundle INTERFACE_UKRAINIAN
            = ResourceBundle.getBundle("web interface", new Locale("uk"));
    static final ResourceBundle INTERFACE_ENGLISH
            = ResourceBundle.getBundle("web interface", new Locale("en"));

    public static ResourceBundle getResourceBundle(HttpServletRequest request) {
        String userLanguage = (String) request.getSession().getAttribute("sessionLang");

        if (userLanguage == null) {
            userLanguage = "uk";
        }

        return userLanguage.equals("uk") ? INTERFACE_UKRAINIAN : INTERFACE_ENGLISH;
    }
}
