package example.services;

import example.dao.UserDAO;
import example.entities.User;

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

    public User login(String email, String password) {
        User user = userDAO.getByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        //return null плохо
        return null;
    }
}
