package by.epam.totalizator.filter;

import by.epam.totalizator.constant.GeneralConstant;
import by.epam.totalizator.entity.UserEntity;
import by.epam.totalizator.type.PageType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "userAccessFilter",
        urlPatterns = {"/jsp/user/profile.jsp", "/jsp/user/change_avatar.jsp", "/jsp/user/change_password.jsp"},
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class UserAccessFilter implements Filter {
    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        UserEntity user = (UserEntity) httpRequest.getSession().getAttribute(GeneralConstant.USER);

        if (user == null) {
            httpResponse.sendRedirect(PageType.INDEX.getPage());
            return;
        }

        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}