package by.epam.litvin.validator;

public class CompetitionTypeValidator implements Validator {

    public boolean isNameValid(String name) {
        boolean isValid = true;

        if (name == null || name.isEmpty() || name.length() > 200) {
            isValid = false;
        }

        return isValid;
    }
}
