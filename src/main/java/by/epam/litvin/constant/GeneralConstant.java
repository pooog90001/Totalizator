package by.epam.litvin.constant;

public class GeneralConstant {

    public static final String PATH_TO_PROPERTIES = "database";
    public static final String SALT = "totalizator";

    private GeneralConstant() {

    }

    public static class DataBase {

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
        public static final String CONNECTION_WAIT_TIME = "connectionWaitTime";
    }


}
