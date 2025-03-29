package bsu.rfe.lavshuk.video.archive.servlet;


import bsu.rfe.lavshuk.video.archive.service.DirectorService;
import bsu.rfe.lavshuk.video.archive.validator.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static java.lang.System.out;

@WebServlet(name = "DirectorServlet", value = "/Director")
public class DirectorServlet extends HttpServlet {

    private DirectorService directorService;

    @Override
    public void init() throws ServletException {
        out.println("Init DirectorServlet");
        directorService = DirectorService.getINSTANCE();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String birthdate = req.getParameter("birthdate");
        HttpSession session = req.getSession();
        try{
            directorService.createDirector(name, surname, birthdate);
            session.setAttribute("directorRegistered", true);
            resp.sendRedirect(req.getContextPath() + "/home.jsp");
        }catch (ValidationException e){
            session.setAttribute("incorrect", true);
            resp.sendRedirect(req.getContextPath() + "/director.jsp");

        }




    }
}
