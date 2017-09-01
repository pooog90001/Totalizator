package by.epam.totalizator.validator.impl;

import by.epam.totalizator.validator.NewsValidator;

public class NewsValidatorImpl implements NewsValidator {
    @Override
    public boolean isTitleValid(String title) {
        boolean isValid = true;

        if (title == null || title.isEmpty() || title.length() > 45) {
            isValid = false;
        }

        return isValid;
    }

    @Override
    public boolean isTextValid(String text) {
        boolean isValid = true;

        if (text == null || text.isEmpty() || text.length() > 16_770_000) {
            isValid = false;
        }

        return isValid;
    }

}
