package by.epam.totalizator.receiver;

import by.epam.totalizator.content.RequestContent;
import by.epam.totalizator.exception.ReceiverException;

public interface CommonReceiver extends Receiver {
    /**
     * Change locale.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void changeLocale(RequestContent requestContent) throws ReceiverException;

    /**
     * Open main page.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void openMainPage(RequestContent requestContent) throws ReceiverException;

    /**
     * Open admin statistic.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void openAdminStatistic(RequestContent requestContent) throws ReceiverException;

    /**
     * Open not found page.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void openNotFoundPage(RequestContent requestContent) throws ReceiverException;

    /**
     * Send question email.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void sendQuestionEmail(RequestContent requestContent) throws ReceiverException;

    /**
     * Send confirm email.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void sendConfirmEmail(RequestContent requestContent) throws ReceiverException;
}
