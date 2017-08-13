package by.epam.litvin.validator.impl;

import by.epam.litvin.validator.CommentValidator;

public class CommentValidatorImpl implements CommentValidator {

    @Override
    public boolean isCommentTextValid(String text) {
        boolean isValid = true;

        if (text == null) {
            isValid = false;
        } else if (text.length() > 300 || text.isEmpty()) {
            isValid = false;
        }
        return isValid;
    }
}
