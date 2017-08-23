package by.epam.litvin.constant;



final public class SQLRequestConstant {
    private SQLRequestConstant(){}

    public static final String  CREATE_USER =
            "INSERT INTO user (user_name, user_email, user_password, user_confirm_url) " +
                    "VALUES (?, ?, ?, ?);";

    public static final String FIND_USER_BY_EMAIL_AND_PASSWORD =
            "SELECT user_id, user_name , user_email, user_password, user_avatar_url, user_type, " +
                    "user_confirm_url, user_is_blocked, user_blocked_text, user_is_confirm, user_cash " +
                    "FROM user " +
                    "WHERE user_email = ? AND user_password = ?;";

    public static final String FIND_USER_BY_ID =
            "SELECT user_id, user_name , user_email, user_password, user_avatar_url, user_type, " +
                    "user_confirm_url, user_is_blocked, user_blocked_text, user_is_confirm, user_cash " +
                    "FROM user " +
                    "WHERE user_id = ?;";

    public static final String  FIND_LIMIT_USERS =
            "SELECT user_id, user_name , user_email, user_password, user_avatar_url, user_type, " +
                    "user_confirm_url, user_is_blocked, user_blocked_text, user_is_confirm, user_cash " +
                    "FROM user " +
                    "ORDER BY user_id DESC " +
                    "LIMIT ?, ?;";

    public static final String FIND_USERS_COUNT =
            "SELECT  " +
                    "    COUNT(user_id) AS count " +
                    "FROM " +
                    "    user;";

    public static final String UPDATE_USER_ROLE =
            "UPDATE user " +
                    "SET user_type = ? " +
                    "WHERE user_id = ?;";

    public static final String UPDATE_USER_LOCK =
            "UPDATE user " +
                    "SET user_is_blocked = ?, " +
                    " user_blocked_text = ? " +
                    "WHERE user_id = ?;";

    public static final String UPDATE_USER_CASH =
            "UPDATE user " +
                    "SET user_cash = ? " +
                    "WHERE user_id = ?;";


    public static final String FIND_LIMIT_NEWS =
            "SELECT news_id, news_title, news_image_url, news_text, news_date_creation " +
                    "FROM news " +
                    "ORDER BY news_id DESC " +
                    "LIMIT ?, ?;";

    public static final String FIND_ALL_NEWS =
            "SELECT news_id, news_title, news_image_url, news_text, news_date_creation " +
                    "FROM news " +
                    "ORDER BY news_id DESC;";

    public static final String FIND_NEWS_BY_ID =
            "SELECT news_id, news_title, news_image_url, news_text, news_date_creation " +
                    "FROM news " +
                    "WHERE news_id = ? ;";

    public static final String FIND_NEWS_COUNT =
            "SELECT  " +
                    "    COUNT(news_id) AS count " +
                    "FROM " +
                    "    news;";

    public static final String DELETE_NEWS_BY_ID =
            "DELETE " +
                    "FROM news  " +
                    "WHERE news_id = ?; ";

    public static final String CREATE_NEWS =
            "INSERT INTO news  (news_title, news_text, news_date_creation)" +
                    "VALUES (?, ?, now());";

    public static final String UPDATE_IMAGE_PATH_NEWS =
            "UPDATE news  " +
                    "SET news_image_url = ? " +
                    "WHERE news_id = ? ;";

    public static final String FIND_ALL_COMMAND =
            "SELECT command_id, command_name, kind_of_sport_id " +
                    "FROM command;";

    public static final String FIND_ALL_COMMANDS_WITH_KIND_OF_SPORT =
            "SELECT command_id, " +
                    "       command_name, " +
                    "       command.kind_of_sport_id, " +
                    "       kind_of_sport_name " +
                    "FROM  command, " +
                    "       kind_of_sport " +
                    "WHERE command.kind_of_sport_id = kind_of_sport.kind_of_sport_id;";

