package ua.kpi.model.services.declaration;

import ua.kpi.model.dao.impl.declaration.DeclarationDao;
import ua.kpi.model.dao.impl.declaration.DeclarationDaoFactory;
import ua.kpi.model.entities.Declaration;

import java.util.List;

public class DeclarationService {

        DeclarationDaoFactory daoFactory = DeclarationDaoFactory.getInstance();

//        public void delete(int id) {                                  TODO+ validate use or delete
//            try (DeclarationDao dao = daoFactory.delete()) {
//                dao.delete(id);
//            }
//        }

//
//        public void update(Declaration declaration) {
//            try(DeclarationDao dao = daoFactory.update()) {
//                dao.update(declaration);
//            }
//        }

        public List<Declaration> findAllByInspectorLogin(String login) {
            try(DeclarationDao dao = daoFactory.findAllByInspectorLogin()) {
                return dao.findAllByInspectorLogin(login);
            }
        }

        public List<Declaration> findAllByClientLogin(String login) {
            try(DeclarationDao dao = daoFactory.findAllByClientLogin()) {
                return dao.findAllByClientLogin(login);
            }
        }

        public boolean create(Declaration declaration) {
            try(DeclarationDao dao = daoFactory.create()) {
                return dao.create(declaration);
            }
        }
}
