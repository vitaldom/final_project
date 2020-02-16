package ua.kpi.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.kpi.model.dao.impl.ConnectionPool;
import ua.kpi.model.entities.AbstractAppUser;
import ua.kpi.model.entities.ClientUser;
import ua.kpi.model.services.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcAbstractUserDaoTest {

    UserService userService = new UserService();
    boolean tmp;

    @Before
     public void createTestUser() {

        AbstractAppUser user = new ClientUser.Builder().firstName("Ана").secondName("Саушкіна")
                .login("ana").password("4567").role("CLIENT").build();


        tmp = userService.create(user);
    }

    @Test
    public void findTest() {
        AbstractAppUser user = userService.find("ana", "4567");

        System.out.println(user);
        System.out.println("New user created: " + tmp);
    }

    @After
    public void cleanUp() {

        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             Statement statement = connection.createStatement()){

            statement.execute("DELETE FROM `testdb`.`clients` WHERE (`login` = 'ana')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
