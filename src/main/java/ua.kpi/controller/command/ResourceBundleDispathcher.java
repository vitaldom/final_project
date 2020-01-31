package ua.kpi.controller.command;

import com.sun.deploy.net.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleDispathcher {

    static final ResourceBundle interfaceUkrainian = ResourceBundle.getBundle("web interface", new Locale("uk"));
    static final ResourceBundle interfaceEnglish = ResourceBundle.getBundle("web interface", new Locale("en"));
   //TODO check for quality of solution. Consider making name of resource bundle as constant

    static ResourceBundle getResourceBundle(HttpServletRequest request) {
        String userLanguage = (String) request.getSession().getAttribute("sessionLang");

        return userLanguage.equals("uk") ? interfaceUkrainian : interfaceEnglish;
    }
}
