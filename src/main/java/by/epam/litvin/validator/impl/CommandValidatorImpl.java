package by.epam.litvin.validator.impl;

import by.epam.litvin.validator.CommandValidator;

public class CommandValidatorImpl implements CommandValidator {

    @Override
    public boolean isNameValid(String name) {
        boolean isValid = true;

        if (name == null || name.isEmpty() || name.length() > 80) {
            isValid = false;
        }

        return isValid;
    }
}
