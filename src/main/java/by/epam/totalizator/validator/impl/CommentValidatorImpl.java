package by.epam.totalizator.validator.impl;

import by.epam.totalizator.validator.CommentValidator;

public class CommentValidatorImpl implements CommentValidator {
    private static final int MAX_COMMENT_LENGTH = 300;

    @Override
    public boolean isCommentTextValid(String text) {
        boolean isValid = true;

        if (text == null) {
            isValid = false;
        } else if (text.trim().length() > MAX_COMMENT_LENGTH || text.trim().isEmpty()) {
            isValid = false;
        }
        return isValid;
    }
}
