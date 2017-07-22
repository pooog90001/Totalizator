package by.epam.litvin.constant;

final public class SQLRequestConstant {
    private SQLRequestConstant(){}

    public static final String  CREATE_USER =
                    "INSERT INTO `user` (`user_name`, `user_email`, `user_password`, `user_confirm_url`) " +
                    "VALUES (?, ?, ?, ?);";

    public static final String  FIND_USER =
                    "SELECT `user_name` , `user_email`, `user_password`, `user_confirm_url`, `user_is_blocked` , `user_is_confirm`, `user_cash`" +
                    "FROM `user`" +
                    "WHERE `user_email` = ? AND `user_password` = ?;";

    public static final String FIND_LIMIT_NEWS =
                    "SELECT news_id, news_title, news_image_url, news_text, news_date_creation" +
                    "FROM news" +
                    "ORDER BY news_id DESC" +
                    "LIMIT ?;";

    public static final String FIND_NEWS_BY_ID =
                    "SELECT news_id, news_title, news_image_url, news_text, news_date_creation" +
                    "FROM news" +
                    "WHERE news_id = ? ;";
}
