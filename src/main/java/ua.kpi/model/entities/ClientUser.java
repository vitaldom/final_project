package ua.kpi.model.entities;

import ua.kpi.model.entities.AbstractAppUser;

public class ClientUser extends AbstractAppUser {
    public ClientUser(String firstName, String secondName, String login, String password) {

        setFirstName(firstName);
        setSecondName(secondName);
        setLogin(login);
        setPassword(password); //TODO add personal tax number
        setRole("CLIENT"); //TODO consider changing to a constant
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
