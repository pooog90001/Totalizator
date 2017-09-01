package by.epam.totalizator.validator;

public interface TeamValidator extends Validator {
    /**
     * Is name valid.
     *
     * @param name
     * @return
     */
    boolean isNameValid(String name);
}
