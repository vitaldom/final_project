package ua.kpi.model.dao.impl.inspectorchange;

import ua.kpi.model.dao.impl.ConnectionPool;
import ua.kpi.model.dao.impl.declaration.DeclarationDao;
import ua.kpi.utils.DeclarationUtils;
import ua.kpi.utils.InspectorChangeRequestUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcInspectorChangeRequestDaoFactory extends InspectorChangeRequestDaoFactory {

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
    public InspectorChangeRequestDao create()  {
        return new InspectorChangeRequestUtils(getConnection());
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
