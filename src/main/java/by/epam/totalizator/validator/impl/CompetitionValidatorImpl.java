package by.epam.totalizator.validator.impl;

import by.epam.totalizator.entity.CompetitionEntity;
import by.epam.totalizator.validator.CompetitionValidator;

import java.math.BigDecimal;
import java.util.Date;

public class CompetitionValidatorImpl implements CompetitionValidator {
    private static final int MAX_NAME_LENGTH = 100;
    private static final int CASH_SCALE = 2;
    private static final int MIN_NUMBER_VALUE = 0;
    private static final int MIN_COEFFICIENT = 1;

    @Override
    public boolean isNameValid(String name) {
        boolean isValid = true;

        if (name == null || name.trim().isEmpty() ||
                name.trim().length() > MAX_NAME_LENGTH) {
            isValid = false;
        }

        return isValid;
    }

    @Override
    public boolean isCoeffValid(BigDecimal coeff) {
        boolean isValid = true;

        if (coeff == null || (coeff.compareTo(new BigDecimal(MIN_COEFFICIENT)) == -1)) {
            isValid = false;
        }

        return isValid;
    }

    public boolean isCoeff(String coeff) {
        boolean isValid = true;

        try {
            if (coeff == null || coeff.isEmpty() ||
                    (new BigDecimal(coeff).compareTo(new BigDecimal(MIN_COEFFICIENT)) == -1)) {
                isValid = false;
            }
        } catch (NumberFormatException e) {
            isValid = false;
        }

        return isValid;
    }

    @Override
    public boolean isNumberValid(BigDecimal number) {
        boolean isValid = true;

        if (number == null || (number.compareTo(new BigDecimal(MIN_NUMBER_VALUE)) == -1)) {
            isValid = false;
        }

        return isValid;
    }


    @Override
    public BigDecimal checkStringCoeff(String stringCoeff) {
        BigDecimal coeff = null;

        if (stringCoeff != null && !stringCoeff.isEmpty()) {
            try {
                BigDecimal decimal = new BigDecimal(stringCoeff);
                decimal = decimal.setScale(CASH_SCALE, BigDecimal.ROUND_HALF_UP);

                if (isCoeffValid(decimal)) {
                    coeff = decimal;
                }
            } catch (NumberFormatException e) {
                coeff = null;
            }


        }

        return coeff;
    }

    @Override
    public BigDecimal checkStringNumber(String stringCoeff) {
        BigDecimal number = null;

        try {
            if (stringCoeff != null && !stringCoeff.isEmpty()) {
                BigDecimal decimal = new BigDecimal(stringCoeff);
                decimal = decimal.setScale(CASH_SCALE, BigDecimal.ROUND_HALF_UP);

                if (isNumberValid(decimal)) {
                    number = decimal;
                }
            }
        } catch (NumberFormatException e) {
            number = null;
        }

        return number;
    }

    @Override
    public boolean isValidForBet(CompetitionEntity competition) {
        boolean isValid = true;

        if (competition.getDateStart().before(new Date())) {
            isValid = false;
        }

        if (!competition.getActive()) {
            isValid = false;
        }

        return isValid;
    }

}
