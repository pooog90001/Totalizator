package by.epam.litvin.receiver;

import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;
import sun.misc.Request;

public interface CompetitionReceiver extends Receiver {
    /**
     * Create competition.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void createCompetition(RequestContent requestContent) throws ReceiverException;

    /**
     * Open upcoming competition.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void openUpcomingCompetition(RequestContent requestContent) throws ReceiverException;

    /**
     * Open past competition.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void openPastCompetition(RequestContent requestContent) throws ReceiverException;

    /**
     * Open competitions by type.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void openCompetitionsByType(RequestContent requestContent) throws ReceiverException;

    /**
     * Open concrete competition.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void openConcreteCompetition(RequestContent requestContent) throws ReceiverException;

    /**
     * Open competition settings.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void openCompetitionSettings(RequestContent requestContent) throws ReceiverException;

    /**
     * Update upcoming activated.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void updateUpcomingActivated(RequestContent requestContent) throws ReceiverException;

    /**
     * Update upcoming deactivated.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void updateUpcomingDeactivated(RequestContent requestContent) throws ReceiverException;

    /**
     * Update state competition.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void updateStateCompetition(RequestContent requestContent) throws ReceiverException;

    /**
     * Update result competition.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void updateResultsCompetition(RequestContent requestContent) throws ReceiverException;

    /**
     * Delete unfilled competition.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void deleteUnfilledCompetition(RequestContent requestContent) throws ReceiverException;

    /**
     * Delete filled competition.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void deleteFilledCompetition(RequestContent requestContent) throws ReceiverException;
}
