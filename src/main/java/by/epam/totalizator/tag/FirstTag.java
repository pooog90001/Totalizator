package by.epam.totalizator.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;

@SuppressWarnings("serial")
public class FirstTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        String time = "<hr/>Time : <b> it's tag by Litvin Vlad </b><hr/>";
        String locale = "Locale : <b> " + Locale.getDefault() + " </b><hr/> ";

        try {
            JspWriter out = pageContext.getOut();
            out.write(time + locale);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;

    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
