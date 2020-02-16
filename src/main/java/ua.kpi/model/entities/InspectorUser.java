package ua.kpi.model.entities;

/**
 * Represents inspector users of the application, who can check (approve or decline) submitted declarations.
 */
public class InspectorUser extends AbstractAppUser {
    public InspectorUser(String firstName, String secondName, String login, String password) {

        setFirstName(firstName);
        setSecondName(secondName);
        setLogin(login);
        setPassword(password);
        setRole(Role.INSPECTOR.toString());
    }

    public static class Builder extends AbstractAppUser.Builder<Builder> {

        @Override
        public InspectorUser build() {
            return new InspectorUser(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private InspectorUser(Builder builder) {
        super(builder);
    }
}
