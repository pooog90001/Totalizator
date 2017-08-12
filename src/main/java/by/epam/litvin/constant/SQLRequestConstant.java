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

    public static final String FIND_LIVE_GAMES_COUNT = 
                    "SELECT  " +
                    "    COUNT(DISTINCT competition.competition_id) as count " +
                    "FROM " +
                    "    competition, " +
                    "    competitor, " +
                    "    command, " +
                    "    kind_of_sport " +
                    "WHERE " +
                    "    competitor.competition_id = competition.competition_id " +
                    "        AND competitor.command_id = command.command_id " +
                    "        AND command.kind_of_sport_id = kind_of_sport.kind_of_sport_id " +
                    "        AND kind_of_sport.competitor_count = 2 " +
                    "        AND competition_date_start < NOW() " +
                    "        AND competition_date_finish IS NULL " +
                    "        AND competition_is_active = TRUE";

    public static final String FIND_LIMIT_LIVE_GAMES = 
                    "SELECT  " +
                    "    competition.competition_id, " +
                    "    kind_of_sport_name, " +
                    "    command_name, " +
                    "    competition_total, " +
                    "    competition_name, " +
                    "    competition_less_total_coeff, " +
                    "    competition_more_total_coeff, " +
                    "    competition_standoff_coeff," +
                    "    competitor_id, " +
                    "    competitor_win_coeff, " +
                    "    competition_name " +
                    "FROM " +
                    "    competition, " +
                    "    competitor, " +
                    "    command, " +
                    "    kind_of_sport " +
                    "WHERE " +
                    "        competitor.competition_id = competition.competition_id " +
                    "        AND competitor.command_id = command.command_id " +
                    "        AND command.kind_of_sport_id = kind_of_sport.kind_of_sport_id " +
                    "        AND kind_of_sport.competitor_count = 2 " +
                    "        AND competition_date_start < NOW() " +
                    "        AND competition_date_finish IS NULL " +
                    "        AND competition_is_active = TRUE " +
                    "LIMIT ?, ?;";

    public static final String FIND_ALL_LIVE_GAMES =
            "SELECT  " +
                    "    competition.competition_id, " +
                    "    kind_of_sport_name, " +
                    "    command_name, " +
                    "    competition_total, " +
                    "    competition_name, " +
                    "    competition_less_total_coeff, " +
                    "    competition_more_total_coeff, " +
                    "    competition_standoff_coeff," +
                    "    competitor_id, " +
                    "    competitor_win_coeff, " +
                    "    competition_name " +
                    "FROM " +
                    "    competition, " +
                    "    competitor, " +
                    "    command, " +
                    "    kind_of_sport " +
                    "WHERE " +
                    "        competitor.competition_id = competition.competition_id " +
                    "        AND competitor.command_id = command.command_id " +
                    "        AND command.kind_of_sport_id = kind_of_sport.kind_of_sport_id " +
                    "        AND kind_of_sport.competitor_count = 2 " +
                    "        AND competition_date_start < NOW() " +
                    "        AND competition_date_finish IS NULL " +
                    "        AND competition_is_active = TRUE ;";

    public static final String FIND_FILTERED_LIVE_GAMES =
            "SELECT  " +
                    "    competition.competition_id, " +
                    "    kind_of_sport_name, " +
                    "    command_name, " +
                    "    competition_total, " +
                    "    competition_name, " +
                    "    competition_less_total_coeff, " +
                    "    competition_more_total_coeff, " +
                    "    competition_standoff_coeff," +
                    "    competitor_id, " +
                    "    competitor_win_coeff, " +
                    "    competition_name " +
                    "FROM " +
                    "    competition, " +
                    "    competitor, " +
                    "    command, " +
                    "    kind_of_sport " +
                    "WHERE " +
                    "        competitor.competition_id = competition.competition_id " +
                    "        AND competitor.command_id = command.command_id " +
                    "        AND command.kind_of_sport_id = kind_of_sport.kind_of_sport_id " +
                    "        AND kind_of_sport.competitor_count = 2 " +
                    "        AND competition_date_start < NOW() " +
                    "        AND competition_date_finish IS NULL " +
                    "        AND competition_is_active = TRUE " +
                    "        AND kind_of_sport.kind_of_sport_id = ?;";

    public static final String FIND_LIMIT_UPCOMING_GAMES =
                    "SELECT  " +
                    "    competition.competition_id, " +
                    "    kind_of_sport_name, " +
                    "    command_name, " +
                    "    competition_total, " +
                    "    competition_less_total_coeff, " +
                    "    competition_more_total_coeff, " +
                    "    competition_standoff_coeff, " +
                    "    competitor_win_coeff, " +
                    "    competitor_id, " +
                    "    competition_date_start, " +
                    "    competition_name " +
                    "FROM " +
                    "    competition, " +
                    "    competitor, " +
                    "    command, " +
                    "    kind_of_sport " +
                    "WHERE " +
                    "    competitor.competition_id = competition.competition_id " +
                    "        AND competitor.command_id = command.command_id " +
                    "        AND command.kind_of_sport_id = kind_of_sport.kind_of_sport_id " +
                    "        AND kind_of_sport.competitor_count = 2 " +
                    "        AND competition_date_start > NOW() " +
                    "        AND competition_is_active = TRUE " +
                    "LIMIT ?, ?;";

    public static final String FIND_ACTIVE_UPCOMING_GAMES_FOR_SETTINGS =
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
                    "        AND competition_is_active = TRUE ";


    public static final String FIND_LIMIT_PAST_GAMES =
                    "SELECT  " +
                    "    competition.competition_id, " +
                    "    competition_date_start, " +
                    "    competition_name, " +
                    "    kind_of_sport_name, " +
                    "    command_name, " +
                    "    competitor_result " +
                    "FROM " +
                    "    competition, " +
                    "    competitor, " +
                    "    command, " +
                    "    kind_of_sport " +
                    "WHERE " +
                    "    competitor.competition_id = competition.competition_id " +
                    "        AND competitor.command_id = command.command_id " +
                    "        AND command.kind_of_sport_id = kind_of_sport.kind_of_sport_id " +
                    "        AND kind_of_sport.competitor_count = 2 " +
                    "        AND competition_date_finish IS NOT NULL " +
                    "        AND competition_date_finish < NOW() " +
                    "        AND competition_is_active = FALSE " +
                            "ORDER BY competition.competition_id DESC " +
                    "LIMIT ?, ?;";
    
    public static final String FIND_USING_KINDS_OF_SPORT =
                    "SELECT DISTINCT " +
                    "    kind_of_sport_name,  " +
                    "    competition_type.competition_type_id, " +
                    "    competition_type_name " +
                    "FROM " +
                    "    kind_of_sport, " +
                    "    competition_type, " +
                    "    command, " +
                    "    competitor, " +
                    "    competition " +
                    "WHERE " +
                    "    competition_type.competition_type_id = competition.competition_type_id " +
                    "        AND competitor.competition_id = competition.competition_id " +
                    "        AND command.command_id = competitor.command_id " +
                    "        AND kind_of_sport.kind_of_sport_id = command.kind_of_sport_id;";

    public static final String FIND_LIVE_GAMES_KINDS_OF_SPORT =
                    "SELECT DISTINCT " +
                    "    kind_of_sport.kind_of_sport_id, " +
                    "    kind_of_sport_name, " +
                    "    competitor_count " +
                    "FROM " +
                    "    competition, " +
                    "    competitor, " +
                    "    command, " +
                    "    kind_of_sport " +
                    "WHERE " +
                    "        competitor.competition_id = competition.competition_id " +
                    "        AND competitor.command_id = command.command_id " +
                    "        AND command.kind_of_sport_id = kind_of_sport.kind_of_sport_id " +
                    "        AND kind_of_sport.competitor_count = 2 " +
                    "        AND competition_date_start < NOW() " +
                    "        AND competition_date_finish IS NULL " +
                    "        AND competition_is_active = TRUE;";

    public static final String FIND_ALL_KINDS_OF_SPORT =
                    "SELECT " +
                    "    kind_of_sport.kind_of_sport_id, " +
                    "    kind_of_sport_name, " +
                    "    competitor_count " +
                    "FROM " +
                    "    kind_of_sport; ";

    public static final String UPDATE_KIND_OF_SPORT_BY_ID =
            "UPDATE `totalizatorr`.`kind_of_sport` " +
                    "SET `kind_of_sport_name`= ? " +
                    "WHERE `kind_of_sport_id`= ?; ";
    
    public static final String CREATE_KIND_OF_SPORT = 
            "INSERT INTO `totalizatorr`.`kind_of_sport` (`kind_of_sport_name`, `competitor_count`) " +
                    "VALUES (?, ?);";

    public static final String DELETE_KIND_OF_SPORT_BY_ID =
            "DELETE FROM `totalizatorr`.`kind_of_sport` " +
                    "WHERE `kind_of_sport_id`= ?;\n";


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

    public static final String FIND_ALL_COMPETITION_TYPES =
            "SELECT competition_type_id, " +
                    "competition_type_name " +
            "FROM competition_type;";

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

    public static final String FIND_ALL_COMPETITORS_WITH_COMMAND_BY_COMPETITION_ID =
            "    SELECT   " +
            "          competitor.competitor_id,   " +
            "          command_name,   " +
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

    public static final String COUNT_BETS_ON_COMPETITION =
            "SELECT  " +
                    "    COUNT(DISTINCT bet_id) AS count " +
                    "FROM " +
                    "    bet, " +
                    "    competitor " +
                    "WHERE " +
                    "    bet.competitor_id = ? " +
                    "        AND bet_total = ? " +
                    "        AND command_id = 0";

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
                    "    SUM(bet_cash) as cash " +
                    "FROM " +
                    "    competitor, " +
                    "    competition, " +
                    "    bet " +
                    "WHERE " +
                    "    competition.competition_id = ? " +
                    "        AND competitor.competition_id = competition.competition_id " +
                    "        AND competitor.competitor_id = bet.competitor_id " +
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

    public static final String UPDATE_COMPETITION_COEFFS =
                    "UPDATE competition  " +
                    "SET  " +
                    "    competition_more_total_coeff = ?, " +
                    "    competition_less_total_coeff = ?, " +
                    "    competition_standoff_coeff = ?" +
                    "WHERE " +
                    "    competition_id = ?;";

    public static final String UPDATE_COMPETITOR_COEFFS =
                    "UPDATE competitor " +
                    "SET  " +
                    "    competitor_win_coeff = ? " +
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
}
