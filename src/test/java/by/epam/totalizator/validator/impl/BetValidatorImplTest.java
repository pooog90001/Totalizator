package by.epam.totalizator.validator.impl;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BetValidatorImplTest {
    private BetValidatorImpl betValidator;

    @Before
    public void setUp() throws Exception {
        betValidator = new BetValidatorImpl();
    }

    @Test
    public void checkBetSize1() {
        BigDecimal decimal = new BigDecimal(1000);

        assertTrue(betValidator.checkBetSize(decimal));
    }

    @Test
    public void checkBetSize2() {
        BigDecimal decimal = new BigDecimal(1001);

        assertFalse(betValidator.checkBetSize(decimal));
    }

    @Test
    public void checkBetSize3() {
        BigDecimal decimal = new BigDecimal(-1000);

        assertFalse(betValidator.checkBetSize(decimal));
    }

    @Test
    public void checkBetSize4() {

        assertFalse(betValidator.checkBetSize(null));
    }


}