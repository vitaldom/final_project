package ua.kpi.model.dao.impl.inspectorchange;

import ua.kpi.model.dao.impl.declaration.DeclarationDao;

public abstract class InspectorChangeRequestDaoFactory {
    private static InspectorChangeRequestDaoFactory daoFactory;

//    public abstract InspectorChangeRequestDao findAll(); TODO+ validate use or delete
//    public abstract InspectorChangeRequestDao delete();
//    public abstract InspectorChangeRequestDao find();
//    public abstract InspectorChangeRequestDao update();
    public abstract InspectorChangeRequestDao create();


    public static InspectorChangeRequestDaoFactory getInstance(){

        if (daoFactory == null){
            synchronized (InspectorChangeRequestDaoFactory.class) {
                if (daoFactory==null){

                    InspectorChangeRequestDaoFactory temp = new JdbcInspectorChangeRequestDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
