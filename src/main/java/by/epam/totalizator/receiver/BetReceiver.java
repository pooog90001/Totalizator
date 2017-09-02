package by.epam.totalizator.receiver;

import by.epam.totalizator.content.RequestContent;
import by.epam.totalizator.exception.DAOException;
import by.epam.totalizator.exception.ReceiverException;

public interface BetReceiver extends Receiver {
    /**
     * Create bet.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void createBet(RequestContent requestContent) throws ReceiverException;
}
