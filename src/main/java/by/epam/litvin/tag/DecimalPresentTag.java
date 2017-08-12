package by.epam.litvin.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DecimalStyle;

public class DecimalPresentTag extends TagSupport {

    /**
     * BigDecimal number operand
     */
    private BigDecimal number;

    /**
     * Number of fractional characters
     */
    private int scale = 2;


    /**
     *  Setter on private field number
     * @param number BigDecimal number operand
     */
    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    /**
     * Setter on private field round
     * @param scale number of fractional characters
     */
    public void setScale(int scale) {
        this.scale = scale;
    }

    @Override
    public int doStartTag() throws JspException {
        BigDecimal scaledNumber;
        if (number != null) {
            scaledNumber = number.setScale(scale, BigDecimal.ROUND_HALF_EVEN);

        } else {
            scaledNumber = new BigDecimal("0").setScale(scale, BigDecimal.ROUND_HALF_EVEN);
        }

        JspWriter out = pageContext.getOut();

        try {
            out.write(scaledNumber.toString());

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
