package by.epam.totalizator.validator.impl;

import by.epam.totalizator.bean.UserEntity;
import by.epam.totalizator.type.UserType;
import by.epam.totalizator.validator.UserValidator;

import java.util.regex.Pattern;

public class UserValidatorImpl implements UserValidator {
    private final static String EMAIL_REGEX = "([_\\w-]+(\\.[_\\w-]+)*@[\\w-]+(\\.[\\w-]+)*(\\.[a-zA-Z]{1,6}))";
    private final static  String BIG_RU_ENG_LETTER_REGEX = "[A-ZА-Я]";
    private final static  String LITTLE_RU_ENG_LETTER_REGEX = "[a-zа-я]";
    private final static  String NUMBER_REGEX = "\\d";

    @Override
    public boolean checkPassword(String password) {
        boolean hasBigWord = Pattern.compile(BIG_RU_ENG_LETTER_REGEX).matcher(password).find();
        boolean hasWord = Pattern.compile(LITTLE_RU_ENG_LETTER_REGEX).matcher(password).find();
        boolean hasNumber = Pattern.compile(NUMBER_REGEX).matcher(password).find();
        boolean hasSatisfactoryLength = (password.length() >= 6 && password.length() < 80);

        return (hasBigWord && hasWord && hasNumber && hasSatisfactoryLength);
    }

    @Override
    public boolean checkEmail(String email) {
        boolean isEmail = Pattern.compile(EMAIL_REGEX).matcher(email).matches();
        boolean hasSatisfactoryLength = email.length() < 100;

        return isEmail && hasSatisfactoryLength;
    }

    @Override
    public boolean checkName(String name) {
        boolean isName = name.length() >= 2;
        boolean hasSatisfactoryLength = name.length() < 45;

        return isName && hasSatisfactoryLength;
    }

    @Override
    public boolean isAdmin(UserEntity user) {
        boolean isAdmin = true;

        if (user == null) {
            isAdmin = false;

        } else if (!UserType.ADMIN.equals(user.getType())) {
            isAdmin = false;
        }

        return isAdmin;
    }

    @Override
    public boolean isBookmaker(UserEntity user) {
        boolean isBookmaker = true;

        if (user == null) {
            isBookmaker = false;

        } else if (!UserType.BOOKMAKER.equals(user.getType())) {
            isBookmaker = false;
        }

        return isBookmaker;
    }

    @Override
    public boolean isUser(UserEntity user) {
        boolean isUser = true;

        if (user == null) {
            isUser = false;

        } else if (!UserType.USER.equals(user.getType())) {
            isUser = false;
        }

        return isUser;
    }

    public boolean isCashValid(int cash) {
        return (cash > 0 && cash < 1000);
    }

}
