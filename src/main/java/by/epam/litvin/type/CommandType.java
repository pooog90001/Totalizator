package by.epam.litvin.type;


import by.epam.litvin.command.*;
import by.epam.litvin.command.command.*;
import by.epam.litvin.command.comment.ChangeLockCommentCommand;
import by.epam.litvin.command.comment.CreateCommentCommand;
import by.epam.litvin.command.common.ChangeLocaleCommand;
import by.epam.litvin.command.common.OpenAdminPanelCommand;
import by.epam.litvin.command.common.OpenMainCommand;
import by.epam.litvin.command.competition.*;
import by.epam.litvin.command.competitiontype.CreateCompetitionTypeCommand;
import by.epam.litvin.command.competitiontype.DeleteCompetitionTypeCommand;
import by.epam.litvin.command.competitiontype.OpenCompetitionTypeCommand;
import by.epam.litvin.command.competitiontype.UpdateCompetitionTypeCommand;
import by.epam.litvin.command.kindofsport.CreateKindOfSportCommand;
import by.epam.litvin.command.kindofsport.DeleteKindOfSportCommand;
import by.epam.litvin.command.kindofsport.OpenKindOfSportCommand;
import by.epam.litvin.command.kindofsport.UpdateKindOfSportCommand;
import by.epam.litvin.command.news.OpenAllNewsCommand;
import by.epam.litvin.command.news.OpenConcreteNewsCommand;
import by.epam.litvin.command.user.SignInCommand;
import by.epam.litvin.command.user.SignOutCommand;
import by.epam.litvin.command.user.SignUpCommand;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.impl.*;

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

    OPEN_MAIN(new OpenMainCommand(new CommonReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CommonReceiverImpl) getCommand().getReceiver()).openMainPage(content);
        }
    },

    OPEN_CONCRETE_NEWS(new OpenConcreteNewsCommand(new NewsReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((NewsReceiverImpl) getCommand().getReceiver()).openConcreteNewsPage(content);
        }
    },

    OPEN_ALL_NEWS(new OpenAllNewsCommand(new NewsReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((NewsReceiverImpl) getCommand().getReceiver()).openAllNewsPage(content);
        }
    },

    CHANGE_LOCK_COMMENT(new ChangeLockCommentCommand(new CommentReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CommentReceiverImpl) getCommand().getReceiver()).changeLockComment(content);
        }
    },

    CREATE_COMMENT(new CreateCommentCommand(new CommentReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CommentReceiverImpl) getCommand().getReceiver()).createComment(content);
        }
    },

    OPEN_ALL_LIVE_COMPETITIONS(new OpenLiveCompetitionCommand(new CompetitionReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CompetitionReceiverImpl) getCommand().getReceiver()).getLiveCompetitions(content);
        }
    },

    OPEN_ALL_UPCOMING_COMPETITIONS(new OpenUpcomingCompetitionCommand(new CompetitionReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CompetitionReceiverImpl) getCommand().getReceiver()).getUpcomingCompetition(content);
        }
    },

    OPEN_ALL_PAST_COMPETITIONS(new OpenPastCompetitionCommand(new CompetitionReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CompetitionReceiverImpl) getCommand().getReceiver()).getPastCompetition(content);
        }
    },

    FILTER_LIVE_COMPETITIONS(new FilterLiveCompetitionCommand(new CompetitionReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CompetitionReceiverImpl) getCommand().getReceiver()).filterLiveCompetitions(content);
        }
    },

    OPEN_ADMIN_PANEL(new OpenAdminPanelCommand(new CommonReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CommonReceiverImpl) getCommand().getReceiver()).openMainAdminPage(content);
        }
    },

    OPEN_KIND_OF_SPORT_SETTINGS(new OpenKindOfSportCommand(new KindOfSportReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((KindOfSportReceiverImpl) getCommand().getReceiver()).openKindOfSportSetting(content);
        }
    },

    UPDATE_KIND_OF_SPORT(new UpdateKindOfSportCommand(new KindOfSportReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((KindOfSportReceiverImpl) getCommand().getReceiver()).updateKindOfSport(content);
        }
    },

    CREATE_KIND_OF_SPORT(new CreateKindOfSportCommand(new KindOfSportReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((KindOfSportReceiverImpl) getCommand().getReceiver()).createKindOfSport(content);
        }
    },

    DELETE_KIND_OF_SPORT(new DeleteKindOfSportCommand(new KindOfSportReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((KindOfSportReceiverImpl) getCommand().getReceiver()).deleteKindOfSport(content);
        }
    },

    OPEN_COMMAND_SETTINGS(new OpenCommandsCommand(new CommandReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CommandReceiverImpl) getCommand().getReceiver()).openCommandSetting(content);
        }
    },

    UPDATE_COMMAND(new UpdateCommandCommand(new CommandReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CommandReceiverImpl) getCommand().getReceiver()).updateCommand(content);
        }
    },

    CREATE_COMMAND(new CreateCommandCommand(new CommandReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CommandReceiverImpl) getCommand().getReceiver()).createCommand(content);
        }
    },

    DELETE_COMMAND(new DeleteCommandCommand(new CommandReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CommandReceiverImpl) getCommand().getReceiver()).deleteCommand(content);
        }
    },

    OPEN_COMPETITION_TYPE_SETTINGS(new OpenCompetitionTypeCommand(new CompetitionTypeReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CompetitionTypeReceiverImpl) getCommand().getReceiver()).openCompetitionTypeSetting(content);
        }
    },

    UPDATE_COMPETITION_TYPE(new UpdateCompetitionTypeCommand(new CompetitionTypeReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CompetitionTypeReceiverImpl) getCommand().getReceiver()).updateCompetitionType(content);
        }
    },

    CREATE_COMPETITION_TYPE(new CreateCompetitionTypeCommand(new CompetitionTypeReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CompetitionTypeReceiverImpl) getCommand().getReceiver()).createCompetitionType(content);
        }
    },

    DELETE_COMPETITION_TYPE(new DeleteCompetitionTypeCommand(new CompetitionTypeReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CompetitionTypeReceiverImpl) getCommand().getReceiver()).deleteCompetitionType(content);
        }
    },

    OPEN_COMPETITION_SETTINGS(new OpenCompetitionCommand(new CompetitionReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CompetitionReceiverImpl) getCommand().getReceiver()).openCompetitionSettings(content);
        }
    },

    FIND_COMMANDS(new FindCommandsCommand(new CommandReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CommandReceiverImpl) getCommand().getReceiver()).findCommand(content);
        }
    },

    CREATE_COMPETITION(new CreateCompetitionCommand(new CompetitionReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CompetitionReceiverImpl) getCommand().getReceiver()).createCompetition(content);
        }
    },

    EDIT_UPCOMING_ACTIVE_COMPETITION(new UpdateUpcomingActiveCommand(new CompetitionReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CompetitionReceiverImpl) getCommand().getReceiver()).editUpcomingActive(content);
        }
    },

    DELETE_UPCOMING_COMPETITION(new DeleteUpcomingCompetitionCommand(new CompetitionReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CompetitionReceiverImpl) getCommand().getReceiver()).deleteUpcomingCompetition(content);
        }
    },

    DEACTIVATE_COMPETITION(new DeactivateCompetitionCommand(new CompetitionReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CompetitionReceiverImpl) getCommand().getReceiver()).deactivateCompetition(content);
        }
    },

    ACTIVATE_COMPETITION(new ActivateCompetitionCommand(new CompetitionReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CompetitionReceiverImpl) getCommand().getReceiver()).activateCompetition(content);
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
