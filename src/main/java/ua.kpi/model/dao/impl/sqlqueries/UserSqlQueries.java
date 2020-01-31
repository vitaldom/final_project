package ua.kpi.model.dao.impl.sqlqueries;

public interface UserSqlQueries {
    String PRE_CHECK_CREATION_USER = "SELECT COUNT(1) AS count FROM clients WHERE login = ?";
    String CREATE_USER = "INSERT INTO clients (second_name, first_name, login, password, role) VALUES (?, ?, ?, ?, ?)";
    String FIND_USER = "SELECT second_name, first_name, login, password, role FROM clients WHERE login = ? AND password = ?";

    String SELECT_LAST_DECLARATION_ID = "SELECT MAX(id) FROM declarations";
    String SELECT_LEAST_BUSY_INSPECTOR = "SELECT login, MIN(reports_assigned) FROM inspectors";
    String INCREMENT_REPORTS_ASSIGNED = "UPDATE inspectors SET reports_assigned = reports_assigned + 1 WHERE login = ?";

    String CREATE_DECLARATION = "INSERT INTO declarations(id, author_login, inspector_login, declaration_year, " +
            "tax_category, income, tax_sum_declared, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    String SELECT_DECLARATIONS_BY_CLIENT_LOGIN = "SELECT * FROM declarations WHERE author_login = ?";

    String FIND_CLIENT_BY_LOGIN = "SELECT second_name, first_name, login, password, role FROM clients WHERE login = ?"; //todo delete role


}