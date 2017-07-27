package by.epam.litvin.receiver.impl;

import by.epam.litvin.content.RequestContent;
import by.epam.litvin.exception.ReceiverException;
import by.epam.litvin.receiver.CommonReceiver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonReceiverImpl implements CommonReceiver{
    private static final Logger LOGGER = LogManager.getLogger();



    @Override
    public void changeLocale(RequestContent requestContent) throws ReceiverException {
        String locale = requestContent.getRequestParameters().get("locale")[0];
        requestContent.getSessionAttributes().put("locale", locale.toLowerCase());
    }
}
