package ua.kpi.model.dao.impl.declaration;

import ua.kpi.model.dao.impl.ConnectionPool;
import ua.kpi.utils.DeclarationUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDeclarationDaoFactory extends DeclarationDaoFactory {

    private DataSource dataSource = ConnectionPool.getDataSource();

//    @Override                                                                     TODO+ validate use or delete
//    public DeclarationDao findAll() {
//        return new DeclarationUtils(getConnection());
//    }

//    @Override
//    public DeclarationDao delete() {
//        return new DeclarationUtils(getConnection());
//    }

//    @Override
//    public DeclarationDao update() {
//        return new DeclarationUtils(getConnection());
//    }

    @Override
    public DeclarationDao findAllByInspectorLogin() {
        return new DeclarationUtils(getConnection());
    }

    @Override
    public DeclarationDao findAllByClientLogin() {
        return new DeclarationUtils(getConnection());
    }

    @Override
    public DeclarationDao create()  {
        return new DeclarationUtils(getConnection());
    }

    private Connection getConnection(){

        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.getStackTrace();
            throw new RuntimeException(e);
        }

    }
}
