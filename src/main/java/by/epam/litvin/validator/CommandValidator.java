package by.epam.litvin.validator;

public class CommandValidator implements Validator {

    public boolean isNameValid(String name) {
        boolean isValid = true;

        if (name == null || name.isEmpty() || name.length() > 80) {
            isValid = false;
        }

        return isValid;
    }
}
