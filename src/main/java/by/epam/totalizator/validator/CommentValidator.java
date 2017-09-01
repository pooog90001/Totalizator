package by.epam.totalizator.validator;

public interface CommentValidator extends Validator {

    /**
     * Is comment text valid.
     *
     * @param text
     * @return
     */
    boolean isCommentTextValid(String text);
}
