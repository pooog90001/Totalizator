package by.epam.litvin.filter;

import by.epam.litvin.bean.UserEntity;
import by.epam.litvin.constant.PageConstant;
import by.epam.litvin.validator.UserValidator;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.litvin.constant.GeneralConstant.USER;

@WebFilter(urlPatterns = { "/jsp/admin_panel/*" })
public class AccessFIlter implements Filter {
    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponce = (HttpServletResponse) response;
        UserValidator validator = new UserValidator();
        UserEntity user = (UserEntity) httpRequest.getSession().getAttribute(USER);

        if (!validator.isAdmin(user) && !validator.isBookmaker(user)) {
            httpResponce.sendRedirect(PageConstant.INDEX);
        }

        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}