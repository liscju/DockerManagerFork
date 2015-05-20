package pl.edu.agh.controllers.api;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.edu.agh.dao.UserDAO;
import pl.edu.agh.model.User;

public class UserAuthorizator {
    private UserDAO userDAO;

    public UserAuthorizator(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return userDAO.getUser(username);
    }
}
