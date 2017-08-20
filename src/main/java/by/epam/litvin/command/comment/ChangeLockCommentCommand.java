package by.epam.litvin.command.comment;

import by.epam.litvin.command.AbstractCommand;
import by.epam.litvin.constant.PageConstant;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.Receiver;
import by.epam.litvin.type.CommandType;
import by.epam.litvin.type.RouteType;
import by.epam.litvin.util.Router;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.litvin.constant.GeneralConstant.ACCESS_DENIED;
import static by.epam.litvin.constant.GeneralConstant.WRONG_NEWS;

public class ChangeLockCommentCommand extends AbstractCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public ChangeLockCommentCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public Router execute(RequestContent requestContent) {
        Router router = new Router();

        try {
            receiver.action(CommandType.takeCommandType(this), requestContent);

            if (!requestContent.getRequestAttributes().containsKey(ACCESS_DENIED)) {
                router.setRoutePath(PageConstant.CONCRETE_NEWS_ADD);
                router.setRouteType(RouteType.REDIRECT);

            } else {
                router.setRoutePath(PageConstant.ERROR_404);
                router.setRouteType(RouteType.REDIRECT);
            }

        } catch (ReceiverException e) {
            LOGGER.log(Level.ERROR, "Handle change comment lock receiver error", e);
            router.setRoutePath(PageConstant.ERROR_RUNTIME);
            router.setRouteType(RouteType.REDIRECT);
        }

        return router;

    }
}
