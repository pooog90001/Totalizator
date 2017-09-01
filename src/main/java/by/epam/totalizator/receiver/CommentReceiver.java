package by.epam.totalizator.receiver;

import by.epam.totalizator.content.RequestContent;
import by.epam.totalizator.exception.ReceiverException;

public interface CommentReceiver extends Receiver {
    /**
     * Change lock comment.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void changeLockComment(RequestContent requestContent) throws ReceiverException;

    /**
     * Create comment.
     *
     * @param requestContent content with request parameters, request and session attributes.
     * @throws ReceiverException when database error.
     */
    void createComment(RequestContent requestContent) throws ReceiverException;

}
