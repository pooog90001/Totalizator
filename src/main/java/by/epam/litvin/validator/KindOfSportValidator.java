package by.epam.litvin.validator;

public class KindOfSportValidator {

    public boolean isCompetitorsCountValid(int competitorsCount) {
        boolean isValid = true;

        if (competitorsCount < 2 || competitorsCount > 1000) {
            isValid = false;
        }

        return isValid;
    }

    public boolean isNameValid(String name) {
        boolean isValid = true;

        if (name == null || name.isEmpty() || name.length() > 45) {
            isValid = false;
        }

        return isValid;
    }

}
