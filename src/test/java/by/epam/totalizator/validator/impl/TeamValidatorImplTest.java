package by.epam.totalizator.validator.impl;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TeamValidatorImplTest {
    private static TeamValidatorImpl teamValidator;

    @BeforeClass
    public static void setUp() throws Exception {
        teamValidator = new TeamValidatorImpl();
    }

    @Test
    public void isNameValid1() {
        String name = "eeeeee";
        assertTrue(teamValidator.isNameValid(name));
    }

    @Test
    public void isNameValid2() {
        String name = "eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee " +
                "eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee " +
                "eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee " +
                "eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee " +
                "eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee";
        assertFalse(teamValidator.isNameValid(name));
    }

    @Test
    public void isNameValid3() {
        assertFalse(teamValidator.isNameValid(null));
    }

    @Test
    public void isNameValid4() {
        String name = "  ";
        assertFalse(teamValidator.isNameValid(name));
    }

}