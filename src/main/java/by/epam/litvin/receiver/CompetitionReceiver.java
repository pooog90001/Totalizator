package by.epam.litvin.receiver;

import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;
import sun.misc.Request;

public interface CompetitionReceiver extends Receiver {
    void getLiveCompetitions(RequestContent requestContent) throws ReceiverException;
    void getUpcomingCompetition(RequestContent requestContent);
    void getPastCompetition(RequestContent requestContent);
    void filterLiveCompetitions(RequestContent requestContent) throws ReceiverException;
    void openCompetitionSettings(RequestContent requestContent) throws ReceiverException;
    void createCompetition(RequestContent requestContent) throws ReceiverException;
    void editUpcomingActivated(RequestContent requestContent) throws ReceiverException;
    void editUpcomingDeactivated(RequestContent requestContent) throws ReceiverException;
    void deleteUpcomingCompetition(RequestContent requestContent) throws ReceiverException;
    void changeStateCompetition(RequestContent requestContent) throws ReceiverException;
}
