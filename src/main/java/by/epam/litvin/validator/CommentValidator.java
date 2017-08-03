package by.epam.litvin.validator;

public class CommentValidator {

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
