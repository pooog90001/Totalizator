package by.epam.litvin.command.user;

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

import java.util.Set;

import static by.epam.litvin.constant.GeneralConstant.IS_BLOCKED;
import static by.epam.litvin.constant.RequestNameConstant.WRONG_DATA;

public class SignInCommand extends AbstractCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public SignInCommand(Receiver receiver) {
        super(receiver);
    }

    public Router execute(RequestContent requestContent) {
        Router router = new Router();

        try {
            receiver.action(CommandType.takeCommandType(this), requestContent);

            Set<String> keys = requestContent.getRequestAttributes().keySet();

            if (keys.contains(WRONG_DATA)) {
                router.setRouteType(RouteType.FORWARD);
                router.setRoutePath(PageType.SIGN_IN.getPage());

            } else if (keys.contains(IS_BLOCKED)) {
                router.setRouteType(RouteType.REDIRECT);
                router.setRoutePath(PageType.BLOCK.getPage());

            } else {
                router.setRouteType(RouteType.REDIRECT);
                router.setRoutePath(PageType.INDEX.getPage());
            }

        } catch (ReceiverException e) {
            LOGGER.log(Level.ERROR, "Handle receiver error", e);
            router.setRouteType(RouteType.REDIRECT);
            router.setRoutePath(PageType.ERROR_SERVER.getPage());
        }

        return router;
    }
}
