package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }
        if (username.length() < 3) return false;
        if (password.length() < 8) return false;
        
        boolean notAlphaFound = false;
        for (int i = 0; i < password.length(); i++) {
            char charAt2 = password.charAt(i);
            if (!Character.isLetter(charAt2)) {
                notAlphaFound = true;
                break;
            }
        }
        if (!notAlphaFound) return false;

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password

        return false;
    }
}
