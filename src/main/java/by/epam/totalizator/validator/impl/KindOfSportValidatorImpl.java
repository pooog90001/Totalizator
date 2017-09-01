package by.epam.totalizator.validator.impl;

import by.epam.totalizator.validator.KindOfSportValidator;

public class KindOfSportValidatorImpl implements KindOfSportValidator {

    @Override
    public boolean isCompetitorsCountValid(int competitorsCount) {
        boolean isValid = true;

        if (competitorsCount < 2 || competitorsCount > 1000) {
            isValid = false;
        }

        return isValid;
    }

    @Override
    public boolean isNameValid(String name) {
        boolean isValid = true;

        if (name == null || name.isEmpty() || name.length() > 45) {
            isValid = false;
        }

        return isValid;
    }

}
