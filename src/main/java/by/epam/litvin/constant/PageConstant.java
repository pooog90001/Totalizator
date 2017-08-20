package by.epam.litvin.constant;

import com.sun.xml.internal.bind.v2.TODO;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import static by.epam.litvin.constant.GeneralConstant.PATH_TO_PAGE_PATH_PROPERTIES;

final public class PageConstant {
    private final static Logger LOGGER = LogManager.getLogger();
   /* private final static Properties properties;

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(PATH_TO_PAGE_PATH_PROPERTIES);
        properties = new Properties();

        try {
            ttt = resourceBundle.getString("sdf");

        } catch (MissingResourceException e) {
            LOGGER.log(Level.FATAL, "Connection pool will nowhere create. ", e);
            throw new RuntimeException("Data base configuration file not found. ", e);

        }
    }*/
// TODO Что делать с путями к страницам? Как вариант сделать ENUM
    private PageConstant() {}

    // relative path to upload file
    public static final String PATH_TO_UPLOAD_NEWS = "/image/news/";
    public static final String PATH_TO_UPLOAD_AVATARS = "/image/avatar/";

    //users pages
    private static final String ROOT = "jsp/";

    public static final String MAIN = ROOT + "main.jsp";
    public static final String SIGN_UP = ROOT + "sign_up.jsp";
    public static final String SIGN_IN = ROOT + "sign_in.jsp";
    public static final String CONFIRM = ROOT + "confirm.jsp";
    public static final String ALL_NEWS = ROOT + "all_news.jsp";
    public static final String ALL_LIVE_COMPETITIONS = ROOT + "all_live_competitions.jsp";
    public static final String ALL_UPCOMING_COMPETITIONS = ROOT + "all_upcoming_competitions.jsp";
    public static final String ALL_PAST_COMPETITIONS = ROOT + "all_past_competitions.jsp";
    public static final String CONCRETE_NEWS = ROOT + "concrete_news.jsp";
    public static final String CONCRETE_NEWS_ADD = ROOT + "concrete_news_add.jsp";
    public static final String INDEX = "index.jsp";

    //Admin pages
    private static final String ADMIN_ROOT = "jsp/admin_panel/";
    public static final String ADMIN_MAIN = ADMIN_ROOT + "main.jsp";
    public static final String ADMIN_COMMAND = ADMIN_ROOT + "command.jsp";
    public static final String ADMIN_COMMAND_ADD = ADMIN_ROOT + "command_add.jsp";
    public static final String ADMIN_COMPETITION_TYPE = ADMIN_ROOT + "competition_type.jsp";
    public static final String ADMIN_COMPETITION_TYPE_ADD = ADMIN_ROOT + "competition_type_add.jsp";
    public static final String ADMIN_COMPETITION = ADMIN_ROOT + "competition.jsp";
    public static final String ADMIN_COMPETITION_ADD = ADMIN_ROOT + "competition_add.jsp";
    public static final String ADMIN_KIND_OF_SPORT = ADMIN_ROOT + "kind_of_sport.jsp";
    public static final String ADMIN_KIND_OF_SPORT_ADD = ADMIN_ROOT + "kind_of_sport_add.jsp";
    public static final String ADMIN_NEWS = ADMIN_ROOT + "news.jsp";
    public static final String ADMIN_USER = ADMIN_ROOT + "user.jsp";


    //Error pages
    private static final String ERROR_ROOT = "jsp/error/";
    public static final String ERROR_404 = ERROR_ROOT + "404.jsp";
    public static final String ERROR_500 = ERROR_ROOT + "500.jsp";
    public static final String ERROR_RUNTIME = ERROR_ROOT + "runtime.jsp";
}
