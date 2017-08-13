package by.epam.litvin.validator;

import by.epam.litvin.bean.UserEntity;

public interface UserValidator extends Validator {
    boolean checkPassword(String password);
    boolean checkEmail(String email);
    boolean checkName(String name);
    boolean isAdmin(UserEntity user);
    boolean isBookmaker(UserEntity user);
    boolean isUser(UserEntity user);
}
