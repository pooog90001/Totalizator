package by.epam.litvin.validator.impl;

import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.CommonReceiver;
import by.epam.litvin.type.ImageFormatType;
import by.epam.litvin.validator.CommonValidator;

public class CommonValidatorImpl implements CommonValidator {
    @Override
    public boolean isVarExist(String[] stringVar) {
        boolean isExist = true;

        if (stringVar == null || stringVar[0] == null ||
                stringVar[0].trim().isEmpty()) {

            isExist = false;
        }

        return isExist;
    }

    @Override
    public boolean isImageExtensionValid(String extension) {
        boolean isValid = true;

        try {
           ImageFormatType.valueOf(extension.toUpperCase());

        } catch (IllegalArgumentException e) {
            isValid = false;
        }

        return isValid;
    }
}
