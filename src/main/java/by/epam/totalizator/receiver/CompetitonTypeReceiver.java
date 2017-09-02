package by.epam.totalizator.receiver;

import by.epam.totalizator.content.RequestContent;
import by.epam.totalizator.exception.DAOException;
import by.epam.totalizator.exception.ReceiverException;

public interface CompetitonTypeReceiver extends Receiver {
    /**
     * Open competition type setting.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void openCompetitionTypeSetting(RequestContent requestContent) throws ReceiverException;

    /**
     * Update competition type.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void updateCompetitionType(RequestContent requestContent) throws ReceiverException;

    /**
     * Create competition type.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void createCompetitionType(RequestContent requestContent) throws ReceiverException;

    /**
     * Delete competition type.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void deleteCompetitionType(RequestContent requestContent) throws ReceiverException;
}
