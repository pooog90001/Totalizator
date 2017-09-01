package by.epam.totalizator.validator.impl;

import by.epam.totalizator.type.ImageFormatType;
import by.epam.totalizator.validator.CommonValidator;

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

        if (extension != null) {
            try {
                ImageFormatType.valueOf(extension.toUpperCase());

            } catch (IllegalArgumentException e) {
                isValid = false;
            }
        } else {
            isValid = false;
        }

        return isValid;
    }

    @Override
    public boolean isInteger(String stringNumber) {
        boolean isInteger = true;


        try {
            if (stringNumber != null) {
                Integer.parseInt(stringNumber);

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

}
