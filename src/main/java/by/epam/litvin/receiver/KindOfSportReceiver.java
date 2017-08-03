package by.epam.litvin.receiver;

import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;

public interface KindOfSportReceiver extends Receiver {
    void openKindOfSportSetting(RequestContent requestContent) throws ReceiverException;
    void updateKindOfSport(RequestContent requestContent) throws ReceiverException;
    void createKindOfSport(RequestContent requestContent) throws ReceiverException;
    void deleteKindOfSport(RequestContent requestContent) throws ReceiverException;
}
