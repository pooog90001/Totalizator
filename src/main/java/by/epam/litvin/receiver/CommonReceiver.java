package by.epam.litvin.receiver;

import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;

public interface CommonReceiver extends Receiver{
    void openMainPage(RequestContent requestContent) throws ReceiverException;
    void changeLocale(RequestContent requestContent) throws ReceiverException;
}
