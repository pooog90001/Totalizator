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

import java.util.Set;

import static by.epam.totalizator.constant.RequestNameConstant.*;

public class SignUpCommand extends AbstractCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public SignUpCommand(Receiver receiver) {
        super(receiver);
    }

    public Router execute(RequestContent requestContent) {
        Router router = new Router();

        try {
            receiver.action(CommandType.takeCommandType(this), requestContent);
            Set<String> keys = requestContent.getRequestAttributes().keySet();

            if (keys.contains(WRONG_EMAIL) || keys.contains(WRONG_NAME) ||
                    keys.contains(WRONG_PASSWORD) || keys.contains(WRONG_REPEAT_PASSWORD) ||
                    keys.contains(EMAIL_EXISTS)) {
                router.setRouteType(RouteType.FORWARD);
                router.setRoutePath(PageType.SIGN_UP.getPage());

            } else {
                router.setRouteType(RouteType.REDIRECT);
                router.setRoutePath(PageType.INDEX.getPage());
            }

        } catch (ReceiverException e) {
            LOGGER.log(Level.ERROR, "Sign up receiver error", e);
            router.setRouteType(RouteType.REDIRECT);
            router.setRoutePath(PageType.ERROR_SERVER.getPage());
        }
        return router;
    }
}
