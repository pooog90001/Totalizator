package by.epam.litvin.receiver;

import by.epam.litvin.content.RequestContent;

public interface BetReceiver extends Receiver {
    void openDoBetPage(RequestContent requestContent);
}