    public static final String FIND_COMMANDS_BY_SPORT_ID =
            "   SELECT DISTINCT " +
                    "    command_id, command_name, command.kind_of_sport_id " +
                    "FROM " +
                    "    command, " +
                    "    kind_of_sport " +
                    "WHERE " +
                    "    command.kind_of_sport_id = ?;";


    public static final String FIND_COMMAND_BY_ID =
            "SELECT command_name, kind_of_sport_id " +
                    "FROM command " +
                    "WHERE command_id = ? ;";


    public static final String DELETE_COMMAND_BY_ID =
            "DELETE " +
                    "FROM command " +
                    "WHERE command_id = ?;";


    public static final String UPDATE_COMMAND_BY_ID =
            "UPDATE command " +
                    "SET command_name = ? " +
                    "WHERE command_id = ?;";


    public static final String CREATE_COMMAND =
            "INSERT INTO command (command_name, kind_of_sport_id) " +
                    "VALUES (?, ?);";

    public static final String FIND_UPCOMING_GAME_BY_ID =
            "SELECT  DISTINCT " +
                    "    competition.competition_id,  " +
                    "    competition_type_name,  " +
                    "    kind_of_sport_name,  " +
                    "    competition_total,  " +
                    "    competition_less_total_coeff,  " +
                    "    competition_more_total_coeff,  " +
                    "    competition_standoff_coeff,  " +
                    "    competition_date_start,  " +
                    "    competition_date_finish,  " +
                    "    competition_name  " +
                    "FROM  " +
                    "    competition,  " +
                    "    competition_type,  " +
                    "    competitor,  " +
                    "    command,  " +
                    "    kind_of_sport  " +
                    "WHERE  " +
                    "        competition.competition_id  = ? " +
                    "        AND competitor.competition_id = competition.competition_id  " +
                    "        AND competitor.command_id = command.command_id  " +
                    "        AND competition.competition_type_id = competition_type.competition_type_id  " +
                    "        AND command.kind_of_sport_id = kind_of_sport.kind_of_sport_id  " +
                    "        AND competition_date_start > NOW()  " +
                    "        AND competition_is_active = true ;";

    public static final String FIND_GAME_BY_ID =
            "SELECT  DISTINCT " +
                    "    competition.competition_id,  " +
                    "    competition_type_id,  " +
                    "    competition_total,  " +
                    "    competition_less_total_coeff,  " +
                    "    competition_more_total_coeff,  " +
                    "    competition_standoff_coeff,  " +
                    "    competition_date_start,  " +
                    "    competition_date_finish,  " +
                    "    competition_is_result_filled,  " +
                    "    competition_is_active,  " +
                    "    competition_name  " +
                    "FROM  " +
                    "    competition  " +
                    "WHERE  " +
                    "        competition.competition_id  = ? ;";

    public static final String FIND_LIMIT_UPCOMING_GAMES =
            "SELECT  DISTINCT " +
                    "    competition.competition_id,  " +
                    "    competition_type_name,  " +
                    "    kind_of_sport_name,  " +
                    "    competition_total,  " +
                    "    competition_less_total_coeff,  " +
                    "    competition_more_total_coeff,  " +
                    "    competition_standoff_coeff,  " +
                    "    competition_date_start,  " +
                    "    competition_date_finish,  " +
                    "    competition_name  " +
                    "FROM  " +
                    "    competition,  " +
                    "    competition_type,  " +
                    "    competitor,  " +
                    "    command,  " +
                    "    kind_of_sport  " +
                    "WHERE  " +
                    "    competitor.competition_id = competition.competition_id  " +
                    "        AND competitor.command_id = command.command_id  " +
                    "        AND competition.competition_type_id = competition_type.competition_type_id  " +
                    "        AND command.kind_of_sport_id = kind_of_sport.kind_of_sport_id  " +
                    "        AND competition_date_start > NOW()  " +
                    "        AND competition_is_active = ? " +
                    "ORDER BY competition.competition_date_start " +
                    "LIMIT ?, ?;";

