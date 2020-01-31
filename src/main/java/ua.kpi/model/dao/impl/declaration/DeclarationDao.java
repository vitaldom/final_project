package ua.kpi.model.dao.impl.declaration;

import ua.kpi.model.dao.GenericDao;
import ua.kpi.model.entities.Declaration;

import java.util.List;

public interface DeclarationDao extends GenericDao<Declaration> {
//    Declaration find(int id); TODO+ validate use or delete
    List<Declaration> findAllByClientLogin(String login);
//    void delete(int id);
}