package by.epam.litvin.constant;

final public class PageConstant {
    private PageConstant() {}

    private static final String ROOT = "jsp/";

    public static final String MAIN = ROOT + "main.jsp";
    public static final String SIGN_UP = ROOT + "sign_up.jsp";
    public static final String SIGN_IN = ROOT + "sign_in.jsp";
    public static final String CONFIRM = ROOT + "confirm.jsp";
    public static final String ALL_NEWS = ROOT + "all_news.jsp";
    public static final String CONCRETE_NEWS = ROOT + "concrete_news.jsp";
    public static final String INDEX = "index.jsp";
    //Error pages
    private static final String ERROR_ROOT = "jsp/error/";

    public static final String ERROR_404 = ERROR_ROOT + "404.jsp";
    public static final String ERROR_500 = ERROR_ROOT + "500.jsp";
    public static final String ERROR_RUNTIME = ERROR_ROOT + "runtime.jsp";
}
