package by.epam.litvin.receiver;

import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;

public interface TeamReceiver extends Receiver {
    void openTeamSetting(RequestContent requestContent) throws ReceiverException;

    void updateTeam(RequestContent requestContent) throws ReceiverException;

    void createTeam(RequestContent requestContent) throws ReceiverException;

    void deleteTeam(RequestContent requestContent) throws ReceiverException;

    void findTeam(RequestContent requestContent) throws ReceiverException;
}
