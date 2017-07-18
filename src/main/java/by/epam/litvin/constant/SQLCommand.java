package by.epam.litvin.constant;

public class SQLCommand {
    private SQLCommand(){}

    public static final String  CREATE_USER =
            "INSERT INTO `user` (`user_name`, `user_email`, `user_password`, `user_confirm_url`) " +
                    "VALUES (?, ?, ?, ?);";
    public static final String  FIND_USER =
            "SELECT `user_name` , `user_email`, `user_password`, `user_confirm_url`, `user_is_blocked` , `user_is_confirm`, `user_cash`" +
                    "FROM `user`" +
                    "WHERE `user_email` = ? AND `user_password` = ?;";

}
