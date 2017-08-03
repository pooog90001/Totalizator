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
                    "SELECT command_id,command_name, kind_of_sport_id " +
                    "FROM command;";

    public static final String FIND_ALL_COMMAND_WITH_KIND_OF_SPORT =
                    "SELECT command_id, " +
                    "       command_name, " +
                    "       command.kind_of_sport_id, " +
                    "       kind_of_sport_name " +
                    "FROM  command, " +
                    "       kind_of_sport " +
                    "WHERE command.kind_of_sport_id = kind_of_sport.kind_of_sport_id;";

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
                    "        AND competition_date_finish IS NULL " +
                    "        AND competition_is_active = TRUE " +
                    "LIMIT ?, ?;";

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

}
