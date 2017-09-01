package by.epam.totalizator.type;


import by.epam.totalizator.command.AbstractCommand;
import by.epam.totalizator.command.bet.CreateBetCommand;
import by.epam.totalizator.command.comment.ChangeLockCommentCommand;
import by.epam.totalizator.command.comment.CreateCommentCommand;
import by.epam.totalizator.command.common.*;
import by.epam.totalizator.command.competition.*;
import by.epam.totalizator.command.competitiontype.CreateCompetitionTypeCommand;
import by.epam.totalizator.command.competitiontype.DeleteCompetitionTypeCommand;
import by.epam.totalizator.command.competitiontype.OpenCompetitionTypeCommand;
import by.epam.totalizator.command.competitiontype.UpdateCompetitionTypeCommand;
import by.epam.totalizator.command.kindofsport.CreateKindOfSportCommand;
import by.epam.totalizator.command.kindofsport.DeleteKindOfSportCommand;
import by.epam.totalizator.command.kindofsport.OpenKindOfSportCommand;
import by.epam.totalizator.command.kindofsport.UpdateKindOfSportCommand;
import by.epam.totalizator.command.news.*;
import by.epam.totalizator.command.team.*;
import by.epam.totalizator.command.user.*;
import by.epam.totalizator.content.RequestContent;
import by.epam.totalizator.exception.ReceiverException;
import by.epam.totalizator.receiver.impl.*;

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

    OPEN_PROFILE(new OpenProfileCommand(new UserReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((UserReceiverImpl) getCommand().getReceiver()).openProfile(content);
        }
    },

    WITHDRAW_MONEY(new WithdrawMoneyCommand(new UserReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((UserReceiverImpl) getCommand().getReceiver()).withdrawMoney(content);
        }
    },

    ADD_MONEY(new AddMoneyCommand(new UserReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((UserReceiverImpl) getCommand().getReceiver()).addMoney(content);
        }
    },

    CHANGE_AVATAR(new UpdateAvatarCommand(new UserReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((UserReceiverImpl) getCommand().getReceiver()).changeAvatar(content);
        }
    },

    CHANGE_PASSWORD(new UpdatePasswordCommand(new UserReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((UserReceiverImpl) getCommand().getReceiver()).changePassword(content);
        }
    },

    OPEN_PAGE_NOT_FOUND(new OpenPageNotFoundCommand(new CommonReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CommonReceiverImpl) getCommand().getReceiver()).openNotFoundPage(content);
        }
    },

    CHANGE_LOCALE(new ChangeLocaleCommand(new CommonReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CommonReceiverImpl) getCommand().getReceiver()).changeLocale(content);
        }
    },

    SEND_QUESTION_EMAIL(new SendQuestionEmailCommand(new CommonReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CommonReceiverImpl) getCommand().getReceiver()).sendQuestionEmail(content);
        }
    },

    OPEN_MAIN(new OpenMainCommand(new CommonReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CommonReceiverImpl) getCommand().getReceiver()).openMainPage(content);
        }
    },

    CREATE_BET(new CreateBetCommand(new BetReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((BetReceiverImpl) getCommand().getReceiver()).createBet(content);
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


    OPEN_ALL_UPCOMING_COMPETITIONS(new OpenUpcomingCompetitionsCommand(new CompetitionReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CompetitionReceiverImpl) getCommand().getReceiver()).openUpcomingCompetition(content);
        }
    },

    OPEN_CONCRETE_COMPETITION(new OpenConcreteCompetitionCommand(new CompetitionReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CompetitionReceiverImpl) getCommand().getReceiver()).openConcreteCompetition(content);
        }
    },

    OPEN_ALL_PAST_COMPETITIONS(new OpenPastCompetitionsCommand(new CompetitionReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CompetitionReceiverImpl) getCommand().getReceiver()).openPastCompetition(content);
        }
    },

    OPEN_COMPETITIONS_BY_TYPE(new OpenCompetitionsByTypeCommand(new CompetitionReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CompetitionReceiverImpl) getCommand().getReceiver()).openCompetitionsByType(content);
        }
    },


    OPEN_ADMIN_PANEL(new OpenAdminStatisticCommand(new CommonReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CommonReceiverImpl) getCommand().getReceiver()).openAdminStatistic(content);
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

    OPEN_TEAM_SETTINGS(new OpenTeamsCommand(new TeamReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((TeamReceiverImpl) getCommand().getReceiver()).openTeamSetting(content);
        }
    },

    UPDATE_TEAM(new UpdateTeamCommand(new TeamReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((TeamReceiverImpl) getCommand().getReceiver()).updateTeam(content);
        }
    },

    CREATE_TEAM(new CreateTeamCommand(new TeamReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((TeamReceiverImpl) getCommand().getReceiver()).createTeam(content);
        }
    },

    DELETE_TEAM(new DeleteTeamCommand(new TeamReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((TeamReceiverImpl) getCommand().getReceiver()).deleteTeam(content);
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

    FIND_TEAMS(new FindTeamsCommand(new TeamReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((TeamReceiverImpl) getCommand().getReceiver()).findTeam(content);
        }
    },

    CREATE_COMPETITION(new CreateCompetitionCommand(new CompetitionReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CompetitionReceiverImpl) getCommand().getReceiver()).createCompetition(content);
        }
    },

    EDIT_UPCOMING_ACTIVATED_COMPETITION(new UpdateUpcomingActivatedCommand(new CompetitionReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CompetitionReceiverImpl) getCommand().getReceiver()).updateUpcomingActivated(content);
        }
    },

    EDIT_UPCOMING_DEACTIVATED_COMPETITION(new UpdateUpcomingDeactivatedCommand(new CompetitionReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CompetitionReceiverImpl) getCommand().getReceiver()).updateUpcomingDeactivated(content);
        }
    },

    DELETE_UNFILLED_COMPETITION(new DeleteUnfilledCompetitionCommand(new CompetitionReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CompetitionReceiverImpl) getCommand().getReceiver()).deleteUnfilledCompetition(content);
        }
    },

    DELETE_FILLED_COMPETITION(new DeleteFilledCompetitionCommand(new CompetitionReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CompetitionReceiverImpl) getCommand().getReceiver()).deleteFilledCompetition(content);
        }
    },

    CHANGE_STATE_COMPETITION(new UpdateStateCompetitionCommand(new CompetitionReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CompetitionReceiverImpl) getCommand().getReceiver()).updateStateCompetition(content);
        }
    },

    FILL_RESULTS_COMPETITION(new UpdateResultsCompetitionCommand(new CompetitionReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CompetitionReceiverImpl) getCommand().getReceiver()).updateResultsCompetition(content);
        }
    },

    OPEN_NEWS_SETTINGS(new OpenNewsSettingsCommand(new NewsReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((NewsReceiverImpl) getCommand().getReceiver()).openAllNewsPage(content);
        }
    },

    CREATE_NEWS(new CreateNewsCommand(new NewsReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((NewsReceiverImpl) getCommand().getReceiver()).createNews(content);
        }
    },

    DELETE_NEWS(new DeleteNewsCommand(new NewsReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((NewsReceiverImpl) getCommand().getReceiver()).deleteNews(content);
        }
    },

    OPEN_USER_SETTINGS(new OpenUserSettingsCommand(new UserReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((UserReceiverImpl) getCommand().getReceiver()).openUserSettings(content);
        }
    },

    CHANGE_USER_ROLE(new UpdateUserRoleCommand(new UserReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((UserReceiverImpl) getCommand().getReceiver()).changeRole(content);
        }
    },

    CHANGE_USER_LOCK(new UpdateUserLockCommand(new UserReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((UserReceiverImpl) getCommand().getReceiver()).changeLock(content);
        }
    },

    OPEN_ADMIN_STATISTIC(new OpenAdminStatisticCommand(new CommonReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CommonReceiverImpl) getCommand().getReceiver()).openAdminStatistic(content);
        }
    };



    private AbstractCommand command;

    CommandType(AbstractCommand command) {
        this.command = command;

    }

    public static CommandType takeCommandType(AbstractCommand command) {
        ArrayList<CommandType> result = new ArrayList<>();
        List<CommandType> types = Arrays.asList(CommandType.values());
        types.stream().filter(t -> t.getCommand() == command).forEach(result::add);
        return result.get(0);
    }

    public AbstractCommand getCommand() {
        return command;
    }

    public abstract void doReceiver(RequestContent content) throws ReceiverException;
}
