package bsu.rfe.lavshuk.video.archive.servlet;

import bsu.rfe.lavshuk.video.archive.entity.Actor;
import bsu.rfe.lavshuk.video.archive.service.ActorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.List;

@WebServlet(name = "DownloadActorServlet", value = "/downloadActor")
public class DownloadActor extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("Init DownloadActor");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.setHeader("Content-disposition", "attachment; filename=actors.txt");


        File tempFile = File.createTempFile("actors", ".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            List<Actor> actors = ActorService.getInstance().getAll();
            int count = 1;

            for (Actor actor : actors) {
                writer.write((count++) + "." + actor.getName() + " " + actor.getSurname()
                        + ", " + actor.getBirthdate());
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
