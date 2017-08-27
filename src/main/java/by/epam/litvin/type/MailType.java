package by.epam.litvin.type;

import by.epam.litvin.constant.GeneralConstant;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import static by.epam.litvin.constant.GeneralConstant.EMAIL_PROPERTIES;

public enum MailType {

    SMTP_HOST(GeneralConstant.Mail.MAIL_SMTP_HOST),
    SMTP_AUTH(GeneralConstant.Mail.MAIL_SMTP_AUTH),
    SMTP_START_TLS_ENABLE(GeneralConstant.Mail.MAIL_SMTP_START_TLS_ENABLE),
    SMTP_PORT(GeneralConstant.Mail.MAIL_SMTP_PORT),
    EMAIL(GeneralConstant.Mail.EMAIL),
    PASSWORD(GeneralConstant.Mail.PASSWORD),
    NAME(GeneralConstant.Mail.NAME),
    URL(GeneralConstant.Mail.URL);

    private static final Logger LOGGER = LogManager.getLogger();
    private static ResourceBundle jspBundle = ResourceBundle.getBundle(EMAIL_PROPERTIES);

    private String realName;

    MailType(String name) {
        this.realName = name;
    }

    public String getValue() {
        try {
            return jspBundle.getString(this.realName);

        } catch (MissingResourceException e) {
            LOGGER.log(Level.ERROR, "Value not found.", e);
            throw new RuntimeException("Value not found.", e);

        } catch (ClassCastException e) {
            LOGGER.log(Level.ERROR, "Value is not a string.", e);
            throw new RuntimeException("Value is not a string.", e);
        }
    }
}
