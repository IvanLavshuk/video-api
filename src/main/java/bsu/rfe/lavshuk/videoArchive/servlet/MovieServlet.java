package bsu.rfe.lavshuk.videoArchive.servlet;

import bsu.rfe.lavshuk.videoArchive.entity.Movie;
import bsu.rfe.lavshuk.videoArchive.service.DirectorService;
import bsu.rfe.lavshuk.videoArchive.service.MovieService;
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

@WebServlet(name = "MovieServlet", value = "/Movie")
public class MovieServlet extends HttpServlet {

    private MovieService movieService;
    private DirectorService directorService;

    @Override
    public void init() throws ServletException {
        out.println("Init MovieServlet");
        movieService = MovieService.getInstance();
        directorService = DirectorService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        List<Movie> movies = movieService.getAll();
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<p>" + "Movies : " + "</p>");
        int count = 1;
        for (Movie m : movies) {
            out.println(count++);
            out.println("<p>" + m.getTitle() + "</p>");
            out.println("<p>" + "(" + m.getReleaseDate() + ")" + "</p>");
            out.println("<p>" + m.getGenre() + "</p>");
            out.println("<p>" + m.getCountry() + "</p>");
            out.println("<p></p>");
            out.println("<p></p>");
        }
        out.println("</body></html>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String genre = req.getParameter("genre");
        String country = req.getParameter("country");
        String releaseDate = req.getParameter("releaseDate");
        int director = Integer.parseInt(req.getParameter("director"));
        if (title == null || title.isEmpty() || genre == null || genre.isEmpty()
                || country == null || country.isEmpty() || directorService.isExist(director)) {
            HttpSession session = req.getSession();
            session.setAttribute("Incorrect", true);
            resp.sendRedirect(req.getContextPath() + "/movie.jsp");
            return;
        }

        movieService.createMovie(title, genre, country, releaseDate, director);
        HttpSession session = req.getSession();
        session.setAttribute("movieRegistered", true);
        resp.sendRedirect(req.getContextPath() + "/home.jsp");

    }
}
