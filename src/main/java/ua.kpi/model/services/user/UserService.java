package ua.kpi.model.services.user;

import ua.kpi.model.entities.AbstractAppUser;
import ua.kpi.model.dao.impl.user.UserDao;
import ua.kpi.model.dao.impl.user.UserDaoFactory;
import ua.kpi.model.entities.ClientUser;

public class UserService {

    UserDaoFactory daoFactory = UserDaoFactory.getInstance();

    public boolean create(AbstractAppUser user) {
        try (UserDao dao = daoFactory.create()) {
            return dao.create(user);
        }
    }

    public AbstractAppUser find(String login, String password) {
        try (UserDao dao = daoFactory.find()) {
            return dao.find(login, password);
        }
    }

    public ClientUser findClientByLogin(String login) {
        try (UserDao dao = daoFactory.findClientByLogin()) {
            return dao.findClientByLogin(login);
        }
    }
}