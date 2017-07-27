package by.epam.litvin.tag;

import by.epam.litvin.util.Paginator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

@SuppressWarnings("serial")
public class PaginationTag extends TagSupport {

    /**
     * Logger to write logs.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * The name of the command to correctly form the request
     */
    private String command;

    /**
     * Total count of entities.
     */
    private int total;

    /**
     * Limit to show entities on page.
     */
    private int limit;

    /**
     * Set total count of all entities.
     *
     * @param total total elements which need paginating
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * Set limit to show entities on page.
     *
     * @param limit limit elements to contain one page
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     *  Set the name of the command to correctly form the request
     *
     * @param command The name of the command to correctly form the request
     */
    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public int doStartTag() throws JspException {
        if(limit >= total) {
            return SKIP_BODY;
        }

        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        Paginator paginator = new Paginator(limit, total, command);

        String pageParam = request.getParameter("pageNumber");

        try {
            if (pageParam != null) {
                int page = Integer.valueOf(pageParam);
                paginator.setPageNumber(page);
            }

            JspWriter out = pageContext.getOut();
            out.write(paginator.generate());

        } catch (IOException e) {
            throw new JspException("Write output error", e);
        }

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
