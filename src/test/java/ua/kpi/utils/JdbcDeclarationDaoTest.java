package ua.kpi.utils;

import org.junit.Test;
import ua.kpi.model.dao.impl.ConnectionPool;
import ua.kpi.model.entities.Declaration;
import ua.kpi.model.services.DeclarationService;

import java.sql.*;
import java.util.List;

public class JdbcDeclarationDaoTest {

    @Test
    public void findAllByClientLoginTest() {

        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             Statement statement = connection.createStatement()){


            DeclarationService declarationService = new DeclarationService();

           List<Declaration> test = declarationService.findAllByClientLogin("john");


           int count = 1;

           for (Declaration d : test) {
               System.out.println("Count in loop is: " + count++);
               System.out.println(d);
           }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}