package bsu.rfe.lavshuk.videoArchive.servlet;



import bsu.rfe.lavshuk.videoArchive.entity.Actor;
import bsu.rfe.lavshuk.videoArchive.entity.Director;
import bsu.rfe.lavshuk.videoArchive.service.DirectorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static java.lang.System.out;

@WebServlet(name = "DirectorServlet", value = "/Director")
public class DirectorServlet extends HttpServlet {

    private DirectorService directorService;

    @Override
    public void init() throws ServletException {
        out.println("Init DirectorServlet");
        directorService = DirectorService.getInstance();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        List<Director> directors = directorService.getAll();
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "Directors : " + "</h1>");
        int count = 1;
        for (Director d : directors) {

            out.println("<td>" + (count++) + "." + d.getName() + "</td>");
            out.println("<td>" + d.getSurname() + "</td>");
            out.println("<td>" + d.getBirthdate() + "</td>");
            out.println("<p></p>");

        }
        out.println("</body></html>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String birthdate = req.getParameter("birthdate");

        if (name == null || name.isEmpty() || surname == null || surname.isEmpty()
                || birthdate == null || birthdate.isEmpty() ) {
            HttpSession session = req.getSession();
            session.setAttribute("incorrect", true);
            resp.sendRedirect(req.getContextPath() + "/director.jsp");
            return;
        }

        directorService.createDirector(name,surname,birthdate);
        HttpSession session = req.getSession();
        session.setAttribute("directorRegistered", true);
        resp.sendRedirect(req.getContextPath() + "/home.jsp");

    }
}