    public static final String FIND_ALL_UPCOMING_GAMES =
            "SELECT  DISTINCT " +
                    "    competition.competition_id,  " +
                    "    competition_type_name,  " +
                    "    kind_of_sport_name,  " +
                    "    competition_total,  " +
                    "    competition_less_total_coeff,  " +
                    "    competition_more_total_coeff,  " +
                    "    competition_standoff_coeff,  " +
                    "    competition_date_start,  " +
                    "    competition_date_finish,  " +
                    "    competition_name  " +
                    "FROM  " +
                    "    competition,  " +
                    "    competition_type,  " +
                    "    competitor,  " +
                    "    command,  " +
                    "    kind_of_sport  " +
                    "WHERE  " +
                    "    competitor.competition_id = competition.competition_id  " +
                    "        AND competitor.command_id = command.command_id  " +
                    "        AND competition.competition_type_id = competition_type.competition_type_id  " +
                    "        AND command.kind_of_sport_id = kind_of_sport.kind_of_sport_id  " +
                    "        AND competition_date_start > NOW()  " +
                    "        AND competition_is_active = ? ";


    public static final String FIND_ALL_UPCOMING_BY_TYPE_ID =
                    "SELECT  DISTINCT " +
                    "    competition.competition_id,  " +
                    "    competition_type_name,  " +
                    "    kind_of_sport_name,  " +
                    "    competition_total,  " +
                    "    competition_less_total_coeff,  " +
                    "    competition_more_total_coeff,  " +
                    "    competition_standoff_coeff,  " +
                    "    competition_date_start,  " +
                    "    competition_date_finish,  " +
                    "    competition_name  " +
                    "FROM  " +
                    "    competition,  " +
                    "    competition_type,  " +
                    "    competitor,  " +
                    "    command,  " +
                    "    kind_of_sport  " +
                    "WHERE  " +
                    "    competitor.competition_id = competition.competition_id  " +
                    "        AND competitor.command_id = command.command_id  " +
                    "        AND competition.competition_type_id = competition_type.competition_type_id  " +
                    "        AND competition_type.competition_type_id = ? " +
                    "        AND command.kind_of_sport_id = kind_of_sport.kind_of_sport_id  " +
                    "        AND kind_of_sport.kind_of_sport_id = ? " +
                    "        AND competition_date_start > NOW()  " +
                    "        AND competition_is_active = ? ";


    public static final String FIND_ALL_PAST_BY_TYPE_ID =
            "SELECT  DISTINCT " +
                    "    competition.competition_id,  " +
                    "    competition_type_name,  " +
                    "    kind_of_sport_name,  " +
                    "    competition_total,  " +
                    "    competition_less_total_coeff,  " +
                    "    competition_more_total_coeff,  " +
                    "    competition_standoff_coeff,  " +
                    "    competition_date_start,  " +
                    "    competition_date_finish,  " +
                    "    competition_name  " +
                    "FROM  " +
                    "    competition,  " +
                    "    competition_type,  " +
                    "    competitor,  " +
                    "    command,  " +
                    "    kind_of_sport  " +
                    "WHERE  " +
                    "    competitor.competition_id = competition.competition_id  " +
                    "        AND competitor.command_id = command.command_id  " +
                    "        AND competition.competition_type_id = competition_type.competition_type_id  " +
                    "        AND competition_type.competition_type_id = ? " +
                    "        AND command.kind_of_sport_id = kind_of_sport.kind_of_sport_id  " +
                    "        AND kind_of_sport.kind_of_sport_id = ? " +
                    "        AND competition_date_finish < NOW()  " +
                    "        AND competition_is_active = ? " +
                    "        AND competition_is_result_filled = ? ;";


    public static final String FIND_UPCOMING_GAMES_COUNT =
            "SELECT " +
                    "    COUNT(DISTINCT competition.competition_id) as count  " +
                    "FROM  " +
                    "    competition,  " +
                    "    competition_type,  " +
                    "    competitor,  " +
                    "    command,  " +
                    "    kind_of_sport  " +
                    "WHERE  " +
                    "    competitor.competition_id = competition.competition_id  " +
                    "        AND competitor.command_id = command.command_id  " +
                    "        AND competition.competition_type_id = competition_type.competition_type_id  " +
                    "        AND command.kind_of_sport_id = kind_of_sport.kind_of_sport_id  " +
                    "        AND competition_date_start > NOW()  " +
                    "        AND competition_is_active = ? ";



