package by.epam.totalizator.filter;

import by.epam.totalizator.bean.UserEntity;
import by.epam.totalizator.constant.GeneralConstant;
import by.epam.totalizator.type.PageType;
import by.epam.totalizator.validator.impl.UserValidatorImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AdminAccessFilter", urlPatterns = "/jsp/admin_panel/*",
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class AdminAccessFilter implements Filter {
    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        UserValidatorImpl validator = new UserValidatorImpl();
        UserEntity user = (UserEntity) httpRequest.getSession().getAttribute(GeneralConstant.USER);

        if (!validator.isAdmin(user) && !validator.isBookmaker(user)) {
            httpResponse.sendRedirect(PageType.INDEX.getPage());
            return;
        }

        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
