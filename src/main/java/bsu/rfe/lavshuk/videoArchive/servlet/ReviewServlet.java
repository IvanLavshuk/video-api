package bsu.rfe.lavshuk.videoArchive.servlet;


import bsu.rfe.lavshuk.videoArchive.service.MovieService;
import bsu.rfe.lavshuk.videoArchive.service.ReviewService;
import bsu.rfe.lavshuk.videoArchive.service.UserService;
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
        String usersEmail = req.getParameter("email");
        if (rating == null || rating.isEmpty() || text == null || text.isEmpty()
                || movie == null || movie.isEmpty() || usersEmail == null || usersEmail.isEmpty()
                || MovieService.getInstance().isExistByTitle(movie) || UserService.getInstance().findByEmail(usersEmail)==null) {
            HttpSession session = req.getSession();
            session.setAttribute("incorrect", true);
            resp.sendRedirect(req.getContextPath() + "/review.jsp");
            return;
        }

        double r = Double.parseDouble(rating);
        reviewService.createReview(r,text,movie,usersEmail);
        HttpSession session = req.getSession();
        session.setAttribute("reviewRegistered", true);
        resp.sendRedirect(req.getContextPath() + "/home.jsp");

    }

}
