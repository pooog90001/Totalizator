package by.epam.litvin.command.kindofsport;

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

public class CreateKindOfSportCommand extends AbstractCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public CreateKindOfSportCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public Router execute(RequestContent requestContent){
        Router router = new Router();

        try {
            receiver.action(CommandType.takeCommandType(this), requestContent);

            router.setRoutePath(PageType.ADMIN_KIND_OF_SPORT_ADD.getPage());
            router.setRouteType(RouteType.REDIRECT);

        } catch (ReceiverException e) {
            LOGGER.log(Level.ERROR, "Create kind of sport receiver error", e);
            router.setRoutePath(PageType.ERROR_SERVER.getPage());
            router.setRouteType(RouteType.REDIRECT);
        }
        return router;
    }
}
