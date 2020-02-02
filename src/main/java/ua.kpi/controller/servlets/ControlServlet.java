package ua.kpi.controller.servlets;

import ua.kpi.controller.command.Command;
import ua.kpi.controller.command.CommandMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(urlPatterns = "/")
public class ControlServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //eq.setAttribute("users", users.values());
        //System.out.println("RequestURI: " + req.getRequestURI());
        //System.out.println("Context path: " + req.getContextPath());
        //req.getRequestDispatcher(index).forward(req, resp); //TODO delete
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //String commandPath = request.getRequestURI().replaceAll(".*/", ""); TODO validate use or delete

        String commandPath = request.getRequestURI();
//        System.out.println("Request URI: " + request.getRequestURI()); //TODO

        Command command = CommandMapper.getCommand(commandPath);
        command.execute(request, response);
    }


}
