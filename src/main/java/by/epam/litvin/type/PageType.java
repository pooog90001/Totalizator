package by.epam.litvin.type;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import static by.epam.litvin.constant.GeneralConstant.PAGE_PATH_PROPERTIES;

public enum PageType {

    //general pages
    MAIN,
    SIGN_UP,
    SIGN_IN,
    CONFIRM,
    ALL_NEWS,
    ALL_UPCOMING_COMPETITIONS,
    ALL_PAST_COMPETITIONS,
    COMPETITIONS_BY_TYPE,
    CONCRETE_COMPETITION,
    CONCRETE_NEWS,
    CONCRETE_NEWS_ADD,
    PROFILE,
    BLOCK,
    INDEX,

    //Admin pages
    ADMIN_MAIN,
    ADMIN_COMMAND,
    ADMIN_COMMAND_ADD,
    ADMIN_COMPETITION_TYPE,
    ADMIN_COMPETITION_TYPE_ADD,
    ADMIN_COMPETITION,
    ADMIN_COMPETITION_ADD,
    ADMIN_KIND_OF_SPORT,
    ADMIN_KIND_OF_SPORT_ADD,
    ADMIN_NEWS,
    ADMIN_USER,

    //Error pages
    ERROR_404,
    ERROR_500,
    ERROR_SERVER;

    private static final Logger LOGGER = LogManager.getLogger();
    private static ResourceBundle jspBundle = ResourceBundle.getBundle(PAGE_PATH_PROPERTIES);

    public String getPage() {
        try {
            return jspBundle.getString(this.toString());

        } catch (MissingResourceException e) {
            LOGGER.log(Level.ERROR, "Path not found.", e);
            throw new RuntimeException("Path not found.", e);

        } catch (ClassCastException e) {
            LOGGER.log(Level.ERROR, "Path is not a string.", e);
            throw new RuntimeException("Path is not a string.", e);
        }
    }

}
