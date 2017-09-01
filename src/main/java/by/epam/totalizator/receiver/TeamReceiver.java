package by.epam.totalizator.receiver;

import by.epam.totalizator.content.RequestContent;
import by.epam.totalizator.exception.ReceiverException;

public interface TeamReceiver extends Receiver {

    /**
     * Open team setting.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void openTeamSetting(RequestContent requestContent) throws ReceiverException;

    /**
     * Update team.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void updateTeam(RequestContent requestContent) throws ReceiverException;

    /**
     * Create team.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void createTeam(RequestContent requestContent) throws ReceiverException;

    /**
     * Delete team.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void deleteTeam(RequestContent requestContent) throws ReceiverException;

    /**
     * Find team.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void findTeam(RequestContent requestContent) throws ReceiverException;
}
