package by.epam.totalizator.receiver;

import by.epam.totalizator.content.RequestContent;
import by.epam.totalizator.exception.DAOException;
import by.epam.totalizator.exception.ReceiverException;

public interface CompetitionReceiver extends Receiver {
    /**
     * Create competition.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void createCompetition(RequestContent requestContent) throws ReceiverException;

    /**
     * Open upcoming competition.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void openUpcomingCompetition(RequestContent requestContent) throws ReceiverException;

    /**
     * Open past competition.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void openPastCompetition(RequestContent requestContent) throws ReceiverException;

    /**
     * Open competitions by type.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void openCompetitionsByType(RequestContent requestContent) throws ReceiverException;

    /**
     * Open concrete competition.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void openConcreteCompetition(RequestContent requestContent) throws ReceiverException;

    /**
     * Open competition settings.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void openCompetitionSettings(RequestContent requestContent) throws ReceiverException;

    /**
     * Update upcoming activated.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void updateUpcomingActivated(RequestContent requestContent) throws ReceiverException;

    /**
     * Update upcoming deactivated.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void updateUpcomingDeactivated(RequestContent requestContent) throws ReceiverException;

    /**
     * Update state competition.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void updateStateCompetition(RequestContent requestContent) throws ReceiverException;

    /**
     * Update result competition.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void updateResultsCompetition(RequestContent requestContent) throws ReceiverException;

    /**
     * Delete unfilled competition.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void deleteUnfilledCompetition(RequestContent requestContent) throws ReceiverException;

    /**
     * Delete filled competition.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void deleteFilledCompetition(RequestContent requestContent) throws ReceiverException;
}
