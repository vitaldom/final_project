import org.junit.Test;
import ua.kpi.model.dao.impl.ConnectionPool;

import java.sql.*;

public class LocaleGetTest {
    @Test
    public void DBtest() {

        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             Statement statement = connection.createStatement()){

            PreparedStatement ps = connection.prepareStatement("select MAX(id) from inspector_change_requests");
            ResultSet rs = ps.executeQuery();

            System.out.println("Value of rs.next(): " + rs.next());

            int i = rs.getInt("Max(id)");

            System.out.println("int is: " + i);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}