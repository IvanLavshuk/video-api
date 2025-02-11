package bsu.rfe.lavshuk.video.archive.servlet;

import bsu.rfe.lavshuk.video.archive.entity.User;
import bsu.rfe.lavshuk.video.archive.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        System.out.println("Init UserServlet");
        userService = UserService.getInstance();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = userService.findByEmail(email);

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/registration.jsp");
            return;
        }

        boolean checkPassword = userService.checkPassword(user.getPassword(), password);
        HttpSession session = request.getSession();
        if (checkPassword) {
            session.setAttribute("user", user.getName() + " " + user.getSurname());
            session.setAttribute("USER", user);
            response.sendRedirect(request.getContextPath() + "/home.jsp");

        } else {
            session.setAttribute("checkPassword", true);
            response.sendRedirect(request.getContextPath() + "/login.jsp");

        }

    }
}
