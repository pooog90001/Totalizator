package by.epam.totalizator.validator;

public interface CompetitionTypeValidator extends Validator {
    /**
     * Is name valid.
     *
     * @param name
     * @return
     */
    boolean isNameValid(String name);
}
