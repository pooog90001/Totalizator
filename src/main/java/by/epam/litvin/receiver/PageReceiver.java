package by.epam.litvin.receiver;

import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;

public interface PageReceiver extends Receiver {

    void openMainPage(RequestContent requestContent) throws ReceiverException;
    void openAllNewsPage(RequestContent requestContent) throws ReceiverException;
    void openConcreteNewsPage(RequestContent requestContent) throws ReceiverException;

}
