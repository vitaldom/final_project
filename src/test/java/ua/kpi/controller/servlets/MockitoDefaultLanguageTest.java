package ua.kpi.controller.servlets;

import org.junit.Assert;
import org.junit.Test;
import ua.kpi.controller.inputcheck.ResourceBundleDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ResourceBundle;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoDefaultLanguageTest {

    @Test
    public void mockitoDefaultLanguageTest() throws ServletException, IOException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        ResourceBundle resourceBundle = ResourceBundleDispatcher.getResourceBundle(request);
        resourceBundle.getLocale();

        Assert.assertEquals(resourceBundle.getLocale().toString(), "uk");
    }
}