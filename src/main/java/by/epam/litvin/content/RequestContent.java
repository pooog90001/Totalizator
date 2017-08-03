package by.epam.litvin.content;

import by.epam.litvin.type.RouteType;
import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;

public class RequestContent {
    private HashMap<String, Object> requestAttributes;
    private HashMap<String, String[]> requestParameters;
    private HashMap<String, Object> sessionAttributes;
    private JsonObject ajaxResult;

    public RequestContent() {
        requestAttributes = new HashMap<>();
        requestParameters = new HashMap<>();
        sessionAttributes = new HashMap<>();
    }

    public void extractValues(HttpServletRequest request) throws IOException {
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

    }


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

    public HashMap<String, Object> getRequestAttributes() {
        return requestAttributes;
    }

    public void setRequestAttributes(HashMap<String, Object> requestAttributes) {
        this.requestAttributes = requestAttributes;
    }

    public HashMap<String, String[]> getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(HashMap<String, String[]> requestParameters) {
        this.requestParameters = requestParameters;
    }

    public HashMap<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }

    public void setSessionAttributes(HashMap<String, Object> sessionAttributes) {
        this.sessionAttributes = sessionAttributes;
    }

    public JsonObject  getAjaxResult() {
        return ajaxResult;
    }

    public void setAjaxResult(JsonObject  ajaxResult) {
        this.ajaxResult = ajaxResult;
    }
}
