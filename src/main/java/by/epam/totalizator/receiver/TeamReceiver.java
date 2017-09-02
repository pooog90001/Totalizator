package by.epam.totalizator.receiver;

import by.epam.totalizator.content.RequestContent;
import by.epam.totalizator.exception.DAOException;
import by.epam.totalizator.exception.ReceiverException;

public interface TeamReceiver extends Receiver {

    /**
     * Open team setting.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void openTeamSetting(RequestContent requestContent) throws ReceiverException;

    /**
     * Update team.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void updateTeam(RequestContent requestContent) throws ReceiverException;

    /**
     * Create team.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void createTeam(RequestContent requestContent) throws ReceiverException;

    /**
     * Delete team.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void deleteTeam(RequestContent requestContent) throws ReceiverException;

    /**
     * Find team.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void findTeam(RequestContent requestContent) throws ReceiverException;
}
