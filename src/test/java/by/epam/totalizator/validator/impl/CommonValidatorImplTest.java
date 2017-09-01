package by.epam.totalizator.validator.impl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommonValidatorImplTest {
    CommonValidatorImpl commonValidator;

    @Before
    public void setUp() throws Exception {
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
    }

}