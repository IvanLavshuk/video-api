package bsu.rfe.lavshuk.videoArchive.servlet;

import bsu.rfe.lavshuk.videoArchive.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        System.out.println("Init UserServlet");
        userService = UserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "register":
                    registerUser(req, resp);
                    break;
                case "login":
                    loginUser(req, resp);

            }
        }

    }

    private void loginUser(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        userService.login(email, password);
    }

    private void registerUser(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        userService.createUser(name, surname, password, email);
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("Destroy UserServlet");
    }
}
