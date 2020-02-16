package ua.kpi.model.entities;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeclarationTest {

    @Test
    public void getStatusTest() {
        Declaration.Status test = Declaration.Status.UNDER_CORRECTION;

        Assert.assertTrue(test.toString() == "UNDER_CORRECTION");

        System.out.println("Printing test: " + test);
        System.out.println("Printing test.toString: " + test.toString());
    }

}