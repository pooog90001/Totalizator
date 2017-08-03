package by.epam.litvin.receiver;

import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;

public interface CommentReceiver extends Receiver {
    void changeLockComment(RequestContent requestContent) throws ReceiverException;
    void createComment(RequestContent requestContent) throws ReceiverException;

}
