package by.epam.litvin.receiver;

import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;

public interface CommandReceiver extends Receiver {
    void openCommandSetting(RequestContent requestContent) throws ReceiverException;
    void updateCommand(RequestContent requestContent) throws ReceiverException;
    void createCommand(RequestContent requestContent) throws ReceiverException;
    void deleteCommand(RequestContent requestContent) throws ReceiverException;
    void findCommand(RequestContent requestContent) throws ReceiverException;
}
