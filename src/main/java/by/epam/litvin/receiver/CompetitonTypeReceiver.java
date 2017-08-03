package by.epam.litvin.receiver;

import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;

public interface CompetitonTypeReceiver extends Receiver {
    void openCompetitionTypeSetting(RequestContent requestContent) throws ReceiverException;
    void updateCompetitionType(RequestContent requestContent) throws ReceiverException;
    void createCompetitionType(RequestContent requestContent) throws ReceiverException;
    void deleteCompetitionType(RequestContent requestContent) throws ReceiverException;
}
