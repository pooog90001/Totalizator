package by.epam.totalizator.receiver;

import by.epam.totalizator.content.RequestContent;
import by.epam.totalizator.exception.ReceiverException;

public interface CompetitonTypeReceiver extends Receiver {
    /**
     * Open competition type setting.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void openCompetitionTypeSetting(RequestContent requestContent) throws ReceiverException;

    /**
     * Update competition type.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void updateCompetitionType(RequestContent requestContent) throws ReceiverException;

    /**
     * Create competition type.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void createCompetitionType(RequestContent requestContent) throws ReceiverException;

    /**
     * Delete competition type.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void deleteCompetitionType(RequestContent requestContent) throws ReceiverException;
}
