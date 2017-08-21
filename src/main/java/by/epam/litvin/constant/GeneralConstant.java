package by.epam.litvin.constant;

final public class GeneralConstant {

    private GeneralConstant() {}

    public static final String NEWS_ID = "newsId";
    public static final String NEWS_LIST = "newsList";
    public static final String _NEWS_DOT = "_news.";
    public static final String NEWS = "news";

    public static final String PATH_TO_DB_PROPERTIES = "database";
    public static final String PATH_TO_PAGE_PATH_PROPERTIES = "pages";


    public static final String SALT = "totalizator";
    public static final String DUPLICATE_UNIQUE_INDEX = "23000";
    public static final String CAN_NOT_DELETE_OR_UPDATE = "23000";
    public static final String TEMPORARY = "temporary";
    public static final String ACCESS_DENIED = "accessDenied";
    public static final String WRONG_NEWS = "wrongNews";
    public static final String USER = "user";
    public static final String LIVE_GAMES = "liveGames";
    public static final String UPCOMING_GAMES = "upcomingGames";
    public static final String PAST_GAMES = "pastGames";
    public static final String KINDS_OF_SPORT = "kindsOfSport";
    public static final String LOCALE = "locale";
    public static final String COMMENT_ID = "commentId";
    public static final String IS_BLOCKED = "isBlocked";
    public static final String TEXT = "text";
    public static final String INVALID_TEXT = "invalidText";
    public static final String KIND_OF_SPORT_ID = "kindOfSportId";
    public static final String COMMAND_ID = "commandId";
    public static final String NEW_NAME = "newName";
    public static final String KINDS_OF_SPORT_LIST = "kindsOfSportList";
    public static final String CURRENT_ID = "currentId";
    public static final String SUCCESS = "success";
    public static final String COUNT = "count";
    public static final String CASH = "cash";

    public static final String IMAGE = "image";


    public static final int COUNT_NEWS_ON_PAGE = 10;
    public static final int COUNT_USERS_ON_PAGE = 10;


    public static final int COUNT_NEWS_ON_MAIN_PAGE = 3;
    public static final int COUNT_COMPETITIONS_ON_MAIN_PAGE = 5;

    public static final class DataBase {
        public static final String DRIVER = "driver";
        public static final String URL = "url";
        public static final String USER = "user";
        public static final String PASSWORD = "password";
        public static final String USE_UNICODE = "useUnicode";
        public static final String CHARACTER_ENCODING = "characterEncoding";
        public static final String AUTO_RECONNECT = "autoReconnect";

    }

    public static class ConnectionPool {
        public static final String CAPACITY = "poolCapacity";
    }


}
