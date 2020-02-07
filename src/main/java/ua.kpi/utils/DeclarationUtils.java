package ua.kpi.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.model.dao.Mapper.DeclarationMapper;
import ua.kpi.model.dao.impl.declaration.DeclarationDao;
import ua.kpi.model.dao.impl.sqlqueries.UserSqlQueries;
import ua.kpi.model.entities.Declaration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.kpi.model.dao.impl.sqlqueries.UserSqlQueries.*;

public class DeclarationUtils implements DeclarationDao {

    private static final Logger LOGGER = LogManager.getLogger(DeclarationUtils.class);

    Connection connection;

    public DeclarationUtils(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Declaration declaration) {
        try(PreparedStatement ps = connection.prepareStatement(SELECT_LAST_DECLARATION_ID)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            assignInspector(declaration);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) { //TODO check necessary?

                int id = rs.getInt("max(id)");
                declaration.setId(++id);

                    try(PreparedStatement ps1 = connection.prepareStatement(CREATE_DECLARATION)) {

                        ps1.setInt(1, declaration.getId());
                        ps1.setString(2, declaration.getAuthorLogin());
                        ps1.setString(3, declaration.getInspectorLogin());
                        ps1.setString(4, declaration.getDeclarationYear().getYear());
                        ps1.setString(5, declaration.getTaxCategory().toString());
                        ps1.setLong(6, declaration.getIncome());
                        ps1.setLong(7, declaration.getTaxSumDeclared());
                        ps1.setString(8, declaration.getStatus().toString());

                        ps1.executeUpdate();
                        connection.commit();

                        return true;
                    } catch (SQLException exception) {
                        connection.rollback();
                        LOGGER.error("SQL error creating new declaration {} ", exception.getMessage());
                    }

            }
        } catch (SQLException exception) {
            LOGGER.error("SQL error creating new declaration {} ", exception.getMessage());
        }
        return false;
    }

    public boolean assignInspector(Declaration declaration) {
        try(PreparedStatement ps1 = connection.prepareStatement(SELECT_LEAST_BUSY_INSPECTOR)) {

            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                String inspectorLogin = rs.getString("login");
                declaration.setInspectorLogin(inspectorLogin);

                PreparedStatement ps2 = connection.prepareStatement(INCREMENT_REPORTS_ASSIGNED); //TODO Combine in one request?
                ps2.setString(1, inspectorLogin);
                ps2.executeUpdate(); //TODO Any additional checks needed?
                ps2.close();
            }
        } catch (SQLException exception) {

        LOGGER.error("SQL error assigning inspector {} ", exception.getMessage());
    }
        return false;
    }

    @Override
    public List<Declaration> findAllByClientLogin(String login) {
        List<Declaration> list = new ArrayList<>();
        DeclarationMapper declarationMapper = new DeclarationMapper();

            try (PreparedStatement ps = connection.prepareStatement(SELECT_DECLARATIONS_BY_CLIENT_LOGIN)) {

                ps.setString(1,login);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Declaration declaration = declarationMapper.extractFromResultSet(rs);
                    list.add(declaration);
                }

            } catch (SQLException exception) {
                LOGGER.error("SQL receiving all declaration by client login {} ", exception.getMessage());
            }

        return list;
    }

    @Override
    public List<Declaration> findAllByInspectorLogin(String login) {
        List<Declaration> list = new ArrayList<>();
        DeclarationMapper declarationMapper = new DeclarationMapper();

        try (PreparedStatement ps = connection.prepareStatement(SELECT_DECLARATIONS_BY_INSPECTOR_LOGIN)) {

            ps.setString(1,login);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Declaration declaration = declarationMapper.extractFromResultSet(rs);
                list.add(declaration);
            }

        } catch (SQLException exception) {
            LOGGER.error("SQL receiving all declaration by inspector login {} ", exception.getMessage());
        }

        return list;
    }

    @Override
    public boolean changeStatus(int id, String newStatus) {
        try (PreparedStatement statement = connection.prepareStatement(UserSqlQueries.CHANGE_DECLARATION_STATUS)) {

            statement.setInt(2, id);
            statement.setString(1, newStatus);
            statement.executeUpdate();

        } catch (SQLException exception) {
            LOGGER.error("Error approving declaration: {} ", exception.getMessage());
            exception.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean decline(int id, String declineMessage) {
        try (PreparedStatement statement = connection.prepareStatement(UserSqlQueries.DECLINE_DECLARATION)) {
            statement.setInt(2, id);
            statement.setString(1, declineMessage);

            statement.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error("Error declining declaration: {} ", exception.getMessage());
            exception.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean correct(Declaration correctedDeclaration) {
        try (PreparedStatement ps = connection.prepareStatement(CORRECT_DECLARATION)) {

            ps.setString(1, correctedDeclaration.getDeclarationYear().getYear());
            ps.setString(2, correctedDeclaration.getTaxCategory().toString());
            ps.setLong(3, correctedDeclaration.getIncome());
            ps.setLong(4, correctedDeclaration.getTaxSumDeclared());
            ps.setString(5, correctedDeclaration.getStatus().toString());
            ps.setInt(6, correctedDeclaration.getId());
            ps.executeUpdate();

        } catch (SQLException exception) {
            LOGGER.error("SQL error correcting declaration {} ", exception.getMessage());
            exception.printStackTrace();
        }

        return true;
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