    public static final String FIND_ALL_NOW_GAMES =
            "SELECT  DISTINCT " +
                    "    competition.competition_id,  " +
                    "    competition_type_name,  " +
                    "    kind_of_sport_name,  " +
                    "    competition_total,  " +
                    "    competition_less_total_coeff,  " +
                    "    competition_more_total_coeff,  " +
                    "    competition_standoff_coeff,  " +
                    "    competition_date_start,  " +
                    "    competition_date_finish,  " +
                    "    competition_name  " +
                    "FROM  " +
                    "    competition,  " +
                    "    competition_type,  " +
                    "    competitor,  " +
                    "    command,  " +
                    "    kind_of_sport  " +
                    "WHERE  " +
                    "    competitor.competition_id = competition.competition_id  " +
                    "        AND competitor.command_id = command.command_id  " +
                    "        AND competition.competition_type_id = competition_type.competition_type_id  " +
                    "        AND command.kind_of_sport_id = kind_of_sport.kind_of_sport_id  " +
                    "        AND competition_date_start < NOW()  " +
                    "        AND competition_date_finish > NOW()  " +
                    "        AND competition_is_active = ?; ";

    public static final String FIND_PAST_GAMES_COUNT =
            "SELECT   " +
                    "    COUNT(DISTINCT competition.competition_id) as count  " +
                    "FROM  " +
                    "    competition,  " +
                    "    competition_type,  " +
                    "    competitor,  " +
                    "    command,  " +
                    "    kind_of_sport  " +
                    "WHERE  " +
                    "    competitor.competition_id = competition.competition_id  " +
                    "        AND competitor.command_id = command.command_id  " +
                    "        AND competition.competition_type_id = competition_type.competition_type_id  " +
                    "        AND command.kind_of_sport_id = kind_of_sport.kind_of_sport_id  " +
                    "        AND competition_date_finish < NOW()  " +
                    "        AND competition_is_result_filled = ?  " +
                    "        AND competition_is_active = ?; ";

    public static final String FIND_ALL_PAST_GAMES =
            "SELECT  DISTINCT " +
                    "    competition.competition_id,  " +
                    "    competition_type_name,  " +
                    "    kind_of_sport_name,  " +
                    "    competition_total,  " +
                    "    competition_less_total_coeff,  " +
                    "    competition_more_total_coeff,  " +
                    "    competition_standoff_coeff,  " +
                    "    competition_date_start,  " +
                    "    competition_date_finish,  " +
                    "    competition_name  " +
                    "FROM  " +
                    "    competition,  " +
                    "    competition_type,  " +
                    "    competitor,  " +
                    "    command,  " +
                    "    kind_of_sport  " +
                    "WHERE  " +
                    "    competitor.competition_id = competition.competition_id  " +
                    "        AND competitor.command_id = command.command_id  " +
                    "        AND competition.competition_type_id = competition_type.competition_type_id  " +
                    "        AND command.kind_of_sport_id = kind_of_sport.kind_of_sport_id  " +
                    "        AND competition_date_finish < NOW()  " +
                    "        AND competition_is_result_filled = ?  " +
                    "        AND competition_is_active = ?; ";


