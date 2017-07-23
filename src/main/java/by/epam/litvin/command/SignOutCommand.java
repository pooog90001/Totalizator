package by.epam.litvin.command;

import by.epam.litvin.constant.PageConstant;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.type.CommandType;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.receiver.Receiver;
import by.epam.litvin.type.RouteType;
import by.epam.litvin.util.Router;

public class SignOutCommand extends AbstractCommand {

    public SignOutCommand(Receiver receiver) {
        super(receiver);
    }

    public Router execute(RequestContent requestContent) {
        Router router = new Router();

        try {
            receiver.action(CommandType.takeCommandType(this), requestContent);

            router.setRoutePath(PageConstant.MAIN);
            router.setRouteType(RouteType.REDIRECT);

        } catch (ReceiverException e) {
            router.setRoutePath(PageConstant.ERROR_500);
            router.setRouteType(RouteType.REDIRECT);
        }
        return router;
    }
}
