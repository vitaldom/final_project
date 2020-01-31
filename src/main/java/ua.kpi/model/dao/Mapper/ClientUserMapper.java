package ua.kpi.model.dao.Mapper;

import ua.kpi.model.dao.impl.columns.ClientUserSqlColumns;
import ua.kpi.model.entities.ClientUser;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientUserMapper implements Mapper<ClientUser> {

    @Override
    public ClientUser extractFromResultSet(ResultSet rs) throws SQLException {
        if (rs.getString(ClientUserSqlColumns.LOGIN) == null) { // TODO consider returning non-null value
            return null;
        }
        return new ClientUser.Builder()
                .firstName(rs.getString(ClientUserSqlColumns.FIRST_NAME))
                .secondName(rs.getString(ClientUserSqlColumns.SECOND_NAME))
                .login(rs.getString(ClientUserSqlColumns.LOGIN))
                .password(rs.getString(ClientUserSqlColumns.PASSWORD))
                .role(rs.getString(ClientUserSqlColumns.ROLE))
                .build();
    }
}
