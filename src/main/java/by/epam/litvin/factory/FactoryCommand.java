package by.epam.litvin.factory;


import by.epam.litvin.command.AbstractCommand;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.type.CommandType;

public class FactoryCommand { //FactoryMethod
    public AbstractCommand initCommand(RequestContent requestContent) {
        AbstractCommand command = null;
        try {
            String commandName = requestContent.getRequestParameters().get("command")[0];
            CommandType cmdEnum = CommandType.valueOf(commandName.toUpperCase());
            command = cmdEnum.getCommand();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return command;
    }
}