    public static final String FIND_LIMIT_PAST_GAMES =
            "SELECT  DISTINCT " +
                    "    competition.competition_id,  " +
                    "    competition_type_name,  " +
                    "    kind_of_sport_name,  " +
                    "    competition_total,  " +
                    "    competition_less_total_coeff,  " +
                    "    competition_more_total_coeff,  " +
                    "    competition_standoff_coeff,  " +
                    "    competition_date_start,  " +
                    "    competition_date_finish,  " +
                    "    competition_name  " +
                    "FROM  " +
                    "    competition,  " +
                    "    competition_type,  " +
                    "    competitor,  " +
                    "    command,  " +
                    "    kind_of_sport  " +
                    "WHERE  " +
                    "    competitor.competition_id = competition.competition_id  " +
                    "        AND competitor.command_id = command.command_id  " +
                    "        AND competition.competition_type_id = competition_type.competition_type_id  " +
                    "        AND command.kind_of_sport_id = kind_of_sport.kind_of_sport_id  " +
                    "        AND competition_date_finish < NOW()  " +
                    "        AND competition_is_result_filled = ?  " +
                    "        AND competition_is_active = ? " +
                    "ORDER BY competition.competition_date_finish " +
                    "LIMIT ?, ?;";

    public static final String FIND_USING_KINDS_OF_SPORT =
            "SELECT DISTINCT " +
            "    kind_of_sport_name, " +
            "    kind_of_sport.kind_of_sport_id, " +
            "    competition_type.competition_type_id, " +
            "    competition_type_name " +
            "FROM " +
            "    kind_of_sport " +
            "        LEFT JOIN " +
            "    command ON (kind_of_sport.kind_of_sport_id = command.kind_of_sport_id) " +
            "        LEFT JOIN " +
            "    competitor ON (command.command_id = competitor.command_id) " +
            "        LEFT JOIN " +
            "    competition ON (competitor.competition_id = competition.competition_id) " +
            "        LEFT JOIN " +
            "    competition_type ON (competition_type.competition_type_id = competition.competition_type_id) " +
            "WHERE " +
            "    (competition_date_start > NOW() OR (competition_date_finish < NOW() AND competition_is_result_filled)) " +
            "     AND competition.competition_is_active = TRUE;";
    

    public static final String FIND_ALL_KINDS_OF_SPORT =
            "SELECT " +
                    "    kind_of_sport.kind_of_sport_id, " +
                    "    kind_of_sport_name, " +
                    "    competitor_count " +
                    "FROM " +
                    "    kind_of_sport; ";

    public static final String FIND_KIND_OF_SPORT_BY_ID = 
                    "SELECT  " +
                    "    kind_of_sport_id, kind_of_sport_name, competitor_count " +
                    "FROM " +
                    "    kind_of_sport " +
                    "WHERE " +
                    "    kind_of_sport_id = ?;";

    public static final String UPDATE_KIND_OF_SPORT_BY_ID =
            "UPDATE `totalizatorr`.`kind_of_sport` " +
                    "SET `kind_of_sport_name`= ? " +
                    "WHERE `kind_of_sport_id`= ?; ";

    public static final String CREATE_KIND_OF_SPORT =
            "INSERT INTO `totalizatorr`.`kind_of_sport` (`kind_of_sport_name`, `competitor_count`) " +
                    "VALUES (?, ?);";

    public static final String DELETE_KIND_OF_SPORT_BY_ID =
            "DELETE FROM `totalizatorr`.`kind_of_sport` " +
                    "WHERE `kind_of_sport_id`= ?; ";


    public static final String FIND_NEWS_COMMENTS =
            "SELECT DISTINCT " +
                    "    comment_id, " +
                    "    comment_is_blocked, " +
                    "    comment_post_date, " +
                    "    comment_text, " +
                    "    user_avatar_url, " +
                    "    user_name " +
                    "FROM " +
                    "    comment, " +
                    "    user, " +
                    "    news " +
                    "WHERE " +
                    "    comment.user_id = user.user_id " +
                    "    AND comment.news_id = ?;";

    public static final String CREATE_COMMENT =
            "INSERT INTO comment (comment_text, comment_post_date, news_id, user_id) " +
                    "VALUES (? , now(), ?, ?);";

    public static final String CHANGE_LOCK_COMMENT =
            "UPDATE comment " +
                    "SET comment_is_blocked = ? " +
                    "WHERE comment_id = ?;";

