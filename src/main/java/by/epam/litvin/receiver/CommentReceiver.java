package by.epam.litvin.receiver;

import by.epam.litvin.content.RequestContent;

public interface CommentReceiver extends Receiver {
    void blockComment(RequestContent requestContent);
    void postComment(RequestContent requestContent);

}
