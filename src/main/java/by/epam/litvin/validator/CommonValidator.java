package by.epam.litvin.validator;

public interface CommonValidator {
    /**
     * Is variable exists.
     *
     * @param stringVar
     * @return
     */
    boolean isVarExist(String[] stringVar);

    /**
     * Is image extension valid.
     *
     * @param imageFormat
     * @return
     */
    boolean isImageExtensionValid(String imageFormat);

    /**
     * Is integer.
     *
     * @param string
     * @return
     */
    boolean isInteger(String string);
}
