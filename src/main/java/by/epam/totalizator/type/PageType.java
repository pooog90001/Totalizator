package by.epam.totalizator.type;

import by.epam.totalizator.constant.GeneralConstant;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public enum PageType {

    /* ------ General pages ------*/
    MAIN,
    SIGN_UP,
    SIGN_IN,
    ALL_NEWS,
    ALL_UPCOMING_COMPETITIONS,
    ALL_PAST_COMPETITIONS,
    COMPETITIONS_BY_TYPE,
    CONCRETE_COMPETITION,
    CONCRETE_NEWS,
    CONCRETE_NEWS_ADD,
    HELP,
    PROFILE,
    BLOCK,
    INDEX,

    /* ------- Admin pages --------*/
    ADMIN_MAIN,
    ADMIN_TEAM,
    ADMIN_TEAM_ADD,
    ADMIN_COMPETITION_TYPE,
    ADMIN_COMPETITION_TYPE_ADD,
    ADMIN_COMPETITION,
    ADMIN_COMPETITION_ADD,
    ADMIN_KIND_OF_SPORT,
    ADMIN_KIND_OF_SPORT_ADD,
    ADMIN_NEWS,
    ADMIN_USER,

    /* ------- Error pages ------- */
    ERROR_404,
    ERROR_500,
    ERROR_SERVER;

    private static final Logger LOGGER = LogManager.getLogger();
    private static ResourceBundle jspBundle = ResourceBundle.getBundle(GeneralConstant.PAGE_PATH_PROPERTIES);

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
