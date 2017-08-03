package by.epam.litvin.command.bet;

import by.epam.litvin.command.AbstractCommand;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.receiver.Receiver;
import by.epam.litvin.util.Router;

public class OpenDoBetCommand extends AbstractCommand {
    public OpenDoBetCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public Router execute(RequestContent requestContent) {
        return null;
    }
}
