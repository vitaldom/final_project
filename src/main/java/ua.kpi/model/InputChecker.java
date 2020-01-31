package ua.kpi.model;

import static org.apache.commons.lang3.ObjectUtils.allNotNull;

public class InputChecker {

    private static final String NAME_REGEX_UA = "^[А-ЩЬЮЯҐІЇЄ][а-щьюяґіїє']{1,20}$";
    private static final String NAME_REGEX_EN = "^[A-Z][a-z]{1,20}$";
    private static final String LOGIN_REGEX ="^[\\w-.]{3,20}$";
    private static final String PASSWORD_REGEX = "^[\\w\\W]{4,20}$";
    //private static final String EMAIL_REGEX = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"; //TODO

    public static boolean nameIsValid(String name, String userLanguage) {
        if (!allNotNull(name, userLanguage)) {
            return false;
        }
        return userLanguage.equals("uk") ? name.matches(NAME_REGEX_UA) : name.matches(NAME_REGEX_EN);
    }


    public static boolean loginIsValid(String login) {
        return login != null && login.matches(LOGIN_REGEX);
    }

    public static boolean passwordIsValid(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }

    public static boolean longIsValid(String longFromInput) {
        try {
            long i = Long.parseLong(longFromInput, 10);
            return i >= 0;
        }
        catch (Exception exception) {
            return false;
        }
    }
}
