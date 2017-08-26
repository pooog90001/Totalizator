package by.epam.litvin.factory;


import by.epam.litvin.command.AbstractCommand;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.type.CommandType;
import by.epam.litvin.validator.CommonValidator;
import by.epam.litvin.validator.impl.CommonValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import static by.epam.litvin.constant.GeneralConstant.COMMAND;

public class FactoryCommand {
    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger();

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
