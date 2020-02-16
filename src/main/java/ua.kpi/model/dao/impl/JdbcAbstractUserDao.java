package ua.kpi.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.model.dao.Mapper.AbstractUserMapper;
import ua.kpi.model.dao.UserDao;
import ua.kpi.model.entities.AbstractAppUser;
import ua.kpi.model.entities.ClientUser;

import java.sql.*;

import static ua.kpi.model.dao.impl.sqlqueries.SqlQueries.*;

public class JdbcAbstractUserDao implements UserDao {

    private static final String COUNT = "count";
    private static final Logger LOGGER = LogManager.getLogger(JdbcAbstractUserDao.class);

    AbstractUserMapper userMapper = new AbstractUserMapper();
    Connection connection;

    public JdbcAbstractUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(AbstractAppUser user) {

        try(PreparedStatement ps1 = connection.prepareStatement(PRE_CHECK_CREATION_USER)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            ps1.setString(1, user.getLogin());

            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(COUNT);
                if(count == 0) {

                    try(PreparedStatement ps = connection.prepareStatement(CREATE_USER)) {
                        ps.setString(1, user.getSecondName());
                        ps.setString(2, user.getFirstName());
                        ps.setString(3, user.getLogin());
                        ps.setString(4, user.getPassword());
                        ps.setString(5, user.getRole());

                        ps.executeUpdate();
                        connection.commit();

                        LOGGER.debug("New user committed to the database"); //TODO
                        return true;

                    } catch (SQLException exception) {
                        connection.rollback();
                        LOGGER.error(exception.getMessage()); //TODO
                        exception.printStackTrace();
                    }
                }
            }
        } catch (SQLException exception) {
            LOGGER.error("SQL error when adding new user {} ", user);
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public AbstractAppUser find(String login, String password) {

        try(PreparedStatement ps = connection.prepareStatement(FIND_USER)) {

            ps.setString(1, login);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) { //TODO+ duplicate values possible?
                AbstractAppUser user = userMapper.extractFromResultSet(rs);
                return user;
            }
        } catch (SQLException exception) {
            LOGGER.error("Error while getting a user from database");
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public ClientUser findClientByLogin(String login) {

        try(PreparedStatement ps = connection.prepareStatement(FIND_CLIENT_BY_LOGIN)) {

            ps.setString(1, login);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) { //TODO+ duplicate values possible?
                ClientUser user = (ClientUser) userMapper.extractFromResultSet(rs);
                return user;
            }
        } catch (SQLException exception) {
            LOGGER.error("Error while getting a user from database");
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Error closing database connection {}", e.getMessage());
            throw new RuntimeException(e); //TODO validate throwing runtime exception
        }
    }
}
