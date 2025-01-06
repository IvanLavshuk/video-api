package bsu.rfe.lavshuk.video.archive.servlet;

import bsu.rfe.lavshuk.video.archive.entity.Director;
import bsu.rfe.lavshuk.video.archive.service.DirectorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.List;

@WebServlet(name = "DownloadDirectorServlet", value = "/downloadDirector")
public class DownloadDirector extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("Init DirectorDownload");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.setHeader("Content-disposition", "attachment; filename=directors.txt");


        File tempFile = File.createTempFile("directors", ".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            List<Director> directors = DirectorService.getInstance().getAll();
            int count = 1;

            for (Director director : directors) {
                writer.write((count++) + "." + director.getName() + " " + director.getSurname()
                        + ", " + director.getBirthdate());
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
