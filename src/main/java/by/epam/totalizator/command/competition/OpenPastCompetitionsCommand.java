package by.epam.totalizator.command.competition;

import by.epam.totalizator.command.AbstractCommand;
import by.epam.totalizator.constant.GeneralConstant;
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

public class OpenPastCompetitionsCommand extends AbstractCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public OpenPastCompetitionsCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();

        try {
            receiver.action(CommandType.takeCommandType(this), content);

            if (!content.getRequestAttributes().containsKey(GeneralConstant.PAGE_NOT_FOUND)) {
                router.setRoutePath(PageType.ALL_PAST_COMPETITIONS.getPage());
                router.setRouteType(RouteType.FORWARD);

            } else {
                router.setRoutePath(PageType.ERROR_404.getPage());
                router.setRouteType(RouteType.REDIRECT);
            }

        } catch (ReceiverException e) {
            LOGGER.log(Level.ERROR, "Open past competitions receiver error", e);
            router.setRoutePath(PageType.ERROR_SERVER.getPage());
            router.setRouteType(RouteType.REDIRECT);
        }
        return router;
    }
}
