package bsu.rfe.lavshuk.videoArchive.servlet;


import bsu.rfe.lavshuk.videoArchive.service.ActorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static java.lang.System.out;

@WebServlet(name = "ActorServlet", value = "/AddingActor")
public class ActorServlet extends HttpServlet {
    private ActorService actorService;

    @Override
    public void init() throws ServletException {
        out.println("Init ActorServlet");
        actorService = ActorService.getInstance();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("nameA");
        String surname = req.getParameter("surnameA");
        String birthdate = req.getParameter("birthdate");

        if (name == null || name.isEmpty() || surname == null || surname.isEmpty()
                || birthdate == null || birthdate.isEmpty() ) {
            HttpSession session = req.getSession();
            session.setAttribute("incorrect", true);
            resp.sendRedirect(req.getContextPath() + "/actor.jsp");
            return;
        }

        actorService.createActor(name,surname,birthdate);
        HttpSession session = req.getSession();
        session.setAttribute("actorRegistered", true);
        resp.sendRedirect(req.getContextPath() + "/home.jsp");

    }

}
