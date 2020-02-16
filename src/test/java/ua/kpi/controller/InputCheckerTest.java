package ua.kpi.controller;

import org.junit.Assert;
import org.junit.Test;
import ua.kpi.controller.inputcheck.InputChecker;

public class InputCheckerTest {

    @Test
    public void nameIsValidUkrTest() {
        Assert.assertTrue(InputChecker.nameIsValid("Наталія", "uk"));
        Assert.assertTrue(InputChecker.nameIsValid("Борисівньаюїяг", "uk"));
        Assert.assertFalse(InputChecker.nameIsValid("варвара", "uk"));
        Assert.assertFalse(InputChecker.nameIsValid("ыънаш", "uk"));
        Assert.assertFalse(InputChecker.nameIsValid("Ivan", "uk"));
        Assert.assertFalse(InputChecker.nameIsValid("Нат алія", "uk"));
        Assert.assertFalse(InputChecker.nameIsValid("Роман.", "uk"));
        Assert.assertFalse(InputChecker.nameIsValid(" Костянтин", "uk"));
        Assert.assertFalse(InputChecker.nameIsValid("Іван1", "uk"));
        Assert.assertFalse(InputChecker.nameIsValid("Паніжвдоавлоеріловадівожіоаіжоі", "uk"));
    }

    @Test
    public void nameIsValidEnTest() {
        Assert.assertTrue(InputChecker.nameIsValid("John", "en"));
        Assert.assertTrue(InputChecker.nameIsValid("Ofhsjfhjakhfkjeoi", "en"));
        Assert.assertFalse(InputChecker.nameIsValid("ыънаш", "en"));
        Assert.assertFalse(InputChecker.nameIsValid("Ivan.", "en"));
        Assert.assertFalse(InputChecker.nameIsValid("kvazimodo", "en"));
        Assert.assertFalse(InputChecker.nameIsValid("ksas-", "en"));
        Assert.assertFalse(InputChecker.nameIsValid(" John", "en"));
        Assert.assertFalse(InputChecker.nameIsValid("Katalina1", "en"));
        Assert.assertFalse(InputChecker.nameIsValid("+r", "en"));
    }

    @Test
    public void loginIsValidEnTest() {
        Assert.assertTrue(InputChecker.loginIsValid("John"));
        Assert.assertTrue(InputChecker.loginIsValid("Ofhsjfhjakhfkjeoi"));
        Assert.assertTrue(InputChecker.loginIsValid("Katalina1"));
        Assert.assertTrue(InputChecker.loginIsValid("Ivan."));
        Assert.assertTrue(InputChecker.loginIsValid("kvazimodo"));
        Assert.assertTrue(InputChecker.loginIsValid("ksas-"));
        Assert.assertFalse(InputChecker.loginIsValid(" John"));
        Assert.assertFalse(InputChecker.loginIsValid("ыънаш"));
        Assert.assertFalse(InputChecker.loginIsValid("+r"));
        Assert.assertFalse(InputChecker.loginIsValid("kvaz imodo"));
    }

    @Test
    public void passwordIsValidEnTest() {
        Assert.assertTrue(InputChecker.loginIsValid("John"));
        Assert.assertTrue(InputChecker.loginIsValid("Ofhsjfhjakhfkjeoi"));
        Assert.assertTrue(InputChecker.loginIsValid("Katalina1"));
        Assert.assertTrue(InputChecker.loginIsValid("Ivan."));
        Assert.assertTrue(InputChecker.loginIsValid("kvazimodo"));
        Assert.assertTrue(InputChecker.loginIsValid("ksas-"));
        Assert.assertFalse(InputChecker.loginIsValid(" John"));
        Assert.assertFalse(InputChecker.loginIsValid("ыънаш"));
        Assert.assertFalse(InputChecker.loginIsValid("+r"));
        Assert.assertFalse(InputChecker.loginIsValid("kvaz imodo"));
    }

    @Test
    public void longIsValidEnTest() {
        Assert.assertTrue(InputChecker.longIsValid("32132"));
        Assert.assertTrue(InputChecker.longIsValid("0"));
        Assert.assertTrue(InputChecker.longIsValid("72984092883434"));
        Assert.assertFalse(InputChecker.longIsValid("-1"));
        Assert.assertFalse(InputChecker.longIsValid("ыънаш"));
        Assert.assertFalse(InputChecker.longIsValid(""));
        Assert.assertFalse(InputChecker.longIsValid("ee"));
    }



}