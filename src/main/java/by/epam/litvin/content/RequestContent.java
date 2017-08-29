package by.epam.litvin.content;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static by.epam.litvin.constant.GeneralConstant.SUCCESS;

public class RequestContent {
    private HashMap<String, Object> requestAttributes;
    private HashMap<String, String[]> requestParameters;
    private HashMap<String, Object> sessionAttributes;
    private Map<String, Part> requestParts;
    private JsonObject ajaxResult;

    private String contextPath;
    private String realPath;

    /**
     * Default constructor.
     */
    public RequestContent() {
        requestAttributes = new HashMap<>();
        requestParameters = new HashMap<>();
        sessionAttributes = new HashMap<>();
    }

    /**
     * Extract values from HTTP servlet request.
     *
     * @param request
     * @throws IOException
     * @throws ServletException
     */
    public void extractValues(HttpServletRequest request) throws IOException, ServletException {
        Enumeration<String> attrNames = request.getAttributeNames();
        Enumeration<String> paramNames = request.getParameterNames();
        Enumeration<String> sessionAttrNames = request.getSession().getAttributeNames();

        while (attrNames.hasMoreElements()) {
            String attrName = attrNames.nextElement();
            requestAttributes.put(attrName, request.getAttribute(attrName));
        }

        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            requestParameters.put(paramName, request.getParameterValues(paramName));
        }


        while (sessionAttrNames.hasMoreElements()) {
            String sessionAttr = sessionAttrNames.nextElement();
            sessionAttributes.put(sessionAttr, request.getSession().getAttribute(sessionAttr));
        }

        if (ServletFileUpload.isMultipartContent(request)) {
            requestParts = request.getParts().stream()
                    .collect(Collectors.toMap(Part::getName, x -> x));
        }

        ajaxResult = new JsonObject();
        contextPath = request.getContextPath();
        realPath = request.getServletContext().getRealPath("");
    }


    /**
     * Insert attributes into HTTP servlet request.
     *
     * @param request
     */
    public void insertAttributes(HttpServletRequest request) {
        Enumeration<String> sessionAttrNames = request.getSession().getAttributeNames();
        Enumeration<String> attrNames = request.getAttributeNames();

        while (sessionAttrNames.hasMoreElements()) {
            String sessionAttr = sessionAttrNames.nextElement();
            request.getSession().removeAttribute(sessionAttr);
        }

        while (attrNames.hasMoreElements()) {
            String attrName = attrNames.nextElement();
            request.removeAttribute(attrName);
        }

        requestAttributes.forEach(request::setAttribute);
        sessionAttributes.forEach((key, value) -> request.getSession().setAttribute(key, value));
    }

    /**
     * Get request attributes.
     *
     * @return
     */
    public HashMap<String, Object> getRequestAttributes() {
        return requestAttributes;
    }

    /**
     * Get request parameters.
     *
     * @return
     */
    public HashMap<String, String[]> getRequestParameters() {
        return requestParameters;
    }

    /**
     * Get session attributes.
     *
     * @return
     */
    public HashMap<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }

    /**
     * Get AJAX result.
     *
     * @return
     */
    public JsonObject  getAjaxResult() {
        return ajaxResult;
    }

    /**
     * Set AJAX result.
     *
     * @param ajaxResult
     */
    public void setAjaxResult(JsonObject  ajaxResult) {
        this.ajaxResult = ajaxResult;
    }

    /**
     * Set AJAX success.
     *
     * @param isSuccess
     */
    public void setAjaxSuccess(boolean isSuccess) {
        JsonObject jsonObject = new JsonObject();
        JsonElement element = new Gson().toJsonTree(isSuccess);
        jsonObject.add(SUCCESS, element);

        if (this.ajaxResult == null) {
            this.ajaxResult = jsonObject;

        } else {
            ajaxResult.add(SUCCESS, element);
        }
    }

    /**
     * Get request parts.
     *
     * @return
     */
    public Map<String, Part> getRequestParts() {
        return requestParts;
    }

    /**
     * Get context path.
     *
     * @return
     */
    public String getContextPath() {
        return contextPath;
    }

    /**
     * Get real path.
     *
     * @return
     */
    public String getRealPath() {
        return realPath;
    }
}
