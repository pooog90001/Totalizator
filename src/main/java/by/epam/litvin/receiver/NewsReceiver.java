package by.epam.litvin.receiver;

import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;

public interface NewsReceiver extends Receiver {

    void openConcreteNewsPage(RequestContent requestContent) throws ReceiverException;
    void openAllNewsPage(RequestContent requestContent) throws ReceiverException;
    void openNewsSettings(RequestContent requestContent) throws ReceiverException;
    void createNews(RequestContent requestContent) throws ReceiverException;
    void deleteNews(RequestContent requestContent) throws ReceiverException;
}
