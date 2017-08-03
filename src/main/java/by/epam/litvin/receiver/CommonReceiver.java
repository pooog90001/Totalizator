package by.epam.litvin.receiver;

import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;

public interface CommonReceiver extends Receiver {
    void changeLocale(RequestContent requestContent) throws ReceiverException;
    void openMainPage(RequestContent requestContent) throws ReceiverException;
    void openMainAdminPage(RequestContent requestContent) throws ReceiverException;
}
