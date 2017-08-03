package by.epam.litvin.receiver;

import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;

public interface CompetitionReceiver extends Receiver {
    void getLiveCompetitions(RequestContent requestContent) throws ReceiverException;
    void getUpcomingCompetition(RequestContent requestContent);
    void getPastCompetition(RequestContent requestContent);
    void filterLiveCompetitions(RequestContent requestContent) throws ReceiverException;
}
