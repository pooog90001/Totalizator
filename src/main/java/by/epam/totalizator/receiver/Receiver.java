package by.epam.totalizator.receiver;


import by.epam.totalizator.content.RequestContent;
import by.epam.totalizator.exception.ReceiverException;
import by.epam.totalizator.type.CommandType;

public interface Receiver {
    /**
     * Action for receiver.
     *
     * @param type command type
     * @param content request content
     * @throws ReceiverException when some error
     */
    default void action(CommandType type, RequestContent content) throws ReceiverException {
        type.doReceiver(content);
    }

}
