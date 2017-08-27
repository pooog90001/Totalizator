package by.epam.litvin.receiver;

import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;

public interface UserReceiver extends Receiver {
    void signUp(RequestContent content) throws ReceiverException;

    void signIn(RequestContent content) throws ReceiverException;

    void signOut(RequestContent content);

    void openUserSettings(RequestContent content) throws ReceiverException;

    void openProfile(RequestContent content) throws ReceiverException;

    void changeRole(RequestContent content) throws ReceiverException;

    void changeLock(RequestContent content) throws ReceiverException;

    void changeAvatar(RequestContent content) throws ReceiverException;

    void changePassword(RequestContent content) throws ReceiverException;

    void addMoney(RequestContent content) throws ReceiverException;

    void withdrawMoney(RequestContent content) throws ReceiverException;
}
