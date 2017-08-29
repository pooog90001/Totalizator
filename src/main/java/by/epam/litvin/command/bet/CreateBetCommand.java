package by.epam.litvin.command.bet;

import by.epam.litvin.command.AbstractCommand;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.Receiver;
import by.epam.litvin.type.CommandType;
import by.epam.litvin.util.Router;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateBetCommand extends AbstractCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public CreateBetCommand(Receiver receiver) {
        super(receiver);
    }



    @Override
    public Router execute(RequestContent requestContent) {
        try {
            receiver.action(CommandType.takeCommandType(this), requestContent);

        } catch (ReceiverException e) {
            LOGGER.log(Level.ERROR, "Handle create bet receiver error", e);
        }

        return null; //TODO Возвращать нуль нормально?
    }
}
