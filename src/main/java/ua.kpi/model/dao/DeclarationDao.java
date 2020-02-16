package ua.kpi.model.dao;

import ua.kpi.model.entities.Declaration;

import java.util.List;

public interface DeclarationDao extends GenericDao<Declaration> {

    List<Declaration> findAllByClientLogin(String clientLogin);

    List<Declaration> findAllByInspectorLogin(String inspectorLogin);

    boolean changeStatus(int id, String newStatus);

    boolean decline(int id, String declineMessage);

    boolean correct(Declaration correctedDeclaration);

    boolean assignInspector(Declaration declaration);
}