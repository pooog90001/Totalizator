package by.epam.litvin.receiver;

import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;

public interface UserReceiver extends Receiver {
    /**
     * Sing up.
     *
     * @param content
     * @throws ReceiverException
     */
    void signUp(RequestContent content) throws ReceiverException;

    /**
     * Sign in.
     *
     * @param content
     * @throws ReceiverException
     */
    void signIn(RequestContent content) throws ReceiverException;

    /**
     * Sign out.
     *
     * @param content
     */
    void signOut(RequestContent content);

    /**
     * Open user setting.
     *
     * @param content
     * @throws ReceiverException
     */
    void openUserSettings(RequestContent content) throws ReceiverException;

    /**
     * Open profile.
     *
     * @param content
     * @throws ReceiverException
     */
    void openProfile(RequestContent content) throws ReceiverException;

    /**
     * Change role.
     *
     * @param content
     * @throws ReceiverException
     */
    void changeRole(RequestContent content) throws ReceiverException;

    /**
     * Change lock.
     *
     * @param content
     * @throws ReceiverException
     */
    void changeLock(RequestContent content) throws ReceiverException;

    /**
     * Change avatar.
     *
     * @param content
     * @throws ReceiverException
     */
    void changeAvatar(RequestContent content) throws ReceiverException;

    /**
     * Change password.
     *
     * @param content
     * @throws ReceiverException
     */
    void changePassword(RequestContent content) throws ReceiverException;

    /**
     * Add money.
     *
     * @param content
     * @throws ReceiverException
     */
    void addMoney(RequestContent content) throws ReceiverException;

    /**
     * Withdraw money.
     *
     * @param content
     * @throws ReceiverException
     */
    void withdrawMoney(RequestContent content) throws ReceiverException;
}
