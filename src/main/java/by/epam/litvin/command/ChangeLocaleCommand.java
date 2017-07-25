package by.epam.litvin.command;

import by.epam.litvin.constant.PageConstant;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.Receiver;
import by.epam.litvin.type.CommandType;
import by.epam.litvin.type.RouteType;
import by.epam.litvin.util.Router;

public class ChangeLocaleCommand  extends AbstractCommand {
    public ChangeLocaleCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public Router execute(RequestContent requestContent) {
        Router router = new Router();

        try {
            receiver.action(CommandType.takeCommandType(this), requestContent);
            router.setRouteType(RouteType.REDIRECT);
            router.setRoutePath(PageConstant.MAIN);

        } catch (ReceiverException e) {
            router.setRouteType(RouteType.REDIRECT);
            router.setRoutePath(PageConstant.ERROR_RUNTIME);
        }
        return router;
    }
}
