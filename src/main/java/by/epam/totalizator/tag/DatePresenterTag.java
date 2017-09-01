package by.epam.totalizator.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePresenterTag extends TagSupport {
    public static final String DATE_FORMAT_PATTERN = "dd.MM.yyyy HH:mm";

    /**
     * Date date operand
     */
    private Date date;


    /**
     *  Setter on private field date
     * @param date Date number operand
     */
    public void setDate(Date date) {
        this.date = date;
    }



    @Override
    public int doStartTag() throws JspException {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_PATTERN);

        JspWriter out = pageContext.getOut();

        try {
            if (date == null) {
                date = new Date(0);
            }
            out.write(dateFormat.format(date));

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