package by.epam.litvin.tag;

import by.epam.litvin.bean.User;
import by.epam.litvin.constant.RequestNameConstant;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

@SuppressWarnings("serial")
public class CollapseBarProfileTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger();


    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute(RequestNameConstant.USER);
        StringBuilder outHtml = new StringBuilder();

        if (currentUser != null) {
            outHtml.append("<div class='w3-row w3-padding'>");
            outHtml.append("    <div class='w3-col s8'>");
            outHtml.append("        <div class='w3-row w3-right-align'>");
            outHtml.append("            <div class='w3-col s12'>");
            outHtml.append("                <div class='w3-container'>");
            outHtml.append("                    <p class='w3-padding-small'>" + currentUser.getName() + "</p>");
            outHtml.append("                </div>");
            outHtml.append("            </div>");
            outHtml.append("            <div class='w3-col s12'>");
            outHtml.append("                <div class='w3-container'>");
            outHtml.append("                    <p class='w3-padding-small'>" + currentUser.getCash() + "</p>");
            outHtml.append("                </div>");
            outHtml.append("            </div>");
            outHtml.append("        </div>");
            outHtml.append("    </div>");
            outHtml.append("    <div class='w3-col s4'>");
            outHtml.append("        <img style='width:80px' class='w3-circle'");
            outHtml.append("                src='" + currentUser.getAvatarURL() + "'/>");
            outHtml.append("    </div>");
            outHtml.append("</div>");

        } else {
           outHtml.append(" <div class='w3-container w3-cell'>");
           outHtml.append("          <a href='" + request.getContextPath() + "/jsp/sign_in.jsp' class='w3-hover-text-yellow w3-center'>");
           outHtml.append("              <p class='w3-center'><fmt:message key='lbl.SignIn' bundle='${rb}'/></p>");
           outHtml.append("          </a>");
           outHtml.append("      </div>");

           outHtml.append("      <div class='w3-container w3-cell'>");
           outHtml.append("          <a href='" + request.getContextPath() + "/jsp/sign_up.jsp' class='w3-hover-text-yellow'>");
           outHtml.append("              <p class='w3-center'><fmt:message key='lbl.SignUp' bundle='${rb}'/></p>");
           outHtml.append("          </a>");
           outHtml.append("      </div>");
        }

        try {

            JspWriter writer = pageContext.getOut();
            writer.write(outHtml.toString());

        } catch (IOException e) {
            LOGGER.log(Level.ERROR,"Can't show tag", e);
        }

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
