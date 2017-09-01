package by.epam.totalizator.command.common;

import by.epam.totalizator.command.AbstractCommand;
import by.epam.totalizator.content.RequestContent;
import by.epam.totalizator.exception.ReceiverException;
import by.epam.totalizator.receiver.Receiver;
import by.epam.totalizator.type.CommandType;
import by.epam.totalizator.type.PageType;
import by.epam.totalizator.type.RouteType;
import by.epam.totalizator.util.Router;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OpenPageNotFoundCommand extends AbstractCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public OpenPageNotFoundCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public Router execute(RequestContent requestContent) {
        Router router = new Router();

        try {
            receiver.action(CommandType.takeCommandType(this), requestContent);
            router.setRouteType(RouteType.REDIRECT);
            router.setRoutePath(PageType.ERROR_404.getPage());

        } catch (ReceiverException e) {
            LOGGER.log(Level.ERROR, "Page not found receiver error", e);
        }
        return router;
    }
}
