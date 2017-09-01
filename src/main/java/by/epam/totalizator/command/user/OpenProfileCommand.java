package by.epam.totalizator.command.user;

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

import static by.epam.totalizator.constant.GeneralConstant.ACCESS_DENIED;
import static by.epam.totalizator.constant.GeneralConstant.IS_BLOCKED;

public class OpenProfileCommand extends AbstractCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public OpenProfileCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();

        try {
            receiver.action(CommandType.takeCommandType(this), content);

            if (content.getRequestAttributes().containsKey(IS_BLOCKED)) {
                router.setRouteType(RouteType.REDIRECT);
                router.setRoutePath(PageType.BLOCK.getPage());

            } else if (content.getRequestAttributes().containsKey(ACCESS_DENIED)) {
                router.setRouteType(RouteType.REDIRECT);
                router.setRoutePath(PageType.ERROR_404.getPage());

            } else {
                router.setRouteType(RouteType.FORWARD);
                router.setRoutePath(PageType.PROFILE.getPage());
            }


        } catch (ReceiverException e) {
            LOGGER.log(Level.ERROR, "Open user profile receiver error", e);
            router.setRouteType(RouteType.REDIRECT);
            router.setRoutePath(PageType.ERROR_SERVER.getPage());
        }

        return router;
    }
}
