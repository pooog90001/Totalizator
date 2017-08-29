package by.epam.litvin.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static by.epam.litvin.constant.GeneralConstant.TEMPORARY;

@WebFilter(filterName = "temporaryDataFilter",
        urlPatterns = {"/generalController", "/ajaxController", "/uploadController"},
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class TemporaryDataFilter implements Filter {

    /**
     * Initiate method.
     *
     * @param fConfig
     * @throws ServletException
     */
    public void init(FilterConfig fConfig) throws ServletException {
    }

    /**
     * Do filter.
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        ((HttpServletRequest) request).getSession().removeAttribute(TEMPORARY);
        chain.doFilter(request, response);
    }

    /**
     * Destroy method.
     */
    public void destroy() {
    }
}