    public static final String DELETE_COMMENT_BY_NEWS_ID =
            "DELETE FROM comment  " +
                    "WHERE " +
                    "    news_id = ?;";

    public static final String FIND_ALL_COMPETITION_TYPES =
            "SELECT competition_type_id, " +
                    "competition_type_name " +
                    "FROM competition_type;";

    public static final String FIND_COMPETITION_TYPE_BY_ID =
            "SELECT  " +
                    "    competition_type_id, competition_type_name " +
                    "FROM " +
                    "    competition_type " +
                    "WHERE " +
                    "    competition_type_id = ?;";

    public static final String CREATE_COMPETITION_TYPE =
            "INSERT INTO competition_type (competition_type_name) " +
                    "VALUES (?);";

    public static final String UPDATE_COMPETITION_TYPE =
            "UPDATE competition_type " +
                    "SET competition_type_name = ? " +
                    "WHERE competition_type_id = ?;";

    public static final String DELETE_COMPETITION_TYPE =
            "DELETE FROM competition_type " +
                    "WHERE competition_type_id = ?;";

    public static final String FIND_ALL_COMPETITORS_WITH_COMMAND_BY_GAME_ID =
            "    SELECT   " +
                    "          competitor.competitor_id,   " +
                    "          command_name,   " +
                    "          competitor_result,   " +
                    "          competitor_is_win,   " +
                    "          competitor_win_coeff  " +
                    "    FROM  " +
                    "          competitor,  " +
                    "          command, " +
                    "          competition " +
                    "    WHERE   " +
                    "          competitor.command_id = command.command_id " +
                    "          and command.command_id != 0  " +
                    "          and competitor.competition_id = competition.competition_id " +
                    "          and competition.competition_id = ?; ";

    public static final String FIND_ALL_COMPETITORS_GAME_ID =
                    "    SELECT  " +
                            "    competitor.competitor_id, " +
                            "    competitor.competition_id, " +
                            "    competitor.command_id, " +
                            "    competitor_result, " +
                            "    competitor_is_win, " +
                            "    competitor_win_coeff " +
                            "FROM " +
                            "    competitor " +
                            "        LEFT JOIN " +
                            "    competition ON (competitor.competition_id = competition.competition_id) " +
                            "WHERE " +
                            "    competition.competition_id = ?;";


    public static final String COUNT_BETS_ON_COMPETITION =
                    "SELECT  " +
                            "    COUNT(DISTINCT bet_id) AS count " +
                            "FROM " +
                            "    bet " +
                            "        LEFT JOIN " +
                            "    competitor ON (bet.competitor_id = competitor.competitor_id) " +
                            "WHERE " +
                            "    competition_id = ? " +
                            "        AND bet_total = ? " +
                            "        AND competitor.command_id = 0;";



    public static final String COUNT_BETS_ON_COMPETITOR =
            "SELECT  " +
                    "    COUNT(DISTINCT bet_id) AS count " +
                    "FROM " +
                    "    bet, " +
                    "    competitor " +
                    "WHERE " +
                    "    bet.competitor_id = ? " +
                    "        AND command_id != 0";

    public static final String AMOUNT_OF_MONEY_ON_COMPETITION =
                    "SELECT  " +
                            "    SUM(bet_cash) AS cash " +
                            "FROM " +
                            "    bet left join competitor on (bet.competitor_id = competitor.competitor_id) " +
                            "WHERE " +
                            "     competitor.competition_id = ? " +
                            "        AND bet_total = ? " +
                            "        AND competitor.command_id = 0;";
    

    public static final String AMOUNT_OF_MONEY_ON_COMPETITOR =
                    "SELECT  " +
                    "    SUM(bet_cash) as cash " +
                    "FROM " +
                    "    competitor, " +
                    "    bet " +
                    "WHERE " +
                    "    bet.competitor_id = ? " +
                    "        AND competitor.competitor_id = bet.competitor_id " +
                    "        AND competitor.command_id != 0;";


