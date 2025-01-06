package bsu.rfe.lavshuk.video.archive.servlet;

import bsu.rfe.lavshuk.video.archive.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static java.lang.System.out;

@WebServlet(name = "RegistrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        out.println("Init UserServlet");
        userService = UserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        if (name == null || name.isEmpty() || surname == null || surname.isEmpty()
                || password == null || password.isEmpty() || email == null || email.isEmpty()) {
            HttpSession session = req.getSession();
            session.setAttribute("message", true);
            resp.sendRedirect(req.getContextPath() + "/registration.jsp");
            return;
        }

        userService.createUser(name, surname, password, email);
        HttpSession session = req.getSession();
        session.setAttribute("registered", true);
        resp.sendRedirect(req.getContextPath() + "/home.jsp");
    }
}
