package bsu.rfe.lavshuk.video.archive.servlet;

import bsu.rfe.lavshuk.video.archive.service.DirectorService;
import bsu.rfe.lavshuk.video.archive.service.MovieService;
import bsu.rfe.lavshuk.video.archive.validator.ServiceException;
import bsu.rfe.lavshuk.video.archive.validator.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static java.lang.System.out;

@WebServlet(name = "MovieServlet", value = "/Movie")
public class MovieServlet extends HttpServlet {

    private MovieService movieService;
    private DirectorService directorService;

    @Override
    public void init() throws ServletException {
        out.println("Init MovieServlet");
        movieService = MovieService.getINSTANCE();
        directorService = DirectorService.getINSTANCE();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String genre = req.getParameter("genre");
        String country = req.getParameter("country");
        String releaseDate = req.getParameter("releaseDate");
        String directorName = req.getParameter("directorName");
        String directorSurname = req.getParameter("directorSurname");
        HttpSession session = req.getSession();

        try {
            movieService.createMovie(title, genre, country, releaseDate, directorName, directorSurname);
            session.setAttribute("movieRegistered", true);
            resp.sendRedirect(req.getContextPath() + "/home.jsp");
        } catch (ValidationException e) {
            session.setAttribute("Incorrect", true);
            resp.sendRedirect(req.getContextPath() + "/movie.jsp");
        }catch (ServiceException e){
            session.setAttribute("Incorrect director", true);
            resp.sendRedirect(req.getContextPath() + "/movie.jsp");
        }

    }
}
