package by.epam.totalizator.receiver;

import by.epam.totalizator.content.RequestContent;
import by.epam.totalizator.exception.ReceiverException;

public interface BetReceiver extends Receiver {
    /**
     * Create bet.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void createBet(RequestContent requestContent) throws ReceiverException;
}
