package by.epam.totalizator.receiver;

import by.epam.totalizator.content.RequestContent;
import by.epam.totalizator.exception.DAOException;
import by.epam.totalizator.exception.ReceiverException;

public interface UserReceiver extends Receiver {
    /**
     * Sing up.
     *
     * @param content request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void signUp(RequestContent content) throws ReceiverException;

    /**
     * Sign in.
     *
     * @param content request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void signIn(RequestContent content) throws ReceiverException;

    /**
     * Sign out.
     *
     * @param content request content
     *
     * @see DAOException
     * @see RequestContent
     */
    void signOut(RequestContent content);

    /**
     * Open user setting.
     *
     * @param content request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void openUserSettings(RequestContent content) throws ReceiverException;

    /**
     * Open profile.
     *
     * @param content request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void openProfile(RequestContent content) throws ReceiverException;

    /**
     * Change role.
     *
     * @param content request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void changeRole(RequestContent content) throws ReceiverException;

    /**
     * Change lock.
     *
     * @param content request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void changeLock(RequestContent content) throws ReceiverException;

    /**
     * Change avatar.
     *
     * @param content request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void changeAvatar(RequestContent content) throws ReceiverException;

    /**
     * Change password.
     *
     * @param content request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void changePassword(RequestContent content) throws ReceiverException;

    /**
     * Add money.
     *
     * @param content request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void addMoney(RequestContent content) throws ReceiverException;

    /**
     * Withdraw money.
     *
     * @param content request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void withdrawMoney(RequestContent content) throws ReceiverException;
}
