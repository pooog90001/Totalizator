package by.epam.totalizator.command.competitiontype;

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

public class CreateCompetitionTypeCommand extends AbstractCommand{
    private static final Logger LOGGER = LogManager.getLogger();

    public CreateCompetitionTypeCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public Router execute(RequestContent requestContent){
        Router router = new Router();

        try {
            receiver.action(CommandType.takeCommandType(this), requestContent);

            router.setRoutePath(PageType.ADMIN_COMPETITION_TYPE_ADD.getPage());
            router.setRouteType(RouteType.REDIRECT);

        } catch (ReceiverException e) {
            LOGGER.log(Level.ERROR, "Create competition type receiver error", e);
            router.setRoutePath(PageType.ERROR_SERVER.getPage());
            router.setRouteType(RouteType.REDIRECT);
        }
        return router;
    }
}
