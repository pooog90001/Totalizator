package by.epam.litvin.filter;

import by.epam.litvin.bean.UserEntity;
import by.epam.litvin.type.PageType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.litvin.constant.GeneralConstant.USER;

@WebFilter(filterName = "userAccessFilter",
        urlPatterns = {"/jsp/user/profile.jsp", "/jsp/user/change_avatar.jsp", "/jsp/user/change_password.jsp"},
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class UserAccessFilter implements Filter {
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

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        UserEntity user = (UserEntity) httpRequest.getSession().getAttribute(USER);

        if (user == null) {
            httpResponse.sendRedirect(PageType.INDEX.getPage());
            return;
        }

        chain.doFilter(request, response);
    }

    /**
     * Destroy method.
     */
    public void destroy() {
    }
}