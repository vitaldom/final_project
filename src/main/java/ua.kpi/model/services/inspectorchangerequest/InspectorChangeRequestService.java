package ua.kpi.model.services.inspectorchangerequest;

import ua.kpi.model.dao.impl.inspectorchange.InspectorChangeRequestDao;
import ua.kpi.model.dao.impl.inspectorchange.InspectorChangeRequestDaoFactory;
import ua.kpi.model.entities.InspectorChangeRequest;

public class InspectorChangeRequestService {

    InspectorChangeRequestDaoFactory daoFactory = InspectorChangeRequestDaoFactory.getInstance();

    public boolean create(InspectorChangeRequest inspectorChangeRequest) {
        try (InspectorChangeRequestDao dao = daoFactory.create()) {
            return dao.create(inspectorChangeRequest);
        }
    }
}