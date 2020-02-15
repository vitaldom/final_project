package ua.kpi.model.dao.impl;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private static final String CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String NAME = "root";
    private static final String PASSWORD = "root";
    private static volatile DataSource dataSource;

    public static DataSource getDataSource(){

        if (dataSource == null){
            synchronized (ConnectionPool.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setDriverClassName(CLASS_NAME);
                    ds.setUrl(URL);
                    ds.setUsername(NAME);
                    ds.setPassword(PASSWORD);
                    ds.setMinIdle(5);
                    ds.setMaxIdle(50);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }

    public Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException exception) {
            LOGGER.error("Error receiving connection in ConnectionPool: {}", exception.getMessage());
            return null;
        }
    }
}