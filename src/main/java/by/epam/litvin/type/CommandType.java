package by.epam.litvin.type;


import by.epam.litvin.command.AbstractCommand;
import by.epam.litvin.command.SignInCommand;
import by.epam.litvin.command.SignOutCommand;
import by.epam.litvin.command.SignUpCommand;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.impl.UserReceiverImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum CommandType {

    SIGN_IN(new SignInCommand(new UserReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((UserReceiverImpl) getCommand().getReceiver()).signIn(content);
            // добавление результатов метода signIn в content
        }
    },
    SIGN_UP(new SignUpCommand(new UserReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((UserReceiverImpl) getCommand().getReceiver()).signUp(content);
            // добавление результатов метода signUp в content
        }
    },
    SIGN_OUT(new SignOutCommand(new UserReceiverImpl())) {
        public void doReceiver(RequestContent content) {
            ((UserReceiverImpl) getCommand().getReceiver()).signOut(content);
            // добавление результатов метода signOut в content
        }
    };

    private AbstractCommand command;

    CommandType(AbstractCommand command) {
        this.command = command;

    }
    public AbstractCommand getCommand() {
        return command;
    }

    public abstract void doReceiver(RequestContent content) throws ReceiverException;

    public static CommandType takeCommandType(AbstractCommand command) {
        ArrayList<CommandType> result = new ArrayList<>();
        List<CommandType> types = Arrays.asList(CommandType.values());
        types.stream().filter(t -> t.getCommand().equals(command)).forEach(result::add);
        return result.get(0);
    }
}
