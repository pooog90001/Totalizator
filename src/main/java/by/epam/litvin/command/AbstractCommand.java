package by.epam.litvin.command;

import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.Receiver;
import by.epam.litvin.type.CommandType;
import by.epam.litvin.util.Router;

public abstract class AbstractCommand {
    protected Receiver receiver;

    public AbstractCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    public abstract Router execute(RequestContent requestContent);

    public Receiver getReceiver() {
        return receiver;
    }

}
