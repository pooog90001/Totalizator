package by.epam.litvin.receiver;

import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;

public interface BetReceiver extends Receiver {
    /**
     * Create bet.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void createBet(RequestContent requestContent) throws ReceiverException;
}
