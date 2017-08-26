package by.epam.litvin.command.competition;

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

import static by.epam.litvin.constant.GeneralConstant.PAGE_NOT_FOUND;

public class OpenUpcomingCompetitionsCommand extends AbstractCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public OpenUpcomingCompetitionsCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();

        try {
            receiver.action(CommandType.takeCommandType(this), content);

            if (!content.getRequestAttributes().containsKey(PAGE_NOT_FOUND)) {
                router.setRoutePath(PageType.ALL_UPCOMING_COMPETITIONS.getPage());
                router.setRouteType(RouteType.FORWARD);

            } else {
                router.setRoutePath(PageType.ERROR_404.getPage());
                router.setRouteType(RouteType.REDIRECT);
            }

        } catch (ReceiverException e) {
            LOGGER.log(Level.ERROR, "Open upcoming competitions receiver error", e);
            router.setRoutePath(PageType.ERROR_SERVER.getPage());
            router.setRouteType(RouteType.REDIRECT);
        }

        return router;
    }
}
