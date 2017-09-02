package by.epam.totalizator.validator.impl;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommonValidatorImplTest {
    private static CommonValidatorImpl commonValidator;

    @BeforeClass
    public static void setUp() throws Exception {
        commonValidator = new CommonValidatorImpl();
    }

    @Test
    public void isVarExist1() {
        String[] arr = {"dsf", "sdf"};
        assertTrue(commonValidator.isVarExist(arr));
    }

    @Test
    public void isVarExist2() {
        String[] arr = {" ", "sdf"};
        assertFalse(commonValidator.isVarExist(arr));
    }

    @Test
    public void isVarExist3() {
        String[] arr = {null, "sdf"};
        assertFalse(commonValidator.isVarExist(arr));
    }

    @Test
    public void isVarExist4() {
        assertFalse(commonValidator.isVarExist(null));
    }

    @Test
    public void isImageExtensionValid1() {
        String extension = "sd";
        assertFalse(commonValidator.isImageExtensionValid(extension));
    }

    @Test
    public void isImageExtensionValid3() {
        String extension = "jpg";
        assertTrue(commonValidator.isImageExtensionValid(extension));
    }


    @Test
    public void isImageExtensionValid2() {
        assertFalse(commonValidator.isImageExtensionValid(null));
    }

    @Test
    public void isInteger1() {
        String stringNumber = "sad";
        assertFalse(commonValidator.isImageExtensionValid(stringNumber));
    }

    @Test
    public void isInteger2() {
        String stringNumber = "12";
        assertTrue(commonValidator.isImageExtensionValid(stringNumber));
    }

    @Test
    public void isInteger3() {
        String stringNumber = "0,12";
        assertFalse(commonValidator.isImageExtensionValid(stringNumber));
    }

    @Test
    public void isInteger4() {
        assertFalse(commonValidator.isImageExtensionValid(null));
    }

    @Test
    public void checkParamsForInteger() {
        String[] param1 = {"213"};
        String[] param2 = {"213"};
        String[] param3 = {"213"};

        assertTrue(commonValidator.checkParamsForInteger(param1, param2, param3));
    }

    @Test
    public void checkParamsForInteger1() {
        String[] param1 = {"213"};
        String[] param2 = {"213"};
        String[] param3 = {"213"};

        assertTrue(commonValidator.checkParamsForInteger(param1, param2, param3));
    }

    @Test
    public void checkParamsForInteger2() {
        String[] param1 = {"213"};
        String[] param2 = {"mk"};
        String[] param3 = {"213"};

        assertFalse(commonValidator.checkParamsForInteger(param1, param2, param3));
    }

    @Test
    public void checkParamsForInteger3() {
        String[] param1 = {"213"};
        String[] param2 = {null};
        String[] param3 = {"213"};

        assertFalse(commonValidator.checkParamsForInteger(param1, param2, param3));
    }

    @Test
    public void checkParamsForInteger4() {
        assertFalse(commonValidator.checkParamsForInteger(null, null));
    }

}