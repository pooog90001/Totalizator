package by.epam.litvin.validator;

import javax.servlet.http.Part;

public interface NewsValidator extends Validator {
    boolean isTitleValid(String title);
    boolean isTextValid(String text);
    boolean isImageValid(Part image);
}
