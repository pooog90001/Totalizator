package by.epam.totalizator.validator.impl;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserValidatorImplTest {
    private static UserValidatorImpl userValidator;

    @BeforeClass
    public static void setUp() throws Exception {
        userValidator = new UserValidatorImpl();
    }

    @Test
    public void checkPassword1() {
        String password = "Epam123";
        assertTrue(userValidator.checkPassword(password));
    }

    @Test
    public void checkPassword2() {
        String password = "12123";
        assertFalse(userValidator.checkPassword(password));
    }

    @Test
    public void checkPassword3() {
        String password = " ";
        assertFalse(userValidator.checkPassword(password));
    }

    @Test
    public void checkPassword4() {
        assertFalse(userValidator.checkPassword(null));
    }

    @Test
    public void checkEmail1() {
        String email = "sdfsdf ";
        assertFalse(userValidator.checkEmail(email));
    }

    @Test
    public void checkEmail2() {
        String email = "test@mail.com";
        assertTrue(userValidator.checkEmail(email));
    }

    @Test
    public void checkEmail3() {
        String email = " ";
        assertFalse(userValidator.checkEmail(email));
    }

    @Test
    public void checkEmail4() {
        assertFalse(userValidator.checkEmail(null));
    }


    @Test
    public void checkName1() {
        String name = "asdasd";
        assertTrue(userValidator.checkName(name));
    }

    @Test
    public void checkName2() {
        String name = " ";
        assertFalse(userValidator.checkName(name));
    }

    @Test
    public void checkName3() {
        String name = "asdssssssssssssss ssssssssssss " +
                "sssssssssss ssssssssssss sssssssssssssss";
        assertFalse(userValidator.checkName(name));
    }

    @Test
    public void checkName4() {
        assertFalse(userValidator.checkName(null));
    }

}