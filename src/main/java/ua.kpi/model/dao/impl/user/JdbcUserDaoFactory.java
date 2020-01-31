package ua.kpi.model.dao.impl.user;

import ua.kpi.model.dao.impl.ConnectionPool;
import ua.kpi.utils.AbstractUserUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUserDaoFactory extends UserDaoFactory {

        private DataSource dataSource = ConnectionPool.getDataSource();

        @Override
        public UserDao findClientByLogin() {
            return new AbstractUserUtils(getConnection());
        }

        @Override
        public UserDao create() {
            return new AbstractUserUtils(getConnection());
        }

        @Override
        public UserDao find() {
            return new AbstractUserUtils(getConnection());
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
