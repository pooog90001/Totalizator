package by.epam.litvin.constant;


final public class SQLFieldConstant {
    private SQLFieldConstant() {
    }

    public final class User {
        private static final String USER = "user_";
        public static final String ID =  USER + "id";
        public static final String NAME = USER + "name";
        public static final String EMAIL = USER + "email";
        public static final String PASSWORD = USER + "password";
        public static final String CONFIRM_URL = USER + "confirm_url";
        public static final String IS_CONFIRM = USER + "is_confirm";
        public static final String IS_BLOCKED = USER + "is_blocked";
        public static final String CASH = USER + "cash";
        public static final String TYPE = USER + "type";
        public static final String BLOCKED_TEXT = USER + "blocked_text";
        public static final String AVATAR_URL = USER + "avatar_url";
    }

    public final class News {
        private static final String NEWS = "news_";
        public static final String ID = NEWS + "id";
        public static final String TITLE = NEWS + "title";
        public static final String IMAGE_URL = NEWS + "image_url";
        public static final String TEXT = NEWS + "text";
        public static final String DATE_CREATION = NEWS + "date_creation";
    }

    public final class Comment {
        private static final String COMMENT = "comment_";
        public static final String ID = COMMENT + "id";
        public static final String TEXT = COMMENT + "text";
        public static final String IS_BLOCKED = COMMENT + "is_blocked";
        public static final String POST_DATE = COMMENT + "post_date";
    }

    public final class Command {
        private static final String COMMAND = "command_";
        public static final String ID = COMMAND + "id";
        public static final String NAME = COMMAND + "name";
        public static final String KIND_OF_SPORT_ID = "kind_of_sport_id";
    }

    public final class CompetitionType {
        private static final String COMPETITION_TYPE = "competition_type_";
        public static final String ID = COMPETITION_TYPE + "id";
        public static final String NAME = COMPETITION_TYPE + "name";
    }

    public final class Competition {
        private static final String COMPETITION = "competition_";
        public static final String ID = COMPETITION + "id";
        public static final String NAME = COMPETITION + "name";
        public static final String DESCRIPTION = COMPETITION + "description";
        public static final String DATE_START = COMPETITION + "date_start";
        public static final String DATE_FINISH = COMPETITION + "date_finish";
        public static final String TOTAL = COMPETITION + "total";
        public static final String MORE_TOTAL_COEFF = COMPETITION + "more_total_coeff";
        public static final String LESS_TOTAL_COEFF = COMPETITION + "less_total_coeff";
        public static final String STANDOFF_COEFF = COMPETITION + "standoff_coeff";
        public static final String IS_ACTIVE = COMPETITION + "is_active";
        public static final String IS_RESULT_FILLED = COMPETITION + "is_result_filled";

    }

    public final class KindOfSport {
        private static final String KIND_OF_SPORT = "kind_of_sport_";
        public static final String ID = KIND_OF_SPORT + "id";
        public static final String NAME = KIND_OF_SPORT + "name";
        public static final String COMPETITOR_COUNT = "competitor_count";
    }

    public final class Competitor {
        private static final String COMPETITOR = "competitor_";
        public static final String ID = COMPETITOR + "id";
        public static final String IS_WIN = COMPETITOR + "is_win";
        public static final String WIN_COEFF =  COMPETITOR + "win_coeff";
        public static final String RESULT = COMPETITOR + "result";
    }

    public final class Bet {
        private static final String BET = "bet_";
        public static final String ID = BET + "id";
        public static final String CASH = BET + "cash";
        public static final String IS_WIN = BET + "is_win";
        public static final String TOTAL = BET + "total";
        public static final String IS_ACTIVE = BET + "is_active";
    }

}

