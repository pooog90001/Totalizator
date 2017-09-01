package by.epam.totalizator.filter;

import by.epam.totalizator.type.PageType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static javax.servlet.jsp.PageContext.SESSION;

@WebFilter(filterName = "sessionFilter", urlPatterns = {"/jsp/*"},
        dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class SessionFilter implements Filter {
    private static final int SESSION_LIFE_TIME = 1000 * 60;

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();


        if (session.isNew()) {
            session.setMaxInactiveInterval(SESSION_LIFE_TIME);
        }

        if (session.getAttribute(SESSION) == null) {

            session.setAttribute(SESSION, true);
            httpResponse.sendRedirect(PageType.INDEX.getPage());
            return;
        }


        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
