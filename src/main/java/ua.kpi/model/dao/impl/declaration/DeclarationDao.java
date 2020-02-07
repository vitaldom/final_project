package ua.kpi.model.dao.impl.declaration;

import ua.kpi.model.dao.GenericDao;
import ua.kpi.model.entities.Declaration;

import java.util.List;

public interface DeclarationDao extends GenericDao<Declaration> {
//    Declaration find(int id); TODO+ validate use or delete
    List<Declaration> findAllByClientLogin(String clientLogin);
    List<Declaration> findAllByInspectorLogin(String inspectorLogin);
    boolean changeStatus(int id, String newStatus);
    boolean decline(int id, String declineMessage);
    boolean correct(Declaration correctedDeclaration);
//    void delete(int id);
}