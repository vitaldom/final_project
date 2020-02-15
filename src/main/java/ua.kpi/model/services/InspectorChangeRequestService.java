package ua.kpi.model.services;

import ua.kpi.model.dao.DaoFactory;
import ua.kpi.model.dao.InspectorChangeRequestDao;
import ua.kpi.model.entities.InspectorChangeRequest;

public class InspectorChangeRequestService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    public boolean create(InspectorChangeRequest inspectorChangeRequest) {
        try (InspectorChangeRequestDao dao = daoFactory.createInspectorChangeRequestDao()) {
            return dao.create(inspectorChangeRequest);
        }
    }
}