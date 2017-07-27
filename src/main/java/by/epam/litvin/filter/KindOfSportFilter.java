package by.epam.litvin.filter;

import by.epam.litvin.constant.PageConstant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = { "/jsp/*" },
        initParams = {
                @WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param") })
public class KindOfSportFilter implements Filter {


    public void init(FilterConfig fConfig) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        /*HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;

        if (servletRequest.getSession().getAttribute("kindsOfSport") == null) {
            servletResponse.sendRedirect(PageConstant.INDEX);
        }*/

        chain.doFilter(request, response);
    }

    public void destroy() {}
}
