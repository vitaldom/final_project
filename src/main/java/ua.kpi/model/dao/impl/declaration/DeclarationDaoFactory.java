package ua.kpi.model.dao.impl.declaration;

public abstract class DeclarationDaoFactory {
    private static DeclarationDaoFactory daoFactory;

//    public abstract DeclarationDao findAll(); TODO+ validate use or delete
//    public abstract DeclarationDao delete();
//    public abstract DeclarationDao find();
//    public abstract DeclarationDao update();
    public abstract DeclarationDao findAllByInspectorLogin();
    public abstract DeclarationDao create();
    public abstract DeclarationDao findAllByClientLogin();
    public abstract DeclarationDao approve();
    public abstract DeclarationDao decline();


    public static DeclarationDaoFactory getInstance(){

        if( daoFactory == null ){
            synchronized (DeclarationDaoFactory.class){
                if(daoFactory==null){

                    DeclarationDaoFactory temp = new JdbcDeclarationDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
