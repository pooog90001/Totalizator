package by.epam.litvin.validator;

import java.util.regex.Pattern;

public class UserValidator {
    private final static String EMAIL_REGEX = "([_\\w-]+(\\.[_\\w-]+)*@[\\w-]+(\\.[\\w-]+)*(\\.[a-zA-Z]{1,6}))";

    public boolean checkPassword(String password) {
        boolean hasBigWord = Pattern.compile("[A-ZА-Я]").matcher(password).find();
        boolean hasWord = Pattern.compile("[a-zа-я]").matcher(password).find();
        boolean hasNumber = Pattern.compile("\\d").matcher(password).find();
        boolean hasSatisfactoryLength = (password.length() >= 6 && password.length() < 80);

        return (hasBigWord && hasWord && hasNumber && hasSatisfactoryLength);
    }

    public boolean checkEmail(String email) {
        boolean isEmail = Pattern.compile(EMAIL_REGEX).matcher(email).matches();
        boolean hasSatisfactoryLength = email.length() < 100;

        return isEmail && hasSatisfactoryLength;
    }

    public boolean checkName(String name) {
        boolean isName = Pattern.compile("\\w{2,}").matcher(name).matches();
        boolean hasSatisfactoryLength = name.length() < 45;

        return isName && hasSatisfactoryLength;
    }

}
