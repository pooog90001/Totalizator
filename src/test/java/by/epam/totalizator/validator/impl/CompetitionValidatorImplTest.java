package by.epam.totalizator.validator.impl;

import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CompetitionValidatorImplTest {
    private static CompetitionValidatorImpl competitionValidator;

    @BeforeClass
    public static void setUp() throws Exception {
        competitionValidator = new CompetitionValidatorImpl();
    }

    @Test
    public void isNameValid1() {
        String name = "asdasd";
        assertTrue(competitionValidator.isNameValid(name));
    }

    @Test
    public void isNameValid2() {
        String name = "  ";
        assertFalse(competitionValidator.isNameValid(name));
    }

    @Test
    public void isNameValid3() {
        String name = "eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee " +
                "eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee " +
                "eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee " +
                "eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee " +
                "eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee eeeeeeeeee";
        assertFalse(competitionValidator.isNameValid(name));
    }

    @Test
    public void isNameValid4() {
        assertFalse(competitionValidator.isNameValid(null));
    }


    @Test
    public void isCoeff1() {
        String stringCoeff = "sad";
        assertFalse(competitionValidator.isCoeff(stringCoeff));
    }

    @Test
    public void isCoeff2() {
        String stringCoeff = "  ";
        assertFalse(competitionValidator.isCoeff(stringCoeff));
    }

    @Test
    public void isCoeff3() {
        assertFalse(competitionValidator.isCoeff(null));
    }

    @Test
    public void isCoeff4() {
        String stringCoeff = "1";
        assertTrue(competitionValidator.isCoeff(stringCoeff));
    }

    @Test
    public void isCoeff5() {
        String stringCoeff = "-23";
        assertFalse(competitionValidator.isCoeff(stringCoeff));
    }

    @Test
    public void isCoeffValid1() {
        BigDecimal coeff = new BigDecimal(23);
        assertTrue(competitionValidator.isCoeffValid(coeff));
    }

    @Test
    public void isCoeffValid2() {
        BigDecimal coeff = new BigDecimal(-2);
        assertFalse(competitionValidator.isCoeffValid(coeff));
    }

    @Test
    public void isCoeffValid3() {
        assertFalse(competitionValidator.isCoeffValid(null));
    }


    @Test
    public void isNumberValid1() {
        BigDecimal coeff = new BigDecimal(2);
        assertTrue(competitionValidator.isNumberValid(coeff));
    }

    @Test
    public void isNumberValid2() {
        BigDecimal coeff = new BigDecimal(-2);
        assertFalse(competitionValidator.isNumberValid(coeff));
    }

    @Test
    public void isNumberValid3() {
        assertFalse(competitionValidator.isNumberValid(null));
    }

}