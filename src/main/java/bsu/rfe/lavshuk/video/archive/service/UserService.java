package bsu.rfe.lavshuk.video.archive.service;

import bsu.rfe.lavshuk.video.archive.dao.UserDAO;
import bsu.rfe.lavshuk.video.archive.entity.User;

public class UserService {
    private volatile static UserService INSTANCE;
    private final UserDAO userDAO;

    private UserService() {
        userDAO = UserDAO.getINSTANCE();
    }

    public static UserService getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (UserService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserService();
                }
            }
        }
        return INSTANCE;
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
