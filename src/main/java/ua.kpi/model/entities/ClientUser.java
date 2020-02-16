package ua.kpi.model.entities;

/**
 * Represents client users of the application, who can register and submit declarations.
 */
public class ClientUser extends AbstractAppUser {

    public ClientUser(String firstName, String secondName, String login, String password) {

        setFirstName(firstName);
        setSecondName(secondName);
        setLogin(login);
        setPassword(password);
        setRole(Role.CLIENT.toString());
    }

    public static class Builder extends AbstractAppUser.Builder<Builder> {

        @Override
        public ClientUser build() {
            return new ClientUser(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private ClientUser(Builder builder) {
        super(builder);
    }
}
