package by.epam.litvin.receiver;

import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;

public interface UserReceiver extends Receiver {
    void signUp(RequestContent requestContent) throws ReceiverException;
    void signIn(RequestContent requestContent) throws ReceiverException;
    void signOut(RequestContent requestContent);
}
