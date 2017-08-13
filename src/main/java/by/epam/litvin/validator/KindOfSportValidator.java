package by.epam.litvin.validator;

public interface KindOfSportValidator extends Validator {
    boolean isCompetitorsCountValid(int competitorsCount);
    boolean isNameValid(String name);
}
