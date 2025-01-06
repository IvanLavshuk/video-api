package bsu.rfe.lavshuk.video.archive.servlet;

import bsu.rfe.lavshuk.video.archive.entity.User;
import bsu.rfe.lavshuk.video.archive.service.MovieService;
import bsu.rfe.lavshuk.video.archive.service.ReviewService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static java.lang.System.out;

@WebServlet(name = "ReviewServlet", value = "/AddingReview")
public class ReviewServlet extends HttpServlet {
    private ReviewService reviewService;


    @Override
    public void init() throws ServletException {
        out.println("Init ActorServlet");
        reviewService = ReviewService.getInstance();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rating = req.getParameter("rating");
        String text = req.getParameter("text");
        String movie = req.getParameter("movie");
        HttpSession session0 = req.getSession();


        if (rating == null || rating.isEmpty() || text == null || text.isEmpty()
                || movie == null || movie.isEmpty()
                || !MovieService.getInstance().isExistByTitle(movie)
        ) {
            session0.setAttribute("incorrect", true);
            resp.sendRedirect(req.getContextPath() + "/review.jsp");
            return;
        }
        User u = (User) session0.getAttribute("USER");
        session0.removeAttribute("USER");
        double r = Double.parseDouble(rating);
        reviewService.createReview(r, text, movie, u.getEmail());
        session0.setAttribute("reviewRegistered", true);
        resp.sendRedirect(req.getContextPath() + "/home.jsp");
    }

}
