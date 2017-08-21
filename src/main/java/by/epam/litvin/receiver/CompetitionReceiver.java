package by.epam.litvin.receiver;

import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;
import sun.misc.Request;

public interface CompetitionReceiver extends Receiver {
    void getUpcomingGamesPage(RequestContent requestContent) throws ReceiverException;
    void getPastGamesPage(RequestContent requestContent) throws ReceiverException;
    void openCompetitionSettings(RequestContent requestContent) throws ReceiverException;
    void createCompetition(RequestContent requestContent) throws ReceiverException;
    void editUpcomingActivated(RequestContent requestContent) throws ReceiverException;
    void editUpcomingDeactivated(RequestContent requestContent) throws ReceiverException;
    void deleteUnfilledCompetition(RequestContent requestContent) throws ReceiverException;
    void deleteFilledCompetition(RequestContent requestContent) throws ReceiverException;
    void updateStateCompetition(RequestContent requestContent) throws ReceiverException;
    void updateResultsCompetition(RequestContent requestContent) throws ReceiverException;
}
