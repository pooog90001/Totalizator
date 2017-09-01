package by.epam.totalizator.validator.impl;

import by.epam.totalizator.validator.TeamValidator;

import java.util.regex.Pattern;

public class TeamValidatorImpl implements TeamValidator {
    public static final String WRONG_SYMBOLS_REGEXP = "[<>]";

    @Override
    public boolean isNameValid(String name) {
        boolean isValid = true;

        if (name == null || name.isEmpty() || name.length() > 80 ||
                Pattern.compile(WRONG_SYMBOLS_REGEXP).matcher(name).find()) {
            isValid = false;
        }

        return isValid;
    }
}
