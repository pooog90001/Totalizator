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
public class BarProfileTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute(RequestNameConstant.USER);
        StringBuilder outHtml = new StringBuilder();

        if (currentUser != null) {
            outHtml.append("<div class='w3-bar-item w3-dropdown-hover w3-right w3-hide-small' style='padding: 0'>");
            outHtml.append("    <a href='#'' class='w3-button w3-padding-small'>");
            outHtml.append("        <div class='w3-row' style='max-width: 200px'>");
            outHtml.append("            <div class='w3-col s8'>");
            outHtml.append("                         <div class='w3-row w3-right-align w3-small'>");
            outHtml.append("                      <div class='w3-col s12'>");
            outHtml.append("                          <p class='w3-padding-small'>" + currentUser.getName() + "</p>");
            outHtml.append("                      </div>");
            outHtml.append("                      <div class='w3-col s12'>");
            outHtml.append("                          <p class='w3-padding-small'>" + currentUser.getCash() + "$</p>");
            outHtml.append("                      </div>");
            outHtml.append("                  </div>");
            outHtml.append("              </div>");
            outHtml.append("              <div class='w3-col s4'>");
            outHtml.append("                  <img style='width:60px' class='w3-circle''");
            outHtml.append("  src='" + currentUser.getAvatarURL() + "'/>");
            outHtml.append("              </div>");
            outHtml.append("          </div>");
            outHtml.append("      </a>");
            outHtml.append("      <div class='w3-dropdown-content w3-bar-block w3-border'>");
            outHtml.append("          <a href='#' class='w3-bar-item w3-button'>Profile </a>");
            outHtml.append("          <a href='#' class='w3-bar-item w3-button'>Settings</a>");
            outHtml.append("          <a href='#' class='w3-bar-item w3-button'>Sign out</a>");
            outHtml.append("      </div>");
            outHtml.append("  </div>");


        } else {
           outHtml.append(" <div class='w3-bar-item w3-hide-small w3-right w3-padding-16 w3-small'>");
           outHtml.append("      <a href='" + request.getContextPath() + "/jsp/sign_up.jsp' class='w3-hover-text-yellow'>");
           outHtml.append("          <fmt:message key='lbl.SignUp' bundle='${rb}'/>");
           outHtml.append("      </a>");
           outHtml.append("      <fmt:message key='lbl.or' bundle='${rb}'/>");
           outHtml.append("      <a href='" + request.getContextPath() + "/jsp/sign_in.jsp' class='w3-hover-text-yellow'>");
           outHtml.append("          <fmt:message key='lbl.SignIn' bundle='${rb}'/>");
           outHtml.append("      </a>");
           outHtml.append("  </div>");
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
