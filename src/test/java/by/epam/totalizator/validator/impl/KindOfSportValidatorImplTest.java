package by.epam.totalizator.validator.impl;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class KindOfSportValidatorImplTest {
    private static KindOfSportValidatorImpl kindOfSportValidator;

    @BeforeClass
    public static void setUp() throws Exception {
        kindOfSportValidator = new KindOfSportValidatorImpl();
    }

    @Test
    public void isCompetitorsCountValid1() {
        int count = 2;
        assertTrue(kindOfSportValidator.isCompetitorsCountValid(count));
    }

    @Test
    public void isCompetitorsCountValid2() {
        int count = 0;
        assertFalse(kindOfSportValidator.isCompetitorsCountValid(count));
    }

    @Test
    public void isCompetitorsCountValid3() {
        int count = 1222;
        assertFalse(kindOfSportValidator.isCompetitorsCountValid(count));
    }


    @Test
    public void isNameValid1() {
        String name = "eeeeee";
        assertTrue(kindOfSportValidator.isNameValid(name));
    }

    @Test
    public void isNameValid2() {
        String name = "eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee " +
                "eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee " +
                "eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee " +
                "eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee " +
                "eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee";
        assertFalse(kindOfSportValidator.isNameValid(name));
    }

    @Test
    public void isNameValid3() {
        assertFalse(kindOfSportValidator.isNameValid(null));
    }

    @Test
    public void isNameValid4() {
        String name = "  ";
        assertFalse(kindOfSportValidator.isNameValid(name));
    }

}