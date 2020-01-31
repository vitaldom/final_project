package ua.kpi.model.dao.impl.user;

public abstract class UserDaoFactory {
    private static UserDaoFactory daoFactory;

//    public abstract UserDao findByUserName(); //TODO+ validate usage, compare and coordinate with UserDao interface
    public abstract UserDao create();
    public abstract UserDao find();
    public abstract UserDao findClientByLogin();

    public static UserDaoFactory getInstance(){

        if (daoFactory == null) {
            synchronized (UserDaoFactory.class){
                if(daoFactory==null){

                    UserDaoFactory temp = new JdbcUserDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
