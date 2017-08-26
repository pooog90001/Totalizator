package by.epam.litvin.controller;

import by.epam.litvin.command.AbstractCommand;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.factory.FactoryCommand;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
        maxFileSize=1024*1024*10,      // 10MB
        maxRequestSize = 1024 * 1024 * 15) // 15MB
@WebServlet(name = "UploadController", urlPatterns = {"/uploadController"})
public class UploadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }


    private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        AbstractCommand executionCommand;
        RequestContent requestContent = new RequestContent();
        requestContent.extractValues(req);
        executionCommand = new FactoryCommand().initCommand(requestContent);
        executionCommand.execute(requestContent);
        requestContent.insertAttributes(req);
        JsonObject json = requestContent.getAjaxResult();
        resp.getWriter().println(json.toString());
        resp.getWriter().close();
    }
}
