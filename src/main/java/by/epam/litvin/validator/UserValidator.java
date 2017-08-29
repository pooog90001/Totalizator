package by.epam.litvin.validator;

import by.epam.litvin.bean.UserEntity;

public interface UserValidator extends Validator {
    /**
     * Check password.
     *
     * @param password
     * @return
     */
    boolean checkPassword(String password);

    /**
     * Check email.
     *
     * @param email
     * @return
     */
    boolean checkEmail(String email);

    /**
     * Check name.
     *
     * @param name
     * @return
     */
    boolean checkName(String name);

    /**
     * Is admin.
     *
     * @param user
     * @return
     */
    boolean isAdmin(UserEntity user);

    /**
     * Is bookmaker.
     *
     * @param user
     * @return
     */
    boolean isBookmaker(UserEntity user);

    /**
     * Is user.
     *
     * @param user
     * @return
     */
    boolean isUser(UserEntity user);
}
