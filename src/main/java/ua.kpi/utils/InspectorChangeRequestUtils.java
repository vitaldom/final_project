package ua.kpi.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.model.dao.impl.declaration.DeclarationDao;
import ua.kpi.model.dao.impl.inspectorchange.InspectorChangeRequestDao;
import ua.kpi.model.entities.InspectorChangeRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static ua.kpi.model.dao.impl.sqlqueries.UserSqlQueries.CREATE_INSPECTOR_CHANGE_REQUEST;
import static ua.kpi.model.dao.impl.sqlqueries.UserSqlQueries.SELECT_LAST_INSPECTOR_CHANGE_REQUEST_ID;

public class InspectorChangeRequestUtils implements InspectorChangeRequestDao {

    private static final Logger LOGGER = LogManager.getLogger(InspectorChangeRequestUtils.class);

    Connection connection;

    public InspectorChangeRequestUtils(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(InspectorChangeRequest inspectorChangeRequest) {
        try(PreparedStatement ps = connection.prepareStatement(SELECT_LAST_INSPECTOR_CHANGE_REQUEST_ID)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) { //TODO check necessary?

                int id = rs.getInt("MAX(id)");
                inspectorChangeRequest.setId(++id);

                    try(PreparedStatement ps1 = connection.prepareStatement(CREATE_INSPECTOR_CHANGE_REQUEST)) {

                        ps1.setInt(1, inspectorChangeRequest.getId());
                        ps1.setInt(2, inspectorChangeRequest.getDeclarationID());
                        ps1.setString(3, inspectorChangeRequest.getAuthorLogin());
                        ps1.setString(4, inspectorChangeRequest.getInspectorLogin());
                        ps1.setString(5, inspectorChangeRequest.getRequestReason());

                        ps1.executeUpdate();
                        connection.commit();

                        return true;
                    } catch (SQLException exception) {
                        connection.rollback();
                        LOGGER.error("SQL error creating new inspector change request {} ", exception.getMessage());
                    }
                    finally {
                        connection.setAutoCommit(true); //TODO Validate necessity of setting AutoCommit
                    }
            }
        } catch (SQLException exception) {
            LOGGER.error("SQL error creating new inspector change request {} ", exception.getMessage());
        }
        return false;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
            throw new RuntimeException(exception); //TODO validate throwing runtime exception
        }
    }
}
