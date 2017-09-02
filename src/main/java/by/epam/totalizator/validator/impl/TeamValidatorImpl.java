package by.epam.totalizator.validator.impl;

import by.epam.totalizator.validator.TeamValidator;

import java.util.regex.Pattern;

public class TeamValidatorImpl implements TeamValidator {
    private static final String WRONG_SYMBOLS_REGEXP = "[<>]";
    private static final int MAX_NAME_LENGTH = 80;


    @Override
    public boolean isNameValid(String name) {
        boolean isValid = true;

        if (name == null || name.trim().isEmpty() || name.trim().length() > MAX_NAME_LENGTH ||
                Pattern.compile(WRONG_SYMBOLS_REGEXP).matcher(name).find()) {
            isValid = false;
        }

        return isValid;
    }
}
