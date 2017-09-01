package by.epam.totalizator.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "encodingFilter",
        urlPatterns = {"/generalController", "/ajaxController", "/uploadController"},
        initParams = {
                @WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param"),
                @WebInitParam(name = "contentType", value = "text/html", description = "Content type Param") })
public class EncodingFilter implements Filter {
    private String code;
    private String contentType;


    public void init(FilterConfig fConfig) throws ServletException {
        code = fConfig.getInitParameter("encoding");
        contentType = fConfig.getInitParameter("contentType");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String codeRequest = request.getCharacterEncoding();
        String contentTypeRequest = request.getContentType();

        if (contentType != null && !contentType.equalsIgnoreCase(contentTypeRequest)) {
            response.setContentType(contentType);
        }

        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
        code = null;
        contentType = null;
    }
}

