package by.epam.litvin.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static by.epam.litvin.constant.GeneralConstant.TEMPORARY;

@WebFilter(urlPatterns = { "/generalController", "/ajaxController" },
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class TemporaryDataFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        ((HttpServletRequest) request).getSession().removeAttribute(TEMPORARY);
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
