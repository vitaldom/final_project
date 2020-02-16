package ua.kpi.model.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientUserTest {

    @Test
    public void ClientUserBuilderTest() throws Exception {
        ClientUser test = new ClientUser.Builder().firstName("John").secondName("Snow").login("login")
                .password("1234").role("CLIENT").build();

        System.out.println(test);

        Assert.assertTrue(test instanceof  AbstractAppUser);
        Assert.assertTrue(test instanceof  ClientUser);
    }
}