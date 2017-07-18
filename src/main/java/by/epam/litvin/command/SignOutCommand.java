package by.epam.litvin.command;

import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.type.CommandType;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.receiver.Receiver;
import by.epam.litvin.util.Router;

public class SignOutCommand extends AbstractCommand {

    public SignOutCommand(Receiver receiver) {
        super(receiver);
    }

    public Router execute(RequestContent requestContent) {
        Router router = new Router();

        try {
            receiver.action(CommandType.takeCommandType(this), requestContent);

        } catch (ReceiverException e) {
            //sadd
        }
    }
}
