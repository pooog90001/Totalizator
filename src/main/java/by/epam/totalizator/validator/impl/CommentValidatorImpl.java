package by.epam.totalizator.validator.impl;

import by.epam.totalizator.validator.CommentValidator;

public class CommentValidatorImpl implements CommentValidator {

    @Override
    public boolean isCommentTextValid(String text) {
        boolean isValid = true;

        if (text == null) {
            isValid = false;
        } else if (text.trim().length() > 300 || text.trim().isEmpty()) {
            isValid = false;
        }
        return isValid;
    }
}
