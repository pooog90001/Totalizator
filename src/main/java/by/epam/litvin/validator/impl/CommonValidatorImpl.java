package by.epam.litvin.validator.impl;

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

    @Override
    public boolean isInteger(String string) {
        boolean isInteger = true;

        try {
            if (string != null) {
                int i = Integer.parseInt(string);

            } else {
                isInteger = false;
            }

        } catch (NumberFormatException e) {
            isInteger = false;
        }
        return isInteger;
    }


    public boolean checkParamsForInteger(String[]... params) {
        boolean isValid = true;

        if (params == null) {
            isValid = false;
        }

        if (isValid) {
            for (String[] param : params) {
                if (!isVarExist(param) || !isInteger(param[0])) {
                    isValid = false;
                    break;
                }
            }
        }
        return isValid;
    }

    public boolean isPageNumber(String stringPage) {
        boolean isPageNumber = true;

        try {
            int page = Integer.parseInt(stringPage);

            if (page < 1) {
                isPageNumber = false;
            }

        } catch (NumberFormatException e) {
            isPageNumber = false;
        }
        return isPageNumber;
    }

}
