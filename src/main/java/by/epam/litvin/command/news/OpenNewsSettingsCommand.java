package by.epam.litvin.command.news;

import by.epam.litvin.command.AbstractCommand;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.Receiver;
import by.epam.litvin.type.CommandType;
import by.epam.litvin.type.PageType;
import by.epam.litvin.type.RouteType;
import by.epam.litvin.util.Router;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OpenNewsSettingsCommand extends AbstractCommand {
    private static final Logger LOGGER = LogManager.getLogger();


    public OpenNewsSettingsCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public Router execute(RequestContent requestContent) {
        Router router = new Router();

        try {
            receiver.action(CommandType.takeCommandType(this), requestContent);

            router.setRoutePath(PageType.ADMIN_NEWS.getPath());
            router.setRouteType(RouteType.FORWARD);


        } catch (ReceiverException e) {
            LOGGER.log(Level.ERROR, "Handle receiver error", e);
            router.setRoutePath(PageType.ERROR_SERVER.getPath());
            router.setRouteType(RouteType.REDIRECT);
        }

        return router;
    }
}
