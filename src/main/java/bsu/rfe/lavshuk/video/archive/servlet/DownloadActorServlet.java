package bsu.rfe.lavshuk.video.archive.servlet;

import bsu.rfe.lavshuk.video.archive.entity.Actor;
import bsu.rfe.lavshuk.video.archive.service.ActorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "DownloadActorServlet", value = "/downloadActor")
public class DownloadActorServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("Init DownloadActor");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.setHeader("Content-disposition", "attachment; filename=actors.txt");

        try (PrintWriter writer =  resp.getWriter()) {
            List<Actor> actors = ActorService.getINSTANCE().getAll();
            int count = 1;

            for (Actor actor : actors) {
                writer.println(count + ". " + actor.getName() + " " + actor.getSurname() + ", " + actor.getBirthdate());
            }

        }catch (Exception e){
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Error generating download file");
        }


    }
}
