package by.epam.totalizator.validator.impl;

import by.epam.totalizator.validator.KindOfSportValidator;

public class KindOfSportValidatorImpl implements KindOfSportValidator {
    private static final int MIN_COMPETITORS_COUNT = 2;
    private static final int MAX_COMPETITORS_COUNT = 1000;
    private static final int MAX_NAME_LENGTH = 45;

    @Override
    public boolean isCompetitorsCountValid(int competitorsCount) {
        boolean isValid = true;

        if (competitorsCount < MIN_COMPETITORS_COUNT
                || competitorsCount > MAX_COMPETITORS_COUNT) {
            isValid = false;
        }
        return isValid;
    }

    @Override
    public boolean isNameValid(String name) {
        boolean isValid = true;

        if (name == null || name.trim().isEmpty() || name.trim().length() > MAX_NAME_LENGTH) {
            isValid = false;
        }
        return isValid;
    }

}
