package by.epam.litvin.controller;

import by.epam.litvin.command.AbstractCommand;
import by.epam.litvin.content.RequestContent;
import by.epam.litvin.factory.FactoryCommand;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AjaxController", urlPatterns = {"/ajaxController"})
public class AjaxController  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        executeRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        executeRequest(req, resp);
    }


    private void executeRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
