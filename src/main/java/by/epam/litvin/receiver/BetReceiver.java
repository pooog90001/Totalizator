package by.epam.litvin.receiver;

import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;

public interface BetReceiver extends Receiver {
    void createBet(RequestContent requestContent) throws ReceiverException;
}
