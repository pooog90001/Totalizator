package by.epam.litvin.receiver;

import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;

public interface KindOfSportReceiver extends Receiver {
    /**
     * Open kind of sport setting.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void openKindOfSportSetting(RequestContent requestContent) throws ReceiverException;

    /**
     * Update kind of sport.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void updateKindOfSport(RequestContent requestContent) throws ReceiverException;

    /**
     * Create kind of sport.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void createKindOfSport(RequestContent requestContent) throws ReceiverException;

    /**
     * Delete kind of sport.
     *
     * @param requestContent
     * @throws ReceiverException
     */
    void deleteKindOfSport(RequestContent requestContent) throws ReceiverException;
}
