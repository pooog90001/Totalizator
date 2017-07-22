package by.epam.litvin.receiver;

import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;

public interface CommonReceiver {
    void openMainPage(RequestContent requestContent) throws ReceiverException;

}
