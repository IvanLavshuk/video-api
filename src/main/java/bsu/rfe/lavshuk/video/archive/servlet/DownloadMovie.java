package bsu.rfe.lavshuk.video.archive.servlet;

import bsu.rfe.lavshuk.video.archive.entity.Movie;
import bsu.rfe.lavshuk.video.archive.service.MovieService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.List;

import static java.lang.System.out;

@WebServlet(name = "DownloadMovieServlet", value = "/downloadMovie")
public class DownloadMovie extends HttpServlet {
    @Override
    public void init() throws ServletException {
        out.println("Init DownloadMovie");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        resp.setHeader("Content-disposition", "attachment; filename=movies.txt");


        File tempFile = File.createTempFile("movies", ".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            List<Movie> movies = MovieService.getInstance().getAll();
            int count = 1;

            for (Movie movie : movies) {
                writer.write((count++) + "." + movie.getTitle() + "(" + movie.getReleaseDate() + ")"
                        + ", " + movie.getGenre() + ", " + movie.getCountry() + ", "
                        + MovieService.getInstance().getDirector(movie.getDirector()));
                writer.newLine();
                writer.newLine();
            }
        }

        try (InputStream in = new FileInputStream(tempFile);
             OutputStream out = resp.getOutputStream()) {
            byte[] buffer = new byte[10000];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }

        tempFile.delete();
    }
}