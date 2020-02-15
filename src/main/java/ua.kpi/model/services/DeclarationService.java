package ua.kpi.model.services;

import ua.kpi.model.dao.DaoFactory;
import ua.kpi.model.dao.DeclarationDao;
import ua.kpi.model.entities.Declaration;

import java.util.List;

public class DeclarationService {

    private DaoFactory daoFactory = DaoFactory.getInstance();


    public List<Declaration> findAllByInspectorLogin(String login) {
        try(DeclarationDao dao = daoFactory.createDeclarationDao()) {
            return dao.findAllByInspectorLogin(login);
        }
    }

    public List<Declaration> findAllByClientLogin(String login) {
        try(DeclarationDao dao = daoFactory.createDeclarationDao()) {
            return dao.findAllByClientLogin(login);
        }
    }

    public boolean create(Declaration declaration) {
        try(DeclarationDao dao = daoFactory.createDeclarationDao()) {
            return dao.create(declaration);
        }
    }

    public boolean changeStatus(int id, String newStatus) {
        try(DeclarationDao dao = daoFactory.createDeclarationDao()) {
            return dao.changeStatus(id, newStatus);
        }
    }

    public boolean decline(int id, String declineMessage) {
        try(DeclarationDao dao = daoFactory.createDeclarationDao()) {
            return dao.decline(id, declineMessage);
        }
    }

    public boolean correct(Declaration correctedDeclaration) {
        try(DeclarationDao dao = daoFactory.createDeclarationDao()) {
            return dao.correct(correctedDeclaration);
        }
    }
}
