package pl.edu.agh.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.edu.agh.dao.UserDAO;
import pl.edu.agh.model.User;

@Service
public class UserAuthorizator {
    @Autowired
    private UserDAO userDAO;

    public UserAuthorizator() {}

    public UserAuthorizator(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return userDAO.getUser(username);
    }
}
