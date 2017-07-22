package by.epam.litvin.constant;

final public class SQLFieldConstant {
    private SQLFieldConstant() {
    }

    public final class User {
        private static final String USER_ = "user_";
        public static final String ID =  USER_ + "id";
        public static final String NAME = USER_ + "name";
        public static final String EMAIL = USER_ + "email";
        public static final String PASSWORD = USER_ + "password";
        public static final String CONFIRM_URL = USER_ + "confirm_url";
        public static final String IS_CONFIRM = USER_ + "is_confirm";
        public static final String IS_BLOCKED = USER_ + "is_blocked";
        public static final String CASH = USER_ + "cash";
    }

}

