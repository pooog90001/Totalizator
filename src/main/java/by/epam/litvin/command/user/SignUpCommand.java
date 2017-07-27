package by.epam.litvin.command.user;

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

import java.util.Set;

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

            if (keys.contains("wrongEmail") ||
                    keys.contains("wrongName") ||
                    keys.contains("wrongPassword") ||
                    keys.contains("wrongRepeatPassword") ||
                    keys.contains("emailExists")) {
                router.setRouteType(RouteType.FORWARD);
                router.setRoutePath(PageConstant.SIGN_UP);

            } else {
                router.setRouteType(RouteType.REDIRECT);
                router.setRoutePath(PageConstant.CONFIRM);
            }

        } catch (ReceiverException e) {
            LOGGER.log(Level.ERROR, "Handle receiver error", e);
            router.setRouteType(RouteType.REDIRECT);
            router.setRoutePath(PageConstant.ERROR_RUNTIME);
        }

        return router;
    }
}
