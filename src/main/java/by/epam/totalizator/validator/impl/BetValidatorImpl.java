package by.epam.totalizator.validator.impl;

import by.epam.totalizator.validator.BetValidator;

import java.math.BigDecimal;

public class BetValidatorImpl implements BetValidator {
    private static final int MAX_BET_SIZE = 1000;
    private static final int MIN_BET_SIZE = 1;
    private static final int SCALE = 2;

    @Override
    public boolean checkBetSize(BigDecimal betSize) {
        boolean isValid = true;

        if (betSize != null) {
            BigDecimal comparableNumber = new BigDecimal(MIN_BET_SIZE).setScale(SCALE, BigDecimal.ROUND_DOWN);
            betSize = betSize.setScale(SCALE, BigDecimal.ROUND_DOWN);

            if (betSize.compareTo(comparableNumber) < 0) {
                isValid = false;
            }
            if (betSize.compareTo(new BigDecimal(MAX_BET_SIZE)) == 1) {
                isValid = false;
            }

        } else {
            isValid = false;
        }

        return isValid;
    }
}
