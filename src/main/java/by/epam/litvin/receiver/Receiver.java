package by.epam.litvin.receiver;


import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.type.CommandType;
import by.epam.litvin.content.RequestContent;

public interface Receiver {
    /**
     * Action for receiver.
     *
     * @param type
     * @param content
     * @throws ReceiverException
     */
    default void action(CommandType type, RequestContent content) throws ReceiverException {
        type.doReceiver(content);
    }

}
