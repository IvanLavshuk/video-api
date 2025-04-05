package bsu.rfe.lavshuk.video.archive.service;

import bsu.rfe.lavshuk.video.archive.dao.UserDAO;
import bsu.rfe.lavshuk.video.archive.entity.User;
import bsu.rfe.lavshuk.video.archive.validator.UserValidator;
import bsu.rfe.lavshuk.video.archive.validator.ValidationException;

import java.util.Optional;

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


    public void createUser(String name, String surname, String password, String email) throws ValidationException {
        try {
            UserValidator.validateUserParameters(name, surname, password, email);
        } catch (ValidationException e) {
            throw e;
        }

        User user = new User();
        user.setPassword(password);
        user.setSurname(surname);
        user.setName(name);
        user.setEmail(email);
        userDAO.create(user);
    }

    public Optional<User> findByEmail(String email) {
        return userDAO.getByEmail(email);
    }

    public Optional<User> getByEmail(String name) {
        return userDAO.getByEmail(name);
    }

}