    public static final String SELECT_ADMIN_STATISTIC =
            "SELECT " +
                    "(SELECT COUNT(user_id) FROM user) AS countRegistered, " +
                    "(SELECT COUNT(user_id) FROM user WHERE user_is_confirm = 0) AS countConfirmed, " +
                    "(SELECT COUNT(user_id) FROM user WHERE user_is_blocked) AS countLocked, " +
                    "(SELECT COUNT(news_id) FROM news) AS countNews, " +
                    "(SELECT COUNT(kind_of_sport_id) FROM kind_of_sport) AS countSports; ";

    public static final String INSERT_COMPETITION =
            "INSERT INTO `totalizatorr`.`competition` " +
                    "(`competition_name`, " +
                    "`competition_description`, " +
                    "`competition_date_start`, " +
                    "`competition_date_finish`, " +
                    "`competition_is_active`, " +
                    "`competition_type_id`, " +
                    "`competition_total`, " +
                    "`competition_more_total_coeff`, " +
                    "`competition_less_total_coeff`, " +
                    "`competition_standoff_coeff`) " +

                    "VALUES (?, '', ?, ?, ?, ?, ?, ?, ?, ?);";

    public static final String INSERT_COMPETITOR =
            "INSERT INTO `totalizatorr`.`competitor` " +
                    "(`command_id`, " +
                    "`competition_id`, " +
                    "`competitor_win_coeff`) " +
                    "VALUES (?, ?, ?);";

    public static final String UPDATE_UPCOMING_ACTIVATED_COMPETITION_COEFFS =
            "UPDATE competition  " +
                    "SET  " +
                    "    competition_more_total_coeff = ?, " +
                    "    competition_less_total_coeff = ?, " +
                    "    competition_standoff_coeff = ? " +
                    "WHERE " +
                    "    competition_id = ?;";

    public static final String UPDATE_UPCOMING_DEACTIVATED_COMPETITION_COEFFS =
            "UPDATE competition  " +
                    "SET  " +
                    "    competition_more_total_coeff = ?, " +
                    "    competition_less_total_coeff = ?, " +
                    "    competition_standoff_coeff = ?, " +
                    "    competition_total = ? " +
                    "WHERE " +
                    "    competition_id = ?;";

    public static final String UPDATE_COMPETITION_NAME =
            "UPDATE competition  " +
                    "SET  " +
                    "    competition_name = ? " +
                    "WHERE " +
                    "    competition_id = ?;";

    public static final String UPDATE_COMPETITOR_COEFFS =
            "UPDATE competitor " +
                    "SET  " +
                    "    competitor_win_coeff = ? " +
                    "WHERE " +
                    "    competitor_id = ?;";

    public static final String UPDATE_COMPETITOR_RESULT =
            "UPDATE competitor " +
                    "SET  " +
                    "    competitor_is_win = ?, " +
                    "    competitor_result = ? " +
                    "WHERE " +
                    "    competitor_id = ?;";

    public static final String FIND_COMPETITOR_IDS_BY_COMPETITION_ID =
            "SELECT  " +
                    "    competitor_id " +
                    "FROM " +
                    "    competitor " +
                    "WHERE " +
                    "    competitor.competition_id = ?;";

    public static final String RETURN_MONEY_FOR_BETS =
            "UPDATE  " +
                    "    user, " +
                    "    bet, " +
                    "    competition, " +
                    "    competitor  " +
                    "SET  " +
                    "    user_cash = user_cash + bet_cash " +
                    "WHERE " +
                    "    user.user_id = bet.user_id " +
                    "        AND bet.competitor_id = competitor.competitor_id " +
                    "        AND competitor.competition_id = competition.competition_id " +
                    "        AND competition.competition_id = ?;";

    public static final String DELETE_BETS_BY_COMPETITION_ID =
            "DELETE bet FROM bet, " +
                    "    competition, " +
                    "    competitor  " +
                    "WHERE " +
                    "    competition.competition_id = ? " +
                    "    AND competition.competition_id = competitor.competition_id " +
                    "    AND competitor.competitor_id = bet.competitor_id;";

