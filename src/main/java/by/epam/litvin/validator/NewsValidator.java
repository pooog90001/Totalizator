package by.epam.litvin.validator;

import javax.servlet.http.Part;

public interface NewsValidator extends Validator {
    /**
     * Is title valid.
     *
     * @param title
     * @return
     */
    boolean isTitleValid(String title);

    /**
     * Is text valid.
     *
     * @param text
     * @return
     */
    boolean isTextValid(String text);

    /**
     * Is image valid.
     *
     * @param image
     * @return
     */
    boolean isImageValid(Part image);
}
