package ua.kpi.model.entities;

public class InspectorUser extends AbstractAppUser {
    public InspectorUser(String firstName, String secondName, String login, String password) {

        setFirstName(firstName);
        setSecondName(secondName);
        setLogin(login);
        setPassword(password);
        setRole("INSPECTOR"); //TODO consider changing to a constant
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
