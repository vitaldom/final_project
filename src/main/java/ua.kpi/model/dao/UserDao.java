package ua.kpi.model.dao;

import ua.kpi.model.entities.AbstractAppUser;
import ua.kpi.model.entities.ClientUser;

public interface UserDao extends GenericDao<AbstractAppUser> {

//        List<String> findByLogin(AbstractAppUser user); //TODO+ validate use or delete

        AbstractAppUser find(String login, String password);

        ClientUser findClientByLogin(String login);
}
