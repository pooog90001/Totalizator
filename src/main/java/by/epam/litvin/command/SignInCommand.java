package by.epam.litvin.command;

import by.epam.litvin.constant.Page;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.Receiver;
import by.epam.litvin.type.CommandType;
import by.epam.litvin.type.RouteType;
import by.epam.litvin.util.Router;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

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

            if (keys.contains("wrongEmail") ||
                    keys.contains("wrongPassword") ||
                    keys.contains("wrongData")) {
                router.setRouteType(RouteType.FORWARD);
                router.setRoutePath(Page.SIGN_IN);

            } else {
                router.setRouteType(RouteType.REDIRECT);
                router.setRoutePath(Page.MAIN);
            }

        } catch (ReceiverException e) {
            LOGGER.log(Level.ERROR, "Handle receiver error", e);
            router.setRouteType(RouteType.REDIRECT);
            router.setRoutePath(Page.ERROR_500);
        }

        return router;
    }
}
