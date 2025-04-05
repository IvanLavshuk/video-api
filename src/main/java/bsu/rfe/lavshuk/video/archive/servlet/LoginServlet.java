package bsu.rfe.lavshuk.video.archive.servlet;

import bsu.rfe.lavshuk.video.archive.entity.User;
import bsu.rfe.lavshuk.video.archive.service.UserService;
import bsu.rfe.lavshuk.video.archive.util.PasswordUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        System.out.println("Init UserServlet");
        userService = UserService.getINSTANCE();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Optional<User> optionalUser = userService.findByEmail(email);

        if (!optionalUser.isPresent()) {
            response.sendRedirect(request.getContextPath() + "/registration.jsp");
            return;
        }
        User user = optionalUser.get();
        String passUser = user.getPassword();
        String hashedPassword = PasswordUtil.hash(passUser);
        boolean checkPassword = PasswordUtil.checkPassword(password, hashedPassword);

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
