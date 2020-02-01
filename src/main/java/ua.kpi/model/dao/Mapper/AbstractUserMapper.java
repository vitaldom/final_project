package ua.kpi.model.dao.Mapper;

import ua.kpi.model.dao.impl.columns.UsersSqlColumns;
import ua.kpi.model.entities.AbstractAppUser;
import ua.kpi.model.entities.ClientUser;
import ua.kpi.model.entities.InspectorUser;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AbstractUserMapper implements Mapper<AbstractAppUser> {

    @Override
    public AbstractAppUser extractFromResultSet(ResultSet rs) throws SQLException {

        String role = rs.getString(UsersSqlColumns.ROLE);

        if (role == null) { // TODO consider returning non-null value. Check if null is possible
            return null;
        }

        if (role.equals("CLIENT")) {
            return new ClientUser.Builder()
                    .firstName(rs.getString(UsersSqlColumns.FIRST_NAME))
                    .secondName(rs.getString(UsersSqlColumns.SECOND_NAME))
                    .login(rs.getString(UsersSqlColumns.LOGIN))
                    .password(rs.getString(UsersSqlColumns.PASSWORD))
                    .role(rs.getString(UsersSqlColumns.ROLE))
                    .build();
        }

        else {  //role = "INSPECTOR"                                            //TODO Check ways to avoid duplication
                                                                                //TODO returning Abstract app user is a good practice?
            return new InspectorUser.Builder()
                    .firstName(rs.getString(UsersSqlColumns.FIRST_NAME))
                    .secondName(rs.getString(UsersSqlColumns.SECOND_NAME))
                    .login(rs.getString(UsersSqlColumns.LOGIN))
                    .password(rs.getString(UsersSqlColumns.PASSWORD))
                    .role(rs.getString(UsersSqlColumns.ROLE))
                    .build();
        }
    }
}
