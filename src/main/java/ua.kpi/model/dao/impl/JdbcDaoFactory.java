package ua.kpi.model.dao.impl;

import ua.kpi.model.dao.DaoFactory;
import ua.kpi.model.dao.DeclarationDao;
import ua.kpi.model.dao.InspectorChangeRequestDao;
import ua.kpi.model.dao.UserDao;

import java.sql.Connection;

public class JdbcDaoFactory extends DaoFactory {

    private ConnectionPool connectionPool = new ConnectionPool();

    @Override
    public UserDao createUserDao() {
        return new JdbcAbstractUserDao(getConnection());
    }

    @Override
    public DeclarationDao createDeclarationDao() {
        return new JdbcDeclarationDao(getConnection());
    }

    @Override
    public InspectorChangeRequestDao createInspectorChangeRequestDao() {
        return new JdbcInspectorChangeRequestDao(getConnection());
    }

    private Connection getConnection() {
        return connectionPool.getConnection();
    }
}