package by.epam.litvin.validator;

public interface CommonValidator {
    boolean isVarExist(String[] stringVar);
    boolean isImageExtensionValid(String imageFormat);

    boolean isInteger(String string);
}
