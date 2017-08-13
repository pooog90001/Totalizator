package by.epam.litvin.validator.impl;

import by.epam.litvin.validator.CompetitionTypeValidator;

public class CompetitionTypeValidatorImpl implements CompetitionTypeValidator {

    @Override
    public boolean isNameValid(String name) {
        boolean isValid = true;

        if (name == null || name.isEmpty() || name.length() > 200) {
            isValid = false;
        }

        return isValid;
    }
}
