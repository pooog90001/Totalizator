package by.epam.litvin.constant;

final public class GeneralConstant {

    public static final String NEWS_ID = "newsId";
    public static final String NEWS_LIST = "newsList";
    public static final String _NEWS_DOT = "_news.";
    public static final String _AVATAR_DOT = "_avatar.";
    public static final String NEWS = "news";
    public static final String DB_PROPERTIES = "config/database";
    public static final String PAGE_PATH_PROPERTIES = "config/pages";
    public static final String UPLOAD_PROPERTIES = "config/upload";
    public static final String EMAIL_PROPERTIES = "config/email";

    public static final String SALT = "totalizator";
    public static final String DUPLICATE_UNIQUE_INDEX = "23000";
    public static final String CAN_NOT_DELETE_OR_UPDATE = "23000";
    public static final String TEMPORARY = "temporary";
    public static final String ACCESS_DENIED = "accessDenied";
    public static final String WRONG_NEWS = "wrongNews";
    public static final String WRONG_DATA = "wrongData";
    public static final String WRONG_EXTENSION = "wrongExtension";
    public static final String WRONG_UPLOAD = "wrongUpload";
    public static final String POINT_X1 = "x1";
    public static final String POINT_X2 = "x2";
    public static final String POINT_Y1 = "y1";
    public static final String POINT_Y2 = "y2";
    public static final String HEIGHT = "height";
    public static final String WIDTH = "width";
    public static final String USER = "user";
    public static final String UPCOMING_GAMES = "upcomingGames";
    public static final String PAST_GAMES = "pastGames";
    public static final String KINDS_OF_SPORT = "kindsOfSport";
    public static final String LOCALE = "locale";
    public static final String COMMENT_ID = "commentId";
    public static final String IS_BLOCKED = "isBlocked";
    public static final String IS_NOT_CONFIRMED = "isNotConfirmed";
    public static final String TEXT = "text";
    public static final String INVALID_TEXT = "invalidText";
    public static final String KIND_OF_SPORT_ID = "kindOfSportId";
    public static final String COMMAND_ID = "commandId";
    public static final String COMPETITOR_ID = "competitorId";
    public static final String COMPETITION_ID = "competitionId";
    public static final String NEW_NAME = "newName";
    public static final String KINDS_OF_SPORT_LIST = "kindsOfSportList";
    public static final String CURRENT_ID = "currentId";
    public static final String SUCCESS = "success";
    public static final String COUNT = "count";
    public static final String CASH = "cash";
    public static final String COMMAND = "command";
    public static final String PAGE_NOT_FOUND = "pageNotFound";
    public static final String PAGE_NUMBER = "pageNumber";
    public static final String LITTLE_MONEY = "littleMoney";
    public static final String IMAGE = "image";
    public static final int COUNT_NEWS_ON_PAGE = 2;
    public static final int COUNT_USERS_ON_PAGE = 10;
    public static final int COUNT_NEWS_ON_MAIN_PAGE = 3;
    public static final int COUNT_COMPETITIONS_ON_MAIN_PAGE = 5;
    public static final int COUNT_COMPETITIONS_ON_PAGE = 3;

    private GeneralConstant() {
    }

    public static final class DataBase {
        public static final String DRIVER = "driver";
        public static final String URL = "url";
        public static final String USER = "user";
        public static final String PASSWORD = "password";
        public static final String USE_UNICODE = "useUnicode";
        public static final String CHARACTER_ENCODING = "characterEncoding";
        public static final String AUTO_RECONNECT = "autoReconnect";

    }

    public static final class Mail {
        public static final String MAIL_SMTP_HOST = "mail.smtp.host";
        public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
        public static final String MAIL_SMTP_START_TLS_ENABLE = "mail.smtp.starttls.enable";
        public static final String MAIL_SMTP_PORT = "mail.smtp.port";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String NAME = "name";
        public static final String URL = "url";
    }

    public static class ConnectionPool {
        public static final String CAPACITY = "poolCapacity";
    }


}
