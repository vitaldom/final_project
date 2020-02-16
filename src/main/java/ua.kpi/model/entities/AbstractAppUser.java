package ua.kpi.model.entities;

/**
 * Designed for inheritance by classes, representing concrete user roles in the application -
 * client user and inspector user.
 */
abstract public class AbstractAppUser {
    private String firstName;
    private String secondName;
    private String login;
    private String password;
    private Role role; //TODO add id

    public enum Role {
        CLIENT, INSPECTOR
    }

    public AbstractAppUser() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role.name();
    }

    public void setRole(String role) {
        this.role = Role.valueOf(role);
    }

    @Override
    public String toString() {
        return "\nfirstName: " + getFirstName() +
                "\nsecondName: " + getSecondName() +
                "\nlogin: " + getLogin() +
                "\nrole: " + getRole();
    }

    public abstract static class Builder<T extends Builder<T>> {
        private String firstName;
        private String secondName;
        private String login;
        private String password;
        private Role role;

        public T firstName(String firstName) {
            this.firstName = firstName;
            return self();
        }

        public T secondName(String secondName) {
            this.secondName = secondName;
            return self();
        }

        public T login(String login) {
            this.login = login;
            return self();
        }

        public T password(String password) {
            this.password = password;
            return self();
        }

        public T role(String role) {
            this.role = Role.valueOf(role);
            return self();
        }

        abstract AbstractAppUser build();

        protected abstract T self();
    }

    AbstractAppUser(Builder<?> builder) {
        firstName = builder.firstName;
        secondName = builder.secondName;
        login = builder.login;
        password = builder.password;
        role = builder.role;
    }
}
