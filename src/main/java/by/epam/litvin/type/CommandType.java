package by.epam.litvin.type;


import by.epam.litvin.command.*;
import by.epam.litvin.command.common.ChangeLocaleCommand;
import by.epam.litvin.command.page.OpenAllNewsPageCommand;
import by.epam.litvin.command.page.OpenConcreteNewsCommand;
import by.epam.litvin.command.page.OpenMainPageCommand;
import by.epam.litvin.command.user.SignInCommand;
import by.epam.litvin.command.user.SignOutCommand;
import by.epam.litvin.command.user.SignUpCommand;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.impl.CommonReceiverImpl;
import by.epam.litvin.receiver.impl.PageReceiverImpl;
import by.epam.litvin.receiver.impl.UserReceiverImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum CommandType {

    SIGN_IN(new SignInCommand(new UserReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((UserReceiverImpl) getCommand().getReceiver()).signIn(content);
        }
    },

    SIGN_UP(new SignUpCommand(new UserReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((UserReceiverImpl) getCommand().getReceiver()).signUp(content);
        }
    },

    SIGN_OUT(new SignOutCommand(new UserReceiverImpl())) {
        public void doReceiver(RequestContent content) {
            ((UserReceiverImpl) getCommand().getReceiver()).signOut(content);
        }
    },

    CHANGE_LOCALE(new ChangeLocaleCommand(new CommonReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CommonReceiverImpl) getCommand().getReceiver()).changeLocale(content);
        }
    },

    OPEN_MAIN_PAGE(new OpenMainPageCommand(new PageReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((PageReceiverImpl) getCommand().getReceiver()).openMainPage(content);
        }
    },

    OPEN_CONCRETE_NEWS_PAGE(new OpenConcreteNewsCommand(new PageReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((PageReceiverImpl) getCommand().getReceiver()).openConcreteNewsPage(content);
        }
    },

    OPEN_ALL_NEWS_PAGE(new OpenAllNewsPageCommand(new PageReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((PageReceiverImpl) getCommand().getReceiver()).openAllNewsPage(content);
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
        types.stream().filter(t -> t.getCommand() == command).forEach(result::add);
        return result.get(0);
    }
}
