package by.epam.totalizator.factory;


import by.epam.totalizator.command.AbstractCommand;
import by.epam.totalizator.content.RequestContent;
import by.epam.totalizator.type.CommandType;
import by.epam.totalizator.validator.CommonValidator;
import by.epam.totalizator.validator.impl.CommonValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.totalizator.constant.GeneralConstant.COMMAND;

public class FactoryCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * @param requestContent requset content
     * @return
     */
    public AbstractCommand initCommand(RequestContent requestContent) {
        CommonValidator commonValidator = new CommonValidatorImpl();
        AbstractCommand command;
        try {
            String[] commandName = requestContent.getRequestParameters().get(COMMAND);
            if (commonValidator.isVarExist(commandName)) {
                CommandType cmdEnum = CommandType.valueOf(commandName[0].toUpperCase());
                command = cmdEnum.getCommand();

            } else {
                LOGGER.log(Level.WARN, "Variable command not found. ");
                command = CommandType.OPEN_PAGE_NOT_FOUND.getCommand();
            }
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.ERROR, "Command not exist", e);
            command = CommandType.OPEN_PAGE_NOT_FOUND.getCommand();
        }
        return command;
    }
}
