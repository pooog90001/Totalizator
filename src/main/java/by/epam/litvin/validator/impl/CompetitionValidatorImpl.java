package by.epam.litvin.validator.impl;

import by.epam.litvin.bean.CompetitionEntity;
import by.epam.litvin.validator.CompetitionValidator;

import java.math.BigDecimal;
import java.util.Date;

public class CompetitionValidatorImpl implements CompetitionValidator {

    @Override
    public boolean isNameValid(String name) {
        boolean isValid = true;

        if (name == null || name.isEmpty() || name.length() > 100) {
            isValid = false;
        }

        return isValid;
    }

    @Override
    public boolean isCoeffValid(BigDecimal coeff) {
        boolean isValid = true;

        if (coeff == null || (coeff.compareTo(new BigDecimal("1.00")) == -1)) {
            isValid = false;
        }

        return isValid;
    }

    @Override
    public boolean isNumberValid(BigDecimal number) {
        boolean isValid = true;

        if (number == null || (number.compareTo(new BigDecimal("0.00")) == -1)) {
            isValid = false;
        }

        return isValid;
    }


    @Override
    public BigDecimal checkStringCoeff(String stringCoeff) {
        BigDecimal coeff = null;

        if (stringCoeff != null && !stringCoeff.isEmpty()) {
            BigDecimal decimal = new BigDecimal(stringCoeff);
            decimal = decimal.setScale(2, BigDecimal.ROUND_HALF_UP);

            if (isCoeffValid(decimal)) {
                coeff = decimal;
            }
        }

        return coeff;
    }

    @Override
    public BigDecimal checkStringNumber(String stringCoeff) {
        BigDecimal number = null;

        if (stringCoeff != null && !stringCoeff.isEmpty()) {
            BigDecimal decimal = new BigDecimal(stringCoeff);
            decimal = decimal.setScale(2, BigDecimal.ROUND_HALF_UP);

            if (isNumberValid(decimal)) {
                number = decimal;
            }
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
