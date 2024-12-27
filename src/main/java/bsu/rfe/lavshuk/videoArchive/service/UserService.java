package bsu.rfe.lavshuk.videoArchive.service;

import bsu.rfe.lavshuk.videoArchive.dao.UserDAO;
import bsu.rfe.lavshuk.videoArchive.entity.User;

public class UserService {
    private volatile static UserService instance;
    private final UserDAO userDAO;

    private UserService() {
        userDAO = new UserDAO();
    }

    public static UserService getInstance() {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    instance = new UserService();
                }
            }
        }
        return instance;
    }


    public void createUser(String name, String surname, String password, String email) {
        User user = new User();
        user.setPassword(password);
        user.setSurname(surname);
        user.setName(name);
        user.setEmail(email);
        userDAO.create(user);
    }

    public User findByEmail(String email) {
        return userDAO.getByEmail(email);
    }


    public boolean checkPassword(String requestPassword, String userPassword) {
        return userPassword.equals(requestPassword);
    }


}
