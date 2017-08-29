package by.epam.litvin.receiver;

import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;

public interface NewsReceiver extends Receiver {

    /**
     * Open concrete news page.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void openConcreteNewsPage(RequestContent requestContent) throws ReceiverException;

    /**
     * Open all news.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void openAllNewsPage(RequestContent requestContent) throws ReceiverException;

    /**
     * Create news.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void createNews(RequestContent requestContent) throws ReceiverException;

    /**
     * Delete news.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void deleteNews(RequestContent requestContent) throws ReceiverException;
}
