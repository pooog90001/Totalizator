package by.epam.litvin.receiver;

import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;

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
     * @param requestContent
     * @throws ReceiverException
     */
    void createComment(RequestContent requestContent) throws ReceiverException;

}
