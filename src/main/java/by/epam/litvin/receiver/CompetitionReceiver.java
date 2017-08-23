package by.epam.litvin.receiver;

import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;
import sun.misc.Request;

public interface CompetitionReceiver extends Receiver {
    void createCompetition(RequestContent requestContent) throws ReceiverException;
    void openUpcomingCompetition(RequestContent requestContent) throws ReceiverException;
    void openPastCompetition(RequestContent requestContent) throws ReceiverException;
    void openCompetitionsByType(RequestContent requestContent) throws ReceiverException;
    void openConcreteCompetition(RequestContent requestContent) throws ReceiverException;
    void openCompetitionSettings(RequestContent requestContent) throws ReceiverException;
    void updateUpcomingActivated(RequestContent requestContent) throws ReceiverException;
    void updateUpcomingDeactivated(RequestContent requestContent) throws ReceiverException;
    void updateStateCompetition(RequestContent requestContent) throws ReceiverException;
    void updateResultsCompetition(RequestContent requestContent) throws ReceiverException;
    void deleteUnfilledCompetition(RequestContent requestContent) throws ReceiverException;
    void deleteFilledCompetition(RequestContent requestContent) throws ReceiverException;
}
