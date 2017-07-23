package by.epam.litvin.constant;

final public class SQLRequestConstant {
    private SQLRequestConstant(){}

    public static final String  CREATE_USER =
                    "INSERT INTO user (user_name, user_email, user_password, user_confirm_url) " +
                    "VALUES (?, ?, ?, ?);";

    public static final String  FIND_USER =
                    "SELECT user_id, user_name , user_email, user_password, user_avatar_url, user_type, " +
                            "user_confirm_url, user_is_blocked, user_blocked_text, user_is_confirm, user_cash " +
                    "FROM user " +
                    "WHERE user_email = ? AND user_password = ?;";

    public static final String FIND_LIMIT_NEWS =
                    "SELECT news_id, news_title, news_image_url, news_text, news_date_creation " +
                    "FROM news " +
                    "ORDER BY news_id DESC " +
                    "LIMIT ?;";

    public static final String FIND_NEWS_BY_ID =
                    "SELECT news_id, news_title, news_image_url, news_text, news_date_creation " +
                    "FROM news " +
                    "WHERE news_id = ? ;";

    // By Max

    /*
     * Получение всех команд
     */
    public static final String FIND_ALL_COMMAND =
                    "SELECT command_id,command_name, kind_of_sport_id " +
                    "FROM command;";

    /*
     * Получение команд по ID
     */
    public static final String FIND_COMMAND_BY_ID =
                    "SELECT command_name, kind_of_sport_id " +
                    "FROM command " +
                    "WHERE command_id = ? ;";

    /*
     * Удаление команды по ID
     */
    public static final String DELETE_COMMAND_BY_ID =
                    "DELETE " +
                    "FROM command " +
                    "WHERE command_id = ?;";

    /*
     * Изменение команды по ID
     */
    public static final String UPDATE_COMMAND_BY_ID =
                    "UPDATE command " +
                    "SET command_name = ?, kind_of_sport_id = ? " +
                    "WHERE command_id = ?;";

    /*
     * Добавление новой команды
     */
    public static final String INSERT_COMMAND =
                    "INSERT INTO command (name, kind_of_sport_id) " +
                    "VALUES (?, ?);";



    //
}