    public static final String CREATE_BET =
            "INSERT " +
                    "INTO bet " +
                    "       (user_id, " +
                    "        bet_cash, " +
                    "        competitor_id," +
                    "        bet_total) " +
                    "VALUES (?, ?, ?, ?);";


    public static final String DELETE_COMPETITORS_BY_COMPETITION_ID =
            "DELETE competitor FROM competition, " +
                    "    competitor  " +
                    "WHERE " +
                    "    competition.competition_id = ? " +
                    "    AND competition.competition_id = competitor.competition_id;";

    public static final String DELETE_COMPETITION_BY_ID =
            "DELETE FROM competition  " +
                    "WHERE " +
                    "    competition_id = ?;";

    public static final String CHANGE_COMPETITION_ACTIVE_STATE =
            "UPDATE competition  " +
                    "SET  " +
                    "    competition_is_active = ? " +
                    "WHERE " +
                    "    competition_id = ?;";

    public static final String CHANGE_COMPETITION_RESULT_FILL_STATE =
            "UPDATE competition  " +
                    "SET  " +
                    "    competition_is_result_filled = ? " +
                    "WHERE " +
                    "    competition_id = ?;";


    public static final String UPDATE_COMPETITOR_RESULT_AND_PAY_MONEY =
            "UPDATE user, " +
                    "    bet, " +
                    "    competitor  " +
                    "SET  " +
                    "    user.user_cash = IF(competitor_is_win = TRUE, " +
                    "        user_cash + bet_cash * competitor_win_coeff, user_cash), " +
                    "    bet.bet_is_win = competitor_is_win " +
                    "WHERE " +
                    "    competitor.competition_id = ? " +
                    "        AND competitor.command_id != 0 " +
                    "        AND competitor.competitor_id = bet.competitor_id " +
                    "        AND bet.user_id = user.user_id;";

    public static final String SET_VAR_FIRST_COMPETITOR_RESULT = "SET @competitor1 = ?;";

    public static final String SET_VAR_SECOND_COMPETITOR_RESULT = "SET @competitor2 = ?;";

    public static final String UPDATE_BET_RESULT_AND_PAY_MONEY =
            "        UPDATE user,  " +
                    "            bet,  " +
                    "            competitor,  " +
                    "            competition   " +
                    "        SET   " +
                    "            user.user_cash =  " +
                    "            CASE  " +
                    "                WHEN bet.bet_total = 'LESS' AND competition_total < (@competitor1 + @competitor2)  " +
                    "                      THEN user_cash + (bet_cash * competition_less_total_coeff)  " +
                    "                WHEN bet.bet_total = 'MORE' AND competition_total > (@competitor1 + @competitor2)  " +
                    "                      THEN user_cash + (bet_cash * competition_more_total_coeff)  " +
                    "                WHEN bet.bet_total = 'EQUALS' AND @competitor1 = @competitor2  " +
                    "                      THEN user_cash + (bet_cash * competition_standoff_coeff)  " +
                    "                ELSE user_cash  " +
                    "            END,  " +
                    "            bet.bet_is_win =  " +
                    "            CASE  " +
                    "                WHEN bet.bet_total = 'LESS' AND competition_total < (@competitor1 + @competitor2)   " +
                    "                   THEN TRUE  " +
                    "                WHEN bet.bet_total = 'MORE' AND competition_total > (@competitor1 + @competitor2)   " +
                    "                   THEN TRUE  " +
                    "                WHEN bet.bet_total = 'EQUALS' AND @competitor1 = @competitor2   " +
                    "                   THEN TRUE  " +
                    "                ELSE FALSE  " +
                    "            END  " +
                    "        WHERE  " +
                    "            competition.competition_id = ?  " +
                    "                AND competitor.competition_id = competition.competition_id  " +
                    "                AND competitor.command_id = 0  " +
                    "                AND competitor.competitor_id = bet.competitor_id  " +
                    "                AND bet.user_id = user.user_id;";
}


        