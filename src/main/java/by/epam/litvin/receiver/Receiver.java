package by.epam.litvin.receiver;


import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.type.CommandType;
import by.epam.litvin.content.RequestContent;

public interface Receiver {
    default void action(CommandType type, RequestContent content) throws ReceiverException {
        type.doReceiver(content);
    }

}
