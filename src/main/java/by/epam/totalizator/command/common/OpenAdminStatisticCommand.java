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

import static by.epam.totalizator.constant.GeneralConstant.ACCESS_DENIED;

public class OpenAdminStatisticCommand extends AbstractCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public OpenAdminStatisticCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public Router execute(RequestContent requestContent){
        Router router = new Router();

        try {
            receiver.action(CommandType.takeCommandType(this), requestContent);

            if (requestContent.getRequestAttributes().containsKey(ACCESS_DENIED)) {
                router.setRoutePath(PageType.ERROR_404.getPage());
                router.setRouteType(RouteType.REDIRECT);

            } else {
                router.setRoutePath(PageType.ADMIN_MAIN.getPage());
                router.setRouteType(RouteType.FORWARD);
            }

        } catch (ReceiverException e) {
            LOGGER.log(Level.ERROR, "Open admin statistic receiver error", e);
            router.setRoutePath(PageType.ERROR_SERVER.getPage());
            router.setRouteType(RouteType.REDIRECT);
        }

        return router;
    }
}
