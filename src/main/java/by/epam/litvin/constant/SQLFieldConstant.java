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
        public static final String TYPE = USER_ + "type";
        public static final String BLOCKED_TEXT = USER_ + "blocked_text";
    }

    public final class Command {
        private static final String COMMAND = "command_";
        public static final String ID = COMMAND + "id";
        public static final String NAME = COMMAND + "name";
        public static final String KIND_OF_SPORT_ID = "kind_of_sport_id";
    }

}

