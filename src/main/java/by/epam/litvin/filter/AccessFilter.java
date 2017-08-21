package by.epam.litvin.filter;

import by.epam.litvin.bean.UserEntity;
import by.epam.litvin.constant.PageConstant;
import by.epam.litvin.validator.impl.UserValidatorImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.litvin.constant.GeneralConstant.USER;

@WebFilter(urlPatterns = "/jsp/admin_panel/*",
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class AccessFilter implements Filter {
    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        UserValidatorImpl validator = new UserValidatorImpl();
        UserEntity user = (UserEntity) httpRequest.getSession().getAttribute(USER);

        if (!validator.isAdmin(user) && !validator.isBookmaker(user)) {
            httpResponse.sendRedirect(PageConstant.INDEX);
        }

        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
