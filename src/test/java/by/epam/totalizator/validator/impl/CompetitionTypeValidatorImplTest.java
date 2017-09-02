package by.epam.totalizator.validator.impl;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CompetitionTypeValidatorImplTest {
    private static CompetitionTypeValidatorImpl competitionTypeValidator;

    @BeforeClass
    public static void setUp() throws Exception {
        competitionTypeValidator = new CompetitionTypeValidatorImpl();
    }

    @Test
    public void isNameValid1() {
        String name = "ASd";
        assertTrue(competitionTypeValidator.isNameValid(name));
    }

    @Test
    public void isNameValid2() {
        assertFalse(competitionTypeValidator.isNameValid(null));
    }

    @Test
    public void isNameValid3() {
        String name = "eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee " +
                "eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee " +
                "eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee " +
                "eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee " +
                "eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee";
        assertFalse(competitionTypeValidator.isNameValid(name));
    }

    @Test
    public void isNameValid4() {
        String name = "    ";
        assertFalse(competitionTypeValidator.isNameValid(name));
    }

}